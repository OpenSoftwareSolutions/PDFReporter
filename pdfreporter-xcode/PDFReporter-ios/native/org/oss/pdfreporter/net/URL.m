//
//  URL.m
//  JasperReportiOS
//
//  Created by Martin Krasnočka on 5/10/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "URL.h"

@interface OrgOssPdfreporterNetURL ()
@property (nonatomic, strong) NSString *urlString;
@end

@implementation OrgOssPdfreporterNetURL

-(id)initWithNSString:(NSString *)urlString
{
    self = [super init];
    if (self) {
        self.urlString = urlString;
    }
    return self;
}

- (JavaIoInputStream *)openStream
{
    return nil;
}

-(NSString *)description
{
    return self.urlString;
}

- (NSString *)getPath
{
    return self.urlString;
}

@end
