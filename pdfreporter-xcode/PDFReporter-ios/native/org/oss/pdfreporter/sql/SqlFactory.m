//
//  SqlFactory.m
//  JasperReportiOS
//
//  Created by Fr3e on 6/18/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "SqlFactory.h"
#import "Connection.h"
#import "org/oss/pdfreporter/sql/factory/DateTimeImpl.h"
#import "org/oss/pdfreporter/sql/factory/DateImpl.h"
#import "org/oss/pdfreporter/sql/factory/TimeImpl.h"
#import "org/oss/pdfreporter/sql/factory/TimestampImpl.h"
#import "org/oss/pdfreporter/sql/factory/BlobImpl.h"
#import "org/oss/pdfreporter/registry/ApiRegistry.h"

@implementation OrgOssPdfreporterSqlSqlFactory

+ (void)registerFactory {
    [OrgOssPdfreporterRegistryApiRegistry register__WithOrgOssPdfreporterSqlFactoryISqlFactory:[[OrgOssPdfreporterSqlSqlFactory alloc] init]];
}

- (id<OrgOssPdfreporterSqlIConnection>)newConnectionWithNSString:(NSString *)parameter
{
    return [[OrgOssPdfreporterSqlConnection alloc] initWithFilename:parameter];
}

- (id<OrgOssPdfreporterSqlIConnection>)newConnectionWithNSString:(NSString *)url withNSString:(NSString *)user withNSString:(NSString *)password
{
    return [self newConnectionWithNSString:url];
}

- (id<OrgOssPdfreporterSqlIDate>)newDateWithJavaUtilDate:(JavaUtilDate *)date
{
    return [[OrgOssPdfreporterSqlFactoryDateImpl alloc] initWithJavaUtilDate:date];
}

- (id<OrgOssPdfreporterSqlITime>)newTimeWithJavaUtilDate:(JavaUtilDate *)time
{
    return [[OrgOssPdfreporterSqlFactoryTimeImpl alloc] initWithJavaUtilDate:time];
}

- (id<OrgOssPdfreporterSqlITimestamp>)newTimestampWithLongInt:(long long int)milliseconds
{
    return [[OrgOssPdfreporterSqlFactoryTimestampImpl alloc] initWithLong:milliseconds];
}

- (id<OrgOssPdfreporterSqlIDateTime>)newDateTimeWithJavaUtilDate:(JavaUtilDate *)datetime
{
    return [[OrgOssPdfreporterSqlFactoryDateTimeImpl alloc] initWithJavaUtilDate:datetime];
}

- (id<OrgOssPdfreporterSqlIBlob>)newBlobWithJavaIoInputStream:(JavaIoInputStream *)is
{
    return [[OrgOssPdfreporterSqlFactoryBlobImpl alloc] initWithJavaIoInputStream:is];
}

- (id<OrgOssPdfreporterSqlIBlob>)newBlobWithByteArray:(IOSByteArray *)bytes
{
    return [[OrgOssPdfreporterSqlFactoryBlobImpl alloc] initWithByteArray:bytes];
}

- (id<OrgOssPdfreporterSqlITimestamp>)newTimestampWithLong:(jlong)milliseconds
{
    return [[OrgOssPdfreporterSqlFactoryTimestampImpl alloc] initWithLong:milliseconds];
}


void OrgOssPdfreporterSqlSqlFactory_registerFactory()
{
    [OrgOssPdfreporterSqlSqlFactory registerFactory];
}

@end
