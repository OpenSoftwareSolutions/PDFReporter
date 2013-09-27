//
//  FontBox.m
//  JasperReportiOS
//
//  Created by Fr3e on 6/8/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "FontBox.h"

@implementation FontBox

- (id)initWithHpdfFont:(HPDF_Font)font {
    self = [super init];
    if (self) {
        mFont = font;
    }
    return self;
}

- (HPDF_Font)getHpdfFont {
    return mFont;
}

@end
