package net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.sep1

import groovy.transform.CompileStatic
import net.sf.jremoterun.utilities.JrrClassUtils
import net.sf.jremoterun.utilities.groovystarter.runners.RunnableFactory
import net.sf.jremoterun.utilities.groovystarter.seqpattern.SeqPatternRunnerEnum
import net.sf.jremoterun.utilities.groovystarter.st.GroovyRunnerConfigurator
import net.sf.jremoterun.utilities.nonjdk.firstdownload.FirstDownloadSettings2
import net.sf.jremoterun.utilities.nonjdk.firstdownload.starter.settings.FirstDownloadSettings

import java.util.logging.Logger

@CompileStatic
class IfFrameworkDownloader extends GroovyRunnerConfigurator {

    private static final Logger log = JrrClassUtils.getJdkLogForCurrentClass();


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
