//
//  PDFReporter.m
//  PDFReporter-ios
//
//  Created by Martin Krasnocka on 04/06/15.
//  Copyright (c) 2015 inloop. All rights reserved.
//

#import "PDFReporter.h"
#import "ReportExporter.h"

@implementation PDFReporter
{
    ReporterConfiguration *_reporterConfiguration;
}

- (instancetype)initWithReporterConfiguration:(ReporterConfiguration *)reporterConfiguration
{
    self = [super init];
    if (self) {
        _reporterConfiguration = reporterConfiguration;
    }
    return self;
}

- (void)generate
{
    NSAssert(_reporterConfiguration, @"_reporterConfiguration must be set!");
    
    if (_reporterConfiguration.dataSourceType == DATASOURCE_TYPE_XML) {
        [ReportExporter exportReportToPdf:_reporterConfiguration.outputPdfFilePath withJrxml:_reporterConfiguration.jrxmlFileName withResourceFolders:_reporterConfiguration.resourceFolders withXml:_reporterConfiguration.dataSourceFileName andXPath:_reporterConfiguration.selectExpression withParameters:_reporterConfiguration.parameters withSubreports:_reporterConfiguration.subreportsDefinition];
        
    } else if (_reporterConfiguration.dataSourceType == DATASOURCE_TYPE_SQL) {
        [ReportExporter exportReportToPdf:_reporterConfiguration.outputPdfFilePath withJrxml:_reporterConfiguration.jrxmlFileName withResourceFolders:_reporterConfiguration.resourceFolders andSqlite3:_reporterConfiguration.dataSourceFileName withParameters:_reporterConfiguration.parameters withSubreports:_reporterConfiguration.subreportsDefinition];
        
    } else if (_reporterConfiguration.dataSourceType == DATASOURCE_TYPE_NONE) {
        [ReportExporter exportReportToPdf:_reporterConfiguration.outputPdfFilePath withJrxml:_reporterConfiguration.jrxmlFileName withResourceFolders:_reporterConfiguration.resourceFolders withParameters:_reporterConfiguration.parameters withSubreports:_reporterConfiguration.subreportsDefinition];
    } else {
        NSAssert(NO, @"Unsupported datasource type");
    }
}


#pragma mark - public

+ (void)exportReportWithConfigurationBlock:(ReporterConfigurationBlock)configurationBlock
{
    NSParameterAssert(configurationBlock);
    @autoreleasepool {
        ReporterConfiguration *configuration = [[ReporterConfiguration alloc] init];
        configurationBlock(configuration);
        PDFReporter *pdfReporter = [[PDFReporter alloc] initWithReporterConfiguration:configuration];
        [pdfReporter generate];
    }
    
}

@end


