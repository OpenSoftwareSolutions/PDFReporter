//
//  Sqlite3Box.h
//  JasperReportiOS
//
//  Created by Fr3e on 6/18/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <sqlite3.h>

@interface Sqlite3Box : NSObject
{
    sqlite3 *db;
}

- (id)initWithSqlite3:(sqlite3*)sqlite3;
- (sqlite3*)getSqlite3;

@end
