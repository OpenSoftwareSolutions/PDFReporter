//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/geometry/factory/IGeometryFactory.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterGeometryFactoryIGeometryFactory_H_
#define _OrgOssPdfreporterGeometryFactoryIGeometryFactory_H_

@class IOSFloatArray;
@class OrgOssPdfreporterGeometryFactoryIGeometryFactory_Rotate90Enum;
@protocol OrgOssPdfreporterGeometryIAffineTransformMatrix;
@protocol OrgOssPdfreporterGeometryIColor;
@protocol OrgOssPdfreporterGeometryIDimension;
@protocol OrgOssPdfreporterGeometryIRectangle;
@protocol OrgOssPdfreporterGeometryIStroke;

#import "JreEmulation.h"
#include "java/lang/Enum.h"

@protocol OrgOssPdfreporterGeometryFactoryIGeometryFactory < NSObject, JavaObject >
- (id<OrgOssPdfreporterGeometryIColor>)newColorWithInt:(int)rgb OBJC_METHOD_FAMILY_NONE;
- (id<OrgOssPdfreporterGeometryIColor>)newColorWithInt:(int)r
                                               withInt:(int)g
                                               withInt:(int)b OBJC_METHOD_FAMILY_NONE;
- (id<OrgOssPdfreporterGeometryIRectangle>)newRectangleWithFloat:(float)x
                                                       withFloat:(float)y
                                                       withFloat:(float)width
                                                       withFloat:(float)height OBJC_METHOD_FAMILY_NONE;
- (id<OrgOssPdfreporterGeometryIDimension>)newDimensionWithFloat:(float)width
                                                       withFloat:(float)height OBJC_METHOD_FAMILY_NONE;
- (id<OrgOssPdfreporterGeometryIAffineTransformMatrix>)newAffineTransformMatrixWithFloat:(float)m00
                                                                               withFloat:(float)m01
                                                                               withFloat:(float)m02
                                                                               withFloat:(float)m10
                                                                               withFloat:(float)m11
                                                                               withFloat:(float)m12 OBJC_METHOD_FAMILY_NONE;
- (id<OrgOssPdfreporterGeometryIAffineTransformMatrix>)newAffineTransformMatrixWithFloat:(float)x
                                                                               withFloat:(float)y
                       withOrgOssPdfreporterGeometryFactoryIGeometryFactory_Rotate90Enum:(OrgOssPdfreporterGeometryFactoryIGeometryFactory_Rotate90Enum *)angle OBJC_METHOD_FAMILY_NONE;
- (id<OrgOssPdfreporterGeometryIStroke>)newStrokeWithFloat:(float)width
                                                   withInt:(int)cap
                                                   withInt:(int)join
                                                 withFloat:(float)miterlimit
                                            withFloatArray:(IOSFloatArray *)dash
                                                 withFloat:(float)dash_phase OBJC_METHOD_FAMILY_NONE;
- (id<OrgOssPdfreporterGeometryIStroke>)newStrokeWithFloat:(float)width
                                                   withInt:(int)cap
                                                   withInt:(int)join OBJC_METHOD_FAMILY_NONE;
@end

typedef enum {
  OrgOssPdfreporterGeometryFactoryIGeometryFactory_Rotate90_DEGREE_90 = 0,
  OrgOssPdfreporterGeometryFactoryIGeometryFactory_Rotate90_DEGREE_180 = 1,
  OrgOssPdfreporterGeometryFactoryIGeometryFactory_Rotate90_DEGREE_270 = 2,
  OrgOssPdfreporterGeometryFactoryIGeometryFactory_Rotate90_DEGREE_360 = 3,
} OrgOssPdfreporterGeometryFactoryIGeometryFactory_Rotate90;

@interface OrgOssPdfreporterGeometryFactoryIGeometryFactory_Rotate90Enum : JavaLangEnum < NSCopying > {
}
+ (OrgOssPdfreporterGeometryFactoryIGeometryFactory_Rotate90Enum *)DEGREE_90;
+ (OrgOssPdfreporterGeometryFactoryIGeometryFactory_Rotate90Enum *)DEGREE_180;
+ (OrgOssPdfreporterGeometryFactoryIGeometryFactory_Rotate90Enum *)DEGREE_270;
+ (OrgOssPdfreporterGeometryFactoryIGeometryFactory_Rotate90Enum *)DEGREE_360;
+ (IOSObjectArray *)values;
+ (OrgOssPdfreporterGeometryFactoryIGeometryFactory_Rotate90Enum *)valueOfWithNSString:(NSString *)name;
- (id)copyWithZone:(NSZone *)zone;
- (id)initWithNSString:(NSString *)name withInt:(int)ordinal;
@end

#endif // _OrgOssPdfreporterGeometryFactoryIGeometryFactory_H_