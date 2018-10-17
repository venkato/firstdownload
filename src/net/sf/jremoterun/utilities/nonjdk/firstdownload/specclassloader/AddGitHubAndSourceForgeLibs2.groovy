package net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader

import groovy.transform.CompileStatic
import net.sf.jremoterun.utilities.JrrClassUtils
import net.sf.jremoterun.utilities.groovystarter.GroovyRunnerConfigurator2
import net.sf.jremoterun.utilities.nonjdk.classpath.refs2.CutomJarAdd1

import java.util.logging.Logger

@CompileStatic
class AddGitHubAndSourceForgeLibs2 extends GroovyRunnerConfigurator2 {

    private static final Logger log = JrrClassUtils.getJdkLogForCurrentClass();

    @Override
    void doConfig() {
//        CutomJarAdd.addCustom adder
        adder.add CutomJarAdd1.downloadIdw()
//        adder.add JeditTermCompilerConsoleCompiler.compileIfNeededS()
    }

}
