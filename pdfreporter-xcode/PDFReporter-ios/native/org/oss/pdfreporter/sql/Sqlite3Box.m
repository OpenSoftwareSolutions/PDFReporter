//
//  Sqlite3Box.m
//  JasperReportiOS
//
//  Created by Fr3e on 6/18/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "Sqlite3Box.h"

@implementation Sqlite3Box

- (id)initWithSqlite3:(sqlite3*)sqlite3
{
    self = [super init];
    if(self)
    {
        db = sqlite3;
    }
    return self;
}

- (sqlite3*)getSqlite3
{
    return db;
}
@end
