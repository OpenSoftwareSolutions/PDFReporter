//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/export/DefaultHyperlinkProducerFactory.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineExportDefaultHyperlinkProducerFactory_H_
#define _OrgOssPdfreporterEngineExportDefaultHyperlinkProducerFactory_H_

@protocol OrgOssPdfreporterEngineExportJRHyperlinkProducer;
@protocol OrgOssPdfreporterEngineJasperReportsContext;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/engine/export/JRHyperlinkProducerFactory.h"

@interface OrgOssPdfreporterEngineExportDefaultHyperlinkProducerFactory : OrgOssPdfreporterEngineExportJRHyperlinkProducerFactory {
 @public
  id<OrgOssPdfreporterEngineJasperReportsContext> jasperReportsContext_;
}

@property (nonatomic, strong) id<OrgOssPdfreporterEngineJasperReportsContext> jasperReportsContext;

- (id)init;
- (id)initWithOrgOssPdfreporterEngineJasperReportsContext:(id<OrgOssPdfreporterEngineJasperReportsContext>)jasperReportsContext;
- (id<OrgOssPdfreporterEngineExportJRHyperlinkProducer>)getHandlerWithNSString:(NSString *)linkType;
@end

#endif // _OrgOssPdfreporterEngineExportDefaultHyperlinkProducerFactory_H_