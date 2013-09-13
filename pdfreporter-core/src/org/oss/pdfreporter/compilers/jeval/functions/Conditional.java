package org.oss.pdfreporter.compilers.jeval.functions;

import org.oss.pdfreporter.compilers.jeval.ResultUtil;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.ArgumentTokenizer;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.EvaluationConstants;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.Evaluator;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.Function;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionConstants;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionException;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionHelper;
import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionResult;


public class Conditional implements Function {

	private final static Double TRUE = 1.0;
	@Override
	public String getName() {
		return "ifelse";
	}

	@Override
	public FunctionResult execute(Evaluator evaluator, String arguments)
			throws FunctionException {
		String resultTrue = null;
		String resultFalse = null;
		Double expression = null;

		try {
			final ArgumentTokenizer tokenizer = new ArgumentTokenizer(
					arguments, EvaluationConstants.FUNCTION_ARGUMENT_SEPARATOR);
			expression = new Double(tokenizer.nextToken());
			resultTrue = tokenizer.nextToken();
			resultFalse = tokenizer.nextToken();
		} catch (Exception e) {
			throw new FunctionException("Invalid argument.", e);
		}

		String result = expression.compareTo(TRUE)==0 ? resultTrue : resultFalse;
		boolean isResultString = ResultUtil.isString(result,evaluator.getQuoteCharacter());
		if (isResultString) {
			result = FunctionHelper.trimAndRemoveQuoteChars(
					result, evaluator.getQuoteCharacter());
		}
		return new FunctionResult(result, 
				isResultString ? FunctionConstants.FUNCTION_RESULT_TYPE_STRING : FunctionConstants.FUNCTION_RESULT_TYPE_NUMERIC);
	}	
}
