//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/design/JRDesignGraphicElement.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineDesignJRDesignGraphicElement_H_
#define _OrgOssPdfreporterEngineDesignJRDesignGraphicElement_H_

@class JavaLangFloat;
@class OrgOssPdfreporterEngineJRExpressionCollector;
@class OrgOssPdfreporterEngineTypeFillEnumEnum;
@protocol OrgOssPdfreporterEngineJRDefaultStyleProvider;
@protocol OrgOssPdfreporterEngineJRPen;
@protocol OrgOssPdfreporterEngineJRVisitor;
@protocol OrgOssPdfreporterGeometryIColor;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/engine/JRGraphicElement.h"
#include "org/oss/pdfreporter/engine/design/JRDesignElement.h"

#define OrgOssPdfreporterEngineDesignJRDesignGraphicElement_serialVersionUID 10200

@interface OrgOssPdfreporterEngineDesignJRDesignGraphicElement : OrgOssPdfreporterEngineDesignJRDesignElement < OrgOssPdfreporterEngineJRGraphicElement > {
 @public
  id<OrgOssPdfreporterEngineJRPen> linePen_;
  OrgOssPdfreporterEngineTypeFillEnumEnum *fillValue_;
}

@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRPen> linePen;
@property (nonatomic, strong) OrgOssPdfreporterEngineTypeFillEnumEnum *fillValue;

- (id)initWithOrgOssPdfreporterEngineJRDefaultStyleProvider:(id<OrgOssPdfreporterEngineJRDefaultStyleProvider>)defaultStyleProvider;
- (id<OrgOssPdfreporterEngineJRPen>)getLinePen;
- (OrgOssPdfreporterEngineTypeFillEnumEnum *)getFillValue;
- (OrgOssPdfreporterEngineTypeFillEnumEnum *)getOwnFillValue;
- (void)setFillWithOrgOssPdfreporterEngineTypeFillEnumEnum:(OrgOssPdfreporterEngineTypeFillEnumEnum *)fillValue;
- (JavaLangFloat *)getDefaultLineWidth;
- (id<OrgOssPdfreporterGeometryIColor>)getDefaultLineColor;
- (id)clone;
- (void)collectExpressionsWithOrgOssPdfreporterEngineJRExpressionCollector:(OrgOssPdfreporterEngineJRExpressionCollector *)param0;
- (void)visitWithOrgOssPdfreporterEngineJRVisitor:(id<OrgOssPdfreporterEngineJRVisitor>)param0;
@end

#endif // _OrgOssPdfreporterEngineDesignJRDesignGraphicElement_H_