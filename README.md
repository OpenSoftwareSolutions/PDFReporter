# PDFReporter
The library is a fork of the popular open source Jasper Reports and supports the common features provided by Jasper Reports, but offline and for mobile apps. The PDFReporter library supports iOS, Java and Android library. For your document and report design you use the PDFReporter Studio where you can visualize your data.

# Licensing
Generally you can use the library as long as your app is open source and published on one of the common repositories on the web. The library is a multi project and supports the following licenses, Apache License V2.0, GNU General Public License version 3.0 (GPLv3), GNU Library or Lesser General Public License version 3.0 (LGPLv3)

If you want to use it commercially please contact sales@pdfreporting.com for further informations. 

# Webpages & Informations
[Official Webpage](http://www.pdfreporting.com)
[Github Page](http://opensoftwaresolutions.github.io/PDFReporter/)
[Wiki Page](https://github.com/OpenSoftwareSolutions/PDFReporter/wiki)
[Milestones](http://sourceforge.net/p/pdfreporter/tickets/)

#Download the PDFReporter
[Our Maven Repository - Desktop / Android](https://github.com/OpenSoftwareSolutions/mvn-repo)
[Jar Files - Desktop ](http://sourceforge.net/projects/pdfreporter/files/Releases/Java/)
[Jar Files - Android ](http://sourceforge.net/projects/pdfreporter/files/Releases/Android/)
[iOS ](http://sourceforge.net/projects/pdfreporter/files/Releases/iOS/)


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



