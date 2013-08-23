package org.oss.pdfreporter.text.format.factory;

import java.util.Locale;
import java.util.TimeZone;

import org.oss.pdfreporter.text.format.IDateFormat;
import org.oss.pdfreporter.text.format.IMessageFormat;
import org.oss.pdfreporter.text.format.INumberFormat;


public interface IFormatFactory {
	public enum FormatType {
		/**
		 * The Default formatter implements the semantics of net.sf.jasperreports.engine.util.DefaultFormatFactory
		 * DEFAULT
		 */
		DEFAULT,
		/**
		 * The Simple formatter implements the semantics of the java.text.SimpleDateFormat, java.text.DecimalFormat
		 * and the java.text.MessageFormat formatters
		 * SIMPLE
		 */
		SIMPLE,
		
		/**
		 * Fallback implementation based on String.format 
		 * STANDARD
		 */
		STANDARD
	}
	IDateFormat newDateFormat(String pattern, Locale locale, TimeZone timezone);
	INumberFormat newNumberFormat(String pattern, Locale locale);
	IMessageFormat newMessageFormat(String pattern, Locale locale);
}
