//
//  Connection.h
//  JasperReportiOS
//
//  Created by Fr3e on 6/18/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "org/oss/pdfreporter/sql/IConnection.h"
#import <sqlite3.h>
#import "Sqlite3Box.h"

@interface OrgOssPdfreporterSqlConnection : NSObject < OrgOssPdfreporterSqlIConnection >
{
    sqlite3 *db;
    Sqlite3Box *dbBox;
}

- (id)initWithFilename:(NSString*)filename;
- (void)execSql:(NSString*)sql;
- (BOOL)runSqlScriptFile:(NSString*)filepath;
- (BOOL)runSqlScriptFileByLine:(NSString*)filepath;
@end
