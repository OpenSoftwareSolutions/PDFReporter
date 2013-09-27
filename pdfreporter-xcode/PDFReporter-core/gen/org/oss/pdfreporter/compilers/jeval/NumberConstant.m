//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/compilers/jeval/NumberConstant.java
//
//  Created by kendra on 9/27/13.
//

#include "java/lang/Byte.h"
#include "java/lang/Integer.h"
#include "java/lang/Long.h"
#include "java/lang/Short.h"
#include "java/util/regex/Matcher.h"
#include "java/util/regex/Pattern.h"
#include "org/oss/pdfreporter/compilers/jeval/ExpressionParseException.h"
#include "org/oss/pdfreporter/compilers/jeval/NumberConstant.h"

@implementation OrgOssPdfreporterCompilersJevalNumberConstant

static NSString * OrgOssPdfreporterCompilersJevalNumberConstant_INTEGER_MATCH_;
static NSString * OrgOssPdfreporterCompilersJevalNumberConstant_LONG_MATCH_;
static NSString * OrgOssPdfreporterCompilersJevalNumberConstant_SHORT_MATCH_;
static NSString * OrgOssPdfreporterCompilersJevalNumberConstant_NUMBER_MATCH_;
static NSString * OrgOssPdfreporterCompilersJevalNumberConstant_BYTE_MATCH_;
static JavaUtilRegexPattern * OrgOssPdfreporterCompilersJevalNumberConstant_NUMBER_SPLIT_;

@synthesize number = number_;

+ (NSString *)INTEGER_MATCH {
  return OrgOssPdfreporterCompilersJevalNumberConstant_INTEGER_MATCH_;
}

+ (void)setINTEGER_MATCH:(NSString *)INTEGER_MATCH {
  OrgOssPdfreporterCompilersJevalNumberConstant_INTEGER_MATCH_ = INTEGER_MATCH;
}

+ (NSString *)LONG_MATCH {
  return OrgOssPdfreporterCompilersJevalNumberConstant_LONG_MATCH_;
}

+ (void)setLONG_MATCH:(NSString *)LONG_MATCH {
  OrgOssPdfreporterCompilersJevalNumberConstant_LONG_MATCH_ = LONG_MATCH;
}

+ (NSString *)SHORT_MATCH {
  return OrgOssPdfreporterCompilersJevalNumberConstant_SHORT_MATCH_;
}

+ (void)setSHORT_MATCH:(NSString *)SHORT_MATCH {
  OrgOssPdfreporterCompilersJevalNumberConstant_SHORT_MATCH_ = SHORT_MATCH;
}

+ (NSString *)NUMBER_MATCH {
  return OrgOssPdfreporterCompilersJevalNumberConstant_NUMBER_MATCH_;
}

+ (void)setNUMBER_MATCH:(NSString *)NUMBER_MATCH {
  OrgOssPdfreporterCompilersJevalNumberConstant_NUMBER_MATCH_ = NUMBER_MATCH;
}

+ (NSString *)BYTE_MATCH {
  return OrgOssPdfreporterCompilersJevalNumberConstant_BYTE_MATCH_;
}

+ (void)setBYTE_MATCH:(NSString *)BYTE_MATCH {
  OrgOssPdfreporterCompilersJevalNumberConstant_BYTE_MATCH_ = BYTE_MATCH;
}

+ (JavaUtilRegexPattern *)NUMBER_SPLIT {
  return OrgOssPdfreporterCompilersJevalNumberConstant_NUMBER_SPLIT_;
}

+ (void)setNUMBER_SPLIT:(JavaUtilRegexPattern *)NUMBER_SPLIT {
  OrgOssPdfreporterCompilersJevalNumberConstant_NUMBER_SPLIT_ = NUMBER_SPLIT;
}

+ (BOOL)isNumberWithNSString:(NSString *)text {
  return [((NSString *) nil_chk(text)) matches:OrgOssPdfreporterCompilersJevalNumberConstant_INTEGER_MATCH_] || [((NSString *) nil_chk(text)) matches:OrgOssPdfreporterCompilersJevalNumberConstant_LONG_MATCH_] || [((NSString *) nil_chk(text)) matches:OrgOssPdfreporterCompilersJevalNumberConstant_SHORT_MATCH_] || [((NSString *) nil_chk(text)) matches:OrgOssPdfreporterCompilersJevalNumberConstant_BYTE_MATCH_] || [((NSString *) nil_chk(text)) matches:OrgOssPdfreporterCompilersJevalNumberConstant_NUMBER_MATCH_];
}

+ (OrgOssPdfreporterCompilersJevalNumberConstant *)parseNumberWithNSString:(NSString *)s {
  if ([((NSString *) nil_chk(s)) matches:OrgOssPdfreporterCompilersJevalNumberConstant_INTEGER_MATCH_]) {
    return [[OrgOssPdfreporterCompilersJevalNumberConstant alloc] initWithNSNumber:[JavaLangInteger valueOfWithNSString:[OrgOssPdfreporterCompilersJevalNumberConstant extractWithJavaUtilRegexPattern:OrgOssPdfreporterCompilersJevalNumberConstant_NUMBER_SPLIT_ withNSString:s]]];
  }
  else if ([((NSString *) nil_chk(s)) matches:OrgOssPdfreporterCompilersJevalNumberConstant_LONG_MATCH_]) {
    return [[OrgOssPdfreporterCompilersJevalNumberConstant alloc] initWithNSNumber:[JavaLangLong valueOfWithNSString:[OrgOssPdfreporterCompilersJevalNumberConstant extractWithJavaUtilRegexPattern:OrgOssPdfreporterCompilersJevalNumberConstant_NUMBER_SPLIT_ withNSString:s]]];
  }
  else if ([((NSString *) nil_chk(s)) matches:OrgOssPdfreporterCompilersJevalNumberConstant_SHORT_MATCH_]) {
    return [[OrgOssPdfreporterCompilersJevalNumberConstant alloc] initWithNSNumber:[JavaLangShort valueOfWithNSString:[OrgOssPdfreporterCompilersJevalNumberConstant extractWithJavaUtilRegexPattern:OrgOssPdfreporterCompilersJevalNumberConstant_NUMBER_SPLIT_ withNSString:s]]];
  }
  else if ([((NSString *) nil_chk(s)) matches:OrgOssPdfreporterCompilersJevalNumberConstant_BYTE_MATCH_]) {
    return [[OrgOssPdfreporterCompilersJevalNumberConstant alloc] initWithNSNumber:[JavaLangByte valueOfWithNSString:[OrgOssPdfreporterCompilersJevalNumberConstant extractWithJavaUtilRegexPattern:OrgOssPdfreporterCompilersJevalNumberConstant_NUMBER_SPLIT_ withNSString:s]]];
  }
  else if ([((NSString *) nil_chk(s)) matches:OrgOssPdfreporterCompilersJevalNumberConstant_NUMBER_MATCH_]) {
    return [[OrgOssPdfreporterCompilersJevalNumberConstant alloc] initWithNSNumber:[JavaLangInteger valueOfWithNSString:[OrgOssPdfreporterCompilersJevalNumberConstant extractWithJavaUtilRegexPattern:OrgOssPdfreporterCompilersJevalNumberConstant_NUMBER_SPLIT_ withNSString:s]]];
  }
  @throw [[OrgOssPdfreporterCompilersJevalExpressionParseException alloc] initWithNSString:[NSString stringWithFormat:@"Unsupported Numberconstant %@", s]];
}

+ (NSString *)extractWithJavaUtilRegexPattern:(JavaUtilRegexPattern *)p
                                 withNSString:(NSString *)text {
  JavaUtilRegexMatcher *m = [((JavaUtilRegexPattern *) nil_chk(p)) matcherWithJavaLangCharSequence:text];
  if ([((JavaUtilRegexMatcher *) nil_chk(m)) find]) {
    return [((JavaUtilRegexMatcher *) nil_chk(m)) group];
  }
  @throw [[OrgOssPdfreporterCompilersJevalExpressionParseException alloc] initWithNSString:[NSString stringWithFormat:@"Pattern: %@ does not match: %@", p, text]];
}

- (id)initWithNSNumber:(NSNumber *)number {
  if ((self = [super init])) {
    self.number = number;
  }
  return self;
}

- (id)getValue {
  return number_;
}

+ (void)initialize {
  if (self == [OrgOssPdfreporterCompilersJevalNumberConstant class]) {
    OrgOssPdfreporterCompilersJevalNumberConstant_INTEGER_MATCH_ = @"new java\\.lang\\.Integer\\(-?\\d+\\)";
    OrgOssPdfreporterCompilersJevalNumberConstant_LONG_MATCH_ = @"new java\\.lang\\.Long\\(-?\\d+\\)";
    OrgOssPdfreporterCompilersJevalNumberConstant_SHORT_MATCH_ = @"new java\\.lang\\.Short\\(-?\\d+\\)";
    OrgOssPdfreporterCompilersJevalNumberConstant_NUMBER_MATCH_ = @"new java\\.lang\\.Number\\(-?\\d+\\)";
    OrgOssPdfreporterCompilersJevalNumberConstant_BYTE_MATCH_ = @"new java\\.lang\\.Byte\\(-?\\d+\\)";
    OrgOssPdfreporterCompilersJevalNumberConstant_NUMBER_SPLIT_ = [JavaUtilRegexPattern compileWithNSString:@"-?\\d+"];
  }
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterCompilersJevalNumberConstant *typedCopy = (OrgOssPdfreporterCompilersJevalNumberConstant *) copy;
  typedCopy.number = number_;
}

@end