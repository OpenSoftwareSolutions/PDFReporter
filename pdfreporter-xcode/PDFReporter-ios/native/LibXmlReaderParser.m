//
//  XmlParser.m
//  JasperReportiOS
//
//  Created by Daniel Novak on 5/15/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//
#import "LibXmlReaderParser.h"
#import <libxml/tree.h>
#import <libxml/xmlreader.h>
#import <libxml/xmlIO.h>
#import <libxml/parserInternals.h>
#import "InputStreamMarshaller.h"
#import "org/oss/pdfreporter/xml/parsers/IInputSource.h"
#import "org/oss/pdfreporter/xml/parsers/IContentHandler.h"
#import "Attributes.h"
#import "org/oss/pdfreporter/xml/parsers/XMLErrorHandler.h"
#import "org/oss/pdfreporter/xml/parsers/XMLParseException.h"
#import "org/oss/pdfreporter/xml/parsers/XMLEntityResolver.h"
#import "NSString+JavaString.h"

@implementation LibXmlReaderParser

NSString *uri;
NSString *localName;
NSString *qualifiedName;
NSMutableDictionary* attributeDict;
NSString *value;
id thisClass;
//OrgOssPdfreporterXmlParsersXMLEntityResolver *entityResolver;

- (void)parse
{
    NSData *parserData = [InputStreamMarshaller convertInputSourceToNSData:[self getInput]];
    // TODO: create NSInputStream from JavaIoInputStream
    //entityResolver = getEntityResolver();
    
    //NSInputStream *readData = [[NSInputStream alloc] initWithData:parserData];
    [self configureWithStream:parserData];
}

- (void)parseWithOrgOssPdfreporterXmlParsersIInputSource:(id<OrgOssPdfreporterXmlParsersIInputSource>)param0
{
    //entityResolver = getEntityResolver();
    NSData *parserData = [InputStreamMarshaller convertInputSourceToNSData:param0];
    //NSInputStream *readData = [[NSInputStream alloc] initWithData:parserData];
    [self configureWithStream:parserData];
}

- (void)configureWithStream:(NSData *)data
{
    entityResolver2 = [self getEntityResolver];
    thisClass = self;
    xmlTextReaderPtr reader;
    int ret;
    
    //NSString *jrxmlString = [[NSBundle mainBundle] pathForResource:@"CDBooklet" ofType:@"jrxml" inDirectory:@"testdata/jrxml/cdbooklet"];
    xmlSetExternalEntityLoader(customXmlExternalEntityLoader);
    
    int options = 0;
    
    if ([self getEntityResolver]) {
        options |= XML_PARSE_DTDLOAD;
        options |= XML_PARSE_DTDATTR;
    }
    
    if ([self isNamespaceAware]) {
        
    }
    
    if ([self isXIncludeAware]) {
        options |= XML_PARSE_XINCLUDE;
    }
    
    //reader = xmlReaderForFile([jrxmlString cStringUsingEncoding:NSUTF8StringEncoding], NULL,
    //                          options); /* force load the DTD*/
    reader = xmlReaderForMemory([data bytes], [data length], NULL,
                             IANAEncodingCStringFromNSStringEncoding(NSUTF8StringEncoding),
                             options);
    
    
    if (reader != nil) {
        ret = xmlTextReaderRead(reader);
        while (ret == 1) {
            [self processNodeWithReader:reader];
            ret = xmlTextReaderRead(reader);
        }
        xmlFreeTextReader(reader);
    } else {
        NSLog(@"Unable to read from file");
    }
}

const char *IANAEncodingCStringFromNSStringEncoding(NSStringEncoding encoding)
{
	CFStringEncoding cfEncoding = CFStringConvertNSStringEncodingToEncoding(encoding);
	CFStringRef ianaCharacterSetName = CFStringConvertEncodingToIANACharSetName(cfEncoding);
	return [(__bridge NSString*)ianaCharacterSetName UTF8String];
}

//static int _sg_inputStreamReadCallback (void * context, char * buffer, int len) {
//    NSInputStream *stream = (__bridge NSInputStream *)context;
//    return [stream read:(uint8_t *)buffer maxLength:len];
//}
//static int _sg_inputStreamCloseCallback	(void * context) {
//    NSInputStream *stream = (__bridge NSInputStream *)context;
//    [stream close];
//    return 0;
//}

xmlParserInputPtr customXmlExternalEntityLoader (const char * URL,
                                                 const char * ID,
                                                 xmlParserCtxtPtr context)
{
    // this is just for testing
    //NSString *jrxmlString = [[NSBundle mainBundle] pathForResource:@"jasperprint" ofType:@"dtd"];
    //return fileInput;
    NSString* pubId = nil;
    NSString* systemID = nil;
    if (ID != nil)
    {
        pubId = [NSString stringWithUTF8String:(const char *)ID];
    }
    if (URL != nil)
    {
        systemID = [NSString stringWithUTF8String:(const char *)URL];
    }
    id<OrgOssPdfreporterXmlParsersIInputSource> inputSource = [[thisClass getEntityResolver] resolveEntityWithNSString:pubId withNSString:systemID];

    NSData *parserData = [InputStreamMarshaller convertInputSourceToNSData:inputSource];
    xmlParserInputBufferPtr buffer = xmlParserInputBufferCreateMem([parserData bytes], [parserData length],                                                                  XML_CHAR_ENCODING_UTF8);
    xmlParserInputPtr fileInput= xmlNewIOInputStream(context, buffer, XML_CHAR_ENCODING_UTF8);
    
    return fileInput;
}



xmlNodePtr currentNode;
int currentDepth = -1;
bool wasClosed = NO;

- (void) processNodeWithReader:(xmlTextReaderPtr)reader
{
    int nodeType = xmlTextReaderNodeType(reader);
    //NSString *name = [NSString stringWithUTF8String:(const char *)xmlTextReaderConstName(reader)];
    switch (nodeType)
    {
        case XML_READER_TYPE_ELEMENT : {
            
            int tempDepth = xmlTextReaderDepth(reader);
            if (tempDepth == currentDepth)
            {
                if (!wasClosed)
                {
                    [[self getContentHandler] endElementWithNSString:uri withNSString:localName withNSString:qualifiedName];
                    //NSLog(@"End element %@", localName);
                }

            }
                        
            currentDepth = tempDepth;
            
            // start element
            xmlChar* test = xmlTextReaderNamespaceUri(reader);
            if (test != nil)
            {
                uri = [NSString stringWithUTF8String:(const char *)test];
            }

            localName = [NSString stringWithUTF8String:(const char *)xmlTextReaderLocalName(reader)];
            qualifiedName = [NSString stringWithUTF8String:(const char *)xmlTextReaderName(reader)];
            int attributeCount = xmlTextReaderHasAttributes(reader) == 1 ? xmlTextReaderAttributeCount(reader) : 0;
            attributeDict = [NSMutableDictionary dictionaryWithCapacity:attributeCount];
            while (xmlTextReaderMoveToNextAttribute(reader)) {
                NSString *key = [NSString stringWithUTF8String:(const char *)xmlTextReaderConstName(reader)];
                NSString *value = [NSString stringWithUTF8String:(const char *)xmlTextReaderConstValue(reader)];
                [attributeDict setValue:value forKey:key];
            }
            id<OrgOssPdfreporterXmlParsersIAttributes> digiAttributes = [[Attributes alloc] initWithDictionary:attributeDict];
            
                        //special case <a/> is an empty element, we have to report the end element right away
            [[self getContentHandler] startElementWithNSString:uri withNSString:localName withNSString:qualifiedName withOrgOssPdfreporterXmlParsersIAttributes:digiAttributes];

            //NSLog(@"Start element. Uri %@, LocalName %@, QualifiedName %@, Attributes %@", uri, localName, qualifiedName, attributeDict);
            //NSLog(@"Start element %@", localName);
            
            if (xmlTextReaderIsEmptyElement(reader))
            {
                wasClosed = YES;
                [[self getContentHandler] endElementWithNSString:uri withNSString:localName withNSString:qualifiedName];
                //NSLog(@"End element %@", localName);
            } else {
                wasClosed = NO;
            }
            
            currentNode = xmlTextReaderCurrentNode(reader);
            break;
            
        }
        case XML_READER_TYPE_END_ELEMENT: {
            int tempDepth = xmlTextReaderDepth(reader);
            if (tempDepth < currentDepth)
            {
                if (!wasClosed)
                {
                    [[self getContentHandler] endElementWithNSString:uri withNSString:localName withNSString:qualifiedName];
                    //NSLog(@"End element %@", localName);
                } 
                
            }
            currentDepth = tempDepth;
            wasClosed = YES;
            // end element
            
            uri = nil;
            unsigned char *tmp = xmlTextReaderNamespaceUri(reader); 
            if(tmp) uri = [NSString stringWithUTF8String:(const char *)tmp]; // in som cases uri was null
            
            localName = [NSString stringWithUTF8String:(const char *)xmlTextReaderLocalName(reader)];
            qualifiedName = [NSString stringWithUTF8String:(const char *)xmlTextReaderName(reader)];
            //NSLog(@"End element - Uri %@, LocalName %@, QualifiedName %@", uri, localName, qualifiedName);
            [[self getContentHandler] endElementWithNSString:uri withNSString:localName withNSString:qualifiedName];
            //NSLog(@"End element %@", localName);
            break;
        }
            
        case XML_READER_TYPE_TEXT:
        case XML_READER_TYPE_CDATA:
        {
            // text element, CDATA
            value = [NSString stringWithUTF8String:(const char *)xmlTextReaderConstValue(reader)];
            IOSCharArray *charArray = [nil_chk(value) toCharArray];
            [[self getContentHandler] charactersWithCharArray:charArray withInt:0 withInt:[charArray length]];
            //NSLog(@"Characters - %@", value);
            break;
        }
    }
}

@end
