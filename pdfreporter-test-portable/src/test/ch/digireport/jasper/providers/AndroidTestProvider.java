package test.ch.digireport.jasper.providers;

public class AndroidTestProvider implements TestProviderInterface{

	@Override
	public String inputPath(String input) {
		return ""+input;
	}

	@Override
	public String outputPath(String input) {
		return ""+input;
	}

	@Override
	public String databasePath() {
		return ":memory:";
	}
}
