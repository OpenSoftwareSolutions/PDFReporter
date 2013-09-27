//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/export/JROriginExporterFilter.java
//
//  Created by kendra on 9/27/13.
//

#include "java/lang/Boolean.h"
#include "java/lang/Integer.h"
#include "java/util/HashMap.h"
#include "java/util/Iterator.h"
#include "java/util/List.h"
#include "java/util/Map.h"
#include "java/util/Set.h"
#include "org/oss/pdfreporter/engine/DefaultJasperReportsContext.h"
#include "org/oss/pdfreporter/engine/JROrigin.h"
#include "org/oss/pdfreporter/engine/JRPrintElement.h"
#include "org/oss/pdfreporter/engine/JRPropertiesMap.h"
#include "org/oss/pdfreporter/engine/JRPropertiesUtil.h"
#include "org/oss/pdfreporter/engine/JasperReportsContext.h"
#include "org/oss/pdfreporter/engine/export/JROriginExporterFilter.h"
#include "org/oss/pdfreporter/engine/type/BandTypeEnum.h"

@implementation OrgOssPdfreporterEngineExportJROriginExporterFilter

static NSString * OrgOssPdfreporterEngineExportJROriginExporterFilter_PROPERTY_EXCLUDE_ORIGIN_PREFIX_ = @"exclude.origin.";
static NSString * OrgOssPdfreporterEngineExportJROriginExporterFilter_KEEP_FIRST_PREFIX_ = @"keep.first.";
static NSString * OrgOssPdfreporterEngineExportJROriginExporterFilter_BAND_PREFIX_ = @"band.";
static NSString * OrgOssPdfreporterEngineExportJROriginExporterFilter_GROUP_PREFIX_ = @"group.";
static NSString * OrgOssPdfreporterEngineExportJROriginExporterFilter_REPORT_PREFIX_ = @"report.";

@synthesize originsToExclude = originsToExclude_;
@synthesize firstOccurrences = firstOccurrences_;
@synthesize matchedOrigins = matchedOrigins_;

+ (NSString *)PROPERTY_EXCLUDE_ORIGIN_PREFIX {
  return OrgOssPdfreporterEngineExportJROriginExporterFilter_PROPERTY_EXCLUDE_ORIGIN_PREFIX_;
}

+ (NSString *)KEEP_FIRST_PREFIX {
  return OrgOssPdfreporterEngineExportJROriginExporterFilter_KEEP_FIRST_PREFIX_;
}

+ (NSString *)BAND_PREFIX {
  return OrgOssPdfreporterEngineExportJROriginExporterFilter_BAND_PREFIX_;
}

+ (NSString *)GROUP_PREFIX {
  return OrgOssPdfreporterEngineExportJROriginExporterFilter_GROUP_PREFIX_;
}

+ (NSString *)REPORT_PREFIX {
  return OrgOssPdfreporterEngineExportJROriginExporterFilter_REPORT_PREFIX_;
}

- (void)addOriginWithOrgOssPdfreporterEngineJROrigin:(OrgOssPdfreporterEngineJROrigin *)origin {
  [self addOriginWithOrgOssPdfreporterEngineJROrigin:origin withBOOL:NO];
}

- (void)addOriginWithOrgOssPdfreporterEngineJROrigin:(OrgOssPdfreporterEngineJROrigin *)origin
                                            withBOOL:(BOOL)keepFirst {
  (void) [((id<JavaUtilMap>) nil_chk(originsToExclude_)) putWithId:origin withId:keepFirst ? [JavaLangBoolean getTRUE] : [JavaLangBoolean getFALSE]];
}

- (void)removeOriginWithOrgOssPdfreporterEngineJROrigin:(OrgOssPdfreporterEngineJROrigin *)origin {
  (void) [((id<JavaUtilMap>) nil_chk(originsToExclude_)) removeWithId:origin];
}

- (void)reset {
  firstOccurrences_ = [[JavaUtilHashMap alloc] init];
}

- (BOOL)isToExportWithOrgOssPdfreporterEngineJRPrintElement:(id<OrgOssPdfreporterEngineJRPrintElement>)element {
  OrgOssPdfreporterEngineJROrigin *origin = [((id<OrgOssPdfreporterEngineJRPrintElement>) nil_chk(element)) getOrigin];
  JavaLangBoolean *keepFirst = nil;
  if (origin != nil) {
    keepFirst = [((id<JavaUtilMap>) nil_chk(matchedOrigins_)) getWithId:origin];
    if (keepFirst == nil) {
      {
        id<JavaUtilIterator> iter__ = [((id<JavaUtilSet>) nil_chk([((id<JavaUtilMap>) nil_chk(originsToExclude_)) keySet])) iterator];
        while ([((id<JavaUtilIterator>) nil_chk(iter__)) hasNext]) {
          OrgOssPdfreporterEngineJROrigin *originToExclude = [((id<JavaUtilIterator>) nil_chk(iter__)) next];
          if ([self matchWithOrgOssPdfreporterEngineJROrigin:originToExclude withOrgOssPdfreporterEngineJROrigin:origin]) {
            keepFirst = [((id<JavaUtilMap>) nil_chk(originsToExclude_)) getWithId:originToExclude];
            (void) [((id<JavaUtilMap>) nil_chk(matchedOrigins_)) putWithId:origin withId:keepFirst];
            break;
          }
        }
      }
    }
  }
  BOOL originMatched = keepFirst != nil;
  return !originMatched || ([((JavaLangBoolean *) nil_chk(keepFirst)) booleanValue] && [self isFirstWithOrgOssPdfreporterEngineJRPrintElement:element]);
}

- (BOOL)matchWithOrgOssPdfreporterEngineJROrigin:(OrgOssPdfreporterEngineJROrigin *)originToExclude
             withOrgOssPdfreporterEngineJROrigin:(OrgOssPdfreporterEngineJROrigin *)origin {
  NSString *groupName1 = [((OrgOssPdfreporterEngineJROrigin *) nil_chk(originToExclude)) getGroupName];
  NSString *reportName1 = [((OrgOssPdfreporterEngineJROrigin *) nil_chk(originToExclude)) getReportName];
  NSString *groupName2 = [((OrgOssPdfreporterEngineJROrigin *) nil_chk(origin)) getGroupName];
  NSString *reportName2 = [((OrgOssPdfreporterEngineJROrigin *) nil_chk(origin)) getReportName];
  return ([((OrgOssPdfreporterEngineJROrigin *) nil_chk(originToExclude)) getBandTypeValue] == [((OrgOssPdfreporterEngineJROrigin *) nil_chk(origin)) getBandTypeValue] && (([@"*" isEqual:groupName1] && groupName2 != nil) || (groupName1 == nil ? groupName2 == nil : groupName2 != nil && [((NSString *) nil_chk(groupName1)) isEqual:groupName2])) && (([@"*" isEqual:reportName1] && reportName2 != nil) || (reportName1 == nil ? reportName2 == nil : reportName2 != nil && [((NSString *) nil_chk(reportName1)) isEqual:reportName2])));
}

- (BOOL)isFirstWithOrgOssPdfreporterEngineJRPrintElement:(id<OrgOssPdfreporterEngineJRPrintElement>)element {
  int elementId = [((id<OrgOssPdfreporterEngineJRPrintElement>) nil_chk(element)) getSourceElementId];
  if (elementId == OrgOssPdfreporterEngineJRPrintElement_UNSET_SOURCE_ELEMENT_ID) {
    return YES;
  }
  id<OrgOssPdfreporterEngineJRPrintElement> firstElement = [((id<JavaUtilMap>) nil_chk(firstOccurrences_)) getWithId:[JavaLangInteger valueOfWithInt:elementId]];
  if (firstElement == nil || firstElement == element) {
    (void) [((id<JavaUtilMap>) nil_chk(firstOccurrences_)) putWithId:[JavaLangInteger valueOfWithInt:elementId] withId:element];
    return YES;
  }
  return NO;
}

+ (OrgOssPdfreporterEngineExportJROriginExporterFilter *)getFilterWithOrgOssPdfreporterEngineJasperReportsContext:(id<OrgOssPdfreporterEngineJasperReportsContext>)jasperReportsContext
                                                                       withOrgOssPdfreporterEngineJRPropertiesMap:(OrgOssPdfreporterEngineJRPropertiesMap *)propertiesMap
                                                                                                     withNSString:(NSString *)originFilterPrefix {
  OrgOssPdfreporterEngineExportJROriginExporterFilter *filter = nil;
  filter = [OrgOssPdfreporterEngineExportJROriginExporterFilter addOriginsToFilterWithOrgOssPdfreporterEngineJasperReportsContext:jasperReportsContext withOrgOssPdfreporterEngineExportJROriginExporterFilter:filter withOrgOssPdfreporterEngineJRPropertiesMap:propertiesMap withNSString:originFilterPrefix withBOOL:NO];
  filter = [OrgOssPdfreporterEngineExportJROriginExporterFilter addOriginsToFilterWithOrgOssPdfreporterEngineJasperReportsContext:jasperReportsContext withOrgOssPdfreporterEngineExportJROriginExporterFilter:filter withOrgOssPdfreporterEngineJRPropertiesMap:propertiesMap withNSString:[NSString stringWithFormat:@"%@keep.first.", originFilterPrefix] withBOOL:YES];
  return filter;
}

+ (OrgOssPdfreporterEngineExportJROriginExporterFilter *)getFilterWithOrgOssPdfreporterEngineJRPropertiesMap:(OrgOssPdfreporterEngineJRPropertiesMap *)propertiesMap
                                                                                                withNSString:(NSString *)originFilterPrefix {
  return [OrgOssPdfreporterEngineExportJROriginExporterFilter getFilterWithOrgOssPdfreporterEngineJasperReportsContext:[OrgOssPdfreporterEngineDefaultJasperReportsContext getInstance] withOrgOssPdfreporterEngineJRPropertiesMap:propertiesMap withNSString:originFilterPrefix];
}

+ (OrgOssPdfreporterEngineExportJROriginExporterFilter *)addOriginsToFilterWithOrgOssPdfreporterEngineJasperReportsContext:(id<OrgOssPdfreporterEngineJasperReportsContext>)jasperReportsContext
                                                                   withOrgOssPdfreporterEngineExportJROriginExporterFilter:(OrgOssPdfreporterEngineExportJROriginExporterFilter *)filter
                                                                                withOrgOssPdfreporterEngineJRPropertiesMap:(OrgOssPdfreporterEngineJRPropertiesMap *)propertiesMap
                                                                                                              withNSString:(NSString *)originFilterPrefix
                                                                                                                  withBOOL:(BOOL)keepFirst {
  OrgOssPdfreporterEngineJRPropertiesUtil *propUtil = [OrgOssPdfreporterEngineJRPropertiesUtil getInstanceWithOrgOssPdfreporterEngineJasperReportsContext:jasperReportsContext];
  id<JavaUtilList> properties = [((OrgOssPdfreporterEngineJRPropertiesUtil *) nil_chk(propUtil)) getPropertiesWithNSString:[NSString stringWithFormat:@"%@band.", originFilterPrefix]];
  [((id<JavaUtilList>) nil_chk(properties)) addAllWithJavaUtilCollection:[OrgOssPdfreporterEngineJRPropertiesUtil getPropertiesWithOrgOssPdfreporterEngineJRPropertiesMap:propertiesMap withNSString:[NSString stringWithFormat:@"%@band.", originFilterPrefix]]];
  if (![((id<JavaUtilList>) nil_chk(properties)) isEmpty]) {
    filter = (filter == nil ? [[OrgOssPdfreporterEngineExportJROriginExporterFilter alloc] init] : filter);
    for (id<JavaUtilIterator> it = [((id<JavaUtilList>) nil_chk(properties)) iterator]; [((id<JavaUtilIterator>) nil_chk(it)) hasNext]; ) {
      OrgOssPdfreporterEngineJRPropertiesUtil_PropertySuffix *propertySuffix = [((id<JavaUtilIterator>) nil_chk(it)) next];
      NSString *suffix = [((OrgOssPdfreporterEngineJRPropertiesUtil_PropertySuffix *) nil_chk(propertySuffix)) getSuffix];
      OrgOssPdfreporterEngineTypeBandTypeEnumEnum *bandType = [OrgOssPdfreporterEngineTypeBandTypeEnumEnum getByNameWithNSString:[((OrgOssPdfreporterEngineJRPropertiesUtil *) nil_chk(propUtil)) getPropertyWithOrgOssPdfreporterEngineJRPropertiesMap:propertiesMap withNSString:[((OrgOssPdfreporterEngineJRPropertiesUtil_PropertySuffix *) nil_chk(propertySuffix)) getKey]]];
      if (bandType != nil) {
        [((OrgOssPdfreporterEngineExportJROriginExporterFilter *) nil_chk(filter)) addOriginWithOrgOssPdfreporterEngineJROrigin:[[OrgOssPdfreporterEngineJROrigin alloc] initWithNSString:[((OrgOssPdfreporterEngineJRPropertiesUtil *) nil_chk(propUtil)) getPropertyWithOrgOssPdfreporterEngineJRPropertiesMap:propertiesMap withNSString:[NSString stringWithFormat:@"%@report.%@", originFilterPrefix, suffix]] withNSString:[((OrgOssPdfreporterEngineJRPropertiesUtil *) nil_chk(propUtil)) getPropertyWithOrgOssPdfreporterEngineJRPropertiesMap:propertiesMap withNSString:[NSString stringWithFormat:@"%@group.%@", originFilterPrefix, suffix]] withOrgOssPdfreporterEngineTypeBandTypeEnumEnum:bandType] withBOOL:keepFirst];
      }
    }
  }
  return filter;
}

- (id)init {
  if ((self = [super init])) {
    originsToExclude_ = [[JavaUtilHashMap alloc] init];
    firstOccurrences_ = [[JavaUtilHashMap alloc] init];
    matchedOrigins_ = [[JavaUtilHashMap alloc] init];
  }
  return self;
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterEngineExportJROriginExporterFilter *typedCopy = (OrgOssPdfreporterEngineExportJROriginExporterFilter *) copy;
  typedCopy.originsToExclude = originsToExclude_;
  typedCopy.firstOccurrences = firstOccurrences_;
  typedCopy.matchedOrigins = matchedOrigins_;
}

@end