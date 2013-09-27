//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/util/JRBoxUtil.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineUtilJRBoxUtil_H_
#define _OrgOssPdfreporterEngineUtilJRBoxUtil_H_

@class JavaLangByte;
@class JavaLangInteger;
@class OrgOssPdfreporterEngineTypeRotationEnumEnum;
@protocol OrgOssPdfreporterEngineJRBox;
@protocol OrgOssPdfreporterEngineJRLineBox;
@protocol OrgOssPdfreporterGeometryIColor;

#import "JreEmulation.h"

@interface OrgOssPdfreporterEngineUtilJRBoxUtil : NSObject {
}

+ (id<OrgOssPdfreporterEngineJRLineBox>)copyBordersNoPaddingWithOrgOssPdfreporterEngineJRLineBox:(id<OrgOssPdfreporterEngineJRLineBox>)box
                                                                                        withBOOL:(BOOL)keepLeft
                                                                                        withBOOL:(BOOL)keepRight
                                                                                        withBOOL:(BOOL)keepTop
                                                                                        withBOOL:(BOOL)keepBottom
                                                            withOrgOssPdfreporterEngineJRLineBox:(id<OrgOssPdfreporterEngineJRLineBox>)complementaryBox OBJC_METHOD_FAMILY_NONE;
+ (void)resetWithOrgOssPdfreporterEngineJRLineBox:(id<OrgOssPdfreporterEngineJRLineBox>)box
                                         withBOOL:(BOOL)resetLeft
                                         withBOOL:(BOOL)resetRight
                                         withBOOL:(BOOL)resetTop
                                         withBOOL:(BOOL)resetBottom;
+ (void)setToBoxWithJavaLangByte:(JavaLangByte *)border
                withJavaLangByte:(JavaLangByte *)topBorder
                withJavaLangByte:(JavaLangByte *)leftBorder
                withJavaLangByte:(JavaLangByte *)bottomBorder
                withJavaLangByte:(JavaLangByte *)rightBorder
withOrgOssPdfreporterGeometryIColor:(id<OrgOssPdfreporterGeometryIColor>)borderColor
withOrgOssPdfreporterGeometryIColor:(id<OrgOssPdfreporterGeometryIColor>)topBorderColor
withOrgOssPdfreporterGeometryIColor:(id<OrgOssPdfreporterGeometryIColor>)leftBorderColor
withOrgOssPdfreporterGeometryIColor:(id<OrgOssPdfreporterGeometryIColor>)bottomBorderColor
withOrgOssPdfreporterGeometryIColor:(id<OrgOssPdfreporterGeometryIColor>)rightBorderColor
             withJavaLangInteger:(JavaLangInteger *)padding
             withJavaLangInteger:(JavaLangInteger *)topPadding
             withJavaLangInteger:(JavaLangInteger *)leftPadding
             withJavaLangInteger:(JavaLangInteger *)bottomPadding
             withJavaLangInteger:(JavaLangInteger *)rightPadding
withOrgOssPdfreporterEngineJRLineBox:(id<OrgOssPdfreporterEngineJRLineBox>)box;
+ (void)rotateWithOrgOssPdfreporterEngineJRLineBox:(id<OrgOssPdfreporterEngineJRLineBox>)box
   withOrgOssPdfreporterEngineTypeRotationEnumEnum:(OrgOssPdfreporterEngineTypeRotationEnumEnum *)rotation;
+ (void)setBoxToLineBoxWithOrgOssPdfreporterEngineJRBox:(id<OrgOssPdfreporterEngineJRBox>)box
                   withOrgOssPdfreporterEngineJRLineBox:(id<OrgOssPdfreporterEngineJRLineBox>)lineBox;
- (id)init;
@end

#endif // _OrgOssPdfreporterEngineUtilJRBoxUtil_H_