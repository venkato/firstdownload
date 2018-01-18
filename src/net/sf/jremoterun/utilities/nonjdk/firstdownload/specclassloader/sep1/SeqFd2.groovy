package net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.sep1

import groovy.transform.CompileStatic
import net.sf.jremoterun.utilities.JrrClassUtils
import net.sf.jremoterun.utilities.classpath.ClRef
import net.sf.jremoterun.utilities.groovystarter.runners.ClRefRef
import net.sf.jremoterun.utilities.groovystarter.runners.RunnableFactory
import net.sf.jremoterun.utilities.groovystarter.seqpattern.JrrRunnerPhaseI
import net.sf.jremoterun.utilities.groovystarter.seqpattern.JrrRunnerPhaseI2
import net.sf.jremoterun.utilities.groovystarter.seqpattern.NextPhaseEnumUtil

import java.util.logging.Logger

@CompileStatic
enum SeqFd2 implements JrrRunnerPhaseI2, ClRefRef {


    consoleRedirect(new ClRef('net.sf.jremoterun.utilities.nonjdk.firstdownload.ConsoleRedirectFD'))
    , addMavenIds(new ClRef('net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.AddMavenIds'))
//    , actualSupportAdder(new ClRef('net.sf.jremoterun.utilities.nonjdk.classpath.CustomObjectHandlerSetter2'))
    , addGitHubAndSourceForgeLibs(new ClRef('net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.AddGitHubAndSourceForgeLibs'))
    , addToolsJar(new ClRef('net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.sep2.AddToolsJar'))
//    , compileAll(new ClRef('net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.sep2.CompileAllFd'))
    , afterClassPathAdded(new ClRef('net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.AfterClasspathAdded'))

    ;


    private static final Logger log = JrrClassUtils.getJdkLogForCurrentClass();

    ClRef clRef

    SeqFd2(ClRef cnr2) {
        this.clRef = cnr2
    }

    @Override
    Runnable getRunnerForCurrentPhase() {
        return RunnableFactory.createRunner(clRef)
    }

    @Override
    JrrRunnerPhaseI nextPhase() {
        JrrRunnerPhaseI nextPahse2 = NextPhaseEnumUtil.nextPhase2(this, values()) as JrrRunnerPhaseI
        return nextPahse2
    }
}
