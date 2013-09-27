//
//  NumberFormat.h
//  JasperReportiOS
//
//  Created by Fr3e on 6/14/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "org/oss/pdfreporter/text/format/INumberFormat.h"

@interface NumberFormat : NSObject < OrgOssPdfreporterTextFormatINumberFormat > {
    NSNumberFormatter *formatter;
}

- (id)initWithPattern:(NSString*)pattern locale:(JavaUtilLocale*)locale;

@end
