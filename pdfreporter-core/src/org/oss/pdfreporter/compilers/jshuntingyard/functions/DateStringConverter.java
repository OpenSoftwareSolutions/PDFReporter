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

import org.oss.uses.org.oss.jshuntingyard.evaluator.AbstractOneArgFunctionElement;
import org.oss.uses.org.oss.jshuntingyard.evaluator.DoubleArgument;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElementArgument;

/**
 * Date to String
 *
 */
public class DateStringConverter extends AbstractOneArgFunctionElement<String,Double>  {

	/**
	 *
	 */
	public DateStringConverter() {
		super("dateString", Precedence.USERFUNCTION);
	}


	@Override
	public boolean isUserFunction() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.oss.jshuntingyard.evaluator.AbstractOneArgFunctionElement#execute(org.oss.jshuntingyard.evaluator.FunctionElementArgument)
	 */
	protected FunctionElementArgument<String> execute(FunctionElementArgument<Double> a) throws IllegalArgumentException {
		// TODO this probably does not work with jshuntingyard the (date) convention is specific to jeval integration of pdfreporter
		// TODO think we should add support for FunctionElementArgument<Date> rather than this pseudo cast
		return FunctionArgumentFactory.createString("(date)" + ((DoubleArgument)a).getValue().toString());
	}
}