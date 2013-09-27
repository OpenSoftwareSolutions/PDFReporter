//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/sql/factory/BlobImpl.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterSqlFactoryBlobImpl_H_
#define _OrgOssPdfreporterSqlFactoryBlobImpl_H_

@class IOSByteArray;
@class JavaIoInputStream;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/sql/IBlob.h"

@interface OrgOssPdfreporterSqlFactoryBlobImpl : NSObject < OrgOssPdfreporterSqlIBlob > {
 @public
  JavaIoInputStream *in_;
  BOOL consumed_;
}

@property (nonatomic, strong) JavaIoInputStream *in;
@property (nonatomic, assign) BOOL consumed;

- (id)initWithJavaIoInputStream:(JavaIoInputStream *)is;
- (id)initWithByteArray:(IOSByteArray *)bytes;
- (JavaIoInputStream *)getInputStream;
- (IOSByteArray *)getBytes;
@end

#endif // _OrgOssPdfreporterSqlFactoryBlobImpl_H_