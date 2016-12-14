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
* [Library (java, android, ios)](https://sourceforge.net/projects/pdfreporter/)
* [Android sample app (a bit outdated)](https://github.com/OpenSoftwareSolutions/PDFReporter/tree/master/pdfreporter-android-sample)
* [iOS sample app (static library)](https://github.com/OpenSoftwareSolutions/PDFReporter/tree/master/pdfreporter-xcode/PDFReporter)
* [PDFReporter Kit (framework)](https://github.com/OpenSoftwareSolutions/PDFReporterKit)


# Download the PDFReporter
* [Our Maven Repository - Desktop / Android](https://github.com/OpenSoftwareSolutions/mvn-repo)
* [Jar Files - Desktop ](http://sourceforge.net/projects/pdfreporter/files/Releases/Java/)
* [Jar Files - Android ](http://sourceforge.net/projects/pdfreporter/files/Releases/Android/)
* [iOS ](https://github.com/OpenSoftwareSolutions/PDFReporterKit/releases)

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
    
    
