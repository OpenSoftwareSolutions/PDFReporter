//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/util/JRPenUtil.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineUtilJRPenUtil_H_
#define _OrgOssPdfreporterEngineUtilJRPenUtil_H_

@class JavaLangByte;
@class OrgOssPdfreporterEngineTypePenEnumEnum;
@protocol OrgOssPdfreporterEngineJRPen;
@protocol OrgOssPdfreporterGeometryIStroke;

#import "JreEmulation.h"

@interface OrgOssPdfreporterEngineUtilJRPenUtil : NSObject {
}

+ (void)setLinePenFromPenWithJavaLangByte:(JavaLangByte *)pen
         withOrgOssPdfreporterEngineJRPen:(id<OrgOssPdfreporterEngineJRPen>)linePen;
+ (void)setLinePenFromPenWithOrgOssPdfreporterEngineTypePenEnumEnum:(OrgOssPdfreporterEngineTypePenEnumEnum *)pen
                                   withOrgOssPdfreporterEngineJRPen:(id<OrgOssPdfreporterEngineJRPen>)linePen;
+ (char)getPenFromLinePenWithOrgOssPdfreporterEngineJRPen:(id<OrgOssPdfreporterEngineJRPen>)linePen;
+ (JavaLangByte *)getOwnPenFromLinePenWithOrgOssPdfreporterEngineJRPen:(id<OrgOssPdfreporterEngineJRPen>)linePen;
+ (id<OrgOssPdfreporterGeometryIStroke>)getStrokeWithOrgOssPdfreporterEngineJRPen:(id<OrgOssPdfreporterEngineJRPen>)pen
                                                                          withInt:(int)lineCap;
- (id)init;
@end

#endif // _OrgOssPdfreporterEngineUtilJRPenUtil_H_