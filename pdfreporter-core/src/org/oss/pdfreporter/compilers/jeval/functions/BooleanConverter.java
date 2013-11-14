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
package org.oss.pdfreporter.compilers.jeval.functions;

import org.oss.pdfreporter.uses.net.sourceforge.jeval.Evaluator;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.Util;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.Function;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionConstants;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionException;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionResult;

public class BooleanConverter implements Function {

	private final static Double TRUE = 1.0;
	@Override
	public String getName() {
		return "booleanString";
	}

	@Override
	public FunctionResult execute(Evaluator evaluator, String arguments)
			throws FunctionException {
		String result = null;
		Double number = null;

		try {
			number = Util.s2d(arguments);
		} catch (Exception e) {
			throw new FunctionException("Invalid argument.", e);
		}

		result = number.compareTo(TRUE)==0 ? "TRUE" : "FALSE";

		return new FunctionResult(result, 
				FunctionConstants.FUNCTION_RESULT_TYPE_STRING);
	}

}
