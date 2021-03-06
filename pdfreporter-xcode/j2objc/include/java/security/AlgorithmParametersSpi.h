//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: android/libcore/luni/src/main/java/java/security/AlgorithmParametersSpi.java
//

#ifndef _JavaSecurityAlgorithmParametersSpi_H_
#define _JavaSecurityAlgorithmParametersSpi_H_

@class IOSByteArray;
@class IOSClass;
@protocol JavaSecuritySpecAlgorithmParameterSpec;

#include "J2ObjC_header.h"

@interface JavaSecurityAlgorithmParametersSpi : NSObject {
}

- (void)engineInitWithJavaSecuritySpecAlgorithmParameterSpec:(id<JavaSecuritySpecAlgorithmParameterSpec>)paramSpec;

- (void)engineInitWithByteArray:(IOSByteArray *)params;

- (void)engineInitWithByteArray:(IOSByteArray *)params
                   withNSString:(NSString *)format;

- (id)engineGetParameterSpecWithIOSClass:(IOSClass *)paramSpec;

- (IOSByteArray *)engineGetEncoded;

- (IOSByteArray *)engineGetEncodedWithNSString:(NSString *)format;

- (NSString *)engineToString;

- (instancetype)init;

@end

J2OBJC_EMPTY_STATIC_INIT(JavaSecurityAlgorithmParametersSpi)

CF_EXTERN_C_BEGIN
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(JavaSecurityAlgorithmParametersSpi)

#endif // _JavaSecurityAlgorithmParametersSpi_H_
