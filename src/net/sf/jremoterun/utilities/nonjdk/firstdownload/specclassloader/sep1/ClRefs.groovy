package net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.sep1

import groovy.transform.CompileStatic;
import net.sf.jremoterun.utilities.JrrClassUtils
import net.sf.jremoterun.utilities.classpath.ClRef
import net.sf.jremoterun.utilities.groovystarter.runners.ClRefRef;

import java.util.logging.Logger;

enum ClRefs implements ClRefRef {
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
