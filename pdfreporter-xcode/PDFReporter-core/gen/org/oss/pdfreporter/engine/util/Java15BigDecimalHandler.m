//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/util/Java15BigDecimalHandler.java
//
//  Created by kendra on 9/27/13.
//

#include "IOSClass.h"
#include "IOSObjectArray.h"
#include "java/lang/IllegalArgumentException.h"
#include "java/lang/System.h"
#include "java/math/BigDecimal.h"
#include "java/math/MathContext.h"
#include "java/math/RoundingMode.h"
#include "org/oss/pdfreporter/engine/JRPropertiesUtil.h"
#include "org/oss/pdfreporter/engine/util/JRProperties.h"
#include "org/oss/pdfreporter/engine/util/Java15BigDecimalHandler.h"

@implementation OrgOssPdfreporterEngineUtilJava15BigDecimalHandler

static NSString * OrgOssPdfreporterEngineUtilJava15BigDecimalHandler_PROPERTY_MINIMUM_PRECISION_ = @"net.sf.jasperreports.big.decimal.minimum.precision";

@synthesize minPrecision = minPrecision_;
@synthesize mathContexts = mathContexts_;

+ (NSString *)PROPERTY_MINIMUM_PRECISION {
  return OrgOssPdfreporterEngineUtilJava15BigDecimalHandler_PROPERTY_MINIMUM_PRECISION_;
}

- (id)init {
  return [self initOrgOssPdfreporterEngineUtilJava15BigDecimalHandlerWithInt:[OrgOssPdfreporterEngineUtilJava15BigDecimalHandler readConfiguredPrecision]];
}

- (id)initOrgOssPdfreporterEngineUtilJava15BigDecimalHandlerWithInt:(int)minPrecision {
  if ((self = [super init])) {
    if (minPrecision <= 0) {
      @throw [[JavaLangIllegalArgumentException alloc] initWithNSString:@"minPrecision must be positive"];
    }
    self.minPrecision = minPrecision;
    self.mathContexts = [IOSObjectArray arrayWithLength:0 type:[IOSClass classWithClass:[JavaMathMathContext class]]];
  }
  return self;
}

- (id)initWithInt:(int)minPrecision {
  return [self initOrgOssPdfreporterEngineUtilJava15BigDecimalHandlerWithInt:minPrecision];
}

+ (int)readConfiguredPrecision {
  return [OrgOssPdfreporterEngineUtilJRProperties getIntegerPropertyWithNSString:OrgOssPdfreporterEngineUtilJava15BigDecimalHandler_PROPERTY_MINIMUM_PRECISION_];
}

- (JavaMathBigDecimal *)divideWithJavaMathBigDecimal:(JavaMathBigDecimal *)dividend
                              withJavaMathBigDecimal:(JavaMathBigDecimal *)divisor {
  int precision = [self getDivisionPrecisionWithJavaMathBigDecimal:dividend withJavaMathBigDecimal:divisor];
  JavaMathMathContext *mathContext = [self getMathContextWithInt:precision];
  return [((JavaMathBigDecimal *) nil_chk(dividend)) divideWithJavaMathBigDecimal:divisor withJavaMathMathContext:mathContext];
}

- (int)getDivisionPrecisionWithJavaMathBigDecimal:(JavaMathBigDecimal *)dividend
                           withJavaMathBigDecimal:(JavaMathBigDecimal *)divisor {
  int precision = minPrecision_;
  if ([((JavaMathBigDecimal *) nil_chk(dividend)) precision] > precision) {
    precision = [((JavaMathBigDecimal *) nil_chk(dividend)) precision];
  }
  if ([((JavaMathBigDecimal *) nil_chk(divisor)) precision] > precision) {
    precision = [((JavaMathBigDecimal *) nil_chk(divisor)) precision];
  }
  return precision;
}

- (JavaMathMathContext *)getMathContextWithInt:(int)precision {
  IOSObjectArray *contexts = mathContexts_;
  int idx = precision - minPrecision_;
  if (contexts == nil || (int) [((IOSObjectArray *) nil_chk(contexts)) count] < idx + 1) {
    IOSObjectArray *newContexts = [IOSObjectArray arrayWithLength:idx + 1 type:[IOSClass classWithClass:[JavaMathMathContext class]]];
    if (contexts != nil) {
      [JavaLangSystem arraycopyWithId:contexts withInt:0 withId:newContexts withInt:0 withInt:(int) [contexts count]];
    }
    mathContexts_ = newContexts;
    contexts = newContexts;
  }
  JavaMathMathContext *mathContext = [((IOSObjectArray *) nil_chk(contexts)) objectAtIndex:idx];
  if (mathContext == nil) {
    mathContext = [[JavaMathMathContext alloc] initWithInt:precision withJavaMathRoundingModeEnum:[JavaMathRoundingModeEnum HALF_UP]];
    (void) [((IOSObjectArray *) nil_chk(contexts)) replaceObjectAtIndex:idx withObject:mathContext];
  }
  return mathContext;
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterEngineUtilJava15BigDecimalHandler *typedCopy = (OrgOssPdfreporterEngineUtilJava15BigDecimalHandler *) copy;
  typedCopy.minPrecision = minPrecision_;
  typedCopy.mathContexts = mathContexts_;
}

@end