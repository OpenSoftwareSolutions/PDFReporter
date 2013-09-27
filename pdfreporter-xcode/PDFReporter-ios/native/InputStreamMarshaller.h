//
//  J2ObjcHelper.h
//  JasperReportiOS
//
//  Created by Martin Krasnočka on 5/3/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "java/io/InputStream.h"
#import "java/io/Reader.h"
#import "org/oss/pdfreporter/xml/parsers/IInputSource.h"

@interface InputStreamMarshaller : NSObject
+(NSData *)convertInputSourceToNSData:(id<OrgOssPdfreporterXmlParsersIInputSource>)input;
@end
