//
//  FallbackFormater.m
//  JasperReportLib
//
//  Created by Fr3e on 7/30/13.
//  Copyright (c) 2013 Fr3e. All rights reserved.
//

#import "FallbackFormater.h"
#import "org/oss/pdfreporter/exception/NotImplementedException.h"
#import "Formatter.h"
#import "java/lang/Double.h"
#import "java/lang/Long.h"
#include "IOSClass.h"

@implementation FallbackFormater

- (id)initWithPattern:(NSString*)pattern
{
    self = [super init];
    if (self) {
        m_pattern = pattern;
    }
    return self;
}

- (id)parseWithNSString:(NSString *)source
{
    @throw [[OrgOssPdfreporterExceptionNotImplementedException alloc] init];
}

//IFormat
- (id)parseObjectWithNSString:(NSString *)source
{
    @throw [[OrgOssPdfreporterExceptionNotImplementedException alloc] init];
}

- (NSString *)formatWithId:(id)obj
{
    return [Formatter formatWithNSString:m_pattern withNSObjectArray:[IOSObjectArray arrayWithObjects:(id[]){ obj } count:1 type:[IOSClass classForIosName:@"NSObject"]]];
}

// IDateFormat
- (NSString *)formatWithJavaUtilDate:(JavaUtilDate *)date
{
    return [self formatWithId:date];
}

// IMessageFormat
- (NSString *)formatWithNSObjectArray:(IOSObjectArray *)arguments
{
    return [Formatter formatWithNSString:m_pattern withNSObjectArray:arguments];
}

// INumberFormat


- (NSString *)formatWithLongInt:(long long int)number
{
    return [self formatWithId:[[JavaLangLong alloc] initWithLongLong:number]];
}

- (NSString *)formatWithDouble:(double)number
{
    return [self formatWithId:[[JavaLangDouble alloc] initWithDouble:number]];
}

@end
