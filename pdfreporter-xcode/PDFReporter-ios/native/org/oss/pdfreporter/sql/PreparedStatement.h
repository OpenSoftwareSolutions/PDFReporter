//
//  PreparedStatement.h
//  JasperReportiOS
//
//  Created by Fr3e on 6/18/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "org/oss/pdfreporter/sql/IPreparedStatement.h"
#import <sqlite3.h>

@interface PreparedStatement : NSObject < OrgOssPdfreporterSqlIPreparedStatement >
{
    sqlite3_stmt *stmt;
    sqlite3 *db;
}

- (id)initWithSql:(NSString*)sql andSqlite3:(sqlite3*)sqlite3;

@end
