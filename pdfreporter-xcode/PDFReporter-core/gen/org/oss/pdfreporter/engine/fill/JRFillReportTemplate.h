//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/fill/JRFillReportTemplate.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineFillJRFillReportTemplate_H_
#define _OrgOssPdfreporterEngineFillJRFillReportTemplate_H_

@class OrgOssPdfreporterEngineFillJRBaseFiller;
@class OrgOssPdfreporterEngineFillJRFillObjectFactory;
@protocol OrgOssPdfreporterEngineJRExpression;
@protocol OrgOssPdfreporterEngineJRTemplate;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/engine/JRReportTemplate.h"

@interface OrgOssPdfreporterEngineFillJRFillReportTemplate : NSObject < OrgOssPdfreporterEngineJRReportTemplate > {
 @public
  id<OrgOssPdfreporterEngineJRReportTemplate> parent_;
  OrgOssPdfreporterEngineFillJRBaseFiller *filler_;
}

@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRReportTemplate> parent;
@property (nonatomic, strong) OrgOssPdfreporterEngineFillJRBaseFiller *filler;

- (id)initWithOrgOssPdfreporterEngineJRReportTemplate:(id<OrgOssPdfreporterEngineJRReportTemplate>)template_
          withOrgOssPdfreporterEngineFillJRBaseFiller:(OrgOssPdfreporterEngineFillJRBaseFiller *)filler
   withOrgOssPdfreporterEngineFillJRFillObjectFactory:(OrgOssPdfreporterEngineFillJRFillObjectFactory *)factory;
- (id<OrgOssPdfreporterEngineJRExpression>)getSourceExpression;
- (id<OrgOssPdfreporterEngineJRTemplate>)evaluate;
+ (id<OrgOssPdfreporterEngineJRTemplate>)loadTemplateWithId:(id)source
                withOrgOssPdfreporterEngineFillJRBaseFiller:(OrgOssPdfreporterEngineFillJRBaseFiller *)filler;
@end

#endif // _OrgOssPdfreporterEngineFillJRFillReportTemplate_H_