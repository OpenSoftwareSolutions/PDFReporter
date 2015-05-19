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
package org.oss.pdfreporter.sql.factory;

import java.util.Calendar;
import java.util.Date;

import org.oss.pdfreporter.sql.IDate;


public class DateImpl implements IDate {

	private final int year, month, day;
	
	DateImpl(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	DateImpl(Date date) {
		this(DateUtil.getCalendar(date));
	}
	
	DateImpl(Calendar date) {
		this(date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1, date.get(Calendar.DATE));		
	}
	
	@Override
	public Date getDate() {
		return DateUtil.getDate(getYear(), getMonth(), getDay());
	}

	@Override
	public int getYear() {
		return year;
	}

	@Override
	public int getMonth() {
		return month;
	}

	@Override
	public int getDay() {
		return day;
	}

}
