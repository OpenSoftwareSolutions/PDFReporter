/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 *
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package test.org.oss.pdfreporter;


import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Handler;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.oss.pdfreporter.PdfReporter;
import org.oss.pdfreporter.repo.RepositoryManager;

import test.org.oss.pdfreporter.providers.TestProviderInterface;



public abstract class ExporterTest {


	// DRIVERS for Java
	protected final String HSQLDB_URLPREFIX;
	protected final String HSQLDB_JDBC_DRIVER;

	// FOLDERS
	protected final String JRXML_RESOURCE_FOLDER;
	protected final String JRXML_REPORT_FOLDER;
	protected final String XML_DATASOURCE_FOLDER;
	protected final String PDF_OUTPUT_FOLDER;

	// DESIGN REPORTS
	protected final String DESIGN_REPORT_FONTS;
	protected final String DESIGN_REPORT_SHIPMENTS;
	protected final String DESIGN_REPORT_PRODUCTS;
	protected final String DESIGN_REPORT_ORDERS;
	protected final String DESIGN_REPORT_LATE_ORDERS;
	protected final String DESIGN_REPORT_CUSTOMERS;

	//JSON
	protected final String DESIGN_REPORT_JSON_ORDERS;
	protected final String DESIGN_REPORT_JSON_CUSTOMERS;
	protected final String SUBREPORT_JSON_ORDERS_NAME;
	protected final String SUBREPORT_JSON_ORDERS_LOCATION;

	protected final String DESIGN_REPORT_IMAGE;
	protected final String DESIGN_REPORT_SHAPES;
	protected final String DESIGN_REPORT_PARAGRAPH;
	protected final String DESIGN_REPORT_STYLEDTEXT;
	protected final String DESIGN_REPORT_I18N;
	protected final String DESIGN_REPORT_CDBOOCKLET;
//	protected final  String DESIGN_REPORT_JASPER;
	protected final String DESIGN_REPORT_ROTATION;
	protected final String DESIGN_REPORT_PDFCRYPT;

	protected final String DESIGN_REPORT_MASTER;
	protected final String SUBREPORT_MASTER_NAME;
	protected final String SUBREPORT_MASTER_LOCATION;

	protected final String DESIGN_REPORT_HORIZONTAL;
	protected final String DESIGN_REPORT_LANDSCAPE;
	protected final String DESIGN_REPORT_STRETCH;
	protected final String DESIGN_REPORT_TABULAR;

	protected final String DESIGN_REPORT_LIST;
	protected final String DESIGN_REPORT_HORIZONTALLIST;
//	protected final String DESIGN_REPORT_NOPAGEBREAK;
//	protected final String DESIGN_REPORT_TABLE;
	protected final String DESIGN_REPORT_TEMPLATES;

	// XML DATA
	protected final String XML_DATA_CDBOOKLET;
	protected final String XPATH_DATA_CDBOOKLET;

	// JSON DATA
	protected final String JSON_DATA_NORTHWIND;

	protected final String XML_DATA_NORTHWIND;
	protected final String XPATH_DATA_NORTHWIND_ORDERS;
	protected final String XPATH_DATA_NORTHWIND_ORDERS_SHIPPED_NOT_NULL;
	protected final String XPATH_DATA_NORTHWIND_CUSTOMERS;

	public String SQL_USERNAME;
	public String SQL_PASSWORD;

	protected final TestProviderInterface testProvider;

	protected ExporterTest(boolean initJava, TestProviderInterface testProvider) {

		ResourceBundle resourceBundle = getExpressionLanguage();

		// DRIVERS for Java
		HSQLDB_URLPREFIX = resourceBundle.getString("HSQLDB_URLPREFIX");
		HSQLDB_JDBC_DRIVER = resourceBundle.getString("HSQLDB_JDBC_DRIVER");

		// FOLDERS
		JRXML_RESOURCE_FOLDER = resourceBundle.getString("JRXML_RESOURCE_FOLDER");
		JRXML_REPORT_FOLDER = resourceBundle.getString("JRXML_REPORT_FOLDER");
		XML_DATASOURCE_FOLDER = resourceBundle.getString("XML_DATASOURCE_FOLDER");
		PDF_OUTPUT_FOLDER = resourceBundle.getString("PDF_OUTPUT_FOLDER");

		// DESIGN REPORTS
		DESIGN_REPORT_FONTS = resourceBundle.getString("DESIGN_REPORT_FONTS");
		DESIGN_REPORT_SHIPMENTS = resourceBundle.getString("DESIGN_REPORT_SHIPMENTS");
		DESIGN_REPORT_PRODUCTS = resourceBundle.getString("DESIGN_REPORT_PRODUCTS");
		DESIGN_REPORT_ORDERS = resourceBundle.getString("DESIGN_REPORT_ORDERS");
		DESIGN_REPORT_LATE_ORDERS = resourceBundle.getString("DESIGN_REPORT_LATE_ORDERS");
		DESIGN_REPORT_CUSTOMERS = resourceBundle.getString("DESIGN_REPORT_CUSTOMERS");

		//JSON
		DESIGN_REPORT_JSON_ORDERS = resourceBundle.getString("DESIGN_REPORT_JSON_ORDERS");
		DESIGN_REPORT_JSON_CUSTOMERS = resourceBundle.getString("DESIGN_REPORT_JSON_CUSTOMERS");
		SUBREPORT_JSON_ORDERS_NAME = resourceBundle.getString("SUBREPORT_JSON_ORDERS_NAME");
		SUBREPORT_JSON_ORDERS_LOCATION = resourceBundle.getString("SUBREPORT_JSON_ORDERS_LOCATION");

		DESIGN_REPORT_IMAGE = resourceBundle.getString("DESIGN_REPORT_IMAGE");
		DESIGN_REPORT_SHAPES = resourceBundle.getString("DESIGN_REPORT_SHAPES");
		DESIGN_REPORT_PARAGRAPH = resourceBundle.getString("DESIGN_REPORT_PARAGRAPH");
		DESIGN_REPORT_STYLEDTEXT = resourceBundle.getString("DESIGN_REPORT_STYLEDTEXT");
		DESIGN_REPORT_I18N = resourceBundle.getString("DESIGN_REPORT_I18N");
		DESIGN_REPORT_CDBOOCKLET = resourceBundle.getString("DESIGN_REPORT_CDBOOCKLET");
//		private  String DESIGN_REPORT_JASPER = resourceBundle.getString("DESIGN_REPORT_JASPER");
		DESIGN_REPORT_ROTATION = resourceBundle.getString("DESIGN_REPORT_ROTATION");
		DESIGN_REPORT_PDFCRYPT = resourceBundle.getString("DESIGN_REPORT_PDFCRYPT");

		DESIGN_REPORT_MASTER = resourceBundle.getString("DESIGN_REPORT_MASTER");
		SUBREPORT_MASTER_NAME = resourceBundle.getString("SUBREPORT_MASTER_NAME");
		SUBREPORT_MASTER_LOCATION = resourceBundle.getString("SUBREPORT_MASTER_LOCATION");

		DESIGN_REPORT_HORIZONTAL = resourceBundle.getString("DESIGN_REPORT_HORIZONTAL");
		DESIGN_REPORT_LANDSCAPE = resourceBundle.getString("DESIGN_REPORT_LANDSCAPE");
		DESIGN_REPORT_STRETCH = resourceBundle.getString("DESIGN_REPORT_STRETCH");
		DESIGN_REPORT_TABULAR = resourceBundle.getString("DESIGN_REPORT_TABULAR");

		DESIGN_REPORT_LIST = resourceBundle.getString("DESIGN_REPORT_LIST");
		DESIGN_REPORT_HORIZONTALLIST = resourceBundle.getString("DESIGN_REPORT_HORIZONTALLIST");
//		DESIGN_REPORT_NOPAGEBREAK = resourceBundle.getString("DESIGN_REPORT_NOPAGEBREAK");
//		DESIGN_REPORT_TABLE = resourceBundle.getString("DESIGN_REPORT_TABLE");
		DESIGN_REPORT_TEMPLATES = resourceBundle.getString("DESIGN_REPORT_TEMPLATES");

		// XML DATA
		XML_DATA_CDBOOKLET = resourceBundle.getString("XML_DATA_CDBOOKLET");
		XPATH_DATA_CDBOOKLET = resourceBundle.getString("XPATH_DATA_CDBOOKLET");

		// JSON DATA
		JSON_DATA_NORTHWIND = resourceBundle.getString("JSON_DATA_NORTHWIND");

		XML_DATA_NORTHWIND = resourceBundle.getString("XML_DATA_NORTHWIND");
		XPATH_DATA_NORTHWIND_ORDERS = resourceBundle.getString("XPATH_DATA_NORTHWIND_ORDERS");
		XPATH_DATA_NORTHWIND_ORDERS_SHIPPED_NOT_NULL = resourceBundle.getString("XPATH_DATA_NORTHWIND_ORDERS_SHIPPED_NOT_NULL");
		XPATH_DATA_NORTHWIND_CUSTOMERS = resourceBundle.getString("XPATH_DATA_NORTHWIND_CUSTOMERS");

		SQL_USERNAME = resourceBundle.getString("SQL_USERNAME");
		SQL_PASSWORD = resourceBundle.getString("SQL_PASSWORD");


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

				//Class<?> sqlFactory = Class.forName("org.oss.pdfreporter.sql.SqlFactory");
				//sqlFactory.getMethod("registerFactory", new Class<?>[] {String.class,String.class}).invoke(noInstance, new Object[] {HSQLDB_JDBC_DRIVER, HSQLDB_URLPREFIX});
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

	// FIXME
	//@Test
	//public void exportTemplates() throws Exception {
		//getExporter(DESIGN_REPORT_TEMPLATES, "templates").exportPdf();
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

	protected PdfReporter getExporter(String jrxml, String reportFolder) {
		return getExporter(jrxml, reportFolder, null);
	}

	protected PdfReporter getExporter(String jrxml, String reportFolder, String extraFolder) {
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

	protected abstract ResourceBundle getExpressionLanguage();

	/**
	 * TODO split in a test per langauge
	 * @return select a language used in the multi language report
	 */
	protected static Locale chooseLocale()
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
