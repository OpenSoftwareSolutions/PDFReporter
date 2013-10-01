package test.ch.digireport.jasper;

import java.lang.reflect.Method;

import test.ch.digireport.jasper.providers.JavaTestProvider;
import test.ch.digireport.jasper.providers.TestProviderInterface;

public class PortableTest {
	
	public static void main(String[] arg){
		new PortableTest().test(new JavaTestProvider());
	}
	
	public void test(TestProviderInterface testProvider) {
		ExporterTest exporterTest = new ExporterTest(false, testProvider);
		Method[] methods = ExporterTest.class.getMethods();
		boolean failure = false;
		
		System.out.println("Testign started...");
		for (Method method : methods) {
			/* Not working in iOS
            Test testAnnotation = method.getAnnotation(Test.class);
            if (testAnnotation != null) {*/
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
                try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
        }
		if(!failure) {
			System.out.println("\nTest finished successfully.");
		} else {
			System.err.println("\nTest finished with errors.");
		}
	}
	
}
