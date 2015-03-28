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

import org.oss.pdfreporter.uses.org.oss.evaluator.function.FunctionArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.FunctionArgumentFactory;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.AbstractStringOperatorAssociativityLeftOneArg;

/**
 * Date to String
 *
 */
public class DateStringConverter extends AbstractStringOperatorAssociativityLeftOneArg {

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
	protected FunctionArgument<?> execute(FunctionArgument<?> a) throws IllegalArgumentException {
		if(a.getType()==FunctionArgument.ArgumentType.STRING){
			Long number = FunctionArgumentFactory.createDouble((String)a.getValue()).getValue().longValue();
			return FunctionArgumentFactory.createString("(date)" + number.toString());
		}
		throw new IllegalArgumentException(String.format("only string as type is supported and not ", a.getType()));
	}
}