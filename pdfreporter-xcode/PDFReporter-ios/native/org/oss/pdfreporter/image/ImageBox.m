//
//  ImageBox.m
//  JasperReportiOS
//
//  Created by Fr3e on 6/1/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "ImageBox.h"

@implementation ImageBox

- (id)initWithHpdfImage:(HPDF_Image)image {
    self = [super init];
    if (self) {
        mImage = image;
    }
    return self;
}

- (HPDF_Image)getHpdfImage {
    return mImage;
}

@end
