//
//  CustomJavaUtilLoggingHandler.m
//  JasperReportiOS
//
//  Created by Fr3e on 7/16/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "CustomJavaUtilLoggingHandler.h"
#import "java/util/logging/LogRecord.h"
#import "java/util/logging/Level.h"

@implementation CustomJavaUtilLoggingHandler

- (void)publishWithJavaUtilLoggingLogRecord:(JavaUtilLoggingLogRecord *)record {
    NSString *text = [NSString stringWithFormat:
                      @"%@: %@: %@", [record getLoggerName], [[record getLevel] getLocalizedName], [record getMessage]];
    printf("%s\n", [text UTF8String]);
}
@end
