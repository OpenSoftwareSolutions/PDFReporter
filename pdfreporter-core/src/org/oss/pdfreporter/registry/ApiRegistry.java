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
package org.oss.pdfreporter.registry;

import org.oss.pdfreporter.font.factory.IFontFactory;
import org.oss.pdfreporter.geometry.factory.IGeometryFactory;
import org.oss.pdfreporter.image.factory.IImageFactory;
import org.oss.pdfreporter.pdf.factory.IPdfFactory;
import org.oss.pdfreporter.sql.factory.ISqlFactory;
import org.oss.pdfreporter.text.format.factory.IFormatFactory;
import org.oss.pdfreporter.text.format.factory.IFormatFactory.FormatType;

public class ApiRegistry {
	private static IPdfFactory pdfFactory = null;
	private static IImageFactory imageFactory = null;
	private static IFontFactory fontFactory = null;
	private static IGeometryFactory geometryFactory = null;
	private static ISqlFactory sqlFactory = null;
	private static Session session = null;
	private static IFormatFactory defaultFormatFactory, simpleFormatFactory;
	private static IFormatFactory standardFormatFactory;
	
	public static void initSession() {
		dispose();
		ApiRegistry.session = new Session();
		setSession(pdfFactory);
		setSession(imageFactory);
		setSession(fontFactory);
		setSession(geometryFactory);
		setSession(sqlFactory);
	}

	private static void setSession(Object factory) {
		if (factory instanceof ISessionCapable) {
			((ISessionCapable)factory).setSession(ApiRegistry.session);
		}
	}
	
	public static void dispose() {
		if (ApiRegistry.session!=null) {
			ApiRegistry.session.dispose();
			ApiRegistry.session = null;
		}
	}
	
	public static IPdfFactory getPdfFactory() {
		if (pdfFactory!=null) {			
			return pdfFactory;
		}
		throw new RuntimeException("No IPdfFactory registred.");
	}
	public static void register(IPdfFactory pdfFactory) {
		ApiRegistry.pdfFactory = pdfFactory;
		setSession(pdfFactory);
	}
	public static IImageFactory getImageFactory() {
		if (imageFactory!=null) {
			return imageFactory;			
		}
		throw new RuntimeException("No IImageFactory registred.");
	}
	public static void register(IImageFactory imageFactory) {
		ApiRegistry.imageFactory = imageFactory;
		setSession(imageFactory);
	}
	public static IFontFactory getFontFactory() {
		if (fontFactory!=null) {			
			return fontFactory;
		}
		throw new RuntimeException("No IFontFactory registred.");
	}
	public static void register(IFontFactory fontFactory) {
		ApiRegistry.fontFactory = fontFactory;
		setSession(fontFactory);
	}
	public static IGeometryFactory getGeometryFactory() {
		if (geometryFactory!=null) {			
			return geometryFactory;
		}
		throw new RuntimeException("No IGeometryFactory registred.");
	}
	public static void register(IGeometryFactory geometryFactory) {
		ApiRegistry.geometryFactory = geometryFactory;
		setSession(geometryFactory);
	}
	
	public static ISqlFactory getSqlFactory() {
		if (sqlFactory!=null) {			
			return sqlFactory;
		}
		throw new RuntimeException("No ISqlFactory registred.");
	}
	
	public static void register(ISqlFactory sqlFactory) {
		ApiRegistry.sqlFactory = sqlFactory;
		setSession(sqlFactory);
	}
	
	public static void register(FormatType type, IFormatFactory factory) {
		if (type==FormatType.DEFAULT) {
			ApiRegistry.defaultFormatFactory = factory;			
		} else if (type==FormatType.SIMPLE) {
			ApiRegistry.simpleFormatFactory = factory;
		} else {
			ApiRegistry.standardFormatFactory = factory;
		}
	}
	public static IFormatFactory getIFormatFactory(FormatType type) {
		return type==FormatType.DEFAULT ? ApiRegistry.defaultFormatFactory : type==FormatType.SIMPLE ? ApiRegistry.simpleFormatFactory : ApiRegistry.standardFormatFactory;
	}
	
}
