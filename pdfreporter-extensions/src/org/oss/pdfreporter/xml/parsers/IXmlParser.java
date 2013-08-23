package org.oss.pdfreporter.xml.parsers;

public interface IXmlParser {
	
	boolean isNamespaceAware();

	boolean isValidating();

	boolean isXIncludeAware();
	
	/**
	 * Parses the configured xml source
	 * @throws XMLParseException
	 */
	void parse() throws XMLParseException;

	/**
	 * Parses the source
	 * @param source
	 * @throws XMLParseException
	 */
	void parse(IInputSource source) throws XMLParseException;

	void setEntityResolver(XMLEntityResolver er);

	void setErrorHandler(XMLErrorHandler eh);

	void setContentHandler(IContentHandler handler);
}
