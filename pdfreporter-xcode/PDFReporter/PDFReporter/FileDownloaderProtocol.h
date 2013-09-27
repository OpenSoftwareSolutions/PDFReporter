//
//  FileDownloaderProtocol.h
//  PDFReporter
//
//  Created by Fr3e on 8/27/13.
//  Copyright (c) 2013 Fr3e. All rights reserved.
//

#import <Foundation/Foundation.h>

@class FileDownloader;

@protocol FileDownloaderProtocol <NSObject>

-(void)downloader:(FileDownloader*)downloader onProgressUpdate:(float)progress;
-(void)downloader:(FileDownloader*)downloader finishedDownloadingUrl:(NSString*)url toFile:(NSString*)filePath withStatus:(int)status;
-(void)downloader:(FileDownloader*)downloader failedDownloadingUrl:(NSString*)url toFile:(NSString*)filePath;
@end
