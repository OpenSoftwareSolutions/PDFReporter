//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-extensions/src/org/oss/pdfreporter/xml/parsers/impl/SaxToDomHandler.java
//
//  Created by kendra on 9/27/13.
//

#include "IOSCharArray.h"
#include "org/oss/pdfreporter/uses/org/w3c/dom/Document.h"
#include "org/oss/pdfreporter/uses/org/w3c/dom/Element.h"
#include "org/oss/pdfreporter/uses/org/w3c/dom/Node.h"
#include "org/oss/pdfreporter/uses/org/w3c/dom/Text.h"
#include "org/oss/pdfreporter/xml/parsers/IAttributes.h"
#include "org/oss/pdfreporter/xml/parsers/impl/SaxToDomHandler.h"

@implementation OrgOssPdfreporterXmlParsersImplSaxToDomHandler

@synthesize document = document_;
@synthesize currentNode = currentNode_;

- (id)initWithOrgOssPdfreporterUsesOrgW3cDomDocument:(id<OrgOssPdfreporterUsesOrgW3cDomDocument>)document {
  if ((self = [super init])) {
    self.document = document;
    self.currentNode = document;
  }
  return self;
}

- (void)startElementWithNSString:(NSString *)uri
                    withNSString:(NSString *)localName
                    withNSString:(NSString *)qName
withOrgOssPdfreporterXmlParsersIAttributes:(id<OrgOssPdfreporterXmlParsersIAttributes>)attributes {
  id<OrgOssPdfreporterUsesOrgW3cDomElement> element = [((id<OrgOssPdfreporterUsesOrgW3cDomDocument>) nil_chk(document_)) createElementWithNSString:qName];
  for (int i = 0; i < [((id<OrgOssPdfreporterXmlParsersIAttributes>) nil_chk(attributes)) getLength]; i++) {
    NSString *attrName = [((id<OrgOssPdfreporterXmlParsersIAttributes>) nil_chk(attributes)) getLocalNameWithInt:i];
    if (attrName == nil || [@"" isEqual:attrName]) {
      attrName = [((id<OrgOssPdfreporterXmlParsersIAttributes>) nil_chk(attributes)) getQNameWithInt:i];
    }
    if (attrName != nil || ![@"" isEqual:attrName]) {
      [((id<OrgOssPdfreporterUsesOrgW3cDomElement>) nil_chk(element)) setAttributeWithNSString:attrName withNSString:[((id<OrgOssPdfreporterXmlParsersIAttributes>) nil_chk(attributes)) getValueWithInt:i]];
    }
  }
  (void) [((id<OrgOssPdfreporterUsesOrgW3cDomNode>) nil_chk(currentNode_)) appendChildWithOrgOssPdfreporterUsesOrgW3cDomNode:element];
  currentNode_ = element;
}

- (void)endElementWithNSString:(NSString *)uri
                  withNSString:(NSString *)localName
                  withNSString:(NSString *)qName {
  currentNode_ = [((id<OrgOssPdfreporterUsesOrgW3cDomNode>) nil_chk(currentNode_)) getParentNode];
}

- (void)charactersWithCharArray:(IOSCharArray *)ch
                        withInt:(int)start
                        withInt:(int)length {
  NSString *data = [NSString stringWithCharacters:ch offset:start length:length];
  id<OrgOssPdfreporterUsesOrgW3cDomText> text = [((id<OrgOssPdfreporterUsesOrgW3cDomDocument>) nil_chk(document_)) createTextNodeWithNSString:data];
  (void) [((id<OrgOssPdfreporterUsesOrgW3cDomNode>) nil_chk(currentNode_)) appendChildWithOrgOssPdfreporterUsesOrgW3cDomNode:text];
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterXmlParsersImplSaxToDomHandler *typedCopy = (OrgOssPdfreporterXmlParsersImplSaxToDomHandler *) copy;
  typedCopy.document = document_;
  typedCopy.currentNode = currentNode_;
}

@end