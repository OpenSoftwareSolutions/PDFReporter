package org.oss.pdfreporter.jasperreports.compilers.jeval.functions;

import org.oss.pdfreporter.uses.net.sourceforge.jeval.Evaluator;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.Function;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionConstants;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionException;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionResult;

public class IsNull implements Function {
	private static String QUOTED_NULL = "'#null#'";
	private static String TRUE = "1.0";
	private static String FALSE = "0.0";

	@Override
	public String getName() {
		return "isNull";
	}

	@Override
	public FunctionResult execute(Evaluator evaluator, String arguments) throws FunctionException {
		String result = arguments.equals(QUOTED_NULL) ? TRUE : FALSE;
		return new FunctionResult(result, 
				FunctionConstants.FUNCTION_RESULT_TYPE_NUMERIC);
	}

}
