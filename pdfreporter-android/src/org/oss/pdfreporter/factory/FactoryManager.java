/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package org.oss.pdfreporter.factory;

import org.oss.pdfreporter.beans.factory.BeansFactory;
import org.oss.pdfreporter.font.FontFactory;
import org.oss.pdfreporter.geometry.GeometryFactory;
import org.oss.pdfreporter.image.ImageFactory;
import org.oss.pdfreporter.json.factory.JsonDataSourceFactory;
import org.oss.pdfreporter.net.factory.NetFactory;
import org.oss.pdfreporter.pdf.PdfFactory;
import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.sql.SQLiteFactory;
import org.oss.pdfreporter.text.format.factory.DefaultFormatFactory;
import org.oss.pdfreporter.text.format.factory.SimpleFormatFactory;
import org.oss.pdfreporter.text.format.fallback.FallbackFormatFactory;
import org.oss.pdfreporter.xml.parsers.factory.AndroidXmlParserFactory;

public class FactoryManager {
	public static void registerFactories() {
		GeometryFactory.registerFactory();
		AndroidXmlParserFactory.registerFactory();
		JsonDataSourceFactory.registerFactory();
		NetFactory.registerFactory();
		SimpleFormatFactory.registerFactory();		
		DefaultFormatFactory.registerFactory();	
		FallbackFormatFactory.registerFactory();
		BeansFactory.registerFactory();
		FontFactory.registerFactory();
		ImageFactory.registerFactory();
		PdfFactory.registerFactory();
		SQLiteFactory.registerFactory();
	}
	
	public static void dispose() {
		ApiRegistry.dispose();
	}
}
