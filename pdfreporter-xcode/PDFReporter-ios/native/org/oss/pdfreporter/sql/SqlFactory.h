//
//  SqlFactory.h
//  JasperReportiOS
//
//  Created by Fr3e on 6/18/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "org/oss/pdfreporter/sql/factory/ISqlFactory.h"

@interface OrgOssPdfreporterSqlSqlFactory : NSObject < OrgOssPdfreporterSqlFactoryISqlFactory >

- (id<OrgOssPdfreporterSqlIConnection>)newConnectionWithNSString:(NSString *)parameter;
+ (void)registerFactory;
void OrgOssPdfreporterSqlSqlFactory_registerFactory();
@end
