//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/repo/ObjectResource.java
//
//  Created by kendra on 9/27/13.
//

#include "org/oss/pdfreporter/repo/ObjectResource.h"

@implementation OrgOssPdfreporterRepoObjectResource

@synthesize name = name_;
@synthesize value = value_;

- (NSString *)getName {
  return name_;
}

- (void)setNameWithNSString:(NSString *)name {
  self.name = name;
}

- (id)getValue {
  return value_;
}

- (void)setValueWithId:(id)value {
  self.value = value;
}

- (id)init {
  return [super init];
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterRepoObjectResource *typedCopy = (OrgOssPdfreporterRepoObjectResource *) copy;
  typedCopy.name = name_;
  typedCopy.value = value_;
}

@end