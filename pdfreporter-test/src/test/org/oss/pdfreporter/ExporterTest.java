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


import java.io.File;
import java.util.Locale;
import java.util.logging.Handler;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.junit.Test;
import org.oss.pdfreporter.PdfReporter;
import org.oss.pdfreporter.pdf.IDocument;
import org.oss.pdfreporter.repo.RepositoryManager;

import test.org.oss.pdfreporter.providers.JavaTestProvider;
import test.org.oss.pdfreporter.providers.TestProviderInterface;



public class ExporterTest {

	// DRIVERS for Java
	private static final String HSQLDB_URLPREFIX = "jdbc:hsqldb:hsql://";
	private static final String HSQLDB_JDBC_DRIVER = "org.hsqldb.jdbcDriver";

	// FOLDERS
	private static final String JRXML_RESOURCE_FOLDER = "resource";
	private static final String JRXML_REPORT_FOLDER = "testdata/jrxml";
	private static final String XML_DATASOURCE_FOLDER = "datasource";
	private static final String PDF_OUTPUT_FOLDER = "testdata/pdf/java";

	// DESIGN REPORTS
	private static final String DESIGN_REPORT_FONTS = "FontsReport.jrxml";
	private static final String DESIGN_REPORT_SHIPMENTS = "ShipmentsReport.jrxml";
	private static final String DESIGN_REPORT_PRODUCTS = "ProductsReport.jrxml";
	private static final String DESIGN_REPORT_ORDERS = "OrdersReport.jrxml";
	private static final String DESIGN_REPORT_LATE_ORDERS = "LateOrdersReport.jrxml";

	//JSON
	private static final String DESIGN_REPORT_JSON_ORDERS = "JsonOrdersReport.jrxml";
	private static final String DESIGN_REPORT_JSON_CUSTOMERS = "JsonCustomersReport.jrxml";

	private static final String DESIGN_REPORT_IMAGE = "ImagesReport.jrxml";
	private static final String DESIGN_REPORT_SHAPES = "ShapesReport.jrxml";
	private static final String DESIGN_REPORT_PARAGRAPH = "ParagraphsReport.jrxml";
	private static final String DESIGN_REPORT_STYLEDTEXT = "StyledTextReport.jrxml";
	private static final String DESIGN_REPORT_I18N = "I18nReport.jrxml";
	private static final String DESIGN_REPORT_CDBOOCKLET = "CDBooklet.jrxml";
//	private static final String DESIGN_REPORT_JASPER = "FirstJasper.jrxml";
	private static final String DESIGN_REPORT_ROTATION = "RotationReport.jrxml";
	private static final String DESIGN_REPORT_PDFCRYPT = "PdfEncryptReport.jrxml";
	private static final String DESIGN_REPORT_MASTER = "MasterReport.jrxml";
	private static final String DESIGN_REPORT_HORIZONTAL = "HorizontalReport.jrxml";
	private static final String DESIGN_REPORT_LANDSCAPE = "LandscapeReport.jrxml";
	private static final String DESIGN_REPORT_STRETCH = "StretchReport.jrxml";
	private static final String DESIGN_REPORT_TABULAR = "TabularReport.jrxml";

// Tests that are not supported in PDFReporter see comments at the end of unit tests for details
	private static final String DESIGN_REPORT_LIST = "ListReport.jrxml";
	private static final String DESIGN_REPORT_HORIZONTALLIST = "HorizontalListReport.jrxml";
//	private static final String DESIGN_REPORT_NOPAGEBREAK = "NoPageBreakReport.jrxml";
//	private static final String DESIGN_REPORT_TABLE = "TableReport.jrxml";
	private static final String DESIGN_REPORT_TEMPLATES = "StylesReport.jrxml";

	// XML DATA
	private static final String XML_DATA_CDBOOKLET = "CDBooklets.xml";
	private static final String XPATH_DATA_CDBOOKLET = "/CDBooklets";

	// JSON DATA
	private static final String JSON_DATA_NORTHWIND = "northwind.json";

	private static final String XML_DATA_NORTHWIND = "northwind.xml";
	private static final String XPATH_DATA_NORTHWIND_ORDERS = "/Northwind/Orders";
	private static final String XPATH_DATA_NORTHWIND_ORDERS_SHIPPED_NOT_NULL = "/Northwind/Orders[ShippedDate]";

	public static final String SQL_USERNAME = "sa";
	public static final String SQL_PASSWORD = "";

	private TestProviderInterface testProvider;

	public ExporterTest() {
		this(true, new JavaTestProvider());
	}

	protected ExporterTest(boolean initJava, TestProviderInterface testProvider) {
		this.testProvider = testProvider;
		new File(outputPath(PDF_OUTPUT_FOLDER)).mkdirs();
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
			} catch (Exception e) {

			}
		}
	}


	@Test
	public void exportFonts() throws Exception {
		getExporter(DESIGN_REPORT_FONTS, "fonts","extra-fonts")
			.exportPdf();
	}


	@Test
	public void exportShippment() throws Exception {
		getExporter(DESIGN_REPORT_SHIPMENTS, "crosstabs","extra-fonts")
			.setSqlSource(testProvider.databasePath(), SQL_USERNAME, SQL_PASSWORD)
			.exportPdf();
	}

//	TODO Fix
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
	public void exportMasterReport() throws Exception {
        getExporter(DESIGN_REPORT_MASTER, "subreports", "extra-fonts")
        	.addSubreport("ProductsSubreport", "ProductReport.jasper")
        	.setSqlSource(testProvider.databasePath(), SQL_USERNAME, SQL_PASSWORD)
        	.exportPdf();
	}

	@Test
	public void exportProducts() throws Exception {
		getExporter(DESIGN_REPORT_PRODUCTS, "crosstabs","extra-fonts")
			.setSqlSource(testProvider.databasePath(), SQL_USERNAME, SQL_PASSWORD)
			.exportPdf();
	}

// FIXME PDF ERROR generates no pages
	//@Test
	//public void exportHorizontal() throws Exception {
		//getExporter("horizontal","extra-fonts").exportSqlReport(DESIGN_REPORT_HORIZONTAL);
	//}



	@Test
	public void exportStretch() throws Exception {
		getExporter(DESIGN_REPORT_STRETCH, "stretch")
			.exportPdf();
	}

	@Test
	public void exportTabular() throws Exception {
		getExporter(DESIGN_REPORT_TABULAR, "tabular","extra-fonts")
			.exportPdf();
	}


	@Test
	public void exportOrders() throws Exception {
// TODO reenable xml datasource for iOS on this report
//		getExporter("crosstabs","extra-fonts").exportReport(DESIGN_REPORT_ORDERS,XML_DATA_NORTHWIND,XPATH_DATA_NORTHWIND_ORDERS);
		getExporter(DESIGN_REPORT_ORDERS, "crosstabs","extra-fonts")
			.setSqlSource(testProvider.databasePath(), SQL_USERNAME, SQL_PASSWORD)
			.exportPdf();
	}

	@Test
	public void exportLateOrder() throws Exception {
		// TODO reenable xml datasource for iOS on this report
//		getExporter("crosstabs","extra-fonts").exportReport(DESIGN_REPORT_LATE_ORDERS,XML_DATA_NORTHWIND,XPATH_DATA_NORTHWIND_ORDERS_SHIPPED_NOT_NULL);
		getExporter(DESIGN_REPORT_LATE_ORDERS, "crosstabs","extra-fonts")
			.setSqlSource(testProvider.databasePath(), SQL_USERNAME, SQL_PASSWORD)
			.exportPdf();
	}

	@Test
	public void exportImages() throws Exception {
		getExporter(DESIGN_REPORT_IMAGE, "images")
			.exportPdf();
	}

	@Test
	public void exportLandscape() throws Exception {
		getExporter(DESIGN_REPORT_LANDSCAPE, "landscape")
			.exportPdf();
	}

	@Test
	public void exportShapes() throws Exception {
		getExporter(DESIGN_REPORT_SHAPES, "shapes")
			.exportPdf();
	}

	@Test
	public void exportRotation() throws Exception {
		getExporter(DESIGN_REPORT_ROTATION, "misc")
			.exportPdf();
	}

	@Test
	public void exportEncrypt() throws Exception {
		getExporter(DESIGN_REPORT_PDFCRYPT, "misc", null)
			.addEncryption(true, "jasper", "reports", IDocument.PERMISSION_COPY | IDocument.PERMISSION_PRINT)
			.exportPdf();
	}

	@Test
	public void exportParagraph() throws Exception {
		getExporter(DESIGN_REPORT_PARAGRAPH, "text","extra-fonts")
			.exportPdf();
	}

	@Test
	public void exportStyledText() throws Exception {
		getExporter(DESIGN_REPORT_STYLEDTEXT, "text","extra-fonts")
			.exportPdf();
	}

	@Test
	public void exportCDBooklet() throws Exception {
		getExporter(DESIGN_REPORT_CDBOOCKLET, "cdbooklet","extra-fonts")
			.setXmlSource(XML_DATA_CDBOOKLET, XPATH_DATA_CDBOOKLET)
			.exportPdf();
	}

	/*
	 * */
	@Test
	public void exportI18n() throws Exception {
		Locale locale = chooseLocale();

		if(locale != null){
			getExporter(DESIGN_REPORT_I18N, "i18n")
		    .addFillParameter("number", new Double(1234567 + Math.random()))
			.newResourceBundle("test.org.oss.pdfreporter.resourcebundle.i18n", locale)
			.exportPdf();
		}

	}

	/*
	 * */
	@Test
	public void exportJsonDataSource() throws Exception {
		getExporter(DESIGN_REPORT_JSON_CUSTOMERS, "jsondatasource","extra-fonts")
        	.addSubreport("JsonOrdersReport", "JsonOrdersReport.jasper")
        	.addJSONParams("yyyy-MM-dd", "#,##0.##", Locale.ENGLISH, Locale.US)
        	.setJsonSource()
        	.exportPdf();
	}


	@Test
	public void exportHorizontalList() throws Exception {
		getExporter(DESIGN_REPORT_HORIZONTALLIST, "list", "extra-fonts")
			.setSqlSource(testProvider.databasePath(), SQL_USERNAME, SQL_PASSWORD)
			.exportPdf();
	}

	@Test
	public void exportList() throws Exception {
		getExporter(DESIGN_REPORT_LIST, "list", "extra-fonts")
			.setSqlSource(testProvider.databasePath(), SQL_USERNAME, SQL_PASSWORD)
			.exportPdf();
	}

	//@Test FIXME
	//public void exportTemplates() throws Exception {
		//getExporter("templates").exportReport(DESIGN_REPORT_TEMPLATES);
	//}

///////////// BEGIN NOT SUPPORTED FEATURES /////////////////

	/**
	 * Our Digester does not support addRuleSet and setFeature @see {@link NotImplementedDigester}
	 *

	*
	* running above reports results in NotImplementedException
	*/


	/**
	 * Components are not supported in initial release, for the tests below code from the original
	 * source package net.sf.jasperreports.components.list is required.

		@Test
		public void exportTable() throws Exception {
			getExporter("table").exportReport(DESIGN_REPORT_TABLE);
		}

	*
	* running above reports results in
	* No such accessible method: setDatasetRun() on object: org.oss.pdfreporter.engine.design.JRDesignComponentElement
	*/

	/**
	 * The JEval expression language does not support java classes as expression elements such as new java.util.ArrayList()
	 * If required the class ResultCast could be extended to support something like '(emptycollection)'
	 *
		@Test
		public void exportNoPageBreak() throws Exception {
			getExporter("nopagebreak").exportSqlReport(DESIGN_REPORT_NOPAGEBREAK);
		}
	*
	* running above reports results in compiler parse exception
	*/

///////////// END NOT SUPPORTED FEATURES /////////////////

	private PdfReporter getExporter(String jrxml, String reportFolder) {
		return getExporter(jrxml, reportFolder, null);
	}

	private PdfReporter getExporter(String jrxml, String reportFolder, String extraFolder) {
		RepositoryManager repo = RepositoryManager.getInstance();
		repo.setDefaultResourceFolder(inputPath(JRXML_RESOURCE_FOLDER));
		repo.setDefaulReportFolder(inputPath(JRXML_REPORT_FOLDER + RepositoryManager.PATH_DELIMITER + reportFolder));
		if (null != extraFolder) {
			repo.addExtraReportFolder(inputPath(JRXML_REPORT_FOLDER + RepositoryManager.PATH_DELIMITER + extraFolder));
		}
		repo.addExtraReportFolder(inputPath(XML_DATASOURCE_FOLDER));

		return new PdfReporter(jrxml, outputPath(PDF_OUTPUT_FOLDER), getFilenameFromJrxml(jrxml));
	}

    public static String getFilenameFromJrxml(String jrxml) {
        return jrxml.replace(".jrxml", "");
    }

	public String inputPath(String path) {
		if(testProvider != null) return testProvider.inputPath(path);
		else return path;
	}

	public String outputPath(String path) {
		if(testProvider != null) return testProvider.outputPath(path);
		else return path;
	}


	private static Locale chooseLocale()
	{
		LocaleWrapper[] locales =
			new LocaleWrapper[]
			{
				new LocaleWrapper(new Locale("cs", "CZ")),
				new LocaleWrapper(Locale.GERMANY),
				new LocaleWrapper(Locale.US),
				new LocaleWrapper(Locale.FRANCE),
				new LocaleWrapper(new Locale("pt", "PT")),
				new LocaleWrapper(new Locale("ro", "RO")),
				new LocaleWrapper(new Locale("sk", "SK"))
			};

		LocaleWrapper locale =
			(LocaleWrapper)JOptionPane.showInputDialog(
				null,
				"Choose the locale",
				"Locale",
				JOptionPane.PLAIN_MESSAGE,
				null,
				locales,
				null
				);

		if (locale != null)
			return locale.getLocale();

		return null;
	}


}
 class LocaleWrapper {
	Locale locale = null;

	public LocaleWrapper(Locale locale)
	{
		this.locale = locale;
	}

	public Locale getLocale()
	{
		return locale;
	}

	public String toString()
	{
		return locale.toString() + " - " + locale.getDisplayName(locale);
	}
}
