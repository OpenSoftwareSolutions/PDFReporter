//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-extensions/src/org/oss/pdfreporter/uses/net/sourceforge/jeval/function/string/Eval.java
//
//  Created by kendra on 9/27/13.
//

#include "java/lang/Double.h"
#include "java/lang/NumberFormatException.h"
#include "org/oss/pdfreporter/uses/net/sourceforge/jeval/EvaluationException.h"
#include "org/oss/pdfreporter/uses/net/sourceforge/jeval/Evaluator.h"
#include "org/oss/pdfreporter/uses/net/sourceforge/jeval/function/FunctionConstants.h"
#include "org/oss/pdfreporter/uses/net/sourceforge/jeval/function/FunctionException.h"
#include "org/oss/pdfreporter/uses/net/sourceforge/jeval/function/FunctionResult.h"
#include "org/oss/pdfreporter/uses/net/sourceforge/jeval/function/string/Eval.h"

@implementation OrgOssPdfreporterUsesNetSourceforgeJevalFunctionStringEval

- (NSString *)getName {
  return @"eval";
}

- (OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunctionResult *)executeWithOrgOssPdfreporterUsesNetSourceforgeJevalEvaluator:(OrgOssPdfreporterUsesNetSourceforgeJevalEvaluator *)evaluator
                                                                                                                    withNSString:(NSString *)arguments {
  NSString *result = nil;
  @try {
    result = [((OrgOssPdfreporterUsesNetSourceforgeJevalEvaluator *) nil_chk(evaluator)) evaluateWithNSString:arguments withBOOL:NO withBOOL:YES];
  }
  @catch (OrgOssPdfreporterUsesNetSourceforgeJevalEvaluationException *ee) {
    @throw [[OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunctionException alloc] initWithNSString:[((OrgOssPdfreporterUsesNetSourceforgeJevalEvaluationException *) nil_chk(ee)) getMessage] withJavaLangException:ee];
  }
  int resultType = OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunctionConstants_FUNCTION_RESULT_TYPE_NUMERIC;
  @try {
    [JavaLangDouble parseDoubleWithNSString:result];
  }
  @catch (JavaLangNumberFormatException *nfe) {
    resultType = OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunctionConstants_FUNCTION_RESULT_TYPE_STRING;
  }
  return [[OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunctionResult alloc] initWithNSString:result withInt:resultType];
}

- (id)init {
  return [super init];
}

@end