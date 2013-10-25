/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package test.org.oss.pdfreporter;

import java.io.Closeable;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.engine.JRDataSource;
import org.oss.pdfreporter.engine.JREmptyDataSource;
import org.oss.pdfreporter.engine.JRExporterParameter;
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
import org.oss.pdfreporter.sql.IConnection;
import org.oss.pdfreporter.sql.factory.ISqlFactory;


public class ReportExporter {
	
	private final String pdfOutputFolder;
	private final String databasePath;
	
	public ReportExporter(String pdfOutputFolder, String databasePath) {
		super();
		this.pdfOutputFolder = pdfOutputFolder;
		this.databasePath = databasePath;
		Logger.getLogger("").setLevel(Level.FINEST);
		ApiRegistry.initSession();
	}
	
	public void exportReport(String reportFileName, String xmlDataFile, String xmlXpath, Map<JRExporterParameter, Object> exporterParameters) throws Exception{
		JasperDesign design = loadReport(reportFileName);
		JasperReport report = compileReport(design);
		exportReport(report, xmlDataFile, xmlXpath,exporterParameters);
		ApiRegistry.dispose();
	}

	public void exportReport(String reportFileName, String xmlDataFile, String xmlXpath) throws Exception{
		exportReport(reportFileName, xmlDataFile, xmlXpath, null);
	}
	
	
	public void exportSqlReport(String reportFileName) throws Exception{
		exportSqlReport(reportFileName,null,null);
	}

	public void exportSqlReport(String reportFileName, Map<String,Object> fillParameters) throws Exception{
		exportSqlReport(reportFileName,fillParameters,null);
	}
	
	public void exportSqlReport(String reportFileName, Map<String,Object> fillParameters, Map<JRExporterParameter, Object> exporterParameters) throws Exception{
		JasperDesign design = loadReport(reportFileName);
		JasperReport report = compileReport(design);
		exportSqlReport(report,fillParameters,exporterParameters);
		ApiRegistry.dispose();
	}
	
	public void exportReport(String reportFileName, Map<JRExporterParameter, Object> exporterParameters) throws Exception{
		exportReport(reportFileName,null,null, exporterParameters);
	}
	
	public void exportReport(String reportFileName) throws Exception{
		exportReport(reportFileName,null,null, null);
	}
	
	public JasperDesign loadReport(String reportFileName) throws Exception {
		InputStream isReport = null;
		try {
			isReport = FileResourceLoader.getInputStream(reportFileName);
			return JRXmlLoader.load(isReport);
		} finally {
			close(isReport);
		}
	}
	
	public JasperReport compileReport(JasperDesign design) throws Exception {
		return JasperCompileManager.compileReport(design);
	}
	
	public void exportReport(JasperReport compiledReport, String xmlDataFile, String xmlXpath, Map<JRExporterParameter, Object> exporterParameters) throws Exception {
		JRDataSource dataSource = null;
		InputStream isXmlData = null;
		try {
			if (xmlDataFile == null) {
				dataSource = new JREmptyDataSource();
			} else {
				isXmlData = FileResourceLoader.getInputStream(xmlDataFile);
				JRXmlDataSource xmlDataSource = new JRXmlDataSource(isXmlData, xmlXpath);
				xmlDataSource.setDatePattern("yyyy-MM-dd");
				dataSource = xmlDataSource;
			}
			JasperPrint printReport = JasperFillManager.fillReport(compiledReport, null, dataSource);
			String pathToPdfFile = pdfOutputFolder + "/" + printReport.getName() + ".pdf";
            JasperExportManager.exportReportToPdfFile(printReport, pathToPdfFile,exporterParameters);
		} finally {
			close(isXmlData);
		}
	}
	
	public void exportSqlReport(JasperReport compiledReport, Map<String,Object> fillParameters, Map<JRExporterParameter, Object> exporterParameters) throws Exception {
		IConnection sqlDataSource = null;
		ISqlFactory sqlFactory = ApiRegistry.getSqlFactory();
		try {
			sqlDataSource = sqlFactory.newConnection(databasePath, "sa", "");
			JasperPrint printReport = JasperFillManager.fillReport(compiledReport, fillParameters, sqlDataSource);
			String pathToPdfFile = pdfOutputFolder + "/" + printReport.getName() + ".pdf";
            JasperExportManager.exportReportToPdfFile(printReport, pathToPdfFile,exporterParameters);
		} finally {
			close(sqlDataSource);
		}
	}
		
	private void close(Closeable stream) throws Exception {
		if (stream!=null) {
			stream.close();
		}
	}
	
}
