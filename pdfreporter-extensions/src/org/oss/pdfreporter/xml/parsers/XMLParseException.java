package org.oss.pdfreporter.xml.parsers;

public class XMLParseException extends Exception {

	/**
	 * Wraps native XMLParser Exception
	 */
	private static final long serialVersionUID = 1L;

	public XMLParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public XMLParseException(String message) {
		super(message);
	}

	public XMLParseException(Throwable cause) {
		super(cause);
	}

}
