//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/xml/JRSectionFactory.java
//
//  Created by kendra on 9/27/13.
//

#include "org/oss/pdfreporter/engine/JRSection.h"
#include "org/oss/pdfreporter/engine/design/JRDesignGroup.h"
#include "org/oss/pdfreporter/engine/design/JasperDesign.h"
#include "org/oss/pdfreporter/engine/xml/JRSectionFactory.h"
#include "org/oss/pdfreporter/uses/org/apache/digester/IDigester.h"
#include "org/oss/pdfreporter/xml/parsers/IAttributes.h"

@implementation OrgOssPdfreporterEngineXmlJRSectionFactory

- (id<OrgOssPdfreporterEngineJRSection>)getSection {
  // can't call an abstract method
  [self doesNotRecognizeSelector:_cmd];
  return 0;
}

- (id)createObjectWithOrgOssPdfreporterXmlParsersIAttributes:(id<OrgOssPdfreporterXmlParsersIAttributes>)atts {
  return [self getSection];
}

- (id)init {
  return [super init];
}

@end
@implementation OrgOssPdfreporterEngineXmlJRSectionFactory_DetailSectionFactory

- (id<OrgOssPdfreporterEngineJRSection>)getSection {
  return [((OrgOssPdfreporterEngineDesignJasperDesign *) [((id<OrgOssPdfreporterUsesOrgApacheDigesterIDigester>) nil_chk(digester_)) peekWithInt:[((id<OrgOssPdfreporterUsesOrgApacheDigesterIDigester>) nil_chk(digester_)) getCount] - 2]) getDetailSection];
}

- (id)init {
  return [super init];
}

@end
@implementation OrgOssPdfreporterEngineXmlJRSectionFactory_GroupHeaderSectionFactory

- (id<OrgOssPdfreporterEngineJRSection>)getSection {
  return [((OrgOssPdfreporterEngineDesignJRDesignGroup *) [((id<OrgOssPdfreporterUsesOrgApacheDigesterIDigester>) nil_chk(digester_)) peek]) getGroupHeaderSection];
}

- (id)init {
  return [super init];
}

@end
@implementation OrgOssPdfreporterEngineXmlJRSectionFactory_GroupFooterSectionFactory

- (id<OrgOssPdfreporterEngineJRSection>)getSection {
  return [((OrgOssPdfreporterEngineDesignJRDesignGroup *) [((id<OrgOssPdfreporterUsesOrgApacheDigesterIDigester>) nil_chk(digester_)) peek]) getGroupFooterSection];
}

- (id)init {
  return [super init];
}

@end