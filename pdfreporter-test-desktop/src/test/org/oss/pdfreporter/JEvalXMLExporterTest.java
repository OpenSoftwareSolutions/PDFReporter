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

import org.junit.Test;


public class JEvalXMLExporterTest extends JEvalDesktopExporterTest{

	public JEvalXMLExporterTest() {
		super();
	}

	@Test
	public void exportCDBooklet() throws Exception {
		getExporter(DESIGN_REPORT_CDBOOCKLET, "cdbooklet","extra-fonts")
			.setXmlSource(XML_DATA_CDBOOKLET, XPATH_DATA_CDBOOKLET)
			.exportPdf();
	}

	// TODO reenable xml datasource for iOS on this report
	@Test
	public void exportOrders() throws Exception {
		getExporter(DESIGN_REPORT_ORDERS, "crosstabs","extra-fonts")
		.setXmlSource(XML_DATA_CDBOOKLET, XPATH_DATA_CDBOOKLET)
		.exportPdf();
	}

	// TODO reenable xml datasource for iOS on this report
	@Test
	public void exportLateOrder() throws Exception {
		getExporter(DESIGN_REPORT_LATE_ORDERS, "crosstabs","extra-fonts")
		.setXmlSource(XML_DATA_NORTHWIND, XPATH_DATA_NORTHWIND_ORDERS_SHIPPED_NOT_NULL)
		.exportPdf();
	}
}
