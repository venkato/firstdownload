package net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.sep1

import groovy.transform.CompileStatic
import net.sf.jremoterun.utilities.JrrClassUtils
import net.sf.jremoterun.utilities.classpath.ClRef
import net.sf.jremoterun.utilities.groovystarter.GroovyMethodRunnerParams
import net.sf.jremoterun.utilities.groovystarter.ShortcutSelector
import net.sf.jremoterun.utilities.groovystarter.runners.ClRefRef
import net.sf.jremoterun.utilities.groovystarter.runners.RunnableFactory
import net.sf.jremoterun.utilities.groovystarter.seqpattern.SeqPatternRunnerEnum
import net.sf.jremoterun.utilities.groovystarter.st.GroovyRunnerConfigurator
import net.sf.jremoterun.utilities.nonjdk.firstdownload.FirstDownloadSettings2
import net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.FirstDownloadSettings

import java.util.logging.Logger

@CompileStatic
class IfFrameworkDownloader extends GroovyRunnerConfigurator {

    private static final Logger log = JrrClassUtils.getJdkLogForCurrentClass();

    static enum ClRefs implements ClRefRef {
        addIffFrameToClassPath(new ClRef('net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.sep1.DownloadIfFrameworkAndAddToClassPath')),
        //        actualSupportAdder(new ClRef('net.sf.jremoterun.utilities.nonjdk.classpath.CustomObjectHandlerSetter2')),
        firstDownloader(new ClRef('net.sf.jremoterun.utilities.nonjdk.firstdownload.FirstDownloader')),
        sshConsoleRunner(new ClRef('net.sf.jremoterun.utilities.nonjdk.firstdownload.SshConsoleRunner')),
        methodNotFoundHandler(new ClRef('net.sf.jremoterun.utilities.nonjdk.consoleprograms.MethodNotFoundRunShell')),
        compileAll(new ClRef('net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.sep2.CompileAllFd'))
        ;

        ClRef clRef

        ClRefs(ClRef clRef) {
            this.clRef = clRef
        }
    }

    public static SeqPatternRunnerEnum seqPatternRunner = new SeqPatternRunnerEnum();

    public static String updateRepo = 'updateRepo';


    @Override
    void doConfig() {
        doConfigImpl()
    }


    void doConfigImpl() {
        decideIfNeedUpdateOnStart()
        FirstDownloadSettings.init()
        RunnableFactory.runRunner ClRefs.addIffFrameToClassPath
//        RunnableWithParamsFactory.fromClass4(ClRefs.actualSupportAdder, FirstDownloadSettings.gitRepoDir)

        if (FirstDownloadSettings.continueRunAfterFirstDownloadedAdded) {
            seqPatternRunner.start(SeqFd2.consoleRedirect)
        }
    }


    void decideIfNeedUpdateOnStart() {
        if (getFirstParam2("${updateRepo} : update repo") == updateRepo) {
            FirstDownloadSettings2.updateRepos = true
            gmrp.args.remove(0)
            log.info "Setting updateRepos = true"
        }
    }



}
