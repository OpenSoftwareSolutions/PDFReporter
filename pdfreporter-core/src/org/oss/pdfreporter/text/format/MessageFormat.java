/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package org.oss.pdfreporter.text.format;

import java.util.Locale;

import org.oss.pdfreporter.text.format.IMessageFormat;

public class MessageFormat implements IMessageFormat {

	private final java.text.MessageFormat format;
	
	public MessageFormat(String pattern, Locale locale) {
		if (pattern!=null && locale!=null) {
			format = new java.text.MessageFormat(pattern,locale);
		} else if (pattern!=null) {
			format = new java.text.MessageFormat(pattern);
		} else if (locale!=null) {
			format = new java.text.MessageFormat("");
			format.setLocale(locale);
		} else {
			format = new java.text.MessageFormat("");
		}
	}

	@Override
	public String format(Object[] arguments) {
		StringBuffer sb = new StringBuffer();
		format.format(arguments, sb, null);
		return sb.toString();
	}
}
