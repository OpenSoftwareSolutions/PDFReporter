//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/fill/JRFillElementGroup.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineFillJRFillElementGroup_H_
#define _OrgOssPdfreporterEngineFillJRFillElementGroup_H_

@class IOSObjectArray;
@class JavaUtilLoggingLogger;
@class OrgOssPdfreporterEngineFillJRFillCloneFactory;
@class OrgOssPdfreporterEngineFillJRFillObjectFactory;
@protocol JavaUtilList;
@protocol OrgOssPdfreporterEngineJRElement;
@protocol OrgOssPdfreporterEngineJRVisitor;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/engine/JRElementGroup.h"
#include "org/oss/pdfreporter/engine/fill/JRFillCloneable.h"

@interface OrgOssPdfreporterEngineFillJRFillElementGroup : NSObject < OrgOssPdfreporterEngineJRElementGroup, OrgOssPdfreporterEngineFillJRFillCloneable > {
 @public
  id<JavaUtilList> children_;
  id<OrgOssPdfreporterEngineJRElementGroup> elementGroup_;
  IOSObjectArray *elements_;
  id<OrgOssPdfreporterEngineJRElement> topElementInGroup_;
  id<OrgOssPdfreporterEngineJRElement> bottomElementInGroup_;
  int stretchHeightDiff_;
}

@property (nonatomic, strong) id<JavaUtilList> children;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRElementGroup> elementGroup;
@property (nonatomic, strong) IOSObjectArray *elements;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRElement> topElementInGroup;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRElement> bottomElementInGroup;
@property (nonatomic, assign) int stretchHeightDiff;

+ (JavaUtilLoggingLogger *)logger;
- (id)initWithOrgOssPdfreporterEngineJRElementGroup:(id<OrgOssPdfreporterEngineJRElementGroup>)elementGrp
 withOrgOssPdfreporterEngineFillJRFillObjectFactory:(OrgOssPdfreporterEngineFillJRFillObjectFactory *)factory;
- (id)initWithOrgOssPdfreporterEngineFillJRFillElementGroup:(OrgOssPdfreporterEngineFillJRFillElementGroup *)elementGrp
          withOrgOssPdfreporterEngineFillJRFillCloneFactory:(OrgOssPdfreporterEngineFillJRFillCloneFactory *)factory;
- (id<JavaUtilList>)getChildren;
- (id<OrgOssPdfreporterEngineJRElementGroup>)getElementGroup;
- (IOSObjectArray *)getElements;
- (id<OrgOssPdfreporterEngineJRElement>)getElementByKeyWithNSString:(NSString *)key;
- (void)reset;
- (int)getStretchHeightDiff;
- (void)setTopBottomElements;
- (void)visitWithOrgOssPdfreporterEngineJRVisitor:(id<OrgOssPdfreporterEngineJRVisitor>)visitor;
- (id<OrgOssPdfreporterEngineFillJRFillCloneable>)createCloneWithOrgOssPdfreporterEngineFillJRFillCloneFactory:(OrgOssPdfreporterEngineFillJRFillCloneFactory *)factory;
- (id)clone;
- (id)cloneWithOrgOssPdfreporterEngineJRElementGroup:(id<OrgOssPdfreporterEngineJRElementGroup>)parentGroup;
- (id)copyWithZone:(NSZone *)zone;
@end

#endif // _OrgOssPdfreporterEngineFillJRFillElementGroup_H_