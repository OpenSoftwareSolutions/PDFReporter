//
//  Attributes.h
//  JasperReportiOS
//
//  Created by Martin Krasnočka on 5/8/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "org/oss/pdfreporter/xml/parsers/IAttributes.h"

@interface Attributes : NSObject <OrgOssPdfreporterXmlParsersIAttributes>
-(id)initWithDictionary:(NSDictionary *)dict;
@end
