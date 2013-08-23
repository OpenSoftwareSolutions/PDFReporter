package org.oss.pdfreporter.xml.parsers;


/**
 * Wrapper interface for ErrorHandler
 * @author donatmuller
 *
 */
public interface XMLErrorHandler {
	void warning (XMLParseException exception) throws XMLParseException;
	void error (XMLParseException exception) throws XMLParseException;
	void fatalError (XMLParseException exception) throws XMLParseException;
}
