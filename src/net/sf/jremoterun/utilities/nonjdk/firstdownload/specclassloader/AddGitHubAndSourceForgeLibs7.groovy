package net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader

import groovy.transform.CompileStatic
import net.sf.jremoterun.utilities.JrrClassUtils
import net.sf.jremoterun.utilities.groovystarter.GroovyRunnerConfigurator2
import net.sf.jremoterun.utilities.nonjdk.compile.JeditTermCompilerConsoleCompiler

import java.util.logging.Logger

@CompileStatic
class AddGitHubAndSourceForgeLibs7 extends GroovyRunnerConfigurator2 {

    private static final Logger log = JrrClassUtils.getJdkLogForCurrentClass();

    @Override
    void doConfig() {
//        CutomJarAdd.addCustom adder
//        adder.add CutomJarAdd1.downloadIdw()
        adder.add JeditTermCompilerConsoleCompiler.compileIfNeededSpecialBranchChkout()
    }

}
