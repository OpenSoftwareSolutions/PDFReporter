package test.org.oss.pdfreporter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	JEvalNODataRessourceExporterTest.class,
	JEvalSQLExporterTest.class,
	JEvalXMLExporterTest.class,
	JEvalJSONExporterTest.class,
	JEvalMultiLanguageExporterTest.class
})

public class JEvalDesktopSuite {

}
