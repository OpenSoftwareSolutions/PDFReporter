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

import org.oss.pdfreporter.sql.IDateTime;


public class DateTimeImpl implements IDateTime {
	private final int year, month, day;
	private final int hour, minute, second;

	DateTimeImpl(int year, int month, int day, int hour, int minute,
			int second) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	DateTimeImpl(Date timestamp) {
		this(DateUtil.getCalendar(timestamp));
	}
	
	DateTimeImpl(Calendar timestamp) {
		this(timestamp.get(Calendar.YEAR), timestamp.get(Calendar.MONTH) + 1, timestamp.get(Calendar.DATE),
				timestamp.get(Calendar.HOUR_OF_DAY), timestamp.get(Calendar.MINUTE), timestamp.get(Calendar.SECOND));		
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

	@Override
	public Date getDate() {
		return DateUtil.getDateTime(getYear(), getMonth(), getDay(), getHours(), getMinutes(), getSeconds());
	}

	@Override
	public int getHours() {
		return hour;
	}

	@Override
	public int getMinutes() {
		return minute;
	}

	@Override
	public int getSeconds() {
		return second;
	}

}
