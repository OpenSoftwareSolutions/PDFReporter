/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package org.oss.pdfreporter.engine.data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.oss.pdfreporter.engine.JRDataSource;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.util.FormatUtils;
import org.oss.pdfreporter.engine.util.JRDataUtils;
import org.oss.pdfreporter.text.ParseException;
import org.oss.pdfreporter.text.format.IDateFormat;
import org.oss.pdfreporter.text.format.INumberFormat;
import org.oss.pdfreporter.text.format.LocaleConverter;




/**
 * Abstract text data source, containing methods used to parse text
 * data into numerical or date values.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRAbstractTextDataSource.java 4709 2011-10-18 08:59:12Z narcism $
 */
public abstract class JRAbstractTextDataSource implements JRDataSource
{
	
	
	private Locale locale;
	private String datePattern;
	private String numberPattern;
	private TimeZone timeZone;

	protected Object convertStringValue(String text, Class<?> valueClass)
	{
		Object value = null;
		if (String.class.equals(valueClass))
		{
			value = text;
		}
		else if (Number.class.isAssignableFrom(valueClass))
		{
			value = LocaleConverter.convert(text.trim(), valueClass, locale, numberPattern);
		}
		else if (Date.class.isAssignableFrom(valueClass))
		{
			value = LocaleConverter.convert(text.trim(), valueClass, locale, datePattern);
		}
		else if (Boolean.class.equals(valueClass))
		{
			value = Boolean.valueOf(text);
		}
		return value;
	}

	protected Object convertNumber(Number number, Class<?> valueClass) throws JRException
	{
		Number value = null;
		if (valueClass.equals(Byte.class))
		{
			value = new Byte(number.byteValue());
		}
		else if (valueClass.equals(Short.class))
		{
			value = new Short(number.shortValue());
		}
		else if (valueClass.equals(Integer.class))
		{
			value = Integer.valueOf(number.intValue());
		}
		else if (valueClass.equals(Long.class))
		{
			value = new Long(number.longValue());
		}
		else if (valueClass.equals(Float.class))
		{
			value = new Float(number.floatValue());
		}
		else if (valueClass.equals(Double.class))
		{
			value = new Double(number.doubleValue());
		}
		else if (valueClass.equals(BigInteger.class))
		{
			value = BigInteger.valueOf(number.longValue());
		}
		else if (valueClass.equals(BigDecimal.class))
		{
			value = new BigDecimal(Double.toString(number.doubleValue()));
		}
		else
		{
			throw new JRException("Unknown number class " + valueClass.getName());
		}
		return value;
	}

	/**
	 * @deprecated Replaced by {@link FormatUtils#getFormattedNumber(NumberFormat, String, Class)}
	 */
	protected Number getFormattedNumber(INumberFormat numberFormat, String fieldValue, Class<?> valueClass) throws ParseException
	{
		return FormatUtils.getFormattedNumber(numberFormat, fieldValue, valueClass);
	}
	
	/**
	 * @deprecated Replaced by {@link FormatUtils#getFormattedDate(DateFormat, String, Class)}
	 */
	protected Date getFormattedDate(IDateFormat dateFormat, String fieldValue, Class<?> valueClass) throws ParseException 
	{
		return FormatUtils.getFormattedDate(dateFormat, fieldValue, valueClass);
	}


	/**
	 * Copy the text parsing attributes for another object.
	 * 
	 * @param textDataSource the object to copy the attributes from
	 */
	public void setTextAttributes(JRAbstractTextDataSource textDataSource)
	{
		setLocale(textDataSource.getLocale());
		setDatePattern(textDataSource.getDatePattern());
		setNumberPattern(textDataSource.getNumberPattern());
		setTimeZone(textDataSource.getTimeZone());
	}
	
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	public void setLocale(String locale) {
		setLocale(JRDataUtils.getLocale(locale));
	}

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	public String getNumberPattern() {
		return numberPattern;
	}

	public void setNumberPattern(String numberPattern) {
		this.numberPattern = numberPattern;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}
	
	public void setTimeZone(String timeZoneId){
		setTimeZone(JRDataUtils.getTimeZone(timeZoneId));
	}
	
}
