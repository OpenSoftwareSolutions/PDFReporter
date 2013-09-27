//
//  UpdateHelper.h
//  PDFReporter
//
//  Created by Fr3e on 8/21/13.
//  Copyright (c) 2013 Fr3e. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface UpdateHelper : NSObject
+(BOOL)updateReportsWithZip:(NSString*)zipPath;
+(BOOL)unzipFile:(NSString*)zipPath toDirectory:(NSString*)dirPath;
+(NSArray*)getAllPLists;
+(void)runReportFromPListAtPosition:(int)pos;
+(void)initializeReportsDirectory;

+(void)runPhase1ReportFromPListAtPosition:(int)pos;
+(void)runPhase2ReportFromPListAtPosition:(int)pos;

+(NSString*) downloadUrl;
@end
