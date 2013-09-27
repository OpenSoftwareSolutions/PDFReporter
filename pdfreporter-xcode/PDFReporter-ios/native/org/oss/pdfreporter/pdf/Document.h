//
//  Document.h
//  JasperReportiOS
//
//  Created by Fr3e on 5/27/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "org/oss/pdfreporter/pdf/AbstractDocument.h"
#import "hpdf.h"

@interface OrgOssPdfreporterPdfDocument : OrgOssPdfreporterPdfAbstractDocument {
    NSString *filename;
    float document_width;
    float document_height;
    bool closed;
}

- (id)initWithNSString:(NSString *)_filename;
- (id)initWithFileName:(NSString *)_filename width:(float)_widht height:(float)_height;
- (int)convertPermissions:(int)permission;
@end
