//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/util/JRValueStringUtils.java
//
//  Created by kendra on 9/27/13.
//

#include "IOSCharArray.h"
#include "IOSClass.h"
#include "java/lang/Boolean.h"
#include "java/lang/Byte.h"
#include "java/lang/Character.h"
#include "java/lang/Double.h"
#include "java/lang/Float.h"
#include "java/lang/Integer.h"
#include "java/lang/Long.h"
#include "java/lang/NumberFormatException.h"
#include "java/lang/Short.h"
#include "java/math/BigDecimal.h"
#include "java/math/BigInteger.h"
#include "java/util/Date.h"
#include "java/util/HashMap.h"
#include "java/util/Map.h"
#include "org/oss/pdfreporter/engine/JRRuntimeException.h"
#include "org/oss/pdfreporter/engine/util/JRValueStringUtils.h"

@implementation OrgOssPdfreporterEngineUtilJRValueStringUtils

static id<JavaUtilMap> OrgOssPdfreporterEngineUtilJRValueStringUtils_serializers_;
static id<OrgOssPdfreporterEngineUtilJRValueStringUtils_ValueSerializer> OrgOssPdfreporterEngineUtilJRValueStringUtils_defaultSerializer_;

+ (id<JavaUtilMap>)serializers {
  return OrgOssPdfreporterEngineUtilJRValueStringUtils_serializers_;
}

+ (id<OrgOssPdfreporterEngineUtilJRValueStringUtils_ValueSerializer>)defaultSerializer {
  return OrgOssPdfreporterEngineUtilJRValueStringUtils_defaultSerializer_;
}

+ (BOOL)hasSerializerWithNSString:(NSString *)valueClass {
  return [((id<JavaUtilMap>) nil_chk(OrgOssPdfreporterEngineUtilJRValueStringUtils_serializers_)) containsKeyWithId:valueClass];
}

+ (NSString *)serializeWithNSString:(NSString *)valueClass
                             withId:(id)value {
  NSString *data;
  if (value == nil) {
    data = nil;
  }
  else {
    id<OrgOssPdfreporterEngineUtilJRValueStringUtils_ValueSerializer> serializer = [OrgOssPdfreporterEngineUtilJRValueStringUtils getSerializerWithNSString:valueClass];
    data = [((id<OrgOssPdfreporterEngineUtilJRValueStringUtils_ValueSerializer>) nil_chk(serializer)) serializeWithId:value];
  }
  return data;
}

+ (id)deserializeWithNSString:(NSString *)valueClass
                 withNSString:(NSString *)data {
  id value;
  if (data == nil) {
    value = nil;
  }
  else {
    id<OrgOssPdfreporterEngineUtilJRValueStringUtils_ValueSerializer> serializer = [OrgOssPdfreporterEngineUtilJRValueStringUtils getSerializerWithNSString:valueClass];
    value = [((id<OrgOssPdfreporterEngineUtilJRValueStringUtils_ValueSerializer>) nil_chk(serializer)) deserializeWithNSString:data];
  }
  return value;
}

+ (id<OrgOssPdfreporterEngineUtilJRValueStringUtils_ValueSerializer>)getSerializerWithNSString:(NSString *)valueClass {
  id<OrgOssPdfreporterEngineUtilJRValueStringUtils_ValueSerializer> serializer = [((id<JavaUtilMap>) nil_chk(OrgOssPdfreporterEngineUtilJRValueStringUtils_serializers_)) getWithId:valueClass];
  if (serializer == nil) {
    serializer = OrgOssPdfreporterEngineUtilJRValueStringUtils_defaultSerializer_;
  }
  return serializer;
}

+ (id<JavaUtilMap>)getSerializers {
  id<JavaUtilMap> map = [[JavaUtilHashMap alloc] init];
  (void) [((id<JavaUtilMap>) nil_chk(map)) putWithId:[[IOSClass classWithClass:[NSString class]] getName] withId:[[OrgOssPdfreporterEngineUtilJRValueStringUtils_StringSerializer alloc] init]];
  (void) [((id<JavaUtilMap>) nil_chk(map)) putWithId:[[IOSClass classWithClass:[JavaLangCharacter class]] getName] withId:[[OrgOssPdfreporterEngineUtilJRValueStringUtils_CharacterSerializer alloc] init]];
  (void) [((id<JavaUtilMap>) nil_chk(map)) putWithId:[[IOSClass classWithClass:[JavaLangBoolean class]] getName] withId:[[OrgOssPdfreporterEngineUtilJRValueStringUtils_BooleanSerializer alloc] init]];
  (void) [((id<JavaUtilMap>) nil_chk(map)) putWithId:[[IOSClass classWithClass:[JavaLangByte class]] getName] withId:[[OrgOssPdfreporterEngineUtilJRValueStringUtils_ByteSerializer alloc] init]];
  (void) [((id<JavaUtilMap>) nil_chk(map)) putWithId:[[IOSClass classWithClass:[JavaLangShort class]] getName] withId:[[OrgOssPdfreporterEngineUtilJRValueStringUtils_ShortSerializer alloc] init]];
  (void) [((id<JavaUtilMap>) nil_chk(map)) putWithId:[[IOSClass classWithClass:[JavaLangInteger class]] getName] withId:[[OrgOssPdfreporterEngineUtilJRValueStringUtils_IntegerSerializer alloc] init]];
  (void) [((id<JavaUtilMap>) nil_chk(map)) putWithId:[[IOSClass classWithClass:[JavaLangLong class]] getName] withId:[[OrgOssPdfreporterEngineUtilJRValueStringUtils_LongSerializer alloc] init]];
  (void) [((id<JavaUtilMap>) nil_chk(map)) putWithId:[[IOSClass classWithClass:[JavaLangFloat class]] getName] withId:[[OrgOssPdfreporterEngineUtilJRValueStringUtils_FloatSerializer alloc] init]];
  (void) [((id<JavaUtilMap>) nil_chk(map)) putWithId:[[IOSClass classWithClass:[JavaLangDouble class]] getName] withId:[[OrgOssPdfreporterEngineUtilJRValueStringUtils_DoubleSerializer alloc] init]];
  (void) [((id<JavaUtilMap>) nil_chk(map)) putWithId:[[IOSClass classWithClass:[JavaMathBigInteger class]] getName] withId:[[OrgOssPdfreporterEngineUtilJRValueStringUtils_BigIntegerSerializer alloc] init]];
  (void) [((id<JavaUtilMap>) nil_chk(map)) putWithId:[[IOSClass classWithClass:[JavaMathBigDecimal class]] getName] withId:[[OrgOssPdfreporterEngineUtilJRValueStringUtils_BigDecimalSerializer alloc] init]];
  (void) [((id<JavaUtilMap>) nil_chk(map)) putWithId:[[IOSClass classWithClass:[JavaUtilDate class]] getName] withId:[[OrgOssPdfreporterEngineUtilJRValueStringUtils_DateSerializer alloc] init]];
  return map;
}

- (id)init {
  return [super init];
}

+ (void)initialize {
  if (self == [OrgOssPdfreporterEngineUtilJRValueStringUtils class]) {
    {
      OrgOssPdfreporterEngineUtilJRValueStringUtils_serializers_ = [OrgOssPdfreporterEngineUtilJRValueStringUtils getSerializers];
      OrgOssPdfreporterEngineUtilJRValueStringUtils_defaultSerializer_ = nil;
    }
  }
}

@end
@implementation OrgOssPdfreporterEngineUtilJRValueStringUtils_StringSerializer

- (id)deserializeWithNSString:(NSString *)data {
  return data;
}

- (NSString *)serializeWithId:(id)value {
  return (NSString *) value;
}

- (id)init {
  return [super init];
}

@end
@implementation OrgOssPdfreporterEngineUtilJRValueStringUtils_CharacterSerializer

- (id)deserializeWithNSString:(NSString *)data {
  if ([((NSString *) nil_chk(data)) length] != 1) {
    @throw [[OrgOssPdfreporterEngineJRRuntimeException alloc] initWithNSString:[NSString stringWithFormat:@"Character data \"%@\" should be exactly one character long", data]];
  }
  return [[JavaLangCharacter alloc] initWithUnichar:[((NSString *) nil_chk(data)) charAtWithInt:0]];
}

- (NSString *)serializeWithId:(id)value {
  return [NSString valueOfChars:[IOSCharArray arrayWithCharacters:(unichar[]){ [((JavaLangCharacter *) value) charValue] } count:1]];
}

- (id)init {
  return [super init];
}

@end
@implementation OrgOssPdfreporterEngineUtilJRValueStringUtils_BooleanSerializer

- (id)deserializeWithNSString:(NSString *)data {
  if ([((NSString *) nil_chk(data)) isEqual:@"true"]) {
    return [JavaLangBoolean getTRUE];
  }
  if ([((NSString *) nil_chk(data)) isEqual:@"false"]) {
    return [JavaLangBoolean getFALSE];
  }
  @throw [[OrgOssPdfreporterEngineJRRuntimeException alloc] initWithNSString:[NSString stringWithFormat:@"Unkown boolean data \"%@\"", data]];
}

- (NSString *)serializeWithId:(id)value {
  return [((JavaLangBoolean *) value) booleanValue] ? @"true" : @"false";
}

- (id)init {
  return [super init];
}

@end
@implementation OrgOssPdfreporterEngineUtilJRValueStringUtils_ByteSerializer

- (id)deserializeWithNSString:(NSString *)data {
  @try {
    return [JavaLangByte valueOfWithNSString:data];
  }
  @catch (JavaLangNumberFormatException *e) {
    @throw [[OrgOssPdfreporterEngineJRRuntimeException alloc] initWithNSString:[NSString stringWithFormat:@"Error parsing Byte data \"%@\"", data] withJavaLangThrowable:e];
  }
}

- (NSString *)serializeWithId:(id)value {
  return [((JavaLangByte *) value) description];
}

- (id)init {
  return [super init];
}

@end
@implementation OrgOssPdfreporterEngineUtilJRValueStringUtils_ShortSerializer

- (id)deserializeWithNSString:(NSString *)data {
  @try {
    return [JavaLangShort valueOfWithNSString:data];
  }
  @catch (JavaLangNumberFormatException *e) {
    @throw [[OrgOssPdfreporterEngineJRRuntimeException alloc] initWithNSString:[NSString stringWithFormat:@"Error parsing Short data \"%@\"", data] withJavaLangThrowable:e];
  }
}

- (NSString *)serializeWithId:(id)value {
  return [((JavaLangShort *) value) description];
}

- (id)init {
  return [super init];
}

@end
@implementation OrgOssPdfreporterEngineUtilJRValueStringUtils_IntegerSerializer

- (id)deserializeWithNSString:(NSString *)data {
  @try {
    return [JavaLangInteger valueOfWithNSString:data];
  }
  @catch (JavaLangNumberFormatException *e) {
    @throw [[OrgOssPdfreporterEngineJRRuntimeException alloc] initWithNSString:[NSString stringWithFormat:@"Error parsing Integer data \"%@\"", data] withJavaLangThrowable:e];
  }
}

- (NSString *)serializeWithId:(id)value {
  return [((JavaLangInteger *) value) description];
}

- (id)init {
  return [super init];
}

@end
@implementation OrgOssPdfreporterEngineUtilJRValueStringUtils_LongSerializer

- (id)deserializeWithNSString:(NSString *)data {
  @try {
    return [JavaLangLong valueOfWithNSString:data];
  }
  @catch (JavaLangNumberFormatException *e) {
    @throw [[OrgOssPdfreporterEngineJRRuntimeException alloc] initWithNSString:[NSString stringWithFormat:@"Error parsing Long data \"%@\"", data] withJavaLangThrowable:e];
  }
}

- (NSString *)serializeWithId:(id)value {
  return [((JavaLangLong *) value) description];
}

- (id)init {
  return [super init];
}

@end
@implementation OrgOssPdfreporterEngineUtilJRValueStringUtils_FloatSerializer

- (id)deserializeWithNSString:(NSString *)data {
  @try {
    return [JavaLangFloat valueOfWithNSString:data];
  }
  @catch (JavaLangNumberFormatException *e) {
    @throw [[OrgOssPdfreporterEngineJRRuntimeException alloc] initWithNSString:[NSString stringWithFormat:@"Error parsing Float data \"%@\"", data] withJavaLangThrowable:e];
  }
}

- (NSString *)serializeWithId:(id)value {
  return [((JavaLangFloat *) value) description];
}

- (id)init {
  return [super init];
}

@end
@implementation OrgOssPdfreporterEngineUtilJRValueStringUtils_DoubleSerializer

- (id)deserializeWithNSString:(NSString *)data {
  @try {
    return [JavaLangDouble valueOfWithNSString:data];
  }
  @catch (JavaLangNumberFormatException *e) {
    @throw [[OrgOssPdfreporterEngineJRRuntimeException alloc] initWithNSString:[NSString stringWithFormat:@"Error parsing Double data \"%@\"", data] withJavaLangThrowable:e];
  }
}

- (NSString *)serializeWithId:(id)value {
  return [((JavaLangDouble *) value) description];
}

- (id)init {
  return [super init];
}

@end
@implementation OrgOssPdfreporterEngineUtilJRValueStringUtils_BigIntegerSerializer

- (id)deserializeWithNSString:(NSString *)data {
  @try {
    return [[JavaMathBigInteger alloc] initWithNSString:data];
  }
  @catch (JavaLangNumberFormatException *e) {
    @throw [[OrgOssPdfreporterEngineJRRuntimeException alloc] initWithNSString:[NSString stringWithFormat:@"Error parsing BigInteger data \"%@\"", data] withJavaLangThrowable:e];
  }
}

- (NSString *)serializeWithId:(id)value {
  return [((JavaMathBigInteger *) value) description];
}

- (id)init {
  return [super init];
}

@end
@implementation OrgOssPdfreporterEngineUtilJRValueStringUtils_BigDecimalSerializer

- (id)deserializeWithNSString:(NSString *)data {
  @try {
    return [[JavaMathBigDecimal alloc] initWithNSString:data];
  }
  @catch (JavaLangNumberFormatException *e) {
    @throw [[OrgOssPdfreporterEngineJRRuntimeException alloc] initWithNSString:[NSString stringWithFormat:@"Error parsing BigDecimal data \"%@\"", data] withJavaLangThrowable:e];
  }
}

- (NSString *)serializeWithId:(id)value {
  return [((JavaMathBigDecimal *) value) description];
}

- (id)init {
  return [super init];
}

@end
@implementation OrgOssPdfreporterEngineUtilJRValueStringUtils_DateSerializer

- (id)deserializeWithNSString:(NSString *)data {
  @try {
    long long int time = [JavaLangLong parseLongWithNSString:data];
    return [[JavaUtilDate alloc] initWithLongInt:time];
  }
  @catch (JavaLangNumberFormatException *e) {
    @throw [[OrgOssPdfreporterEngineJRRuntimeException alloc] initWithNSString:[NSString stringWithFormat:@"Error parsing Date data \"%@\"", data] withJavaLangThrowable:e];
  }
}

- (NSString *)serializeWithId:(id)value {
  return [JavaLangLong toStringWithLongInt:[((JavaUtilDate *) value) getTime]];
}

- (id)init {
  return [super init];
}

@end