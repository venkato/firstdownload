package net.sf.jremoterun.utilities.nonjdk.firstdownload;

import net.sf.jremoterun.utilities.JrrClassUtils
import net.sf.jremoterun.utilities.groovystarter.st.GroovyRunnerConfigurator
import net.sf.jremoterun.utilities.nonjdk.ConsoleRedirect
import net.sf.jremoterun.utilities.nonjdk.firstdownload.starter.settings.FirstDownloadSettings

import java.util.logging.Logger;
import groovy.transform.CompileStatic;


@CompileStatic
class ConsoleRedirectFD extends GroovyRunnerConfigurator{

    private static final Logger log = JrrClassUtils.getJdkLogForCurrentClass();

    @Override
    void doConfig() {
        setConsoleOut()
        FirstDownloadSettings2.init2()
    }


    static void setConsoleOut() {
        File outFile = FirstDownloadSettings.outLog;
        outFile.parentFile.mkdirs();
        assert outFile.parentFile.exists();
        ConsoleRedirect.setOutputWithRotationAndFormatter(outFile.absoluteFile, 100);
    }

}
