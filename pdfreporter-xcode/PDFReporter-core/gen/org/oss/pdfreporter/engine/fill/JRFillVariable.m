//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/fill/JRFillVariable.java
//
//  Created by kendra on 9/27/13.
//

#include "IOSClass.h"
#include "IOSObjectArray.h"
#include "java/lang/UnsupportedOperationException.h"
#include "org/oss/pdfreporter/engine/JRExpression.h"
#include "org/oss/pdfreporter/engine/JRGroup.h"
#include "org/oss/pdfreporter/engine/JRVariable.h"
#include "org/oss/pdfreporter/engine/fill/JRAbstractFillObjectFactory.h"
#include "org/oss/pdfreporter/engine/fill/JRCalculable.h"
#include "org/oss/pdfreporter/engine/fill/JRDefaultIncrementerFactory.h"
#include "org/oss/pdfreporter/engine/fill/JRExtendedIncrementerFactory.h"
#include "org/oss/pdfreporter/engine/fill/JRFillVariable.h"
#include "org/oss/pdfreporter/engine/fill/JRIncrementer.h"
#include "org/oss/pdfreporter/engine/fill/JRIncrementerFactory.h"
#include "org/oss/pdfreporter/engine/fill/JRIncrementerFactoryCache.h"
#include "org/oss/pdfreporter/engine/type/CalculationEnum.h"
#include "org/oss/pdfreporter/engine/type/IncrementTypeEnum.h"
#include "org/oss/pdfreporter/engine/type/ResetTypeEnum.h"

@implementation OrgOssPdfreporterEngineFillJRFillVariable

@synthesize parent = parent_;
@synthesize resetGroup = resetGroup_;
@synthesize incrementGroup = incrementGroup_;
@synthesize previousOldValue = previousOldValue_;
@synthesize oldValue = oldValue_;
@synthesize estimatedValue = estimatedValue_;
@synthesize incrementedValue = incrementedValue_;
@synthesize value = value_;
@synthesize isInitialized_ = isInitialized__;
@synthesize savedValue = savedValue_;
@synthesize helperVariables = helperVariables_;
@synthesize incrementer = incrementer_;

- (id)initWithOrgOssPdfreporterEngineJRVariable:(id<OrgOssPdfreporterEngineJRVariable>)variable
withOrgOssPdfreporterEngineFillJRAbstractFillObjectFactory:(OrgOssPdfreporterEngineFillJRAbstractFillObjectFactory *)factory {
  if ((self = [super init])) {
    [((OrgOssPdfreporterEngineFillJRAbstractFillObjectFactory *) nil_chk(factory)) putWithId:variable withId:self];
    parent_ = variable;
    resetGroup_ = [((OrgOssPdfreporterEngineFillJRAbstractFillObjectFactory *) nil_chk(factory)) getGroupWithOrgOssPdfreporterEngineJRGroup:[((id<OrgOssPdfreporterEngineJRVariable>) nil_chk(variable)) getResetGroup]];
    incrementGroup_ = [((OrgOssPdfreporterEngineFillJRAbstractFillObjectFactory *) nil_chk(factory)) getGroupWithOrgOssPdfreporterEngineJRGroup:[((id<OrgOssPdfreporterEngineJRVariable>) nil_chk(variable)) getIncrementGroup]];
    helperVariables_ = [IOSObjectArray arrayWithLength:OrgOssPdfreporterEngineFillJRCalculable_HELPER_SIZE type:[IOSClass classWithClass:[OrgOssPdfreporterEngineFillJRFillVariable class]]];
  }
  return self;
}

- (void)reset {
  previousOldValue_ = nil;
  oldValue_ = nil;
  estimatedValue_ = nil;
  incrementedValue_ = nil;
  value_ = nil;
  isInitialized__ = NO;
  savedValue_ = nil;
}

- (NSString *)getName {
  return [((id<OrgOssPdfreporterEngineJRVariable>) nil_chk(parent_)) getName];
}

- (IOSClass *)getValueClass {
  return [((id<OrgOssPdfreporterEngineJRVariable>) nil_chk(parent_)) getValueClass];
}

- (NSString *)getValueClassName {
  return [((id<OrgOssPdfreporterEngineJRVariable>) nil_chk(parent_)) getValueClassName];
}

- (IOSClass *)getIncrementerFactoryClass {
  return [((id<OrgOssPdfreporterEngineJRVariable>) nil_chk(parent_)) getIncrementerFactoryClass];
}

- (NSString *)getIncrementerFactoryClassName {
  return [((id<OrgOssPdfreporterEngineJRVariable>) nil_chk(parent_)) getIncrementerFactoryClassName];
}

- (id<OrgOssPdfreporterEngineJRExpression>)getExpression {
  return [((id<OrgOssPdfreporterEngineJRVariable>) nil_chk(parent_)) getExpression];
}

- (id<OrgOssPdfreporterEngineJRExpression>)getInitialValueExpression {
  return [((id<OrgOssPdfreporterEngineJRVariable>) nil_chk(parent_)) getInitialValueExpression];
}

- (OrgOssPdfreporterEngineTypeResetTypeEnumEnum *)getResetTypeValue {
  return [((id<OrgOssPdfreporterEngineJRVariable>) nil_chk(parent_)) getResetTypeValue];
}

- (OrgOssPdfreporterEngineTypeIncrementTypeEnumEnum *)getIncrementTypeValue {
  return [((id<OrgOssPdfreporterEngineJRVariable>) nil_chk(parent_)) getIncrementTypeValue];
}

- (OrgOssPdfreporterEngineTypeCalculationEnumEnum *)getCalculationValue {
  return [((id<OrgOssPdfreporterEngineJRVariable>) nil_chk(parent_)) getCalculationValue];
}

- (BOOL)isSystemDefined {
  return [((id<OrgOssPdfreporterEngineJRVariable>) nil_chk(parent_)) isSystemDefined];
}

- (id<OrgOssPdfreporterEngineJRGroup>)getResetGroup {
  return resetGroup_;
}

- (id<OrgOssPdfreporterEngineJRGroup>)getIncrementGroup {
  return incrementGroup_;
}

- (id)getOldValue {
  return oldValue_;
}

- (void)setOldValueWithId:(id)oldValue {
  self.oldValue = oldValue;
}

- (id)getEstimatedValue {
  return estimatedValue_;
}

- (void)setEstimatedValueWithId:(id)estimatedValue {
  self.estimatedValue = estimatedValue;
}

- (id)getIncrementedValue {
  return incrementedValue_;
}

- (void)setIncrementedValueWithId:(id)incrementedValue {
  self.incrementedValue = incrementedValue;
}

- (id)getValue {
  return value_;
}

- (void)setValueWithId:(id)value {
  self.value = value;
}

- (BOOL)isInitialized {
  return isInitialized__;
}

- (void)setInitializedWithBOOL:(BOOL)isInitialized {
  self.isInitialized_ = isInitialized;
}

- (id<OrgOssPdfreporterEngineFillJRIncrementer>)getIncrementer {
  if (incrementer_ == nil) {
    IOSClass *incrementerFactoryClass = [self getIncrementerFactoryClass];
    id<OrgOssPdfreporterEngineFillJRIncrementerFactory> incrementerFactory;
    if (incrementerFactoryClass == nil) {
      incrementerFactory = [OrgOssPdfreporterEngineFillJRDefaultIncrementerFactory getFactoryWithIOSClass:[self getValueClass]];
    }
    else {
      incrementerFactory = [OrgOssPdfreporterEngineFillJRIncrementerFactoryCache getInstanceWithIOSClass:incrementerFactoryClass];
    }
    incrementer_ = [((id<OrgOssPdfreporterEngineFillJRIncrementerFactory>) nil_chk(incrementerFactory)) getIncrementerWithChar:[((OrgOssPdfreporterEngineTypeCalculationEnumEnum *) nil_chk([self getCalculationValue])) getValue]];
  }
  return incrementer_;
}

- (OrgOssPdfreporterEngineFillJRFillVariable *)setHelperVariableWithOrgOssPdfreporterEngineFillJRFillVariable:(OrgOssPdfreporterEngineFillJRFillVariable *)helperVariable
                                                                                                     withChar:(char)type {
  OrgOssPdfreporterEngineFillJRFillVariable *old = [((IOSObjectArray *) nil_chk(helperVariables_)) objectAtIndex:type];
  (void) [((IOSObjectArray *) nil_chk(helperVariables_)) replaceObjectAtIndex:type withObject:helperVariable];
  return old;
}

- (id<OrgOssPdfreporterEngineFillJRCalculable>)getHelperVariableWithChar:(char)type {
  return [((IOSObjectArray *) nil_chk(helperVariables_)) objectAtIndex:type];
}

- (id)getValueWithChar:(char)evaluation {
  id returnValue;
  switch (evaluation) {
    case OrgOssPdfreporterEngineJRExpression_EVALUATION_OLD:
    returnValue = oldValue_;
    break;
    case OrgOssPdfreporterEngineJRExpression_EVALUATION_ESTIMATED:
    returnValue = estimatedValue_;
    break;
    default:
    returnValue = value_;
    break;
  }
  return returnValue;
}

- (void)overwriteValueWithId:(id)newValue
                    withChar:(char)evaluation {
  switch (evaluation) {
    case OrgOssPdfreporterEngineJRExpression_EVALUATION_OLD:
    savedValue_ = oldValue_;
    oldValue_ = newValue;
    break;
    case OrgOssPdfreporterEngineJRExpression_EVALUATION_ESTIMATED:
    savedValue_ = estimatedValue_;
    estimatedValue_ = newValue;
    break;
    default:
    savedValue_ = value_;
    value_ = newValue;
    break;
  }
}

- (void)restoreValueWithChar:(char)evaluation {
  switch (evaluation) {
    case OrgOssPdfreporterEngineJRExpression_EVALUATION_OLD:
    oldValue_ = savedValue_;
    break;
    case OrgOssPdfreporterEngineJRExpression_EVALUATION_ESTIMATED:
    estimatedValue_ = savedValue_;
    break;
    default:
    value_ = savedValue_;
    break;
  }
  savedValue_ = nil;
}

- (id)getPreviousOldValue {
  return previousOldValue_;
}

- (void)setPreviousOldValueWithId:(id)previousOldValue {
  self.previousOldValue = previousOldValue;
}

- (id)clone {
  @throw [[JavaLangUnsupportedOperationException alloc] init];
}

- (id)copyWithZone:(NSZone *)zone {
  return [self clone];
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterEngineFillJRFillVariable *typedCopy = (OrgOssPdfreporterEngineFillJRFillVariable *) copy;
  typedCopy.parent = parent_;
  typedCopy.resetGroup = resetGroup_;
  typedCopy.incrementGroup = incrementGroup_;
  typedCopy.previousOldValue = previousOldValue_;
  typedCopy.oldValue = oldValue_;
  typedCopy.estimatedValue = estimatedValue_;
  typedCopy.incrementedValue = incrementedValue_;
  typedCopy.value = value_;
  typedCopy.isInitialized_ = isInitialized__;
  typedCopy.savedValue = savedValue_;
  typedCopy.helperVariables = helperVariables_;
  typedCopy.incrementer = incrementer_;
}

@end