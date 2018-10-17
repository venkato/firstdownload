package net.sf.jremoterun.utilities.nonjdk.firstdownload

import groovy.transform.CompileStatic
import net.sf.jremoterun.utilities.JrrClassUtils
import net.sf.jremoterun.utilities.classpath.MavenCommonUtils
import net.sf.jremoterun.utilities.groovystarter.GroovyMethodRunnerParams
import net.sf.jremoterun.utilities.nonjdk.classpath.console.ClasspathStatus
import net.sf.jremoterun.utilities.nonjdk.classpath.helpers.UnzipRef
import net.sf.jremoterun.utilities.nonjdk.classpath.refs.starter.JrrStarterOsSpecificFilesSrc
import net.sf.jremoterun.utilities.nonjdk.firstdownload.starter.settings.FirstDownloadSettings
import net.sf.jremoterun.utilities.nonjdk.nativeprocess.NativeProcessResult
import net.sf.jremoterun.utilities.nonjdk.winutils.WinCmdUtils
import org.apache.commons.lang3.SystemUtils

import java.util.logging.Logger

@CompileStatic
class FirstDownloader {

    private static final Logger log = JrrClassUtils.getJdkLogForCurrentClass();


    MavenCommonUtils mcu = new MavenCommonUtils()

//    File gitRepoDir = MavenDefaultSettings.mavenDefaultSettings.jrrDownloadDir

    File scroopScriptFile = new File(SystemUtils.userHome, "scoop/apps/scoop/current/bin/scoop.ps1")

    GroovyMethodRunnerParams gmrp = GroovyMethodRunnerParams.instance


    void testStopUselessServces() {
        WinCmdUtils.stopService('DiagTrack')
    }


    void testScoop() {
        if (scroopScriptFile.exists()) {
            log.info "already exists ${scroopScriptFile}"
        } else {
            File scoopScript = new File(FirstDownloadSettings.firstdownDir, "resources-fd/scoop.PS1")
            assert scoopScript.exists()
            String cmd = "powershell -ExecutionPolicy RemoteSigned -File ${scoopScript}"
            log.info "${cmd}"
            NativeProcessResult.runNativeProcessAndWait cmd
        }
    }


    void testInstallScoop() {
        testScoop()
        assert scroopScriptFile.exists()
        List<String> soft = [
                'maven',
                'gradle',
                // 'oraclejdk', // not exist
                'ant',
                'grep',
                'which',
                'nano',
                'telnet',
                'wget',
                'curl',
                'git',
                'far',
                'groovy',
                '',
                '',
                '',
                '',
                '',

        ]
        soft = soft.findAll { it.length() > 2 }
        String cmd = "powershell ${scroopScriptFile} install ${soft.join(' ')}"
        NativeProcessResult.runNativeProcessAndWait cmd
    }


    void testChoco() {
        File chocoScript = new File(FirstDownloadSettings.firstdownDir, "resources-fd/choco.PS1")
        assert chocoScript.exists()

        String cmd = "powershell -NoProfile -ExecutionPolicy Bypass -File ${chocoScript}"
        NativeProcessResult.runNativeProcessAndWait cmd
    }

    void testInstallChocoPackages() {
        assert FirstDownloadSettings2.chocoDir.exists()
        List<String> packages = [
                'putty.portable',
                'winscp.portable',
                'gitversion.portable',
                '7zip.portable',
                'mpv.portable',
                'git.portable',
                'groovy',
                'intellijidea-community',
                'eclipse',
                'googlechrome',
                'jdk8',
                'procexp',
                'git',
                'foxitreader',
                'totalcommander',
                'notepadplusplus',
                'conemu',
                '',
                '',
                '',
        ]
        packages = packages.findAll { it.length() > 1 }

        //String cmd = "choco.exe install ${packages.join(' ')}"
        String cmd = "choco.exe install ${packages.first()}"
        log.info "running : ${cmd}"
        NativeProcessResult.runNativeProcessAndWait (cmd, FirstDownloadSettings2.chocoDir)
    }

    void testDumpClassPathWise() {
        new ClasspathStatus().dumpClassPathFilesWise2();
    }

    void testSetSysInternalsClassPath() {
//        File sysInternals = UrlDownloadUtils3.urlDownloadUtils.downloadUrlAndUnzip(SoftUrls.sysinternals)
        File sysInternals = FDZipRefs.sysinternals.resolveToFile()
        assert sysInternals.exists()
        sysInternals = sysInternals.absoluteFile.canonicalFile
        File fdDir = JrrStarterOsSpecificFilesSrc.firstdownload.calcGitRefSrc().resolveToFile().canonicalFile.absoluteFile
        NativeProcessResult.runNativeProcessAndWait "setx JRRCOMMANDSPATH ${sysInternals.absolutePath};${fdDir.absolutePath}"
        // GeneralUtils.runNativeProcess( "setx PATH ~JRRCOMMANDSPATH~",null,true)
    }

    void testSetTextFileAsscisation(File file) {
        testSetFileAsscisation("txtfile", file)
    }

    void testSetFileAsscisation(String filetype, File file) {
        assert file.exists()
        assert file.file
        String fileName = file.absolutePath
        if (fileName.contains(' ')) {
            fileName = """"${fileName}" """;
        }
        String cmd = "cmd /c Ftype ${filetype}=${fileName} %1"
        log.info "cmd  = ${cmd}"
        NativeProcessResult.runNativeProcessAndWait cmd
    }

    void setPlayer() {
        File file = "c:/program files/DAUM/PotPlayer/PotPlayerMini64.exe" as File;
        assert file.exists()
        testSetFileAsscisation('custvideo', file)

        List<String> all = 'cmd /C ASSOC'.execute().in.readLines().findAll { it.contains('=WMP11.AssocFile.') }
        all.collect { it.substring(0, it.indexOf('=')).trim() }.each {
            log.info "${it}"
//             testSetFileAsscisation(it,file)
            NativeProcessResult.runNativeProcessAndWait "cmd /c ASSOC ${it}=custvideo"
        }
    }


    void testDownloadConstantWins() {
        List<SoftUrls> urls = SoftUrls.values().toList()
//        List<String> urls = SoftUrls.values().toList().collect {it.url}
        urls.each {
            try {
                new UnzipRef(it).resolveToFile()
//                UrlDownloadUtils3.urlDownloadUtils.downloadUrlAndUnzip(new URL(it.trim()))
            } catch (Exception e) {
                log.warn("failed download ${it}", e)
            }
        }
    }



}
