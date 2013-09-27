//
//  URL.h
//  JasperReportiOS
//
//  Created by Martin Krasnočka on 5/10/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "org/oss/pdfreporter/net/IURL.h"

@interface OrgOssPdfreporterNetURL : NSObject <OrgOssPdfreporterNetIURL>
-(id)initWithNSString:(NSString *)urlString;
@end
