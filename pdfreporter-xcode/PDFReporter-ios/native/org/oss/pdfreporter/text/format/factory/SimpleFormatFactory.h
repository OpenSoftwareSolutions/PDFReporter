//
//  SimpleFormatFactory.h
//  JasperReportiOS
//
//  Created by Fr3e on 6/14/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "org/oss/pdfreporter/text/format/factory/IFormatFactory.h"

@interface OrgOssPdfreporterTextFormatFactorySimpleFormatFactory : NSObject < OrgOssPdfreporterTextFormatFactoryIFormatFactory >
+ (void)registerFactory;
@end
