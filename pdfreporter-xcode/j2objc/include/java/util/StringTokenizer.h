//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: android/libcore/luni/src/main/java/java/util/StringTokenizer.java
//

#ifndef _JavaUtilStringTokenizer_H_
#define _JavaUtilStringTokenizer_H_

#include "J2ObjC_header.h"
#include "java/util/Enumeration.h"

@interface JavaUtilStringTokenizer : NSObject < JavaUtilEnumeration > {
}

- (instancetype)initWithNSString:(NSString *)string;

- (instancetype)initWithNSString:(NSString *)string
                    withNSString:(NSString *)delimiters;

- (instancetype)initWithNSString:(NSString *)string
                    withNSString:(NSString *)delimiters
                     withBoolean:(jboolean)returnDelimiters;

- (jint)countTokens;

- (jboolean)hasMoreElements;

- (jboolean)hasMoreTokens;

- (id)nextElement;

- (NSString *)nextToken;

- (NSString *)nextTokenWithNSString:(NSString *)delims;

@end

J2OBJC_EMPTY_STATIC_INIT(JavaUtilStringTokenizer)

CF_EXTERN_C_BEGIN
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(JavaUtilStringTokenizer)

#endif // _JavaUtilStringTokenizer_H_
