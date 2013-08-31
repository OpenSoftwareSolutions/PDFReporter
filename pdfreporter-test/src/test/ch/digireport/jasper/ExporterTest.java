package test.ch.digireport.jasper;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Logger;

import org.junit.Test;
import org.oss.pdfreporter.engine.JasperReport;
import org.oss.pdfreporter.image.IImage;
import org.oss.pdfreporter.image.ImageFactory;
import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.repo.DigireportRepositoryManager;
import org.oss.pdfreporter.repo.SubreportUtil;
import org.oss.pdfreporter.sql.IDate;


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
	private static final String DESIGN_REPORT_SHIPMENTS_XML_JEVAL = "ShipmentsReportXmlJeval.jrxml";
	private static final String DESIGN_REPORT_SHIPMENTS_SQL_JEVAL = "ShipmentsReportSqlJeval.jrxml";
	private static final String DESIGN_REPORT_SHIPMENTS_SQL_WITH_PARAM = "ShipmentsReportSqlJevalWithDateParam.jrxml";
	private static final String DESIGN_REPORT_PRODUCTS = "ProductsReport.jrxml";
	private static final String DESIGN_REPORT_ORDERS = "OrdersReport.jrxml";
	private static final String DESIGN_REPORT_LATE_ORDERS_JEVAL = "LateOrdersReportJeval.jrxml";
	private static final String DESIGN_REPORT_LATE_ORDERS_JEVAL_NO_SUMMARY = "LateOrdersReportJevalNoSummary.jrxml";
	
	private static final String DESIGN_REPORT_IMAGE = "ImagesReport.jrxml";
	private static final String DESIGN_REPORT_URLIMAGE = "ImagesWithUrlReport.jrxml";
	private static final String DESIGN_REPORT_SHAPES = "ShapesReport.jrxml";
	private static final String DESIGN_REPORT_IMAGE_JEVAL = "ImagesReport-JEval.jrxml";
	private static final String DESIGN_REPORT_URLIMAGE_JEVAL = "ImagesWithUrlReport-JEval.jrxml";
	private static final String DESIGN_REPORT_SHAPES_JEVAL = "ShapesReport-JEval.jrxml";
	private static final String DESIGN_REPORT_PARAGRAPH = "ParagraphsReport.jrxml";
	private static final String DESIGN_REPORT_STYLEDTEXT = "StyledTextReport.jrxml";
	private static final String DESIGN_REPORT_PARAGRAPH_JEVAL = "ParagraphsReport-JEval.jrxml";
	private static final String DESIGN_REPORT_STYLEDTEXT_JEVAL = "StyledTextReport-JEval.jrxml";
	private static final String DESIGN_REPORT_CDBOOCKLET = "CDBooklet.jrxml";
	private static final String DESIGN_REPORT_CDBOOCKLET_JEVAL = "CDBooklet-JEval.jrxml";
	private static final String DESIGN_REPORT_JASPER = "FirstJasper.jrxml";
	private static final String DESIGN_REPORT_ROTATION = "RotationReport.jrxml";
	private static final String DESIGN_REPORT_PDFCRYPT = "PdfEncryptReport.jrxml";
	private static final String DESIGN_REPORT_MASTER = "MasterReport.jrxml";
	
	
	private static final String DESIGN_REPORT_REALESTATE_JEVAL = "firstrealestateReport.jrxml";
	private static final String DESIGN_REPORT_REALESTATE_DEFECTGALLERY_JEVAL = "realestate-defectgallery.jrxml";
	private static final String DESIGN_REPORT_REALESTATE_PROTOCOL_JEVAL = "realestate-protocol.jrxml";
	private static final String DESIGN_REPORT_REALESTATE_SIGNING_JEVAL = "realestate-signing.jrxml";

	private static final String DESIGN_REPORT_REALESTATE_CHEKLIST_IN_DE = "realestate-checklist-movein-de.jrxml";
	private static final String DESIGN_REPORT_REALESTATE_CHEKLIST_OUT_DE = "realestate-checklist-moveout-de.jrxml";
	private static final String DESIGN_REPORT_REALESTATE_DEFECT_DE = "realestate-defectgallery-de.jrxml";

	private static final String DESIGN_REPORT_REALESTATE_CHEKLIST_IN_EN = "realestate-checklist-movein-en.jrxml";
	private static final String DESIGN_REPORT_REALESTATE_CHEKLIST_OUT_EN = "realestate-checklist-moveout-en.jrxml";
	private static final String DESIGN_REPORT_REALESTATE_DEFECT_EN = "realestate-defectgallery-en.jrxml";
	
	private static final String DESIGN_REPORT_REALESTATE_PROTOCOL_SUBREPORT_JEVAL = "realestate-protocol-subreport.jrxml";
//	private static final String DESIGN_REPORT_CROSSTAB[] = new String[] {"data/LateOrdersReport.jrxml","data/ShipmentsReport.jrxml","data/OrdersReport.jrxml","data/ProductsReport.jrxml"};
//	private static final String DESIGN_REPORT_CUSTOMERS = "data/CustomersReport.jrxml";

	// XML DATA
	private static final String XML_DATA_CDBOOKLET = "CDBooklets.xml";
	private static final String XPATH_DATA_CDBOOKLET = "/CDBooklets";
	
	private static final String XML_DATA_REALESTATE = "realestate.xml";
	private static final String XPATH_DATA_REALESTATE = "/protocolInstance/protocolKind/category/column";
	private static final String XML_DATA_REALESTATE_PROTOCOL = "realestate-protocol.xml";
	private static final String XPATH_DATA_REALESTATE_PROTOCOL = "/protocolInstance/protocolKind/*/column";
	private static final String XPATH_DATA_REALESTATE_SIGNING = "/protocolInstance/protocolKind/*/sign";
	private static final String XPATH_DATA_REALESTATE_DEFECTGALLERY = "/protocolInstance/protocolKind/*/column/images";
	
	private static final String XML_DATA_REALESTATE_CHECKLIST = "digireport-realestate-checklist-v0.xml";
	private static final String XPATH_DATA_REALESTATE_CHECKLIST = "/protocolKind/*/column";
	private static final String XPATH_DATA_REALESTATE_DEFECT = "/protocolKind/*/column/images";
	
	
	private static final String XML_DATA_NORTHWIND = "northwind.xml";
	private static final String XPATH_DATA_NORTHWIND_ORDERS = "/Northwind/Orders";
	private static final String XPATH_DATA_NORTHWIND_ORDERS_SHIPPED_NOT_NULL = "/Northwind/Orders[ShippedDate]";
	private static final String XPATH_DATA_NORTHWIND_ORDERS_DISTINCT_COUNTRY = "/Northwind/Orders[not(ShipCountry=preceding-sibling::*/ShipCountry)]";
	

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
	public void exportShippmentXml() throws Exception {
		getExporter("crosstabs","extra-fonts").exportReport(DESIGN_REPORT_SHIPMENTS_XML_JEVAL,XML_DATA_NORTHWIND,XPATH_DATA_NORTHWIND_ORDERS_DISTINCT_COUNTRY);
	}

	@Test
	public void exportShippmentSql() throws Exception {
		getExporter("crosstabs","extra-fonts").exportSqlReport(DESIGN_REPORT_SHIPMENTS_SQL_JEVAL);
	}

	@Test
	public void exportShippmentSqlWithParam() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, 1998);
		calendar.set(Calendar.MONTH, 4);
		calendar.set(Calendar.DATE, 1);		
		IDate startDate = ApiRegistry.getSqlFactory().newDate(new Date(calendar.getTimeInMillis()));
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("StartDate", startDate);
		getExporter("crosstabs","extra-fonts").exportSqlReport(DESIGN_REPORT_SHIPMENTS_SQL_WITH_PARAM, parameters);
	}
	
	@Test
	public void exportFirstJasper() throws Exception {
		ImageFactory.registerFactory();
		IImage image = ApiRegistry.getImageFactory().getImageManager().loadImage("src/dukesign.jpg");
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("ReportTitle", "The First Jasper Report Ever");
		parameters.put("MaxOrderID", new Integer(10500));
		parameters.put("SummaryImage", image);
		getExporter("firstjasper","extra-fonts").exportSqlReport(DESIGN_REPORT_JASPER,parameters);
	}
	
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
	public void exportLateOrderJEval() throws Exception {
		getExporter("crosstabs","extra-fonts").exportReport(DESIGN_REPORT_LATE_ORDERS_JEVAL,XML_DATA_NORTHWIND,XPATH_DATA_NORTHWIND_ORDERS_SHIPPED_NOT_NULL);
	}
	
	@Test
	public void exportLateOrderJEvalNoSummary() throws Exception {
		getExporter("crosstabs","extra-fonts").exportSqlReport(DESIGN_REPORT_LATE_ORDERS_JEVAL_NO_SUMMARY);
	}
	
	@Test
	public void exportImages() throws Exception {
		getExporter("images").exportReport(DESIGN_REPORT_IMAGE);
	}
	
	@Test
	public void exportUrlImages() throws Exception {
		getExporter("images").exportReport(DESIGN_REPORT_URLIMAGE);
	}
	
	@Test
	public void exportShapesJEval() throws Exception {
		getExporter("shapes").exportReport(DESIGN_REPORT_SHAPES_JEVAL);
	}
	
	@Test
	public void exportImagesJEval() throws Exception {
		getExporter("images").exportReport(DESIGN_REPORT_IMAGE_JEVAL);
	}
	
	@Test
	public void exportUrlImagesJEval() throws Exception {
		getExporter("images").exportReport(DESIGN_REPORT_URLIMAGE_JEVAL);
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
		getExporter("misc").exportReport(DESIGN_REPORT_PDFCRYPT);
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
	public void exportParagrahJEval() throws Exception {
		getExporter("text","extra-fonts").exportReport(DESIGN_REPORT_PARAGRAPH_JEVAL);
	}
	
	@Test
	public void exportStyledTextJEval() throws Exception {
		getExporter("text","extra-fonts").exportReport(DESIGN_REPORT_STYLEDTEXT_JEVAL);
	}
	
	@Test
	public void exportCDBooklet() throws Exception {
		getExporter("cdbooklet").exportReport(DESIGN_REPORT_CDBOOCKLET, XML_DATA_CDBOOKLET, XPATH_DATA_CDBOOKLET);
	}
	
	@Test
	public void exportJevalCDBooklet() throws Exception {
		getExporter("cdbooklet").exportReport(DESIGN_REPORT_CDBOOCKLET_JEVAL, XML_DATA_CDBOOKLET, XPATH_DATA_CDBOOKLET);
	}

	@Test
	public void exportRealEstateJEval() throws Exception {
		getExporter("realestate","extra-fonts").exportReport(DESIGN_REPORT_REALESTATE_JEVAL, XML_DATA_REALESTATE, XPATH_DATA_REALESTATE);
	}
	
	@Test
	public void exportRealEstateDefectGalleryJEval() throws Exception {
		getExporter("realestate-defectgallery","extra-fonts").exportReport(DESIGN_REPORT_REALESTATE_DEFECTGALLERY_JEVAL, XML_DATA_REALESTATE_PROTOCOL, XPATH_DATA_REALESTATE_DEFECTGALLERY);
	}
	
	@Test
	public void exportRealEstateSigningJEval() throws Exception {
		getExporter("realestate-signing","extra-fonts").exportReport(DESIGN_REPORT_REALESTATE_SIGNING_JEVAL, XML_DATA_REALESTATE_PROTOCOL, XPATH_DATA_REALESTATE_SIGNING);
	}
	
	@Test
	public void exportRealEstateProtocolJEval() throws Exception {
		getExporter("realestate-protocol","extra-fonts").exportReport(DESIGN_REPORT_REALESTATE_PROTOCOL_JEVAL, XML_DATA_REALESTATE_PROTOCOL, XPATH_DATA_REALESTATE_PROTOCOL);
	}
	
	@Test
	public void exportRealEstateProtocolSubreportJEval() throws Exception {
		getExporter("realestate-protocol-subreport","extra-fonts").exportReport(DESIGN_REPORT_REALESTATE_PROTOCOL_SUBREPORT_JEVAL, XML_DATA_REALESTATE_PROTOCOL, XPATH_DATA_REALESTATE_SIGNING);
	}
	
	
	@Test
	public void exportRealEstateChecklistMoveInDe() throws Exception {
		getExporter("digireport-realestate-checklist-v0").exportReport(DESIGN_REPORT_REALESTATE_CHEKLIST_IN_DE, XML_DATA_REALESTATE_CHECKLIST, XPATH_DATA_REALESTATE_CHECKLIST);
	}
	@Test
	public void exportRealEstateChecklistMoveOutDe() throws Exception {
		getExporter("digireport-realestate-checklist-v0").exportReport(DESIGN_REPORT_REALESTATE_CHEKLIST_OUT_DE, XML_DATA_REALESTATE_CHECKLIST, XPATH_DATA_REALESTATE_CHECKLIST);
	}
	@Test
	public void exportRealEstateDefectDe() throws Exception {
		getExporter("digireport-realestate-checklist-v0").exportReport(DESIGN_REPORT_REALESTATE_DEFECT_DE, XML_DATA_REALESTATE_CHECKLIST, XPATH_DATA_REALESTATE_DEFECT);
	}
	@Test
	public void exportRealEstateChecklistMoveInEn() throws Exception {
		getExporter("digireport-realestate-checklist-v0").exportReport(DESIGN_REPORT_REALESTATE_CHEKLIST_IN_EN, XML_DATA_REALESTATE_CHECKLIST, XPATH_DATA_REALESTATE_CHECKLIST);
	}
	@Test
	public void exportRealEstateChecklistMoveOutEn() throws Exception {
		getExporter("digireport-realestate-checklist-v0").exportReport(DESIGN_REPORT_REALESTATE_CHEKLIST_OUT_EN, XML_DATA_REALESTATE_CHECKLIST, XPATH_DATA_REALESTATE_CHECKLIST);
	}
	@Test
	public void exportRealEstateDefectEn() throws Exception {
		getExporter("digireport-realestate-checklist-v0").exportReport(DESIGN_REPORT_REALESTATE_DEFECT_EN, XML_DATA_REALESTATE_CHECKLIST, XPATH_DATA_REALESTATE_DEFECT);
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
