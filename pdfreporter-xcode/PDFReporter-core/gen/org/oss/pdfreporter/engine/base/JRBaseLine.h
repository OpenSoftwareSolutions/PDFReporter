//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/base/JRBaseLine.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineBaseJRBaseLine_H_
#define _OrgOssPdfreporterEngineBaseJRBaseLine_H_

@class OrgOssPdfreporterEngineBaseJRBaseObjectFactory;
@class OrgOssPdfreporterEngineJRExpressionCollector;
@class OrgOssPdfreporterEngineTypeLineDirectionEnumEnum;
@protocol OrgOssPdfreporterEngineJRVisitor;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/engine/JRLine.h"
#include "org/oss/pdfreporter/engine/base/JRBaseGraphicElement.h"

#define OrgOssPdfreporterEngineBaseJRBaseLine_serialVersionUID 10200

@interface OrgOssPdfreporterEngineBaseJRBaseLine : OrgOssPdfreporterEngineBaseJRBaseGraphicElement < OrgOssPdfreporterEngineJRLine > {
 @public
  OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *directionValue_;
}

@property (nonatomic, strong) OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *directionValue;

+ (NSString *)PROPERTY_DIRECTION;
- (id)initWithOrgOssPdfreporterEngineJRLine:(id<OrgOssPdfreporterEngineJRLine>)line
withOrgOssPdfreporterEngineBaseJRBaseObjectFactory:(OrgOssPdfreporterEngineBaseJRBaseObjectFactory *)factory;
- (void)setWidthWithInt:(int)width;
- (OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *)getDirectionValue;
- (void)setDirectionWithOrgOssPdfreporterEngineTypeLineDirectionEnumEnum:(OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *)directionValue;
- (void)collectExpressionsWithOrgOssPdfreporterEngineJRExpressionCollector:(OrgOssPdfreporterEngineJRExpressionCollector *)collector;
- (void)visitWithOrgOssPdfreporterEngineJRVisitor:(id<OrgOssPdfreporterEngineJRVisitor>)visitor;
@end

#endif // _OrgOssPdfreporterEngineBaseJRBaseLine_H_