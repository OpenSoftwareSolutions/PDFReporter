//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: android/libcore/luni/src/main/java/java/lang/ExceptionInInitializerError.java
//

#ifndef _JavaLangExceptionInInitializerError_H_
#define _JavaLangExceptionInInitializerError_H_

@class JavaLangThrowable;

#include "J2ObjC_header.h"
#include "java/lang/LinkageError.h"

#define JavaLangExceptionInInitializerError_serialVersionUID 1521711792217232256LL

@interface JavaLangExceptionInInitializerError : JavaLangLinkageError {
}

- (instancetype)init;

- (instancetype)initWithNSString:(NSString *)detailMessage;

- (instancetype)initWithJavaLangThrowable:(JavaLangThrowable *)exception;

- (JavaLangThrowable *)getException;

- (JavaLangThrowable *)getCause;

@end

J2OBJC_EMPTY_STATIC_INIT(JavaLangExceptionInInitializerError)

CF_EXTERN_C_BEGIN

J2OBJC_STATIC_FIELD_GETTER(JavaLangExceptionInInitializerError, serialVersionUID, jlong)
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(JavaLangExceptionInInitializerError)

#endif // _JavaLangExceptionInInitializerError_H_
