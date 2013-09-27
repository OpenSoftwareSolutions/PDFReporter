//
//  FallbackFormatFactory.m
//  PDFReporter-ios
//
//  Created by Kendra on 9/24/13.
//  Copyright (c) 2013 inloop. All rights reserved.
//

#import "FallbackFormatFactory.h"
#import "org/oss/pdfreporter/registry/ApiRegistry.h"
#import "FallbackFormater.h"

@implementation OrgOssPdfreporterTextFormatFallbackFallbackFormatFactory

+(void)registerFactory
{
    [OrgOssPdfreporterRegistryApiRegistry register__WithOrgOssPdfreporterTextFormatFactoryIFormatFactory_FormatTypeEnum:[OrgOssPdfreporterTextFormatFactoryIFormatFactory_FormatTypeEnum STANDARD] withOrgOssPdfreporterTextFormatFactoryIFormatFactory: [[OrgOssPdfreporterTextFormatFallbackFallbackFormatFactory alloc] init]];
}

- (id<OrgOssPdfreporterTextFormatIDateFormat>)newDateFormatWithNSString:(NSString *)pattern
                                                     withJavaUtilLocale:(JavaUtilLocale *)locale
                                                   withJavaUtilTimeZone:(JavaUtilTimeZone *)timezone
{
    return [[FallbackFormater alloc] initWithPattern:pattern];
}

- (id<OrgOssPdfreporterTextFormatINumberFormat>)newNumberFormatWithNSString:(NSString *)pattern
                                                         withJavaUtilLocale:(JavaUtilLocale *)locale
{
    return [[FallbackFormater alloc] initWithPattern:pattern];
}

- (id<OrgOssPdfreporterTextFormatIMessageFormat>)newMessageFormatWithNSString:(NSString *)pattern
                                                           withJavaUtilLocale:(JavaUtilLocale *)locale
{
    return [[FallbackFormater alloc] initWithPattern:pattern];
}
@end
