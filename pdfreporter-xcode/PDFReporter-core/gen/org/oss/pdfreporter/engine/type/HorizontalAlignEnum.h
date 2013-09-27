//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/type/HorizontalAlignEnum.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineTypeHorizontalAlignEnum_H_
#define _OrgOssPdfreporterEngineTypeHorizontalAlignEnum_H_

@class JavaLangByte;

#import "JreEmulation.h"
#include "java/lang/Enum.h"
#include "org/oss/pdfreporter/engine/type/JREnum.h"

#define OrgOssPdfreporterEngineTypeHorizontalAlignEnumEnum_serialVersionUID 10200

typedef enum {
  OrgOssPdfreporterEngineTypeHorizontalAlignEnum_LEFT = 0,
  OrgOssPdfreporterEngineTypeHorizontalAlignEnum_CENTER = 1,
  OrgOssPdfreporterEngineTypeHorizontalAlignEnum_RIGHT = 2,
  OrgOssPdfreporterEngineTypeHorizontalAlignEnum_JUSTIFIED = 3,
} OrgOssPdfreporterEngineTypeHorizontalAlignEnum;

@interface OrgOssPdfreporterEngineTypeHorizontalAlignEnumEnum : JavaLangEnum < NSCopying, OrgOssPdfreporterEngineTypeJREnum > {
 @public
  char value_;
  NSString *name_HorizontalAlignEnum_;
}
@property (nonatomic, assign) char value;
@property (nonatomic, copy) NSString *name_HorizontalAlignEnum;

+ (OrgOssPdfreporterEngineTypeHorizontalAlignEnumEnum *)LEFT;
+ (OrgOssPdfreporterEngineTypeHorizontalAlignEnumEnum *)CENTER;
+ (OrgOssPdfreporterEngineTypeHorizontalAlignEnumEnum *)RIGHT;
+ (OrgOssPdfreporterEngineTypeHorizontalAlignEnumEnum *)JUSTIFIED;
+ (IOSObjectArray *)values;
+ (OrgOssPdfreporterEngineTypeHorizontalAlignEnumEnum *)valueOfWithNSString:(NSString *)name;
- (id)copyWithZone:(NSZone *)zone;
- (id)initWithChar:(char)value
      withNSString:(NSString *)enumName
      withNSString:(NSString *)name
           withInt:(int)ordinal;
- (JavaLangByte *)getValueByte;
- (char)getValue;
- (NSString *)getName;
+ (OrgOssPdfreporterEngineTypeHorizontalAlignEnumEnum *)getByNameWithNSString:(NSString *)enumName;
+ (OrgOssPdfreporterEngineTypeHorizontalAlignEnumEnum *)getByValueWithJavaLangByte:(JavaLangByte *)value;
+ (OrgOssPdfreporterEngineTypeHorizontalAlignEnumEnum *)getByValueWithChar:(char)value;
@end

#endif // _OrgOssPdfreporterEngineTypeHorizontalAlignEnum_H_