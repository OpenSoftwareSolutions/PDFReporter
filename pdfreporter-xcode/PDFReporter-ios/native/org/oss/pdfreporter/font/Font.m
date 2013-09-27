//
//  Font.m
//  JasperReportiOS
//
//  Created by Fr3e on 6/5/13.
//  Copyright (c) 2013 Digireport. All rights reserved. //

#import "Font.h"
#import "FontMetrics.h"
#import "FontManager.h"
#import "FontBox.h"
#import "HpdfDocBox.h"
#import "org/oss/pdfreporter/registry/ApiRegistry.h"
#import "org/oss/pdfreporter/font/FontFactory.h"

@implementation Font

- (id)initWithFontName:(NSString*)name style:(OrgOssPdfreporterFontIFont_FontStyleEnum*)style size:(float)size encoding:(NSString*)encoding manager:(OrgOssPdfreporterFontFontManager*)manager
{
    self = [super init];
    if(self) {
        mName = name;
        mEncoding = encoding;
        mStyle = style;
        mSize = size;
        mManager = manager;
    }
    return self;
}

-(id<OrgOssPdfreporterFontIFontManager>)getFontManager
{
    return mManager;
}

- (NSString *)getName
{
    return mName;
}

- (float)getSize
{
    return mSize;
}

- (NSString *)getEncoding
{
    return mEncoding;
}

- (OrgOssPdfreporterFontIFont_FontStyleEnum *)getStyle
{
    return mStyle;
}

- (OrgOssPdfreporterFontIFont_FontDecorationEnum *)getDecoration
{
    return nil;
}

- (id<OrgOssPdfreporterFontIFontMetric>)getMetric
{
    return [[FontMetrics alloc] initWithFont:self];
}

- (NSString *)getResourcePath
{
    return mName;
}

- (id)getPeer
{
    HpdfDocBox *docBox = [HpdfDocBox GetDocBoxFromSession:[[OrgOssPdfreporterRegistryApiRegistry getFontFactory] getSession]];
    if (!HPDF_HasDoc([docBox getHpdfDoc])) @throw [NSException exceptionWithName:@"HPDF_NEW" reason:@"!has doc" userInfo:nil];
    const char *cName = [mName UTF8String];
    const char *cEncoding = [mEncoding UTF8String];
    HPDF_Font hpdf_Font = HPDF_GetFont([docBox getHpdfDoc], cName, cEncoding);
    if(hpdf_Font == NULL) @throw [[NSException alloc] initWithName:@"Font exception" reason:@"Font not loaded" userInfo:nil];
    FontBox *fontBox = [[FontBox alloc] initWithHpdfFont:hpdf_Font];
    return fontBox;
}

@end
