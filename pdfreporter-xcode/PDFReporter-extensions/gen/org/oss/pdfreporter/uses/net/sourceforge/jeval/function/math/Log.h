//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-extensions/src/org/oss/pdfreporter/uses/net/sourceforge/jeval/function/math/Log.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterUsesNetSourceforgeJevalFunctionMathLog_H_
#define _OrgOssPdfreporterUsesNetSourceforgeJevalFunctionMathLog_H_

@class OrgOssPdfreporterUsesNetSourceforgeJevalEvaluator;
@class OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunctionResult;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/uses/net/sourceforge/jeval/function/Function.h"

@interface OrgOssPdfreporterUsesNetSourceforgeJevalFunctionMathLog : NSObject < OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunction > {
}

- (NSString *)getName;
- (OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunctionResult *)executeWithOrgOssPdfreporterUsesNetSourceforgeJevalEvaluator:(OrgOssPdfreporterUsesNetSourceforgeJevalEvaluator *)evaluator
                                                                                                                    withNSString:(NSString *)arguments;
- (id)init;
@end

#endif // _OrgOssPdfreporterUsesNetSourceforgeJevalFunctionMathLog_H_