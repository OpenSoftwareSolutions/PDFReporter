package org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.oss.uses.org.oss.jshuntingyard.evaluator.AbstractTwoArgFunctionElement;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElementArgument;

public class ToDate extends AbstractTwoArgFunctionElement<Date,String, String> {

	public ToDate() {
		super("toDate", Precedence.USERFUNCTION);
	}

	@Override
	public boolean isUserFunction() {
		return true;
	}

	@Override
	protected FunctionElementArgument<Date> execute(
			FunctionElementArgument<String> a, FunctionElementArgument<String> b)
			throws IllegalArgumentException {
		try {
			return FunctionArgumentFactory.createObject(new SimpleDateFormat(a.getValue()).parse(b.getValue()));
		} catch (ParseException e) {
			throw new IllegalArgumentException("Cannot parse String '"+ b.getValue() + "' to Date with Date pattern: " + a.getString(),e);
		}
	}




}
