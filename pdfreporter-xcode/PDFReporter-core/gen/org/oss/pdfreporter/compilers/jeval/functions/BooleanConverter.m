//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/compilers/jeval/functions/BooleanConverter.java
//
//  Created by kendra on 9/27/13.
//

#include "java/lang/Double.h"
#include "java/lang/Exception.h"
#include "org/oss/pdfreporter/compilers/jeval/functions/BooleanConverter.h"
#include "org/oss/pdfreporter/uses/net/sourceforge/jeval/Evaluator.h"
#include "org/oss/pdfreporter/uses/net/sourceforge/jeval/function/FunctionConstants.h"
#include "org/oss/pdfreporter/uses/net/sourceforge/jeval/function/FunctionException.h"
#include "org/oss/pdfreporter/uses/net/sourceforge/jeval/function/FunctionResult.h"

@implementation OrgOssPdfreporterCompilersJevalFunctionsBooleanConverter

static JavaLangDouble * OrgOssPdfreporterCompilersJevalFunctionsBooleanConverter_TRUE__;

+ (JavaLangDouble *)getTRUE {
  return OrgOssPdfreporterCompilersJevalFunctionsBooleanConverter_TRUE__;
}

- (NSString *)getName {
  return @"booleanString";
}

- (OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunctionResult *)executeWithOrgOssPdfreporterUsesNetSourceforgeJevalEvaluator:(OrgOssPdfreporterUsesNetSourceforgeJevalEvaluator *)evaluator
                                                                                                                    withNSString:(NSString *)arguments {
  NSString *result = nil;
  JavaLangDouble *number = nil;
  @try {
    number = [[JavaLangDouble alloc] initWithNSString:arguments];
  }
  @catch (JavaLangException *e) {
    @throw [[OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunctionException alloc] initWithNSString:@"Invalid argument." withJavaLangException:e];
  }
  result = [((JavaLangDouble *) nil_chk(number)) compareToWithId:OrgOssPdfreporterCompilersJevalFunctionsBooleanConverter_TRUE__] == 0 ? @"TRUE" : @"FALSE";
  return [[OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunctionResult alloc] initWithNSString:result withInt:OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunctionConstants_FUNCTION_RESULT_TYPE_STRING];
}

- (id)init {
  return [super init];
}

+ (void)initialize {
  if (self == [OrgOssPdfreporterCompilersJevalFunctionsBooleanConverter class]) {
    OrgOssPdfreporterCompilersJevalFunctionsBooleanConverter_TRUE__ = [JavaLangDouble valueOfWithDouble:1.0];
  }
}

@end