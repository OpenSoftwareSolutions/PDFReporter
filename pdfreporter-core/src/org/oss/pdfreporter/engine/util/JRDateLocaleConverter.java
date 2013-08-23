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

package org.oss.pdfreporter.engine.util;

import java.util.TimeZone;

import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.text.ParseException;
import org.oss.pdfreporter.text.format.factory.IFormatFactory.FormatType;



/**
 * A Converter class dedicated for the java.util.Date type.
 * <p/>
 * In order to obtain a java.util.Date object from a given String, a JRJavaUtilDateConverter
 * object should be instantiated and it's inherited convert() method should be called. The 
 * final result is provided by the JRJavaUtilDateConverter's parse() invoked method.
 *  <p/>
 * If if any of constructor arguments is null, default values will be provided.
 *  <p/>
 * @see org.apache.commons.beanutils.locale.converters.DateLocaleConverter
 * @author szaharia
 * @version $Id: JRDateLocaleConverter.java 5180 2012-03-29 13:23:12Z teodord $
 */

public class JRDateLocaleConverter
{

	// holds the timezone's ID
	private TimeZone timeZone;


	/**
	 *
	 */
	public JRDateLocaleConverter(TimeZone timeZone) 
	{
		super();

		this.timeZone = timeZone;
	}

	/**
	 * @throws org.oss.pdfreporter.text.ParseException 
	 *
	 */
	protected Object parse(Object value, String pattern) throws ParseException 
	{
		 // Handle Date
        if (value instanceof java.util.Date) {
            return value;
        }

        // Handle Calendar
        if (value instanceof java.util.Calendar) {
            return ((java.util.Calendar)value).getTime();
        }
        return ApiRegistry.getIFormatFactory(FormatType.SIMPLE).newDateFormat(null, null, timeZone).parse((String)value);
	}
}
