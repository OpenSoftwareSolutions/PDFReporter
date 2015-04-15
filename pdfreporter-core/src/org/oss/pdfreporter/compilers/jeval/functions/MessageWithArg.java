/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 *
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.compilers.jeval.functions;

import java.text.MessageFormat;
import java.util.Arrays;

import org.oss.pdfreporter.uses.net.sourceforge.jeval.Evaluator;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.Function;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionConstants;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionException;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionResult;

public class MessageWithArg implements Function {

	@Override
	public String getName() {
		return "message";
	}

	@Override
	public FunctionResult execute(Evaluator evaluator, String arguments) throws FunctionException {
		Object[] params = arguments.split(",");
		String text = (String)params[0];
		// if lenght is equal to 2 we just have the leading and ending quotes.
		if(text.length() == 2){
			text = "";
		} else {
			text = text.substring(1, text.length() - 2);
		}
		// trail leading and ending single quote.
		Object[] args = Arrays.copyOfRange(params, 1, params.length);
		String result = MessageFormat.format(text, args);
		return new FunctionResult(result, FunctionConstants.FUNCTION_RESULT_TYPE_STRING);
	}
}
