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

import java.util.Date;

import org.oss.pdfreporter.exception.NotImplementedException;
import org.oss.pdfreporter.text.ParseException;
import org.oss.pdfreporter.text.format.IDateFormat;


public class DateFormat implements IDateFormat {

	private final String pattern;
	
	DateFormat(String pattern) {
		super();
		this.pattern = pattern;
	}
	
	DateFormat() {
		this("%tT");
	}

	@Override
	public Object parseObject(String source) throws ParseException {
		throw new NotImplementedException();
	}

	@Override
	public String format(Object obj) {
		return String.format(pattern, obj);
	}

	@Override
	public Date parse(String source) throws ParseException {
		throw new NotImplementedException();
	}

	@Override
	public String format(Date date) {
		return String.format(pattern, date);
	}

}
