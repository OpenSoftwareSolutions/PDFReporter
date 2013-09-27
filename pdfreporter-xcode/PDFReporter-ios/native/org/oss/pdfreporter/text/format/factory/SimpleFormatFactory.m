//
//  SimpleFormatFactory.m
//  JasperReportiOS
//
//  Created by Fr3e on 6/14/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "SimpleFormatFactory.h"
#import "org/oss/pdfreporter/registry/ApiRegistry.h"
#import "org/oss/pdfreporter/registry/IRegistry.h"
#import "NumberFormat.h"
#import "DateFormat.h"

@implementation OrgOssPdfreporterTextFormatFactorySimpleFormatFactory

+ (void)registerFactory {
    [OrgOssPdfreporterRegistryApiRegistry register__WithOrgOssPdfreporterTextFormatFactoryIFormatFactory_FormatTypeEnum:[OrgOssPdfreporterTextFormatFactoryIFormatFactory_FormatTypeEnum SIMPLE] withOrgOssPdfreporterTextFormatFactoryIFormatFactory:[[OrgOssPdfreporterTextFormatFactorySimpleFormatFactory alloc] init]];
}

- (id<OrgOssPdfreporterTextFormatIDateFormat>)newDateFormatWithNSString:(NSString *)pattern withJavaUtilLocale:(JavaUtilLocale *)locale
                                              withJavaUtilTimeZone:(JavaUtilTimeZone *)timezone {
    return [[OrgOssPdfreporterTextFormatDateFormat alloc] initWithPattern:pattern locale:locale timeZone:timezone];
}

- (id<OrgOssPdfreporterTextFormatINumberFormat>)newNumberFormatWithNSString:(NSString *)pattern withJavaUtilLocale:(JavaUtilLocale *)locale {
    return [[NumberFormat alloc] initWithPattern:pattern locale:locale];
}

- (id<OrgOssPdfreporterTextFormatIMessageFormat>)newMessageFormatWithNSString:(NSString *)pattern withJavaUtilLocale:(JavaUtilLocale *)locale {
    @throw [NSException exceptionWithName:@"NotImpl" reason:@"NotImp" userInfo:nil];
}

@end
