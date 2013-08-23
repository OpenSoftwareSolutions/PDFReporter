package org.oss.pdfreporter.text.format;

import java.util.Date;

import org.oss.pdfreporter.text.ParseException;


public interface IDateFormat extends IFormat {
	 Date parse(String source) throws ParseException;
	 String format(Date date);
}
