//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-extensions/src/org/oss/pdfreporter/uses/net/sourceforge/jeval/function/string/Length.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterUsesNetSourceforgeJevalFunctionStringLength_H_
#define _OrgOssPdfreporterUsesNetSourceforgeJevalFunctionStringLength_H_

@class OrgOssPdfreporterUsesNetSourceforgeJevalEvaluator;
@class OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunctionResult;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/uses/net/sourceforge/jeval/function/Function.h"

@interface OrgOssPdfreporterUsesNetSourceforgeJevalFunctionStringLength : NSObject < OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunction > {
}

- (NSString *)getName;
- (OrgOssPdfreporterUsesNetSourceforgeJevalFunctionFunctionResult *)executeWithOrgOssPdfreporterUsesNetSourceforgeJevalEvaluator:(OrgOssPdfreporterUsesNetSourceforgeJevalEvaluator *)evaluator
                                                                                                                    withNSString:(NSString *)arguments;
- (id)init;
@end

#endif // _OrgOssPdfreporterUsesNetSourceforgeJevalFunctionStringLength_H_