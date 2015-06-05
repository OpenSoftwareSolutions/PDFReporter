//
//  ReporterConfiguration.m
//  PDFReporter-ios
//
//  Created by Martin Krasnocka on 04/06/15.
//  Copyright (c) 2015 Open Software Solutions GmbH. All rights reserved.
//

#import "ReporterConfiguration.h"
#include "org/oss/pdfreporter/engine/export/JRPdfExporterParameter.h"
#include "java/lang/Boolean.h"
#include "java/lang/Integer.h"
#include "org/oss/pdfreporter/pdf/IDocument.h"


@implementation ReporterConfiguration

- (instancetype)init
{
    self = [super init];
    if (self) {
        _dataSourceType = DATASOURCE_TYPE_NONE;
    }
    return self;
}

- (void)setXmlSource:(NSString *)xmlDataFile selectExpression:(NSString *)xmlXpath
{
    _dataSourceType = DATASOURCE_TYPE_XML;
    _dataSourceFileName = xmlDataFile;
    _selectExpression = xmlXpath;
}

- (void)setSqlSource:(NSString *)databasePath
{
    _dataSourceType = DATASOURCE_TYPE_SQL;
    _dataSourceFileName = databasePath;
}

- (void)addSubreport:(NSString *)subreportName location:(NSString *)location
{
    if (!_subreportsDefinition) {
        _subreportsDefinition = [NSMutableDictionary dictionary];
    }
    [(NSMutableDictionary *)_subreportsDefinition setObject:location forKey:subreportName];
}

- (void)addExportParameterValue:(id)value forKey:(OrgOssPdfreporterEngineExportJRPdfExporterParameter *)key
{
    if (!_exportParameters) {
        _exportParameters = [NSMutableDictionary dictionary];
    }
    NSMutableDictionary *exportParameters = (NSMutableDictionary *)_exportParameters;
    [exportParameters setObject:value forKey:(id<NSCopying>)key];
}

- (void)addEncryptionIs128bitKey:(BOOL)is128bitKey userPassword:(NSString *)userPassword ownerPassword:(NSString *)ownerPassword permissions:(int)permissions
{
    [self addExportParameterValue:JavaLangBoolean_get_TRUE__() forKey:OrgOssPdfreporterEngineExportJRPdfExporterParameter_get_IS_ENCRYPTED_()];
    [self addExportParameterValue:is128bitKey ? JavaLangBoolean_get_TRUE__() : JavaLangBoolean_get_FALSE__() forKey:OrgOssPdfreporterEngineExportJRPdfExporterParameter_get_IS_128_BIT_KEY_()];
    [self addExportParameterValue:@"jasper" forKey:OrgOssPdfreporterEngineExportJRPdfExporterParameter_get_USER_PASSWORD_()];
    [self addExportParameterValue:@"reports" forKey:OrgOssPdfreporterEngineExportJRPdfExporterParameter_get_OWNER_PASSWORD_()];
    [self addExportParameterValue:JavaLangInteger_valueOfWithInt_(permissions) forKey:OrgOssPdfreporterEngineExportJRPdfExporterParameter_get_PERMISSIONS_()];
}


@end
