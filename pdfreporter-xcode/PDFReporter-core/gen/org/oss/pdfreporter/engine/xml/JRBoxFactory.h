//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/xml/JRBoxFactory.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineXmlJRBoxFactory_H_
#define _OrgOssPdfreporterEngineXmlJRBoxFactory_H_

@protocol OrgOssPdfreporterEngineJRLineBox;
@protocol OrgOssPdfreporterXmlParsersIAttributes;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/engine/xml/JRBaseFactory.h"

@interface OrgOssPdfreporterEngineXmlJRBoxFactory : OrgOssPdfreporterEngineXmlJRBaseFactory {
}

- (id)createObjectWithOrgOssPdfreporterXmlParsersIAttributes:(id<OrgOssPdfreporterXmlParsersIAttributes>)atts;
+ (void)setBoxAttributesWithOrgOssPdfreporterXmlParsersIAttributes:(id<OrgOssPdfreporterXmlParsersIAttributes>)atts
                              withOrgOssPdfreporterEngineJRLineBox:(id<OrgOssPdfreporterEngineJRLineBox>)box;
- (id)init;
@end

#endif // _OrgOssPdfreporterEngineXmlJRBoxFactory_H_