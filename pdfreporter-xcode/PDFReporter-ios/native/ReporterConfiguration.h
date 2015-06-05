//
//  ReporterConfiguration.h
//  PDFReporter-ios
//
//  Created by Martin Krasnocka on 04/06/15.
//  Copyright (c) 2015 Open Software Solutions GmbH. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef NS_ENUM(NSUInteger, ReporterDataSourceType) {
    DATASOURCE_TYPE_NONE = 0,
    DATASOURCE_TYPE_XML,
    DATASOURCE_TYPE_SQL
};

@interface ReporterConfiguration : NSObject

/* DataSource type. Default is DATASOURCE_TYPE_NONE */
@property (nonatomic) ReporterDataSourceType dataSourceType;

/* Search these folders and all subfolders recursively for resource files (jrxml, datasource, fonts, properties, etc). */
@property (nonatomic, strong) NSArray *resourceFolders;

/* File name without path. Path to this file must be provided in resourceFolders property. */
@property (nonatomic, strong) NSString *jrxmlFileName;

/* File name without path. Path to this file must be provided in resourceFolders property. */
@property (nonatomic, strong) NSString *dataSourceFileName;

/* File path to output pdf file. */
@property (nonatomic, strong) NSString *outputPdfFilePath;

/* When using datasource type DATASOURCE_TYPE_XML, XPath selectExpression must be provided. */
@property (nonatomic, strong) NSString *selectExpression;

/* Reporter parameters. Key must be of a class JRPdfExporterParameter. Value class examples: java.lang.Boolean, java.lang.Integer */
@property (nonatomic, strong) NSDictionary *parameters;

/* Definition for subreports. Example key=@"ProductsSubreport", value=@"ProductReport.jasper"  */
@property (nonatomic, strong) NSDictionary *subreportsDefinition;

@end
