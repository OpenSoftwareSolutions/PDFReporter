package org.oss.pdfreporter.text.format.factory;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.text.ParseException;
import org.oss.pdfreporter.text.format.IDateFormat;
import org.oss.pdfreporter.text.format.IMessageFormat;
import org.oss.pdfreporter.text.format.INumberFormat;
import org.oss.pdfreporter.text.format.factory.IFormatFactory;


public class DefaultFormatFactory implements IFormatFactory {
	
	private final org.oss.pdfreporter.engine.util.DefaultFormatFactory delegate;
	
	private DefaultFormatFactory() {
		this.delegate = new org.oss.pdfreporter.engine.util.DefaultFormatFactory();
	}
	public static void registerFactory() {
		ApiRegistry.register(FormatType.DEFAULT,new DefaultFormatFactory());
	}

	@Override
	public IDateFormat newDateFormat(String pattern, Locale locale,
			TimeZone timezone) {
		return DateFormatWrapper.marshall(delegate.createDateFormat(pattern, locale, timezone));
	}

	@Override
	public INumberFormat newNumberFormat(String pattern, Locale locale) {
		return NumberFormatWrapper.marshall(delegate.createNumberFormat(pattern, locale));
	}
	
	@Override
	public IMessageFormat newMessageFormat(String pattern, Locale locale) {
		MessageFormat messageFormat = new MessageFormat("");
		messageFormat.setLocale(locale);
		messageFormat.applyPattern(pattern);
		return MessageFormatWrapper.marshall(messageFormat);
	}
	
	private static class MessageFormatWrapper implements IMessageFormat {
		private final MessageFormat delegate;
		
		public MessageFormatWrapper(MessageFormat delegate) {
			super();
			this.delegate = delegate;
		}

		static MessageFormatWrapper marshall(MessageFormat delegate) {
			return delegate == null ? null : new MessageFormatWrapper(delegate);
		}
		
		@Override
		public String format(Object[] arguments) {
			return delegate.format(arguments,new StringBuffer(),null).toString();
		}
	}
	
	private static class DateFormatWrapper implements IDateFormat {
		private final DateFormat delegate;

		private DateFormatWrapper(DateFormat delegate) {
			super();
			this.delegate = delegate;
		}
		
		static DateFormatWrapper marshall(DateFormat delegate) {
			return delegate == null ? null : new DateFormatWrapper(delegate);
		}

		@Override
		public Date parse(String source) throws ParseException {
			try {
				return delegate.parse(source);
			} catch (java.text.ParseException e) {
				throw new ParseException(e.getMessage());
			}
		}

		@Override
		public String format(Date date) {
			return delegate.format(date);
		}

		@Override
		public Object parseObject(String source) throws ParseException {
			try {
				return delegate.parseObject(source);
			} catch (java.text.ParseException e) {
				throw new ParseException(e.getMessage());
			}
		}

		@Override
		public String format(Object obj) {
			return delegate.format(obj);
		}
	}
	
	private static class NumberFormatWrapper implements INumberFormat {
		private final NumberFormat delegate;

		private NumberFormatWrapper(NumberFormat delegate) {
			super();
			this.delegate = delegate;
		}

		static NumberFormatWrapper marshall(NumberFormat delegate) {
			return delegate == null ? null : new NumberFormatWrapper(delegate);
		}
		
		@Override
		public Number parse(String source) throws ParseException {
			try {
				return delegate.parse(source);
			} catch (java.text.ParseException e) {
				throw new ParseException(e.getMessage());
			}
		}

		@Override
		public String format(long number) {
			return delegate.format(number);
		}

		@Override
		public String format(double number) {
			return delegate.format(number);
		}

		@Override
		public Object parseObject(String source) throws ParseException {
			try {
				return delegate.parseObject(source);
			} catch (java.text.ParseException e) {
				throw new ParseException(e.getMessage());
			}
		}

		@Override
		public String format(Object obj) {
			return delegate.format(obj);
		}
	}


}
