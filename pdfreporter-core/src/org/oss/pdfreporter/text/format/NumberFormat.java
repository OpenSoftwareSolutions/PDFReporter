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

import java.text.DecimalFormat;
import java.util.Locale;

import org.oss.pdfreporter.text.ParseException;
import org.oss.pdfreporter.text.format.INumberFormat;


public class NumberFormat implements INumberFormat {

	private final java.text.NumberFormat format;
	
	public NumberFormat(String pattern, Locale locale) {
		if (pattern!=null) {
			this.format = new DecimalFormat(pattern);
		} else if (locale!=null) {
			this.format = DecimalFormat.getNumberInstance(locale);
		} else {
			this.format = new DecimalFormat();
		}
	}
	@Override
	public Object parseObject(String source) throws ParseException {
		return parse(source);
	}

	@Override
	public String format(Object obj) {
		return format.format(obj);
	}

	@Override
	public Number parse(String source) throws ParseException {
		try {
			return format.parse(source);
		} catch (java.text.ParseException e) {
			throw new ParseException(e.getMessage());
		}
	}

	@Override
	public String format(long number) {
		return format.format(number);
	}

	@Override
	public String format(double number) {
		return format.format(number);
	}

}
