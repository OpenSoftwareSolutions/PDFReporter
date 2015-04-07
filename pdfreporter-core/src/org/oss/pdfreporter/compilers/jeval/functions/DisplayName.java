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

import java.util.Locale;

import org.oss.pdfreporter.uses.net.sourceforge.jeval.Evaluator;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.Function;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionConstants;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionException;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionResult;


public class DisplayName implements Function {

	@Override
	public String getName() {
		return "displayName";
	}

	@Override
	public FunctionResult execute(Evaluator evaluator, String arguments) throws FunctionException {
		String[] parameter = arguments.split("\'|_");
		String language = parameter[1];
		String country = parameter[2];
		String displayName = new Locale(language, country).getDisplayName();
		return new FunctionResult(displayName, FunctionConstants.FUNCTION_RESULT_TYPE_STRING);
	}
}
