/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package org.oss.pdfreporter.text.format;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.oss.pdfreporter.text.ParseException;
import org.oss.pdfreporter.text.format.IDateFormat;


public class DateFormat implements IDateFormat {

	private static final String DEFAULT_DATE_PATTERN = "yyyy-mm-dd";
	private final SimpleDateFormat format;
	public DateFormat(String pattern, Locale locale, TimeZone timeZone) {
		if (pattern!=null && locale!=null) {
			this.format = new SimpleDateFormat(pattern,locale);
		} else if (pattern!=null) {
			this.format = new SimpleDateFormat(pattern);
			
		} else if (locale!=null) {
			this.format = new SimpleDateFormat(DEFAULT_DATE_PATTERN,locale);
			
		} else {
			this.format = new SimpleDateFormat(DEFAULT_DATE_PATTERN);			
		}
		if (timeZone!=null) {
			format.setTimeZone(timeZone);
		}
	}
	@Override
	public Object parseObject(String source) throws ParseException {
		return parse(source);
	}

	@Override
	public String format(Object obj) {
		return format((Date)obj);
	}

	@Override
	public Date parse(String source) throws ParseException {
		try {
			return format.parse(source);
		} catch (java.text.ParseException e) {
			throw new ParseException(e.getMessage());
		}
	}

	@Override
	public String format(Date date) {
		return format.format(date);
	}

}
