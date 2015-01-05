/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package test.ch.digireport.jasper.registry;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.android.sql.SQLiteFactory;
import org.oss.pdfreporter.beans.factory.BeansFactory;
import org.oss.pdfreporter.engine.JRPropertiesMap;
import org.oss.pdfreporter.extensions.ExtensionsRegistry;
import org.oss.pdfreporter.extensions.ExtensionsRegistryFactory;
import org.oss.pdfreporter.font.FontFactory;
import org.oss.pdfreporter.geometry.GeometryFactory;
import org.oss.pdfreporter.image.ImageFactory;
import org.oss.pdfreporter.net.factory.NetFactory;
import org.oss.pdfreporter.pdf.PdfFactory;
import org.oss.pdfreporter.text.format.factory.DefaultFormatFactory;
import org.oss.pdfreporter.text.format.factory.SimpleFormatFactory;
import org.oss.pdfreporter.text.format.fallback.FallbackFormatFactory;
import org.oss.pdfreporter.xml.parsers.factory.XmlParserFactory;


public class DefaultIRegistryExtensionsRegistryFactory implements ExtensionsRegistryFactory {
	private final static Logger logger = Logger.getLogger(DefaultIRegistryExtensionsRegistryFactory.class.getName());
	private static boolean isInitialized = false;
	
	@Override
	public ExtensionsRegistry createRegistry(String registryId,
			JRPropertiesMap properties) {
		initializeIRegistry();
		return new NullExtensionsRegistry();
	}
	
	private static class NullExtensionsRegistry implements ExtensionsRegistry {

		@Override
		public <T> List<T> getExtensions(Class<T> extensionType) {
			return null;
		}
	}
	
	// TODO (20.07.2013, Donat, Open Software Solutions): Find a better place to initialize the factories. 
	// This is a static configuration that will not change. Whereas extension registries and properties can change on a report base and should therefore be discarded for each run.
	synchronized private void initializeIRegistry() {
		if (!isInitialized) {
			Logger.getLogger("").setLevel(Level.FINEST);
			XmlParserFactory.registerFactory();
			NetFactory.registerFactory();
			SimpleFormatFactory.registerFactory();		
			DefaultFormatFactory.registerFactory();	
			FallbackFormatFactory.registerFactory();
			BeansFactory.registerFactory();
			FontFactory.registerFactory();
			ImageFactory.registerFactory();
			GeometryFactory.registerFactory();
			PdfFactory.registerFactory();
			SQLiteFactory.registerFactory();
			isInitialized = true;
			logger.info("Initialized IRegistry");
		}
	}

}
