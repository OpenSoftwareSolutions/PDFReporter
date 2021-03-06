//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: android/frameworks/base/core/java/android/util/Base64InputStream.java
//

#ifndef _AndroidUtilBase64InputStream_H_
#define _AndroidUtilBase64InputStream_H_

@class AndroidUtilBase64_Coder;
@class IOSByteArray;
@class JavaIoInputStream;

#include "J2ObjC_header.h"
#include "java/io/FilterInputStream.h"

#define AndroidUtilBase64InputStream_BUFFER_SIZE 2048

@interface AndroidUtilBase64InputStream : JavaIoFilterInputStream {
}

- (instancetype)initWithJavaIoInputStream:(JavaIoInputStream *)inArg
                                  withInt:(jint)flags;

- (instancetype)initWithJavaIoInputStream:(JavaIoInputStream *)inArg
                                  withInt:(jint)flags
                              withBoolean:(jboolean)encode;

- (jboolean)markSupported;

- (void)markWithInt:(jint)readlimit;

- (void)reset;

- (void)close;

- (jint)available;

- (jlong)skipWithLong:(jlong)n;

- (jint)read;

- (jint)readWithByteArray:(IOSByteArray *)b
                  withInt:(jint)off
                  withInt:(jint)len;

@end

FOUNDATION_EXPORT BOOL AndroidUtilBase64InputStream_initialized;
J2OBJC_STATIC_INIT(AndroidUtilBase64InputStream)

CF_EXTERN_C_BEGIN

FOUNDATION_EXPORT IOSByteArray *AndroidUtilBase64InputStream_EMPTY_;
J2OBJC_STATIC_FIELD_GETTER(AndroidUtilBase64InputStream, EMPTY_, IOSByteArray *)
J2OBJC_STATIC_FIELD_SETTER(AndroidUtilBase64InputStream, EMPTY_, IOSByteArray *)

J2OBJC_STATIC_FIELD_GETTER(AndroidUtilBase64InputStream, BUFFER_SIZE, jint)
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(AndroidUtilBase64InputStream)

#endif // _AndroidUtilBase64InputStream_H_
