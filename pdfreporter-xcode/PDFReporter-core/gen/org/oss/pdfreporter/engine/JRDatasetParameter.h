//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/JRDatasetParameter.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineJRDatasetParameter_H_
#define _OrgOssPdfreporterEngineJRDatasetParameter_H_

@protocol OrgOssPdfreporterEngineJRExpression;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/engine/JRCloneable.h"

@protocol OrgOssPdfreporterEngineJRDatasetParameter < OrgOssPdfreporterEngineJRCloneable, NSObject, JavaObject >
- (NSString *)getName;
- (id<OrgOssPdfreporterEngineJRExpression>)getExpression;
- (id)copyWithZone:(NSZone *)zone;
@end

#endif // _OrgOssPdfreporterEngineJRDatasetParameter_H_