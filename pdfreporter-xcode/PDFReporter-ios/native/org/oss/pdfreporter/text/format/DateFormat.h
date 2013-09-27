//
//  DateFormat.h
//  JasperReportiOS
//
//  Created by Fr3e on 6/17/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "org/oss/pdfreporter/text/format/IDateFormat.h"
#import "java/util/Locale.h"
#import "java/util/TimeZone.h"

@interface OrgOssPdfreporterTextFormatDateFormat : NSObject < OrgOssPdfreporterTextFormatIDateFormat >
{
    NSDateFormatter *formatter;
    NSString *m_pattern;
}

- (id)initWithPattern:(NSString*)pattern locale:(JavaUtilLocale*)locale timeZone:(JavaUtilTimeZone*)timeZone;

@end
