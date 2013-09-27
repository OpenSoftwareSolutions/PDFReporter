//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/util/ValuePropertiesWrapper.java
//
//  Created by kendra on 9/27/13.
//

#include "IOSClass.h"
#include "IOSObjectArray.h"
#include "java/lang/ClassCastException.h"
#include "java/lang/Comparable.h"
#include "org/oss/pdfreporter/engine/util/ValuePropertiesWrapper.h"

@implementation OrgOssPdfreporterEngineUtilValuePropertiesWrapper

@synthesize value = value_;
@synthesize propertyValues = propertyValues_;

- (id)initWithId:(id)value
withNSObjectArray:(IOSObjectArray *)propertyValues {
  if ((self = [super init])) {
    self.value = value;
    self.propertyValues = propertyValues;
  }
  return self;
}

- (NSUInteger)hash {
  return value_ == nil ? 0 : [nil_chk(value_) hash];
}

- (BOOL)isEqual:(id)obj {
  if (!([obj isKindOfClass:[OrgOssPdfreporterEngineUtilValuePropertiesWrapper class]])) {
    return NO;
  }
  OrgOssPdfreporterEngineUtilValuePropertiesWrapper *wrapper = (OrgOssPdfreporterEngineUtilValuePropertiesWrapper *) obj;
  return (value_ == nil) ? (((OrgOssPdfreporterEngineUtilValuePropertiesWrapper *) nil_chk(wrapper)).value == nil) : (((OrgOssPdfreporterEngineUtilValuePropertiesWrapper *) nil_chk(wrapper)).value != nil && [nil_chk(value_) isEqual:((OrgOssPdfreporterEngineUtilValuePropertiesWrapper *) nil_chk(wrapper)).value]);
}

- (int)compareToWithId:(OrgOssPdfreporterEngineUtilValuePropertiesWrapper *)o {
  if (o != nil && ![o isKindOfClass:[OrgOssPdfreporterEngineUtilValuePropertiesWrapper class]]) {
    @throw [[JavaLangClassCastException alloc] init];
  }
  return [((id<JavaLangComparable>) value_) compareToWithId:((OrgOssPdfreporterEngineUtilValuePropertiesWrapper *) nil_chk(o)).value];
}

- (id)getValue {
  return value_;
}

- (IOSObjectArray *)getPropertyValues {
  return propertyValues_;
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterEngineUtilValuePropertiesWrapper *typedCopy = (OrgOssPdfreporterEngineUtilValuePropertiesWrapper *) copy;
  typedCopy.value = value_;
  typedCopy.propertyValues = propertyValues_;
}

@end