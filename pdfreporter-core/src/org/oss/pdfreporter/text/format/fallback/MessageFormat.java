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
