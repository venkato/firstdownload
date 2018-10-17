package net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.sep2

import groovy.transform.CompileStatic
import net.sf.jremoterun.utilities.JrrClassUtils
import net.sf.jremoterun.utilities.classpath.MavenCommonUtils
import net.sf.jremoterun.utilities.groovystarter.GroovyRunnerConfigurator2
import net.sf.jremoterun.utilities.nonjdk.classpath.helpers.ClassPathInit3
import net.sf.jremoterun.utilities.nonjdk.firstdownload.FDZipRefs;
import net.sf.jremoterun.utilities.nonjdk.firstdownload.starter.settings.FirstDownloadSettings

import java.util.logging.Logger

@CompileStatic
class AddToolsJar extends GroovyRunnerConfigurator2 {

    private static final Logger log = JrrClassUtils.getJdkLogForCurrentClass();


    public static File toolsJarFile;

    @Override
    void doConfig() {
        ClassPathInit3.addGitRefSupport(adder, FirstDownloadSettings.gitRepoDir)
        addToolsJar()
    }


    void addToolsJar() {
        toolsJarFile = new MavenCommonUtils().getToolsJarFile()
//        if (toolsJarFile == null) {
        if (toolsJarFile == null || !toolsJarFile.exists()) {
            log.info "failed find tools.jar : ${toolsJarFile}, downloading open jdk"
            toolsJarFile = downloadOpenJdkAndFindToolJar();
        }
//        }

        adder.add toolsJarFile
    }


    File downloadOpenJdkAndFindToolJar() {
        File unzip = FDZipRefs.openJdk.resolveToFile();
        File f = unzip.listFiles()[0]
        File f2 = new File(f, 'lib/tools.jar')
        if (!f2.exists()) {
            throw new FileNotFoundException(f2.absolutePath)
        }
        return f2
    }


}
