//
//  J2ObjcHelper.m
//  JasperReportiOS
//
//  Created by Martin Krasnočka on 5/3/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "InputStreamMarshaller.h"
#import "IOSPrimitiveArray.h"
#import "java/io/ByteArrayOutputStream.h"
#import "java/io/FileInputStream.h"
#import "java/io/InputStream.h"
#import "java/lang/Exception.h"
#import "NSString+JavaString.h"
@interface InputStreamMarshaller()
+(NSData *)convertJavaIoInputStreamToNSData:(JavaIoInputStream *)is;
+(NSData *)convertJavaIoReaderToNSData:(JavaIoReader *)is;
@end

@implementation InputStreamMarshaller

+(NSData *)convertJavaIoInputStreamToNSData:(JavaIoInputStream *)is
{
    int available = [is available];
    IOSByteArray *data = [IOSByteArray newArrayWithLength:available];
    int length = [is readWithByteArray:data];
    NSString *string = [NSString stringWithBytes:data offset:0 length:length];
    return [string dataUsingEncoding:NSUTF8StringEncoding];
}


+(NSData *)convertJavaIoReaderToNSData:(JavaIoReader *)is
{
    IOSCharArray *data = [IOSCharArray newArrayWithLength:16384*2];
    int length = [is readWithCharArray:data];
    NSString *string = [NSString stringWithCharacters:data offset:0 length:length];

    return [string dataUsingEncoding:NSUTF8StringEncoding];
}

+(NSData *)convertInputSourceToNSData:(id<OrgOssPdfreporterXmlParsersIInputSource>)input
{
    JavaIoInputStream *is = [input getByteStream];
    if(is) {
        return [self convertJavaIoInputStreamToNSData:is];
    } else {
        JavaIoReader *reader = [input getCharacterStream];
        if(reader) {
            return [self convertJavaIoReaderToNSData:reader];
        }
        else @throw [JavaLangException exceptionWithName:@"InputStreamMarshaller" reason:@"nil exception" userInfo:nil];
    }
}




@end
