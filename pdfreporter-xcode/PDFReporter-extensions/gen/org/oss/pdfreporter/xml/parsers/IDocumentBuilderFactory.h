//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-extensions/src/org/oss/pdfreporter/xml/parsers/IDocumentBuilderFactory.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterXmlParsersIDocumentBuilderFactory_H_
#define _OrgOssPdfreporterXmlParsersIDocumentBuilderFactory_H_

@protocol OrgOssPdfreporterXmlParsersIDocumentBuilder;

#import "JreEmulation.h"

@protocol OrgOssPdfreporterXmlParsersIDocumentBuilderFactory < NSObject, JavaObject >
- (id<OrgOssPdfreporterXmlParsersIDocumentBuilder>)newDocumentBuilder OBJC_METHOD_FAMILY_NONE;
- (void)setNamespaceAwareWithBOOL:(BOOL)awareness;
- (void)setValidatingWithBOOL:(BOOL)validating;
- (void)setIgnoringElementContentWhitespaceWithBOOL:(BOOL)whitespace;
- (void)setExpandEntityReferencesWithBOOL:(BOOL)expandEntityRef;
- (void)setIgnoringCommentsWithBOOL:(BOOL)ignoreComments;
- (void)setCoalescingWithBOOL:(BOOL)coalescing;
- (BOOL)isNamespaceAware;
- (BOOL)isValidating;
- (BOOL)isIgnoringElementContentWhitespace;
- (BOOL)isExpandEntityReferences;
- (BOOL)isIgnoringComments;
- (BOOL)isCoalescing;
- (void)setAttributeWithNSString:(NSString *)name
                          withId:(id)value;
- (id)getAttributeWithNSString:(NSString *)name;
- (void)setFeatureWithNSString:(NSString *)name
                      withBOOL:(BOOL)value;
- (BOOL)getFeatureWithNSString:(NSString *)name;
- (void)setXIncludeAwareWithBOOL:(BOOL)state;
- (BOOL)isXIncludeAware;
@end

#endif // _OrgOssPdfreporterXmlParsersIDocumentBuilderFactory_H_