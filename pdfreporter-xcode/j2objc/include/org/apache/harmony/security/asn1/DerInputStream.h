//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: android/libcore/luni/src/main/java/org/apache/harmony/security/asn1/DerInputStream.java
//

#ifndef _OrgApacheHarmonySecurityAsn1DerInputStream_H_
#define _OrgApacheHarmonySecurityAsn1DerInputStream_H_

@class IOSByteArray;
@class JavaIoInputStream;
@class OrgApacheHarmonySecurityAsn1ASN1Sequence;
@class OrgApacheHarmonySecurityAsn1ASN1SetOf;
@class OrgApacheHarmonySecurityAsn1ASN1StringType;

#include "J2ObjC_header.h"
#include "org/apache/harmony/security/asn1/BerInputStream.h"

@interface OrgApacheHarmonySecurityAsn1DerInputStream : OrgApacheHarmonySecurityAsn1BerInputStream {
}

- (instancetype)initWithByteArray:(IOSByteArray *)encoded;

- (instancetype)initWithByteArray:(IOSByteArray *)encoded
                          withInt:(jint)offset
                          withInt:(jint)encodingLen;

- (instancetype)initWithJavaIoInputStream:(JavaIoInputStream *)inArg;

- (jint)next;

- (void)readBitString;

- (void)readBoolean;

- (void)readOctetString;

- (void)readSequenceWithOrgApacheHarmonySecurityAsn1ASN1Sequence:(OrgApacheHarmonySecurityAsn1ASN1Sequence *)sequence;

- (void)readSetOfWithOrgApacheHarmonySecurityAsn1ASN1SetOf:(OrgApacheHarmonySecurityAsn1ASN1SetOf *)setOf;

- (void)readStringWithOrgApacheHarmonySecurityAsn1ASN1StringType:(OrgApacheHarmonySecurityAsn1ASN1StringType *)type;

- (void)readUTCTime;

- (void)readGeneralizedTime;

@end

FOUNDATION_EXPORT BOOL OrgApacheHarmonySecurityAsn1DerInputStream_initialized;
J2OBJC_STATIC_INIT(OrgApacheHarmonySecurityAsn1DerInputStream)

CF_EXTERN_C_BEGIN

FOUNDATION_EXPORT IOSByteArray *OrgApacheHarmonySecurityAsn1DerInputStream_UNUSED_BITS_MASK_;
J2OBJC_STATIC_FIELD_GETTER(OrgApacheHarmonySecurityAsn1DerInputStream, UNUSED_BITS_MASK_, IOSByteArray *)
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(OrgApacheHarmonySecurityAsn1DerInputStream)

#endif // _OrgApacheHarmonySecurityAsn1DerInputStream_H_
