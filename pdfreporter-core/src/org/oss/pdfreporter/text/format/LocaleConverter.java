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
package org.oss.pdfreporter.text.format;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Locale;

import org.oss.pdfreporter.exception.ConversionException;
import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.text.ParseException;
import org.oss.pdfreporter.text.format.factory.IFormatFactory;


public class LocaleConverter {

	public static Object convert(String valueString, Class<?> valueClass, Locale locale, String pattern) {
		try {
			IFormatFactory factory = ApiRegistry.getIFormatFactory(IFormatFactory.FormatType.SIMPLE);
			if (valueClass.isAssignableFrom(Date.class)) {
				return factory.newDateFormat(pattern, locale, null).parse(valueString);
			} 
			Number number = factory.newNumberFormat(pattern, locale).parse(valueString);
			return getNumber(number,valueClass);
		} catch (ParseException e) {
			throw new ConversionException("Conversion of '" + valueString + "' to " + valueClass.getSimpleName() + " with pattern: " + pattern + " and locale: " + locale + " failed, " + e.getMessage());
		}
	}

	private static Number getNumber(Number number, Class<?> c) {
		if (c.isAssignableFrom(BigDecimal.class)) {
			return new BigDecimal(number.doubleValue());
		} else if (c.isAssignableFrom(BigInteger.class)) {
			return BigInteger.valueOf(number.longValue());
		} else if (c.isAssignableFrom(Double.class)) {
			return number.doubleValue();
		} else if (c.isAssignableFrom(Float.class)) {
			return new Float(number.doubleValue());
		} else if (c.isAssignableFrom(Long.class)) {
			return new Long(number.longValue());
		} else if (c.isAssignableFrom(Integer.class)) {
			return new Integer(number.intValue());
		} else if (c.isAssignableFrom(Short.class)) {
			return new Short(number.shortValue());
		}  else if (c.isAssignableFrom(Byte.class)) {
			return new Byte(number.byteValue());
		} 
		throw new ConversionException("Not supported Number type " + c);
	}
	
}
