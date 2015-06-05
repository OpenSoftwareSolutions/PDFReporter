//
//  OrgOssPdfreporterEngineExportJRPdfExporterParameter+NSCopying.m
//  PDFReporter-core
//
//  Created by Martin Krasnocka on 04/06/15.
//  Copyright (c) 2015 Open Software Solutions GmbH. All rights reserved.
//

#import "OrgOssPdfreporterEngineExportJRPdfExporterParameter+NSCopying.h"

@implementation OrgOssPdfreporterEngineExportJRPdfExporterParameter (NSCopying)

- (id)copyWithZone:(NSZone *)zone
{
    OrgOssPdfreporterEngineExportJRPdfExporterParameter *objectCopy = [[[self class] alloc] initWithNSString:[self description]];
    return objectCopy;
}

@end
