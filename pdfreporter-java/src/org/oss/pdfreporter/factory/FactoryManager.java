package org.oss.pdfreporter.factory;

import org.oss.pdfreporter.font.FontFactory;
import org.oss.pdfreporter.geometry.GeometryFactory;
import org.oss.pdfreporter.image.ImageFactory;
import org.oss.pdfreporter.pdf.PdfFactory;
import org.oss.pdfreporter.registry.ApiRegistry;


public class FactoryManager {
	public static void registerFactories() {
		ApiRegistry.initSession();
		PdfFactory.registerFactory();
		FontFactory.registerFactory();
		GeometryFactory.registerFactory();
		ImageFactory.registerFactory();
	}
	
	public static void dispose() {
		ApiRegistry.dispose();
	}
}
