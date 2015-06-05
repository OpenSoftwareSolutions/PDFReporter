//
//  ReporterConfiguration.h
//  PDFReporter-ios
//
//  Created by Martin Krasnocka on 04/06/15.
//  Copyright (c) 2015 Open Software Solutions GmbH. All rights reserved.
//

#import <Foundation/Foundation.h>
@class OrgOssPdfreporterEngineExportJRPdfExporterParameter;

typedef NS_ENUM(NSUInteger, ReporterDataSourceType) {
    DATASOURCE_TYPE_NONE = 0,
    DATASOURCE_TYPE_XML,
    DATASOURCE_TYPE_SQL
};


@interface ReporterConfiguration : NSObject

/** Search these folders and all subfolders recursively for resource files (jrxml, datasource, fonts, properties, etc). */
@property (nonatomic, strong) NSArray *resourceFolders;

/** File name without path. Path to this file must be provided in resourceFolders property. */
@property (nonatomic, strong) NSString *jrxmlFileName;

/** File path to output pdf file. */
@property (nonatomic, strong) NSString *outputPdfFilePath;


/* Readonly properties */
@property (nonatomic, readonly) ReporterDataSourceType dataSourceType;
@property (nonatomic, strong, readonly) NSString *dataSourceFileName;
@property (nonatomic, strong, readonly) NSString *selectExpression;
@property (nonatomic, strong, readonly) NSDictionary *subreportsDefinition;
@property (nonatomic, strong, readonly) NSDictionary *exportParameters;

/**
 * xmlDataFile - File name without path. Path to this file must be provided in resourceFolders property.
 * xmlXpath - When using xml datasource, XPath select expression must be provided.
 */
- (void)setXmlSource:(NSString *)xmlDataFile selectExpression:(NSString *)xmlXpath;

/**
 * databasePath - Absolute path to a database file, or file name without path. If only file name is specified, path to this file must be provided in resourceFolders property.
 */
- (void)setSqlSource:(NSString *)databasePath;

/**
 * Add Definition for subreports. Example subreportName=@"ProductsSubreport", location=@"ProductReport.jasper"
 */
- (void)addSubreport:(NSString *)subreportName location:(NSString *)location;

/**
 * Add encryption for output pdf file.
 */
- (void)addEncryptionIs128bitKey:(BOOL)is128bitKey userPassword:(NSString *)userPassword ownerPassword:(NSString *)ownerPassword permissions:(int)permissions;

/**
 * Reporter parameters. Key must be of a class JRPdfExporterParameter. Value class examples: java.lang.Boolean, java.lang.Integer
 */
- (void)addExportParameterValue:(id)value forKey:(OrgOssPdfreporterEngineExportJRPdfExporterParameter *)key;


@end
