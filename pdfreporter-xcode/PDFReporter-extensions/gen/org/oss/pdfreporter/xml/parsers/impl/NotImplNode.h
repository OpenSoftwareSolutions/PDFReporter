//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-extensions/src/org/oss/pdfreporter/xml/parsers/impl/NotImplNode.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterXmlParsersImplNotImplNode_H_
#define _OrgOssPdfreporterXmlParsersImplNotImplNode_H_

@protocol OrgOssPdfreporterUsesOrgW3cDomDocument;
@protocol OrgOssPdfreporterUsesOrgW3cDomNamedNodeMap;
@protocol OrgOssPdfreporterUsesOrgW3cDomNodeList;
@protocol OrgOssPdfreporterUsesOrgW3cDomUserDataHandler;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/uses/org/w3c/dom/Node.h"

@interface OrgOssPdfreporterXmlParsersImplNotImplNode : NSObject < OrgOssPdfreporterUsesOrgW3cDomNode > {
}

- (NSString *)getNodeName;
- (NSString *)getNodeValue;
- (void)setNodeValueWithNSString:(NSString *)nodeValue;
- (short int)getNodeType;
- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)getParentNode;
- (id<OrgOssPdfreporterUsesOrgW3cDomNodeList>)getChildNodes;
- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)getFirstChild;
- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)getLastChild;
- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)getPreviousSibling;
- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)getNextSibling;
- (id<OrgOssPdfreporterUsesOrgW3cDomNamedNodeMap>)getAttributes;
- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)insertBeforeWithOrgOssPdfreporterUsesOrgW3cDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)newChild
                                                      withOrgOssPdfreporterUsesOrgW3cDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)refChild;
- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)replaceChildWithOrgOssPdfreporterUsesOrgW3cDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)newChild
                                                      withOrgOssPdfreporterUsesOrgW3cDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)oldChild;
- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)removeChildWithOrgOssPdfreporterUsesOrgW3cDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)oldChild;
- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)appendChildWithOrgOssPdfreporterUsesOrgW3cDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)newChild;
- (BOOL)hasChildNodes;
- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)cloneNodeWithBOOL:(BOOL)deep;
- (void)normalize;
- (BOOL)isSupportedWithNSString:(NSString *)feature
                   withNSString:(NSString *)version_;
- (NSString *)getNamespaceURI;
- (NSString *)getPrefix;
- (void)setPrefixWithNSString:(NSString *)prefix;
- (NSString *)getLocalName;
- (BOOL)hasAttributes;
- (NSString *)getBaseURI;
- (short int)compareDocumentPositionWithOrgOssPdfreporterUsesOrgW3cDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)other;
- (NSString *)getTextContent;
- (void)setTextContentWithNSString:(NSString *)textContent;
- (BOOL)isSameNodeWithOrgOssPdfreporterUsesOrgW3cDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)other;
- (NSString *)lookupPrefixWithNSString:(NSString *)namespaceURI;
- (BOOL)isDefaultNamespaceWithNSString:(NSString *)namespaceURI;
- (NSString *)lookupNamespaceURIWithNSString:(NSString *)prefix;
- (BOOL)isEqualNodeWithOrgOssPdfreporterUsesOrgW3cDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)arg;
- (id)getFeatureWithNSString:(NSString *)feature
                withNSString:(NSString *)version_;
- (id)setUserDataWithNSString:(NSString *)key
                       withId:(id)data
withOrgOssPdfreporterUsesOrgW3cDomUserDataHandler:(id<OrgOssPdfreporterUsesOrgW3cDomUserDataHandler>)handler;
- (id)getUserDataWithNSString:(NSString *)key;
- (id<OrgOssPdfreporterUsesOrgW3cDomDocument>)getOwnerDocument;
- (id)init;
@end

#endif // _OrgOssPdfreporterXmlParsersImplNotImplNode_H_