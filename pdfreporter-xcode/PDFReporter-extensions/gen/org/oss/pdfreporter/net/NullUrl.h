//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-extensions/src/org/oss/pdfreporter/net/NullUrl.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterNetNullUrl_H_
#define _OrgOssPdfreporterNetNullUrl_H_

@class JavaIoInputStream;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/net/IURL.h"

@interface OrgOssPdfreporterNetNullUrl : NSObject < OrgOssPdfreporterNetIURL > {
}

- (JavaIoInputStream *)openStream;
- (NSString *)getPath;
- (id)init;
@end

#endif // _OrgOssPdfreporterNetNullUrl_H_