//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/base/JRBasePrintText.java
//
//  Created by kendra on 9/27/13.
//

#include "IOSShortArray.h"
#include "java/lang/Boolean.h"
#include "java/lang/Integer.h"
#include "java/util/Locale.h"
#include "java/util/Map.h"
#include "org/oss/pdfreporter/engine/DefaultJasperReportsContext.h"
#include "org/oss/pdfreporter/engine/JRAnchor.h"
#include "org/oss/pdfreporter/engine/JRCommonText.h"
#include "org/oss/pdfreporter/engine/JRDefaultStyleProvider.h"
#include "org/oss/pdfreporter/engine/JRFont.h"
#include "org/oss/pdfreporter/engine/JRHyperlinkHelper.h"
#include "org/oss/pdfreporter/engine/JRLineBox.h"
#include "org/oss/pdfreporter/engine/JRParagraph.h"
#include "org/oss/pdfreporter/engine/JRPrintHyperlinkParameter.h"
#include "org/oss/pdfreporter/engine/JRPrintHyperlinkParameters.h"
#include "org/oss/pdfreporter/engine/JRStyledTextAttributeSelector.h"
#include "org/oss/pdfreporter/engine/PrintElementVisitor.h"
#include "org/oss/pdfreporter/engine/base/JRBaseLineBox.h"
#include "org/oss/pdfreporter/engine/base/JRBaseParagraph.h"
#include "org/oss/pdfreporter/engine/base/JRBasePrintElement.h"
#include "org/oss/pdfreporter/engine/base/JRBasePrintText.h"
#include "org/oss/pdfreporter/engine/fill/TextFormat.h"
#include "org/oss/pdfreporter/engine/type/HorizontalAlignEnum.h"
#include "org/oss/pdfreporter/engine/type/HyperlinkTargetEnum.h"
#include "org/oss/pdfreporter/engine/type/HyperlinkTypeEnum.h"
#include "org/oss/pdfreporter/engine/type/LineSpacingEnum.h"
#include "org/oss/pdfreporter/engine/type/ModeEnum.h"
#include "org/oss/pdfreporter/engine/type/RotationEnum.h"
#include "org/oss/pdfreporter/engine/type/RunDirectionEnum.h"
#include "org/oss/pdfreporter/engine/type/VerticalAlignEnum.h"
#include "org/oss/pdfreporter/engine/util/JRStyleResolver.h"
#include "org/oss/pdfreporter/engine/util/JRStyledText.h"
#include "org/oss/pdfreporter/engine/util/JRStyledTextParser.h"
#include "org/oss/pdfreporter/engine/util/JRStyledTextUtil.h"
#include "org/oss/pdfreporter/geometry/IColor.h"

@implementation OrgOssPdfreporterEngineBaseJRBasePrintText

@synthesize text = text_;
@synthesize textTruncateIndex = textTruncateIndex_;
@synthesize lineBreakOffsets = lineBreakOffsets_;
@synthesize textTruncateSuffix = textTruncateSuffix_;
@synthesize value = value_;
@synthesize lineSpacingFactor = lineSpacingFactor_;
@synthesize leadingOffset = leadingOffset_;
@synthesize horizontalAlignmentValue = horizontalAlignmentValue_;
@synthesize verticalAlignmentValue = verticalAlignmentValue_;
@synthesize rotationValue = rotationValue_;
@synthesize runDirectionValue = runDirectionValue_;
@synthesize textHeight = textHeight_;
@synthesize markup = markup_;
@synthesize textFormat = textFormat_;
@synthesize anchorName = anchorName_;
@synthesize linkType = linkType_;
@synthesize linkTarget = linkTarget_;
@synthesize hyperlinkReference = hyperlinkReference_;
@synthesize hyperlinkAnchor = hyperlinkAnchor_;
@synthesize hyperlinkPage = hyperlinkPage_;
@synthesize hyperlinkTooltip = hyperlinkTooltip_;
@synthesize hyperlinkParameters = hyperlinkParameters_;
@synthesize bookmarkLevel = bookmarkLevel_;
@synthesize lineBox = lineBox_;
@synthesize paragraph = paragraph_;
@synthesize fontName = fontName_;
@synthesize isBold_ = isBold__;
@synthesize isItalic_ = isItalic__;
@synthesize isUnderline_ = isUnderline__;
@synthesize isStrikeThrough_ = isStrikeThrough__;
@synthesize fontSize = fontSize_;
@synthesize pdfFontName = pdfFontName_;
@synthesize pdfEncoding = pdfEncoding_;
@synthesize isPdfEmbedded_ = isPdfEmbedded__;
@synthesize valueClassName = valueClassName_;
@synthesize pattern = pattern_;
@synthesize formatFactoryClass = formatFactoryClass_;
@synthesize localeCode = localeCode_;
@synthesize timeZoneId = timeZoneId_;

- (id)initWithOrgOssPdfreporterEngineJRDefaultStyleProvider:(id<OrgOssPdfreporterEngineJRDefaultStyleProvider>)defaultStyleProvider {
  if ((self = [super initWithOrgOssPdfreporterEngineJRDefaultStyleProvider:defaultStyleProvider])) {
    text_ = @"";
    runDirectionValue_ = [OrgOssPdfreporterEngineTypeRunDirectionEnumEnum LTR];
    bookmarkLevel_ = OrgOssPdfreporterEngineJRAnchor_NO_BOOKMARK;
    lineBox_ = [[OrgOssPdfreporterEngineBaseJRBaseLineBox alloc] initWithOrgOssPdfreporterEngineJRBoxContainer:self];
    paragraph_ = [[OrgOssPdfreporterEngineBaseJRBaseParagraph alloc] initWithOrgOssPdfreporterEngineJRParagraphContainer:self];
  }
  return self;
}

- (OrgOssPdfreporterEngineTypeModeEnumEnum *)getModeValue {
  return [OrgOssPdfreporterEngineUtilJRStyleResolver getModeWithOrgOssPdfreporterEngineJRCommonElement:self withOrgOssPdfreporterEngineTypeModeEnumEnum:[OrgOssPdfreporterEngineTypeModeEnumEnum TRANSPARENT]];
}

- (NSString *)getText {
  return [((OrgOssPdfreporterEngineUtilJRStyledTextUtil *) nil_chk([OrgOssPdfreporterEngineUtilJRStyledTextUtil getInstanceWithOrgOssPdfreporterEngineJasperReportsContext:[OrgOssPdfreporterEngineDefaultJasperReportsContext getInstance]])) getTruncatedTextWithOrgOssPdfreporterEngineJRPrintText:self];
}

- (void)setTextWithNSString:(NSString *)text {
  self.text = text;
}

- (JavaLangInteger *)getTextTruncateIndex {
  return textTruncateIndex_;
}

- (void)setTextTruncateIndexWithJavaLangInteger:(JavaLangInteger *)textTruncateIndex {
  self.textTruncateIndex = textTruncateIndex;
}

- (NSString *)getTextTruncateSuffix {
  return textTruncateSuffix_;
}

- (void)setTextTruncateSuffixWithNSString:(NSString *)textTruncateSuffix {
  self.textTruncateSuffix = textTruncateSuffix;
}

- (IOSShortArray *)getLineBreakOffsets {
  return lineBreakOffsets_;
}

- (void)setLineBreakOffsetsWithShortArray:(IOSShortArray *)lineBreakOffsets {
  self.lineBreakOffsets = lineBreakOffsets;
}

- (NSString *)getFullText {
  NSString *fullText = self.text;
  if (textTruncateIndex_ == nil && textTruncateSuffix_ != nil) {
    fullText = [NSString stringWithFormat:@"%@%@", fullText, textTruncateSuffix_];
  }
  return fullText;
}

- (NSString *)getOriginalText {
  return text_;
}

- (OrgOssPdfreporterEngineUtilJRStyledText *)getStyledTextWithOrgOssPdfreporterEngineJRStyledTextAttributeSelector:(OrgOssPdfreporterEngineJRStyledTextAttributeSelector *)attributeSelector {
  return [((OrgOssPdfreporterEngineUtilJRStyledTextUtil *) nil_chk([OrgOssPdfreporterEngineUtilJRStyledTextUtil getInstanceWithOrgOssPdfreporterEngineJasperReportsContext:[OrgOssPdfreporterEngineDefaultJasperReportsContext getInstance]])) getStyledTextWithOrgOssPdfreporterEngineJRPrintText:self withOrgOssPdfreporterEngineJRStyledTextAttributeSelector:attributeSelector];
}

- (OrgOssPdfreporterEngineUtilJRStyledText *)getFullStyledTextWithOrgOssPdfreporterEngineJRStyledTextAttributeSelector:(OrgOssPdfreporterEngineJRStyledTextAttributeSelector *)attributeSelector {
  if ([self getFullText] == nil) {
    return nil;
  }
  return [((OrgOssPdfreporterEngineUtilJRStyledTextParser *) nil_chk([OrgOssPdfreporterEngineUtilJRStyledTextParser getInstance])) getStyledTextWithJavaUtilMap:[((OrgOssPdfreporterEngineJRStyledTextAttributeSelector *) nil_chk(attributeSelector)) getStyledTextAttributesWithOrgOssPdfreporterEngineJRPrintText:self] withNSString:[self getFullText] withBOOL:![((NSString *) nil_chk([OrgOssPdfreporterEngineJRCommonText MARKUP_NONE])) isEqual:[self getMarkup]] withJavaUtilLocale:[OrgOssPdfreporterEngineJRStyledTextAttributeSelector getTextLocaleWithOrgOssPdfreporterEngineJRPrintText:self]];
}

- (id)getValue {
  return value_;
}

- (void)setValueWithId:(id)value {
  self.value = value;
}

- (float)getLineSpacingFactor {
  return lineSpacingFactor_;
}

- (void)setLineSpacingFactorWithFloat:(float)lineSpacingFactor {
  self.lineSpacingFactor = lineSpacingFactor;
}

- (float)getLeadingOffset {
  return leadingOffset_;
}

- (void)setLeadingOffsetWithFloat:(float)leadingOffset {
  self.leadingOffset = leadingOffset;
}

- (OrgOssPdfreporterEngineTypeHorizontalAlignEnumEnum *)getHorizontalAlignmentValue {
  return [OrgOssPdfreporterEngineUtilJRStyleResolver getHorizontalAlignmentValueWithOrgOssPdfreporterEngineJRAlignment:self];
}

- (OrgOssPdfreporterEngineTypeHorizontalAlignEnumEnum *)getOwnHorizontalAlignmentValue {
  return horizontalAlignmentValue_;
}

- (void)setHorizontalAlignmentWithOrgOssPdfreporterEngineTypeHorizontalAlignEnumEnum:(OrgOssPdfreporterEngineTypeHorizontalAlignEnumEnum *)horizontalAlignmentValue {
  self.horizontalAlignmentValue = horizontalAlignmentValue;
}

- (OrgOssPdfreporterEngineTypeVerticalAlignEnumEnum *)getVerticalAlignmentValue {
  return [OrgOssPdfreporterEngineUtilJRStyleResolver getVerticalAlignmentValueWithOrgOssPdfreporterEngineJRAlignment:self];
}

- (OrgOssPdfreporterEngineTypeVerticalAlignEnumEnum *)getOwnVerticalAlignmentValue {
  return verticalAlignmentValue_;
}

- (void)setVerticalAlignmentWithOrgOssPdfreporterEngineTypeVerticalAlignEnumEnum:(OrgOssPdfreporterEngineTypeVerticalAlignEnumEnum *)verticalAlignmentValue {
  self.verticalAlignmentValue = verticalAlignmentValue;
}

- (OrgOssPdfreporterEngineTypeRotationEnumEnum *)getRotationValue {
  return [OrgOssPdfreporterEngineUtilJRStyleResolver getRotationValueWithOrgOssPdfreporterEngineJRCommonText:self];
}

- (OrgOssPdfreporterEngineTypeRotationEnumEnum *)getOwnRotationValue {
  return rotationValue_;
}

- (void)setRotationWithOrgOssPdfreporterEngineTypeRotationEnumEnum:(OrgOssPdfreporterEngineTypeRotationEnumEnum *)rotationValue {
  self.rotationValue = rotationValue;
}

- (OrgOssPdfreporterEngineTypeRunDirectionEnumEnum *)getRunDirectionValue {
  return self.runDirectionValue;
}

- (void)setRunDirectionWithOrgOssPdfreporterEngineTypeRunDirectionEnumEnum:(OrgOssPdfreporterEngineTypeRunDirectionEnumEnum *)runDirectionValue {
  self.runDirectionValue = runDirectionValue;
}

- (float)getTextHeight {
  return textHeight_;
}

- (void)setTextHeightWithFloat:(float)textHeight {
  self.textHeight = textHeight;
}

- (OrgOssPdfreporterEngineTypeLineSpacingEnumEnum *)getLineSpacingValue {
  return [((id<OrgOssPdfreporterEngineJRParagraph>) nil_chk([self getParagraph])) getLineSpacing];
}

- (OrgOssPdfreporterEngineTypeLineSpacingEnumEnum *)getOwnLineSpacingValue {
  return [((id<OrgOssPdfreporterEngineJRParagraph>) nil_chk([self getParagraph])) getOwnLineSpacing];
}

- (void)setLineSpacingWithOrgOssPdfreporterEngineTypeLineSpacingEnumEnum:(OrgOssPdfreporterEngineTypeLineSpacingEnumEnum *)lineSpacing {
  [((id<OrgOssPdfreporterEngineJRParagraph>) nil_chk([self getParagraph])) setLineSpacingWithOrgOssPdfreporterEngineTypeLineSpacingEnumEnum:lineSpacing];
}

- (NSString *)getMarkup {
  return [OrgOssPdfreporterEngineUtilJRStyleResolver getMarkupWithOrgOssPdfreporterEngineJRCommonText:self];
}

- (NSString *)getOwnMarkup {
  return markup_;
}

- (void)setMarkupWithNSString:(NSString *)markup {
  self.markup = markup;
}

- (id<OrgOssPdfreporterEngineJRLineBox>)getLineBox {
  return lineBox_;
}

- (id<OrgOssPdfreporterEngineJRParagraph>)getParagraph {
  return paragraph_;
}

- (void)copyBoxWithOrgOssPdfreporterEngineJRLineBox:(id<OrgOssPdfreporterEngineJRLineBox>)lineBox OBJC_METHOD_FAMILY_NONE {
  self.lineBox = [((id<OrgOssPdfreporterEngineJRLineBox>) nil_chk(lineBox)) cloneWithOrgOssPdfreporterEngineJRBoxContainer:self];
}

- (void)copyParagraphWithOrgOssPdfreporterEngineJRParagraph:(id<OrgOssPdfreporterEngineJRParagraph>)paragraph OBJC_METHOD_FAMILY_NONE {
  self.paragraph = [((id<OrgOssPdfreporterEngineJRParagraph>) nil_chk(paragraph)) cloneWithOrgOssPdfreporterEngineJRParagraphContainer:self];
}

- (id<OrgOssPdfreporterEngineJRFont>)getFont {
  return self;
}

- (void)setFontWithOrgOssPdfreporterEngineJRFont:(id<OrgOssPdfreporterEngineJRFont>)font {
  fontName_ = [((id<OrgOssPdfreporterEngineJRFont>) nil_chk(font)) getOwnFontName];
  isBold__ = [((id<OrgOssPdfreporterEngineJRFont>) nil_chk(font)) isOwnBold];
  isItalic__ = [((id<OrgOssPdfreporterEngineJRFont>) nil_chk(font)) isOwnItalic];
  isUnderline__ = [((id<OrgOssPdfreporterEngineJRFont>) nil_chk(font)) isOwnUnderline];
  isStrikeThrough__ = [((id<OrgOssPdfreporterEngineJRFont>) nil_chk(font)) isOwnStrikeThrough];
  fontSize_ = [((id<OrgOssPdfreporterEngineJRFont>) nil_chk(font)) getOwnFontSize];
  pdfFontName_ = [((id<OrgOssPdfreporterEngineJRFont>) nil_chk(font)) getOwnPdfFontName];
  pdfEncoding_ = [((id<OrgOssPdfreporterEngineJRFont>) nil_chk(font)) getOwnPdfEncoding];
  isPdfEmbedded__ = [((id<OrgOssPdfreporterEngineJRFont>) nil_chk(font)) isOwnPdfEmbedded];
}

- (void)setTextFormatWithOrgOssPdfreporterEngineFillTextFormat:(id<OrgOssPdfreporterEngineFillTextFormat>)textFormat {
  self.textFormat = textFormat;
}

- (NSString *)getAnchorName {
  return anchorName_;
}

- (void)setAnchorNameWithNSString:(NSString *)anchorName {
  self.anchorName = anchorName;
}

- (OrgOssPdfreporterEngineTypeHyperlinkTypeEnumEnum *)getHyperlinkTypeValue {
  return [OrgOssPdfreporterEngineJRHyperlinkHelper getHyperlinkTypeValueWithNSString:[self getLinkType]];
}

- (void)setHyperlinkTypeWithOrgOssPdfreporterEngineTypeHyperlinkTypeEnumEnum:(OrgOssPdfreporterEngineTypeHyperlinkTypeEnumEnum *)hyperlinkType {
  [self setLinkTypeWithNSString:[OrgOssPdfreporterEngineJRHyperlinkHelper getLinkTypeWithOrgOssPdfreporterEngineTypeHyperlinkTypeEnumEnum:hyperlinkType]];
}

- (OrgOssPdfreporterEngineTypeHyperlinkTargetEnumEnum *)getHyperlinkTargetValue {
  return [OrgOssPdfreporterEngineJRHyperlinkHelper getHyperlinkTargetValueWithNSString:[self getLinkTarget]];
}

- (void)setHyperlinkTargetWithOrgOssPdfreporterEngineTypeHyperlinkTargetEnumEnum:(OrgOssPdfreporterEngineTypeHyperlinkTargetEnumEnum *)hyperlinkTarget {
  [self setLinkTargetWithNSString:[OrgOssPdfreporterEngineJRHyperlinkHelper getLinkTargetWithOrgOssPdfreporterEngineTypeHyperlinkTargetEnumEnum:hyperlinkTarget]];
}

- (NSString *)getHyperlinkReference {
  return hyperlinkReference_;
}

- (void)setHyperlinkReferenceWithNSString:(NSString *)hyperlinkReference {
  self.hyperlinkReference = hyperlinkReference;
}

- (NSString *)getHyperlinkAnchor {
  return hyperlinkAnchor_;
}

- (void)setHyperlinkAnchorWithNSString:(NSString *)hyperlinkAnchor {
  self.hyperlinkAnchor = hyperlinkAnchor;
}

- (JavaLangInteger *)getHyperlinkPage {
  return hyperlinkPage_;
}

- (void)setHyperlinkPageWithJavaLangInteger:(JavaLangInteger *)hyperlinkPage {
  self.hyperlinkPage = hyperlinkPage;
}

- (void)setHyperlinkPageWithNSString:(NSString *)hyperlinkPage {
  self.hyperlinkPage = [JavaLangInteger valueOfWithNSString:hyperlinkPage];
}

- (int)getBookmarkLevel {
  return bookmarkLevel_;
}

- (void)setBookmarkLevelWithInt:(int)bookmarkLevel {
  self.bookmarkLevel = bookmarkLevel;
}

- (NSString *)getFontName {
  return [OrgOssPdfreporterEngineUtilJRStyleResolver getFontNameWithOrgOssPdfreporterEngineJRFont:self];
}

- (NSString *)getOwnFontName {
  return fontName_;
}

- (void)setFontNameWithNSString:(NSString *)fontName {
  self.fontName = fontName;
}

- (BOOL)isBold {
  return [OrgOssPdfreporterEngineUtilJRStyleResolver isBoldWithOrgOssPdfreporterEngineJRFont:self];
}

- (JavaLangBoolean *)isOwnBold {
  return isBold__;
}

- (void)setBoldWithBOOL:(BOOL)isBold {
  [self setBoldWithJavaLangBoolean:isBold ? [JavaLangBoolean getTRUE] : [JavaLangBoolean getFALSE]];
}

- (void)setBoldWithJavaLangBoolean:(JavaLangBoolean *)isBold {
  self.isBold_ = isBold;
}

- (BOOL)isItalic {
  return [OrgOssPdfreporterEngineUtilJRStyleResolver isItalicWithOrgOssPdfreporterEngineJRFont:self];
}

- (JavaLangBoolean *)isOwnItalic {
  return isItalic__;
}

- (void)setItalicWithBOOL:(BOOL)isItalic {
  [self setItalicWithJavaLangBoolean:isItalic ? [JavaLangBoolean getTRUE] : [JavaLangBoolean getFALSE]];
}

- (void)setItalicWithJavaLangBoolean:(JavaLangBoolean *)isItalic {
  self.isItalic_ = isItalic;
}

- (BOOL)isUnderline {
  return [OrgOssPdfreporterEngineUtilJRStyleResolver isUnderlineWithOrgOssPdfreporterEngineJRFont:self];
}

- (JavaLangBoolean *)isOwnUnderline {
  return isUnderline__;
}

- (void)setUnderlineWithBOOL:(BOOL)isUnderline {
  [self setUnderlineWithJavaLangBoolean:isUnderline ? [JavaLangBoolean getTRUE] : [JavaLangBoolean getFALSE]];
}

- (void)setUnderlineWithJavaLangBoolean:(JavaLangBoolean *)isUnderline {
  self.isUnderline_ = isUnderline;
}

- (BOOL)isStrikeThrough {
  return [OrgOssPdfreporterEngineUtilJRStyleResolver isStrikeThroughWithOrgOssPdfreporterEngineJRFont:self];
}

- (JavaLangBoolean *)isOwnStrikeThrough {
  return isStrikeThrough__;
}

- (void)setStrikeThroughWithBOOL:(BOOL)isStrikeThrough {
  [self setStrikeThroughWithJavaLangBoolean:isStrikeThrough ? [JavaLangBoolean getTRUE] : [JavaLangBoolean getFALSE]];
}

- (void)setStrikeThroughWithJavaLangBoolean:(JavaLangBoolean *)isStrikeThrough {
  self.isStrikeThrough_ = isStrikeThrough;
}

- (int)getFontSize {
  return [OrgOssPdfreporterEngineUtilJRStyleResolver getFontSizeWithOrgOssPdfreporterEngineJRFont:self];
}

- (JavaLangInteger *)getOwnFontSize {
  return fontSize_;
}

- (void)setFontSizeWithInt:(int)fontSize {
  [self setFontSizeWithJavaLangInteger:[JavaLangInteger valueOfWithInt:fontSize]];
}

- (void)setFontSizeWithJavaLangInteger:(JavaLangInteger *)fontSize {
  self.fontSize = fontSize;
}

- (NSString *)getPdfFontName {
  return [OrgOssPdfreporterEngineUtilJRStyleResolver getPdfFontNameWithOrgOssPdfreporterEngineJRFont:self];
}

- (NSString *)getOwnPdfFontName {
  return pdfFontName_;
}

- (void)setPdfFontNameWithNSString:(NSString *)pdfFontName {
  self.pdfFontName = pdfFontName;
}

- (NSString *)getPdfEncoding {
  return [OrgOssPdfreporterEngineUtilJRStyleResolver getPdfEncodingWithOrgOssPdfreporterEngineJRFont:self];
}

- (NSString *)getOwnPdfEncoding {
  return pdfEncoding_;
}

- (void)setPdfEncodingWithNSString:(NSString *)pdfEncoding {
  self.pdfEncoding = pdfEncoding;
}

- (BOOL)isPdfEmbedded {
  return [OrgOssPdfreporterEngineUtilJRStyleResolver isPdfEmbeddedWithOrgOssPdfreporterEngineJRFont:self];
}

- (JavaLangBoolean *)isOwnPdfEmbedded {
  return isPdfEmbedded__;
}

- (void)setPdfEmbeddedWithBOOL:(BOOL)isPdfEmbedded {
  [self setPdfEmbeddedWithJavaLangBoolean:isPdfEmbedded ? [JavaLangBoolean getTRUE] : [JavaLangBoolean getFALSE]];
}

- (void)setPdfEmbeddedWithJavaLangBoolean:(JavaLangBoolean *)isPdfEmbedded {
  self.isPdfEmbedded_ = isPdfEmbedded;
}

- (NSString *)getPattern {
  return pattern_;
}

- (void)setPatternWithNSString:(NSString *)pattern {
  self.pattern = pattern;
}

- (NSString *)getValueClassName {
  return valueClassName_;
}

- (void)setValueClassNameWithNSString:(NSString *)valueClassName {
  self.valueClassName = valueClassName;
}

- (NSString *)getFormatFactoryClass {
  return formatFactoryClass_;
}

- (void)setFormatFactoryClassWithNSString:(NSString *)formatFactoryClass {
  self.formatFactoryClass = formatFactoryClass;
}

- (NSString *)getLocaleCode {
  return localeCode_;
}

- (void)setLocaleCodeWithNSString:(NSString *)localeCode {
  self.localeCode = localeCode;
}

- (NSString *)getTimeZoneId {
  return timeZoneId_;
}

- (void)setTimeZoneIdWithNSString:(NSString *)timeZoneId {
  self.timeZoneId = timeZoneId;
}

- (OrgOssPdfreporterEngineJRPrintHyperlinkParameters *)getHyperlinkParameters {
  return hyperlinkParameters_;
}

- (void)setHyperlinkParametersWithOrgOssPdfreporterEngineJRPrintHyperlinkParameters:(OrgOssPdfreporterEngineJRPrintHyperlinkParameters *)hyperlinkParameters {
  self.hyperlinkParameters = hyperlinkParameters;
}

- (void)addHyperlinkParameterWithOrgOssPdfreporterEngineJRPrintHyperlinkParameter:(OrgOssPdfreporterEngineJRPrintHyperlinkParameter *)parameter {
  if (hyperlinkParameters_ == nil) {
    hyperlinkParameters_ = [[OrgOssPdfreporterEngineJRPrintHyperlinkParameters alloc] init];
  }
  [((OrgOssPdfreporterEngineJRPrintHyperlinkParameters *) nil_chk(hyperlinkParameters_)) addParameterWithOrgOssPdfreporterEngineJRPrintHyperlinkParameter:parameter];
}

- (NSString *)getLinkType {
  return linkType_;
}

- (void)setLinkTypeWithNSString:(NSString *)linkType {
  self.linkType = linkType;
}

- (NSString *)getLinkTarget {
  return linkTarget_;
}

- (void)setLinkTargetWithNSString:(NSString *)linkTarget {
  self.linkTarget = linkTarget;
}

- (NSString *)getHyperlinkTooltip {
  return hyperlinkTooltip_;
}

- (void)setHyperlinkTooltipWithNSString:(NSString *)hyperlinkTooltip {
  self.hyperlinkTooltip = hyperlinkTooltip;
}

- (id<OrgOssPdfreporterGeometryIColor>)getDefaultLineColor {
  return [self getForecolor];
}

- (void)acceptWithOrgOssPdfreporterEnginePrintElementVisitor:(id<OrgOssPdfreporterEnginePrintElementVisitor>)visitor
                                                      withId:(id)arg {
  [((id<OrgOssPdfreporterEnginePrintElementVisitor>) nil_chk(visitor)) visitWithOrgOssPdfreporterEngineJRPrintText:self withId:arg];
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterEngineBaseJRBasePrintText *typedCopy = (OrgOssPdfreporterEngineBaseJRBasePrintText *) copy;
  typedCopy.text = text_;
  typedCopy.textTruncateIndex = textTruncateIndex_;
  typedCopy.lineBreakOffsets = lineBreakOffsets_;
  typedCopy.textTruncateSuffix = textTruncateSuffix_;
  typedCopy.value = value_;
  typedCopy.lineSpacingFactor = lineSpacingFactor_;
  typedCopy.leadingOffset = leadingOffset_;
  typedCopy.horizontalAlignmentValue = horizontalAlignmentValue_;
  typedCopy.verticalAlignmentValue = verticalAlignmentValue_;
  typedCopy.rotationValue = rotationValue_;
  typedCopy.runDirectionValue = runDirectionValue_;
  typedCopy.textHeight = textHeight_;
  typedCopy.markup = markup_;
  typedCopy.textFormat = textFormat_;
  typedCopy.anchorName = anchorName_;
  typedCopy.linkType = linkType_;
  typedCopy.linkTarget = linkTarget_;
  typedCopy.hyperlinkReference = hyperlinkReference_;
  typedCopy.hyperlinkAnchor = hyperlinkAnchor_;
  typedCopy.hyperlinkPage = hyperlinkPage_;
  typedCopy.hyperlinkTooltip = hyperlinkTooltip_;
  typedCopy.hyperlinkParameters = hyperlinkParameters_;
  typedCopy.bookmarkLevel = bookmarkLevel_;
  typedCopy.lineBox = lineBox_;
  typedCopy.paragraph = paragraph_;
  typedCopy.fontName = fontName_;
  typedCopy.isBold_ = isBold__;
  typedCopy.isItalic_ = isItalic__;
  typedCopy.isUnderline_ = isUnderline__;
  typedCopy.isStrikeThrough_ = isStrikeThrough__;
  typedCopy.fontSize = fontSize_;
  typedCopy.pdfFontName = pdfFontName_;
  typedCopy.pdfEncoding = pdfEncoding_;
  typedCopy.isPdfEmbedded_ = isPdfEmbedded__;
  typedCopy.valueClassName = valueClassName_;
  typedCopy.pattern = pattern_;
  typedCopy.formatFactoryClass = formatFactoryClass_;
  typedCopy.localeCode = localeCode_;
  typedCopy.timeZoneId = timeZoneId_;
}

@end