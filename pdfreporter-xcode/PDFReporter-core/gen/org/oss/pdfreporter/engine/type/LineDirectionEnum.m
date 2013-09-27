//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/type/LineDirectionEnum.java
//
//  Created by kendra on 9/27/13.
//

#include "IOSClass.h"
#include "IOSObjectArray.h"
#include "java/lang/Byte.h"
#include "java/lang/IllegalArgumentException.h"
#include "org/oss/pdfreporter/engine/type/EnumUtil.h"
#include "org/oss/pdfreporter/engine/type/JREnum.h"
#include "org/oss/pdfreporter/engine/type/LineDirectionEnum.h"


static OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *OrgOssPdfreporterEngineTypeLineDirectionEnumEnum_TOP_DOWN;
static OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *OrgOssPdfreporterEngineTypeLineDirectionEnumEnum_BOTTOM_UP;
IOSObjectArray *OrgOssPdfreporterEngineTypeLineDirectionEnumEnum_values;

@implementation OrgOssPdfreporterEngineTypeLineDirectionEnumEnum

+ (OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *)TOP_DOWN {
  return OrgOssPdfreporterEngineTypeLineDirectionEnumEnum_TOP_DOWN;
}
+ (OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *)BOTTOM_UP {
  return OrgOssPdfreporterEngineTypeLineDirectionEnumEnum_BOTTOM_UP;
}

- (id)copyWithZone:(NSZone *)zone {
  return self;
}

@synthesize value = value_;
@synthesize name_LineDirectionEnum = name_LineDirectionEnum_;

- (id)initWithChar:(char)value
      withNSString:(NSString *)enumName
      withNSString:(NSString *)name
           withInt:(int)ordinal {
  if ((self = [super initWithNSString:name withInt:ordinal])) {
    self.value = value;
    self.name_LineDirectionEnum = enumName;
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
  return name_LineDirectionEnum_;
}

+ (OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *)getByNameWithNSString:(NSString *)enumName {
  return (OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *) [OrgOssPdfreporterEngineTypeEnumUtil getByNameWithOrgOssPdfreporterEngineTypeJREnumArray:[OrgOssPdfreporterEngineTypeLineDirectionEnumEnum values] withNSString:enumName];
}

+ (OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *)getByValueWithJavaLangByte:(JavaLangByte *)value {
  return (OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *) [OrgOssPdfreporterEngineTypeEnumUtil getByValueWithOrgOssPdfreporterEngineTypeJREnumArray:[OrgOssPdfreporterEngineTypeLineDirectionEnumEnum values] withJavaLangByte:value];
}

+ (OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *)getByValueWithChar:(char)value {
  return [OrgOssPdfreporterEngineTypeLineDirectionEnumEnum getByValueWithJavaLangByte:[[JavaLangByte alloc] initWithChar:value]];
}

+ (void)initialize {
  if (self == [OrgOssPdfreporterEngineTypeLineDirectionEnumEnum class]) {
    OrgOssPdfreporterEngineTypeLineDirectionEnumEnum_TOP_DOWN = [[OrgOssPdfreporterEngineTypeLineDirectionEnumEnum alloc] initWithChar:(char) 1 withNSString:@"TopDown" withNSString:@"OrgOssPdfreporterEngineTypeLineDirection_TOP_DOWN" withInt:0];
    OrgOssPdfreporterEngineTypeLineDirectionEnumEnum_BOTTOM_UP = [[OrgOssPdfreporterEngineTypeLineDirectionEnumEnum alloc] initWithChar:(char) 2 withNSString:@"BottomUp" withNSString:@"OrgOssPdfreporterEngineTypeLineDirection_BOTTOM_UP" withInt:1];
    OrgOssPdfreporterEngineTypeLineDirectionEnumEnum_values = [[IOSObjectArray alloc] initWithObjects:(id[]){ OrgOssPdfreporterEngineTypeLineDirectionEnumEnum_TOP_DOWN, OrgOssPdfreporterEngineTypeLineDirectionEnumEnum_BOTTOM_UP, nil } count:2 type:[IOSClass classWithClass:[OrgOssPdfreporterEngineTypeLineDirectionEnumEnum class]]];
  }
}

+ (IOSObjectArray *)values {
  return [IOSObjectArray arrayWithArray:OrgOssPdfreporterEngineTypeLineDirectionEnumEnum_values];
}

+ (OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *)valueOfWithNSString:(NSString *)name {
  for (int i = 0; i < [OrgOssPdfreporterEngineTypeLineDirectionEnumEnum_values count]; i++) {
    OrgOssPdfreporterEngineTypeLineDirectionEnumEnum *e = [OrgOssPdfreporterEngineTypeLineDirectionEnumEnum_values objectAtIndex:i];
    if ([name isEqual:[e name]]) {
      return e;
    }
  }
  @throw [[JavaLangIllegalArgumentException alloc] initWithNSString:name];
  return nil;
}

@end