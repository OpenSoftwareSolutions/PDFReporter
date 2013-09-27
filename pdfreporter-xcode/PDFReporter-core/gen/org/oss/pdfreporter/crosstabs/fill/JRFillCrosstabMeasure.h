//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/crosstabs/fill/JRFillCrosstabMeasure.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterCrosstabsFillJRFillCrosstabMeasure_H_
#define _OrgOssPdfreporterCrosstabsFillJRFillCrosstabMeasure_H_

@class IOSClass;
@class OrgOssPdfreporterCrosstabsFillJRFillCrosstabObjectFactory;
@class OrgOssPdfreporterCrosstabsTypeCrosstabPercentageEnumEnum;
@class OrgOssPdfreporterEngineFillJRFillVariable;
@class OrgOssPdfreporterEngineTypeCalculationEnumEnum;
@protocol OrgOssPdfreporterCrosstabsFillJRPercentageCalculator;
@protocol OrgOssPdfreporterEngineFillJRExtendedIncrementerFactory;
@protocol OrgOssPdfreporterEngineJRExpression;
@protocol OrgOssPdfreporterEngineJRVariable;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/crosstabs/JRCrosstabMeasure.h"

@interface OrgOssPdfreporterCrosstabsFillJRFillCrosstabMeasure : NSObject < OrgOssPdfreporterCrosstabsJRCrosstabMeasure > {
 @public
  id<OrgOssPdfreporterCrosstabsJRCrosstabMeasure> parentMeasure_;
  OrgOssPdfreporterEngineFillJRFillVariable *variable_;
  id<OrgOssPdfreporterEngineFillJRExtendedIncrementerFactory> incrementerFactory_;
  id<OrgOssPdfreporterCrosstabsFillJRPercentageCalculator> percentageCalculator_;
}

@property (nonatomic, strong) id<OrgOssPdfreporterCrosstabsJRCrosstabMeasure> parentMeasure;
@property (nonatomic, strong) OrgOssPdfreporterEngineFillJRFillVariable *variable;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineFillJRExtendedIncrementerFactory> incrementerFactory;
@property (nonatomic, strong) id<OrgOssPdfreporterCrosstabsFillJRPercentageCalculator> percentageCalculator;

- (id)initWithOrgOssPdfreporterCrosstabsJRCrosstabMeasure:(id<OrgOssPdfreporterCrosstabsJRCrosstabMeasure>)measure
withOrgOssPdfreporterCrosstabsFillJRFillCrosstabObjectFactory:(OrgOssPdfreporterCrosstabsFillJRFillCrosstabObjectFactory *)factory;
- (NSString *)getName;
- (NSString *)getValueClassName;
- (IOSClass *)getValueClass;
- (id<OrgOssPdfreporterEngineJRExpression>)getValueExpression;
- (OrgOssPdfreporterEngineTypeCalculationEnumEnum *)getCalculationValue;
- (NSString *)getIncrementerFactoryClassName;
- (IOSClass *)getIncrementerFactoryClass;
- (OrgOssPdfreporterCrosstabsTypeCrosstabPercentageEnumEnum *)getPercentageType;
- (id<OrgOssPdfreporterEngineJRVariable>)getVariable;
- (OrgOssPdfreporterEngineFillJRFillVariable *)getFillVariable;
- (id<OrgOssPdfreporterEngineFillJRExtendedIncrementerFactory>)getIncrementerFactory;
- (id<OrgOssPdfreporterCrosstabsFillJRPercentageCalculator>)getPercentageCalculator;
- (id<OrgOssPdfreporterEngineFillJRExtendedIncrementerFactory>)createIncrementerFactory;
- (id<OrgOssPdfreporterCrosstabsFillJRPercentageCalculator>)createPercentageCalculator;
- (NSString *)getPercentageCalculatorClassName;
- (IOSClass *)getPercentageCalculatorClass;
- (id)clone;
- (id)copyWithZone:(NSZone *)zone;
@end

#endif // _OrgOssPdfreporterCrosstabsFillJRFillCrosstabMeasure_H_