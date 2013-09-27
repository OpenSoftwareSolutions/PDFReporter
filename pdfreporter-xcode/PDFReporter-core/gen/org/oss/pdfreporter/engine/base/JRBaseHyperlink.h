//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/base/JRBaseHyperlink.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineBaseJRBaseHyperlink_H_
#define _OrgOssPdfreporterEngineBaseJRBaseHyperlink_H_

@class IOSObjectArray;
@class OrgOssPdfreporterEngineBaseJRBaseObjectFactory;
@class OrgOssPdfreporterEngineTypeHyperlinkTypeEnumEnum;
@protocol OrgOssPdfreporterEngineJRExpression;

#import "JreEmulation.h"
#include "java/io/Serializable.h"
#include "org/oss/pdfreporter/engine/JRHyperlink.h"

#define OrgOssPdfreporterEngineBaseJRBaseHyperlink_serialVersionUID 10200

@interface OrgOssPdfreporterEngineBaseJRBaseHyperlink : NSObject < OrgOssPdfreporterEngineJRHyperlink, JavaIoSerializable > {
 @public
  NSString *linkType_;
  NSString *linkTarget_;
  id<OrgOssPdfreporterEngineJRExpression> hyperlinkReferenceExpression_;
  id<OrgOssPdfreporterEngineJRExpression> hyperlinkAnchorExpression_;
  id<OrgOssPdfreporterEngineJRExpression> hyperlinkPageExpression_;
  id<OrgOssPdfreporterEngineJRExpression> hyperlinkTooltipExpression_;
  IOSObjectArray *hyperlinkParameters_;
}

@property (nonatomic, copy) NSString *linkType;
@property (nonatomic, copy) NSString *linkTarget;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRExpression> hyperlinkReferenceExpression;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRExpression> hyperlinkAnchorExpression;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRExpression> hyperlinkPageExpression;
@property (nonatomic, strong) id<OrgOssPdfreporterEngineJRExpression> hyperlinkTooltipExpression;
@property (nonatomic, strong) IOSObjectArray *hyperlinkParameters;

- (id)init;
- (id)initWithOrgOssPdfreporterEngineJRHyperlink:(id<OrgOssPdfreporterEngineJRHyperlink>)link
withOrgOssPdfreporterEngineBaseJRBaseObjectFactory:(OrgOssPdfreporterEngineBaseJRBaseObjectFactory *)factory;
+ (IOSObjectArray *)copyHyperlinkParametersWithOrgOssPdfreporterEngineJRHyperlink:(id<OrgOssPdfreporterEngineJRHyperlink>)link
                               withOrgOssPdfreporterEngineBaseJRBaseObjectFactory:(OrgOssPdfreporterEngineBaseJRBaseObjectFactory *)factory OBJC_METHOD_FAMILY_NONE;
- (id<OrgOssPdfreporterEngineJRExpression>)getHyperlinkAnchorExpression;
- (id<OrgOssPdfreporterEngineJRExpression>)getHyperlinkPageExpression;
- (IOSObjectArray *)getHyperlinkParameters;
- (id<OrgOssPdfreporterEngineJRExpression>)getHyperlinkReferenceExpression;
- (char)getHyperlinkTarget;
- (char)getHyperlinkType;
- (OrgOssPdfreporterEngineTypeHyperlinkTypeEnumEnum *)getHyperlinkTypeValue;
- (NSString *)getLinkType;
- (NSString *)getLinkTarget;
- (id<OrgOssPdfreporterEngineJRExpression>)getHyperlinkTooltipExpression;
- (id)clone;
- (id)copyWithZone:(NSZone *)zone;
@end

#endif // _OrgOssPdfreporterEngineBaseJRBaseHyperlink_H_