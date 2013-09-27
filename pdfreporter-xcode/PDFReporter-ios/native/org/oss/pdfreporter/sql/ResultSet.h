//
//  ResultSet.h
//  JasperReportiOS
//
//  Created by Fr3e on 6/19/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "org/oss/pdfreporter/sql/IResultSet.h"
#import "ResultMetaData.h"
#import <sqlite3.h>

@interface ResultSet : NSObject < OrgOssPdfreporterSqlIResultSet >
{
    ResultMetaData *metaData;
    sqlite3_stmt *mStmt;
    sqlite3 *db;
    bool next;
    int lastColumnIndex;
}

- (id)initWithStmt:(sqlite3_stmt*)stmt sqlite3:(sqlite3*)sqlite3;

@end
