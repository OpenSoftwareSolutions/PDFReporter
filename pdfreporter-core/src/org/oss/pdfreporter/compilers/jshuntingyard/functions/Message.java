/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 *
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.compilers.jshuntingyard.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.text.format.IMessageFormat;
import org.oss.pdfreporter.text.format.factory.IFormatFactory.FormatType;
import org.oss.uses.org.oss.jshuntingyard.evaluator.AbstractFunctionElement;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElementArgument;

/**
 * Date to String
 *
 */
public class Message extends AbstractFunctionElement  {

	private static final String DATE_CONVERTER = "(date)";

	public Message() {
		super("msg", -1, Precedence.USERFUNCTION);
	}


	@Override
	public boolean isUserFunction() {
		return true;
	}


	@Override
	public FunctionElementArgument<?> execute(
			FunctionElementArgument<?>... args) throws IllegalArgumentException {
		List<Object> messageArgs = new ArrayList<Object>();
		if (isString(args[0])) {
			String pattern = (String) args[0].getValue();
			for (int i=1; i<args.length; i++) {
				if (isString(args[i])) {
					String stringArg = (String) args[i].getValue();
					if (stringArg.startsWith(DATE_CONVERTER)) {
						// TODO fix after jshuntingyard supports Date types (@see DateStringConverter)
						messageArgs.add(new Date(Long.parseLong(stringArg.substring(6))));
					} else {					
						messageArgs.add(stringArg);
					}
				} else {
					messageArgs.add(args[i].getValue());
				}
			}
			IMessageFormat formatter = ApiRegistry.getIFormatFactory(FormatType.STANDARD).newMessageFormat(pattern, null);
			return FunctionArgumentFactory.createString(formatter.format(messageArgs.toArray()));
		} else {
			throw new IllegalArgumentException("First parameter must a String with a message pattern and not " + args[0].getType());
		}
	}
}