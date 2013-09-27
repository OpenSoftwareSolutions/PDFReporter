//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/component/DefaultComponentsBundle.java
//
//  Created by kendra on 9/27/13.
//

#include "java/util/Map.h"
#include "java/util/Set.h"
#include "org/oss/pdfreporter/engine/JRRuntimeException.h"
#include "org/oss/pdfreporter/engine/component/ComponentsXmlParser.h"
#include "org/oss/pdfreporter/engine/component/DefaultComponentsBundle.h"
#include "org/oss/pdfreporter/engine/component/IComponentManager.h"

@implementation OrgOssPdfreporterEngineComponentDefaultComponentsBundle

@synthesize xmlParser = xmlParser_;
@synthesize componentManagers = componentManagers_;

- (id<OrgOssPdfreporterEngineComponentComponentsXmlParser>)getXmlParser {
  return xmlParser_;
}

- (void)setXmlParserWithOrgOssPdfreporterEngineComponentComponentsXmlParser:(id<OrgOssPdfreporterEngineComponentComponentsXmlParser>)xmlParser {
  self.xmlParser = xmlParser;
}

- (id<JavaUtilSet>)getComponentNames {
  return [((id<JavaUtilMap>) nil_chk(componentManagers_)) keySet];
}

- (id<OrgOssPdfreporterEngineComponentIComponentManager>)getComponentManagerWithNSString:(NSString *)componentName {
  id<OrgOssPdfreporterEngineComponentIComponentManager> manager = [((id<JavaUtilMap>) nil_chk(componentManagers_)) getWithId:componentName];
  if (manager == nil) {
    @throw [[OrgOssPdfreporterEngineJRRuntimeException alloc] initWithNSString:[NSString stringWithFormat:@"No component manager found for name %@, namespace %@", componentName, [((id<OrgOssPdfreporterEngineComponentComponentsXmlParser>) nil_chk(xmlParser_)) getNamespace]]];
  }
  return manager;
}

- (id<JavaUtilMap>)getComponentManagers {
  return componentManagers_;
}

- (void)setComponentManagersWithJavaUtilMap:(id<JavaUtilMap>)componentManagers {
  self.componentManagers = componentManagers;
}

- (id)init {
  return [super init];
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterEngineComponentDefaultComponentsBundle *typedCopy = (OrgOssPdfreporterEngineComponentDefaultComponentsBundle *) copy;
  typedCopy.xmlParser = xmlParser_;
  typedCopy.componentManagers = componentManagers_;
}

@end