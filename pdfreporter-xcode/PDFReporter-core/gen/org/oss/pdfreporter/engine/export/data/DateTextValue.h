//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/export/data/DateTextValue.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineExportDataDateTextValue_H_
#define _OrgOssPdfreporterEngineExportDataDateTextValue_H_

@class JavaUtilDate;
@protocol OrgOssPdfreporterEngineExportDataTextValueHandler;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/engine/export/data/TextValue.h"

@interface OrgOssPdfreporterEngineExportDataDateTextValue : OrgOssPdfreporterEngineExportDataTextValue {
 @public
  JavaUtilDate *value_;
  NSString *pattern_;
}

@property (nonatomic, strong) JavaUtilDate *value;
@property (nonatomic, copy) NSString *pattern;

- (id)initWithNSString:(NSString *)text
      withJavaUtilDate:(JavaUtilDate *)value
          withNSString:(NSString *)pattern;
- (NSString *)getPattern;
- (JavaUtilDate *)getValue;
- (void)handleWithOrgOssPdfreporterEngineExportDataTextValueHandler:(id<OrgOssPdfreporterEngineExportDataTextValueHandler>)handler;
@end

#endif // _OrgOssPdfreporterEngineExportDataDateTextValue_H_