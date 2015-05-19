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
package org.oss.pdfreporter.compilers;

import org.oss.pdfreporter.compilers.jeval.NumberConstant;
import org.oss.pdfreporter.compilers.jeval.TextConstant;

public class SingleChunkTextTypeFactory {

	public static IExpressionElement buildExpression(String text) throws ExpressionParseException {
		if (NumberConstant.isNumber(text)) {
			return NumberConstant.parseNumber(text);
		}

		if (TextConstant.isText(text)) {
			return TextConstant.parseText(text);
		}
		return null;
	}
}
