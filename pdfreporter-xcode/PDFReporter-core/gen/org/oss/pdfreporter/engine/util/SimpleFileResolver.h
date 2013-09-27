//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/util/SimpleFileResolver.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineUtilSimpleFileResolver_H_
#define _OrgOssPdfreporterEngineUtilSimpleFileResolver_H_

@class JavaIoFile;
@protocol JavaUtilList;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/engine/util/FileResolver.h"

@interface OrgOssPdfreporterEngineUtilSimpleFileResolver : NSObject < OrgOssPdfreporterEngineUtilFileResolver > {
 @public
  id<JavaUtilList> folders_;
  BOOL isResolveAbsolutePath__;
}

@property (nonatomic, strong) id<JavaUtilList> folders;
@property (nonatomic, assign) BOOL isResolveAbsolutePath_;

- (id)initWithJavaIoFile:(JavaIoFile *)parentFolder;
- (id)initWithJavaUtilList:(id<JavaUtilList>)parentFolders;
- (id<JavaUtilList>)getFolders;
- (BOOL)isResolveAbsolutePath;
- (void)setResolveAbsolutePathWithBOOL:(BOOL)isResolveAbsolutePath;
- (JavaIoFile *)resolveFileWithNSString:(NSString *)fileName;
@end

#endif // _OrgOssPdfreporterEngineUtilSimpleFileResolver_H_