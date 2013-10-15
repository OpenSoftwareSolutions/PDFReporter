//
//  NumberFormat.m
//  JasperReportiOS
//
//  Created by Fr3e on 6/14/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "NumberFormat.h"
#import "java/util/Locale.h"
#import "java/math/BigDecimal.h"

@implementation NumberFormat

- (id)initWithPattern:(NSString*)pattern locale:(JavaUtilLocale*)locale 
{
    self = [super init];
    if(self)
    {
        formatter = [[NSNumberFormatter alloc] init];
        if (pattern != nil)
        {
            [formatter setPositiveFormat:pattern];
        }
        else if (locale != nil)
        {
            NSString *localeIdentifier = [NSString stringWithFormat:@"%@_%@", [locale getLanguage], [locale getCountry]];
            NSLocale *nsLocale = [[NSLocale alloc] initWithLocaleIdentifier:localeIdentifier];
            [formatter setLocale:nsLocale];
        }
    }
    return self;
}

- (NSNumber *)parseWithNSString:(NSString *)source
{
    return [formatter numberFromString:source];
}

- (id)parseObjectWithNSString:(NSString *)source
{
    return [formatter numberFromString:source];
}

- (NSString *)formatWithLongInt:(long long int)number
{
    return [formatter stringFromNumber:[NSNumber numberWithLongLong:number]];
}

- (NSString *)formatWithDouble:(double)number
{
    return [formatter stringFromNumber:[NSNumber numberWithDouble:number]];
}

- (NSString *)formatWithId:(id)obj
{
    if ([obj isKindOfClass:[JavaMathBigDecimal class]]) {
        return [obj description];
    } else {
        return [formatter stringFromNumber:obj];
    }
}

@end
