//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/crosstabs/base/JRBaseCellContents.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterCrosstabsBaseJRBaseCellContents_H_
#define _OrgOssPdfreporterCrosstabsBaseJRBaseCellContents_H_

@class OrgOssPdfreporterEngineBaseJRBaseObjectFactory;
@class OrgOssPdfreporterEngineJRPropertiesMap;
@class OrgOssPdfreporterEngineTypeModeEnumEnum;
@protocol OrgOssPdfreporterEngineJRDefaultStyleProvider;
@protocol OrgOssPdfreporterEngineJRLineBox;
@protocol OrgOssPdfreporterEngineJRPropertiesHolder;
@protocol OrgOssPdfreporterEngineJRStyle;
@protocol OrgOssPdfreporterGeometryIColor;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/crosstabs/JRCellContents.h"
#include "org/oss/pdfreporter/engine/base/JRBaseElementGroup.h"

#define OrgOssPdfreporterCrosstabsBaseJRBaseCellContents_serialVersionUID 10200

@interface OrgOssPdfreporterCrosstabsBaseJRBaseCellContents : OrgOssPdfreporterEngineBaseJRBaseElementGroup < OrgOssPdfreporterCrosstabsJRCellContents > {
 @public
  id<OrgOssPdfreporterEngineJRDefaultStyleProvider> defaultStyleProvider_;
  id<OrgOssPdfreporterEngineJRStyle> style_;
  NSString *styleNameReference_;
  OrgOssPdfreporterEngineTypeModeEnumEnum *modeValue_;
  id<OrgOssPdfreporterGeometryIColor> backcolor_;
  id<OrgOssPdfreporterEngineJRLineBox> lineBox_;
  int width_;
  int height_;
  OrgOssPdfreporterEngineJRPropertiesMap *propertiesMap_;
}

@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRDefaultStyleProvider> defaultStyleProvider;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRStyle> style;
@property (nonatomic, copy) NSString *styleNameReference;
@property (nonatomic, strong) OrgOssPdfreporterEngineTypeModeEnumEnum *modeValue;
@property (nonatomic, strong) id<OrgOssPdfreporterGeometryIColor> backcolor;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRLineBox> lineBox;
@property (nonatomic, assign) int width;
@property (nonatomic, assign) int height;
@property (nonatomic, strong) OrgOssPdfreporterEngineJRPropertiesMap *propertiesMap;

- (id)initWithOrgOssPdfreporterCrosstabsJRCellContents:(id<OrgOssPdfreporterCrosstabsJRCellContents>)cell
    withOrgOssPdfreporterEngineBaseJRBaseObjectFactory:(OrgOssPdfreporterEngineBaseJRBaseObjectFactory *)factory;
- (id<OrgOssPdfreporterGeometryIColor>)getBackcolor;
- (id<OrgOssPdfreporterEngineJRLineBox>)getLineBox;
- (int)getWidth;
- (int)getHeight;
- (id<OrgOssPdfreporterEngineJRDefaultStyleProvider>)getDefaultStyleProvider;
- (id<OrgOssPdfreporterEngineJRStyle>)getStyle;
- (OrgOssPdfreporterEngineTypeModeEnumEnum *)getModeValue;
- (NSString *)getStyleNameReference;
- (id<OrgOssPdfreporterGeometryIColor>)getDefaultLineColor;
- (id)clone;
- (BOOL)hasProperties;
- (OrgOssPdfreporterEngineJRPropertiesMap *)getPropertiesMap;
- (id<OrgOssPdfreporterEngineJRPropertiesHolder>)getParentProperties;
@end

#endif // _OrgOssPdfreporterCrosstabsBaseJRBaseCellContents_H_