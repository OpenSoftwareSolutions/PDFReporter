//
//  OrgOssPdfreporterUtilXmlXalanXPathExecuter.m
//  PDFReporter-ios
//
//  Created by Martin Krasnocka on 15/12/14.
//  Copyright (c) 2014 Open Software Solutions GmbH. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "XalanXPathExecuterFactory.h"
#import "XalanXPathExecuter.h"

@implementation OrgOssPdfreporterEngineUtilXmlXalanXPathExecuterFactory

- (id<OrgOssPdfreporterEngineUtilXmlJRXPathExecuter>)getXPathExecuter
{
    return [[XalanXPathExecuter alloc] init];
}

@end
