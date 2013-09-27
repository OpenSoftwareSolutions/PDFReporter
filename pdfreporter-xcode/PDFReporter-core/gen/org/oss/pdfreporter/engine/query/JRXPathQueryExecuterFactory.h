//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/query/JRXPathQueryExecuterFactory.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineQueryJRXPathQueryExecuterFactory_H_
#define _OrgOssPdfreporterEngineQueryJRXPathQueryExecuterFactory_H_

@class IOSObjectArray;
@protocol JavaUtilMap;
@protocol OrgOssPdfreporterEngineJRDataset;
@protocol OrgOssPdfreporterEngineJasperReportsContext;
@protocol OrgOssPdfreporterEngineQueryJRQueryExecuter;

#import "JreEmulation.h"
#include "org/oss/pdfreporter/engine/query/AbstractQueryExecuterFactory.h"

@interface OrgOssPdfreporterEngineQueryJRXPathQueryExecuterFactory : OrgOssPdfreporterEngineQueryAbstractQueryExecuterFactory {
}

+ (NSString *)PARAMETER_XML_DATA_DOCUMENT;
+ (NSString *)XML_INPUT_STREAM;
+ (NSString *)XML_FILE;
+ (NSString *)XML_SOURCE;
+ (NSString *)XML_DATE_PATTERN;
+ (NSString *)PROPERTY_XML_DATE_PATTERN;
+ (NSString *)XML_NUMBER_PATTERN;
+ (NSString *)PROPERTY_XML_NUMBER_PATTERN;
+ (NSString *)XML_LOCALE;
+ (NSString *)XML_TIME_ZONE;
+ (IOSObjectArray *)XPATH_BUILTIN_PARAMETERS;
- (IOSObjectArray *)getBuiltinParameters;
- (id<OrgOssPdfreporterEngineQueryJRQueryExecuter>)createQueryExecuterWithOrgOssPdfreporterEngineJasperReportsContext:(id<OrgOssPdfreporterEngineJasperReportsContext>)jasperReportsContext
                                                                                 withOrgOssPdfreporterEngineJRDataset:(id<OrgOssPdfreporterEngineJRDataset>)dataset
                                                                                                      withJavaUtilMap:(id<JavaUtilMap>)parameters;
- (BOOL)supportsQueryParameterTypeWithNSString:(NSString *)className_;
- (id)init;
@end

#endif // _OrgOssPdfreporterEngineQueryJRXPathQueryExecuterFactory_H_