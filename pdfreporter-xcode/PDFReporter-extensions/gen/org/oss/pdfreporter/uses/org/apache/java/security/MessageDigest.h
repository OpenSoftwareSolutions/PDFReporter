//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-extensions/src/org/oss/pdfreporter/uses/org/apache/java/security/MessageDigest.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterUsesOrgApacheJavaSecurityMessageDigest_H_
#define _OrgOssPdfreporterUsesOrgApacheJavaSecurityMessageDigest_H_

@class IOSByteArray;

#import "JreEmulation.h"

@interface OrgOssPdfreporterUsesOrgApacheJavaSecurityMessageDigest : NSObject {
}

- (id)init;
- (void)appendWithByteArray:(IOSByteArray *)block;
- (void)appendWithByteArray:(IOSByteArray *)block
                    withInt:(int)length;
- (void)appendWithByteArray:(IOSByteArray *)block
                    withInt:(int)offset
                    withInt:(int)length;
- (IOSByteArray *)digestWithByteArray:(IOSByteArray *)block;
- (IOSByteArray *)digestWithByteArray:(IOSByteArray *)block
                              withInt:(int)length;
- (IOSByteArray *)digestWithByteArray:(IOSByteArray *)block
                              withInt:(int)offset
                              withInt:(int)length;
- (void)reset;
@end

#endif // _OrgOssPdfreporterUsesOrgApacheJavaSecurityMessageDigest_H_