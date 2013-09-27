package org.oss.pdfreporter.text.format.factory;

import java.util.Locale;
import java.util.TimeZone;

import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.text.format.DateFormat;
import org.oss.pdfreporter.text.format.IDateFormat;
import org.oss.pdfreporter.text.format.IMessageFormat;
import org.oss.pdfreporter.text.format.INumberFormat;
import org.oss.pdfreporter.text.format.MessageFormat;
import org.oss.pdfreporter.text.format.NumberFormat;
import org.oss.pdfreporter.text.format.factory.IFormatFactory;


public class SimpleFormatFactory implements IFormatFactory {
	
	
	public static void registerFactory() {
		ApiRegistry.register(FormatType.SIMPLE,new SimpleFormatFactory());
	}

	@Override
	public IDateFormat newDateFormat(String pattern, Locale locale,
			TimeZone timezone) {
		return new DateFormat(pattern,locale,timezone);
	}

	@Override
	public INumberFormat newNumberFormat(String pattern, Locale locale) {
		return new NumberFormat(pattern, locale);
	}
	
	@Override
	public IMessageFormat newMessageFormat(String pattern, Locale locale) {
		return new MessageFormat(pattern, locale);
	}
	

}
