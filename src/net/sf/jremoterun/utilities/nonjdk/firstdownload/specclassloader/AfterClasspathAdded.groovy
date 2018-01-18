package net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader

import groovy.transform.CompileStatic
import net.sf.jremoterun.utilities.JrrClassUtils
import net.sf.jremoterun.utilities.groovystarter.GroovyMethodRunnerParams
import net.sf.jremoterun.utilities.groovystarter.ShortcutSelector
import net.sf.jremoterun.utilities.groovystarter.runners.RunnableFactory
import net.sf.jremoterun.utilities.groovystarter.st.GroovyRunnerConfigurator
import net.sf.jremoterun.utilities.nonjdk.consoleprograms.DefaultConsolePrograms
import net.sf.jremoterun.utilities.nonjdk.downloadutils.LessDownloader
import net.sf.jremoterun.utilities.nonjdk.firstdownload.FirstDownloadSettings2
import net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.sep1.IfFrameworkDownloader
import net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.sep2.AddToolsJar
import net.sf.jremoterun.utilities.nonjdk.rstacore.RstaLangSupportStatic
import net.sf.jremoterun.utilities.nonjdk.str2obj.RegisterConverters

import java.util.logging.Logger

@CompileStatic
class AfterClasspathAdded extends GroovyRunnerConfigurator {

    private static final Logger log = JrrClassUtils.getJdkLogForCurrentClass();

    Map m = [
            'd': this.&doRunDefaultClass,
            'ssh': this.&doRunSshConsole,
    ]

    @Override
    void doConfig() {
        registerToolJar()
        RegisterConverters.regConverters2()
        translateArgs()
    }

    void registerToolJar(){
        if (RstaLangSupportStatic.toolsJarFile == null) {
            assert AddToolsJar.toolsJarFile != null
            RstaLangSupportStatic.toolsJarFile = AddToolsJar.toolsJarFile
        }
    }


    void translateArgs() {
        m.putAll(DefaultConsolePrograms.defaultShortcuts2)
        List<String> args = GroovyMethodRunnerParams.gmrp.args
        while (ShortcutSelector.runActionRemoveFirstParam2(m)){

        }
    }

    void doRunSshConsole() {
        RunnableFactory.runRunner IfFrameworkDownloader.ClRefs.compileAll
        List<String> args = GroovyMethodRunnerParams.gmrp.args
        args.add(0,IfFrameworkDownloader.ClRefs.sshConsoleRunner.clRef.className)
    }

    void doRunDefaultClass() {
        List<String> args = GroovyMethodRunnerParams.gmrp.args
        args.add(0,IfFrameworkDownloader.ClRefs.firstDownloader.clRef.className);
        RunnableFactory.runRunner IfFrameworkDownloader.ClRefs.methodNotFoundHandler

    }



    private void createLessViewer() {
        File lessCommand = null
        //LessDownloader.downloadLess()
        File f = new File(FirstDownloadSettings2.generatedDir, 'lessviewer.bat')
        f.text = """

:loop

${lessCommand.absolutePath} %1%

goto loop

"""
        LessDownloader.lessViewer = f
    }


}
