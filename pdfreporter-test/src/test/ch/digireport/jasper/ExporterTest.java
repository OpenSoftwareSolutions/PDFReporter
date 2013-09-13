package test.ch.digireport.jasper;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Logger;

import org.junit.Test;
import org.oss.pdfreporter.engine.JRExporterParameter;
import org.oss.pdfreporter.engine.JasperReport;
import org.oss.pdfreporter.engine.export.JRPdfExporterParameter;
import org.oss.pdfreporter.pdf.IDocument;
import org.oss.pdfreporter.repo.DigireportRepositoryManager;
import org.oss.pdfreporter.repo.SubreportUtil;


public class ExporterTest {
	// DRIVERS for Java
	private static final String HSQLDB_URLPREFIX = "jdbc:hsqldb:hsql://";
	private static final String HSQLDB_JDBC_DRIVER = "org.hsqldb.jdbcDriver";
	
	// FOLDERS 
	private static final String JRXML_RESOURCE_FOLDER = "../pdfreporter-testdata/resource";
	private static final String JRXML_REPORT_FOLDER = "../pdfreporter-testdata/testdata/jrxml";
	private static final String XML_DATASOURCE_FOLDER = "../pdfreporter-testdata/datasource";
	private static final String PDF_OUTPUT_FOLDER = "../pdfreporter-testdata/testdata/pdf";	
	
	// DESIGN REPORTS
	private static final String DESIGN_REPORT_FONTS = "FontsReport.jrxml";
	private static final String DESIGN_REPORT_SHIPMENTS = "ShipmentsReportSqlJeval.jrxml";
	private static final String DESIGN_REPORT_PRODUCTS = "ProductsReport.jrxml";
	private static final String DESIGN_REPORT_ORDERS = "OrdersReport.jrxml";
	private static final String DESIGN_REPORT_LATE_ORDERS = "LateOrdersReport.jrxml";
	
	private static final String DESIGN_REPORT_IMAGE = "ImagesReport.jrxml";
	private static final String DESIGN_REPORT_SHAPES = "ShapesReport.jrxml";
	private static final String DESIGN_REPORT_PARAGRAPH = "ParagraphsReport.jrxml";
	private static final String DESIGN_REPORT_STYLEDTEXT = "StyledTextReport.jrxml";
	private static final String DESIGN_REPORT_CDBOOCKLET = "CDBooklet.jrxml";
//	private static final String DESIGN_REPORT_JASPER = "FirstJasper.jrxml";
	private static final String DESIGN_REPORT_ROTATION = "RotationReport.jrxml";
	private static final String DESIGN_REPORT_PDFCRYPT = "PdfEncryptReport.jrxml";
	private static final String DESIGN_REPORT_MASTER = "MasterReport.jrxml";

	// XML DATA
	private static final String XML_DATA_CDBOOKLET = "CDBooklets.xml";
	private static final String XPATH_DATA_CDBOOKLET = "/CDBooklets";
	
	
	
	
	private static final String XML_DATA_NORTHWIND = "northwind.xml";
	private static final String XPATH_DATA_NORTHWIND_ORDERS = "/Northwind/Orders";
	private static final String XPATH_DATA_NORTHWIND_ORDERS_SHIPPED_NOT_NULL = "/Northwind/Orders[ShippedDate]";
	

	public ExporterTest() {
		this(true);
	}
	
	private ExporterTest(boolean initJava) {
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
	public void exportFonts() throws Exception {
		getExporter("fonts","extra-fonts").exportReport(DESIGN_REPORT_FONTS);
	}


	@Test
	public void exportShippment() throws Exception {
		getExporter("crosstabs","extra-fonts").exportSqlReport(DESIGN_REPORT_SHIPMENTS);
	}

	
//	@Test
//	public void exportFirstJasper() throws Exception {
//		ImageFactory.registerFactory();
//		IImage image = ApiRegistry.getImageFactory().getImageManager().loadImage("src/dukesign.jpg");
//		Map<String,Object> parameters = new HashMap<String,Object>();
//		parameters.put("ReportTitle", "The First Jasper Report Ever");
//		parameters.put("MaxOrderID", new Integer(10500));
//		parameters.put("SummaryImage", image);
//		getExporter("firstjasper","extra-fonts").exportSqlReport(DESIGN_REPORT_JASPER,parameters);
//	}
	
	@Test
	public void runMasterReport() throws Exception {
		ReportExporter exporter = getExporter("subreports","extra-fonts"); // initialize Repository
		JasperReport subreport = SubreportUtil.loadSubreport("ProductReport.jasper");
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("ProductsSubreport", subreport);		
		exporter.exportSqlReport(DESIGN_REPORT_MASTER, parameters);
	}
	
	@Test
	public void exportProducts() throws Exception {
		getExporter("crosstabs","extra-fonts").exportSqlReport(DESIGN_REPORT_PRODUCTS);
	}

	@Test
	public void exportOrders() throws Exception {
		getExporter("crosstabs","extra-fonts").exportReport(DESIGN_REPORT_ORDERS,XML_DATA_NORTHWIND,XPATH_DATA_NORTHWIND_ORDERS);
	}

	@Test
	public void exportLateOrder() throws Exception {
		getExporter("crosstabs","extra-fonts").exportReport(DESIGN_REPORT_LATE_ORDERS,XML_DATA_NORTHWIND,XPATH_DATA_NORTHWIND_ORDERS_SHIPPED_NOT_NULL);
	}
		
	@Test
	public void exportImages() throws Exception {
		getExporter("images").exportReport(DESIGN_REPORT_IMAGE);
	}
			
	@Test
	public void exportShapes() throws Exception {
		getExporter("shapes").exportReport(DESIGN_REPORT_SHAPES);
	}
	
	@Test
	public void exportRotation() throws Exception {
		getExporter("misc").exportReport(DESIGN_REPORT_ROTATION);
	}
	
	@Test
	public void exportEncrypt() throws Exception {
		Map<JRExporterParameter,Object> parameters = new HashMap<JRExporterParameter,Object>();
		parameters.put(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);		
		parameters.put(JRPdfExporterParameter.IS_128_BIT_KEY, Boolean.TRUE);		
		parameters.put(JRPdfExporterParameter.USER_PASSWORD, "jasper");		
		parameters.put(JRPdfExporterParameter.OWNER_PASSWORD, "reports");		
		parameters.put(JRPdfExporterParameter.PERMISSIONS, IDocument.PERMISSION_COPY | IDocument.PERMISSION_PRINT );				
		getExporter("misc").exportReport(DESIGN_REPORT_PDFCRYPT, parameters);
	}
	
	@Test
	public void exportParagraph() throws Exception {
		getExporter("text","extra-fonts").exportReport(DESIGN_REPORT_PARAGRAPH);
	}
	
	@Test
	public void exportStyledText() throws Exception {
		getExporter("text","extra-fonts").exportReport(DESIGN_REPORT_STYLEDTEXT);
	}
		
	@Test
	public void exportCDBooklet() throws Exception {
		getExporter("cdbooklet","extra-fonts").exportReport(DESIGN_REPORT_CDBOOCKLET, XML_DATA_CDBOOKLET, XPATH_DATA_CDBOOKLET);
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
