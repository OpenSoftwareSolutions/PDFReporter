//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/type/ResetTypeEnum.java
//
//  Created by kendra on 9/27/13.
//

#include "IOSClass.h"
#include "IOSObjectArray.h"
#include "java/lang/Byte.h"
#include "java/lang/IllegalArgumentException.h"
#include "org/oss/pdfreporter/engine/type/EnumUtil.h"
#include "org/oss/pdfreporter/engine/type/JREnum.h"
#include "org/oss/pdfreporter/engine/type/ResetTypeEnum.h"


static OrgOssPdfreporterEngineTypeResetTypeEnumEnum *OrgOssPdfreporterEngineTypeResetTypeEnumEnum_REPORT;
static OrgOssPdfreporterEngineTypeResetTypeEnumEnum *OrgOssPdfreporterEngineTypeResetTypeEnumEnum_PAGE;
static OrgOssPdfreporterEngineTypeResetTypeEnumEnum *OrgOssPdfreporterEngineTypeResetTypeEnumEnum_COLUMN;
static OrgOssPdfreporterEngineTypeResetTypeEnumEnum *OrgOssPdfreporterEngineTypeResetTypeEnumEnum_GROUP;
static OrgOssPdfreporterEngineTypeResetTypeEnumEnum *OrgOssPdfreporterEngineTypeResetTypeEnumEnum_NONE;
IOSObjectArray *OrgOssPdfreporterEngineTypeResetTypeEnumEnum_values;

@implementation OrgOssPdfreporterEngineTypeResetTypeEnumEnum

+ (OrgOssPdfreporterEngineTypeResetTypeEnumEnum *)REPORT {
  return OrgOssPdfreporterEngineTypeResetTypeEnumEnum_REPORT;
}
+ (OrgOssPdfreporterEngineTypeResetTypeEnumEnum *)PAGE {
  return OrgOssPdfreporterEngineTypeResetTypeEnumEnum_PAGE;
}
+ (OrgOssPdfreporterEngineTypeResetTypeEnumEnum *)COLUMN {
  return OrgOssPdfreporterEngineTypeResetTypeEnumEnum_COLUMN;
}
+ (OrgOssPdfreporterEngineTypeResetTypeEnumEnum *)GROUP {
  return OrgOssPdfreporterEngineTypeResetTypeEnumEnum_GROUP;
}
+ (OrgOssPdfreporterEngineTypeResetTypeEnumEnum *)NONE {
  return OrgOssPdfreporterEngineTypeResetTypeEnumEnum_NONE;
}

- (id)copyWithZone:(NSZone *)zone {
  return self;
}

@synthesize value = value_;
@synthesize name_ResetTypeEnum = name_ResetTypeEnum_;

- (id)initWithChar:(char)value
      withNSString:(NSString *)enumName
      withNSString:(NSString *)name
           withInt:(int)ordinal {
  if ((self = [super initWithNSString:name withInt:ordinal])) {
    self.value = value;
    self.name_ResetTypeEnum = enumName;
  }
  return self;
}

- (JavaLangByte *)getValueByte {
  return [[JavaLangByte alloc] initWithChar:value_];
}

- (char)getValue {
  return value_;
}

- (NSString *)getName {
  return name_ResetTypeEnum_;
}

+ (OrgOssPdfreporterEngineTypeResetTypeEnumEnum *)getByNameWithNSString:(NSString *)enumName {
  return (OrgOssPdfreporterEngineTypeResetTypeEnumEnum *) [OrgOssPdfreporterEngineTypeEnumUtil getByNameWithOrgOssPdfreporterEngineTypeJREnumArray:[OrgOssPdfreporterEngineTypeResetTypeEnumEnum values] withNSString:enumName];
}

+ (OrgOssPdfreporterEngineTypeResetTypeEnumEnum *)getByValueWithJavaLangByte:(JavaLangByte *)value {
  return (OrgOssPdfreporterEngineTypeResetTypeEnumEnum *) [OrgOssPdfreporterEngineTypeEnumUtil getByValueWithOrgOssPdfreporterEngineTypeJREnumArray:[OrgOssPdfreporterEngineTypeResetTypeEnumEnum values] withJavaLangByte:value];
}

+ (OrgOssPdfreporterEngineTypeResetTypeEnumEnum *)getByValueWithChar:(char)value {
  return [OrgOssPdfreporterEngineTypeResetTypeEnumEnum getByValueWithJavaLangByte:[[JavaLangByte alloc] initWithChar:value]];
}

+ (void)initialize {
  if (self == [OrgOssPdfreporterEngineTypeResetTypeEnumEnum class]) {
    OrgOssPdfreporterEngineTypeResetTypeEnumEnum_REPORT = [[OrgOssPdfreporterEngineTypeResetTypeEnumEnum alloc] initWithChar:(char) 1 withNSString:@"Report" withNSString:@"OrgOssPdfreporterEngineTypeResetType_REPORT" withInt:0];
    OrgOssPdfreporterEngineTypeResetTypeEnumEnum_PAGE = [[OrgOssPdfreporterEngineTypeResetTypeEnumEnum alloc] initWithChar:(char) 2 withNSString:@"Page" withNSString:@"OrgOssPdfreporterEngineTypeResetType_PAGE" withInt:1];
    OrgOssPdfreporterEngineTypeResetTypeEnumEnum_COLUMN = [[OrgOssPdfreporterEngineTypeResetTypeEnumEnum alloc] initWithChar:(char) 3 withNSString:@"Column" withNSString:@"OrgOssPdfreporterEngineTypeResetType_COLUMN" withInt:2];
    OrgOssPdfreporterEngineTypeResetTypeEnumEnum_GROUP = [[OrgOssPdfreporterEngineTypeResetTypeEnumEnum alloc] initWithChar:(char) 4 withNSString:@"Group" withNSString:@"OrgOssPdfreporterEngineTypeResetType_GROUP" withInt:3];
    OrgOssPdfreporterEngineTypeResetTypeEnumEnum_NONE = [[OrgOssPdfreporterEngineTypeResetTypeEnumEnum alloc] initWithChar:(char) 5 withNSString:@"None" withNSString:@"OrgOssPdfreporterEngineTypeResetType_NONE" withInt:4];
    OrgOssPdfreporterEngineTypeResetTypeEnumEnum_values = [[IOSObjectArray alloc] initWithObjects:(id[]){ OrgOssPdfreporterEngineTypeResetTypeEnumEnum_REPORT, OrgOssPdfreporterEngineTypeResetTypeEnumEnum_PAGE, OrgOssPdfreporterEngineTypeResetTypeEnumEnum_COLUMN, OrgOssPdfreporterEngineTypeResetTypeEnumEnum_GROUP, OrgOssPdfreporterEngineTypeResetTypeEnumEnum_NONE, nil } count:5 type:[IOSClass classWithClass:[OrgOssPdfreporterEngineTypeResetTypeEnumEnum class]]];
  }
}

+ (IOSObjectArray *)values {
  return [IOSObjectArray arrayWithArray:OrgOssPdfreporterEngineTypeResetTypeEnumEnum_values];
}

+ (OrgOssPdfreporterEngineTypeResetTypeEnumEnum *)valueOfWithNSString:(NSString *)name {
  for (int i = 0; i < [OrgOssPdfreporterEngineTypeResetTypeEnumEnum_values count]; i++) {
    OrgOssPdfreporterEngineTypeResetTypeEnumEnum *e = [OrgOssPdfreporterEngineTypeResetTypeEnumEnum_values objectAtIndex:i];
    if ([name isEqual:[e name]]) {
      return e;
    }
  }
  @throw [[JavaLangIllegalArgumentException alloc] initWithNSString:name];
  return nil;
}

@end