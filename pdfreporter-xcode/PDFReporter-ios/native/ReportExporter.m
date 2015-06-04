#import "ReportExporter.h"
#import "org/oss/pdfreporter/registry/ApiRegistry.h"
#import "java/io/InputStream.h"
#import "org/oss/pdfreporter/sql/IConnection.h"
#import "SqlFactory.h"
#import "org/oss/pdfreporter/repo/RepositoryManager.h"
#import "org/oss/pdfreporter/repo/FileResourceLoader.h"
#import "org/oss/pdfreporter/engine/xml/JRXmlLoader.h"
#import "org/oss/pdfreporter/engine/data/JRXmlDataSource.h"
#import "org/oss/pdfreporter/engine/JREmptyDataSource.h"
#import "org/oss/pdfreporter/engine/design/JasperDesign.h"
#import "org/oss/pdfreporter/engine/JasperPrint.h"
#import "org/oss/pdfreporter/engine/JasperReport.h"
#import "org/oss/pdfreporter/engine/JasperFillManager.h"
#import "org/oss/pdfreporter/engine/JasperCompileManager.h"
#import "org/oss/pdfreporter/engine/JasperExportManager.h"
#include "org/oss/pdfreporter/net/IURL.h"
#import "NSDictionaryMap.h"
#include "org/oss/pdfreporter/repo/SubreportUtil.h"

static OrgOssPdfreporterEngineJasperReport *phaseReport = nil;

@implementation ReportExporter

+(void)exportReportToPdf:(NSString*)pdfPath withJrxml:(NSString*)jrxmlPath withResourceFolders:(NSArray*)resourceFolders withParameters:(NSDictionary *)parameters withSubreports:(NSDictionary *)subreports
{
    [OrgOssPdfreporterRegistryApiRegistry initSession];
    NSString *jrxmlFile = [ReportExporter setupJrxmlPath:jrxmlPath andResourceFolders:resourceFolders];
    
    OrgOssPdfreporterEngineJasperReport *report = [ReportExporter loadReport:jrxmlFile];
    
    NSDictionaryMap *parameterMap = [[NSDictionaryMap alloc] initWithDictionary:parameters];
    
    if ([[subreports allKeys] count] > 0) {
        [subreports enumerateKeysAndObjectsUsingBlock:^(NSString *key, NSString *value, BOOL *stop) {
            OrgOssPdfreporterEngineJasperReport *subreport = OrgOssPdfreporterRepoSubreportUtil_loadSubreportWithNSString_(value);
            [parameterMap putWithId:key withId:subreport];
        }];
    }
    
    OrgOssPdfreporterEngineJasperPrint *printReport = [ReportExporter fillReport:report withParameters:parameterMap];
    
    [OrgOssPdfreporterEngineJasperExportManager exportReportToPdfFileWithOrgOssPdfreporterEngineJasperPrint:printReport withNSString:pdfPath];
    [OrgOssPdfreporterRegistryApiRegistry dispose];
}

+(void)exportReportToPdf:(NSString*)pdfPath withJrxml:(NSString*)jrxmlPath withResourceFolders:(NSArray*)resourceFolders andSqlite3:(NSString*)sqlite3 withParameters:(NSDictionary *)parameters withSubreports:(NSDictionary *)subreports
{
    [OrgOssPdfreporterRegistryApiRegistry initSession];
    NSString *jrxmlFile = [ReportExporter setupJrxmlPath:jrxmlPath andResourceFolders:resourceFolders];
    
    OrgOssPdfreporterEngineJasperReport *report = [ReportExporter loadReport:jrxmlFile];
    
    if (![[NSFileManager defaultManager] fileExistsAtPath:sqlite3]) {
        id<OrgOssPdfreporterNetIURL> sqlUrl = OrgOssPdfreporterRepoFileResourceLoader_getURLWithNSString_(sqlite3);
            sqlite3 = [sqlUrl getPath];
    }
    
    NSDictionaryMap *parameterMap = [[NSDictionaryMap alloc] initWithDictionary:parameters];
    
    if ([[subreports allKeys] count] > 0) {
        [subreports enumerateKeysAndObjectsUsingBlock:^(NSString *key, NSString *value, BOOL *stop) {
            OrgOssPdfreporterEngineJasperReport *subreport = OrgOssPdfreporterRepoSubreportUtil_loadSubreportWithNSString_(value);
            [parameterMap putWithId:key withId:subreport];
        }];
    }
    
    OrgOssPdfreporterEngineJasperPrint *printReport = [ReportExporter fillReport:report withParameters:parameterMap withSqlite3:sqlite3];
    
    [OrgOssPdfreporterEngineJasperExportManager exportReportToPdfFileWithOrgOssPdfreporterEngineJasperPrint:printReport withNSString:pdfPath];
    [OrgOssPdfreporterRegistryApiRegistry dispose];
}

+(void)exportReportToPdf:(NSString*)pdfPath withJrxml:(NSString*)jrxmlPath withResourceFolders:(NSArray*)resourceFolders withXml:(NSString*)xmlFile andXPath:(NSString*)xPath withParameters:(NSDictionary *)parameters withSubreports:(NSDictionary *)subreports
{
    [OrgOssPdfreporterRegistryApiRegistry initSession];
    NSString *jrxmlFile = [ReportExporter setupJrxmlPath:jrxmlPath andResourceFolders:resourceFolders];
    
    OrgOssPdfreporterEngineJasperReport *report = [ReportExporter loadReport:jrxmlFile];
    NSDictionaryMap *parameterMap = [[NSDictionaryMap alloc] initWithDictionary:parameters];
    
    if ([[subreports allKeys] count] > 0) {
        [subreports enumerateKeysAndObjectsUsingBlock:^(NSString *key, NSString *value, BOOL *stop) {
            OrgOssPdfreporterEngineJasperReport *subreport = OrgOssPdfreporterRepoSubreportUtil_loadSubreportWithNSString_(value);
            [parameterMap putWithId:key withId:subreport];
        }];
    }
    
    OrgOssPdfreporterEngineJasperPrint *printReport = [ReportExporter fillReport:report withParameters:parameterMap withXml:xmlFile andXPath:xPath];
    
    [OrgOssPdfreporterEngineJasperExportManager exportReportToPdfFileWithOrgOssPdfreporterEngineJasperPrint:printReport withNSString:pdfPath];
    [OrgOssPdfreporterRegistryApiRegistry dispose];
}


#pragma mark - phased export

+(void)phaseLoadReportWithJrxml:(NSString*)jrxmlPath withResourceFolders:(NSArray*)resourceFolders
{
    if(phaseReport) [OrgOssPdfreporterRegistryApiRegistry dispose];
    [OrgOssPdfreporterRegistryApiRegistry initSession];
    NSString *jrxmlFile = [ReportExporter setupJrxmlPath:jrxmlPath andResourceFolders:resourceFolders];
    phaseReport = [ReportExporter loadReport:jrxmlFile];
}

+(void)phaseExportReportToPdf:(NSString*)pdfPath
{
    OrgOssPdfreporterEngineJasperPrint *printReport = [ReportExporter fillReport:phaseReport withParameters:nil];
    [OrgOssPdfreporterEngineJasperExportManager exportReportToPdfFileWithOrgOssPdfreporterEngineJasperPrint:printReport withNSString:pdfPath];
    [OrgOssPdfreporterRegistryApiRegistry dispose];
    phaseReport = nil;
}

+(void)phaseExportReportToPdf:(NSString*)pdfPath withXml:(NSString*)xmlFile andXPath:(NSString*)xPath
{
    OrgOssPdfreporterEngineJasperPrint *printReport = [ReportExporter fillReport:phaseReport withParameters:nil withXml:xmlFile andXPath:xPath];
    [OrgOssPdfreporterEngineJasperExportManager exportReportToPdfFileWithOrgOssPdfreporterEngineJasperPrint:printReport withNSString:pdfPath];
    [OrgOssPdfreporterRegistryApiRegistry dispose];
    phaseReport = nil;
}

+(void)phaseExportReportToPdf:(NSString*)pdfPath andSqlite3:(NSString*)sqlite3
{
    OrgOssPdfreporterEngineJasperPrint *printReport = [ReportExporter fillReport:phaseReport withParameters:nil withSqlite3:sqlite3];
    [OrgOssPdfreporterEngineJasperExportManager exportReportToPdfFileWithOrgOssPdfreporterEngineJasperPrint:printReport withNSString:pdfPath];
    [OrgOssPdfreporterRegistryApiRegistry dispose];
    phaseReport = nil;
}

+(OrgOssPdfreporterEngineJasperReport*)loadReport:(NSString*)jrxmlFile
{
    JavaIoInputStream *isReport = [OrgOssPdfreporterRepoFileResourceLoader getInputStreamWithNSString:jrxmlFile];
    OrgOssPdfreporterEngineDesignJasperDesign *design = [OrgOssPdfreporterEngineXmlJRXmlLoader load__WithJavaIoInputStream:isReport];
    [isReport close];
    return [OrgOssPdfreporterEngineJasperCompileManager compileReportWithOrgOssPdfreporterEngineDesignJasperDesign:design];
}

+(OrgOssPdfreporterEngineJasperPrint*)fillReport:(OrgOssPdfreporterEngineJasperReport*)report withParameters:(id<JavaUtilMap>)parameters
{
    return [OrgOssPdfreporterEngineJasperFillManager fillReportWithOrgOssPdfreporterEngineJasperReport:report withJavaUtilMap:parameters withOrgOssPdfreporterEngineJRDataSource:[[OrgOssPdfreporterEngineJREmptyDataSource alloc] init]];
}

+(OrgOssPdfreporterEngineJasperPrint*)fillReport:(OrgOssPdfreporterEngineJasperReport*)report withParameters:(id<JavaUtilMap>)parameters withXml:(NSString*)xmlFile andXPath:(NSString*)xPath
{
    JavaIoInputStream *isXmlData = [OrgOssPdfreporterRepoFileResourceLoader getInputStreamWithNSString:xmlFile];
    OrgOssPdfreporterEngineDataJRXmlDataSource *xmlDataSource = [[OrgOssPdfreporterEngineDataJRXmlDataSource alloc] initWithJavaIoInputStream:isXmlData withNSString:xPath];
    [xmlDataSource setDatePatternWithNSString:@"yyyy-MM-dd"];
    OrgOssPdfreporterEngineJasperPrint *print = [OrgOssPdfreporterEngineJasperFillManager fillReportWithOrgOssPdfreporterEngineJasperReport:report withJavaUtilMap:parameters withOrgOssPdfreporterEngineJRDataSource:xmlDataSource];
    [isXmlData close];
    return print;
}

+(OrgOssPdfreporterEngineJasperPrint*)fillReport:(OrgOssPdfreporterEngineJasperReport*)report withParameters:(id<JavaUtilMap>)parameters withSqlite3:(NSString*)sqlite3
{
    id<OrgOssPdfreporterSqlIConnection> sqlDataSource = [[OrgOssPdfreporterRegistryApiRegistry getSqlFactory] newConnectionWithNSString:sqlite3 withNSString:nil withNSString:nil];
    
    OrgOssPdfreporterEngineJasperPrint *print = [OrgOssPdfreporterEngineJasperFillManager fillReportWithOrgOssPdfreporterEngineJasperReport:report withJavaUtilMap:parameters withOrgOssPdfreporterSqlIConnection:sqlDataSource];
    return print;
}

+(NSString*)setupJrxmlPath:(NSString*)jrxmlPath andResourceFolders:(NSArray*)resourceFolders
{
    OrgOssPdfreporterRepoRepositoryManager *repositoryManager = [OrgOssPdfreporterRepoRepositoryManager getInstance];
    [repositoryManager reset];
    
    NSString *jrxmlFolder = [NSString string];
    
    NSArray *jrxmlPathComponents = [jrxmlPath pathComponents];
    for(int i=0; i<jrxmlPathComponents.count-1; i++)
    {
        jrxmlFolder = [jrxmlFolder stringByAppendingPathComponent:[jrxmlPathComponents objectAtIndex:i]];
    }
    
    [repositoryManager setDefaulReportFolderWithNSString:jrxmlFolder];
    
    BOOL first = YES;
    for(NSString* resourceFolder in resourceFolders)
    {
        if(first)
        {
            first = NO;
            [repositoryManager setDefaultResourceFolderWithNSString:resourceFolder];
        }
        else
        {
            [repositoryManager addExtraReportFolderWithNSString:resourceFolder];
        }
    }
    return [jrxmlPath lastPathComponent];
}

@end
