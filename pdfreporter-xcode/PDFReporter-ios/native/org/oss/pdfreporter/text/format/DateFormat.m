//
//  DateFormat.m
//  JasperReportiOS
//
//  Created by Fr3e on 6/17/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "DateFormat.h"
#import "java/util/Date.h"
#import "java/util/Calendar.h"

@implementation OrgOssPdfreporterTextFormatDateFormat

- (id)initWithPattern:(NSString*)pattern locale:(JavaUtilLocale*)locale timeZone:(JavaUtilTimeZone*)timeZone
{
    self = [super init];
    if(self)
    {
        formatter = [[NSDateFormatter alloc] init];
        m_pattern = pattern;
        if(pattern != nil)
        {
            [formatter setDateFormat:pattern];
        }
        else
        {
            [formatter setDateFormat:@"yyyy-mm-dd"];
        }
        
        if(locale != nil)
        {
            NSString *localeIdentifier = [NSString stringWithFormat:@"%@_%@", [locale getLanguage], [locale getCountry]];
            NSLocale *nsLocale = [[NSLocale alloc] initWithLocaleIdentifier:localeIdentifier];
            [formatter setLocale:nsLocale];
        }
        if(timeZone != nil)
        {
            NSString *name = [timeZone getID];
            NSTimeZone *zone = [NSTimeZone timeZoneWithName:name];
            [formatter setTimeZone:zone];
        }
    }
    return self;
}

- (JavaUtilDate *)parseWithNSString:(NSString *)source
{
    NSDate *date = [formatter dateFromString:source];
    long long int mili = (long long int)([date timeIntervalSince1970]*1000);
    return [[JavaUtilDate alloc] initWithLong:mili];
}

- (NSString *)formatWithJavaUtilDate:(JavaUtilDate *)date
{
    return [self formatWithId:date];
}

- (id)parseObjectWithNSString:(NSString *)source
{
    NSDate *date = [formatter dateFromString:source];
    long long int mili = (long long int)([date timeIntervalSince1970]*1000);
    return [[JavaUtilDate alloc] initWithLong:mili];
}

- (NSString *)formatWithId:(id)obj
{
    JavaUtilCalendar *cal =[JavaUtilCalendar getInstance];
    [cal setTimeWithJavaUtilDate:obj];
    NSTimeZone *zone = [formatter timeZone];
    double dstOffset =  [zone daylightSavingTimeOffset];
    dstOffset -= ((double)[cal getWithInt:JavaUtilCalendar_DST_OFFSET])/1000;
    double seconds = ((double)[cal getTimeInMillis])/1000;
    seconds += dstOffset;
    NSDate *nsDate = [NSDate dateWithTimeIntervalSince1970:seconds];
    NSString *result = [formatter stringFromDate:nsDate];
    return result;
}

@end
