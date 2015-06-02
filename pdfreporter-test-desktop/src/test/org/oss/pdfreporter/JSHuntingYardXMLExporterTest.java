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

import java.util.Locale;

import org.junit.Test;
import org.oss.pdfreporter.engine.query.JRXPathQueryExecuterFactory;
import org.oss.pdfreporter.engine.util.JRLoader;
import org.oss.pdfreporter.engine.util.JRXmlUtils;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;


public class JSHuntingYardXMLExporterTest extends JSHuntingYardDesktopExporterTest{

	public JSHuntingYardXMLExporterTest() {
		super();
	}

	@Test
	public void exportCDBooklet() throws Exception {
		getExporter(DESIGN_REPORT_CDBOOCKLET, "cdbooklet","extra-fonts")
			.setXmlSource(XML_DATA_CDBOOKLET, XPATH_DATA_CDBOOKLET)
			.exportPdf();
	}

	@Test
	public void exportOrders() throws Exception {
		getExporter(DESIGN_REPORT_ORDERS, "xmldatasource","extra-fonts")
		.setXmlSource(XML_DATA_NORTHWIND, XPATH_DATA_NORTHWIND_ORDERS)
		.exportPdf();
	}

	// TODO reenable xml datasource for iOS on this report
	@Test
	public void exportCostumers() throws Exception {
		Document document = JRXmlUtils.parse(JRLoader.getLocationInputStream(XML_DATA_NORTHWIND));
		getExporter(DESIGN_REPORT_CUSTOMERS, "xmldatasource","extra-fonts")
		.setXmlSource(XML_DATA_NORTHWIND, XPATH_DATA_NORTHWIND_CUSTOMERS)
		.addXMLParams("yyyy-MM-dd", "#,##0.##", Locale.ENGLISH, Locale.US)
		.addFillParameter(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT, document)
		.exportPdf();
	}
}
