package org.oss.pdfreporter.text.format.fallback;

import org.oss.pdfreporter.exception.NotImplementedException;
import org.oss.pdfreporter.text.ParseException;
import org.oss.pdfreporter.text.format.INumberFormat;

public class NumberFormat implements INumberFormat {

	private final String pattern;
	
	
	NumberFormat(String pattern) {
		super();
		this.pattern = pattern;
	}
	
	NumberFormat() {
		this("%d");
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
	public Number parse(String source) throws ParseException {
		throw new NotImplementedException();
	}

	@Override
	public String format(long number) {
		return String.format(pattern, number);
	}

	@Override
	public String format(double number) {
		return String.format(pattern, number);
	}

}
