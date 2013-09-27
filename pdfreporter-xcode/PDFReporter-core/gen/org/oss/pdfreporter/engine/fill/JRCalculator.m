//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/fill/JRCalculator.java
//
//  Created by kendra on 9/27/13.
//

#include "IOSClass.h"
#include "IOSObjectArray.h"
#include "java/util/Map.h"
#include "org/oss/pdfreporter/engine/JRExpression.h"
#include "org/oss/pdfreporter/engine/JRGroup.h"
#include "org/oss/pdfreporter/engine/JRVariable.h"
#include "org/oss/pdfreporter/engine/fill/AbstractValueProvider.h"
#include "org/oss/pdfreporter/engine/fill/DatasetExpressionEvaluator.h"
#include "org/oss/pdfreporter/engine/fill/JRCalculator.h"
#include "org/oss/pdfreporter/engine/fill/JREvaluator.h"
#include "org/oss/pdfreporter/engine/fill/JRFillDataset.h"
#include "org/oss/pdfreporter/engine/fill/JRFillElementDataset.h"
#include "org/oss/pdfreporter/engine/fill/JRFillGroup.h"
#include "org/oss/pdfreporter/engine/fill/JRFillVariable.h"
#include "org/oss/pdfreporter/engine/fill/JRIncrementer.h"
#include "org/oss/pdfreporter/engine/type/IncrementTypeEnum.h"
#include "org/oss/pdfreporter/engine/type/ResetTypeEnum.h"
#include "org/oss/pdfreporter/engine/type/WhenResourceMissingTypeEnum.h"

@implementation OrgOssPdfreporterEngineFillJRCalculator

@synthesize dataset = dataset_;
@synthesize parsm = parsm_;
@synthesize fldsm = fldsm_;
@synthesize varsm = varsm_;
@synthesize variables = variables_;
@synthesize groups = groups_;
@synthesize datasets = datasets_;
@synthesize pageNumber = pageNumber_;
@synthesize columnNumber = columnNumber_;
@synthesize evaluator = evaluator_;

- (id)initOrgOssPdfreporterEngineFillJRCalculatorWithOrgOssPdfreporterEngineFillDatasetExpressionEvaluator:(id<OrgOssPdfreporterEngineFillDatasetExpressionEvaluator>)evaluator {
  if ((self = [super init])) {
    self.evaluator = evaluator;
  }
  return self;
}

- (id)initWithOrgOssPdfreporterEngineFillDatasetExpressionEvaluator:(id<OrgOssPdfreporterEngineFillDatasetExpressionEvaluator>)evaluator {
  return [self initOrgOssPdfreporterEngineFillJRCalculatorWithOrgOssPdfreporterEngineFillDatasetExpressionEvaluator:evaluator];
}

- (id)initWithOrgOssPdfreporterEngineFillJREvaluator:(OrgOssPdfreporterEngineFillJREvaluator *)evaluator {
  return [self initOrgOssPdfreporterEngineFillJRCalculatorWithOrgOssPdfreporterEngineFillDatasetExpressionEvaluator:(id<OrgOssPdfreporterEngineFillDatasetExpressionEvaluator>) evaluator];
}

- (void)init__WithOrgOssPdfreporterEngineFillJRFillDataset:(OrgOssPdfreporterEngineFillJRFillDataset *)dataset OBJC_METHOD_FAMILY_NONE {
  self.dataset = dataset;
  parsm_ = ((OrgOssPdfreporterEngineFillJRFillDataset *) nil_chk(dataset)).parametersMap;
  fldsm_ = ((OrgOssPdfreporterEngineFillJRFillDataset *) nil_chk(dataset)).fieldsMap;
  varsm_ = ((OrgOssPdfreporterEngineFillJRFillDataset *) nil_chk(dataset)).variablesMap;
  variables_ = ((OrgOssPdfreporterEngineFillJRFillDataset *) nil_chk(dataset)).variables;
  groups_ = ((OrgOssPdfreporterEngineFillJRFillDataset *) nil_chk(dataset)).groups;
  datasets_ = ((OrgOssPdfreporterEngineFillJRFillDataset *) nil_chk(dataset)).elementDatasets;
  pageNumber_ = [((id<JavaUtilMap>) nil_chk(varsm_)) getWithId:[OrgOssPdfreporterEngineJRVariable PAGE_NUMBER]];
  columnNumber_ = [((id<JavaUtilMap>) nil_chk(varsm_)) getWithId:[OrgOssPdfreporterEngineJRVariable COLUMN_NUMBER]];
  OrgOssPdfreporterEngineTypeWhenResourceMissingTypeEnumEnum *whenResourceMissingType = [((OrgOssPdfreporterEngineFillJRFillDataset *) nil_chk(dataset)) getWhenResourceMissingTypeValue];
  [((id<OrgOssPdfreporterEngineFillDatasetExpressionEvaluator>) nil_chk(evaluator_)) init__WithJavaUtilMap:parsm_ withJavaUtilMap:fldsm_ withJavaUtilMap:varsm_ withOrgOssPdfreporterEngineTypeWhenResourceMissingTypeEnumEnum:whenResourceMissingType];
}

- (OrgOssPdfreporterEngineFillJRFillVariable *)getPageNumber {
  return pageNumber_;
}

- (OrgOssPdfreporterEngineFillJRFillVariable *)getColumnNumber {
  return columnNumber_;
}

- (void)calculateVariables {
  if (variables_ != nil && (int) [((IOSObjectArray *) nil_chk(variables_)) count] > 0) {
    for (int i = 0; i < (int) [((IOSObjectArray *) nil_chk(variables_)) count]; i++) {
      OrgOssPdfreporterEngineFillJRFillVariable *variable = [((IOSObjectArray *) nil_chk(variables_)) objectAtIndex:i];
      id expressionValue = [self evaluateWithOrgOssPdfreporterEngineJRExpression:[((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getExpression]];
      id newValue = [((id<OrgOssPdfreporterEngineFillJRIncrementer>) nil_chk([((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getIncrementer])) incrementWithOrgOssPdfreporterEngineFillJRFillVariable:variable withId:expressionValue withOrgOssPdfreporterEngineFillAbstractValueProvider:[OrgOssPdfreporterEngineFillAbstractValueProvider getCurrentValueProvider]];
      [((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) setValueWithId:newValue];
      [((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) setInitializedWithBOOL:NO];
      if ([((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getIncrementTypeValue] == [OrgOssPdfreporterEngineTypeIncrementTypeEnumEnum NONE]) {
        [((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) setIncrementedValueWithId:[((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getValue]];
      }
    }
  }
  if (datasets_ != nil && (int) [((IOSObjectArray *) nil_chk(datasets_)) count] > 0) {
    for (int i = 0; i < (int) [((IOSObjectArray *) nil_chk(datasets_)) count]; i++) {
      OrgOssPdfreporterEngineFillJRFillElementDataset *elementDataset = [((IOSObjectArray *) nil_chk(datasets_)) objectAtIndex:i];
      [((OrgOssPdfreporterEngineFillJRFillElementDataset *) nil_chk(elementDataset)) evaluateWithOrgOssPdfreporterEngineFillJRCalculator:self];
      if ([((OrgOssPdfreporterEngineFillJRFillElementDataset *) nil_chk(elementDataset)) getIncrementTypeValue] == [OrgOssPdfreporterEngineTypeIncrementTypeEnumEnum NONE]) {
        [((OrgOssPdfreporterEngineFillJRFillElementDataset *) nil_chk(elementDataset)) increment];
      }
    }
  }
}

- (void)estimateVariables {
  if (variables_ != nil && (int) [((IOSObjectArray *) nil_chk(variables_)) count] > 0) {
    for (int i = 0; i < (int) [((IOSObjectArray *) nil_chk(variables_)) count]; i++) {
      OrgOssPdfreporterEngineFillJRFillVariable *variable = [((IOSObjectArray *) nil_chk(variables_)) objectAtIndex:i];
      id expressionValue = [self evaluateEstimatedWithOrgOssPdfreporterEngineJRExpression:[((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getExpression]];
      id newValue = [((id<OrgOssPdfreporterEngineFillJRIncrementer>) nil_chk([((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getIncrementer])) incrementWithOrgOssPdfreporterEngineFillJRFillVariable:variable withId:expressionValue withOrgOssPdfreporterEngineFillAbstractValueProvider:[OrgOssPdfreporterEngineFillAbstractValueProvider getEstimatedValueProvider]];
      [((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) setEstimatedValueWithId:newValue];
    }
  }
}

- (void)estimateGroupRuptures {
  if (groups_ != nil && (int) [((IOSObjectArray *) nil_chk(groups_)) count] > 0) {
    for (int i = (int) [((IOSObjectArray *) nil_chk(groups_)) count] - 1; i >= 0; i--) {
      OrgOssPdfreporterEngineFillJRFillGroup *group = [((IOSObjectArray *) nil_chk(groups_)) objectAtIndex:i];
      id oldValue = [self evaluateOldWithOrgOssPdfreporterEngineJRExpression:[((OrgOssPdfreporterEngineFillJRFillGroup *) nil_chk(group)) getExpression]];
      id estimatedValue = [self evaluateEstimatedWithOrgOssPdfreporterEngineJRExpression:[((OrgOssPdfreporterEngineFillJRFillGroup *) nil_chk(group)) getExpression]];
      if ((oldValue == nil && estimatedValue != nil) || (oldValue != nil && ![nil_chk(oldValue) isEqual:estimatedValue])) {
        [((OrgOssPdfreporterEngineFillJRFillGroup *) nil_chk(group)) setHasChangedWithBOOL:YES];
      }
      else {
        [((OrgOssPdfreporterEngineFillJRFillGroup *) nil_chk(group)) setHasChangedWithBOOL:NO];
      }
    }
    if (variables_ != nil && (int) [((IOSObjectArray *) nil_chk(variables_)) count] > 0) {
      for (int i = 0; i < (int) [((IOSObjectArray *) nil_chk(variables_)) count]; i++) {
        OrgOssPdfreporterEngineFillJRFillVariable *variable = [((IOSObjectArray *) nil_chk(variables_)) objectAtIndex:i];
        if ([((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getIncrementTypeValue] == [OrgOssPdfreporterEngineTypeIncrementTypeEnumEnum GROUP]) {
          OrgOssPdfreporterEngineFillJRFillGroup *group = (OrgOssPdfreporterEngineFillJRFillGroup *) [((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getIncrementGroup];
          if ([((OrgOssPdfreporterEngineFillJRFillGroup *) nil_chk(group)) hasChanged]) {
            [((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) setIncrementedValueWithId:[((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getValue]];
          }
        }
      }
    }
    [self estimateVariables];
    BOOL groupHasChanged = NO;
    for (int i = 0; i < (int) [((IOSObjectArray *) nil_chk(groups_)) count]; i++) {
      OrgOssPdfreporterEngineFillJRFillGroup *group = [((IOSObjectArray *) nil_chk(groups_)) objectAtIndex:i];
      BOOL isTopLevelChange = NO;
      if (!groupHasChanged) {
        id oldValue = [self evaluateOldWithOrgOssPdfreporterEngineJRExpression:[((OrgOssPdfreporterEngineFillJRFillGroup *) nil_chk(group)) getExpression]];
        id estimatedValue = [self evaluateEstimatedWithOrgOssPdfreporterEngineJRExpression:[((OrgOssPdfreporterEngineFillJRFillGroup *) nil_chk(group)) getExpression]];
        if ((oldValue == nil && estimatedValue != nil) || (oldValue != nil && ![nil_chk(oldValue) isEqual:estimatedValue])) {
          groupHasChanged = YES;
          isTopLevelChange = YES;
        }
      }
      [((OrgOssPdfreporterEngineFillJRFillGroup *) nil_chk(group)) setHasChangedWithBOOL:groupHasChanged];
      [((OrgOssPdfreporterEngineFillJRFillGroup *) nil_chk(group)) setTopLevelChangeWithBOOL:isTopLevelChange];
    }
  }
}

- (void)initializeVariablesWithOrgOssPdfreporterEngineTypeResetTypeEnumEnum:(OrgOssPdfreporterEngineTypeResetTypeEnumEnum *)resetType
                       withOrgOssPdfreporterEngineTypeIncrementTypeEnumEnum:(OrgOssPdfreporterEngineTypeIncrementTypeEnumEnum *)incrementType OBJC_METHOD_FAMILY_NONE {
  if (variables_ != nil && (int) [((IOSObjectArray *) nil_chk(variables_)) count] > 0) {
    for (int i = 0; i < (int) [((IOSObjectArray *) nil_chk(variables_)) count]; i++) {
      [self incrementVariableWithOrgOssPdfreporterEngineFillJRFillVariable:[((IOSObjectArray *) nil_chk(variables_)) objectAtIndex:i] withOrgOssPdfreporterEngineTypeIncrementTypeEnumEnum:incrementType];
      [self initializeVariableWithOrgOssPdfreporterEngineFillJRFillVariable:[((IOSObjectArray *) nil_chk(variables_)) objectAtIndex:i] withOrgOssPdfreporterEngineTypeResetTypeEnumEnum:resetType];
    }
  }
  if (datasets_ != nil && (int) [((IOSObjectArray *) nil_chk(datasets_)) count] > 0) {
    for (int i = 0; i < (int) [((IOSObjectArray *) nil_chk(datasets_)) count]; i++) {
      [self incrementDatasetWithOrgOssPdfreporterEngineFillJRFillElementDataset:[((IOSObjectArray *) nil_chk(datasets_)) objectAtIndex:i] withOrgOssPdfreporterEngineTypeIncrementTypeEnumEnum:incrementType];
      [self initializeDatasetWithOrgOssPdfreporterEngineFillJRFillElementDataset:[((IOSObjectArray *) nil_chk(datasets_)) objectAtIndex:i] withOrgOssPdfreporterEngineTypeResetTypeEnumEnum:resetType];
    }
  }
}

- (void)incrementVariableWithOrgOssPdfreporterEngineFillJRFillVariable:(OrgOssPdfreporterEngineFillJRFillVariable *)variable
                  withOrgOssPdfreporterEngineTypeIncrementTypeEnumEnum:(OrgOssPdfreporterEngineTypeIncrementTypeEnumEnum *)incrementType {
  if ([((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getIncrementTypeValue] != [OrgOssPdfreporterEngineTypeIncrementTypeEnumEnum NONE]) {
    BOOL toIncrement = NO;
    switch ([incrementType ordinal]) {
      case OrgOssPdfreporterEngineTypeIncrementTypeEnum_REPORT:
      {
        toIncrement = YES;
        break;
      }
      case OrgOssPdfreporterEngineTypeIncrementTypeEnum_PAGE:
      {
        toIncrement = ([((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getIncrementTypeValue] == [OrgOssPdfreporterEngineTypeIncrementTypeEnumEnum PAGE] || [((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getIncrementTypeValue] == [OrgOssPdfreporterEngineTypeIncrementTypeEnumEnum COLUMN]);
        break;
      }
      case OrgOssPdfreporterEngineTypeIncrementTypeEnum_COLUMN:
      {
        toIncrement = ([((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getIncrementTypeValue] == [OrgOssPdfreporterEngineTypeIncrementTypeEnumEnum COLUMN]);
        break;
      }
      case OrgOssPdfreporterEngineTypeIncrementTypeEnum_GROUP:
      {
        if ([((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getIncrementTypeValue] == [OrgOssPdfreporterEngineTypeIncrementTypeEnumEnum GROUP]) {
          OrgOssPdfreporterEngineFillJRFillGroup *group = (OrgOssPdfreporterEngineFillJRFillGroup *) [((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getIncrementGroup];
          toIncrement = [((OrgOssPdfreporterEngineFillJRFillGroup *) nil_chk(group)) hasChanged];
        }
        break;
      }
      case OrgOssPdfreporterEngineTypeIncrementTypeEnum_NONE:
      default:
      {
      }
    }
    if (toIncrement) {
      [((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) setIncrementedValueWithId:[((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getValue]];
    }
  }
  else {
    [((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) setIncrementedValueWithId:[((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getValue]];
  }
}

- (void)incrementDatasetWithOrgOssPdfreporterEngineFillJRFillElementDataset:(OrgOssPdfreporterEngineFillJRFillElementDataset *)elementDataset
                       withOrgOssPdfreporterEngineTypeIncrementTypeEnumEnum:(OrgOssPdfreporterEngineTypeIncrementTypeEnumEnum *)incrementType {
  if ([((OrgOssPdfreporterEngineFillJRFillElementDataset *) nil_chk(elementDataset)) getIncrementTypeValue] != [OrgOssPdfreporterEngineTypeIncrementTypeEnumEnum NONE]) {
    BOOL toIncrement = NO;
    switch ([incrementType ordinal]) {
      case OrgOssPdfreporterEngineTypeIncrementTypeEnum_REPORT:
      {
        toIncrement = YES;
        break;
      }
      case OrgOssPdfreporterEngineTypeIncrementTypeEnum_PAGE:
      {
        toIncrement = ([((OrgOssPdfreporterEngineFillJRFillElementDataset *) nil_chk(elementDataset)) getIncrementTypeValue] == [OrgOssPdfreporterEngineTypeIncrementTypeEnumEnum PAGE] || [((OrgOssPdfreporterEngineFillJRFillElementDataset *) nil_chk(elementDataset)) getIncrementTypeValue] == [OrgOssPdfreporterEngineTypeIncrementTypeEnumEnum COLUMN]);
        break;
      }
      case OrgOssPdfreporterEngineTypeIncrementTypeEnum_COLUMN:
      {
        toIncrement = ([((OrgOssPdfreporterEngineFillJRFillElementDataset *) nil_chk(elementDataset)) getIncrementTypeValue] == [OrgOssPdfreporterEngineTypeIncrementTypeEnumEnum COLUMN]);
        break;
      }
      case OrgOssPdfreporterEngineTypeIncrementTypeEnum_GROUP:
      {
        if ([((OrgOssPdfreporterEngineFillJRFillElementDataset *) nil_chk(elementDataset)) getIncrementTypeValue] == [OrgOssPdfreporterEngineTypeIncrementTypeEnumEnum GROUP]) {
          OrgOssPdfreporterEngineFillJRFillGroup *group = (OrgOssPdfreporterEngineFillJRFillGroup *) [((OrgOssPdfreporterEngineFillJRFillElementDataset *) nil_chk(elementDataset)) getIncrementGroup];
          toIncrement = [((OrgOssPdfreporterEngineFillJRFillGroup *) nil_chk(group)) hasChanged];
        }
        break;
      }
      case OrgOssPdfreporterEngineTypeIncrementTypeEnum_NONE:
      default:
      {
      }
    }
    if (toIncrement) {
      [((OrgOssPdfreporterEngineFillJRFillElementDataset *) nil_chk(elementDataset)) increment];
    }
  }
}

- (void)initializeVariableWithOrgOssPdfreporterEngineFillJRFillVariable:(OrgOssPdfreporterEngineFillJRFillVariable *)variable
                       withOrgOssPdfreporterEngineTypeResetTypeEnumEnum:(OrgOssPdfreporterEngineTypeResetTypeEnumEnum *)resetType OBJC_METHOD_FAMILY_NONE {
  if ([((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getResetTypeValue] != [OrgOssPdfreporterEngineTypeResetTypeEnumEnum NONE]) {
    BOOL toInitialize = NO;
    switch ([resetType ordinal]) {
      case OrgOssPdfreporterEngineTypeResetTypeEnum_REPORT:
      {
        toInitialize = YES;
        break;
      }
      case OrgOssPdfreporterEngineTypeResetTypeEnum_PAGE:
      {
        toInitialize = ([((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getResetTypeValue] == [OrgOssPdfreporterEngineTypeResetTypeEnumEnum PAGE] || [((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getResetTypeValue] == [OrgOssPdfreporterEngineTypeResetTypeEnumEnum COLUMN]);
        break;
      }
      case OrgOssPdfreporterEngineTypeResetTypeEnum_COLUMN:
      {
        toInitialize = ([((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getResetTypeValue] == [OrgOssPdfreporterEngineTypeResetTypeEnumEnum COLUMN]);
        break;
      }
      case OrgOssPdfreporterEngineTypeResetTypeEnum_GROUP:
      {
        if ([((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getResetTypeValue] == [OrgOssPdfreporterEngineTypeResetTypeEnumEnum GROUP]) {
          OrgOssPdfreporterEngineFillJRFillGroup *group = (OrgOssPdfreporterEngineFillJRFillGroup *) [((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getResetGroup];
          toInitialize = [((OrgOssPdfreporterEngineFillJRFillGroup *) nil_chk(group)) hasChanged];
        }
        break;
      }
      case OrgOssPdfreporterEngineTypeResetTypeEnum_NONE:
      default:
      {
      }
    }
    if (toInitialize) {
      [((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) setValueWithId:[self evaluateWithOrgOssPdfreporterEngineJRExpression:[((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getInitialValueExpression]]];
      [((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) setInitializedWithBOOL:YES];
      [((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) setIncrementedValueWithId:nil];
    }
  }
  else {
    [((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) setValueWithId:[self evaluateWithOrgOssPdfreporterEngineJRExpression:[((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getExpression]]];
    [((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) setIncrementedValueWithId:[((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk(variable)) getValue]];
  }
}

- (void)initializeDatasetWithOrgOssPdfreporterEngineFillJRFillElementDataset:(OrgOssPdfreporterEngineFillJRFillElementDataset *)elementDataset
                            withOrgOssPdfreporterEngineTypeResetTypeEnumEnum:(OrgOssPdfreporterEngineTypeResetTypeEnumEnum *)resetType OBJC_METHOD_FAMILY_NONE {
  BOOL toInitialize = NO;
  switch ([resetType ordinal]) {
    case OrgOssPdfreporterEngineTypeResetTypeEnum_REPORT:
    {
      toInitialize = YES;
      break;
    }
    case OrgOssPdfreporterEngineTypeResetTypeEnum_PAGE:
    {
      toInitialize = ([((OrgOssPdfreporterEngineFillJRFillElementDataset *) nil_chk(elementDataset)) getResetTypeValue] == [OrgOssPdfreporterEngineTypeResetTypeEnumEnum PAGE] || [((OrgOssPdfreporterEngineFillJRFillElementDataset *) nil_chk(elementDataset)) getResetTypeValue] == [OrgOssPdfreporterEngineTypeResetTypeEnumEnum COLUMN]);
      break;
    }
    case OrgOssPdfreporterEngineTypeResetTypeEnum_COLUMN:
    {
      toInitialize = ([((OrgOssPdfreporterEngineFillJRFillElementDataset *) nil_chk(elementDataset)) getResetTypeValue] == [OrgOssPdfreporterEngineTypeResetTypeEnumEnum COLUMN]);
      break;
    }
    case OrgOssPdfreporterEngineTypeResetTypeEnum_GROUP:
    {
      if ([((OrgOssPdfreporterEngineFillJRFillElementDataset *) nil_chk(elementDataset)) getResetTypeValue] == [OrgOssPdfreporterEngineTypeResetTypeEnumEnum GROUP]) {
        OrgOssPdfreporterEngineFillJRFillGroup *group = (OrgOssPdfreporterEngineFillJRFillGroup *) [((OrgOssPdfreporterEngineFillJRFillElementDataset *) nil_chk(elementDataset)) getResetGroup];
        toInitialize = [((OrgOssPdfreporterEngineFillJRFillGroup *) nil_chk(group)) hasChanged];
      }
      else if ([((OrgOssPdfreporterEngineFillJRFillElementDataset *) nil_chk(elementDataset)) getResetTypeValue] == [OrgOssPdfreporterEngineTypeResetTypeEnumEnum NONE]) {
        toInitialize = YES;
      }
      break;
    }
    case OrgOssPdfreporterEngineTypeResetTypeEnum_NONE:
    default:
    {
    }
  }
  if (toInitialize) {
    [((OrgOssPdfreporterEngineFillJRFillElementDataset *) nil_chk(elementDataset)) initialize__];
  }
}

- (id)evaluateWithOrgOssPdfreporterEngineJRExpression:(id<OrgOssPdfreporterEngineJRExpression>)expression
                                             withChar:(char)evaluationType {
  id value = nil;
  switch (evaluationType) {
    case OrgOssPdfreporterEngineJRExpression_EVALUATION_OLD:
    {
      value = [self evaluateOldWithOrgOssPdfreporterEngineJRExpression:expression];
      break;
    }
    case OrgOssPdfreporterEngineJRExpression_EVALUATION_ESTIMATED:
    {
      value = [self evaluateEstimatedWithOrgOssPdfreporterEngineJRExpression:expression];
      break;
    }
    case OrgOssPdfreporterEngineJRExpression_EVALUATION_DEFAULT:
    default:
    {
      value = [self evaluateWithOrgOssPdfreporterEngineJRExpression:expression];
      break;
    }
  }
  return value;
}

- (id)evaluateOldWithOrgOssPdfreporterEngineJRExpression:(id<OrgOssPdfreporterEngineJRExpression>)expression {
  return [((id<OrgOssPdfreporterEngineFillDatasetExpressionEvaluator>) nil_chk(evaluator_)) evaluateOldWithOrgOssPdfreporterEngineJRExpression:expression];
}

- (id)evaluateEstimatedWithOrgOssPdfreporterEngineJRExpression:(id<OrgOssPdfreporterEngineJRExpression>)expression {
  return [((id<OrgOssPdfreporterEngineFillDatasetExpressionEvaluator>) nil_chk(evaluator_)) evaluateEstimatedWithOrgOssPdfreporterEngineJRExpression:expression];
}

- (id)evaluateWithOrgOssPdfreporterEngineJRExpression:(id<OrgOssPdfreporterEngineJRExpression>)expression {
  return [((id<OrgOssPdfreporterEngineFillDatasetExpressionEvaluator>) nil_chk(evaluator_)) evaluateWithOrgOssPdfreporterEngineJRExpression:expression];
}

- (OrgOssPdfreporterEngineFillJRFillDataset *)getFillDataset {
  return dataset_;
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterEngineFillJRCalculator *typedCopy = (OrgOssPdfreporterEngineFillJRCalculator *) copy;
  typedCopy.dataset = dataset_;
  typedCopy.parsm = parsm_;
  typedCopy.fldsm = fldsm_;
  typedCopy.varsm = varsm_;
  typedCopy.variables = variables_;
  typedCopy.groups = groups_;
  typedCopy.datasets = datasets_;
  typedCopy.pageNumber = pageNumber_;
  typedCopy.columnNumber = columnNumber_;
  typedCopy.evaluator = evaluator_;
}

@end