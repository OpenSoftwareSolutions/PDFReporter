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
