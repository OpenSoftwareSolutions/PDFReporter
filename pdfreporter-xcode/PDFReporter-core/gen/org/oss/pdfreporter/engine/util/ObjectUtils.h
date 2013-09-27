//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/util/ObjectUtils.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineUtilObjectUtils_H_
#define _OrgOssPdfreporterEngineUtilObjectUtils_H_

@class IOSObjectArray;
@class JavaLangEnum;
@class OrgOssPdfreporterEngineJRPropertiesMap;
@class OrgOssPdfreporterEngineUtilObjectUtils_HashCode;
@protocol JavaUtilList;

#import "JreEmulation.h"

@interface OrgOssPdfreporterEngineUtilObjectUtils : NSObject {
}

+ (OrgOssPdfreporterEngineUtilObjectUtils_HashCode *)hash__;
+ (BOOL)equalsIdentityWithId:(id)o1
                      withId:(id)o2;
+ (BOOL)identicalWithId:(id)o1
                 withId:(id)o2;
+ (BOOL)identicalWithNSObjectArray:(IOSObjectArray *)v1
                 withNSObjectArray:(IOSObjectArray *)v2;
+ (BOOL)identicalWithJavaUtilList:(id<JavaUtilList>)l1
                 withJavaUtilList:(id<JavaUtilList>)l2;
+ (BOOL)equalsWithId:(id)o1
              withId:(id)o2;
+ (BOOL)equalsWithJavaLangEnum:(JavaLangEnum *)o1
              withJavaLangEnum:(JavaLangEnum *)o2;
+ (BOOL)equalsWithBOOL:(BOOL)b1
              withBOOL:(BOOL)b2;
+ (BOOL)equalsWithInt:(int)i1
              withInt:(int)i2;
+ (BOOL)equalsWithOrgOssPdfreporterEngineJRPropertiesMap:(OrgOssPdfreporterEngineJRPropertiesMap *)p1
              withOrgOssPdfreporterEngineJRPropertiesMap:(OrgOssPdfreporterEngineJRPropertiesMap *)p2;
- (id)init;
@end

@interface OrgOssPdfreporterEngineUtilObjectUtils_HashCode : NSObject {
 @public
  int coefficient_;
  int hash__;
}

@property (nonatomic, assign) int coefficient;
@property (nonatomic, assign) int hash_;

- (id)init;
- (void)addToHashWithInt:(int)value;
- (void)addWithInt:(int)value;
- (void)addWithBOOL:(BOOL)value;
- (void)addWithId:(id)value;
- (void)addIdenticalWithId:(id)value;
- (void)addIdentityWithId:(id)value;
- (void)addIdenticalWithNSObjectArray:(IOSObjectArray *)values;
- (void)addIdenticalWithJavaUtilList:(id<JavaUtilList>)values;
- (void)addWithOrgOssPdfreporterEngineJRPropertiesMap:(OrgOssPdfreporterEngineJRPropertiesMap *)properties;
- (int)getHashCode;
@end

#endif // _OrgOssPdfreporterEngineUtilObjectUtils_H_