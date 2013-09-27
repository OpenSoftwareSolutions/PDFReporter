//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/xml/ElementReprintGroupReference.java
//
//  Created by kendra on 9/27/13.
//

#include "org/oss/pdfreporter/engine/JRGroup.h"
#include "org/oss/pdfreporter/engine/design/JRDesignElement.h"
#include "org/oss/pdfreporter/engine/design/JRValidationException.h"
#include "org/oss/pdfreporter/engine/xml/ElementReprintGroupReference.h"

@implementation OrgOssPdfreporterEngineXmlElementReprintGroupReference

@synthesize element = element_;

- (id)initWithOrgOssPdfreporterEngineDesignJRDesignElement:(OrgOssPdfreporterEngineDesignJRDesignElement *)element {
  if ((self = [super init])) {
    self.element = element;
  }
  return self;
}

- (id<OrgOssPdfreporterEngineJRGroup>)getGroupReference {
  return [((OrgOssPdfreporterEngineDesignJRDesignElement *) nil_chk(element_)) getPrintWhenGroupChanges];
}

- (void)assignGroupWithOrgOssPdfreporterEngineJRGroup:(id<OrgOssPdfreporterEngineJRGroup>)group {
  [((OrgOssPdfreporterEngineDesignJRDesignElement *) nil_chk(element_)) setPrintWhenGroupChangesWithOrgOssPdfreporterEngineJRGroup:group];
}

- (void)groupNotFoundWithNSString:(NSString *)groupName {
  @throw [[OrgOssPdfreporterEngineDesignJRValidationException alloc] initWithNSString:[NSString stringWithFormat:@"Unknown reprint group '%@' for element.", groupName] withId:element_];
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterEngineXmlElementReprintGroupReference *typedCopy = (OrgOssPdfreporterEngineXmlElementReprintGroupReference *) copy;
  typedCopy.element = element_;
}

@end