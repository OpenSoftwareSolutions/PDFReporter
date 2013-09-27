//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/crosstabs/design/JRDesignCrosstabGroup.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterCrosstabsDesignJRDesignCrosstabGroup_H_
#define _OrgOssPdfreporterCrosstabsDesignJRDesignCrosstabGroup_H_

@class OrgOssPdfreporterCrosstabsDesignJRCrosstabOrigin;
@class OrgOssPdfreporterCrosstabsDesignJRDesignCellContents;
@class OrgOssPdfreporterCrosstabsDesignJRDesignCrosstab;
@class OrgOssPdfreporterCrosstabsDesignJRDesignCrosstabBucket;
@class OrgOssPdfreporterCrosstabsTypeCrosstabTotalPositionEnumEnum;
@class OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport;
@class OrgOssPdfreporterEngineDesignJRDesignVariable;
@protocol OrgOssPdfreporterCrosstabsJRCellContents;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/crosstabs/base/JRBaseCrosstabGroup.h"
#include "org/oss/pdfreporter/engine/design/events/JRChangeEventsSupport.h"

#define OrgOssPdfreporterCrosstabsDesignJRDesignCrosstabGroup_serialVersionUID -3142244933088846956

@interface OrgOssPdfreporterCrosstabsDesignJRDesignCrosstabGroup : OrgOssPdfreporterCrosstabsBaseJRBaseCrosstabGroup < OrgOssPdfreporterEngineDesignEventsJRChangeEventsSupport > {
 @public
  OrgOssPdfreporterEngineDesignJRDesignVariable *designVariable_;
  OrgOssPdfreporterCrosstabsDesignJRDesignCrosstab *parent_;
  OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport *eventSupport_;
}

@property (nonatomic, strong) OrgOssPdfreporterEngineDesignJRDesignVariable *designVariable;
@property (nonatomic, strong) OrgOssPdfreporterCrosstabsDesignJRDesignCrosstab *parent;
@property (nonatomic, strong) OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport *eventSupport;

+ (NSString *)PROPERTY_BUCKET;
+ (NSString *)PROPERTY_HEADER;
+ (NSString *)PROPERTY_NAME;
+ (NSString *)PROPERTY_TOTAL_HEADER;
+ (NSString *)PROPERTY_TOTAL_POSITION;
- (id)init;
- (void)setNameWithNSString:(NSString *)name;
- (void)setTotalPositionWithOrgOssPdfreporterCrosstabsTypeCrosstabTotalPositionEnumEnum:(OrgOssPdfreporterCrosstabsTypeCrosstabTotalPositionEnumEnum *)totalPositionValue;
- (void)setBucketWithOrgOssPdfreporterCrosstabsDesignJRDesignCrosstabBucket:(OrgOssPdfreporterCrosstabsDesignJRDesignCrosstabBucket *)bucket;
- (void)setHeaderWithOrgOssPdfreporterCrosstabsDesignJRDesignCellContents:(OrgOssPdfreporterCrosstabsDesignJRDesignCellContents *)header;
- (void)setTotalHeaderWithOrgOssPdfreporterCrosstabsDesignJRDesignCellContents:(OrgOssPdfreporterCrosstabsDesignJRDesignCellContents *)totalHeader;
- (OrgOssPdfreporterCrosstabsDesignJRDesignCrosstab *)getParent;
- (void)setParentWithOrgOssPdfreporterCrosstabsDesignJRDesignCrosstab:(OrgOssPdfreporterCrosstabsDesignJRDesignCrosstab *)parent;
- (void)setCellOriginWithOrgOssPdfreporterCrosstabsJRCellContents:(id<OrgOssPdfreporterCrosstabsJRCellContents>)cell
             withOrgOssPdfreporterCrosstabsDesignJRCrosstabOrigin:(OrgOssPdfreporterCrosstabsDesignJRCrosstabOrigin *)origin;
- (id)clone;
- (OrgOssPdfreporterCrosstabsDesignJRDesignCrosstabGroup *)cloneWithOrgOssPdfreporterCrosstabsDesignJRDesignCrosstab:(OrgOssPdfreporterCrosstabsDesignJRDesignCrosstab *)parent;
- (OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport *)getEventSupport;
@end

#endif // _OrgOssPdfreporterCrosstabsDesignJRDesignCrosstabGroup_H_