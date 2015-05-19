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
package org.oss.pdfreporter.compilers.jeval.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.oss.pdfreporter.compilers.jeval.ResultUtil;
import org.oss.pdfreporter.converters.DecimalConverter;
import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.text.format.IMessageFormat;
import org.oss.pdfreporter.text.format.factory.IFormatFactory.FormatType;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.ArgumentTokenizer;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.EvaluationConstants;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.Evaluator;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.Function;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionConstants;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionException;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionHelper;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionResult;


public class Message implements Function {

	private static final String DATE_CONVERTER = "(date)";

	@Override
	public String getName() {
		return "msg";
	}

	@Override
	public FunctionResult execute(Evaluator evaluator, String arguments)
			throws FunctionException {
		String pattern = null;
		
		final ArgumentTokenizer tokenizer = new ArgumentTokenizer(
				arguments, EvaluationConstants.FUNCTION_ARGUMENT_SEPARATOR);
		List<Object> args = new ArrayList<Object>();
		pattern = FunctionHelper.trimAndRemoveQuoteChars(tokenizer.nextToken(), evaluator.getQuoteCharacter());
		while(tokenizer.hasMoreTokens()) {
			String rawarg = tokenizer.nextToken();
			if (ResultUtil.isString(rawarg, evaluator.getQuoteCharacter())) {
				String stringArg = FunctionHelper.trimAndRemoveQuoteChars(rawarg, evaluator.getQuoteCharacter());
				if (stringArg.startsWith(DATE_CONVERTER)) {
					args.add(new Date(Long.parseLong(stringArg.substring(6))));
				} else {					
					args.add(stringArg);
				}
			} else {
				Double doubleArg = DecimalConverter.toDouble(rawarg);
				if (doubleArg.compareTo(Math.floor(doubleArg))==0) {
					args.add(doubleArg.longValue());
				} else {
					args.add(doubleArg);
				}
			}
		}
		IMessageFormat formatter = ApiRegistry.getIFormatFactory(FormatType.STANDARD).newMessageFormat(pattern, null);
		return new FunctionResult(formatter.format(args.toArray()), FunctionConstants.FUNCTION_RESULT_TYPE_STRING);
//		return new FunctionResult(String.format(pattern, args.toArray()), FunctionConstants.FUNCTION_RESULT_TYPE_STRING);
	}	
}
