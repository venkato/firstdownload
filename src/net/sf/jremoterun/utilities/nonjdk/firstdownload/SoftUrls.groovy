package net.sf.jremoterun.utilities.nonjdk.firstdownload

import groovy.transform.CompileStatic
import net.sf.jremoterun.utilities.nonjdk.sfdownloader.UrlProvided

@CompileStatic
enum SoftUrls implements UrlProvided {

    sciteEditor('https://bitbucket.org/scite-ru/scite-ru.bitbucket.org/downloads/SciTE_355_102Ru_010517.exe'),
    totalCmd('https://totalcommander.ch/win/tcmd920x64.exe'),
    //    totalCmd ( 'http://totalcommander.ch/win/tcmd920ax64.exe' ),

    gimp('https://download.gimp.org/mirror/pub/gimp/v2.8/windows/gimp-2.8.22-setup.exe'),

    conEmuInstaller('https://www.fosshub.com/ConEmu.html/ConEmuSetup.161206.exe'),

    //    libreOffice ( 'http://download.documentfoundation.org/libreoffice/stable/5.4.3/win/x86/LibreOffice_6.0.5_Win_x86.msi' ),


    fastStone('http://www.faststonesoft.net/DN/FSViewerSetup64.exe'),
    hddscan('http://hddscan.com/download/HDDScan.zip'),
    sysinternals('https://download.sysinternals.com/files/SysinternalsSuite.zip'),
    potplayer('http://get.daum.net/PotPlayer64/Version/Latest/PotPlayerSetup64.exe'),
    babun('http://projects.reficio.org/babun/download'),


    openJdk('https://github.com/ojdkbuild/ojdkbuild/releases/download/1.8.0.141-1/java-1.8.0-openjdk-1.8.0.141-1.b16.ojdkbuild.windows.x86_64.zip'),
    eclipse('http://mirror.switch.ch/eclipse/technology/epp/downloads/release/photon/R/eclipse-committers-photon-R-win32-x86_64.zip'),
    idea('https://download-cf.jetbrains.com/idea/ideaIC-2018.2.win.zip'),
    libreOfficeRu('http://libreoffice-mirror.rbc.ru/pub/libreoffice/libreoffice/stable/6.0.5/win/x86_64/LibreOffice_6.0.5_Win_x64.msi'),
    ;

    String url;

    SoftUrls(String url) {
        this.url = url
    }

    @Override
    URL convertToUrl() {
        return new URL(url)
    }
}



