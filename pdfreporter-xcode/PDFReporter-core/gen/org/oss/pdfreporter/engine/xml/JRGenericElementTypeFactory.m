//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/xml/JRGenericElementTypeFactory.java
//
//  Created by kendra on 9/27/13.
//

#include "org/oss/pdfreporter/engine/JRGenericElementType.h"
#include "org/oss/pdfreporter/engine/xml/JRGenericElementTypeFactory.h"
#include "org/oss/pdfreporter/engine/xml/JRXmlConstants.h"
#include "org/oss/pdfreporter/xml/parsers/IAttributes.h"

@implementation OrgOssPdfreporterEngineXmlJRGenericElementTypeFactory

- (id)createObjectWithOrgOssPdfreporterXmlParsersIAttributes:(id<OrgOssPdfreporterXmlParsersIAttributes>)attrs {
  NSString *namespace_ = [((id<OrgOssPdfreporterXmlParsersIAttributes>) nil_chk(attrs)) getValueWithNSString:[OrgOssPdfreporterEngineXmlJRXmlConstants ATTRIBUTE_namespace]];
  NSString *name = [((id<OrgOssPdfreporterXmlParsersIAttributes>) nil_chk(attrs)) getValueWithNSString:[OrgOssPdfreporterEngineXmlJRXmlConstants ATTRIBUTE_name]];
  return [[OrgOssPdfreporterEngineJRGenericElementType alloc] initWithNSString:namespace_ withNSString:name];
}

- (id)init {
  return [super init];
}

@end