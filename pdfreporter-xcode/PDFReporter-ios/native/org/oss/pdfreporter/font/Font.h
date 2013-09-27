//
//  Font.h
//  JasperReportiOS
//
//  Created by Fr3e on 6/5/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "org/oss/pdfreporter/font/IFont.h"
#import "hpdf.h"

@class OrgOssPdfreporterFontFontManager;

@interface Font : NSObject <OrgOssPdfreporterFontIFont> {
    OrgOssPdfreporterFontIFont_FontStyleEnum *mStyle;
    NSString *mName;
    NSString *mEncoding;
    float mSize;
    OrgOssPdfreporterFontFontManager *mManager;
}

- (id)initWithFontName:(NSString*)name style:(OrgOssPdfreporterFontIFont_FontStyleEnum*)style size:(float)size encoding:(NSString*)encoding manager:(OrgOssPdfreporterFontFontManager*)manager;

@end
