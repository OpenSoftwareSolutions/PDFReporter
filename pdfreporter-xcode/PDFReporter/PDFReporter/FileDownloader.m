//
//  FileDownloader.m
//  PDFReporter
//
//  Created by Fr3e on 8/27/13.
//  Copyright (c) 2013 Fr3e. All rights reserved.
//

#import "FileDownloader.h"

@interface FileDownloader ()
-(id)init;
-(FileDownloader*)_downloadUrl:(NSString*)url toFile:(NSString*)filePath withDelegate:(id<FileDownloaderProtocol>)delegate;
@end

@implementation FileDownloader

- (id)init
{
    self = [super init];
    if (self) {
    }
    return self;
}

+(FileDownloader*)downloadUrl:(NSString*)url toFile:(NSString*)filePath withDelegate:(id<FileDownloaderProtocol>)delegate
{
    return [[[FileDownloader alloc] init] _downloadUrl:url toFile:filePath withDelegate:delegate];
}

-(FileDownloader*)_downloadUrl:(NSString*)url toFile:(NSString*)filePath withDelegate:(id<FileDownloaderProtocol>)delegate
{
    m_delegate = delegate;
    m_filepath = [NSString stringWithString:filePath];
    m_url = [NSString stringWithString:url];;
    NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL URLWithString:url]];
    m_connection = [[NSURLConnection alloc] initWithRequest:request delegate:self];
    return self;
}

#pragma mark Connection Delegate

-(void)connection:(NSURLConnection *)connection didReceiveResponse:(NSHTTPURLResponse *)response
{
    m_statusCode = [response statusCode];
    fileSize = [response expectedContentLength];
    writeSize = 0;
    
    [[NSFileManager defaultManager] createFileAtPath:m_filepath contents:nil attributes:nil];
    m_file = [NSFileHandle fileHandleForWritingAtPath:m_filepath];
}

-(void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data
{
    [m_file writeData:data];
    writeSize += [data length];
    [m_delegate downloader:self onProgressUpdate:(float)writeSize/(float)fileSize];
}

-(void)connectionDidFinishLoading:(NSURLConnection *)connection
{
    [m_file closeFile];
    [m_delegate downloader:self finishedDownloadingUrl:m_url toFile:m_filepath withStatus:m_statusCode];
}

-(void)connection:(NSURLConnection *)connection didFailWithError:(NSError *)error
{
    [m_file closeFile];
    [m_delegate downloader:self failedDownloadingUrl:m_url toFile:m_filepath];
}

-(NSCachedURLResponse *)connection:(NSURLConnection *)connection willCacheResponse:(NSCachedURLResponse *)cachedResponse
{
    return nil;
}


@end
