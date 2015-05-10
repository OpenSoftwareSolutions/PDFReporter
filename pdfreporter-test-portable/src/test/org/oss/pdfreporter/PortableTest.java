package test.org.oss.pdfreporter;

import java.lang.reflect.Method;

//import test.org.oss.pdfreporter.providers.JavaTestProvider;
import test.org.oss.pdfreporter.providers.TestProviderInterface;

import com.google.j2objc.annotations.AutoreleasePool;


// TODO (10.05.2015, Magnus, Open Software Solutions) : this class should probable be removed - Daniel needs to look into it.
public class PortableTest {

	public static void main(String[] arg){
		//new PortableTest().exporterTest(new JavaTestProvider());
		//new PortableTest().realestateTest(new JavaTestProvider());
	}

	public void exporterTest(TestProviderInterface testProvider) {
		//ExporterTest exporterTest = new ExporterTest(false, testProvider);
		Method[] methods = ExporterTest.class.getMethods();
		boolean failure = false;

		System.out.println("Exporter tests started...");
		for (Method method : methods) {
			/* Not working in iOS
            Test testAnnotation = method.getAnnotation(Test.class);
            if (testAnnotation != null) {*/

			//failure = runTest(exporterTest, failure, method);
			failure = runTest(null, failure, method);
        }
		if(!failure) {
			System.out.println("\nExporter tests finished successfully.");
		} else {
			System.err.println("\nExporter tests finished with errors.");
		}
	}

	@AutoreleasePool
	private boolean runTest(ExporterTest exporterTest, boolean failure,
			Method method) {
		if(method.getName().startsWith("export")){
			try {
		    	System.out.print("Test - "+method.getName());
		    	long time = System.currentTimeMillis();
		    	method.invoke(exporterTest);
		    	System.out.println(" [Done] in "+(System.currentTimeMillis()-time)+"ms");
		    } catch (Exception e) {
		    	System.err.println(" [Failure]");
		    	failure = true;
		    }
		}
		return failure;
	}

	public void realestateTest(TestProviderInterface testProvider) {
		//RealestateTest exporterTest = new RealestateTest(false, testProvider);
		//Method[] methods = RealestateTest.class.getMethods();
		Method[] methods = null;
		boolean failure = false;

		System.out.println("Realestate tests started...");
		for (Method method : methods) {
			/* Not working in iOS
            Test testAnnotation = method.getAnnotation(Test.class);
            if (testAnnotation != null) {*/
			if(method.getName().startsWith("export")){
				try {
                	System.out.print("Test - "+method.getName());
                	long time = System.currentTimeMillis();
                	//method.invoke(exporterTest);
                	System.out.println(" [Done] in "+(System.currentTimeMillis()-time)+"ms");
                } catch (Exception e) {
                	System.err.println(" [Failure]");
                	failure = true;
                }
                try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
        }
		if(!failure) {
			System.out.println("\nRealestate tests finished successfully.");
		} else {
			System.err.println("\nRealestate tests finished with errors.");
		}
	}

}
