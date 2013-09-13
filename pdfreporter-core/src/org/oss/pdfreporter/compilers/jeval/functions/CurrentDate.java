package org.oss.pdfreporter.compilers.jeval.functions;

import java.util.Date;

import org.oss.pdfreporter.uses.net.sourceforge.jeval.Evaluator;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.Function;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionConstants;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionException;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionResult;


public class CurrentDate implements Function {

	@Override
	public String getName() {
		return "now";
	}

	@Override
	public FunctionResult execute(Evaluator evaluator, String arguments)
			throws FunctionException {
		
		String result = "" + new Date().getTime();

		return new FunctionResult(result, 
				FunctionConstants.FUNCTION_RESULT_TYPE_NUMERIC);
	}

}
