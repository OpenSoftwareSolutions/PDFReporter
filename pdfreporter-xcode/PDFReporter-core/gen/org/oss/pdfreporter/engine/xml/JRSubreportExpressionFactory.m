//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/xml/JRSubreportExpressionFactory.java
//
//  Created by kendra on 9/27/13.
//

#include "IOSClass.h"
#include "org/oss/pdfreporter/engine/JasperReport.h"
#include "org/oss/pdfreporter/engine/design/JRDesignExpression.h"
#include "org/oss/pdfreporter/engine/xml/JRSubreportExpressionFactory.h"
#include "org/oss/pdfreporter/engine/xml/JRXmlConstants.h"
#include "org/oss/pdfreporter/xml/parsers/IAttributes.h"

@implementation OrgOssPdfreporterEngineXmlJRSubreportExpressionFactory

- (id)createObjectWithOrgOssPdfreporterXmlParsersIAttributes:(id<OrgOssPdfreporterXmlParsersIAttributes>)atts {
  OrgOssPdfreporterEngineDesignJRDesignExpression *expression = [[OrgOssPdfreporterEngineDesignJRDesignExpression alloc] init];
  NSString *value = [((id<OrgOssPdfreporterXmlParsersIAttributes>) nil_chk(atts)) getValueWithNSString:[OrgOssPdfreporterEngineXmlJRXmlConstants ATTRIBUTE_class]];
  if (value != nil) {
    if ([value isEqual:@"dori.jasper.engine.JasperReport"]) {
      value = [[IOSClass classWithClass:[OrgOssPdfreporterEngineJasperReport class]] getName];
    }
    [((OrgOssPdfreporterEngineDesignJRDesignExpression *) nil_chk(expression)) setValueClassNameWithNSString:value];
  }
  else {
    [((OrgOssPdfreporterEngineDesignJRDesignExpression *) nil_chk(expression)) setValueClassWithIOSClass:[IOSClass classWithClass:[NSString class]]];
  }
  return expression;
}

- (id)init {
  return [super init];
}

@end