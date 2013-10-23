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

static OrgOssPdfreporterEngineJasperReport *phaseReport = nil;

@implementation ReportExporter

+(void)exportReportToPdf:(NSString*)pdfPath withJrxml:(NSString*)jrxmlPath withResourceFolders:(NSArray*)resourceFolders
{
    [OrgOssPdfreporterRegistryApiRegistry initSession];
    NSString *jrxmlFile = [ReportExporter setupJrxmlPath:jrxmlPath andResourceFolders:resourceFolders];
        
    OrgOssPdfreporterEngineJasperReport *report = [ReportExporter loadReport:jrxmlFile];        
    OrgOssPdfreporterEngineJasperPrint *printReport = [ReportExporter fillReport:report];
    
    [OrgOssPdfreporterEngineJasperExportManager exportReportToPdfFileWithOrgOssPdfreporterEngineJasperPrint:printReport withNSString:pdfPath];
    [OrgOssPdfreporterRegistryApiRegistry dispose];
}

+(void)exportReportToPdf:(NSString*)pdfPath withJrxml:(NSString*)jrxmlPath withResourceFolders:(NSArray*)resourceFolders andSqlite3:(NSString*)sqlite3
{
    [OrgOssPdfreporterRegistryApiRegistry initSession];
    NSString *jrxmlFile = [ReportExporter setupJrxmlPath:jrxmlPath andResourceFolders:resourceFolders];
    
    OrgOssPdfreporterEngineJasperReport *report = [ReportExporter loadReport:jrxmlFile];
    OrgOssPdfreporterEngineJasperPrint *printReport = [ReportExporter fillReport:report withSqlite3:sqlite3];
    
    [OrgOssPdfreporterEngineJasperExportManager exportReportToPdfFileWithOrgOssPdfreporterEngineJasperPrint:printReport withNSString:pdfPath];
    [OrgOssPdfreporterRegistryApiRegistry dispose];
}

+(void)exportReportToPdf:(NSString*)pdfPath withJrxml:(NSString*)jrxmlPath withResourceFolders:(NSArray*)resourceFolders withXml:(NSString*)xmlFile andXPath:(NSString*)xPath
{
    [OrgOssPdfreporterRegistryApiRegistry initSession];
    NSString *jrxmlFile = [ReportExporter setupJrxmlPath:jrxmlPath andResourceFolders:resourceFolders];
    
    OrgOssPdfreporterEngineJasperReport *report = [ReportExporter loadReport:jrxmlFile];
    OrgOssPdfreporterEngineJasperPrint *printReport = [ReportExporter fillReport:report withXml:xmlFile andXPath:xPath];
    
    [OrgOssPdfreporterEngineJasperExportManager exportReportToPdfFileWithOrgOssPdfreporterEngineJasperPrint:printReport withNSString:pdfPath];
    [OrgOssPdfreporterRegistryApiRegistry dispose];
}

+(void)phaseLoadReportWithJrxml:(NSString*)jrxmlPath withResourceFolders:(NSArray*)resourceFolders
{
    if(phaseReport) [OrgOssPdfreporterRegistryApiRegistry dispose];
    [OrgOssPdfreporterRegistryApiRegistry initSession];
    NSString *jrxmlFile = [ReportExporter setupJrxmlPath:jrxmlPath andResourceFolders:resourceFolders];
    phaseReport = [ReportExporter loadReport:jrxmlFile];
}

+(void)phaseExportReportToPdf:(NSString*)pdfPath
{
    OrgOssPdfreporterEngineJasperPrint *printReport = [ReportExporter fillReport:phaseReport];
    [OrgOssPdfreporterEngineJasperExportManager exportReportToPdfFileWithOrgOssPdfreporterEngineJasperPrint:printReport withNSString:pdfPath];
    [OrgOssPdfreporterRegistryApiRegistry dispose];
    phaseReport = nil;
}

+(void)phaseExportReportToPdf:(NSString*)pdfPath withXml:(NSString*)xmlFile andXPath:(NSString*)xPath
{
    OrgOssPdfreporterEngineJasperPrint *printReport = [ReportExporter fillReport:phaseReport withXml:xmlFile andXPath:xPath];
    [OrgOssPdfreporterEngineJasperExportManager exportReportToPdfFileWithOrgOssPdfreporterEngineJasperPrint:printReport withNSString:pdfPath];
    [OrgOssPdfreporterRegistryApiRegistry dispose];
    phaseReport = nil;
}

+(void)phaseExportReportToPdf:(NSString*)pdfPath andSqlite3:(NSString*)sqlite3
{
    OrgOssPdfreporterEngineJasperPrint *printReport = [ReportExporter fillReport:phaseReport withSqlite3:sqlite3];
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

+(OrgOssPdfreporterEngineJasperPrint*)fillReport:(OrgOssPdfreporterEngineJasperReport*)report
{
    return [OrgOssPdfreporterEngineJasperFillManager fillReportWithOrgOssPdfreporterEngineJasperReport:report withJavaUtilMap:nil withOrgOssPdfreporterEngineJRDataSource:[[OrgOssPdfreporterEngineJREmptyDataSource alloc] init]];
}

+(OrgOssPdfreporterEngineJasperPrint*)fillReport:(OrgOssPdfreporterEngineJasperReport*)report withXml:(NSString*)xmlFile andXPath:(NSString*)xPath
{
    JavaIoInputStream *isXmlData = [OrgOssPdfreporterRepoFileResourceLoader getInputStreamWithNSString:xmlFile];
    OrgOssPdfreporterEngineDataJRXmlDataSource *xmlDataSource = [[OrgOssPdfreporterEngineDataJRXmlDataSource alloc] initWithJavaIoInputStream:isXmlData withNSString:xPath];
    [xmlDataSource setDatePatternWithNSString:@"yyyy-MM-dd"];
    OrgOssPdfreporterEngineJasperPrint *print = [OrgOssPdfreporterEngineJasperFillManager fillReportWithOrgOssPdfreporterEngineJasperReport:report withJavaUtilMap:nil withOrgOssPdfreporterEngineJRDataSource:xmlDataSource];
    [isXmlData close];
    return print;
}

+(OrgOssPdfreporterEngineJasperPrint*)fillReport:(OrgOssPdfreporterEngineJasperReport*)report withSqlite3:(NSString*)sqlite3
{
    id<OrgOssPdfreporterSqlIConnection> sqlDataSource = [[OrgOssPdfreporterRegistryApiRegistry getSqlFactory] newConnectionWithNSString:sqlite3 withNSString:nil withNSString:nil];
    
    OrgOssPdfreporterEngineJasperPrint *print = [OrgOssPdfreporterEngineJasperFillManager fillReportWithOrgOssPdfreporterEngineJasperReport:report withJavaUtilMap:nil withOrgOssPdfreporterSqlIConnection:sqlDataSource];
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
