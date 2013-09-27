//
//  FontBox.h
//  JasperReportiOS
//
//  Created by Fr3e on 6/8/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "hpdf.h"

@interface FontBox : NSObject {
    HPDF_Font mFont;
}

- (id)initWithHpdfFont:(HPDF_Font)font;
- (HPDF_Font)getHpdfFont;
@end
