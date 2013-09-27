//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/export/FontKey.java
//
//  Created by kendra on 9/27/13.
//

#include "IOSClass.h"
#include "org/oss/pdfreporter/engine/export/FontKey.h"

@implementation OrgOssPdfreporterEngineExportFontKey

@synthesize fontName = fontName_;
@synthesize isBold_ = isBold__;
@synthesize isItalic_ = isItalic__;

- (id)initWithNSString:(NSString *)fontName
              withBOOL:(BOOL)bold
              withBOOL:(BOOL)italic {
  if ((self = [super init])) {
    self.fontName = fontName;
    isBold__ = bold;
    isItalic__ = italic;
  }
  return self;
}

- (NSString *)getFontName {
  return fontName_;
}

- (BOOL)isBold {
  return isBold__;
}

- (BOOL)isItalic {
  return isItalic__;
}

- (BOOL)isEqual:(id)o {
  if (self == o) {
    return YES;
  }
  if (o == nil || [self getClass] != [nil_chk(o) getClass]) {
    return NO;
  }
  OrgOssPdfreporterEngineExportFontKey *key = (OrgOssPdfreporterEngineExportFontKey *) o;
  if (isBold__ != ((OrgOssPdfreporterEngineExportFontKey *) nil_chk(key)).isBold_) {
    return NO;
  }
  if (isItalic__ != ((OrgOssPdfreporterEngineExportFontKey *) nil_chk(key)).isItalic_) {
    return NO;
  }
  if (fontName_ != nil ? ![((NSString *) nil_chk(fontName_)) isEqual:((OrgOssPdfreporterEngineExportFontKey *) nil_chk(key)).fontName] : ((OrgOssPdfreporterEngineExportFontKey *) nil_chk(key)).fontName != nil) {
    return NO;
  }
  return YES;
}

- (NSUInteger)hash {
  int result;
  result = (fontName_ != nil ? [((NSString *) nil_chk(fontName_)) hash] : 0);
  result = 29 * result + (isBold__ ? 1 : 0);
  result = 29 * result + (isItalic__ ? 1 : 0);
  return result;
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterEngineExportFontKey *typedCopy = (OrgOssPdfreporterEngineExportFontKey *) copy;
  typedCopy.fontName = fontName_;
  typedCopy.isBold_ = isBold__;
  typedCopy.isItalic_ = isItalic__;
}

@end