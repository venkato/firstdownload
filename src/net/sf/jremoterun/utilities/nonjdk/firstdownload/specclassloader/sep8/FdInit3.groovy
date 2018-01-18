package net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.sep8

import groovy.transform.CompileStatic
import net.sf.jremoterun.utilities.JrrClassUtils
import net.sf.jremoterun.utilities.classpath.AddFilesToUrlClassLoaderGroovy
import net.sf.jremoterun.utilities.classpath.MavenDefaultSettings
import net.sf.jremoterun.utilities.groovystarter.GroovyRunnerConfigurator2

import java.util.logging.Logger

@CompileStatic
class FdInit3 extends GroovyRunnerConfigurator2 {

    private static final Logger log = JrrClassUtils.getJdkLogForCurrentClass();

    @Override
    void doConfig() {
        File jrrDownloadDir = MavenDefaultSettings.mavenDefaultSettings.jrrDownloadDir
        assert jrrDownloadDir != null

        AddFilesToUrlClassLoaderGroovy adder = gmrp.addFilesToClassLoader

        adder.add jrrDownloadDir.child('git/https/github.com/venkato/starter3/git/firstdownload/src')

        adder.add jrrDownloadDir.child('git/https/github.com/venkato/starter3/git/firstdownload/log4j2-config')

    }
}
