//
//  FileDownloader.h
//  PDFReporter
//
//  Created by Fr3e on 8/27/13.
//  Copyright (c) 2013 Fr3e. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "FileDownloaderProtocol.h"

@interface FileDownloader : NSObject <NSURLConnectionDataDelegate>
{
    NSURLConnection *m_connection;
    NSString *m_filepath;
    NSString *m_url;
    long long fileSize;
    long long writeSize;
    NSFileHandle *m_file;
    id<FileDownloaderProtocol> m_delegate;
    int m_statusCode;
}
+(FileDownloader*)downloadUrl:(NSString*)url toFile:(NSString*)filePath withDelegate:(id<FileDownloaderProtocol>)delegate;
@end
