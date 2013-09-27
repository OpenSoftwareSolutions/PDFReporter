//
//  Connection.m
//  JasperReportiOS
//
//  Created by Fr3e on 6/18/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "Connection.h"
#import "org/oss/pdfreporter/sql/SQLException.h"
#import "PreparedStatement.h"

@implementation OrgOssPdfreporterSqlConnection

- (id)initWithFilename:(NSString*)filename
{
    self = [super init];
    if(self)
    {
        int result;
        const char * cFilename = [filename UTF8String];
        result = sqlite3_open(cFilename, &db);
        if(result != SQLITE_OK)
        {
            NSString *message = [NSString stringWithUTF8String:sqlite3_errmsg(db)];
            sqlite3_close(db);
            @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:message];
        }
        dbBox = [[Sqlite3Box alloc] initWithSqlite3:db];
    }
    return self;
}

- (id)getPeer
{
    return dbBox;
}

- (id<OrgOssPdfreporterSqlIPreparedStatement>)prepareStatementWithNSString:(NSString *)query
{
    return [[PreparedStatement alloc] initWithSql:query andSqlite3:db];
}

- (void)execSql:(NSString*)sql
{
    int result = sqlite3_exec(db, [sql UTF8String], NULL, NULL, NULL);
    if(result != SQLITE_OK)
    {
        @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:[NSString stringWithCString:sqlite3_errmsg(db) encoding:NSUTF8StringEncoding]];
    }
}

- (BOOL)runSqlScriptFile:(NSString*)filepath
{
    NSString* fileContents = [NSString stringWithContentsOfFile:filepath encoding:NSUTF8StringEncoding error:nil];
    if(fileContents == nil) return NO;
    [self execSql:@"BEGIN TRANSACTION"];
    @try {
        [self execSql:fileContents];
        [self execSql:@"COMMIT TRANSACTION"];
        return YES;
    }
    @catch (OrgOssPdfreporterSqlSQLException *exception) {
        [self execSql:@"ROLLBACK TRANSACTION"];
        @throw exception;
    }
}

- (BOOL)runSqlScriptFileByLine:(NSString*)filepath
{
    NSString* fileContents = [NSString stringWithContentsOfFile:filepath encoding:NSUTF8StringEncoding error:nil];
    if(fileContents == nil) return NO;
    
    NSArray* allLinedStrings = [fileContents componentsSeparatedByCharactersInSet: [NSCharacterSet newlineCharacterSet]];
    
    [self execSql:@"BEGIN TRANSACTION"];
    @try {
        for(NSString *line in allLinedStrings)
        {
            [self execSql:line];
        }
        [self execSql:@"COMMIT TRANSACTION"];
        return YES;
    }
    @catch (OrgOssPdfreporterSqlSQLException *exception) {
        [self execSql:@"ROLLBACK TRANSACTION"];
        @throw [[OrgOssPdfreporterSqlSQLException alloc] initWithNSString:[NSString stringWithUTF8String:sqlite3_errmsg(db)]];

    }  
}

- (void)close
{
    if(db != nil)
    {
        sqlite3_close(db);
        db = nil;
    }
}

@end
