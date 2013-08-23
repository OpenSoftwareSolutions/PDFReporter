package org.oss.pdfreporter.jasperreports.compilers.jeval.functions;

import org.oss.pdfreporter.uses.net.sourceforge.jeval.Evaluator;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.Function;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionConstants;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionException;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionResult;

public class NullValue implements Function {
	public static String QUOTED_NULL = "'#null#'";
	private static String UNQUOTED_NULL = "#null#";

	@Override
	public String getName() {
		return "null";
	}

	@Override
	public FunctionResult execute(Evaluator evaluator, String arguments)
			throws FunctionException {
		return new FunctionResult(UNQUOTED_NULL, 
				FunctionConstants.FUNCTION_RESULT_TYPE_STRING);
	}

}
