package test.org.oss.pdfreporter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	JSHuntingYardNODataRessourceExporterTest.class,
	JSHuntingYardSQLExporterTest.class,
	JSHuntingYardXMLExporterTest.class,
	JSHuntingYardJSONExporterTest.class,
	JSHuntingYardMultiLanguageExporterTest.class
})

public class JSHuntingYardDesktopSuite {

}
