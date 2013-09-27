//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/xml/JRXmlTemplateLoader.java
//
//  Created by kendra on 9/27/13.
//

#include "IOSByteArray.h"
#include "java/io/BufferedInputStream.h"
#include "java/io/ByteArrayInputStream.h"
#include "java/io/File.h"
#include "java/io/FileInputStream.h"
#include "java/io/FileNotFoundException.h"
#include "java/io/IOException.h"
#include "java/io/InputStream.h"
#include "org/oss/pdfreporter/engine/DefaultJasperReportsContext.h"
#include "org/oss/pdfreporter/engine/JRRuntimeException.h"
#include "org/oss/pdfreporter/engine/JRTemplate.h"
#include "org/oss/pdfreporter/engine/JasperReportsContext.h"
#include "org/oss/pdfreporter/engine/xml/JRXmlDigester.h"
#include "org/oss/pdfreporter/engine/xml/JRXmlTemplateDigesterFactory.h"
#include "org/oss/pdfreporter/engine/xml/JRXmlTemplateLoader.h"
#include "org/oss/pdfreporter/net/IURL.h"
#include "org/oss/pdfreporter/registry/IRegistry.h"
#include "org/oss/pdfreporter/repo/RepositoryUtil.h"
#include "org/oss/pdfreporter/xml/parsers/IInputSource.h"
#include "org/oss/pdfreporter/xml/parsers/ParserConfigurationException.h"
#include "org/oss/pdfreporter/xml/parsers/XMLParseException.h"
#include "org/oss/pdfreporter/xml/parsers/factory/IXmlParserFactory.h"

@implementation OrgOssPdfreporterEngineXmlJRXmlTemplateLoader

@synthesize jasperReportsContext = jasperReportsContext_;

- (id)init {
  return [self initOrgOssPdfreporterEngineXmlJRXmlTemplateLoaderWithOrgOssPdfreporterEngineJasperReportsContext:[OrgOssPdfreporterEngineDefaultJasperReportsContext getInstance]];
}

- (id)initOrgOssPdfreporterEngineXmlJRXmlTemplateLoaderWithOrgOssPdfreporterEngineJasperReportsContext:(id<OrgOssPdfreporterEngineJasperReportsContext>)jasperReportsContext {
  if ((self = [super init])) {
    self.jasperReportsContext = jasperReportsContext;
  }
  return self;
}

- (id)initWithOrgOssPdfreporterEngineJasperReportsContext:(id<OrgOssPdfreporterEngineJasperReportsContext>)jasperReportsContext {
  return [self initOrgOssPdfreporterEngineXmlJRXmlTemplateLoaderWithOrgOssPdfreporterEngineJasperReportsContext:jasperReportsContext];
}

+ (OrgOssPdfreporterEngineXmlJRXmlTemplateLoader *)getDefaultInstance {
  return [[OrgOssPdfreporterEngineXmlJRXmlTemplateLoader alloc] initWithOrgOssPdfreporterEngineJasperReportsContext:[OrgOssPdfreporterEngineDefaultJasperReportsContext getInstance]];
}

+ (OrgOssPdfreporterEngineXmlJRXmlTemplateLoader *)getInstanceWithOrgOssPdfreporterEngineJasperReportsContext:(id<OrgOssPdfreporterEngineJasperReportsContext>)jasperReportsContext {
  return [[OrgOssPdfreporterEngineXmlJRXmlTemplateLoader alloc] initWithOrgOssPdfreporterEngineJasperReportsContext:jasperReportsContext];
}

- (id<OrgOssPdfreporterEngineJRTemplate>)loadTemplateWithNSString:(NSString *)location {
  IOSByteArray *data = [((OrgOssPdfreporterRepoRepositoryUtil *) nil_chk([OrgOssPdfreporterRepoRepositoryUtil getInstanceWithOrgOssPdfreporterEngineJasperReportsContext:jasperReportsContext_])) getBytesFromLocationWithNSString:location];
  return [OrgOssPdfreporterEngineXmlJRXmlTemplateLoader load__WithJavaIoInputStream:[[JavaIoByteArrayInputStream alloc] initWithByteArray:data]];
}

- (id<OrgOssPdfreporterEngineJRTemplate>)loadTemplateWithJavaIoFile:(JavaIoFile *)file {
  JavaIoBufferedInputStream *fileIn;
  @try {
    fileIn = [[JavaIoBufferedInputStream alloc] initWithJavaIoInputStream:[[JavaIoFileInputStream alloc] initWithJavaIoFile:file]];
  }
  @catch (JavaIoFileNotFoundException *e) {
    @throw [[OrgOssPdfreporterEngineJRRuntimeException alloc] initWithNSString:@"Template XML file not found" withJavaLangThrowable:e];
  }
  @try {
    return [OrgOssPdfreporterEngineXmlJRXmlTemplateLoader load__WithJavaIoInputStream:fileIn];
  }
  @finally {
    @try {
      [((JavaIoBufferedInputStream *) nil_chk(fileIn)) close];
    }
    @catch (JavaIoIOException *e) {
    }
  }
}

- (id<OrgOssPdfreporterEngineJRTemplate>)loadTemplateWithOrgOssPdfreporterNetIURL:(id<OrgOssPdfreporterNetIURL>)url {
  JavaIoInputStream *input;
  @try {
    input = [((id<OrgOssPdfreporterNetIURL>) nil_chk(url)) openStream];
  }
  @catch (JavaIoIOException *e) {
    @throw [[OrgOssPdfreporterEngineJRRuntimeException alloc] initWithNSString:[NSString stringWithFormat:@"Error opening connection to template IURL %@", url] withJavaLangThrowable:e];
  }
  @try {
    return [OrgOssPdfreporterEngineXmlJRXmlTemplateLoader load__WithJavaIoInputStream:input];
  }
  @finally {
    @try {
      [((JavaIoInputStream *) nil_chk(input)) close];
    }
    @catch (JavaIoIOException *e) {
    }
  }
}

- (id<OrgOssPdfreporterEngineJRTemplate>)loadTemplateWithJavaIoInputStream:(JavaIoInputStream *)data {
  OrgOssPdfreporterEngineXmlJRXmlDigester *digester = [((OrgOssPdfreporterEngineXmlJRXmlTemplateDigesterFactory *) nil_chk([OrgOssPdfreporterEngineXmlJRXmlTemplateDigesterFactory instance])) createDigesterWithOrgOssPdfreporterEngineJasperReportsContext:jasperReportsContext_];
  @try {
    return (id<OrgOssPdfreporterEngineJRTemplate>) [((OrgOssPdfreporterEngineXmlJRXmlDigester *) nil_chk(digester)) parseWithOrgOssPdfreporterXmlParsersIInputSource:[((id<OrgOssPdfreporterXmlParsersFactoryIXmlParserFactory>) nil_chk([OrgOssPdfreporterRegistryIRegistry getIXmlParserFactory])) newInputSourceWithJavaIoInputStream:data]];
  }
  @catch (JavaIoIOException *e) {
    @throw [[OrgOssPdfreporterEngineJRRuntimeException alloc] initWithNSString:@"Error reading template XML" withJavaLangThrowable:e];
  }
  @catch (OrgOssPdfreporterXmlParsersXMLParseException *e) {
    @throw [[OrgOssPdfreporterEngineJRRuntimeException alloc] initWithNSString:@"Error parsing template XML" withJavaLangThrowable:e];
  }
  @catch (OrgOssPdfreporterXmlParsersParserConfigurationException *e) {
    @throw [[OrgOssPdfreporterEngineJRRuntimeException alloc] initWithNSString:@"Error parsing template XML" withJavaLangThrowable:e];
  }
}

+ (id<OrgOssPdfreporterEngineJRTemplate>)load__WithNSString:(NSString *)location {
  return [((OrgOssPdfreporterEngineXmlJRXmlTemplateLoader *) nil_chk([OrgOssPdfreporterEngineXmlJRXmlTemplateLoader getDefaultInstance])) loadTemplateWithNSString:location];
}

+ (id<OrgOssPdfreporterEngineJRTemplate>)load__WithJavaIoFile:(JavaIoFile *)file {
  return [((OrgOssPdfreporterEngineXmlJRXmlTemplateLoader *) nil_chk([OrgOssPdfreporterEngineXmlJRXmlTemplateLoader getDefaultInstance])) loadTemplateWithJavaIoFile:file];
}

+ (id<OrgOssPdfreporterEngineJRTemplate>)load__WithOrgOssPdfreporterNetIURL:(id<OrgOssPdfreporterNetIURL>)url {
  return [((OrgOssPdfreporterEngineXmlJRXmlTemplateLoader *) nil_chk([OrgOssPdfreporterEngineXmlJRXmlTemplateLoader getDefaultInstance])) loadTemplateWithOrgOssPdfreporterNetIURL:url];
}

+ (id<OrgOssPdfreporterEngineJRTemplate>)load__WithJavaIoInputStream:(JavaIoInputStream *)data {
  return [((OrgOssPdfreporterEngineXmlJRXmlTemplateLoader *) nil_chk([OrgOssPdfreporterEngineXmlJRXmlTemplateLoader getDefaultInstance])) loadTemplateWithJavaIoInputStream:data];
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterEngineXmlJRXmlTemplateLoader *typedCopy = (OrgOssPdfreporterEngineXmlJRXmlTemplateLoader *) copy;
  typedCopy.jasperReportsContext = jasperReportsContext_;
}

@end