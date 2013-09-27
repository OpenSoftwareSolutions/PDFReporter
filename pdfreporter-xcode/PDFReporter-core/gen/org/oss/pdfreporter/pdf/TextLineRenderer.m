//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/pdf/TextLineRenderer.java
//
//  Created by kendra on 9/27/13.
//

#include "java/util/Iterator.h"
#include "org/oss/pdfreporter/font/IFont.h"
#include "org/oss/pdfreporter/font/text/ITextLayout.h"
#include "org/oss/pdfreporter/geometry/IColor.h"
#include "org/oss/pdfreporter/pdf/IPage.h"
#include "org/oss/pdfreporter/pdf/TextLineRenderer.h"
#include "org/oss/pdfreporter/text/IPositionedLine.h"
#include "org/oss/pdfreporter/text/Paragraph.h"
#include "org/oss/pdfreporter/text/ParagraphText.h"

@implementation OrgOssPdfreporterPdfTextLineRenderer

+ (void)renderWithOrgOssPdfreporterFontTextITextLayout:(id<OrgOssPdfreporterFontTextITextLayout>)layout
                         withOrgOssPdfreporterPdfIPage:(id<OrgOssPdfreporterPdfIPage>)page
                                             withFloat:(float)x
                                             withFloat:(float)y {
  {
    id<JavaUtilIterator> iter__ = [((OrgOssPdfreporterTextParagraph *) nil_chk([((id<OrgOssPdfreporterFontTextITextLayout>) nil_chk(layout)) getParagraph])) iterator];
    while ([((id<JavaUtilIterator>) nil_chk(iter__)) hasNext]) {
      OrgOssPdfreporterTextParagraphText *text = [((id<JavaUtilIterator>) nil_chk(iter__)) next];
      [OrgOssPdfreporterPdfTextLineRenderer renderBackgroundWithOrgOssPdfreporterPdfIPage:page withFloat:y withFloat:x withOrgOssPdfreporterTextParagraphText:text];
      [((id<OrgOssPdfreporterPdfIPage>) nil_chk(page)) beginText];
      [((id<OrgOssPdfreporterPdfIPage>) nil_chk(page)) setRGBColorFillWithOrgOssPdfreporterGeometryIColor:[((OrgOssPdfreporterTextParagraphText *) nil_chk(text)) getForeground]];
      [((id<OrgOssPdfreporterPdfIPage>) nil_chk(page)) setFontWithOrgOssPdfreporterFontIFont:[((OrgOssPdfreporterTextParagraphText *) nil_chk(text)) getFont]];
      [((id<OrgOssPdfreporterPdfIPage>) nil_chk(page)) setTextPosWithFloat:x withFloat:y];
      [((id<OrgOssPdfreporterPdfIPage>) nil_chk(page)) textOutWithNSString:[((OrgOssPdfreporterTextParagraphText *) nil_chk(text)) getText]];
      [((id<OrgOssPdfreporterPdfIPage>) nil_chk(page)) endText];
      [OrgOssPdfreporterPdfTextLineRenderer renderLineWithOrgOssPdfreporterPdfIPage:page withFloat:y withFloat:x withOrgOssPdfreporterTextParagraphText:text];
      x += [((OrgOssPdfreporterTextParagraphText *) nil_chk(text)) getWidth];
    }
  }
}

+ (void)renderLineWithOrgOssPdfreporterPdfIPage:(id<OrgOssPdfreporterPdfIPage>)page
                                      withFloat:(float)y
                                      withFloat:(float)x
         withOrgOssPdfreporterTextParagraphText:(OrgOssPdfreporterTextParagraphText *)text {
  id<OrgOssPdfreporterTextIPositionedLine> line = [((OrgOssPdfreporterTextParagraphText *) nil_chk(text)) getLine];
  if (line != nil) {
    [((id<OrgOssPdfreporterPdfIPage>) nil_chk(page)) setLineWidthWithFloat:[line getThikness]];
    [((id<OrgOssPdfreporterPdfIPage>) nil_chk(page)) setRGBColorStrokeWithOrgOssPdfreporterGeometryIColor:[((OrgOssPdfreporterTextParagraphText *) nil_chk(text)) getForeground]];
    [((id<OrgOssPdfreporterPdfIPage>) nil_chk(page)) moveToWithFloat:x withFloat:y + [line getPosition]];
    [((id<OrgOssPdfreporterPdfIPage>) nil_chk(page)) lineToWithFloat:x + [((OrgOssPdfreporterTextParagraphText *) nil_chk(text)) getWidth] withFloat:y + [line getPosition]];
    [((id<OrgOssPdfreporterPdfIPage>) nil_chk(page)) stroke];
  }
}

+ (void)renderBackgroundWithOrgOssPdfreporterPdfIPage:(id<OrgOssPdfreporterPdfIPage>)page
                                            withFloat:(float)y
                                            withFloat:(float)x
               withOrgOssPdfreporterTextParagraphText:(OrgOssPdfreporterTextParagraphText *)text {
  id<OrgOssPdfreporterGeometryIColor> background = [((OrgOssPdfreporterTextParagraphText *) nil_chk(text)) getBackground];
  if (background != nil) {
    [((id<OrgOssPdfreporterPdfIPage>) nil_chk(page)) setRGBColorFillWithOrgOssPdfreporterGeometryIColor:background];
    [((id<OrgOssPdfreporterPdfIPage>) nil_chk(page)) setRGBColorStrokeWithOrgOssPdfreporterGeometryIColor:background];
    float fontSize = [((id<OrgOssPdfreporterFontIFont>) nil_chk([((OrgOssPdfreporterTextParagraphText *) nil_chk(text)) getFont])) getSize];
    [((id<OrgOssPdfreporterPdfIPage>) nil_chk(page)) rectangleWithFloat:x withFloat:y - 0.25f * fontSize + 0.5f withFloat:[((OrgOssPdfreporterTextParagraphText *) nil_chk(text)) getWidth] withFloat:fontSize];
    [((id<OrgOssPdfreporterPdfIPage>) nil_chk(page)) fillStroke];
  }
}

- (id)init {
  return [super init];
}

@end