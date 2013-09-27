//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/crosstabs/design/JRDesignCrosstabMeasure.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterCrosstabsDesignJRDesignCrosstabMeasure_H_
#define _OrgOssPdfreporterCrosstabsDesignJRDesignCrosstabMeasure_H_

@class OrgOssPdfreporterCrosstabsTypeCrosstabPercentageEnumEnum;
@class OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport;
@class OrgOssPdfreporterEngineDesignJRDesignVariable;
@class OrgOssPdfreporterEngineTypeCalculationEnumEnum;
@protocol OrgOssPdfreporterEngineJRExpression;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/crosstabs/base/JRBaseCrosstabMeasure.h"
#include "org/oss/pdfreporter/engine/design/events/JRChangeEventsSupport.h"

#define OrgOssPdfreporterCrosstabsDesignJRDesignCrosstabMeasure_serialVersionUID 10200

@interface OrgOssPdfreporterCrosstabsDesignJRDesignCrosstabMeasure : OrgOssPdfreporterCrosstabsBaseJRBaseCrosstabMeasure < OrgOssPdfreporterEngineDesignEventsJRChangeEventsSupport > {
 @public
  OrgOssPdfreporterEngineDesignJRDesignVariable *designVariable_;
  OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport *eventSupport_;
}

@property (nonatomic, strong) OrgOssPdfreporterEngineDesignJRDesignVariable *designVariable;
@property (nonatomic, strong) OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport *eventSupport;

+ (NSString *)PROPERTY_CALCULATION;
+ (NSString *)PROPERTY_INCREMENTER_FACTORY_CLASS_NAME;
+ (NSString *)PROPERTY_NAME;
+ (NSString *)PROPERTY_PERCENTAGE_CALCULATION_CLASS_NAME;
+ (NSString *)PROPERTY_PERCENTAGE_OF_TYPE;
+ (NSString *)PROPERTY_VALUE_CLASS;
+ (NSString *)PROPERTY_VALUE_EXPRESSION;
- (id)init;
- (void)setCalculationWithOrgOssPdfreporterEngineTypeCalculationEnumEnum:(OrgOssPdfreporterEngineTypeCalculationEnumEnum *)calculationValue;
- (void)setValueExpressionWithOrgOssPdfreporterEngineJRExpression:(id<OrgOssPdfreporterEngineJRExpression>)expression;
- (void)setIncrementerFactoryClassNameWithNSString:(NSString *)incrementerFactoryClassName;
- (void)setNameWithNSString:(NSString *)name;
- (void)setPercentageTypeWithOrgOssPdfreporterCrosstabsTypeCrosstabPercentageEnumEnum:(OrgOssPdfreporterCrosstabsTypeCrosstabPercentageEnumEnum *)percentageType;
- (void)setPercentageCalculatorClassNameWithNSString:(NSString *)percentageCalculatorClassName;
- (void)setValueClassNameWithNSString:(NSString *)valueClassName;
- (OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport *)getPropertyChangeSupport;
- (id)clone;
- (OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport *)getEventSupport;
@end

#endif // _OrgOssPdfreporterCrosstabsDesignJRDesignCrosstabMeasure_H_