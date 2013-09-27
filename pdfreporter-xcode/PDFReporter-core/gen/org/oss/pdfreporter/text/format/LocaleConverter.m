//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/text/format/LocaleConverter.java
//
//  Created by kendra on 9/27/13.
//

#include "IOSClass.h"
#include "java/lang/Byte.h"
#include "java/lang/Double.h"
#include "java/lang/Float.h"
#include "java/lang/Integer.h"
#include "java/lang/Long.h"
#include "java/lang/Short.h"
#include "java/math/BigDecimal.h"
#include "java/math/BigInteger.h"
#include "java/util/Date.h"
#include "java/util/Locale.h"
#include "org/oss/pdfreporter/exception/ConversionException.h"
#include "org/oss/pdfreporter/registry/ApiRegistry.h"
#include "org/oss/pdfreporter/text/ParseException.h"
#include "org/oss/pdfreporter/text/format/IDateFormat.h"
#include "org/oss/pdfreporter/text/format/INumberFormat.h"
#include "org/oss/pdfreporter/text/format/LocaleConverter.h"
#include "org/oss/pdfreporter/text/format/factory/IFormatFactory.h"

@implementation OrgOssPdfreporterTextFormatLocaleConverter

+ (id)convertWithNSString:(NSString *)valueString
             withIOSClass:(IOSClass *)valueClass
       withJavaUtilLocale:(JavaUtilLocale *)locale
             withNSString:(NSString *)pattern {
  @try {
    id<OrgOssPdfreporterTextFormatFactoryIFormatFactory> factory = [OrgOssPdfreporterRegistryApiRegistry getIFormatFactoryWithOrgOssPdfreporterTextFormatFactoryIFormatFactory_FormatTypeEnum:[OrgOssPdfreporterTextFormatFactoryIFormatFactory_FormatTypeEnum SIMPLE]];
    if ([nil_chk(valueClass) isAssignableFrom:[IOSClass classWithClass:[JavaUtilDate class]]]) {
      return [((id<OrgOssPdfreporterTextFormatIDateFormat>) nil_chk([((id<OrgOssPdfreporterTextFormatFactoryIFormatFactory>) nil_chk(factory)) newDateFormatWithNSString:pattern withJavaUtilLocale:locale withJavaUtilTimeZone:nil])) parseWithNSString:valueString];
    }
    NSNumber *number = [((id<OrgOssPdfreporterTextFormatINumberFormat>) nil_chk([((id<OrgOssPdfreporterTextFormatFactoryIFormatFactory>) nil_chk(factory)) newNumberFormatWithNSString:pattern withJavaUtilLocale:locale])) parseWithNSString:valueString];
    return [OrgOssPdfreporterTextFormatLocaleConverter getNumberWithNSNumber:number withIOSClass:valueClass];
  }
  @catch (OrgOssPdfreporterTextParseException *e) {
    @throw [[OrgOssPdfreporterExceptionConversionException alloc] initWithNSString:[NSString stringWithFormat:@"Conversion of '%@' to %@ with pattern: %@ and locale: %@ failed, %@", valueString, [((IOSClass *) nil_chk(valueClass)) getSimpleName], pattern, locale, [((OrgOssPdfreporterTextParseException *) nil_chk(e)) getMessage]]];
  }
}

+ (NSNumber *)getNumberWithNSNumber:(NSNumber *)number
                       withIOSClass:(IOSClass *)c {
  if ([nil_chk(c) isAssignableFrom:[IOSClass classWithClass:[JavaMathBigDecimal class]]]) {
    return [[JavaMathBigDecimal alloc] initWithDouble:[((NSNumber *) nil_chk(number)) doubleValue]];
  }
  else if ([nil_chk(c) isAssignableFrom:[IOSClass classWithClass:[JavaMathBigInteger class]]]) {
    return [JavaMathBigInteger valueOfWithLongInt:[((NSNumber *) nil_chk(number)) longLongValue]];
  }
  else if ([nil_chk(c) isAssignableFrom:[IOSClass classWithClass:[JavaLangDouble class]]]) {
    return [JavaLangDouble valueOfWithDouble:[((NSNumber *) nil_chk(number)) doubleValue]];
  }
  else if ([nil_chk(c) isAssignableFrom:[IOSClass classWithClass:[JavaLangFloat class]]]) {
    return [[JavaLangFloat alloc] initWithDouble:[((NSNumber *) nil_chk(number)) doubleValue]];
  }
  else if ([nil_chk(c) isAssignableFrom:[IOSClass classWithClass:[JavaLangLong class]]]) {
    return [[JavaLangLong alloc] initWithLongInt:[((NSNumber *) nil_chk(number)) longLongValue]];
  }
  else if ([nil_chk(c) isAssignableFrom:[IOSClass classWithClass:[JavaLangInteger class]]]) {
    return [[JavaLangInteger alloc] initWithInt:[((NSNumber *) nil_chk(number)) intValue]];
  }
  else if ([nil_chk(c) isAssignableFrom:[IOSClass classWithClass:[JavaLangShort class]]]) {
    return [[JavaLangShort alloc] initWithShortInt:[((NSNumber *) nil_chk(number)) shortValue]];
  }
  else if ([nil_chk(c) isAssignableFrom:[IOSClass classWithClass:[JavaLangByte class]]]) {
    return [[JavaLangByte alloc] initWithChar:[((NSNumber *) nil_chk(number)) charValue]];
  }
  @throw [[OrgOssPdfreporterExceptionConversionException alloc] initWithNSString:[NSString stringWithFormat:@"Not supported Number type %@", c]];
}

- (id)init {
  return [super init];
}

@end