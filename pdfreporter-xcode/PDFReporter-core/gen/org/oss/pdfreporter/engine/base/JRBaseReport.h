//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/base/JRBaseReport.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineBaseJRBaseReport_H_
#define _OrgOssPdfreporterEngineBaseJRBaseReport_H_

@class IOSObjectArray;
@class OrgOssPdfreporterEngineBaseJRBaseObjectFactory;
@class OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport;
@class OrgOssPdfreporterEngineJRExpressionCollector;
@class OrgOssPdfreporterEngineJRPropertiesMap;
@class OrgOssPdfreporterEngineTypeOrientationEnumEnum;
@class OrgOssPdfreporterEngineTypePrintOrderEnumEnum;
@class OrgOssPdfreporterEngineTypeRunDirectionEnumEnum;
@class OrgOssPdfreporterEngineTypeWhenNoDataTypeEnumEnum;
@class OrgOssPdfreporterEngineTypeWhenResourceMissingTypeEnumEnum;
@class OrgOssPdfreporterUsesJavaUtilUUID;
@protocol JavaUtilList;
@protocol JavaUtilSet;
@protocol OrgOssPdfreporterEngineJRBand;
@protocol OrgOssPdfreporterEngineJRDataset;
@protocol OrgOssPdfreporterEngineJRPropertiesHolder;
@protocol OrgOssPdfreporterEngineJRQuery;
@protocol OrgOssPdfreporterEngineJRSection;
@protocol OrgOssPdfreporterEngineJRStyle;

#import "JreEmulation.h"
#include "java/io/Serializable.h"
#include "org/oss/pdfreporter/engine/JRReport.h"
#include "org/oss/pdfreporter/engine/design/events/JRChangeEventsSupport.h"

#define OrgOssPdfreporterEngineBaseJRBaseReport_serialVersionUID 10200

@interface OrgOssPdfreporterEngineBaseJRBaseReport : NSObject < OrgOssPdfreporterEngineJRReport, JavaIoSerializable, OrgOssPdfreporterEngineDesignEventsJRChangeEventsSupport > {
 @public
  NSString *name_;
  NSString *language_;
  int columnCount_;
  OrgOssPdfreporterEngineTypePrintOrderEnumEnum *printOrderValue_;
  OrgOssPdfreporterEngineTypeRunDirectionEnumEnum *columnDirection_;
  int pageWidth_;
  int pageHeight_;
  OrgOssPdfreporterEngineTypeOrientationEnumEnum *orientationValue_;
  OrgOssPdfreporterEngineTypeWhenNoDataTypeEnumEnum *whenNoDataTypeValue_;
  int columnWidth_;
  int columnSpacing_;
  int leftMargin_;
  int rightMargin_;
  int topMargin_;
  int bottomMargin_;
  BOOL isTitleNewPage__;
  BOOL isSummaryNewPage__;
  BOOL isSummaryWithPageHeaderAndFooter__;
  BOOL isFloatColumnFooter__;
  BOOL ignorePagination_;
  NSString *formatFactoryClass_;
  id<JavaUtilSet> importsSet_;
  IOSObjectArray *templates_;
  id<OrgOssPdfreporterEngineJRStyle> defaultStyle_;
  IOSObjectArray *styles_;
  id<OrgOssPdfreporterEngineJRDataset> mainDataset_;
  IOSObjectArray *datasets_;
  id<OrgOssPdfreporterEngineJRBand> background_;
  id<OrgOssPdfreporterEngineJRBand> title_;
  id<OrgOssPdfreporterEngineJRBand> pageHeader_;
  id<OrgOssPdfreporterEngineJRBand> columnHeader_;
  id<OrgOssPdfreporterEngineJRSection> detailSection_;
  id<OrgOssPdfreporterEngineJRBand> columnFooter_;
  id<OrgOssPdfreporterEngineJRBand> pageFooter_;
  id<OrgOssPdfreporterEngineJRBand> lastPageFooter_;
  id<OrgOssPdfreporterEngineJRBand> summary_;
  id<OrgOssPdfreporterEngineJRBand> noData_;
  OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport *eventSupport_;
}

@property (nonatomic, copy) NSString *name;
@property (nonatomic, copy) NSString *language;
@property (nonatomic, assign) int columnCount;
@property (nonatomic, strong) OrgOssPdfreporterEngineTypePrintOrderEnumEnum *printOrderValue;
@property (nonatomic, strong) OrgOssPdfreporterEngineTypeRunDirectionEnumEnum *columnDirection;
@property (nonatomic, assign) int pageWidth;
@property (nonatomic, assign) int pageHeight;
@property (nonatomic, strong) OrgOssPdfreporterEngineTypeOrientationEnumEnum *orientationValue;
@property (nonatomic, strong) OrgOssPdfreporterEngineTypeWhenNoDataTypeEnumEnum *whenNoDataTypeValue;
@property (nonatomic, assign) int columnWidth;
@property (nonatomic, assign) int columnSpacing;
@property (nonatomic, assign) int leftMargin;
@property (nonatomic, assign) int rightMargin;
@property (nonatomic, assign) int topMargin;
@property (nonatomic, assign) int bottomMargin;
@property (nonatomic, assign) BOOL isTitleNewPage_;
@property (nonatomic, assign) BOOL isSummaryNewPage_;
@property (nonatomic, assign) BOOL isSummaryWithPageHeaderAndFooter_;
@property (nonatomic, assign) BOOL isFloatColumnFooter_;
@property (nonatomic, assign) BOOL ignorePagination;
@property (nonatomic, copy) NSString *formatFactoryClass;
@property (nonatomic, strong) id<JavaUtilSet> importsSet;
@property (nonatomic, strong) IOSObjectArray *templates;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRStyle> defaultStyle;
@property (nonatomic, strong) IOSObjectArray *styles;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRDataset> mainDataset;
@property (nonatomic, strong) IOSObjectArray *datasets;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRBand> background;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRBand> title;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRBand> pageHeader;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRBand> columnHeader;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRSection> detailSection;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRBand> columnFooter;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRBand> pageFooter;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRBand> lastPageFooter;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRBand> summary;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRBand> noData;
@property (nonatomic, strong) OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport *eventSupport;

+ (NSString *)PROPERTY_WHEN_NO_DATA_TYPE;
- (id)init;
- (id)initWithOrgOssPdfreporterEngineJRReport:(id<OrgOssPdfreporterEngineJRReport>)report
withOrgOssPdfreporterEngineJRExpressionCollector:(OrgOssPdfreporterEngineJRExpressionCollector *)expressionCollector;
- (id)initWithOrgOssPdfreporterEngineJRReport:(id<OrgOssPdfreporterEngineJRReport>)report
withOrgOssPdfreporterEngineBaseJRBaseObjectFactory:(OrgOssPdfreporterEngineBaseJRBaseObjectFactory *)factory;
- (void)copyTemplatesWithOrgOssPdfreporterEngineJRReport:(id<OrgOssPdfreporterEngineJRReport>)report
      withOrgOssPdfreporterEngineBaseJRBaseObjectFactory:(OrgOssPdfreporterEngineBaseJRBaseObjectFactory *)factory OBJC_METHOD_FAMILY_NONE;
- (id)initWithOrgOssPdfreporterEngineJRReport:(id<OrgOssPdfreporterEngineJRReport>)report;
- (NSString *)getName;
- (NSString *)getLanguage;
- (int)getColumnCount;
- (OrgOssPdfreporterEngineTypePrintOrderEnumEnum *)getPrintOrderValue;
- (OrgOssPdfreporterEngineTypeRunDirectionEnumEnum *)getColumnDirection;
- (int)getPageWidth;
- (int)getPageHeight;
- (OrgOssPdfreporterEngineTypeOrientationEnumEnum *)getOrientationValue;
- (OrgOssPdfreporterEngineTypeWhenNoDataTypeEnumEnum *)getWhenNoDataTypeValue;
- (void)setWhenNoDataTypeWithOrgOssPdfreporterEngineTypeWhenNoDataTypeEnumEnum:(OrgOssPdfreporterEngineTypeWhenNoDataTypeEnumEnum *)whenNoDataTypeValue;
- (int)getColumnWidth;
- (int)getColumnSpacing;
- (int)getLeftMargin;
- (int)getRightMargin;
- (int)getTopMargin;
- (int)getBottomMargin;
- (BOOL)isTitleNewPage;
- (BOOL)isSummaryNewPage;
- (BOOL)isSummaryWithPageHeaderAndFooter;
- (BOOL)isFloatColumnFooter;
- (NSString *)getScriptletClass;
- (NSString *)getFormatFactoryClass;
- (NSString *)getResourceBundle;
- (IOSObjectArray *)getPropertyNames;
- (NSString *)getPropertyWithNSString:(NSString *)propName;
- (void)setPropertyWithNSString:(NSString *)propName
                   withNSString:(NSString *)value;
- (void)removePropertyWithNSString:(NSString *)propName;
- (IOSObjectArray *)getImports;
- (id<OrgOssPdfreporterEngineJRStyle>)getDefaultStyle;
- (IOSObjectArray *)getStyles;
- (IOSObjectArray *)getScriptlets;
- (IOSObjectArray *)getParameters;
- (id<OrgOssPdfreporterEngineJRQuery>)getQuery;
- (IOSObjectArray *)getFields;
- (IOSObjectArray *)getSortFields;
- (IOSObjectArray *)getVariables;
- (IOSObjectArray *)getGroups;
- (id<OrgOssPdfreporterEngineJRBand>)getBackground;
- (id<OrgOssPdfreporterEngineJRBand>)getTitle;
- (id<OrgOssPdfreporterEngineJRBand>)getPageHeader;
- (id<OrgOssPdfreporterEngineJRBand>)getColumnHeader;
- (id<OrgOssPdfreporterEngineJRSection>)getDetailSection;
- (id<OrgOssPdfreporterEngineJRBand>)getColumnFooter;
- (id<OrgOssPdfreporterEngineJRBand>)getPageFooter;
- (id<OrgOssPdfreporterEngineJRBand>)getLastPageFooter;
- (id<OrgOssPdfreporterEngineJRBand>)getSummary;
- (OrgOssPdfreporterEngineTypeWhenResourceMissingTypeEnumEnum *)getWhenResourceMissingTypeValue;
- (void)setWhenResourceMissingTypeWithOrgOssPdfreporterEngineTypeWhenResourceMissingTypeEnumEnum:(OrgOssPdfreporterEngineTypeWhenResourceMissingTypeEnumEnum *)whenResourceMissingType;
- (id<OrgOssPdfreporterEngineJRDataset>)getMainDataset;
- (IOSObjectArray *)getDatasets;
- (BOOL)isIgnorePagination;
- (BOOL)hasProperties;
- (OrgOssPdfreporterEngineJRPropertiesMap *)getPropertiesMap;
- (id<OrgOssPdfreporterEngineJRPropertiesHolder>)getParentProperties;
- (IOSObjectArray *)getTemplates;
- (id<OrgOssPdfreporterEngineJRBand>)getNoData;
- (IOSObjectArray *)getAllBands;
- (void)addBandWithOrgOssPdfreporterEngineJRBand:(id<OrgOssPdfreporterEngineJRBand>)band
                                withJavaUtilList:(id<JavaUtilList>)bands;
- (void)addBandsWithOrgOssPdfreporterEngineJRSection:(id<OrgOssPdfreporterEngineJRSection>)section
                                    withJavaUtilList:(id<JavaUtilList>)bands;
- (OrgOssPdfreporterUsesJavaUtilUUID *)getUUID;
- (OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport *)getEventSupport;
@end

#endif // _OrgOssPdfreporterEngineBaseJRBaseReport_H_