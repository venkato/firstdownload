package net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.sep1

import groovy.transform.CompileStatic
import net.sf.jremoterun.utilities.JrrClassUtils
import net.sf.jremoterun.utilities.classpath.ClRef
import net.sf.jremoterun.utilities.groovystarter.GroovyRunnerConfigurator2
import net.sf.jremoterun.utilities.groovystarter.runners.RunnableWithParamsFactory
import net.sf.jremoterun.utilities.nonjdk.firstdownload.FirstDownloadSettings2
import net.sf.jremoterun.utilities.nonjdk.firstdownload.starter.SelfUpdater
import net.sf.jremoterun.utilities.nonjdk.firstdownload.starter.CloneGitRepo
import net.sf.jremoterun.utilities.nonjdk.firstdownload.starter.CloneGitRepo3
import net.sf.jremoterun.utilities.nonjdk.firstdownload.starter.settings.FirstDownloadSettings

import java.util.logging.Logger

@CompileStatic
class DownloadIfFrameworkAndAddToClassPath extends GroovyRunnerConfigurator2 {

    private static final Logger log = JrrClassUtils.getJdkLogForCurrentClass();


    public
    static ClRef ifFrameWoekAdder = new ClRef('net.sf.jremoterun.utilities.nonjdk.InfocationFrameworkStructure')


    CloneGitRepo3 cloneGitRepo3;

    @Override
    void doConfig() {
        testGitRepo()
        addToClassPath()
    }

    void testGitRepo() {
        testDownload();
        if (FirstDownloadSettings2.updateRepos) {
            CloneGitRepo.updateGitRepo(FirstDownloadSettings2.InvocationFramework)
            SelfUpdater.doJob2()
        }
    }


    void testDownload() {
        FirstDownloadSettings.gitRepoDir.mkdir()
        if (FirstDownloadSettings2.InvocationFramework == null) {
            FirstDownloadSettings2.InvocationFramework = cloneGitRepo()
        }

    }



    void addToClassPath() {
        adder.add FirstDownloadSettings2.InvocationFramework.child("src-frameworkloader")
        RunnableWithParamsFactory.fromClass4(ifFrameWoekAdder, [adder, FirstDownloadSettings2.InvocationFramework])

    }


    File cloneGitRepo() {
        if (cloneGitRepo3 == null) {
            cloneGitRepo3 = new CloneGitRepo3(FirstDownloadSettings.gitRepoDir);
        }
        return cloneGitRepo3.cloneGitRepo3(FirstDownloadSettings2.ifFrameWorkGitRef,'main')
    }

}
