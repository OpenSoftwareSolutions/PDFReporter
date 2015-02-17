//
//  Page.m
//  JasperReportiOS
//
//  Created by Fr3e on 5/27/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "Page.h"
#import "Image.h"
#import "ImageBox.h"
#import "FontBox.h"
#import "org/oss/pdfreporter/geometry/IAffineTransformMatrix.h"
#import "org/oss/pdfreporter/pdf/PdfFactory.h"
#import "Document.h"
#import "HpdfDocBox.h"
#import "org/oss/pdfreporter/registry/ApiRegistry.h"
#import "org/oss/pdfreporter/geometry/Color.h"
#import "IOSPrimitiveArray.h"

@implementation Page

- (id)initWithWidth:(int)width height:(int)height document:(OrgOssPdfreporterPdfDocument*)document{
    self = [super init];
    if(self) {
        hpdf_doc = [[HpdfDocBox GetDocBoxFromSession:[[OrgOssPdfreporterRegistryApiRegistry getPdfFactory] getSession]] getHpdfDoc];
        if (HPDF_HasDoc(hpdf_doc)) {
            hpdf_page = HPDF_AddPage(hpdf_doc);
            if(width>0 || height>0) {
                HPDF_Page_SetWidth(hpdf_page, width);
                HPDF_Page_SetHeight(hpdf_page, height);
            }
        }
        else @throw [NSException exceptionWithName:@"HPDF_NEW" reason:@"!has doc" userInfo:nil];
        
    }
    return self;
}

- (id)initWithWidth:(int)width height:(int)height orientation:(OrgOssPdfreporterPdfIDocument_PageOrientationEnum *)orientation document:(OrgOssPdfreporterPdfDocument*)document{
    self = [super init];
    if(self) {
        hpdf_doc = [[HpdfDocBox GetDocBoxFromSession:[[OrgOssPdfreporterRegistryApiRegistry getPdfFactory] getSession]] getHpdfDoc];
        if (HPDF_HasDoc(hpdf_doc)) {
            hpdf_page = HPDF_AddPage(hpdf_doc);
            int ori = HPDF_PAGE_PORTRAIT;
            if(orientation == OrgOssPdfreporterPdfIDocument_PageOrientationEnum_LANDSCAPE) {
                ori = HPDF_PAGE_LANDSCAPE;
                int tmp = width;
                width = height;
                height = tmp;
            }
            HPDF_Page_SetSize(hpdf_page, HPDF_PAGE_SIZE_A4, ori);
            HPDF_Page_SetWidth(hpdf_page, width);
            HPDF_Page_SetHeight(hpdf_page, height);
        } else @throw [NSException exceptionWithName:@"HPDF_NEW" reason:@"!has doc" userInfo:nil];
        
    }
    return self;
}

- (HPDF_Doc)getHPDF_Doc {
    return hpdf_doc;
}

- (void)setLineCapWithOrgOssPdfreporterPdfIPage_LineCapEnum:(OrgOssPdfreporterPdfIPage_LineCapEnum *)lineCap {
    HPDF_Page_SetLineCap(hpdf_page, lineCap.ordinal);
}

- (void)setLineJoinWithOrgOssPdfreporterPdfIPage_LineJoinEnum:(OrgOssPdfreporterPdfIPage_LineJoinEnum *)lineJoin {
    HPDF_Page_SetLineJoin(hpdf_page, lineJoin.ordinal);
}

- (void)setLineWidthWithFloat:(float)width {
    HPDF_Page_SetLineWidth(hpdf_page, width);
}

- (void)setRGBColorStrokeWithOrgOssPdfreporterGeometryIColor:(id<OrgOssPdfreporterGeometryIColor>)color {
    HPDF_Page_SetRGBStroke(hpdf_page, (HPDF_REAL)[color getRed]/255, (HPDF_REAL)[color getGreen]/255, (HPDF_REAL)[color getBlue]/255);
}

- (void)setRGBColorFillWithOrgOssPdfreporterGeometryIColor:(id<OrgOssPdfreporterGeometryIColor>)color {
    HPDF_Page_SetRGBFill(hpdf_page, (HPDF_REAL)[color getRed]/255, (HPDF_REAL)[color getGreen]/255, (HPDF_REAL)[color getBlue]/255);
}

- (void)setLineDashWithIntArray:(IOSIntArray *)array withInt:(int)phase {
    HPDF_UINT16 *shortArray = malloc(array.length * sizeof(HPDF_UINT16));
    
    for(int i=0; i<array.length; i++) {
        shortArray[i] = (HPDF_UINT16)[array intAtIndex:i];
    }
    
    HPDF_Page_SetDash(hpdf_page, shortArray, array.length, phase);
    
    free(shortArray);
}

- (void)stroke {
    HPDF_Page_Stroke(hpdf_page);
}

- (void)fill {
    HPDF_Page_Fill(hpdf_page);
}

- (void)fillStroke {
    HPDF_Page_FillStroke(hpdf_page);
}

- (void)moveToWithFloat:(float)x withFloat:(float)y {
    HPDF_Page_MoveTo(hpdf_page, x, y);
}

- (void)lineToWithFloat:(float)x withFloat:(float)y {
    HPDF_Page_LineTo(hpdf_page, x, y);
}

- (void)rectangleWithFloat:(float)x withFloat:(float)y withFloat:(float)width withFloat:(float)height {
    HPDF_Page_Rectangle(hpdf_page, x, y, width, height);
}

- (void)roundRectangleWithFloat:(float)x withFloat:(float)y withFloat:(float)width withFloat:(float)height withInt:(int)radius {
    if (!HPDF_HasDoc(hpdf_doc)) @throw [NSException exceptionWithName:@"HPDF_NEW" reason:@"!has doc" userInfo:nil];
    if (width < 0) {
        x += width;
        width = -width;
    }
    
    if (height < 0) {
        y += height;
        height = -height;
    }
    
    if (radius < 0)
        radius = -radius;
    
    float b = 0.4477f;
    HPDF_Page_MoveTo(hpdf_page, x + radius, y);
    HPDF_Page_LineTo(hpdf_page, x + width - radius , y);
    HPDF_Page_CurveTo(hpdf_page, x + width - radius * b, y, x + width, y + radius * b, x + width, y + radius);
    HPDF_Page_LineTo(hpdf_page, x + width, y + height - radius);
    HPDF_Page_CurveTo(hpdf_page, x + width, y + height - radius * b, x + width - radius * b, y + height, x + width - radius, y + height);
    HPDF_Page_LineTo(hpdf_page, x + radius, y + height);
    HPDF_Page_CurveTo(hpdf_page, x + radius * b, y + height, x, y + height - radius * b, x, y + height - radius);
    HPDF_Page_LineTo(hpdf_page, x, y + radius);
    HPDF_Page_CurveTo(hpdf_page, x, y + radius * b, x + radius * b, y, x + radius, y);
    
}

- (void)ellipseWithFloat:(float)x1 withFloat:(float)y1 withFloat:(float)x2 withFloat:(float)y2 {
    // rect to center
    float cx = x1+(x2-x1)/2;
    float cy = y1+(y2-y1)/2;
    float rx = (x2-x1)/2;
    float ry = (y2-y1)/2;
    HPDF_Page_Ellipse(hpdf_page, cx, cy, rx, ry);
}

- (void)transformWithOrgOssPdfreporterGeometryIAffineTransformMatrix:(id<OrgOssPdfreporterGeometryIAffineTransformMatrix>)matrix {
    HPDF_Page_GSave(hpdf_page);
    HPDF_Page_Concat(hpdf_page, [matrix getM00], [matrix getM10], [matrix getM01], [matrix getM11], [matrix getM02], [matrix getM12]);
}

- (void)restoreTransformation {
   HPDF_Page_GRestore(hpdf_page);
}

- (void)beginText {
    if (!HPDF_HasDoc(hpdf_doc)) @throw [NSException exceptionWithName:@"HPDF_NEW" reason:@"!has doc" userInfo:nil];
    HPDF_Page_BeginText(hpdf_page);
}

- (void)endText {
    HPDF_Page_EndText(hpdf_page);
}

- (void)setTextPosWithFloat:(float)x withFloat:(float)y {
    textPosX = x;
    textPosY = y;
}

- (void)textOutWithNSString:(NSString *)text {
    if (!HPDF_HasDoc(hpdf_doc)) @throw [NSException exceptionWithName:@"HPDF_NEW" reason:@"!has doc" userInfo:nil];
    const char *cText = [text UTF8String];
    HPDF_Page_TextOut(hpdf_page, textPosX, textPosY, cText);
}

- (void)setFontWithOrgOssPdfreporterFontIFont:(id<OrgOssPdfreporterFontIFont>)font {
    HPDF_Font hFont = [[font getPeer] getHpdfFont];
    HPDF_Page_SetFontAndSize(hpdf_page, hFont, [font getSize]);
}

- (void)drawWithOrgOssPdfreporterImageIImage:(id<OrgOssPdfreporterImageIImage>)image withFloat:(float)x withFloat:(float)y {
    if (!HPDF_HasDoc(hpdf_doc)) @throw [NSException exceptionWithName:@"HPDF_NEW" reason:@"!has doc" userInfo:nil];
    ImageBox *box = [image getPeer];
    HPDF_Page_DrawImage(hpdf_page, [box getHpdfImage], x, y, [image getWidth], [image getHeight]);
}

- (void)setWordSpacingWithFloat:(float)spacing {
    HPDF_Page_SetWordSpace(hpdf_page, spacing);
}

- (void)setCharacterSpacingWithFloat:(float)spacing {
    HPDF_Page_SetCharSpace(hpdf_page, spacing);
}

- (void)drawWithOrgOssPdfreporterImageIImage:(id<OrgOssPdfreporterImageIImage>)image withFloat:(float)x withFloat:(float)y withFloat:(float)width withFloat:(float)height withOrgOssPdfreporterPdfIPage_ScaleModeEnum:(OrgOssPdfreporterPdfIPage_ScaleModeEnum *)mode {
    if (!HPDF_HasDoc(hpdf_doc)) @throw [NSException exceptionWithName:@"HPDF_NEW" reason:@"!has doc" userInfo:nil];
    ImageBox *box = [image getPeer];
    if(mode == OrgOssPdfreporterPdfIPage_ScaleModeEnum_NONE) {
        [self drawCroppedWithOrgOssPdfreporterImageIImage:image withFloat:0 withFloat:0 withFloat:x withFloat:y withFloat:width withFloat:height];
    }
    else if(mode == OrgOssPdfreporterPdfIPage_ScaleModeEnum_SCALE) {
        HPDF_Page_DrawImage(hpdf_page, [box getHpdfImage], x, y, width, height);
    }
    else if(mode == OrgOssPdfreporterPdfIPage_ScaleModeEnum_SIZE) {
        float dWidth = (float)width / [image getWidth];
        float dHeight = (float)height / [image getHeight];
        float scale = dHeight;
        
        if(dWidth<dHeight) scale = dWidth;
        
        HPDF_Page_DrawImage(hpdf_page, [box getHpdfImage], x, y, (float)[image getWidth]*scale, (float)[image getHeight]*scale);
    
    }
}

- (void)drawCroppedWithOrgOssPdfreporterImageIImage:(id<OrgOssPdfreporterImageIImage>)image
                                       withFloat:(float)xoffset
                                       withFloat:(float)yoffset
                                       withFloat:(float)x
                                       withFloat:(float)y
                                       withFloat:(float)width
                                       withFloat:(float)height {
    if (!HPDF_HasDoc(hpdf_doc)) @throw [NSException exceptionWithName:@"HPDF_NEW" reason:@"!has doc" userInfo:nil];
    ImageBox *box = [image getPeer];
    HPDF_Page_GSave(hpdf_page);
    HPDF_Page_Rectangle(hpdf_page, x, y, width, height);
    HPDF_Page_Clip(hpdf_page);
    HPDF_Page_EndPath(hpdf_page);
    HPDF_Page_DrawImage(hpdf_page, [box getHpdfImage], x + xoffset, y-[image getHeight]+height - yoffset, [image getWidth], [image getHeight]);
    HPDF_Page_GRestore(hpdf_page);
    
}




@end
