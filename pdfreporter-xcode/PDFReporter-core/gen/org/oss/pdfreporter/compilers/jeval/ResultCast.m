//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/compilers/jeval/ResultCast.java
//
//  Created by kendra on 9/27/13.
//

#include "IOSClass.h"
#include "java/lang/Boolean.h"
#include "java/lang/Double.h"
#include "java/lang/Exception.h"
#include "java/lang/IllegalArgumentException.h"
#include "java/lang/Integer.h"
#include "java/lang/Long.h"
#include "java/util/Date.h"
#include "java/util/logging/Level.h"
#include "java/util/logging/Logger.h"
#include "java/util/regex/Matcher.h"
#include "java/util/regex/Pattern.h"
#include "org/oss/pdfreporter/compilers/ExpressionEvaluationException.h"
#include "org/oss/pdfreporter/compilers/jeval/ExpressionParseException.h"
#include "org/oss/pdfreporter/compilers/jeval/JEvalExpression.h"
#include "org/oss/pdfreporter/compilers/jeval/ResultCast.h"
#include "org/oss/pdfreporter/compilers/jeval/ResultUtil.h"
#include "org/oss/pdfreporter/uses/net/sourceforge/jeval/EvaluationConstants.h"

@implementation OrgOssPdfreporterCompilersJevalResultCast

static JavaUtilLoggingLogger * OrgOssPdfreporterCompilersJevalResultCast_logger_;
static NSString * OrgOssPdfreporterCompilersJevalResultCast_CAST_MATCH_;
static JavaUtilRegexPattern * OrgOssPdfreporterCompilersJevalResultCast_CAST_SPLIT_;

@synthesize type = type_;
@synthesize expression = expression_;

+ (JavaUtilLoggingLogger *)logger {
  return OrgOssPdfreporterCompilersJevalResultCast_logger_;
}

+ (NSString *)CAST_MATCH {
  return OrgOssPdfreporterCompilersJevalResultCast_CAST_MATCH_;
}

+ (void)setCAST_MATCH:(NSString *)CAST_MATCH {
  OrgOssPdfreporterCompilersJevalResultCast_CAST_MATCH_ = CAST_MATCH;
}

+ (JavaUtilRegexPattern *)CAST_SPLIT {
  return OrgOssPdfreporterCompilersJevalResultCast_CAST_SPLIT_;
}

+ (void)setCAST_SPLIT:(JavaUtilRegexPattern *)CAST_SPLIT {
  OrgOssPdfreporterCompilersJevalResultCast_CAST_SPLIT_ = CAST_SPLIT;
}

- (id)initOrgOssPdfreporterCompilersJevalResultCastWithOrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum:(OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum *)type
                                                                 withOrgOssPdfreporterCompilersJevalJEvalExpression:(OrgOssPdfreporterCompilersJevalJEvalExpression *)expression {
  if ((self = [super init])) {
    expression_ = nil;
    self.type = type;
    self.expression = expression;
  }
  return self;
}

- (id)initWithOrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum:(OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum *)type
                        withOrgOssPdfreporterCompilersJevalJEvalExpression:(OrgOssPdfreporterCompilersJevalJEvalExpression *)expression {
  return [self initOrgOssPdfreporterCompilersJevalResultCastWithOrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum:type withOrgOssPdfreporterCompilersJevalJEvalExpression:expression];
}

- (id)initWithOrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum:(OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum *)type {
  return [self initOrgOssPdfreporterCompilersJevalResultCastWithOrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum:type withOrgOssPdfreporterCompilersJevalJEvalExpression:nil];
}

- (id)init {
  return [self initOrgOssPdfreporterCompilersJevalResultCastWithOrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum:[OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum STRING] withOrgOssPdfreporterCompilersJevalJEvalExpression:nil];
}

+ (BOOL)isCastWithNSString:(NSString *)text {
  return [((NSString *) nil_chk(text)) matches:OrgOssPdfreporterCompilersJevalResultCast_CAST_MATCH_];
}

+ (NSString *)getNextWithNSString:(NSString *)text {
  JavaUtilRegexMatcher *m = [((JavaUtilRegexPattern *) nil_chk(OrgOssPdfreporterCompilersJevalResultCast_CAST_SPLIT_)) matcherWithJavaLangCharSequence:text];
  if ([((JavaUtilRegexMatcher *) nil_chk(m)) find]) {
    return [((NSString *) nil_chk(text)) substring:[((JavaUtilRegexMatcher *) nil_chk(m)) end] + 1];
  }
  @throw [[OrgOssPdfreporterCompilersJevalExpressionParseException alloc] initWithNSString:[NSString stringWithFormat:@"Pattern: %@ does not match: %@", OrgOssPdfreporterCompilersJevalResultCast_CAST_SPLIT_, text]];
}

+ (OrgOssPdfreporterCompilersJevalResultCast *)parseCastWithNSString:(NSString *)s {
  if ([((NSString *) nil_chk(s)) matches:OrgOssPdfreporterCompilersJevalResultCast_CAST_MATCH_]) {
    NSString *cast = [OrgOssPdfreporterCompilersJevalResultCast extractWithJavaUtilRegexPattern:OrgOssPdfreporterCompilersJevalResultCast_CAST_SPLIT_ withNSString:s];
    if ([((NSString *) nil_chk(cast)) equalsIgnoreCase:@"boolean"]) {
      return [[OrgOssPdfreporterCompilersJevalResultCast alloc] initWithOrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum:[OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum BOOLEAN]];
    }
    else if ([((NSString *) nil_chk(cast)) equalsIgnoreCase:@"integer"] || [((NSString *) nil_chk(cast)) equalsIgnoreCase:@"int"]) {
      return [[OrgOssPdfreporterCompilersJevalResultCast alloc] initWithOrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum:[OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum INTEGER]];
    }
    else if ([((NSString *) nil_chk(cast)) equalsIgnoreCase:@"double"] || [((NSString *) nil_chk(cast)) equalsIgnoreCase:@"float"]) {
      return [[OrgOssPdfreporterCompilersJevalResultCast alloc] initWithOrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum:[OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum DOUBLE]];
    }
    else if ([((NSString *) nil_chk(cast)) equalsIgnoreCase:@"string"]) {
      return [[OrgOssPdfreporterCompilersJevalResultCast alloc] initWithOrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum:[OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum STRING]];
    }
    else if ([((NSString *) nil_chk(cast)) equalsIgnoreCase:@"long"]) {
      return [[OrgOssPdfreporterCompilersJevalResultCast alloc] initWithOrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum:[OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum LONG]];
    }
    else if ([((NSString *) nil_chk(cast)) equalsIgnoreCase:@"date"]) {
      return [[OrgOssPdfreporterCompilersJevalResultCast alloc] initWithOrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum:[OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum DATE]];
    }
  }
  @throw [[OrgOssPdfreporterCompilersJevalExpressionParseException alloc] initWithNSString:[NSString stringWithFormat:@"Unsupported cast operator: %@", s]];
}

+ (NSString *)extractWithJavaUtilRegexPattern:(JavaUtilRegexPattern *)p
                                 withNSString:(NSString *)text {
  JavaUtilRegexMatcher *m = [((JavaUtilRegexPattern *) nil_chk(p)) matcherWithJavaLangCharSequence:text];
  if ([((JavaUtilRegexMatcher *) nil_chk(m)) find]) {
    return [((JavaUtilRegexMatcher *) nil_chk(m)) group];
  }
  @throw [[OrgOssPdfreporterCompilersJevalExpressionParseException alloc] initWithNSString:[NSString stringWithFormat:@"Pattern: %@ does not match: %@", p, text]];
}

- (void)setExpressionWithOrgOssPdfreporterCompilersJevalJEvalExpression:(OrgOssPdfreporterCompilersJevalJEvalExpression *)expression {
  self.expression = expression;
}

- (id)doCastWithNSString:(NSString *)result {
  if ([OrgOssPdfreporterCompilersJevalResultUtil isNullWithNSString:result]) {
    return nil;
  }
  [self assertResultTypeWithNSString:result];
  switch ([type_ ordinal]) {
    case OrgOssPdfreporterCompilersJevalResultCast_ExpressionType_STRING:
    return [OrgOssPdfreporterCompilersJevalResultUtil getStringResultWithNSString:result];
    case OrgOssPdfreporterCompilersJevalResultCast_ExpressionType_BOOLEAN:
    return [OrgOssPdfreporterCompilersJevalResultUtil getBooleanResultWithNSString:result];
    case OrgOssPdfreporterCompilersJevalResultCast_ExpressionType_INTEGER:
    return [OrgOssPdfreporterCompilersJevalResultUtil getIntResultWithNSString:result];
    case OrgOssPdfreporterCompilersJevalResultCast_ExpressionType_LONG:
    return [OrgOssPdfreporterCompilersJevalResultUtil getLongResultWithNSString:result];
    case OrgOssPdfreporterCompilersJevalResultCast_ExpressionType_DOUBLE:
    return [OrgOssPdfreporterCompilersJevalResultUtil getDoubleResultWithNSString:result];
    case OrgOssPdfreporterCompilersJevalResultCast_ExpressionType_DATE:
    return [OrgOssPdfreporterCompilersJevalResultUtil geDateResultWithNSString:result];
  }
  @throw [[OrgOssPdfreporterCompilersExpressionEvaluationException alloc] initWithNSString:[NSString stringWithFormat:@"Unreachable %@, result: %@", type_, result]];
}

- (void)assertResultTypeWithNSString:(NSString *)result {
  BOOL isText = [OrgOssPdfreporterCompilersJevalResultUtil isStringWithNSString:result withUnichar:OrgOssPdfreporterUsesNetSourceforgeJevalEvaluationConstants_SINGLE_QUOTE];
  if (type_ == [OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum STRING] && !isText) {
    @throw [[OrgOssPdfreporterCompilersExpressionEvaluationException alloc] initWithNSString:[NSString stringWithFormat:@"Result of type String expected actual value is unquoted: %@", result]];
  }
  else if (type_ != [OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum STRING] && isText) {
    @throw [[OrgOssPdfreporterCompilersExpressionEvaluationException alloc] initWithNSString:[NSString stringWithFormat:@"Result of type %@ expected actual value is quoted: %@", type_, result]];
  }
}

- (id)getValue {
  @try {
    return [self doCastWithNSString:[((OrgOssPdfreporterCompilersJevalJEvalExpression *) nil_chk(self.expression)) evaluateValue]];
  }
  @catch (JavaLangException *e) {
    [((JavaUtilLoggingLogger *) nil_chk(OrgOssPdfreporterCompilersJevalResultCast_logger_)) logWithJavaUtilLoggingLevel:[JavaUtilLoggingLevel SEVERE] withNSString:[NSString stringWithFormat:@"Error evaluating expression: %@", [((OrgOssPdfreporterCompilersJevalJEvalExpression *) nil_chk(expression_)) getExpression]] withJavaLangThrowable:e];
    @throw [[OrgOssPdfreporterCompilersExpressionEvaluationException alloc] initWithJavaLangThrowable:e];
  }
}

- (id)getOldValue {
  @try {
    return [self doCastWithNSString:[((OrgOssPdfreporterCompilersJevalJEvalExpression *) nil_chk(self.expression)) evaluateOldValue]];
  }
  @catch (JavaLangException *e) {
    [((JavaUtilLoggingLogger *) nil_chk(OrgOssPdfreporterCompilersJevalResultCast_logger_)) logWithJavaUtilLoggingLevel:[JavaUtilLoggingLevel SEVERE] withNSString:[NSString stringWithFormat:@"Error evaluating expression: %@", [((OrgOssPdfreporterCompilersJevalJEvalExpression *) nil_chk(expression_)) getExpression]] withJavaLangThrowable:e];
    @throw [[OrgOssPdfreporterCompilersExpressionEvaluationException alloc] initWithJavaLangThrowable:e];
  }
}

+ (void)initialize {
  if (self == [OrgOssPdfreporterCompilersJevalResultCast class]) {
    OrgOssPdfreporterCompilersJevalResultCast_logger_ = [JavaUtilLoggingLogger getLoggerWithNSString:[[IOSClass classWithClass:[OrgOssPdfreporterCompilersJevalResultCast class]] getName]];
    OrgOssPdfreporterCompilersJevalResultCast_CAST_MATCH_ = @".*\\(\\s*\\w+\\s*\\).*";
    OrgOssPdfreporterCompilersJevalResultCast_CAST_SPLIT_ = [JavaUtilRegexPattern compileWithNSString:@"\\w+"];
  }
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterCompilersJevalResultCast *typedCopy = (OrgOssPdfreporterCompilersJevalResultCast *) copy;
  typedCopy.type = type_;
  typedCopy.expression = expression_;
}

@end

static OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum *OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_BOOLEAN;
static OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum *OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_INTEGER;
static OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum *OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_LONG;
static OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum *OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_DOUBLE;
static OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum *OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_STRING;
static OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum *OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_DATE;
IOSObjectArray *OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_values;

@implementation OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum

+ (OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum *)BOOLEAN {
  return OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_BOOLEAN;
}
+ (OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum *)INTEGER {
  return OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_INTEGER;
}
+ (OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum *)LONG {
  return OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_LONG;
}
+ (OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum *)DOUBLE {
  return OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_DOUBLE;
}
+ (OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum *)STRING {
  return OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_STRING;
}
+ (OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum *)DATE {
  return OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_DATE;
}

- (id)copyWithZone:(NSZone *)zone {
  return self;
}

- (id)initWithNSString:(NSString *)name withInt:(int)ordinal {
  return [super initWithNSString:name withInt:ordinal];
}

+ (void)initialize {
  if (self == [OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum class]) {
    OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_BOOLEAN = [[OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum alloc] initWithNSString:@"BOOLEAN" withInt:0];
    OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_INTEGER = [[OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum alloc] initWithNSString:@"INTEGER" withInt:1];
    OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_LONG = [[OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum alloc] initWithNSString:@"LONG" withInt:2];
    OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_DOUBLE = [[OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum alloc] initWithNSString:@"DOUBLE" withInt:3];
    OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_STRING = [[OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum alloc] initWithNSString:@"STRING" withInt:4];
    OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_DATE = [[OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum alloc] initWithNSString:@"DATE" withInt:5];
    OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_values = [[IOSObjectArray alloc] initWithObjects:(id[]){ OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_BOOLEAN, OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_INTEGER, OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_LONG, OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_DOUBLE, OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_STRING, OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_DATE, nil } count:6 type:[IOSClass classWithClass:[OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum class]]];
  }
}

+ (IOSObjectArray *)values {
  return [IOSObjectArray arrayWithArray:OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_values];
}

+ (OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum *)valueOfWithNSString:(NSString *)name {
  for (int i = 0; i < [OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_values count]; i++) {
    OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum *e = [OrgOssPdfreporterCompilersJevalResultCast_ExpressionTypeEnum_values objectAtIndex:i];
    if ([name isEqual:[e name]]) {
      return e;
    }
  }
  @throw [[JavaLangIllegalArgumentException alloc] initWithNSString:name];
  return nil;
}

@end