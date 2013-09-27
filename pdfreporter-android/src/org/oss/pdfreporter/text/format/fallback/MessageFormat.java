package org.oss.pdfreporter.text.format.fallback;

import org.oss.pdfreporter.text.format.IMessageFormat;

public class MessageFormat implements IMessageFormat {

	private final String pattern;
	
	MessageFormat(String pattern) {
		super();
		this.pattern = pattern;
	}

	@Override
	public String format(Object[] arguments) {
		return String.format(pattern, arguments);
	}

}
