//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/fonts/FontExtensionsRegistry.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineFontsFontExtensionsRegistry_H_
#define _OrgOssPdfreporterEngineFontsFontExtensionsRegistry_H_

@class IOSClass;
@protocol JavaUtilList;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/extensions/ExtensionsRegistry.h"

@interface OrgOssPdfreporterEngineFontsFontExtensionsRegistry : NSObject < OrgOssPdfreporterExtensionsExtensionsRegistry > {
 @public
  id<JavaUtilList> fontFamiliesLocations_;
  id<JavaUtilList> fontFamilies_;
}

@property (nonatomic, strong) id<JavaUtilList> fontFamiliesLocations;
@property (nonatomic, strong) id<JavaUtilList> fontFamilies;

- (id)initWithJavaUtilList:(id<JavaUtilList>)fontFamiliesLocations;
- (id<JavaUtilList>)getExtensionsWithIOSClass:(IOSClass *)extensionType;
@end

#endif // _OrgOssPdfreporterEngineFontsFontExtensionsRegistry_H_