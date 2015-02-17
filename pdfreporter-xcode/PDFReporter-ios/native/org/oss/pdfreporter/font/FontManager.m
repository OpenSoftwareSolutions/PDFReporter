//
//  FontManager.m
//  JasperReportiOS
//
//  Created by Fr3e on 6/5/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "FontManager.h"
#import "hpdf.h"
#import "HpdfDocBox.h"
#import "Font.h"
#import "org/oss/pdfreporter/registry/ApiRegistry.h"
#import "org/oss/pdfreporter/font/FontFactory.h"

@implementation OrgOssPdfreporterFontFontManager

- (id)init{
    self = [super init];
    if(self) {
        fontDict = [[NSMutableDictionary alloc] init];
    }
    return self;
}

- (NSString *)loadFontInternalWithNSString:(NSString *)filePath withNSString:(NSString *)encoding withBoolean:(BOOL)embed {
    if(filePath == nil) return nil;
    
    HpdfDocBox *docBox = [HpdfDocBox GetDocBoxFromSession:[[OrgOssPdfreporterRegistryApiRegistry getFontFactory] getSession]];
    HPDF_Doc hpdf_doc = [docBox getHpdfDoc];
    
    const char *cPath = [filePath UTF8String];
    const char *cName = HPDF_LoadTTFontFromFile(hpdf_doc, cPath, embed);
    if(cName == nil) return nil;
    NSString *fontName = [NSString stringWithCString:cName encoding:NSUTF8StringEncoding];
    // @"UTF-8" encoding @"FontSpecific"
    Font *font = [[Font alloc] initWithFontName:fontName style:OrgOssPdfreporterFontIFont_FontStyleEnum_PLAIN size:1 encoding: @"UTF-8" manager:self];
    
    [fontDict setObject:font forKey:fontName];
    
    return fontName;
}

- (id<OrgOssPdfreporterFontIFont>)getFontInternalWithNSString:(NSString *)fontname {
    if(fontname == nil) return nil;
    id<OrgOssPdfreporterFontIFont> font = [fontDict objectForKey:fontname];
    if(font == nil) {
        font = [[Font alloc] initWithFontName:fontname style:OrgOssPdfreporterFontIFont_FontStyleEnum_PLAIN size:1 encoding:@"FontSpecific" manager:self];
        [fontDict setObject:font forKey:fontname];
    }
    return font;
}

- (void)disposeInternal {
    
}


@end
