//
//  FontMetrics.m
//  JasperReportiOS
//
//  Created by Fr3e on 6/5/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "FontMetrics.h"
#import "org/oss/pdfreporter/geometry/Rectangle.h"
#import "FontBox.h"

@implementation FontMetrics

- (id)initWithFont:(Font*)font {
    self = [super init];
    if(self) {
        mFont = font;
        mHpdf_Font = [[mFont getPeer] getHpdfFont];
        if(mHpdf_Font == NULL) @throw [NSException exceptionWithName:@"FontMetrics" reason:@"Null font" userInfo:nil];
    }
    return self;
}

- (int)measureTextWithNSString:(NSString *)text withInt:(int)width withBOOL:(BOOL)wordwrap {
    const char *cText = [text UTF8String];
    float calcWidth = ((float)width);
    float realWidth = -1;
    int chars = -1;
    if (!HPDF_Font_Validate(mHpdf_Font)) @throw [NSException exceptionWithName:@"FONT" reason:@"not valid" userInfo:nil];
    chars = HPDF_Font_MeasureText(mHpdf_Font, (const unsigned char*)cText, [text length], calcWidth, 1000, 0, 0, wordwrap, &realWidth);
    //printf("Text: %s, width: %d, chars: %d font: %p\n",cText, width, chars, mHpdf_Font);
    return chars;
}

- (int)getWidthWithNSString:(NSString *)text {
    const char *cText = [text UTF8String];
    HPDF_TextWidth textWidth = HPDF_Font_TextWidth(mHpdf_Font, (const unsigned char*)cText, [text length]);
    return textWidth.width;
}

@end
