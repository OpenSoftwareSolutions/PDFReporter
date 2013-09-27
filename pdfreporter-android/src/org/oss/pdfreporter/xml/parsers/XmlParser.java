package org.oss.pdfreporter.xml.parsers;

import javax.xml.parsers.SAXParser;

import org.oss.pdfreporter.xml.parsers.AbstractXmlParser;
import org.oss.pdfreporter.xml.parsers.IContentHandler;
import org.oss.pdfreporter.xml.parsers.IInputSource;
import org.oss.pdfreporter.xml.parsers.ParserConfigurationException;
import org.oss.pdfreporter.xml.parsers.XMLParseException;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingContentHandler;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingEntityResolver;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingErrorHandler;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingInputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


/**
 * IXmlParser implementation based on a SAXParser.
 * @author donatmuller, 2013, last change 11:28:08 PM
 */
public class XmlParser extends AbstractXmlParser {

	private final XMLReader reader;
	
	public XmlParser(SAXParser parser, IInputSource input, IContentHandler handler) throws ParserConfigurationException {
		super(input,handler,parser.isValidating(),parser.isNamespaceAware(),parser.isXIncludeAware());
		try {
			this.reader = parser.getXMLReader();
		} catch (SAXException e) {
			throw new ParserConfigurationException(e);
		}
	}

	@Override
	public void parse() throws XMLParseException {
		try {
			configureReader();
			reader.parse(new UnmarshallingInputSource(getInput()));
		} catch (Exception e) {
			throw new XMLParseException(e);
		} 
	}
	
	@Override
	public void parse(IInputSource source) throws XMLParseException {
		try {
			configureReader();
			reader.parse(new UnmarshallingInputSource(source));
		} catch (Exception e) {
			throw new XMLParseException(e);
		} 
	}

	private void configureReader() {
		if (getEntityResolver()!=null) {
			reader.setEntityResolver(new UnmarshallingEntityResolver(getEntityResolver()));
		}
		if (getContentHandler()!=null) {
			reader.setContentHandler(new UnmarshallingContentHandler(getContentHandler()));
		}
		if (getErrorHandler()!=null) {
			reader.setErrorHandler(new UnmarshallingErrorHandler(getErrorHandler()));
		}
	}

}
