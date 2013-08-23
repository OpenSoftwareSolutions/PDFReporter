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
