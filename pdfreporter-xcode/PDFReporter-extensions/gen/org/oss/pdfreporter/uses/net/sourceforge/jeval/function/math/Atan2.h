//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-extensions/src/org/oss/pdfreporter/uses/net/sourceforge/jeval/function/math/Atan2.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterUsesNetSourceforgeJevalFunctionMathAtan2_H_
#define _OrgOssPdfreporterUsesNetSourceforgeJevalFunctionMathAtan2_H_

@class OrgOssPdfreporterUsesNetSourceforgeJevalEvaluator;
@class OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunctionResult;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/uses/net/sourceforge/jeval/function/Function.h"

@interface OrgOssPdfreporterUsesNetSourceforgeJevalFunctionMathAtan2 : NSObject < OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunction > {
}

- (NSString *)getName;
- (OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunctionResult *)executeWithOrgOssPdfreporterUsesNetSourceforgeJevalEvaluator:(OrgOssPdfreporterUsesNetSourceforgeJevalEvaluator *)evaluator
                                                                                                                    withNSString:(NSString *)arguments;
- (id)init;
@end

#endif // _OrgOssPdfreporterUsesNetSourceforgeJevalFunctionMathAtan2_H_