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
package org.oss.pdfreporter.text.format.fallback;

import java.util.Locale;
import java.util.TimeZone;

import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.text.format.IDateFormat;
import org.oss.pdfreporter.text.format.IMessageFormat;
import org.oss.pdfreporter.text.format.INumberFormat;
import org.oss.pdfreporter.text.format.factory.IFormatFactory;


public class FallbackFormatFactory implements IFormatFactory {

	public static void registerFactory() {
		ApiRegistry.register(FormatType.STANDARD, new FallbackFormatFactory());
	}

	@Override
	public IDateFormat newDateFormat(String pattern, Locale locale,
			TimeZone timezone) {
		return pattern==null ? new DateFormat() : new DateFormat(pattern);
	}

	@Override
	public INumberFormat newNumberFormat(String pattern, Locale locale) {
		return pattern==null ? new NumberFormat() : new NumberFormat(pattern);
	}

	@Override
	public IMessageFormat newMessageFormat(String pattern, Locale locale) {
		return new MessageFormat(pattern);
	}
	
	

}
