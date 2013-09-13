package org.oss.pdfreporter.compilers.jeval;

public class ExpressionParseException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public ExpressionParseException() {
		super();
	}

	public ExpressionParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExpressionParseException(String message) {
		super(message);
	}

	public ExpressionParseException(Throwable cause) {
		super(cause);
	}
}
