//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-extensions/src/org/oss/pdfreporter/uses/net/sourceforge/jeval/ExpressionTree.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterUsesNetSourceforgeJevalExpressionTree_H_
#define _OrgOssPdfreporterUsesNetSourceforgeJevalExpressionTree_H_

@class OrgOssPdfreporterUsesNetSourceforgeJevalEvaluator;
@protocol OrgOssPdfreporterUsesNetSourceforgeJevalOperatorOperator;

#import "JreEmulation.h"

@interface OrgOssPdfreporterUsesNetSourceforgeJevalExpressionTree : NSObject {
 @public
  id leftOperand_;
  id rightOperand_;
  id<OrgOssPdfreporterUsesNetSourceforgeJevalOperatorOperator> operator__;
  id<OrgOssPdfreporterUsesNetSourceforgeJevalOperatorOperator> unaryOperator_;
  OrgOssPdfreporterUsesNetSourceforgeJevalEvaluator *evaluator_;
}

@property (nonatomic, strong) id leftOperand;
@property (nonatomic, strong) id rightOperand;
@property (nonatomic, strong) id<OrgOssPdfreporterUsesNetSourceforgeJevalOperatorOperator> operator_;
@property (nonatomic, strong) id<OrgOssPdfreporterUsesNetSourceforgeJevalOperatorOperator> unaryOperator;
@property (nonatomic, strong) OrgOssPdfreporterUsesNetSourceforgeJevalEvaluator *evaluator;

- (id)initWithOrgOssPdfreporterUsesNetSourceforgeJevalEvaluator:(OrgOssPdfreporterUsesNetSourceforgeJevalEvaluator *)evaluator
                                                         withId:(id)leftOperand
                                                         withId:(id)rightOperand
   withOrgOssPdfreporterUsesNetSourceforgeJevalOperatorOperator:(id<OrgOssPdfreporterUsesNetSourceforgeJevalOperatorOperator>)operator_
   withOrgOssPdfreporterUsesNetSourceforgeJevalOperatorOperator:(id<OrgOssPdfreporterUsesNetSourceforgeJevalOperatorOperator>)unaryOperator;
- (id)getLeftOperand;
- (id)getRightOperand;
- (id<OrgOssPdfreporterUsesNetSourceforgeJevalOperatorOperator>)getOperator;
- (id<OrgOssPdfreporterUsesNetSourceforgeJevalOperatorOperator>)getUnaryOperator;
- (NSString *)evaluateWithBOOL:(BOOL)wrapStringFunctionResults;
@end

#endif // _OrgOssPdfreporterUsesNetSourceforgeJevalExpressionTree_H_