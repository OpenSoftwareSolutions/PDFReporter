//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-extensions/src/org/oss/pdfreporter/uses/org/apache/commons/collections/comparators/ComparableComparator.java
//
//  Created by kendra on 9/27/13.
//

#include "IOSClass.h"
#include "java/lang/ClassCastException.h"
#include "java/lang/Comparable.h"
#include "org/oss/pdfreporter/uses/org/apache/commons/collections/comparators/ComparableComparator.h"

@implementation OrgOssPdfreporterUsesOrgApacheCommonsCollectionsComparatorsComparableComparator

static OrgOssPdfreporterUsesOrgApacheCommonsCollectionsComparatorsComparableComparator * OrgOssPdfreporterUsesOrgApacheCommonsCollectionsComparatorsComparableComparator_instance_;

+ (OrgOssPdfreporterUsesOrgApacheCommonsCollectionsComparatorsComparableComparator *)instance {
  return OrgOssPdfreporterUsesOrgApacheCommonsCollectionsComparatorsComparableComparator_instance_;
}

+ (OrgOssPdfreporterUsesOrgApacheCommonsCollectionsComparatorsComparableComparator *)getInstance {
  return OrgOssPdfreporterUsesOrgApacheCommonsCollectionsComparatorsComparableComparator_instance_;
}

- (id)init {
  return [super init];
}

- (int)compareWithId:(id)o1
              withId:(id)o2 {
  if ((o1 == nil) || (o2 == nil)) {
    @throw [[JavaLangClassCastException alloc] initWithNSString:[NSString stringWithFormat:@"%@%@, %@)", @"There were nulls in the arguments for this method: compare(", o1, o2]];
  }
  if ([o1 conformsToProtocol: @protocol(JavaLangComparable)]) {
    if ([o2 conformsToProtocol: @protocol(JavaLangComparable)]) {
      int result1 = [((id<JavaLangComparable>) o1) compareToWithId:o2];
      int result2 = [((id<JavaLangComparable>) o2) compareToWithId:o1];
      if (result1 == 0 && result2 == 0) {
        return 0;
      }
      else if (result1 < 0 && result2 > 0) {
        return result1;
      }
      else if (result1 > 0 && result2 < 0) {
        return result1;
      }
      else {
        @throw [[JavaLangClassCastException alloc] initWithNSString:@"o1 not comparable to o2"];
      }
    }
    else {
      @throw [[JavaLangClassCastException alloc] initWithNSString:[NSString stringWithFormat:@"The first argument of this method was not a Comparable: %@", [[nil_chk(o2) getClass] getName]]];
    }
  }
  else if ([o2 conformsToProtocol: @protocol(JavaLangComparable)]) {
    @throw [[JavaLangClassCastException alloc] initWithNSString:[NSString stringWithFormat:@"The second argument of this method was not a Comparable: %@", [[nil_chk(o1) getClass] getName]]];
  }
  else {
    @throw [[JavaLangClassCastException alloc] initWithNSString:[NSString stringWithFormat:@"Both arguments of this method were not Comparables: %@ and %@", [[nil_chk(o1) getClass] getName], [[nil_chk(o2) getClass] getName]]];
  }
}

+ (void)initialize {
  if (self == [OrgOssPdfreporterUsesOrgApacheCommonsCollectionsComparatorsComparableComparator class]) {
    OrgOssPdfreporterUsesOrgApacheCommonsCollectionsComparatorsComparableComparator_instance_ = [[OrgOssPdfreporterUsesOrgApacheCommonsCollectionsComparatorsComparableComparator alloc] init];
  }
}

@end