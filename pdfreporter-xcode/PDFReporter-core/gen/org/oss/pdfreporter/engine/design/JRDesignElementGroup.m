//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/design/JRDesignElementGroup.java
//
//  Created by kendra on 9/27/13.
//

#include "IOSClass.h"
#include "java/util/List.h"
#include "org/oss/pdfreporter/engine/JRChild.h"
#include "org/oss/pdfreporter/engine/JRElement.h"
#include "org/oss/pdfreporter/engine/JRElementGroup.h"
#include "org/oss/pdfreporter/engine/design/JRDesignElement.h"
#include "org/oss/pdfreporter/engine/design/JRDesignElementGroup.h"
#include "org/oss/pdfreporter/engine/design/events/JRPropertyChangeSupport.h"

@implementation OrgOssPdfreporterEngineDesignJRDesignElementGroup

static NSString * OrgOssPdfreporterEngineDesignJRDesignElementGroup_PROPERTY_ELEMENT_GROUP_ = @"elementGroup";
static NSString * OrgOssPdfreporterEngineDesignJRDesignElementGroup_PROPERTY_CHILDREN_ = @"children";

@synthesize eventSupport = eventSupport_;

+ (NSString *)PROPERTY_ELEMENT_GROUP {
  return OrgOssPdfreporterEngineDesignJRDesignElementGroup_PROPERTY_ELEMENT_GROUP_;
}

+ (NSString *)PROPERTY_CHILDREN {
  return OrgOssPdfreporterEngineDesignJRDesignElementGroup_PROPERTY_CHILDREN_;
}

- (void)setElementGroupWithOrgOssPdfreporterEngineJRElementGroup:(id<OrgOssPdfreporterEngineJRElementGroup>)elementGroup {
  id old = self.elementGroup;
  self.elementGroup = elementGroup;
  [((OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport *) nil_chk([self getEventSupport])) firePropertyChangeWithNSString:OrgOssPdfreporterEngineDesignJRDesignElementGroup_PROPERTY_ELEMENT_GROUP_ withId:old withId:self.elementGroup];
}

- (void)addElementWithOrgOssPdfreporterEngineDesignJRDesignElement:(OrgOssPdfreporterEngineDesignJRDesignElement *)element {
  [self addElementWithInt:[((id<JavaUtilList>) nil_chk(children_)) size] withOrgOssPdfreporterEngineDesignJRDesignElement:element];
}

- (void)addElementWithInt:(int)index
withOrgOssPdfreporterEngineDesignJRDesignElement:(OrgOssPdfreporterEngineDesignJRDesignElement *)element {
  [((OrgOssPdfreporterEngineDesignJRDesignElement *) nil_chk(element)) setElementGroupWithOrgOssPdfreporterEngineJRElementGroup:self];
  [((id<JavaUtilList>) nil_chk(self.children)) addWithInt:index withId:element];
  [((OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport *) nil_chk([self getEventSupport])) fireCollectionElementAddedEventWithNSString:OrgOssPdfreporterEngineDesignJRDesignElementGroup_PROPERTY_CHILDREN_ withId:element withInt:index];
}

- (void)addElementWithOrgOssPdfreporterEngineJRElement:(id<OrgOssPdfreporterEngineJRElement>)element {
  [((id<JavaUtilList>) nil_chk(self.children)) addWithId:element];
  [((OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport *) nil_chk([self getEventSupport])) fireCollectionElementAddedEventWithNSString:OrgOssPdfreporterEngineDesignJRDesignElementGroup_PROPERTY_CHILDREN_ withId:element withInt:[((id<JavaUtilList>) nil_chk(self.children)) size] - 1];
}

- (OrgOssPdfreporterEngineDesignJRDesignElement *)removeElementWithOrgOssPdfreporterEngineDesignJRDesignElement:(OrgOssPdfreporterEngineDesignJRDesignElement *)element {
  [((OrgOssPdfreporterEngineDesignJRDesignElement *) nil_chk(element)) setElementGroupWithOrgOssPdfreporterEngineJRElementGroup:nil];
  int idx = [((id<JavaUtilList>) nil_chk(self.children)) indexOfWithId:element];
  if (idx >= 0) {
    (void) [((id<JavaUtilList>) nil_chk(self.children)) removeWithInt:idx];
    [((OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport *) nil_chk([self getEventSupport])) fireCollectionElementRemovedEventWithNSString:OrgOssPdfreporterEngineDesignJRDesignElementGroup_PROPERTY_CHILDREN_ withId:element withInt:idx];
  }
  return element;
}

- (void)addElementGroupWithOrgOssPdfreporterEngineDesignJRDesignElementGroup:(OrgOssPdfreporterEngineDesignJRDesignElementGroup *)elemGrp {
  [self addElementGroupWithInt:[((id<JavaUtilList>) nil_chk(children_)) size] withOrgOssPdfreporterEngineDesignJRDesignElementGroup:elemGrp];
}

- (void)addElementGroupWithInt:(int)index
withOrgOssPdfreporterEngineDesignJRDesignElementGroup:(OrgOssPdfreporterEngineDesignJRDesignElementGroup *)elemGrp {
  [((OrgOssPdfreporterEngineDesignJRDesignElementGroup *) nil_chk(elemGrp)) setElementGroupWithOrgOssPdfreporterEngineJRElementGroup:self];
  [((id<JavaUtilList>) nil_chk(self.children)) addWithInt:index withId:elemGrp];
  [((OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport *) nil_chk([self getEventSupport])) fireCollectionElementAddedEventWithNSString:OrgOssPdfreporterEngineDesignJRDesignElementGroup_PROPERTY_CHILDREN_ withId:elemGrp withInt:index];
}

- (OrgOssPdfreporterEngineDesignJRDesignElementGroup *)removeElementGroupWithOrgOssPdfreporterEngineDesignJRDesignElementGroup:(OrgOssPdfreporterEngineDesignJRDesignElementGroup *)elemGrp {
  [((OrgOssPdfreporterEngineDesignJRDesignElementGroup *) nil_chk(elemGrp)) setElementGroupWithOrgOssPdfreporterEngineJRElementGroup:nil];
  int idx = [((id<JavaUtilList>) nil_chk(self.children)) indexOfWithId:elemGrp];
  if (idx >= 0) {
    (void) [((id<JavaUtilList>) nil_chk(self.children)) removeWithInt:idx];
    [((OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport *) nil_chk([self getEventSupport])) fireCollectionElementRemovedEventWithNSString:OrgOssPdfreporterEngineDesignJRDesignElementGroup_PROPERTY_CHILDREN_ withId:elemGrp withInt:idx];
  }
  return elemGrp;
}

- (id)clone {
  OrgOssPdfreporterEngineDesignJRDesignElementGroup *clone = (OrgOssPdfreporterEngineDesignJRDesignElementGroup *) [super clone];
  ((OrgOssPdfreporterEngineDesignJRDesignElementGroup *) nil_chk(clone)).eventSupport = nil;
  return clone;
}

- (OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport *)getEventSupport {
  @synchronized (self) {
    if (eventSupport_ == nil) {
      eventSupport_ = [[OrgOssPdfreporterEngineDesignEventsJRPropertyChangeSupport alloc] initWithId:self];
    }
  }
  return eventSupport_;
}

- (id)init {
  return [super init];
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterEngineDesignJRDesignElementGroup *typedCopy = (OrgOssPdfreporterEngineDesignJRDesignElementGroup *) copy;
  typedCopy.eventSupport = eventSupport_;
}

@end