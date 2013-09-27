//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/crosstabs/base/JRBaseCrosstab.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterCrosstabsBaseJRBaseCrosstab_H_
#define _OrgOssPdfreporterCrosstabsBaseJRBaseCrosstab_H_

@class IOSObjectArray;
@class JavaLangBoolean;
@class OrgOssPdfreporterEngineBaseJRBaseObjectFactory;
@class OrgOssPdfreporterEngineJRExpressionCollector;
@class OrgOssPdfreporterEngineTypeModeEnumEnum;
@class OrgOssPdfreporterEngineTypeRunDirectionEnumEnum;
@protocol OrgOssPdfreporterCommonsArraysArray2D;
@protocol OrgOssPdfreporterCrosstabsJRCellContents;
@protocol OrgOssPdfreporterCrosstabsJRCrosstabDataset;
@protocol OrgOssPdfreporterEngineJRElement;
@protocol OrgOssPdfreporterEngineJRExpression;
@protocol OrgOssPdfreporterEngineJRLineBox;
@protocol OrgOssPdfreporterEngineJRVisitor;
@protocol OrgOssPdfreporterGeometryIColor;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/crosstabs/JRCrosstab.h"
#include "org/oss/pdfreporter/engine/base/JRBaseElement.h"

#define OrgOssPdfreporterCrosstabsBaseJRBaseCrosstab_serialVersionUID 10200

@interface OrgOssPdfreporterCrosstabsBaseJRBaseCrosstab : OrgOssPdfreporterEngineBaseJRBaseElement < OrgOssPdfreporterCrosstabsJRCrosstab > {
 @public
  int id__;
  IOSObjectArray *parameters_;
  IOSObjectArray *variables_;
  id<OrgOssPdfreporterEngineJRExpression> parametersMapExpression_;
  id<OrgOssPdfreporterCrosstabsJRCrosstabDataset> dataset_;
  IOSObjectArray *rowGroups_;
  IOSObjectArray *columnGroups_;
  IOSObjectArray *measures_;
  int columnBreakOffset_;
  BOOL repeatColumnHeaders_;
  BOOL repeatRowHeaders_;
  OrgOssPdfreporterEngineTypeRunDirectionEnumEnum *runDirectionValue_;
  id<OrgOssPdfreporterCommonsArraysArray2D> cells_;
  id<OrgOssPdfreporterCrosstabsJRCellContents> whenNoDataCell_;
  id<OrgOssPdfreporterCrosstabsJRCellContents> headerCell_;
  JavaLangBoolean *ignoreWidth_;
  id<OrgOssPdfreporterEngineJRLineBox> lineBox_;
}

@property (nonatomic, assign) int id_;
@property (nonatomic, strong) IOSObjectArray *parameters;
@property (nonatomic, strong) IOSObjectArray *variables;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRExpression> parametersMapExpression;
@property (nonatomic, strong) id<OrgOssPdfreporterCrosstabsJRCrosstabDataset> dataset;
@property (nonatomic, strong) IOSObjectArray *rowGroups;
@property (nonatomic, strong) IOSObjectArray *columnGroups;
@property (nonatomic, strong) IOSObjectArray *measures;
@property (nonatomic, assign) int columnBreakOffset;
@property (nonatomic, assign) BOOL repeatColumnHeaders;
@property (nonatomic, assign) BOOL repeatRowHeaders;
@property (nonatomic, strong) OrgOssPdfreporterEngineTypeRunDirectionEnumEnum *runDirectionValue;
@property (nonatomic, strong) id<OrgOssPdfreporterCommonsArraysArray2D> cells;
@property (nonatomic, strong) id<OrgOssPdfreporterCrosstabsJRCellContents> whenNoDataCell;
@property (nonatomic, strong) id<OrgOssPdfreporterCrosstabsJRCellContents> headerCell;
@property (nonatomic, strong) JavaLangBoolean *ignoreWidth;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRLineBox> lineBox;

+ (NSString *)PROPERTY_RUN_DIRECTION;
+ (NSString *)PROPERTY_IGNORE_WIDTH;
- (id)initWithOrgOssPdfreporterCrosstabsJRCrosstab:(id<OrgOssPdfreporterCrosstabsJRCrosstab>)crosstab
withOrgOssPdfreporterEngineBaseJRBaseObjectFactory:(OrgOssPdfreporterEngineBaseJRBaseObjectFactory *)factory
                                           withInt:(int)id_;
- (OrgOssPdfreporterEngineTypeModeEnumEnum *)getModeValue;
- (void)copyParametersWithOrgOssPdfreporterCrosstabsJRCrosstab:(id<OrgOssPdfreporterCrosstabsJRCrosstab>)crosstab
            withOrgOssPdfreporterEngineBaseJRBaseObjectFactory:(OrgOssPdfreporterEngineBaseJRBaseObjectFactory *)factory OBJC_METHOD_FAMILY_NONE;
- (void)copyVariablesWithOrgOssPdfreporterCrosstabsJRCrosstab:(id<OrgOssPdfreporterCrosstabsJRCrosstab>)crosstab
           withOrgOssPdfreporterEngineBaseJRBaseObjectFactory:(OrgOssPdfreporterEngineBaseJRBaseObjectFactory *)factory OBJC_METHOD_FAMILY_NONE;
- (void)copyRowGroupsWithOrgOssPdfreporterCrosstabsJRCrosstab:(id<OrgOssPdfreporterCrosstabsJRCrosstab>)crosstab
           withOrgOssPdfreporterEngineBaseJRBaseObjectFactory:(OrgOssPdfreporterEngineBaseJRBaseObjectFactory *)factory OBJC_METHOD_FAMILY_NONE;
- (void)copyColumnGroupsWithOrgOssPdfreporterCrosstabsJRCrosstab:(id<OrgOssPdfreporterCrosstabsJRCrosstab>)crosstab
              withOrgOssPdfreporterEngineBaseJRBaseObjectFactory:(OrgOssPdfreporterEngineBaseJRBaseObjectFactory *)factory OBJC_METHOD_FAMILY_NONE;
- (void)copyMeasuresWithOrgOssPdfreporterCrosstabsJRCrosstab:(id<OrgOssPdfreporterCrosstabsJRCrosstab>)crosstab
          withOrgOssPdfreporterEngineBaseJRBaseObjectFactory:(OrgOssPdfreporterEngineBaseJRBaseObjectFactory *)factory OBJC_METHOD_FAMILY_NONE;
- (void)copyCellsWithOrgOssPdfreporterCrosstabsJRCrosstab:(id<OrgOssPdfreporterCrosstabsJRCrosstab>)crosstab
       withOrgOssPdfreporterEngineBaseJRBaseObjectFactory:(OrgOssPdfreporterEngineBaseJRBaseObjectFactory *)factory OBJC_METHOD_FAMILY_NONE;
- (int)getId;
- (id<OrgOssPdfreporterCrosstabsJRCrosstabDataset>)getDataset;
- (IOSObjectArray *)getRowGroups;
- (IOSObjectArray *)getColumnGroups;
- (IOSObjectArray *)getMeasures;
- (void)collectExpressionsWithOrgOssPdfreporterEngineJRExpressionCollector:(OrgOssPdfreporterEngineJRExpressionCollector *)collector;
- (void)visitWithOrgOssPdfreporterEngineJRVisitor:(id<OrgOssPdfreporterEngineJRVisitor>)visitor;
- (int)getColumnBreakOffset;
- (BOOL)isRepeatColumnHeaders;
- (BOOL)isRepeatRowHeaders;
- (id<OrgOssPdfreporterCommonsArraysArray2D>)getCells;
- (IOSObjectArray *)getParameters;
- (id<OrgOssPdfreporterEngineJRExpression>)getParametersMapExpression;
- (id<OrgOssPdfreporterCrosstabsJRCellContents>)getWhenNoDataCell;
+ (id<OrgOssPdfreporterEngineJRElement>)getElementByKeyWithOrgOssPdfreporterCrosstabsJRCrosstab:(id<OrgOssPdfreporterCrosstabsJRCrosstab>)crosstab
                                                                                   withNSString:(NSString *)key;
+ (id<OrgOssPdfreporterEngineJRElement>)getHeadersElementWithOrgOssPdfreporterCrosstabsJRCrosstabGroupArray:(IOSObjectArray *)groups
                                                                                               withNSString:(NSString *)key;
- (id<OrgOssPdfreporterEngineJRElement>)getElementByKeyWithNSString:(NSString *)elementKey;
- (id<OrgOssPdfreporterCrosstabsJRCellContents>)getHeaderCell;
- (IOSObjectArray *)getVariables;
- (OrgOssPdfreporterEngineTypeRunDirectionEnumEnum *)getRunDirectionValue;
- (void)setRunDirectionWithOrgOssPdfreporterEngineTypeRunDirectionEnumEnum:(OrgOssPdfreporterEngineTypeRunDirectionEnumEnum *)runDirectionValue;
- (id)clone;
- (JavaLangBoolean *)getIgnoreWidth;
- (void)setIgnoreWidthWithJavaLangBoolean:(JavaLangBoolean *)ignoreWidth;
- (void)setIgnoreWidthWithBOOL:(BOOL)ignoreWidth;
- (id<OrgOssPdfreporterGeometryIColor>)getDefaultLineColor;
- (id<OrgOssPdfreporterEngineJRLineBox>)getLineBox;
@end

#endif // _OrgOssPdfreporterCrosstabsBaseJRBaseCrosstab_H_