//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/JROrigin.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineJROrigin_H_
#define _OrgOssPdfreporterEngineJROrigin_H_

@class JavaLangInteger;
@class OrgOssPdfreporterEngineTypeBandTypeEnumEnum;

#import "JreEmulation.h"
#include "java/io/Serializable.h"
#include "org/oss/pdfreporter/engine/JRCloneable.h"

#define OrgOssPdfreporterEngineJROrigin_serialVersionUID 10200

@interface OrgOssPdfreporterEngineJROrigin : NSObject < OrgOssPdfreporterEngineJRCloneable, JavaIoSerializable > {
 @public
  OrgOssPdfreporterEngineTypeBandTypeEnumEnum *bandTypeValue_;
  NSString *groupName_;
  NSString *reportName_;
  JavaLangInteger *hashCode__;
}

@property (nonatomic, strong) OrgOssPdfreporterEngineTypeBandTypeEnumEnum *bandTypeValue;
@property (nonatomic, copy) NSString *groupName;
@property (nonatomic, copy) NSString *reportName;
@property (nonatomic, strong) JavaLangInteger *hashCode_;

- (id)initWithOrgOssPdfreporterEngineTypeBandTypeEnumEnum:(OrgOssPdfreporterEngineTypeBandTypeEnumEnum *)bandType;
- (id)initWithNSString:(NSString *)reportName
withOrgOssPdfreporterEngineTypeBandTypeEnumEnum:(OrgOssPdfreporterEngineTypeBandTypeEnumEnum *)bandType;
- (id)initWithNSString:(NSString *)reportName
          withNSString:(NSString *)groupName
withOrgOssPdfreporterEngineTypeBandTypeEnumEnum:(OrgOssPdfreporterEngineTypeBandTypeEnumEnum *)bandTypeValue;
- (NSString *)getReportName;
- (NSString *)getGroupName;
- (OrgOssPdfreporterEngineTypeBandTypeEnumEnum *)getBandTypeValue;
- (BOOL)isEqual:(id)obj;
- (NSUInteger)hash;
- (id)clone;
- (NSString *)description;
- (id)copyWithZone:(NSZone *)zone;
@end

#endif // _OrgOssPdfreporterEngineJROrigin_H_