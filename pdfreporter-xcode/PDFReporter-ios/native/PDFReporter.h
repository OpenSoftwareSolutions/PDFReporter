//
//  PDFReporter.h
//  PDFReporter-ios
//
//  Created by Martin Krasnocka on 04/06/15.
//  Copyright (c) 2015 Open Software Solutions GmbH. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ReporterConfiguration.h"

typedef void (^ReporterConfigurationBlock)(ReporterConfiguration *configuration);

@interface PDFReporter : NSObject

/**
 * Export pdf file using configuration. See ReporterConfiguration for more details.
 * return - path to the generated pdf file.
 */
+ (NSString *)exportReportWithConfigurationBlock:(ReporterConfigurationBlock)configurationBlock;

@end
