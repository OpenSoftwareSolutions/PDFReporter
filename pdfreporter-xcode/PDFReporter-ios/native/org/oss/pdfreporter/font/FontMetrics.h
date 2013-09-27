//
//  FontMetrics.h
//  JasperReportiOS
//
//  Created by Fr3e on 6/5/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "org/oss/pdfreporter/font/IFontMetric.h"
#import "Font.h"
#import "hpdf.h"
#import "org/oss/pdfreporter/font/AbstractFontMetric.h"

@interface FontMetrics : OrgOssPdfreporterFontAbstractFontMetric {
    Font *mFont;
    HPDF_Font mHpdf_Font;
}

- (id)initWithFont:(Font*)font;

@end
