//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/util/JRStyledTextParser.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineUtilJRStyledTextParser_H_
#define _OrgOssPdfreporterEngineUtilJRStyledTextParser_H_

@class JavaLangStringBuffer;
@class JavaUtilLocale;
@class OrgOssPdfreporterEngineBaseJRBasePrintHyperlink;
@class OrgOssPdfreporterEngineUtilJRStyledText;
@class OrgOssPdfreporterXmlParsersXMLParseException;
@protocol JavaUtilList;
@protocol JavaUtilMap;
@protocol JavaUtilSet;
@protocol OrgOssPdfreporterUsesJavaAwtTextIAttributedCharacterIterator;
@protocol OrgOssPdfreporterUsesOrgW3cDomNode;
@protocol OrgOssPdfreporterXmlParsersIDocumentBuilder;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/xml/parsers/XMLErrorHandler.h"

@interface OrgOssPdfreporterEngineUtilJRStyledTextParser : NSObject < OrgOssPdfreporterXmlParsersXMLErrorHandler > {
 @public
  JavaUtilLocale *locale_;
  id<OrgOssPdfreporterXmlParsersIDocumentBuilder> documentBuilder_;
  OrgOssPdfreporterEngineBaseJRBasePrintHyperlink *hyperlink_;
}

@property (nonatomic, strong) JavaUtilLocale *locale;
@property (nonatomic, strong) id<OrgOssPdfreporterXmlParsersIDocumentBuilder> documentBuilder;
@property (nonatomic, strong) OrgOssPdfreporterEngineBaseJRBasePrintHyperlink *hyperlink;

+ (id<JavaUtilSet>)AVAILABLE_FONT_FACE_NAMES;
+ (OrgOssPdfreporterEngineUtilJRStyledTextParser *)instance;
+ (NSString *)ROOT_START;
+ (NSString *)ROOT_END;
+ (NSString *)NODE_style;
+ (NSString *)NODE_bold;
+ (NSString *)NODE_italic;
+ (NSString *)NODE_underline;
+ (NSString *)NODE_sup;
+ (NSString *)NODE_sub;
+ (NSString *)NODE_font;
+ (NSString *)NODE_br;
+ (NSString *)NODE_li;
+ (NSString *)NODE_a;
+ (NSString *)NODE_param;
+ (NSString *)ATTRIBUTE_fontName;
+ (NSString *)ATTRIBUTE_fontFace;
+ (NSString *)ATTRIBUTE_color;
+ (NSString *)ATTRIBUTE_size;
+ (NSString *)ATTRIBUTE_isBold;
+ (NSString *)ATTRIBUTE_isItalic;
+ (NSString *)ATTRIBUTE_isUnderline;
+ (NSString *)ATTRIBUTE_isStrikeThrough;
+ (NSString *)ATTRIBUTE_forecolor;
+ (NSString *)ATTRIBUTE_backcolor;
+ (NSString *)ATTRIBUTE_pdfFontName;
+ (NSString *)ATTRIBUTE_pdfEncoding;
+ (NSString *)ATTRIBUTE_isPdfEmbedded;
+ (NSString *)ATTRIBUTE_type;
+ (NSString *)ATTRIBUTE_href;
+ (NSString *)ATTRIBUTE_target;
+ (NSString *)ATTRIBUTE_name;
+ (NSString *)ATTRIBUTE_valueClass;
+ (NSString *)SPACE;
+ (NSString *)EQUAL_QUOTE;
+ (NSString *)QUOTE;
+ (NSString *)SHARP;
+ (NSString *)LESS;
+ (NSString *)LESS_SLASH;
+ (NSString *)GREATER;
+ (OrgOssPdfreporterEngineUtilJRStyledTextParser *)getInstance;
+ (void)setLocaleWithJavaUtilLocale:(JavaUtilLocale *)locale;
+ (JavaUtilLocale *)getLocale;
- (id)init;
- (OrgOssPdfreporterEngineUtilJRStyledText *)parseWithJavaUtilMap:(id<JavaUtilMap>)attributes
                                                     withNSString:(NSString *)text
                                               withJavaUtilLocale:(JavaUtilLocale *)locale;
- (OrgOssPdfreporterEngineUtilJRStyledText *)getStyledTextWithJavaUtilMap:(id<JavaUtilMap>)parentAttributes
                                                             withNSString:(NSString *)text
                                                                 withBOOL:(BOOL)isStyledText
                                                       withJavaUtilLocale:(JavaUtilLocale *)locale;
- (NSString *)writeWithOrgOssPdfreporterEngineUtilJRStyledText:(OrgOssPdfreporterEngineUtilJRStyledText *)styledText;
- (NSString *)writeWithJavaUtilMap:(id<JavaUtilMap>)parentAttrs
withOrgOssPdfreporterUsesJavaAwtTextIAttributedCharacterIterator:(id<OrgOssPdfreporterUsesJavaAwtTextIAttributedCharacterIterator>)iterator
                      withNSString:(NSString *)text;
- (NSString *)writeWithOrgOssPdfreporterEngineUtilJRStyledText:(OrgOssPdfreporterEngineUtilJRStyledText *)styledText
                                                       withInt:(int)startIndex
                                                       withInt:(int)endIndex;
- (void)writeChunkWithJavaLangStringBuffer:(JavaLangStringBuffer *)sbuffer
                           withJavaUtilMap:(id<JavaUtilMap>)parentAttrs
                           withJavaUtilMap:(id<JavaUtilMap>)attrs
                              withNSString:(NSString *)chunk;
- (void)parseStyleWithOrgOssPdfreporterEngineUtilJRStyledText:(OrgOssPdfreporterEngineUtilJRStyledText *)styledText
                       withOrgOssPdfreporterUsesOrgW3cDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)parentNode;
- (void)resizeRunsWithJavaUtilList:(id<JavaUtilList>)runs
                           withInt:(int)startIndex
                           withInt:(int)count;
- (JavaLangStringBuffer *)writeStyleAttributesWithJavaUtilMap:(id<JavaUtilMap>)parentAttrs
                                              withJavaUtilMap:(id<JavaUtilMap>)attrs;
- (NSString *)getFirstTextOccurenceWithOrgOssPdfreporterUsesOrgW3cDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)node;
- (void)errorWithOrgOssPdfreporterXmlParsersXMLParseException:(OrgOssPdfreporterXmlParsersXMLParseException *)e;
- (void)fatalErrorWithOrgOssPdfreporterXmlParsersXMLParseException:(OrgOssPdfreporterXmlParsersXMLParseException *)e;
- (void)warningWithOrgOssPdfreporterXmlParsersXMLParseException:(OrgOssPdfreporterXmlParsersXMLParseException *)e;
@end

#endif // _OrgOssPdfreporterEngineUtilJRStyledTextParser_H_