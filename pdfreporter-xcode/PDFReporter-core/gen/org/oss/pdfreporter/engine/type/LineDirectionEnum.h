//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/type/LineDirectionEnum.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineTypeLineDirectionEnum_H_
#define _OrgOssPdfreporterEngineTypeLineDirectionEnum_H_

@class JavaLangByte;

#import "JreEmulation.h"
#include "java/lang/Enum.h"
#include "org/oss/pdfreporter/engine/type/JREnum.h"

#define OrgOssPdfreporterEngineTypeLineDirectionEnumEnum_serialVersionUID 10200

typedef enum {
  OrgOssPdfreporterEngineTypeLineDirectionEnum_TOP_DOWN = 0,
  OrgOssPdfreporterEngineTypeLineDirectionEnum_BOTTOM_UP = 1,
} OrgOssPdfreporterEngineTypeLineDirectionEnum;

@interface OrgOssPdfreporterEngineTypeLineDirectionEnumEnum : JavaLangEnum < NSCopying, OrgOssPdfreporterEngineTypeJREnum > {
 @public
  char value_;
  NSString *name_LineDirectionEnum_;
}
@property (nonatomic, assign) char value;
@property (nonatomic, copy) NSString *name_LineDirectionEnum;

+ (OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *)TOP_DOWN;
+ (OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *)BOTTOM_UP;
+ (IOSObjectArray *)values;
+ (OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *)valueOfWithNSString:(NSString *)name;
- (id)copyWithZone:(NSZone *)zone;
- (id)initWithChar:(char)value
      withNSString:(NSString *)enumName
      withNSString:(NSString *)name
           withInt:(int)ordinal;
- (JavaLangByte *)getValueByte;
- (char)getValue;
- (NSString *)getName;
+ (OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *)getByNameWithNSString:(NSString *)enumName;
+ (OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *)getByValueWithJavaLangByte:(JavaLangByte *)value;
+ (OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *)getByValueWithChar:(char)value;
@end

#endif // _OrgOssPdfreporterEngineTypeLineDirectionEnum_H_