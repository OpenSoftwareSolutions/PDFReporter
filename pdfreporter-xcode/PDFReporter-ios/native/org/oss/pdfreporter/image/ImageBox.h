//
//  ImageBox.h
//  JasperReportiOS
//
//  Created by Fr3e on 6/1/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "hpdf.h"

@interface ImageBox : NSObject {
    HPDF_Image mImage;
}

- (HPDF_Image)getHpdfImage;
- (id)initWithHpdfImage:(HPDF_Image)image;
@end
