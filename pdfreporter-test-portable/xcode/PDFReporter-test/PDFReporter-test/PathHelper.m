//
//  PathHelper.m
//  PDFReporter-test
//
//  Created by Martin Krasnocka on 04/06/15.
//  Copyright (c) 2015 inloop. All rights reserved.
//

#import "PathHelper.h"

@implementation PathHelper

+ (NSString *) documentsDirectory
{
    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *basePath = ([paths count] > 0) ? [paths objectAtIndex:0] : nil;
    return basePath;
}

@end
