package test.org.oss.pdfreporter.providers;

public class JavaTestProvider implements TestProviderInterface{

	@Override
	public String inputPath(String input) {
		return "../pdfreporter-testdata/"+input;
	}

	@Override
	public String outputPath(String input) {
		return "../pdfreporter-testdata/"+input;
	}
	
	@Override
	public String databasePath() {
		return "localhost";
	}

}
