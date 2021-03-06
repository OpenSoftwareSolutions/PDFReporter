//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: android/libcore/luni/src/main/java/libcore/io/ErrnoException.java
//

#ifndef _LibcoreIoErrnoException_H_
#define _LibcoreIoErrnoException_H_

@class JavaIoIOException;
@class JavaLangThrowable;
@class JavaNetSocketException;

#include "J2ObjC_header.h"
#include "java/lang/Exception.h"

@interface LibcoreIoErrnoException : JavaLangException {
 @public
  jint errno__;
}

- (instancetype)initWithNSString:(NSString *)functionName
                         withInt:(jint)errno_;

- (instancetype)initWithNSString:(NSString *)functionName
                         withInt:(jint)errno_
           withJavaLangThrowable:(JavaLangThrowable *)cause;

- (NSString *)getMessage;

- (JavaIoIOException *)rethrowAsIOException;

- (JavaNetSocketException *)rethrowAsSocketException;

@end

J2OBJC_EMPTY_STATIC_INIT(LibcoreIoErrnoException)

CF_EXTERN_C_BEGIN
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(LibcoreIoErrnoException)

#endif // _LibcoreIoErrnoException_H_
