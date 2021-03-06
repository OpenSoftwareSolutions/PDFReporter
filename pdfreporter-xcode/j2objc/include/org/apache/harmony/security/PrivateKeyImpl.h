//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: android/libcore/luni/src/main/java/org/apache/harmony/security/PrivateKeyImpl.java
//

#ifndef _OrgApacheHarmonySecurityPrivateKeyImpl_H_
#define _OrgApacheHarmonySecurityPrivateKeyImpl_H_

@class IOSByteArray;

#include "J2ObjC_header.h"
#include "java/security/PrivateKey.h"

#define OrgApacheHarmonySecurityPrivateKeyImpl_serialVersionUID 7776497482533790279LL

@interface OrgApacheHarmonySecurityPrivateKeyImpl : NSObject < JavaSecurityPrivateKey > {
}

- (instancetype)initWithNSString:(NSString *)algorithm;

- (NSString *)getAlgorithm;

- (NSString *)getFormat;

- (IOSByteArray *)getEncoded;

- (void)setAlgorithmWithNSString:(NSString *)algorithm;

- (void)setEncodingWithByteArray:(IOSByteArray *)encoding;

@end

J2OBJC_EMPTY_STATIC_INIT(OrgApacheHarmonySecurityPrivateKeyImpl)

CF_EXTERN_C_BEGIN

J2OBJC_STATIC_FIELD_GETTER(OrgApacheHarmonySecurityPrivateKeyImpl, serialVersionUID, jlong)
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(OrgApacheHarmonySecurityPrivateKeyImpl)

#endif // _OrgApacheHarmonySecurityPrivateKeyImpl_H_
