//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/xml/JRElementDatasetFactory.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineXmlJRElementDatasetFactory_H_
#define _OrgOssPdfreporterEngineXmlJRElementDatasetFactory_H_

@class OrgOssPdfreporterEngineDesignJRDesignElementDataset;
@protocol OrgOssPdfreporterXmlParsersIAttributes;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/engine/xml/JRBaseFactory.h"

@interface OrgOssPdfreporterEngineXmlJRElementDatasetFactory : OrgOssPdfreporterEngineXmlJRBaseFactory {
}

- (id)createObjectWithOrgOssPdfreporterXmlParsersIAttributes:(id<OrgOssPdfreporterXmlParsersIAttributes>)atts;
- (void)setDatasetAttsWithOrgOssPdfreporterXmlParsersIAttributes:(id<OrgOssPdfreporterXmlParsersIAttributes>)atts
         withOrgOssPdfreporterEngineDesignJRDesignElementDataset:(OrgOssPdfreporterEngineDesignJRDesignElementDataset *)dataset;
- (id)init;
@end

#endif // _OrgOssPdfreporterEngineXmlJRElementDatasetFactory_H_