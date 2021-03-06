//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: android/libcore/luni/src/main/java/java/sql/SQLSyntaxErrorException.java
//

#ifndef _JavaSqlSQLSyntaxErrorException_H_
#define _JavaSqlSQLSyntaxErrorException_H_

@class JavaLangThrowable;

#include "J2ObjC_header.h"
#include "java/sql/SQLNonTransientException.h"

#define JavaSqlSQLSyntaxErrorException_serialVersionUID -1843832610477496053LL

@interface JavaSqlSQLSyntaxErrorException : JavaSqlSQLNonTransientException {
}

- (instancetype)init;

- (instancetype)initWithNSString:(NSString *)reason;

- (instancetype)initWithNSString:(NSString *)reason
                    withNSString:(NSString *)sqlState;

- (instancetype)initWithNSString:(NSString *)reason
                    withNSString:(NSString *)sqlState
                         withInt:(jint)vendorCode;

- (instancetype)initWithJavaLangThrowable:(JavaLangThrowable *)cause;

- (instancetype)initWithNSString:(NSString *)reason
           withJavaLangThrowable:(JavaLangThrowable *)cause;

- (instancetype)initWithNSString:(NSString *)reason
                    withNSString:(NSString *)sqlState
           withJavaLangThrowable:(JavaLangThrowable *)cause;

- (instancetype)initWithNSString:(NSString *)reason
                    withNSString:(NSString *)sqlState
                         withInt:(jint)vendorCode
           withJavaLangThrowable:(JavaLangThrowable *)cause;


@end

J2OBJC_EMPTY_STATIC_INIT(JavaSqlSQLSyntaxErrorException)

CF_EXTERN_C_BEGIN

J2OBJC_STATIC_FIELD_GETTER(JavaSqlSQLSyntaxErrorException, serialVersionUID, jlong)
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(JavaSqlSQLSyntaxErrorException)

#endif // _JavaSqlSQLSyntaxErrorException_H_
