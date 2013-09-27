//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/fill/JRTemplatePrintFrame.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineFillJRTemplatePrintFrame_H_
#define _OrgOssPdfreporterEngineFillJRTemplatePrintFrame_H_

@class OrgOssPdfreporterEngineFillJRTemplateFrame;
@protocol JavaUtilCollection;
@protocol JavaUtilList;
@protocol OrgOssPdfreporterEngineJRLineBox;
@protocol OrgOssPdfreporterEngineJRPrintElement;
@protocol OrgOssPdfreporterEnginePrintElementVisitor;
@protocol OrgOssPdfreporterGeometryIColor;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/engine/JRPrintElementContainer.h"
#include "org/oss/pdfreporter/engine/JRPrintFrame.h"
#include "org/oss/pdfreporter/engine/fill/JRTemplatePrintElement.h"

#define OrgOssPdfreporterEngineFillJRTemplatePrintFrame_serialVersionUID 10200

@interface OrgOssPdfreporterEngineFillJRTemplatePrintFrame : OrgOssPdfreporterEngineFillJRTemplatePrintElement < OrgOssPdfreporterEngineJRPrintFrame, OrgOssPdfreporterEngineJRPrintElementContainer > {
 @public
  id<JavaUtilList> elements_;
}

@property (nonatomic, strong) id<JavaUtilList> elements;

- (id)initWithOrgOssPdfreporterEngineFillJRTemplateFrame:(OrgOssPdfreporterEngineFillJRTemplateFrame *)templateFrame;
- (id)initWithOrgOssPdfreporterEngineFillJRTemplateFrame:(OrgOssPdfreporterEngineFillJRTemplateFrame *)templateFrame
                                                 withInt:(int)sourceElementId;
- (id<JavaUtilList>)getElements;
- (void)addElementWithOrgOssPdfreporterEngineJRPrintElement:(id<OrgOssPdfreporterEngineJRPrintElement>)element;
- (void)addElementsWithJavaUtilCollection:(id<JavaUtilCollection>)elements;
- (id<OrgOssPdfreporterEngineJRLineBox>)getLineBox;
- (id<OrgOssPdfreporterGeometryIColor>)getDefaultLineColor;
- (void)acceptWithOrgOssPdfreporterEnginePrintElementVisitor:(id<OrgOssPdfreporterEnginePrintElementVisitor>)visitor
                                                      withId:(id)arg;
@end

#endif // _OrgOssPdfreporterEngineFillJRTemplatePrintFrame_H_