//
//  FallbackFormater.h
//  JasperReportLib
//
//  Created by Fr3e on 7/30/13.
//  Copyright (c) 2013 Fr3e. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "org/oss/pdfreporter/text/format/IMessageFormat.h"
#import "org/oss/pdfreporter/text/format/IDateFormat.h"
#import "org/oss/pdfreporter/text/format/INumberFormat.h"

@interface FallbackFormater : NSObject<OrgOssPdfreporterTextFormatIDateFormat, OrgOssPdfreporterTextFormatIMessageFormat, OrgOssPdfreporterTextFormatINumberFormat>
{
    NSString *m_pattern;
}
- (id)initWithPattern:(NSString*)pattern;
@end
