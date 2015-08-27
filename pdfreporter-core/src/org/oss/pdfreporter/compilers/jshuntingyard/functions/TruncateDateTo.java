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

import java.util.Calendar;
import java.util.Date;

import org.oss.uses.org.oss.jshuntingyard.evaluator.AbstractTwoArgFunctionElement;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElementArgument;



/**
 * Truncate date to month or year.
 *
 */
public class TruncateDateTo extends AbstractTwoArgFunctionElement<Date,String,Date> {

	private final static String MODE_MONTH = "'M'";

	public TruncateDateTo() {
		super("truncateDateTo", Precedence.USERFUNCTION);
	}

	@Override
	public boolean isUserFunction() {
		return true;
	}

	/*
	 * @see org.oss.evaluator.function.string.AbstractStringOperatorAssociativityLeftTwoArg#execute(org.oss.evaluator.function.FunctionArgument, org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	protected FunctionElementArgument<Date> execute(FunctionElementArgument<String> a, FunctionElementArgument<Date> b) throws IllegalArgumentException {
		Long date = b.getValue().getTime();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		if (a.getValue().equalsIgnoreCase(MODE_MONTH)) {
			calendar.set(Calendar.MONTH, month);
		}
		date = calendar.getTimeInMillis();
		return FunctionArgumentFactory.createObject(new Date(date));
	}

}
