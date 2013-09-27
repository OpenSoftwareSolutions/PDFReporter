//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/repo/FileSystemResource.java
//
//  Created by kendra on 9/27/13.
//

#include "java/io/File.h"
#include "org/oss/pdfreporter/net/FileResourceLoader.h"
#include "org/oss/pdfreporter/net/IURL.h"
#include "org/oss/pdfreporter/repo/FileSystemResource.h"

@implementation OrgOssPdfreporterRepoFileSystemResource

@synthesize url = url_;
@synthesize folderPath = folderPath_;

- (id)initWithJavaIoFile:(JavaIoFile *)resource {
  if ((self = [super init])) {
    self.url = [[OrgOssPdfreporterNetFileResourceLoader alloc] initWithJavaIoFile:resource];
    self.folderPath = [((JavaIoFile *) nil_chk(resource)) getParent];
  }
  return self;
}

- (id<OrgOssPdfreporterNetIURL>)getUrl {
  return url_;
}

- (NSString *)getFolderPath {
  return folderPath_;
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterRepoFileSystemResource *typedCopy = (OrgOssPdfreporterRepoFileSystemResource *) copy;
  typedCopy.url = url_;
  typedCopy.folderPath = folderPath_;
}

@end