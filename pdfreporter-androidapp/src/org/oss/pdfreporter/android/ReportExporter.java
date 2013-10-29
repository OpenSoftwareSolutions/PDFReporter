package org.oss.pdfreporter.android;

import java.io.IOException;
import java.io.InputStream;

import org.oss.pdfreporter.engine.JREmptyDataSource;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JasperCompileManager;
import org.oss.pdfreporter.engine.JasperExportManager;
import org.oss.pdfreporter.engine.JasperFillManager;
import org.oss.pdfreporter.engine.JasperPrint;
import org.oss.pdfreporter.engine.JasperReport;
import org.oss.pdfreporter.engine.design.JasperDesign;
import org.oss.pdfreporter.engine.xml.JRXmlLoader;
import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.repo.FileResourceLoader;
import org.oss.pdfreporter.repo.RepositoryManager;

public class ReportExporter {
	
	public static String setupJrxmlPath(String jrxmlPath, String[] resourceFolders) {
		RepositoryManager repositoryManager = RepositoryManager.getInstance();
		repositoryManager.reset();
		String jrxmlFolder = jrxmlPath.substring(0, jrxmlPath.lastIndexOf('/'));
		repositoryManager.setDefaulReportFolder(jrxmlFolder);
		
		boolean first = true;
		for (String resourceFolder : resourceFolders) {
			if(first) {
				first = false;
				repositoryManager.setDefaultResourceFolder(resourceFolder);
			}
			else {
				repositoryManager.addExtraReportFolder(resourceFolder);
			}
		}
		
		return jrxmlPath.substring(jrxmlPath.lastIndexOf('/')+1);
	}
	
	public static JasperReport loadReport(String jrxmlFile) throws JRException, IOException {
		InputStream isReport = FileResourceLoader.getInputStream(jrxmlFile);
		JasperDesign design = JRXmlLoader.load(isReport);
		isReport.close();
		return JasperCompileManager.compileReport(design);
	}
	
	public static JasperPrint fillReport(JasperReport report) throws JRException {
		return JasperFillManager.fillReport(report, null, new JREmptyDataSource());
	}
	
	public static void exportReportToPdf(String pdfPath, String jrxmlPath, String[] resourceFolders) throws JRException, IOException {
		ApiRegistry.initSession();
		String jrxmlFile = ReportExporter.setupJrxmlPath(jrxmlPath, resourceFolders);
		
		JasperReport report = ReportExporter.loadReport(jrxmlFile);
		JasperPrint printReport = ReportExporter.fillReport(report);
		
		JasperExportManager.exportReportToPdfFile(printReport, pdfPath);
		ApiRegistry.dispose();
	}
	
	/*static OrgOssPdfreporterEngineJasperReport *phaseReport = nil;

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

	*/
}
