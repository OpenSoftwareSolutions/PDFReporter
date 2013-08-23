package org.oss.pdfreporter.text.format;

import org.oss.pdfreporter.text.ParseException;

public abstract interface IFormat {
	Object parseObject(String source) throws ParseException;
	String format (Object obj);
}
