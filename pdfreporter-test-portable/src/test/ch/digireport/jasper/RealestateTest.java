package test.ch.digireport.jasper;

import java.util.logging.Handler;
import java.util.logging.Logger;

import org.junit.Test;
import org.oss.pdfreporter.repo.DigireportRepositoryManager;


public class RealestateTest {
	// DRIVERS for Java
	private static final String HSQLDB_URLPREFIX = "jdbc:hsqldb:hsql://";
	private static final String HSQLDB_JDBC_DRIVER = "org.hsqldb.jdbcDriver";
	
	// FOLDERS 
	private static final String JRXML_RESOURCE_FOLDER = "../pdfreporter-testdata/resource";
	private static final String JRXML_REPORT_FOLDER = "../pdfreporter-testdata/testdata/jrxml";
	private static final String XML_DATASOURCE_FOLDER = "../pdfreporter-testdata/datasource";
	private static final String PDF_OUTPUT_FOLDER = "../pdfreporter-testdata/testdata/pdf";	
	
	// DESIGN REPORTS
	private static final String DESIGN_REPORT_REALESTATE_CHEKLIST_IN_DE = "realestate-checklist-movein-de.jrxml";
	private static final String DESIGN_REPORT_REALESTATE_CHEKLIST_OUT_DE = "realestate-checklist-moveout-de.jrxml";
	private static final String DESIGN_REPORT_REALESTATE_DEFECT_DE = "realestate-defectgallery-de.jrxml";

	private static final String DESIGN_REPORT_REALESTATE_CHEKLIST_IN_EN = "realestate-checklist-movein-en.jrxml";
	private static final String DESIGN_REPORT_REALESTATE_CHEKLIST_OUT_EN = "realestate-checklist-moveout-en.jrxml";
	private static final String DESIGN_REPORT_REALESTATE_DEFECT_EN = "realestate-defectgallery-en.jrxml";

	

	public RealestateTest() {
		this(true);
	}
	
	private RealestateTest(boolean initJava) {
		if (initJava) {
			try {
				Class<?>[] noArgs = new Class[0];
				Object noInstance = null;
				Object[] noParams = new Object[0];
				Class<?> bridgeHandler = Class.forName("org.slf4j.bridge.SLF4JBridgeHandler");
				bridgeHandler.getMethod("removeHandlersForRootLogger", noArgs).invoke(noInstance, noParams);
				bridgeHandler.getMethod("install", noArgs).invoke(noInstance, noParams);
				Class<?> sqlFactory = Class.forName("org.oss.pdfreporter.sql.SqlFactory");
				sqlFactory.getMethod("registerFactory", new Class<?>[] {String.class,String.class}).invoke(noInstance, new Object[] {HSQLDB_JDBC_DRIVER, HSQLDB_URLPREFIX});
			} catch (Exception e) {
				throw new RuntimeException(e);
			} 
		} else {
			try {
			
			Logger logger = Logger.getLogger("");
			Handler[] handlers = logger.getHandlers();
			for(Handler hndl :handlers) {
				logger.removeHandler(hndl);
			}
			Handler handler = (Handler) Class.forName("CustomJavaUtilLoggingHandler").newInstance();
			logger.addHandler(handler);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} 
		}
	}
	
	
	@Test
	public void exportRealEstateChecklistMoveInDe() throws Exception {
		getExporter("digireport-realestate-checklist-v0").exportSqlReport(DESIGN_REPORT_REALESTATE_CHEKLIST_IN_DE);
	}
	@Test
	public void exportRealEstateChecklistMoveOutDe() throws Exception {
		getExporter("digireport-realestate-checklist-v0").exportSqlReport(DESIGN_REPORT_REALESTATE_CHEKLIST_OUT_DE);
	}
	@Test
	public void exportRealEstateDefectDe() throws Exception {
		getExporter("digireport-realestate-checklist-v0").exportSqlReport(DESIGN_REPORT_REALESTATE_DEFECT_DE);
	}
	@Test
	public void exportRealEstateChecklistMoveInEn() throws Exception {
		getExporter("digireport-realestate-checklist-v0").exportSqlReport(DESIGN_REPORT_REALESTATE_CHEKLIST_IN_EN);
	}
	@Test
	public void exportRealEstateChecklistMoveOutEn() throws Exception {
		getExporter("digireport-realestate-checklist-v0").exportSqlReport(DESIGN_REPORT_REALESTATE_CHEKLIST_OUT_EN);
	}
	@Test
	public void exportRealEstateDefectEn() throws Exception {
		getExporter("digireport-realestate-checklist-v0").exportSqlReport(DESIGN_REPORT_REALESTATE_DEFECT_EN);
	}
	
	private static ReportExporter getExporter(String reportFolder) {
		return getExporter(reportFolder,null);
	}
	
	private static ReportExporter getExporter(String reportFolder, String extraFolder) {
		DigireportRepositoryManager repo = DigireportRepositoryManager.getInstance();
		repo.setDefaultResourceFolder(bundlePath(JRXML_RESOURCE_FOLDER));
		repo.setDefaulReportFolder(bundlePath(JRXML_REPORT_FOLDER + DigireportRepositoryManager.PATH_DELIMITER + reportFolder));
		if (null != extraFolder) {
			repo.addExtraReportFolder(bundlePath(JRXML_REPORT_FOLDER + DigireportRepositoryManager.PATH_DELIMITER + extraFolder));
		}
		repo.addExtraReportFolder(bundlePath(XML_DATASOURCE_FOLDER));
		
		return new ReportExporter(documentsPath(PDF_OUTPUT_FOLDER));
	}
	
	public static String bundlePath(String path) {
		return path;
	}
	
	public static String documentsPath(String path) {
		return path;
	}
	
		
}
