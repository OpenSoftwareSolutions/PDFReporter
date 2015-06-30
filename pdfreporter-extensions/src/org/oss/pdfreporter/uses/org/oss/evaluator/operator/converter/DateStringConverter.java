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
package org.oss.pdfreporter.uses.org.oss.evaluator.operator.converter;

import org.oss.pdfreporter.uses.org.oss.evaluator.function.Function.Precedence;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.FunctionArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.DoubleArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.FunctionArgumentFactory;

/**
 * Date to String
 *
 */
public class DateStringConverter extends AbstractDouble2StringConverterOperatorAssociativityLeftOneArg {

	/**
	 *
	 */
	public DateStringConverter() {
		super("dateString", Precedence.USERFUNCTION);
	}


	@Override
	public boolean isUserFunction() {
		return false;
	}

	/*
	 * @see org.oss.evaluator.function.string.AbstractStringOperatorAssociativityLeftOneArg#execute(org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	protected FunctionArgument<?> execute(FunctionArgument<Double> a) throws IllegalArgumentException {

		if(a instanceof DoubleArgument){
			return FunctionArgumentFactory.createString("(date)" + ((DoubleArgument)a).getValue().toString());
		}
		throw new IllegalArgumentException(String.format("only string as type is supported and not ", a.getType()));
	}
}