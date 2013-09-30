package test.ch.digireport.jasper;

import java.lang.reflect.Method;
import org.junit.Test;

public class PortableTest {
	
	public static void main(String[] arg){
		new PortableTest().test();
	}
	
	public void test() {
		ExporterTest exporterTest = new ExporterTest(false);
		Method[] methods = exporterTest.getClass().getMethods();
		boolean failure = false;
		System.out.println("Testign started...");
		for (Method method : methods) {
            Test testAnnotation = method.getAnnotation(Test.class);
            if (testAnnotation != null) {
                try {
                	System.out.print("Test - "+method.getName());
                	System.out.flush();
                	
                	method.invoke(exporterTest);
                	
                	System.out.println(" [Done]");
                	System.out.flush();
                } catch (Exception e) {
                	System.err.println(" [Failure]");
                	System.err.flush();
                	failure = true;
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
