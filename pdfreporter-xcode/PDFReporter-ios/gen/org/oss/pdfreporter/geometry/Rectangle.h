//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-portable/src/org/oss/pdfreporter/geometry/Rectangle.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterGeometryRectangle_H_
#define _OrgOssPdfreporterGeometryRectangle_H_

#import "JreEmulation.h"
#include "org/oss/pdfreporter/geometry/IRectangle.h"

@interface OrgOssPdfreporterGeometryRectangle : NSObject < OrgOssPdfreporterGeometryIRectangle > {
 @public
  float x_, y_, width_, height_;
}

@property (nonatomic, assign) float x;
@property (nonatomic, assign) float y;
@property (nonatomic, assign) float width;
@property (nonatomic, assign) float height;

- (id)initWithFloat:(float)x
          withFloat:(float)y
          withFloat:(float)width
          withFloat:(float)height;
- (id<OrgOssPdfreporterGeometryIRectangle>)getBounds;
- (float)getWidth;
- (float)getHeight;
- (float)getX;
- (float)getY;
- (NSString *)description;
@end

#endif // _OrgOssPdfreporterGeometryRectangle_H_