package net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.sep2

import groovy.transform.CompileStatic
import net.sf.jremoterun.utilities.JrrClassUtils
import net.sf.jremoterun.utilities.groovystarter.st.GroovyRunnerConfigurator
import net.sf.jremoterun.utilities.mdep.DropshipClasspath
import net.sf.jremoterun.utilities.nonjdk.classpath.refs.GitSomeRefs
import net.sf.jremoterun.utilities.nonjdk.compile.IfFrameworkCompiler
import net.sf.jremoterun.utilities.nonjdk.consoleprograms.SetConsoleColoring
import net.sf.jremoterun.utilities.nonjdk.firstdownload.FirstDownloadSettings2
import net.sf.jremoterun.utilities.nonjdk.firstdownload.starter.CloneGitRepo
import net.sf.jremoterun.utilities.nonjdk.firstdownload.starter.GitRepoDownloader
import org.apache.commons.io.FileUtils

import java.util.logging.Logger

@CompileStatic
class CompileAllFd extends GroovyRunnerConfigurator {

    private static final Logger log = JrrClassUtils.getJdkLogForCurrentClass();

    @Override
    void doConfig() {
        SetConsoleColoring.setConsoleColoringNoRedirect();
        downloadSshConsole()
        compileIfFramework()
    }


    void compileIfFramework() {
        File outputDir2 = FirstDownloadSettings2.InvocationFramework.child('build/ifbuild2')
        if (FirstDownloadSettings2.updateRepos) {
            FileUtils.deleteQuietly(outputDir2)
            if (outputDir2.exists()) {
                assert outputDir2.listFiles().toList().size() == 0
            }
        }
        IfFrameworkCompiler iff = new IfFrameworkCompiler()
        if (outputDir2.exists()) {

        } else {

            iff.baseDir = FirstDownloadSettings2.InvocationFramework
            iff.client.adder.isLogFileAlreadyAdded = false
            iff.prepare()
            assert AddToolsJar.toolsJarFile != null
            iff.client.adder.add AddToolsJar.toolsJarFile
            iff.client.adderParent.add DropshipClasspath.ivyMavenId
            iff.params.addInDir gmrp.grHome.child('JrrUtilities/src')
            iff.params.addInDir gmrp.grHome.child('JrrStarter/src')
            iff.params.addInDir FirstDownloadSettings2.sshConsole.child('src')

            log.info "compiling .."
            iff.compile()

            FileUtils.copyDirectory(iff.params.outputDir, outputDir2)
            log.info "compiling finished"
        }
        adder.add outputDir2
        adder.add iff.client.extMethodsCompiledClasses
    }

    void downloadSshConsole() {
        if (FirstDownloadSettings2.sshConsole == null) {
            FirstDownloadSettings2.sshConsole = new GitRepoDownloader().cloneGitRepo(GitSomeRefs.sshConsole.gitSpec.repo,'master')
            if (FirstDownloadSettings2.updateRepos) {
                CloneGitRepo.updateGitRepo(FirstDownloadSettings2.sshConsole)
            }
        }
        adder.add new File(FirstDownloadSettings2.sshConsole, 'images')
    }


}
