//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/style/StyleProviderContext.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineStyleStyleProviderContext_H_
#define _OrgOssPdfreporterEngineStyleStyleProviderContext_H_

@protocol OrgOssPdfreporterEngineJRElement;
@protocol OrgOssPdfreporterEngineJRExpression;
@protocol OrgOssPdfreporterEngineJasperReportsContext;

#import "JreEmulation.h"

@protocol OrgOssPdfreporterEngineStyleStyleProviderContext < NSObject, JavaObject >
- (id<OrgOssPdfreporterEngineJasperReportsContext>)getJasperReportsContext;
- (id<OrgOssPdfreporterEngineJRElement>)getElement;
- (id)evaluateExpressionWithOrgOssPdfreporterEngineJRExpression:(id<OrgOssPdfreporterEngineJRExpression>)expression
                                                       withChar:(char)evaluation;
- (id)getFieldValueWithNSString:(NSString *)fieldName
                       withChar:(char)evaluation;
- (id)getVariableValueWithNSString:(NSString *)variableName
                          withChar:(char)evaluation;
@end

#endif // _OrgOssPdfreporterEngineStyleStyleProviderContext_H_