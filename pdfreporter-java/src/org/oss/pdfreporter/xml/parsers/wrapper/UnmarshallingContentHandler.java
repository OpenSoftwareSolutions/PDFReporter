package org.oss.pdfreporter.xml.parsers.wrapper;

import org.oss.pdfreporter.xml.parsers.IContentHandler;
import org.oss.pdfreporter.xml.parsers.XMLParseException;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;



public class UnmarshallingContentHandler implements ContentHandler {

	private final IContentHandler delegate;
	
	public UnmarshallingContentHandler(IContentHandler delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	public void setDocumentLocator(Locator locator) {
	}

	@Override
	public void startDocument() throws SAXException {
	}

	@Override
	public void endDocument() throws SAXException {
	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		try {
			delegate.startElement(uri, localName, qName, new DelegatingAttributes(atts));
		} catch (XMLParseException e) {
			throw new SAXException(e);
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		try {
			delegate.endElement(uri, localName, qName);
		} catch (XMLParseException e) {
			throw new SAXException(e);
		}	

	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		try {
			delegate.characters(ch, start, length);
		} catch (XMLParseException e) {
			throw new SAXException(e);
		}
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
	}

}
