//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-extensions/src/org/oss/pdfreporter/uses/net/sourceforge/jeval/operator/AbstractOperator.java
//
//  Created by kendra on 9/27/13.
//

#include "java/lang/IllegalStateException.h"
#include "org/oss/pdfreporter/uses/net/sourceforge/jeval/EvaluationException.h"
#include "org/oss/pdfreporter/uses/net/sourceforge/jeval/operator/AbstractOperator.h"

@implementation OrgOssPdfreporterUsesNetSourceforgeJevalOperatorAbstractOperator

@synthesize symbol = symbol_;
@synthesize precedence = precedence_;
@synthesize unary = unary_;

- (id)initWithNSString:(NSString *)symbol
               withInt:(int)precedence {
  if ((self = [super init])) {
    symbol_ = nil;
    precedence_ = 0;
    unary_ = NO;
    self.symbol = symbol;
    self.precedence = precedence;
  }
  return self;
}

- (id)initWithNSString:(NSString *)symbol
               withInt:(int)precedence
              withBOOL:(BOOL)unary {
  if ((self = [super init])) {
    symbol_ = nil;
    precedence_ = 0;
    unary_ = NO;
    self.symbol = symbol;
    self.precedence = precedence;
    self.unary = unary;
  }
  return self;
}

- (double)evaluateWithDouble:(double)leftOperand
                  withDouble:(double)rightOperand {
  return 0;
}

- (NSString *)evaluateWithNSString:(NSString *)leftOperand
                      withNSString:(NSString *)rightOperand {
  @throw [[OrgOssPdfreporterUsesNetSourceforgeJevalEvaluationException alloc] initWithNSString:@"Invalid operation for a string."];
}

- (double)evaluateWithDouble:(double)operand {
  return 0;
}

- (NSString *)getSymbol {
  return symbol_;
}

- (int)getPrecedence {
  return precedence_;
}

- (int)getLength {
  return [((NSString *) nil_chk(symbol_)) length];
}

- (BOOL)isUnary {
  return unary_;
}

- (BOOL)isEqual:(id)object {
  if (object == nil) {
    return NO;
  }
  if (!([object isKindOfClass:[OrgOssPdfreporterUsesNetSourceforgeJevalOperatorAbstractOperator class]])) {
    @throw [[JavaLangIllegalStateException alloc] initWithNSString:@"Invalid operator object."];
  }
  OrgOssPdfreporterUsesNetSourceforgeJevalOperatorAbstractOperator *operator_ = (OrgOssPdfreporterUsesNetSourceforgeJevalOperatorAbstractOperator *) object;
  if ([((NSString *) nil_chk(symbol_)) isEqual:[((OrgOssPdfreporterUsesNetSourceforgeJevalOperatorAbstractOperator *) nil_chk(operator_)) getSymbol]]) {
    return YES;
  }
  return NO;
}

- (NSString *)description {
  return [self getSymbol];
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterUsesNetSourceforgeJevalOperatorAbstractOperator *typedCopy = (OrgOssPdfreporterUsesNetSourceforgeJevalOperatorAbstractOperator *) copy;
  typedCopy.symbol = symbol_;
  typedCopy.precedence = precedence_;
  typedCopy.unary = unary_;
}

@end