package org.oss.pdfreporter.sql;

public class SQLException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public SQLException() {
		super();
	}

	public SQLException(String message, Throwable cause) {
		super(message, cause);
	}

	public SQLException(String message) {
		super(message);
	}

	public SQLException(Throwable cause) {
		super(cause);
	}


}
