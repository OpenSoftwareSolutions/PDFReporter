//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-extensions/src/org/oss/pdfreporter/xml/parsers/impl/ElementImpl.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterXmlParsersImplElementImpl_H_
#define _OrgOssPdfreporterXmlParsersImplElementImpl_H_

@protocol OrgOssPdfreporterUsesOrgW3cDomAttr;
@protocol OrgOssPdfreporterUsesOrgW3cDomDocument;
@protocol OrgOssPdfreporterUsesOrgW3cDomNodeList;
@protocol OrgOssPdfreporterUsesOrgW3cDomTypeInfo;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/uses/org/w3c/dom/Element.h"
#include "org/oss/pdfreporter/xml/parsers/impl/NodeImpl.h"

@interface OrgOssPdfreporterXmlParsersImplElementImpl : OrgOssPdfreporterXmlParsersImplNodeImpl < OrgOssPdfreporterUsesOrgW3cDomElement > {
}

- (id)initWithOrgOssPdfreporterUsesOrgW3cDomDocument:(id<OrgOssPdfreporterUsesOrgW3cDomDocument>)owner
                                        withNSString:(NSString *)name;
- (NSString *)getTagName;
- (NSString *)getTextContent;
- (NSString *)getAttributeWithNSString:(NSString *)name;
- (void)setAttributeWithNSString:(NSString *)name
                    withNSString:(NSString *)value;
- (void)removeAttributeWithNSString:(NSString *)name;
- (id<OrgOssPdfreporterUsesOrgW3cDomAttr>)getAttributeNodeWithNSString:(NSString *)name;
- (BOOL)hasAttributeWithNSString:(NSString *)name;
- (id<OrgOssPdfreporterUsesOrgW3cDomAttr>)setAttributeNodeWithOrgOssPdfreporterUsesOrgW3cDomAttr:(id<OrgOssPdfreporterUsesOrgW3cDomAttr>)newAttr;
- (id<OrgOssPdfreporterUsesOrgW3cDomAttr>)removeAttributeNodeWithOrgOssPdfreporterUsesOrgW3cDomAttr:(id<OrgOssPdfreporterUsesOrgW3cDomAttr>)oldAttr;
- (id<OrgOssPdfreporterUsesOrgW3cDomNodeList>)getElementsByTagNameWithNSString:(NSString *)name;
- (NSString *)getAttributeNSWithNSString:(NSString *)namespaceURI
                            withNSString:(NSString *)localName;
- (void)setAttributeNSWithNSString:(NSString *)namespaceURI
                      withNSString:(NSString *)qualifiedName
                      withNSString:(NSString *)value;
- (void)removeAttributeNSWithNSString:(NSString *)namespaceURI
                         withNSString:(NSString *)localName;
- (id<OrgOssPdfreporterUsesOrgW3cDomAttr>)getAttributeNodeNSWithNSString:(NSString *)namespaceURI
                                                            withNSString:(NSString *)localName;
- (id<OrgOssPdfreporterUsesOrgW3cDomAttr>)setAttributeNodeNSWithOrgOssPdfreporterUsesOrgW3cDomAttr:(id<OrgOssPdfreporterUsesOrgW3cDomAttr>)newAttr;
- (id<OrgOssPdfreporterUsesOrgW3cDomNodeList>)getElementsByTagNameNSWithNSString:(NSString *)namespaceURI
                                                                    withNSString:(NSString *)localName;
- (BOOL)hasAttributeNSWithNSString:(NSString *)namespaceURI
                      withNSString:(NSString *)localName;
- (id<OrgOssPdfreporterUsesOrgW3cDomTypeInfo>)getSchemaTypeInfo;
- (void)setIdAttributeWithNSString:(NSString *)name
                          withBOOL:(BOOL)isId;
- (void)setIdAttributeNSWithNSString:(NSString *)namespaceURI
                        withNSString:(NSString *)localName
                            withBOOL:(BOOL)isId;
- (void)setIdAttributeNodeWithOrgOssPdfreporterUsesOrgW3cDomAttr:(id<OrgOssPdfreporterUsesOrgW3cDomAttr>)idAttr
                                                        withBOOL:(BOOL)isId;
@end

#endif // _OrgOssPdfreporterXmlParsersImplElementImpl_H_