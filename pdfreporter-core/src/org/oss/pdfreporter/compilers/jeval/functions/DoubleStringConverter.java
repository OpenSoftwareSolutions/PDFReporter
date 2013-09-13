package org.oss.pdfreporter.compilers.jeval.functions;

import org.oss.pdfreporter.uses.net.sourceforge.jeval.Evaluator;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.Function;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionConstants;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionException;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionResult;

public class DoubleStringConverter implements Function {

	@Override
	public String getName() {
		return "doubleString";
	}

	@Override
	public FunctionResult execute(Evaluator evaluator, String arguments)
			throws FunctionException {
		String result = null;
		Double number = null;

		try {
			number = new Double(arguments);
		} catch (Exception e) {
			throw new FunctionException("Invalid argument.", e);
		}

		result = number.toString();

		return new FunctionResult(result, 
				FunctionConstants.FUNCTION_RESULT_TYPE_STRING);
	}

}
