//
//  XmlParser.m
//  JasperReportiOS
//
//  Created by Martin Krasnočka on 5/11/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "XmlParser.h"
#import "InputStreamMarshaller.h"
#import "org/oss/pdfreporter/xml/parsers/IInputSource.h"
#import "org/oss/pdfreporter/xml/parsers/IContentHandler.h"
#import "Attributes.h"
#import "org/oss/pdfreporter/xml/parsers/XMLErrorHandler.h"
#import "org/oss/pdfreporter/xml/parsers/XMLParseException.h"
#import "org/oss/pdfreporter/xml/parsers/XMLEntityResolver.h"

@implementation XmlParser

#pragma mark OrgOssPdfreporterXmlParsersAbstractXmlParser abstract methods impl

- (void)parse
{
    NSData *parserData = [InputStreamMarshaller convertInputSourceToNSData:[self getInput]];
    [self initParserWithData:parserData];
}

- (void)parseWithOrgOssPdfreporterXmlParsersIInputSource:(id<OrgOssPdfreporterXmlParsersIInputSource>)param0
{
    NSData *parserData = [InputStreamMarshaller convertInputSourceToNSData:param0];
    [self initParserWithData:parserData];
}


#pragma mark private

-(void)initParserWithData:(NSData *)parserData
{
    NSXMLParser *parser = [[NSXMLParser alloc] initWithData:parserData];
    parser.delegate = self;
    [self configureParser:parser];
    [parser parse];
}

-(void)configureParser:(NSXMLParser *)parser
{
    parser.shouldProcessNamespaces = YES;
    parser.shouldReportNamespacePrefixes = [self isNamespaceAware];
    parser.shouldResolveExternalEntities = [self getEntityResolver] != nil;
}

#pragma mark NSXMLParserDelegate

- (void)parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName attributes:(NSDictionary *)attributeDict
{
    id<OrgOssPdfreporterXmlParsersIAttributes> digiAttributes = [[Attributes alloc] initWithDictionary:attributeDict];
    [[self getContentHandler] startElementWithNSString:namespaceURI withNSString:elementName withNSString:qName withOrgOssPdfreporterXmlParsersIAttributes:digiAttributes];
}

- (void)parser:(NSXMLParser *)parser foundCharacters:(NSString *)string
{
    IOSCharArray *charArray = [nil_chk(string) toCharArray];
    [[self getContentHandler] charactersWithCharArray:charArray withInt:0 withInt:[charArray count]];
}

- (void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName
{
    [[self getContentHandler] endElementWithNSString:namespaceURI withNSString:elementName withNSString:qName];
}

- (void)parser:(NSXMLParser *)parser parseErrorOccurred:(NSError *)parseError
{
    id<OrgOssPdfreporterXmlParsersXMLErrorHandler> errorHandler = nil_chk([self getErrorHandler]);
    [errorHandler fatalErrorWithOrgOssPdfreporterXmlParsersXMLParseException:[[OrgOssPdfreporterXmlParsersXMLParseException alloc] initWithNSString:parseError.localizedDescription]];
}

- (void)parser:(NSXMLParser *)parser validationErrorOccurred:(NSError *)validationError
{
    id<OrgOssPdfreporterXmlParsersXMLErrorHandler> errorHandler = nil_chk([self getErrorHandler]);
    [errorHandler errorWithOrgOssPdfreporterXmlParsersXMLParseException:[[OrgOssPdfreporterXmlParsersXMLParseException alloc] initWithNSString:validationError.localizedDescription]];
}

- (NSData *)parser:(NSXMLParser *)parser resolveExternalEntityName:(NSString *)name systemID:(NSString *)systemID
{
    id<OrgOssPdfreporterXmlParsersXMLEntityResolver> entityResolver = [self getEntityResolver];
    id<OrgOssPdfreporterXmlParsersIInputSource> inputSource = [entityResolver resolveEntityWithNSString:name withNSString:systemID];
    return [InputStreamMarshaller convertInputSourceToNSData:inputSource];
}

@end
