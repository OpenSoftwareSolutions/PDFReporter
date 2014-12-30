//
//  FallbackFormatFactory.h
//  PDFReporter-ios
//
//  Created by Kendra on 9/24/13.
//  Copyright (c) 2014 Open Software Solutions GmbH. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "org/oss/pdfreporter/text/format/factory/IFormatFactory.h"

@interface OrgOssPdfreporterTextFormatFallbackFallbackFormatFactory : NSObject <OrgOssPdfreporterTextFormatFactoryIFormatFactory>
+(void)registerFactory;
@end
