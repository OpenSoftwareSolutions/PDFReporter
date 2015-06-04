//
//  AppDelegate.m
//  PDFReporter-test
//
//  Created by Kendra on 10/1/13.
//  Copyright (c) 2014 Open Software Solutions GmbH. All rights reserved.
//

#import "AppDelegate.h"
#import <objc/objc-runtime.h>
#include "java/util/logging/Logger.h"

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    [AppDelegate replaceLoggerLogImplementation];
    [self.window makeKeyAndVisible];
    return YES;
}

+ (void)replaceLoggerLogImplementation {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        Class class = [JavaUtilLoggingLogger class];
        
        SEL originalSelector = @selector(logWithJavaUtilLoggingLevel:withNSString:);
        SEL swizzledSelector = @selector(logWithJavaUtilLoggingLevel:withNSString:);
        
        Method originalMethod = class_getInstanceMethod(class, originalSelector);
        Method swizzledMethod = class_getInstanceMethod([self class], swizzledSelector);
        
        BOOL didAddMethod =
        class_addMethod(class,
                        originalSelector,
                        method_getImplementation(swizzledMethod),
                        method_getTypeEncoding(swizzledMethod));
        
        if (didAddMethod) {
            class_replaceMethod(class,
                                swizzledSelector,
                                method_getImplementation(originalMethod),
                                method_getTypeEncoding(originalMethod));
        } else {
            method_exchangeImplementations(originalMethod, swizzledMethod);
        }
    });
}

- (void)logWithJavaUtilLoggingLevel:(JavaUtilLoggingLevel *)logLevel
                       withNSString:(NSString *)msg
{
//    NSLog(@"%@", msg);
}


@end
