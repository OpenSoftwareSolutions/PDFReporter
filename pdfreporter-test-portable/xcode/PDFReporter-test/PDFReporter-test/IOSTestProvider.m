//
//  IOSTestProvider.m
//  PDFReporter-test
//
//  Created by Kendra on 10/1/13.
//  Copyright (c) 2013 inloop. All rights reserved.
//

#import "IOSTestProvider.h"

@implementation IOSTestProvider

- (NSString *)inputPathWithNSString:(NSString *)input
{
    return [[[NSBundle mainBundle] bundlePath] stringByAppendingPathComponent:input];
}

- (NSString *)outputPathWithNSString:(NSString *)input
{
    return [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject];
}

- (NSString *)databasePath {
    return [self inputPathWithNSString:@"database.db"];
}
@end
