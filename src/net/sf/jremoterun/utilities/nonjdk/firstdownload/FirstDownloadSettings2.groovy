package net.sf.jremoterun.utilities.nonjdk.firstdownload;

import net.sf.jremoterun.utilities.JrrClassUtils
import net.sf.jremoterun.utilities.classpath.MavenCommonUtils
import net.sf.jremoterun.utilities.classpath.MavenDefaultSettings
import net.sf.jremoterun.utilities.groovystarter.GroovyMethodRunnerParams
import net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.FirstDownloadSettings;

import java.util.logging.Logger;
import groovy.transform.CompileStatic;


@CompileStatic
class FirstDownloadSettings2{

    private static final Logger log = JrrClassUtils.getJdkLogForCurrentClass();



    public static String ifFrameWorkGitRef = "https://github.com/venkato/InvocationFramework"


    public static File chocoDir = new File(MavenDefaultSettings.mavenDefaultSettings.jrrDownloadDir,"choco")

    public static File generatedDir = new File(MavenDefaultSettings.mavenDefaultSettings.jrrDownloadDir,"generated")

    public static File sshConsole;

    public static boolean updateRepos = false;
    public static File InvocationFramework;

    private volatile static boolean inited2 = false


    public static File unzipDir = new File(MavenDefaultSettings.mavenDefaultSettings.jrrDownloadDir,"unzip")

    public static MavenCommonUtils mcu = new MavenCommonUtils()

    static void init2(){
        if(!inited2){
            inited2 = true
            chocoDir.mkdir()
            generatedDir.mkdir()
            unzipDir.mkdir()
            assert unzipDir.exists()
        }
    }

}
