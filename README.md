# PDFReporter
The library is a fork of the popular open source Jasper Reports and supports the common features provided by Jasper Reports, but offline and for mobile apps. The PDFReporter library supports iOS, Java and Android library. For your document and report design you use the [Jaspersoft® Studio](http://community.jaspersoft.com/project/jaspersoft-studio) or [PDFReporter Studio](https://sourceforge.net/projects/pdfreporterstudio/)(experimental) where you can visualize your data.

# Licensing
Generally you can use the library as long as your app is open source and published on one of the common repositories on the web. The library is a multi project and supports the following licenses, Apache License V2.0, GNU General Public License version 3.0 (GPLv3), GNU Library or Lesser General Public License version 3.0 (LGPLv3)

If you want to use it commercially please contact sales@pdfreporting.com for further informations. 

# Webpages & Informations
* [Official Webpage](http://www.pdfreporting.com)
* [Github Page](http://opensoftwaresolutions.github.io/PDFReporter/)
* [Wiki Page](https://github.com/OpenSoftwareSolutions/PDFReporter/wiki)
* [Milestones](http://sourceforge.net/p/pdfreporter/tickets/)

# Repositories
You find the core library and ios sample app at sourceforge. The android sample on github. The iOS framework of the library and the static c libraries for iOS (libHaru, libPng) are hosted on github as well.
* [PDFReporter Library (java, android & sample, ios & sample static lib)](https://sourceforge.net/projects/pdfreporter/)
* [PDFReporter Kit (framework)](https://github.com/OpenSoftwareSolutions/PDFReporterKit)
* [Maven Repository](https://github.com/OpenSoftwareSolutions/mvn-repo) (use for Android apps)

# Latest updates
* [PDFReporter Library (branch: android-sample)](https://sourceforge.net/p/pdfreporter/code/ci/android-sample/tree/) android sample 1.3.0-SNAPSHOT
* [PDFReporter Library 1.3.0-SNAPSHOT]() (pdfreporter-android-bundle on branch android-sample)
* [PDFReporter Kit](https://github.com/OpenSoftwareSolutions/PDFReporterKit/releases) Fix for SF ticket #80


# Download the PDFReporter
* [Our Maven Repository - Desktop / Android](https://github.com/OpenSoftwareSolutions/mvn-repo)
* [Jar Files - Desktop ](http://sourceforge.net/projects/pdfreporter/files/Releases/Java/)
* [Jar Files - Android ](http://sourceforge.net/projects/pdfreporter/files/Releases/Android/)
* [iOS ](https://github.com/OpenSoftwareSolutions/PDFReporterKit/releases) (use for iOS apps)

## Using PDFReporter on Windows Applications (experimental)
There are a few ways of using the library in windows apps.
We recommand you to bridge the java library with on of the most common C# solutions - also recommended on the web.
* [jni4net](http://jni4net.com)
* [ikvm ](http://www.ikvm.net)
* [Cross platform IDE] (http://www.mono-project.com/download/)

### Bridge your C# class against the API Class 
* [Wiki on the API ](https://github.com/OpenSoftwareSolutions/PDFReporter/wiki/PDFReporter-API)

    org.oss.pdfreporter.PdfReporter
    
## Using in Maven

### Android

    <dependency>
        <groupId>com.pdfreporting</groupId>
        <artifactId>pdfreporter-android-bundle</artifactId>
        <version>1.X.X</version>
    </dependency>

### Java (Desktop)

    <dependency>
        <groupId>com.pdfreporting</groupId>
        <artifactId>pdfreporter-java-desktop-bundle</artifactId>
        <version>1.X.X</version>
    </dependency>

## add our Repository to your pom.xml

    <repository>
            <id>OpenSoftwareSolutionsMvn</id>
            <name>JSHuntingYard Repo</name>
            <url>"https://github.com/OpenSoftwareSolutions/mvn-repo/raw/master/repository/"</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
            <releases>
                <enabled>true</enabled>
                <updatePolicy>daily</updatePolicy>
            </releases>
    </repository>
    
    
