package org.oss.pdfreporter.xml.parsers.wrapper;

import java.io.IOException;

import org.oss.pdfreporter.xml.parsers.IInputSource;
import org.oss.pdfreporter.xml.parsers.XMLEntityResolver;
import org.oss.pdfreporter.xml.parsers.XMLParseException;
import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;
import org.xml.sax.EntityResolver;
import org.xml.sax.SAXException;


public class DelegatingEntityResolver implements XMLEntityResolver {
	private final EntityResolver delegate;

	public DelegatingEntityResolver(EntityResolver delegate) {
		super();
		this.delegate = delegate;
	}

	public EntityResolver getDelegate() {
		return delegate;
	}

	@Override
	public IInputSource resolveEntity(String publicId, String systemId)
			throws XMLParseException, IOException {
		try {
			return XmlParserUnmarshaller.getInputSource(delegate.resolveEntity(publicId, systemId));
		} catch (SAXException e) {
			throw new XMLParseException(e);
		}
	}

	
}
