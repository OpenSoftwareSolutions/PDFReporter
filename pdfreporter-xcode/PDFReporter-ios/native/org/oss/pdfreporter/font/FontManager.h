//
//  FontManager.h
//  JasperReportiOS
//
//  Created by Fr3e on 6/5/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "org/oss/pdfreporter/font/AbstractFontManager.h"

@interface OrgOssPdfreporterFontFontManager : OrgOssPdfreporterFontAbstractFontManager {
    NSMutableDictionary *fontDict;
}

- (id)init;
@end
