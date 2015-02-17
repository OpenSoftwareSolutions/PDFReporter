//
//  AppDelegate.m
//  PDFReporter-test
//
//  Created by Kendra on 10/1/13.
//  Copyright (c) 2014 Open Software Solutions GmbH. All rights reserved.
//

#import "AppDelegate.h"
#import "PortableTest.h"
#import "IOSExportTestProvider.h"
#import "IOSRealEstateTestProvider.h"
#include "java/util/logging/Level.h"
#include "java/util/logging/Logger.h"
#import <objc/objc-runtime.h>

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
//    [AppDelegate replaceLoggerLogImplementation];
//    [[[TestOrgOssPdfreporterPortableTest alloc] init] realestateTestWithTestOrgOssPdfreporterProvidersTestProviderInterface:[[IOSRealestateTestProvider alloc] init]];
    
    [[[TestOrgOssPdfreporterPortableTest alloc] init] exporterTestWithTestOrgOssPdfreporterProvidersTestProviderInterface:[[IOSExportTestProvider alloc] init]];
    
        
    self.window = [[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
    self.window.rootViewController = [[UIViewController alloc] init];
    self.window.backgroundColor = [UIColor whiteColor];
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
    NSLog(@"%@", msg);
}


- (void)applicationWillResignActive:(UIApplication *)application
{
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later. 
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
    // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}

- (void)applicationWillTerminate:(UIApplication *)application
{
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}

@end
