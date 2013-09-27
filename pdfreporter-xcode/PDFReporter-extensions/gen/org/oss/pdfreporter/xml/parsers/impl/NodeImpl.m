//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-extensions/src/org/oss/pdfreporter/xml/parsers/impl/NodeImpl.java
//
//  Created by kendra on 9/27/13.
//

#include "java/util/ArrayList.h"
#include "java/util/List.h"
#include "org/oss/pdfreporter/uses/org/w3c/dom/Document.h"
#include "org/oss/pdfreporter/uses/org/w3c/dom/NamedNodeMap.h"
#include "org/oss/pdfreporter/uses/org/w3c/dom/Node.h"
#include "org/oss/pdfreporter/uses/org/w3c/dom/NodeList.h"
#include "org/oss/pdfreporter/xml/parsers/impl/NamedNodeMapImpl.h"
#include "org/oss/pdfreporter/xml/parsers/impl/NodeImpl.h"

@implementation OrgOssPdfreporterXmlParsersImplNodeImpl

@synthesize owner = owner_;
@synthesize type = type_;
@synthesize name = name_;
@synthesize value = value_;
@synthesize children = children_;
@synthesize attributes = attributes_;
@synthesize parent = parent_;

- (id)initWithOrgOssPdfreporterUsesOrgW3cDomDocument:(id<OrgOssPdfreporterUsesOrgW3cDomDocument>)owner
                                        withShortInt:(short int)type
                                        withNSString:(NSString *)name
                                        withNSString:(NSString *)value {
  if ((self = [super init])) {
    parent_ = nil;
    self.owner = owner;
    self.type = type;
    self.name = name;
    self.value = value;
    self.children = [[OrgOssPdfreporterXmlParsersImplNodeImpl_NodeListImpl alloc] initWithOrgOssPdfreporterUsesOrgW3cDomNode:self];
    self.attributes = [[OrgOssPdfreporterXmlParsersImplNamedNodeMapImpl alloc] initWithOrgOssPdfreporterUsesOrgW3cDomDocument:owner];
  }
  return self;
}

- (id<OrgOssPdfreporterUsesOrgW3cDomNamedNodeMap>)getAttributes {
  return attributes_;
}

- (BOOL)hasAttributes {
  return [((id<OrgOssPdfreporterUsesOrgW3cDomNamedNodeMap>) nil_chk(attributes_)) getLength] > 0;
}

- (NSString *)getNodeName {
  return name_;
}

- (NSString *)getNodeValue {
  return value_;
}

- (short int)getNodeType {
  return type_;
}

- (id<OrgOssPdfreporterUsesOrgW3cDomNodeList>)getChildNodes {
  return children_;
}

- (BOOL)hasChildNodes {
  return [((OrgOssPdfreporterXmlParsersImplNodeImpl_NodeListImpl *) nil_chk(children_)) hasChildNodes];
}

- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)appendChildWithOrgOssPdfreporterUsesOrgW3cDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)newChild {
  return [((OrgOssPdfreporterXmlParsersImplNodeImpl_NodeListImpl *) nil_chk(children_)) addWithOrgOssPdfreporterUsesOrgW3cDomNode:newChild];
}

- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)getFirstChild {
  return [((OrgOssPdfreporterXmlParsersImplNodeImpl_NodeListImpl *) nil_chk(children_)) getFirstChild];
}

- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)getLastChild {
  return [((OrgOssPdfreporterXmlParsersImplNodeImpl_NodeListImpl *) nil_chk(children_)) getLastChild];
}

- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)getPreviousSibling {
  if ([(id) parent_ isKindOfClass:[OrgOssPdfreporterXmlParsersImplNodeImpl class]]) {
    return [((OrgOssPdfreporterXmlParsersImplNodeImpl_NodeListImpl *) nil_chk(((OrgOssPdfreporterXmlParsersImplNodeImpl *) parent_).children)) previousWithOrgOssPdfreporterUsesOrgW3cDomNode:self];
  }
  return nil;
}

- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)getNextSibling {
  if ([(id) parent_ isKindOfClass:[OrgOssPdfreporterXmlParsersImplNodeImpl class]]) {
    return [((OrgOssPdfreporterXmlParsersImplNodeImpl_NodeListImpl *) nil_chk(((OrgOssPdfreporterXmlParsersImplNodeImpl *) parent_).children)) nextWithOrgOssPdfreporterUsesOrgW3cDomNode:self];
  }
  return nil;
}

- (id<OrgOssPdfreporterUsesOrgW3cDomDocument>)getOwnerDocument {
  return owner_;
}

- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)getParentNode {
  return parent_;
}

- (NSString *)description {
  return [NSString stringWithFormat:@"[%@: %@]", name_, value_];
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterXmlParsersImplNodeImpl *typedCopy = (OrgOssPdfreporterXmlParsersImplNodeImpl *) copy;
  typedCopy.owner = owner_;
  typedCopy.type = type_;
  typedCopy.name = name_;
  typedCopy.value = value_;
  typedCopy.children = children_;
  typedCopy.attributes = attributes_;
  typedCopy.parent = parent_;
}

@end
@implementation OrgOssPdfreporterXmlParsersImplNodeImpl_NodeListImpl

@synthesize delegate = delegate_;
@synthesize parent = parent_;

- (id)initWithOrgOssPdfreporterUsesOrgW3cDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)parent {
  if ((self = [super init])) {
    delegate_ = [[JavaUtilArrayList alloc] init];
    self.parent = parent;
  }
  return self;
}

- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)itemWithInt:(int)index {
  return [((id<JavaUtilList>) nil_chk(delegate_)) getWithInt:index];
}

- (int)getLength {
  return [((id<JavaUtilList>) nil_chk(delegate_)) size];
}

- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)addWithOrgOssPdfreporterUsesOrgW3cDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)child {
  [((id<JavaUtilList>) nil_chk(delegate_)) addWithId:child];
  if ([(id) child isKindOfClass:[OrgOssPdfreporterXmlParsersImplNodeImpl class]]) {
    ((OrgOssPdfreporterXmlParsersImplNodeImpl *) child).parent = parent_;
  }
  return child;
}

- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)getFirstChild {
  return [((id<JavaUtilList>) nil_chk(delegate_)) isEmpty] ? nil : [((id<JavaUtilList>) nil_chk(delegate_)) getWithInt:0];
}

- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)getLastChild {
  return [((id<JavaUtilList>) nil_chk(delegate_)) isEmpty] ? nil : [((id<JavaUtilList>) nil_chk(delegate_)) getWithInt:[((id<JavaUtilList>) nil_chk(delegate_)) size] - 1];
}

- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)previousWithOrgOssPdfreporterUsesOrgW3cDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)node {
  int i = [((id<JavaUtilList>) nil_chk(delegate_)) indexOfWithId:node] - 1;
  return i < 0 ? nil : [((id<JavaUtilList>) nil_chk(delegate_)) getWithInt:i];
}

- (id<OrgOssPdfreporterUsesOrgW3cDomNode>)nextWithOrgOssPdfreporterUsesOrgW3cDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)node {
  int i = [((id<JavaUtilList>) nil_chk(delegate_)) indexOfWithId:node] + 1;
  return i < [((id<JavaUtilList>) nil_chk(delegate_)) size] ? [((id<JavaUtilList>) nil_chk(delegate_)) getWithInt:i] : nil;
}

- (BOOL)hasChildNodes {
  return ![((id<JavaUtilList>) nil_chk(delegate_)) isEmpty];
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterXmlParsersImplNodeImpl_NodeListImpl *typedCopy = (OrgOssPdfreporterXmlParsersImplNodeImpl_NodeListImpl *) copy;
  typedCopy.delegate = delegate_;
  typedCopy.parent = parent_;
}

@end