//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-extensions/src/org/oss/pdfreporter/xml/parsers/XMLErrorHandler.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterXmlParsersXMLErrorHandler_H_
#define _OrgOssPdfreporterXmlParsersXMLErrorHandler_H_

@class OrgOssPdfreporterXmlParsersXMLParseException;

#import "JreEmulation.h"

@protocol OrgOssPdfreporterXmlParsersXMLErrorHandler < NSObject, JavaObject >
- (void)warningWithOrgOssPdfreporterXmlParsersXMLParseException:(OrgOssPdfreporterXmlParsersXMLParseException *)exception;
- (void)errorWithOrgOssPdfreporterXmlParsersXMLParseException:(OrgOssPdfreporterXmlParsersXMLParseException *)exception;
- (void)fatalErrorWithOrgOssPdfreporterXmlParsersXMLParseException:(OrgOssPdfreporterXmlParsersXMLParseException *)exception;
@end

#endif // _OrgOssPdfreporterXmlParsersXMLErrorHandler_H_