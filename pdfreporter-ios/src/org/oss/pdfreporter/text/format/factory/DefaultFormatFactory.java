package org.oss.pdfreporter.text.format.factory;

import java.util.Locale;
import java.util.TimeZone;

import org.oss.pdfreporter.text.format.IDateFormat;
import org.oss.pdfreporter.text.format.IMessageFormat;
import org.oss.pdfreporter.text.format.INumberFormat;


public class DefaultFormatFactory implements IFormatFactory {

	@Override
	public IDateFormat newDateFormat(String pattern, Locale locale, TimeZone timezone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public INumberFormat newNumberFormat(String pattern, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMessageFormat newMessageFormat(String pattern, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}
	
	


}
