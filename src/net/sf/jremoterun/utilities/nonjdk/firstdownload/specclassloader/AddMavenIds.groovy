package net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader

import groovy.transform.CompileStatic
import net.sf.jremoterun.utilities.JrrClassUtils
import net.sf.jremoterun.utilities.classpath.ClRef
import net.sf.jremoterun.utilities.groovystarter.GroovyRunnerConfigurator2
import net.sf.jremoterun.utilities.groovystarter.runners.RunnableWithParamsFactory

import java.util.logging.Logger

@CompileStatic
class AddMavenIds extends GroovyRunnerConfigurator2 {

    private static final Logger log = JrrClassUtils.getJdkLogForCurrentClass();

    public static ClRef defaultClasspathAdder = new ClRef("net.sf.jremoterun.utilities.nonjdk.classpath.DefaultClasspathAdder")

    public
    static ClRef actualSupportAdder = new ClRef('net.sf.jremoterun.utilities.nonjdk.classpath.CustomObjectHandlerSetter2')

    @Override
    void doConfig() {
        RunnableWithParamsFactory.fromClass4(defaultClasspathAdder, adder)
        RunnableWithParamsFactory.fromClass4(actualSupportAdder, FirstDownloadSettings.gitRepoDir)
    }


}
