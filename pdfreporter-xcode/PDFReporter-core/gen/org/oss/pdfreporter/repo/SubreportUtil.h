//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/repo/SubreportUtil.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterRepoSubreportUtil_H_
#define _OrgOssPdfreporterRepoSubreportUtil_H_

@class OrgOssPdfreporterEngineJasperReport;
@protocol JavaIoCloseable;

#import "JreEmulation.h"

@interface OrgOssPdfreporterRepoSubreportUtil : NSObject {
}

+ (OrgOssPdfreporterEngineJasperReport *)loadSubreportWithNSString:(NSString *)location;
+ (void)closeWithJavaIoCloseable:(id<JavaIoCloseable>)stream;
- (id)init;
@end

#endif // _OrgOssPdfreporterRepoSubreportUtil_H_