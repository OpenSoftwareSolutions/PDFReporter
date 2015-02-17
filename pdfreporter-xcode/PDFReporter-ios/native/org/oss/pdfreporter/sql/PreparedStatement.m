//
//  PreparedStatement.m
//  JasperReportiOS
//
//  Created by Fr3e on 6/18/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "PreparedStatement.h"
#import "org/oss/pdfreporter/sql/SQLException.h"
#import "ResultSet.h"
#import "org/oss/pdfreporter/sql/factory/DateTimeImpl.h"
#import "org/oss/pdfreporter/sql/factory/TimestampImpl.h"
#import "org/oss/pdfreporter/sql/factory/BlobImpl.h"
#import "IOSPrimitiveArray.h"

@implementation PreparedStatement

- (id)initWithSql:(NSString*)sql andSqlite3:(sqlite3*)sqlite3
{
    self = [super init];
    if(self)
    {
        db = sqlite3;
        int result = sqlite3_prepare_v2(db, [sql UTF8String], -1, &stmt, NULL);
        if(result != SQLITE_OK)
        {
            NSString *message = [NSString stringWithUTF8String:sqlite3_errmsg(db)];
            @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:message];
        }

    }
    return self;
}

- (id<OrgOssPdfreporterSqlIResultSet>)executeQuery
{
    int result = sqlite3_reset(stmt);
    if(result != SQLITE_OK)
    {
        NSString *message = [NSString stringWithUTF8String:sqlite3_errmsg(db)];
        @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:message];
    }
    return [[ResultSet alloc] initWithStmt:stmt sqlite3:db];
}

- (void)setBlobWithInt:(int)columnIndex withOrgOssPdfreporterSqlIBlob:(id<OrgOssPdfreporterSqlIBlob>)value
{
    IOSByteArray *byteArray = [value getBytes];
    
    int count = [byteArray length];
    char *array = malloc(count);
    
    [byteArray getBytes:array offset:0 length:count];
    
    int result = sqlite3_bind_blob(stmt, columnIndex, array, count, SQLITE_TRANSIENT);
    free(array);
    
    if(result != SQLITE_OK)
    {
        NSString *message = [NSString stringWithUTF8String:sqlite3_errmsg(db)];
        @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:message];
    }
}

- (void)setBooleanWithInt:(int)columnIndex withBoolean:(BOOL)value
{
    int result = sqlite3_bind_int(stmt, columnIndex, value);
    if(result != SQLITE_OK)
    {
        NSString *message = [NSString stringWithUTF8String:sqlite3_errmsg(db)];
        @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:message];
    }
}

- (void)setByteWithInt:(int)columnIndex withChar:(char)value
{
    int result = sqlite3_bind_int(stmt, columnIndex, value);
    if(result != SQLITE_OK)
    {
        NSString *message = [NSString stringWithUTF8String:sqlite3_errmsg(db)];
        @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:message];
    }
}

- (void)setDateWithInt:(int)columnIndex withOrgOssPdfreporterSqlIDate:(id<OrgOssPdfreporterSqlIDate>)value
{
    char text[50];
    sprintf(text, "%.4d-%.2d-%.2d 00:00:00.000", [value getYear], [value getMonth], [value getDay]);
    int result = sqlite3_bind_text(stmt, columnIndex, text, -1, SQLITE_TRANSIENT);
    if(result != SQLITE_OK)
    {
        NSString *message = [NSString stringWithUTF8String:sqlite3_errmsg(db)];
        @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:message];
    }
}

- (void)setDateTimeWithInt:(int)columnIndex withOrgOssPdfreporterSqlIDateTime:(id<OrgOssPdfreporterSqlIDateTime>)value
{
    char text[50];
    sprintf(text, "%.4d-%.2d-%.2d %.2d:%.2d:%.2d.000", [value getYear], [value getMonth], [value getDay], [value getHours], [value getMinutes], [value getSeconds]);
    int result = sqlite3_bind_text(stmt, columnIndex, text, -1, SQLITE_TRANSIENT);
    if(result != SQLITE_OK)
    {
        NSString *message = [NSString stringWithUTF8String:sqlite3_errmsg(db)];
        @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:message];
    }
}

- (void)setDecimalWithInt:(int)columnIndex withJavaMathBigDecimal:(JavaMathBigDecimal *)value
{
    @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:@"Not implemented"];
}

- (void)setDoubleWithInt:(int)columnIndex withDouble:(double)value
{
    int result = sqlite3_bind_double(stmt, columnIndex, value);
    if(result != SQLITE_OK)
    {
        NSString *message = [NSString stringWithUTF8String:sqlite3_errmsg(db)];
        @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:message];
    }
}

- (void)setFloatWithInt:(int)columnIndex withFloat:(float)value
{
    int result = sqlite3_bind_double(stmt, columnIndex, value);
    if(result != SQLITE_OK)
    {
        NSString *message = [NSString stringWithUTF8String:sqlite3_errmsg(db)];
        @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:message];
    }
}

- (void)setIntWithInt:(int)columnIndex withInt:(int)value
{
    int result = sqlite3_bind_int(stmt, columnIndex, value);
    if(result != SQLITE_OK)
    {
        NSString *message = [NSString stringWithUTF8String:sqlite3_errmsg(db)];
        @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:message];
    }
}

- (void)setLongWithInt:(int)columnIndex withLongInt:(long long int)value
{
    int result = sqlite3_bind_int64(stmt, columnIndex, value);
    if(result != SQLITE_OK)
    {
        NSString *message = [NSString stringWithUTF8String:sqlite3_errmsg(db)];
        @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:message];
    }
}

- (void)setObjectWithInt:(int)columnIndex withId:(id)value
{
    @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:@"Not implemented"];
}

- (void)setShortWithInt:(int)columnIndex withShortInt:(short int)value
{
    int result = sqlite3_bind_int(stmt, columnIndex, value);
    if(result != SQLITE_OK)
    {
        NSString *message = [NSString stringWithUTF8String:sqlite3_errmsg(db)];
        @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:message];
    }
}

- (void)setStringWithInt:(int)columnIndex withNSString:(NSString *)value
{
    int result = sqlite3_bind_text(stmt, columnIndex, [value UTF8String], -1, SQLITE_TRANSIENT);
    if(result != SQLITE_OK)
    {
        NSString *message = [NSString stringWithUTF8String:sqlite3_errmsg(db)];
        @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:message];
    }
}

- (void)setTimeWithInt:(int)columnIndex withOrgOssPdfreporterSqlITime:(id<OrgOssPdfreporterSqlITime>)value
{
    char text[50];
    sprintf(text, "%.4d-%.2d-%.2d %.2d:%.2d:%.2d", 0, 1, 1, [value getHours], [value getMinutes], [value getSeconds]);
    int result = sqlite3_bind_text(stmt, columnIndex, text, -1, SQLITE_TRANSIENT);
    if(result != SQLITE_OK)
    {
        NSString *message = [NSString stringWithUTF8String:sqlite3_errmsg(db)];
        @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:message];
    }
}

- (void)setTimestampWithInt:(int)columnIndex withOrgOssPdfreporterSqlITimestamp:(id<OrgOssPdfreporterSqlITimestamp>)value
{
    int result = sqlite3_bind_int(stmt, columnIndex, [value getMilliseconds]);
    if(result != SQLITE_OK)
    {
        NSString *message = [NSString stringWithUTF8String:sqlite3_errmsg(db)];
        @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:message];
    }
}

- (void)setNullWithInt:(int)columnIndex withOrgOssPdfreporterSqlSqlTypeEnum:(OrgOssPdfreporterSqlSqlTypeEnum *)type
{
    int result = sqlite3_bind_null(stmt, columnIndex);
    if(result != SQLITE_OK)
    {
        NSString *message = [NSString stringWithUTF8String:sqlite3_errmsg(db)];
        @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:message];
    }
}

- (void)close
{
    if(stmt != NULL)
    {
        sqlite3_finalize(stmt);
        stmt = NULL;
    }
    
}

- (void)cancel
{
    
}

@end
