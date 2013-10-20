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

import org.oss.pdfreporter.sql.ITime;


class TimeImpl implements ITime {
	
	private final int hour, minute, second;

	TimeImpl(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	TimeImpl(Date time) {
		this(DateUtil.getCalendar(time));
	}
	
	TimeImpl(Calendar time) {
		this(time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.SECOND));
	}
	
	
	@Override
	public Date getDate() {
		return DateUtil.getTime(getHours(), getMinutes(), getSeconds());
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
