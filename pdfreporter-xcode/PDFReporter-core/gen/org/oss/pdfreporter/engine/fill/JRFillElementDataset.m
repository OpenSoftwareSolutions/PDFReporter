//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/fill/JRFillElementDataset.java
//
//  Created by kendra on 9/27/13.
//

#include "java/lang/Boolean.h"
#include "java/lang/UnsupportedOperationException.h"
#include "java/util/TimeZone.h"
#include "org/oss/pdfreporter/engine/JRDatasetRun.h"
#include "org/oss/pdfreporter/engine/JRElementDataset.h"
#include "org/oss/pdfreporter/engine/JRExpression.h"
#include "org/oss/pdfreporter/engine/JRExpressionCollector.h"
#include "org/oss/pdfreporter/engine/JRGroup.h"
#include "org/oss/pdfreporter/engine/fill/JRBaseFiller.h"
#include "org/oss/pdfreporter/engine/fill/JRCalculator.h"
#include "org/oss/pdfreporter/engine/fill/JRFillDataset.h"
#include "org/oss/pdfreporter/engine/fill/JRFillDatasetRun.h"
#include "org/oss/pdfreporter/engine/fill/JRFillElementDataset.h"
#include "org/oss/pdfreporter/engine/fill/JRFillGroup.h"
#include "org/oss/pdfreporter/engine/fill/JRFillObjectFactory.h"
#include "org/oss/pdfreporter/engine/type/IncrementTypeEnum.h"
#include "org/oss/pdfreporter/engine/type/ResetTypeEnum.h"

@implementation OrgOssPdfreporterEngineFillJRFillElementDataset

@synthesize parent = parent_;
@synthesize filler = filler_;
@synthesize resetGroup = resetGroup_;
@synthesize incrementGroup = incrementGroup_;
@synthesize isIncremented = isIncremented_;
@synthesize datasetRun = datasetRun_;
@synthesize increment_ = increment__;

- (id)initWithOrgOssPdfreporterEngineJRElementDataset:(id<OrgOssPdfreporterEngineJRElementDataset>)dataset
   withOrgOssPdfreporterEngineFillJRFillObjectFactory:(OrgOssPdfreporterEngineFillJRFillObjectFactory *)factory {
  if ((self = [super init])) {
    isIncremented_ = YES;
    [((OrgOssPdfreporterEngineFillJRFillObjectFactory *) nil_chk(factory)) putWithId:dataset withId:self];
    parent_ = dataset;
    filler_ = [((OrgOssPdfreporterEngineFillJRFillObjectFactory *) nil_chk(factory)) getFiller];
    resetGroup_ = [((OrgOssPdfreporterEngineFillJRFillObjectFactory *) nil_chk(factory)) getGroupWithOrgOssPdfreporterEngineJRGroup:[((id<OrgOssPdfreporterEngineJRElementDataset>) nil_chk(dataset)) getResetGroup]];
    incrementGroup_ = [((OrgOssPdfreporterEngineFillJRFillObjectFactory *) nil_chk(factory)) getGroupWithOrgOssPdfreporterEngineJRGroup:[((id<OrgOssPdfreporterEngineJRElementDataset>) nil_chk(dataset)) getIncrementGroup]];
    datasetRun_ = [((OrgOssPdfreporterEngineFillJRFillObjectFactory *) nil_chk(factory)) getDatasetRunWithOrgOssPdfreporterEngineJRDatasetRun:[((id<OrgOssPdfreporterEngineJRElementDataset>) nil_chk(dataset)) getDatasetRun]];
  }
  return self;
}

- (OrgOssPdfreporterEngineTypeResetTypeEnumEnum *)getResetTypeValue {
  return [((id<OrgOssPdfreporterEngineJRElementDataset>) nil_chk(parent_)) getResetTypeValue];
}

- (OrgOssPdfreporterEngineTypeIncrementTypeEnumEnum *)getIncrementTypeValue {
  return [((id<OrgOssPdfreporterEngineJRElementDataset>) nil_chk(parent_)) getIncrementTypeValue];
}

- (id<OrgOssPdfreporterEngineJRGroup>)getResetGroup {
  return resetGroup_;
}

- (id<OrgOssPdfreporterEngineJRGroup>)getIncrementGroup {
  return incrementGroup_;
}

- (JavaUtilTimeZone *)getTimeZone {
  return [((OrgOssPdfreporterEngineFillJRBaseFiller *) nil_chk(filler_)) getTimeZone];
}

- (void)initialize__ OBJC_METHOD_FAMILY_NONE {
  [self customInitialize];
  isIncremented_ = NO;
  increment__ = NO;
}

- (void)evaluateWithOrgOssPdfreporterEngineFillJRCalculator:(OrgOssPdfreporterEngineFillJRCalculator *)calculator {
  [self evaluateIncrementWhenExpressionWithOrgOssPdfreporterEngineFillJRCalculator:calculator];
  if (increment__) {
    [self customEvaluateWithOrgOssPdfreporterEngineFillJRCalculator:calculator];
  }
  isIncremented_ = NO;
}

- (void)evaluateIncrementWhenExpressionWithOrgOssPdfreporterEngineFillJRCalculator:(OrgOssPdfreporterEngineFillJRCalculator *)calculator {
  id<OrgOssPdfreporterEngineJRExpression> incrementWhenExpression = [self getIncrementWhenExpression];
  if (incrementWhenExpression == nil) {
    increment__ = YES;
  }
  else {
    JavaLangBoolean *evaluated = (JavaLangBoolean *) [((OrgOssPdfreporterEngineFillJRCalculator *) nil_chk(calculator)) evaluateWithOrgOssPdfreporterEngineJRExpression:incrementWhenExpression];
    increment__ = evaluated != nil && [((JavaLangBoolean *) nil_chk(evaluated)) booleanValue];
  }
}

- (void)increment {
  if (!isIncremented_ && increment__) {
    [self customIncrement];
  }
  isIncremented_ = YES;
}

- (void)customInitialize {
  // can't call an abstract method
  [self doesNotRecognizeSelector:_cmd];
}

- (void)customEvaluateWithOrgOssPdfreporterEngineFillJRCalculator:(OrgOssPdfreporterEngineFillJRCalculator *)calculator {
  // can't call an abstract method
  [self doesNotRecognizeSelector:_cmd];
}

- (void)customIncrement {
  // can't call an abstract method
  [self doesNotRecognizeSelector:_cmd];
}

- (id<OrgOssPdfreporterEngineJRDatasetRun>)getDatasetRun {
  return datasetRun_;
}

- (void)evaluateDatasetRunWithChar:(char)evaluation {
  if (datasetRun_ != nil) {
    [datasetRun_ evaluateWithOrgOssPdfreporterEngineFillJRFillElementDataset:self withChar:evaluation];
  }
}

- (OrgOssPdfreporterEngineFillJRFillDataset *)getInputDataset {
  OrgOssPdfreporterEngineFillJRFillDataset *inputDataset;
  if (datasetRun_ != nil) {
    inputDataset = [datasetRun_ getDataset];
  }
  else {
    inputDataset = ((OrgOssPdfreporterEngineFillJRBaseFiller *) nil_chk(filler_)).mainDataset;
  }
  return inputDataset;
}

- (id<OrgOssPdfreporterEngineJRExpression>)getIncrementWhenExpression {
  return [((id<OrgOssPdfreporterEngineJRElementDataset>) nil_chk(parent_)) getIncrementWhenExpression];
}

- (id)clone {
  @throw [[JavaLangUnsupportedOperationException alloc] init];
}

- (OrgOssPdfreporterEngineFillJRFillDataset *)getFillDataset {
  return datasetRun_ == nil ? [((OrgOssPdfreporterEngineFillJRBaseFiller *) nil_chk(filler_)) getMainDataset] : [((OrgOssPdfreporterEngineFillJRFillDatasetRun *) nil_chk(datasetRun_)) getDataset];
}

- (void)collectExpressionsWithOrgOssPdfreporterEngineJRExpressionCollector:(OrgOssPdfreporterEngineJRExpressionCollector *)param0 {
  // can't call an abstract method
  [self doesNotRecognizeSelector:_cmd];
}

- (id)copyWithZone:(NSZone *)zone {
  return [self clone];
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterEngineFillJRFillElementDataset *typedCopy = (OrgOssPdfreporterEngineFillJRFillElementDataset *) copy;
  typedCopy.parent = parent_;
  typedCopy.filler = filler_;
  typedCopy.resetGroup = resetGroup_;
  typedCopy.incrementGroup = incrementGroup_;
  typedCopy.isIncremented = isIncremented_;
  typedCopy.datasetRun = datasetRun_;
  typedCopy.increment_ = increment__;
}

@end