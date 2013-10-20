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
package org.oss.pdfreporter.sql.factory;

import java.util.Calendar;
import java.util.Date;

class DateUtil {
	
	static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	static Date getTime(int hour, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
        setTime(cal, hour, minute, second);
        return cal.getTime();
	}

	
	static Date getDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
        setDate(cal, year, month, day);
        return cal.getTime();
	}

	static Date getDateTime(int year, int month, int day, int hour, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
        setDate(cal, year, month, day);
        setTime(cal, hour, minute, second);
        return cal.getTime();
	}
	
	static Date getTimestamp(int year, int month, int day, int hour, int minute, int second, int millisecond) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
        setDate(cal, year, month, day);
        setTime(cal, hour, minute, second);
        cal.set(Calendar.MILLISECOND, millisecond);
        return cal.getTime();
	}

	
	private static void setTime(Calendar cal, int hour, int minute, int second) {
		cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
	}

	private static void setDate(Calendar cal, int year, int month, int day) {
		cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, day);
	}
	
}
