//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/compilers/jeval/functions/DoubleStringConverter.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterCompilersJevalFunctionsDoubleStringConverter_H_
#define _OrgOssPdfreporterCompilersJevalFunctionsDoubleStringConverter_H_

@class OrgOssPdfreporterUsesNetSourceforgeJevalEvaluator;
@class OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunctionResult;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/uses/net/sourceforge/jeval/function/Function.h"

@interface OrgOssPdfreporterCompilersJevalFunctionsDoubleStringConverter : NSObject < OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunction > {
}

- (NSString *)getName;
- (OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunctionResult *)executeWithOrgOssPdfreporterUsesNetSourceforgeJevalEvaluator:(OrgOssPdfreporterUsesNetSourceforgeJevalEvaluator *)evaluator
                                                                                                                    withNSString:(NSString *)arguments;
- (id)init;
@end

#endif // _OrgOssPdfreporterCompilersJevalFunctionsDoubleStringConverter_H_