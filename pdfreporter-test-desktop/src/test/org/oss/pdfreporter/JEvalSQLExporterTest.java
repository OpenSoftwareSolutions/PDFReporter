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
import java.sql.Connection;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oss.pdfreporter.sql.SQLException;

import test.org.oss.pdfreporter.testdata.DesktopTestdataImporter;


public class JEvalSQLExporterTest extends JEvalDesktopExporterTest{



	public JEvalSQLExporterTest() throws SQLException {
		super();
	}

	@Test
	public void exportShippment() throws Exception {
		getExporter(DESIGN_REPORT_SHIPMENTS, "crosstabs","extra-fonts")
			.setSqlSource(testProvider.databasePath(), SQL_USERNAME, SQL_PASSWORD)
			.exportPdf();
	}

	@Test
	public void exportMasterReport() throws Exception {
        getExporter(DESIGN_REPORT_MASTER, "subreports", "extra-fonts")
        	.addSubreport(SUBREPORT_MASTER_NAME, SUBREPORT_MASTER_LOCATION)
        	.setSqlSource(testProvider.databasePath(), SQL_USERNAME, SQL_PASSWORD)
        	.exportPdf();
	}

	@Test
	public void exportProducts() throws Exception {
		getExporter(DESIGN_REPORT_PRODUCTS, "crosstabs","extra-fonts")
			.setSqlSource(testProvider.databasePath(), SQL_USERNAME, SQL_PASSWORD)
			.exportPdf();
	}

	@Test
	public void exportOrders() throws Exception {
		getExporter(DESIGN_REPORT_ORDERS, "crosstabs","extra-fonts")
			.setSqlSource(testProvider.databasePath(), SQL_USERNAME, SQL_PASSWORD)
			.exportPdf();
	}

	@Test
	public void exportLateOrder() throws Exception {
		getExporter(DESIGN_REPORT_LATE_ORDERS, "crosstabs","extra-fonts")
			.setSqlSource(testProvider.databasePath(), SQL_USERNAME, SQL_PASSWORD)
			.exportPdf();
	}

	// FIXME PDF ERROR generates no pages
	//@Test
	//public void exportHorizontal() throws Exception {
		//getExporter(DESIGN_REPORT_HORIZONTAL, "horizontal","extra-fonts")
		//.setSqlSource(testProvider.databasePath(), SQL_USERNAME, SQL_PASSWORD)
		//.exportPdf();
	//}

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

	@BeforeClass
    public static void setup() throws Exception {
		DesktopTestdataImporter.startHSQLServer();
		Connection newConnection = DesktopTestdataImporter.newConnection();
		Statement s = newConnection.createStatement();
		DesktopTestdataImporter.createTable(s);
		DesktopTestdataImporter.insertData(s);
		DesktopTestdataImporter.close(newConnection, s);
    }

    @AfterClass
    public static void teardown() throws Exception {
    	Connection newConnection = DesktopTestdataImporter.newConnection();
		Statement s = newConnection.createStatement();
		DesktopTestdataImporter.deleteTable(s);
		DesktopTestdataImporter.close(newConnection, s);
		DesktopTestdataImporter.stopHSQLServer();
		DesktopTestdataImporter.deleteDbFiles(new File("."), DesktopTestdataImporter.IVADB);
    }
}
