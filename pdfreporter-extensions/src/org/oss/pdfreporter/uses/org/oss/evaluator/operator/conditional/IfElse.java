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
package org.oss.pdfreporter.uses.org.oss.evaluator.operator.conditional;

import org.oss.pdfreporter.uses.org.oss.evaluator.function.FunctionArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.FunctionArgumentFactory;
import org.oss.pdfreporter.uses.org.oss.evaluator.operator.AbstractStringOperatorAssociativityLeftThreeArg;



public class IfElse extends AbstractStringOperatorAssociativityLeftThreeArg {

	public IfElse() {
		super("ifelse", Precedence.USERFUNCTION);
	}

	private final static Double TRUE = 1.0;

	@Override
	public boolean isUserFunction() {
		return true;
	}


	@Override
	protected FunctionArgument<?> execute(FunctionArgument<?> a, FunctionArgument<?> b, FunctionArgument<?> c) throws IllegalArgumentException {

		if(a.getType()==FunctionArgument.ArgumentType.STRING && b.getType()==FunctionArgument.ArgumentType.STRING && c.getType()==FunctionArgument.ArgumentType.STRING){

			FunctionArgument<Double> expression = FunctionArgumentFactory.createDouble((String)a.getValue());
			String resultTrue = (String) b.getValue();
			String resultFalse = (String) c.getValue();

			String result = expression.getValue().compareTo(TRUE) == 0 ? resultTrue : resultFalse;
			return FunctionArgumentFactory.createString(result);
		}

		return null;
	}
}
