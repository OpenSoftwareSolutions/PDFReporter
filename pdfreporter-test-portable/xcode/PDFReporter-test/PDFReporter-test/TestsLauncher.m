//
//  TestsLauncher.m
//  PDFReporter-test
//
//  Created by Martin Krasnocka on 20/02/15.
//  Copyright (c) 2015 inloop. All rights reserved.
//

#import "TestsLauncher.h"
#import "PortableTest.h"
#import "IOSExportTestProvider.h"

@implementation TestsLauncher

+ (void)runTests
{
    [[[TestOrgOssPdfreporterPortableTest alloc] init] exporterTestWithTestOrgOssPdfreporterProvidersTestProviderInterface:[[IOSExportTestProvider alloc] init]];
}

@end
