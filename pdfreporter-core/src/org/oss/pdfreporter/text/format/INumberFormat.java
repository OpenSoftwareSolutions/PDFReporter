package org.oss.pdfreporter.text.format;

import org.oss.pdfreporter.text.ParseException;


public interface INumberFormat extends IFormat {
	 Number parse(String source) throws ParseException;
	 String format(long number);
	 String format(double number);
}
