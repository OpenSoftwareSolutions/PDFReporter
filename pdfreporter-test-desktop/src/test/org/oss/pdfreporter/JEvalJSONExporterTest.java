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


public class JEvalJSONExporterTest extends JEvalDesktopExporterTest{

	public JEvalJSONExporterTest() {
		super();
	}

	/*
	 * */
	@Test
	public void exportJsonDataSource() throws Exception {
		getExporter(DESIGN_REPORT_JSON_CUSTOMERS, "jsondatasource","extra-fonts")
        	.addSubreport(SUBREPORT_JSON_ORDERS_NAME, SUBREPORT_JSON_ORDERS_LOCATION)
        	.addJSONParams("yyyy-MM-dd", "#,##0.##", Locale.ENGLISH, Locale.US)
        	.setJsonSource()
        	.exportPdf();
	}
}
