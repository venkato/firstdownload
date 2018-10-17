package net.sf.jremoterun.utilities.nonjdk.firstdownload

import groovy.transform.CompileStatic;
import net.sf.jremoterun.utilities.JrrClassUtils
import net.sf.jremoterun.utilities.nonjdk.classpath.helpers.UnzipRef

import java.util.logging.Logger;

@CompileStatic
interface FDZipRefs {

    UnzipRef sysinternals =  new UnzipRef(SoftUrls.sysinternals);
    UnzipRef openJdk =  new UnzipRef(SoftUrls.openJdk);



}
