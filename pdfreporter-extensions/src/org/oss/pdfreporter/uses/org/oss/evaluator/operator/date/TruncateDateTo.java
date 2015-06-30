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
package org.oss.pdfreporter.uses.org.oss.evaluator.operator.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.oss.pdfreporter.uses.org.oss.evaluator.function.Function.Precedence;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.FunctionArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.FunctionArgumentFactory;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.impl.StringArgument;
import org.oss.pdfreporter.uses.org.oss.evaluator.function.string.AbstractStringOperatorAssociativityLeftTwoStringArg;



/**
 * Truncate date to month or year.
 *
 */
public class TruncateDateTo extends AbstractStringOperatorAssociativityLeftTwoStringArg {

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
	protected FunctionArgument<?> execute(FunctionArgument<String> a, FunctionArgument<String> b) throws IllegalArgumentException {


		if (a instanceof StringArgument && b instanceof StringArgument) {

		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Date d = null;
			try {
				d = dateFormat.parse(b.getValue());
			} catch (ParseException e) {
				throw new IllegalArgumentException(e);
			}

			Long date = d.getTime();

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

			return FunctionArgumentFactory.createString(date.toString());

		}
		throw new IllegalArgumentException(String.format("only string as type is supported and not ", a.getType(), b.getType()));
	}

}
