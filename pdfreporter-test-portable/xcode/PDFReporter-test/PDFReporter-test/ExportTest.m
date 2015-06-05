//
//  ExportTest.m
//  PDFReporter-test
//
//  Created by Martin Krasnocka on 04/06/15.
//  Copyright (c) 2015 Open Software Solutions GmbH. All rights reserved.
//

#import "ExportTest.h"
#import "PathHelper.h"
#import "PDFReporter.h"
#include "org/oss/pdfreporter/pdf/IDocument.h"

@implementation ExportTest

+ (void)runTests
{
    // paths setup
    NSString *documentsDir = [PathHelper documentsDirectory];
    NSArray *paths = @[@"datasource", @"resource", @"testdata", @""];
    NSMutableArray *resourceFolders = [NSMutableArray array];
    [paths enumerateObjectsUsingBlock:^(id obj, NSUInteger idx, BOOL *stop) {
        [resourceFolders addObject:[[[NSBundle mainBundle] bundlePath] stringByAppendingPathComponent:obj]];
    }];
    
    // CDBooklet
    [PDFReporter exportReportWithConfigurationBlock:^(ReporterConfiguration *configuration) {
        configuration.resourceFolders = resourceFolders;
        configuration.outputPdfFilePath = [documentsDir stringByAppendingPathComponent:@"CDBooklet.pdf"];
        configuration.jrxmlFileName = @"CDBooklet.jrxml";
        [configuration setXmlSource:@"CDBooklets.xml" selectExpression:@"/CDBooklets"];
    }];
    
    // Fonts
    [PDFReporter exportReportWithConfigurationBlock:^(ReporterConfiguration *configuration) {
        configuration.resourceFolders = resourceFolders;
        configuration.jrxmlFileName = @"FontsReport.jrxml";
        configuration.outputPdfFilePath = [documentsDir stringByAppendingPathComponent:@"FontsReport.pdf"];
    }];

    // ShipmentsReport
    [PDFReporter exportReportWithConfigurationBlock:^(ReporterConfiguration *configuration) {
        configuration.resourceFolders = resourceFolders;
        configuration.jrxmlFileName = @"ShipmentsReport.jrxml";
        [configuration setSqlSource:@"database.db"];
        configuration.outputPdfFilePath = [documentsDir stringByAppendingPathComponent:@"ShipmentsReport.pdf"];
    }];
    
    // MasterReport
    [PDFReporter exportReportWithConfigurationBlock:^(ReporterConfiguration *configuration) {
        configuration.resourceFolders = resourceFolders;
        configuration.jrxmlFileName = @"MasterReport.jrxml";
        [configuration setSqlSource:@"database.db"];
        configuration.outputPdfFilePath = [documentsDir stringByAppendingPathComponent:@"MasterReport.pdf"];
        [configuration addSubreport:@"ProductsSubreport" location:@"ProductReport.jasper"];
    }];
    
    // ProductsReport
    [PDFReporter exportReportWithConfigurationBlock:^(ReporterConfiguration *configuration) {
        configuration.resourceFolders = resourceFolders;
        configuration.jrxmlFileName = @"ProductsReport.jrxml";
        [configuration setSqlSource:@"database.db"];
        configuration.outputPdfFilePath = [documentsDir stringByAppendingPathComponent:@"ProductsReport.pdf"];
    }];

    // StretchReport
    [PDFReporter exportReportWithConfigurationBlock:^(ReporterConfiguration *configuration) {
        configuration.resourceFolders = resourceFolders;
        configuration.jrxmlFileName = @"StretchReport.jrxml";
        configuration.outputPdfFilePath = [documentsDir stringByAppendingPathComponent:@"StretchReport.pdf"];
    }];
    
    // TabularReport
    [PDFReporter exportReportWithConfigurationBlock:^(ReporterConfiguration *configuration) {
        configuration.resourceFolders = resourceFolders;
        configuration.jrxmlFileName = @"TabularReport.jrxml";
        configuration.outputPdfFilePath = [documentsDir stringByAppendingPathComponent:@"TabularReport.pdf"];
    }];

    // OrdersReport
    [PDFReporter exportReportWithConfigurationBlock:^(ReporterConfiguration *configuration) {
        configuration.resourceFolders = resourceFolders;
        configuration.jrxmlFileName = @"OrdersReport.jrxml";
        [configuration setSqlSource:@"database.db"];
        configuration.outputPdfFilePath = [documentsDir stringByAppendingPathComponent:@"OrdersReport.pdf"];
    }];
    
#warning not working
    // LateOrdersReport
//    [PDFReporter exportReportWithConfigurationBlock:^(ReporterConfiguration *configuration) {
//        configuration.resourceFolders = resourceFolders;
//        configuration.jrxmlFileName = @"LateOrdersReport.jrxml";
//        [configuration setSqlSource:@"database.db"];
//        configuration.outputPdfFilePath = [documentsDir stringByAppendingPathComponent:@"LateOrdersReport.pdf"];
//    }];

    // ImagesReport
    [PDFReporter exportReportWithConfigurationBlock:^(ReporterConfiguration *configuration) {
        configuration.resourceFolders = resourceFolders;
        configuration.jrxmlFileName = @"ImagesReport.jrxml";
        configuration.outputPdfFilePath = [documentsDir stringByAppendingPathComponent:@"ImagesReport.pdf"];
    }];
    
    // LandscapeReport
    [PDFReporter exportReportWithConfigurationBlock:^(ReporterConfiguration *configuration) {
        configuration.resourceFolders = resourceFolders;
        configuration.jrxmlFileName = @"LandscapeReport.jrxml";
        configuration.outputPdfFilePath = [documentsDir stringByAppendingPathComponent:@"LandscapeReport.pdf"];
    }];
    
    // ShapesReport
    [PDFReporter exportReportWithConfigurationBlock:^(ReporterConfiguration *configuration) {
        configuration.resourceFolders = resourceFolders;
        configuration.jrxmlFileName = @"ShapesReport.jrxml";
        configuration.outputPdfFilePath = [documentsDir stringByAppendingPathComponent:@"ShapesReport.pdf"];
    }];
    
    // RotationReport
    [PDFReporter exportReportWithConfigurationBlock:^(ReporterConfiguration *configuration) {
        configuration.resourceFolders = resourceFolders;
        configuration.jrxmlFileName = @"RotationReport.jrxml";
        configuration.outputPdfFilePath = [documentsDir stringByAppendingPathComponent:@"RotationReport.pdf"];
    }];

    // PdfEncryptReport
    [PDFReporter exportReportWithConfigurationBlock:^(ReporterConfiguration *configuration) {
        configuration.resourceFolders = resourceFolders;
        configuration.jrxmlFileName = @"PdfEncryptReport.jrxml";
        configuration.outputPdfFilePath = [documentsDir stringByAppendingPathComponent:@"PdfEncryptReport.pdf"];
        [configuration addEncryptionIs128bitKey:YES userPassword:@"jasper" ownerPassword:@"reports" permissions:OrgOssPdfreporterPdfIDocument_PERMISSION_COPY | OrgOssPdfreporterPdfIDocument_PERMISSION_PRINT];
    }];
    
    // ParagraphsReport
    [PDFReporter exportReportWithConfigurationBlock:^(ReporterConfiguration *configuration) {
        configuration.resourceFolders = resourceFolders;
        configuration.jrxmlFileName = @"ParagraphsReport.jrxml";
        configuration.outputPdfFilePath = [documentsDir stringByAppendingPathComponent:@"ParagraphsReport.pdf"];
    }];

    // StyledTextReport
    [PDFReporter exportReportWithConfigurationBlock:^(ReporterConfiguration *configuration) {
        configuration.resourceFolders = resourceFolders;
        configuration.jrxmlFileName = @"StyledTextReport.jrxml";
        configuration.outputPdfFilePath = [documentsDir stringByAppendingPathComponent:@"StyledTextReport.pdf"];
    }];
}

@end
