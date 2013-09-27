//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/base/JRVirtualPrintPage.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineBaseJRVirtualPrintPage_H_
#define _OrgOssPdfreporterEngineBaseJRVirtualPrintPage_H_

@class IOSByteArray;
@class OrgOssPdfreporterEngineBaseVirtualizableElementList;
@class OrgOssPdfreporterEngineFillJRVirtualizationContext;
@class OrgOssPdfreporterEngineJasperPrint;
@class OrgOssPdfreporterEngineTypeImageTypeEnumEnum;
@class OrgOssPdfreporterEngineTypeRenderableTypeEnumEnum;
@protocol JavaUtilList;
@protocol OrgOssPdfreporterEngineJRPrintElement;
@protocol OrgOssPdfreporterEngineJRVirtualizer;
@protocol OrgOssPdfreporterEngineJasperReportsContext;
@protocol OrgOssPdfreporterGeometryIDimension;
@protocol OrgOssPdfreporterGeometryIRectangle;
@protocol OrgOssPdfreporterImageIImage;
@protocol OrgOssPdfreporterPdfIPage;

#import "JreEmulation.h"
#include "java/io/Serializable.h"
#include "org/oss/pdfreporter/engine/JRPrintPage.h"
#include "org/oss/pdfreporter/engine/Renderable.h"
#include "org/oss/pdfreporter/engine/fill/JRTemplateElement.h"

#define OrgOssPdfreporterEngineBaseJRVirtualPrintPage_serialVersionUID 10200

@interface OrgOssPdfreporterEngineBaseJRVirtualPrintPage : NSObject < OrgOssPdfreporterEngineJRPrintPage, JavaIoSerializable > {
 @public
  OrgOssPdfreporterEngineBaseVirtualizableElementList *elements_;
}

@property (nonatomic, strong) OrgOssPdfreporterEngineBaseVirtualizableElementList *elements;

+ (NSString *)PROPERTY_VIRTUAL_PAGE_ELEMENT_SIZE;
- (id)initWithOrgOssPdfreporterEngineJasperPrint:(OrgOssPdfreporterEngineJasperPrint *)printObject
        withOrgOssPdfreporterEngineJRVirtualizer:(id<OrgOssPdfreporterEngineJRVirtualizer>)virtualizer
withOrgOssPdfreporterEngineFillJRVirtualizationContext:(OrgOssPdfreporterEngineFillJRVirtualizationContext *)virtualizationContext;
- (id)initWithOrgOssPdfreporterEngineJasperPrint:(OrgOssPdfreporterEngineJasperPrint *)printObject
withOrgOssPdfreporterEngineFillJRVirtualizationContext:(OrgOssPdfreporterEngineFillJRVirtualizationContext *)virtualizationContext;
- (id<JavaUtilList>)getElements;
- (void)setElementsWithJavaUtilList:(id<JavaUtilList>)elements;
- (void)addElementWithOrgOssPdfreporterEngineJRPrintElement:(id<OrgOssPdfreporterEngineJRPrintElement>)element;
- (void)dispose;
@end

#define OrgOssPdfreporterEngineBaseJRVirtualPrintPage_JRIdHolderRenderer_serialVersionUID 10200

@interface OrgOssPdfreporterEngineBaseJRVirtualPrintPage_JRIdHolderRenderer : NSObject < OrgOssPdfreporterEngineRenderable > {
 @public
  NSString *id__;
}

@property (nonatomic, copy) NSString *id_;

- (id)initWithOrgOssPdfreporterEngineRenderable:(id<OrgOssPdfreporterEngineRenderable>)renderer;
- (NSString *)getId;
- (char)getType;
- (char)getImageType;
- (id<OrgOssPdfreporterGeometryIDimension>)getDimension;
- (IOSByteArray *)getImageData;
- (void)renderWithOrgOssPdfreporterPdfIPage:(id<OrgOssPdfreporterPdfIPage>)page
    withOrgOssPdfreporterGeometryIRectangle:(id<OrgOssPdfreporterGeometryIRectangle>)rectanle;
- (OrgOssPdfreporterEngineTypeRenderableTypeEnumEnum *)getTypeValue;
- (OrgOssPdfreporterEngineTypeImageTypeEnumEnum *)getImageTypeValue;
- (id<OrgOssPdfreporterGeometryIDimension>)getDimensionWithOrgOssPdfreporterEngineJasperReportsContext:(id<OrgOssPdfreporterEngineJasperReportsContext>)jasperReportsContext;
- (id<OrgOssPdfreporterImageIImage>)getImageWithOrgOssPdfreporterEngineJasperReportsContext:(id<OrgOssPdfreporterEngineJasperReportsContext>)jasperReportsContext;
- (void)renderWithOrgOssPdfreporterEngineJasperReportsContext:(id<OrgOssPdfreporterEngineJasperReportsContext>)jasperReportsContext
                                withOrgOssPdfreporterPdfIPage:(id<OrgOssPdfreporterPdfIPage>)page
                      withOrgOssPdfreporterGeometryIRectangle:(id<OrgOssPdfreporterGeometryIRectangle>)rectanle;
@end

#define OrgOssPdfreporterEngineBaseJRVirtualPrintPage_JRIdHolderTemplateElement_serialVersionUID 10200

@interface OrgOssPdfreporterEngineBaseJRVirtualPrintPage_JRIdHolderTemplateElement : OrgOssPdfreporterEngineFillJRTemplateElement {
}

- (id)initWithNSString:(NSString *)id_;
- (int)getHashCode;
- (BOOL)isIdenticalWithId:(id)object;
@end

#endif // _OrgOssPdfreporterEngineBaseJRVirtualPrintPage_H_