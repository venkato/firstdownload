package net.sf.jremoterun.utilities.nonjdk.firstdownload

import groovy.transform.CompileStatic
import net.sf.jremoterun.utilities.JrrClassUtils
import net.sf.jremoterun.utilities.classpath.ClRef
import net.sf.jremoterun.utilities.classpath.MavenCommonUtils
import net.sf.jremoterun.utilities.groovystarter.GroovyMethodRunnerParams
import net.sf.jremoterun.utilities.groovystarter.GroovyRunnerConfigurator2
import net.sf.jremoterun.utilities.nonjdk.downloadutils.IffZipRefs
import net.sf.jremoterun.utilities.nonjdk.downloadutils.WinptyDownloader
import net.sf.jremoterun.utilities.nonjdk.vncviewer.VncViewer
import org.junit.Test

import java.util.logging.Logger

@CompileStatic
class SshConsoleRunner implements Runnable{

    private static final Logger log = JrrClassUtils.getJdkLogForCurrentClass();


    MavenCommonUtils mcu = new MavenCommonUtils()

    GroovyMethodRunnerParams gmrp = GroovyMethodRunnerParams.instance

    @Override
    void run() {
        runSshConsole()
    }

    @Test
    void runSshConsole() {
        WinptyDownloader.downloadWinpty();
//        BinaryWithSource redesktop = new RDesktopCompiler().compileAndBuild()
//        gmrp.addFilesToClassLoader.add redesktop
        gmrp.addFilesToClassLoader.add IffZipRefs.weirdx
//        gmrp.addFilesToClassLoader.add WeirdxDownloader.zipUrl2
        gmrp.addFilesToClassLoader.add VncViewer.downloadJar()
        ClRef sshCon = new ClRef('com.jpto.empty.SshEmptyLauncher')
        GroovyRunnerConfigurator2.createRunnerFromClass(sshCon).run()
    }


}
