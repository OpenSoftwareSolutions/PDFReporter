//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-extensions/src/org/oss/pdfreporter/uses/org/apache/digester/impl/ObjectCreateRule.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterUsesOrgApacheDigesterImplObjectCreateRule_H_
#define _OrgOssPdfreporterUsesOrgApacheDigesterImplObjectCreateRule_H_

@class JavaUtilLoggingLogger;
@protocol OrgOssPdfreporterXmlParsersIAttributes;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/uses/org/apache/digester/impl/Rule.h"

@interface OrgOssPdfreporterUsesOrgApacheDigesterImplObjectCreateRule : OrgOssPdfreporterUsesOrgApacheDigesterImplRule {
 @public
  NSString *attributeName_;
  NSString *className__;
}

@property (nonatomic, copy) NSString *attributeName;
@property (nonatomic, copy) NSString *className_;

+ (JavaUtilLoggingLogger *)logger;
- (id)initWithNSString:(NSString *)className_;
- (id)initWithNSString:(NSString *)className_
          withNSString:(NSString *)attributeName;
- (void)beginWithOrgOssPdfreporterXmlParsersIAttributes:(id<OrgOssPdfreporterXmlParsersIAttributes>)attributes;
- (void)end;
- (NSString *)description;
@end

#endif // _OrgOssPdfreporterUsesOrgApacheDigesterImplObjectCreateRule_H_