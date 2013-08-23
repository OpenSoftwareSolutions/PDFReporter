package test.ch.digireport.jasper;

import java.io.Closeable;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.engine.JRDataSource;
import org.oss.pdfreporter.engine.JREmptyDataSource;
import org.oss.pdfreporter.engine.JasperCompileManager;
import org.oss.pdfreporter.engine.JasperExportManager;
import org.oss.pdfreporter.engine.JasperFillManager;
import org.oss.pdfreporter.engine.JasperPrint;
import org.oss.pdfreporter.engine.JasperReport;
import org.oss.pdfreporter.engine.data.JRXmlDataSource;
import org.oss.pdfreporter.engine.design.JasperDesign;
import org.oss.pdfreporter.engine.xml.JRXmlLoader;
import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.repo.DigireportFileResourceLoader;
import org.oss.pdfreporter.sql.IConnection;
import org.oss.pdfreporter.sql.factory.ISqlFactory;


public class ReportExporter {
	
	private final String pdfOutputFolder;
	
	public ReportExporter(String pdfOutputFolder) {
		super();
		this.pdfOutputFolder = pdfOutputFolder;
		Logger.getLogger("").setLevel(Level.FINEST);
		ApiRegistry.initSession();
	}
	
	public void exportReport(String reportFileName, String xmlDataFile, String xmlXpath) throws Exception{
		JasperDesign design = loadReport(reportFileName);
		JasperReport report = compileReport(design);
		exportReport(report, xmlDataFile, xmlXpath);
		ApiRegistry.dispose();
	}
	
	public void exportSqlReport(String reportFileName) throws Exception{
		exportSqlReport(reportFileName,null);
	}

	public void exportSqlReport(String reportFileName, Map<String,Object> params) throws Exception{
		JasperDesign design = loadReport(reportFileName);
		JasperReport report = compileReport(design);
		exportSqlReport(report,params);
		ApiRegistry.dispose();
	}
	
	public void exportReport(String reportFileName) throws Exception{
		exportReport(reportFileName,null,null);
	}
	
	public JasperDesign loadReport(String reportFileName) throws Exception {
		InputStream isReport = null;
		try {
			isReport = DigireportFileResourceLoader.getInputStream(reportFileName);
			return JRXmlLoader.load(isReport);
		} finally {
			close(isReport);
		}
	}
	
	public JasperReport compileReport(JasperDesign design) throws Exception {
		return JasperCompileManager.compileReport(design);
	}
	
	public void exportReport(JasperReport compiledReport, String xmlDataFile, String xmlXpath) throws Exception {
		JRDataSource dataSource = null;
		InputStream isXmlData = null;
		try {
			if (xmlDataFile == null) {
				dataSource = new JREmptyDataSource();
			} else {
				isXmlData = DigireportFileResourceLoader.getInputStream(xmlDataFile);
				JRXmlDataSource xmlDataSource = new JRXmlDataSource(isXmlData, xmlXpath);
				xmlDataSource.setDatePattern("yyyy-MM-dd");
				dataSource = xmlDataSource;
			}
			JasperPrint printReport = JasperFillManager.fillReport(compiledReport, null, dataSource);
			String pathToPdfFile = pdfOutputFolder + "/" + printReport.getName() + ".pdf";
            JasperExportManager.exportReportToPdfFile(printReport, pathToPdfFile);
		} finally {
			close(isXmlData);
		}
	}
	
	public void exportSqlReport(JasperReport compiledReport, Map<String,Object> params) throws Exception {
		IConnection sqlDataSource = null;
		ISqlFactory sqlFactory = ApiRegistry.getSqlFactory();
		try {
			sqlDataSource = sqlFactory.newConnection("localhost", "sa", "");
			JasperPrint printReport = JasperFillManager.fillReport(compiledReport, params, sqlDataSource);
			String pathToPdfFile = pdfOutputFolder + "/" + printReport.getName() + ".pdf";
            JasperExportManager.exportReportToPdfFile(printReport, pathToPdfFile);
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
