//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/fill/JRExtendedIncrementerFactory.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineFillJRExtendedIncrementerFactory_H_
#define _OrgOssPdfreporterEngineFillJRExtendedIncrementerFactory_H_

@class OrgOssPdfreporterEngineTypeCalculationEnumEnum;
@protocol OrgOssPdfreporterEngineFillJRExtendedIncrementer;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/engine/fill/JRIncrementerFactory.h"

@protocol OrgOssPdfreporterEngineFillJRExtendedIncrementerFactory < OrgOssPdfreporterEngineFillJRIncrementerFactory, NSObject, JavaObject >
- (id<OrgOssPdfreporterEngineFillJRExtendedIncrementer>)getExtendedIncrementerWithChar:(char)calculation;
- (id<OrgOssPdfreporterEngineFillJRExtendedIncrementer>)getExtendedIncrementerWithOrgOssPdfreporterEngineTypeCalculationEnumEnum:(OrgOssPdfreporterEngineTypeCalculationEnumEnum *)calculation;
@end

#endif // _OrgOssPdfreporterEngineFillJRExtendedIncrementerFactory_H_