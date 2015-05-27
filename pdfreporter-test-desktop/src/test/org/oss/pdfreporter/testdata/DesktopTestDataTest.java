package test.org.oss.pdfreporter.testdata;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DesktopTestDataTest {

	@BeforeClass
	public static void setUp() throws Exception {
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

	@Test
	public void selectData() throws Exception {
		Connection newConnection = DesktopTestdataImporter.newConnection();
		Statement s = newConnection.createStatement();
  		ResultSet rs = s.executeQuery( "SELECT * FROM ADDRESS" );
  		System.out.println( "address list" );
  		while(rs.next()) {

  			System.out.println( "FIRSTNAME: " + rs.getString( "FIRSTNAME" ) );
  			System.out.println( "LASTNAME: " + rs.getString( "LASTNAME" ) );
  			System.out.println( "STREET: " + rs.getString( "STREET" ) );
  			System.out.println( "CITY: " + rs.getString( "CITY" ) );
  			System.out.println( " " );
  			System.out.println( " NEXT ROW " );
  			System.out.println( " " );
  		}
  		rs.close();
  		DesktopTestdataImporter.close(newConnection, s);
	}

}
