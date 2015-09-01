package org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.oss.uses.org.oss.jshuntingyard.evaluator.AbstractOneArgFunctionElement;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElementArgument;

public class ToLocalDate extends AbstractOneArgFunctionElement<Date,String> {

	public ToLocalDate() {
		super("toLocalDate", Precedence.USERFUNCTION);
	}

	@Override
	public boolean isUserFunction() {
		return true;
	}

	@Override
	protected FunctionElementArgument<Date> execute(
			FunctionElementArgument<String> a) throws IllegalArgumentException {
		try {
			return FunctionArgumentFactory.createObject(SimpleDateFormat.getDateInstance().parse(a.getValue()));
		} catch (ParseException e) {
			throw new IllegalArgumentException("Cannot parse String '"+ a.getValue() + "' to Date with default Locale",e);
		}
	}



}
