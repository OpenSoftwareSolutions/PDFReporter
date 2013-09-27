package org.oss.pdfreporter.xml.parsers.wrapper;

import java.io.IOException;

import org.oss.pdfreporter.xml.parsers.XMLEntityResolver;
import org.oss.pdfreporter.xml.parsers.XMLParseException;
import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class UnmarshallingEntityResolver implements EntityResolver {
	private final XMLEntityResolver delegate;

	public UnmarshallingEntityResolver(XMLEntityResolver delegate) {
		super();
		this.delegate = delegate;
	}

	public XMLEntityResolver getDelegate() {
		return delegate;
	}

	@Override
	public InputSource resolveEntity(String publicId, String systemId)
			throws SAXException, IOException {
		try {
			return XmlParserUnmarshaller.getInputSource(delegate.resolveEntity(publicId, systemId));
		} catch (XMLParseException e) {
			throw new SAXException(e);
		}
	}

	
}
