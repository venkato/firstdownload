import groovy.transform.CompileStatic
import net.sf.jremoterun.utilities.JrrClassUtils
import net.sf.jremoterun.utilities.classpath.ClRef
import net.sf.jremoterun.utilities.classpath.ClassNameReference
import net.sf.jremoterun.utilities.classpath.MavenDefaultSettings
import net.sf.jremoterun.utilities.groovystarter.GroovyRunnerConfigurator2
import net.sf.jremoterun.utilities.groovystarter.runners.RunnableFactory
import net.sf.jremoterun.utilities.groovystarter.st.JrrRunnerPhase2

/**
 * Purpose : put this file to ${user.dir}/jrr/jrrgroovyconfig.groovy and run gr.sh.bat from any place ( having iframework in classpath).
 *
 */
@CompileStatic
class UserConfig extends GroovyRunnerConfigurator2 {

    ClRef initClass = new ClRef('net.sf.jremoterun.utilities.nonjdk.firstdownload.specclassloader.sep8.FdInit3');

    ClRef myInitClass = new ClRef('myinit');

    @Override
    void doConfig() {
        doJob()
    }

    void doJob() {
        File jrrDownloadDir = new File(gmrp.grHome, 'firstdownload/jrrdownload');
        if (jrrDownloadDir.exists()) {
            doJob1();
        }else{
            log.info "not found : ${jrrDownloadDir}";
        }
    }

    void doJob1() {
        File jrrDownloadDir = new File(gmrp.grHome, 'firstdownload/jrrdownload');
        MavenDefaultSettings.mavenDefaultSettings.jrrDownloadDir = jrrDownloadDir;

        File fdSrc = new File(jrrDownloadDir, 'git/https/github.com/venkato/firstdownload/git/src/');
        if (fdSrc.exists()) {
            gmrp.addFilesToClassLoader.add fdSrc
            log.info "initializing FdInit3";
            RunnableFactory.runRunner initClass;
        } else {
            log.info "file not found : ${fdSrc}"
        }

        // gmrp.addFilesToClassLoader.add "" as File ;
//         gmrp.addL(JrrRunnerPhase2.afterClassPathSet,true,myInitClass);
    }

}

