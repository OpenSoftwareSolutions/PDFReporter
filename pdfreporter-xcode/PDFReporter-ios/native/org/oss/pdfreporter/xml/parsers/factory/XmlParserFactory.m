//
//  XmlParserFactory.m
//  JasperReportiOS
//
//  Created by Martin Krasnočka on 5/8/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "XmlParserFactory.h"
#import "org/oss/pdfreporter/xml/parsers/InputSource.h"
#import "org/oss/pdfreporter/registry/IRegistry.h"
#import "LibXmlReaderParser.h"
#import "XmlParser.h"
#import "org/oss/pdfreporter/xml/parsers/impl/DocumentBuilderFactory.h"

@interface OrgOssPdfreporterXmlParsersFactoryXmlParserFactory ()
@property (nonatomic) BOOL validating;
@property (nonatomic) BOOL namespaceAware;
@property (nonatomic) BOOL xincludeAware;
@property (nonatomic) OrgOssPdfreporterXmlParsersImplDocumentBuilderFactory *documentBuilderFactory_;
@end


@implementation OrgOssPdfreporterXmlParsersFactoryXmlParserFactory

-(id)init
{
    self = [super init];
    if (self) {
        self.validating = NO;
        self.namespaceAware = NO;
        self.xincludeAware = NO;
        self.documentBuilderFactory_ = nil;
    }
    return self;
}

+(void)registerFactory
{
    [OrgOssPdfreporterRegistryIRegistry register__WithOrgOssPdfreporterXmlParsersFactoryIXmlParserFactory:[[self alloc] init]];
}

- (id<OrgOssPdfreporterXmlParsersIDocumentBuilderFactory>)newDocumentBuilderFactory
{
    if (self.documentBuilderFactory_ == nil) {
        self.documentBuilderFactory_ = [[OrgOssPdfreporterXmlParsersImplDocumentBuilderFactory alloc] init];
        [self.documentBuilderFactory_ setNamespaceAwareWithBoolean:self.namespaceAware];
        [self.documentBuilderFactory_ setXIncludeAwareWithBoolean:self.xincludeAware];
        [self.documentBuilderFactory_ setValidatingWithBoolean:self.validating];
    }
    return (self.documentBuilderFactory_);
}

- (id<OrgOssPdfreporterXmlParsersIInputSource>)newInputSourceWithJavaIoInputStream:(JavaIoInputStream *)is
{
    OrgOssPdfreporterXmlParsersInputSource *inputSource = [[OrgOssPdfreporterXmlParsersInputSource alloc] initWithJavaIoInputStream:is];
    return inputSource;
}

- (id<OrgOssPdfreporterXmlParsersIInputSource>)newInputSourceWithJavaIoReader:(JavaIoReader *)r
{
    OrgOssPdfreporterXmlParsersInputSource *inputSource = [[OrgOssPdfreporterXmlParsersInputSource alloc] initWithJavaIoReader:r];
    return inputSource;
}

- (id<OrgOssPdfreporterXmlParsersIXmlParser>)newXmlParserWithOrgOssPdfreporterXmlParsersIInputSource:(id<OrgOssPdfreporterXmlParsersIInputSource>)source withOrgOssPdfreporterXmlParsersIContentHandler:(id<OrgOssPdfreporterXmlParsersIContentHandler>)handler
{
    LibXmlReaderParser *parser = [[LibXmlReaderParser alloc] initWithOrgOssPdfreporterXmlParsersIInputSource:source withOrgOssPdfreporterXmlParsersIContentHandler:handler withBoolean:self.validating withBoolean:self.namespaceAware withBoolean:self.xincludeAware];
    return parser;
}

- (void)setNamespaceAwareWithBoolean:(BOOL)aware
{
    self.namespaceAware = aware;
}
- (void)setXIncludeAwareWithBoolean:(BOOL)aware
{
    self.xincludeAware = aware;
}
- (void)setValidatingWithBoolean:(BOOL)validating
{
    self.validating = validating;
}
- (void)configure
{

}

void OrgOssPdfreporterXmlParsersFactoryXmlParserFactory_registerFactory()
{
    [OrgOssPdfreporterXmlParsersFactoryXmlParserFactory registerFactory];
}

@end
