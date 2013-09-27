//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/xml/JRGenericElementFactory.java
//
//  Created by kendra on 9/27/13.
//

#include "org/oss/pdfreporter/engine/design/JRDesignGenericElement.h"
#include "org/oss/pdfreporter/engine/design/JasperDesign.h"
#include "org/oss/pdfreporter/engine/type/EvaluationTimeEnum.h"
#include "org/oss/pdfreporter/engine/xml/JRGenericElementFactory.h"
#include "org/oss/pdfreporter/engine/xml/JRXmlConstants.h"
#include "org/oss/pdfreporter/uses/org/apache/digester/IDigester.h"
#include "org/oss/pdfreporter/xml/parsers/IAttributes.h"

@implementation OrgOssPdfreporterEngineXmlJRGenericElementFactory

- (id)createObjectWithOrgOssPdfreporterXmlParsersIAttributes:(id<OrgOssPdfreporterXmlParsersIAttributes>)attrs {
  OrgOssPdfreporterEngineDesignJasperDesign *jasperDesign = (OrgOssPdfreporterEngineDesignJasperDesign *) [((id<OrgOssPdfreporterUsesOrgApacheDigesterIDigester>) nil_chk(digester_)) peekWithInt:[((id<OrgOssPdfreporterUsesOrgApacheDigesterIDigester>) nil_chk(digester_)) getCount] - 2];
  OrgOssPdfreporterEngineDesignJRDesignGenericElement *element = [[OrgOssPdfreporterEngineDesignJRDesignGenericElement alloc] initWithOrgOssPdfreporterEngineJRDefaultStyleProvider:jasperDesign];
  OrgOssPdfreporterEngineTypeEvaluationTimeEnumEnum *evaluationTime = [OrgOssPdfreporterEngineTypeEvaluationTimeEnumEnum getByNameWithNSString:[((id<OrgOssPdfreporterXmlParsersIAttributes>) nil_chk(attrs)) getValueWithNSString:[OrgOssPdfreporterEngineXmlJRXmlConstants ATTRIBUTE_evaluationTime]]];
  if (evaluationTime != nil) {
    [((OrgOssPdfreporterEngineDesignJRDesignGenericElement *) nil_chk(element)) setEvaluationTimeWithOrgOssPdfreporterEngineTypeEvaluationTimeEnumEnum:evaluationTime];
  }
  if ([((OrgOssPdfreporterEngineDesignJRDesignGenericElement *) nil_chk(element)) getEvaluationTimeValue] == [OrgOssPdfreporterEngineTypeEvaluationTimeEnumEnum GROUP]) {
    NSString *groupName = [((id<OrgOssPdfreporterXmlParsersIAttributes>) nil_chk(attrs)) getValueWithNSString:[OrgOssPdfreporterEngineXmlJRXmlConstants ATTRIBUTE_evaluationGroup]];
    if (groupName != nil) {
      [((OrgOssPdfreporterEngineDesignJRDesignGenericElement *) nil_chk(element)) setEvaluationGroupNameWithNSString:groupName];
    }
  }
  return element;
}

- (id)init {
  return [super init];
}

@end