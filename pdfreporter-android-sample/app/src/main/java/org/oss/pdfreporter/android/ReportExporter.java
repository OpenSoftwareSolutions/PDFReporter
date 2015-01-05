package org.oss.pdfreporter.android;

import java.io.IOException;
import java.io.InputStream;

import org.oss.pdfreporter.engine.JRDataSource;
import org.oss.pdfreporter.engine.JREmptyDataSource;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JasperCompileManager;
import org.oss.pdfreporter.engine.JasperExportManager;
import org.oss.pdfreporter.engine.JasperFillManager;
import org.oss.pdfreporter.engine.JasperPrint;
import org.oss.pdfreporter.engine.JasperReport;
import org.oss.pdfreporter.engine.data.JRXmlDataSource;
import org.oss.pdfreporter.engine.design.JasperDesign;
import org.oss.pdfreporter.engine.xml.JRXmlLoader;
import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.repo.FileResourceLoader;
import org.oss.pdfreporter.repo.RepositoryManager;
import org.oss.pdfreporter.sql.IConnection;
import org.oss.pdfreporter.sql.SQLException;

import android.util.Log;

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
	
	public static JasperPrint fillReport(JasperReport report, String sqlite3) throws JRException, SQLException {
		IConnection sqlDataSource = ApiRegistry.getSqlFactory().newConnection(sqlite3, null, null);
		return JasperFillManager.fillReport(report, null, sqlDataSource);
	}
		
	/**
	 * Export to PDF (no data source)
	 * @param pdfPath
	 * @param jrxmlPath
	 * @param resourceFolders
	 * @throws JRException
	 * @throws IOException
	 */
	public static void exportReportToPdf(String pdfPath, String jrxmlPath, String[] resourceFolders) throws JRException, IOException {
		ApiRegistry.initSession();
		String jrxmlFile = ReportExporter.setupJrxmlPath(jrxmlPath, resourceFolders);
		
		JasperReport report = ReportExporter.loadReport(jrxmlFile);
		JasperPrint printReport = ReportExporter.fillReport(report);
		
		JasperExportManager.exportReportToPdfFile(printReport, pdfPath);
		ApiRegistry.dispose();
	}
	
	/**
	 * Export to PDF with SQL data source
	 * @param pdfPath
	 * @param jrxmlPath
	 * @param resourceFolders
	 * @param sqlite3
	 * @throws JRException
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void exportReportToPdfSql(String pdfPath, String jrxmlPath, String[] resourceFolders, String sqlite3) throws JRException, IOException, SQLException {
		ApiRegistry.initSession();
		String jrxmlFile = ReportExporter.setupJrxmlPath(jrxmlPath, resourceFolders);
		
		JasperReport report = ReportExporter.loadReport(jrxmlFile);
		JasperPrint printReport = ReportExporter.fillReport(report, sqlite3);
		
		JasperExportManager.exportReportToPdfFile(printReport, pdfPath);
		ApiRegistry.dispose();
	}
	
	/**
	 * Export to PDF with XML data source
	 * @param pdfPath
	 * @param jrxmlPath
	 * @param resourceFolders
	 * @param xmlDataPath
	 * @param xpath
	 * @throws JRException
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void exportReportToPdfXml(String pdfPath, String jrxmlPath, String[] resourceFolders, String xmlDataPath, String xpath) throws JRException, IOException, SQLException {
		ApiRegistry.initSession();
		String jrxmlFile = ReportExporter.setupJrxmlPath(jrxmlPath, resourceFolders);
		
		JasperReport report = ReportExporter.loadReport(jrxmlFile);

		JRDataSource dataSource = null;
		InputStream isXmlData = null;
		
		if (xmlDataPath == null) {
			dataSource = new JREmptyDataSource();
		} else {
			isXmlData = FileResourceLoader.getInputStream(xmlDataPath);
			JRXmlDataSource xmlDataSource = new JRXmlDataSource(isXmlData, xpath);
			xmlDataSource.setDatePattern("yyyy-MM-dd");
			dataSource = xmlDataSource;
		}
		JasperPrint printReport = JasperFillManager.fillReport(report, null, dataSource);		
		
		JasperExportManager.exportReportToPdfFile(printReport, pdfPath);
		ApiRegistry.dispose();
	}

}
