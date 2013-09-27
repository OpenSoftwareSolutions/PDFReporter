package org.oss.pdfreporter.xml.parsers.wrapper;

import org.oss.pdfreporter.uses.org.w3c.dom.DOMException;
import org.oss.pdfreporter.uses.org.w3c.dom.DOMImplementation;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;
import org.oss.pdfreporter.uses.org.w3c.dom.DocumentType;
import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;


public class DelegatingDOMImplementation implements DOMImplementation {
	private final org.w3c.dom.DOMImplementation delegate;

	public DelegatingDOMImplementation(org.w3c.dom.DOMImplementation delegate) {
		super();
		this.delegate = delegate;
	}

	public org.w3c.dom.DOMImplementation getDelegate() {
		return delegate;
	}

	public boolean hasFeature(String feature, String version) {
		return delegate.hasFeature(feature, version);
	}

	public DocumentType createDocumentType(String qualifiedName,
			String publicId, String systemId) throws DOMException {
		return XmlParserUnmarshaller.getDocumentType(delegate.createDocumentType(qualifiedName, publicId, systemId));
	}

	public Document createDocument(String namespaceURI, String qualifiedName,
			DocumentType doctype) throws DOMException {
		return XmlParserUnmarshaller.getDocument(delegate.createDocument(namespaceURI, qualifiedName, XmlParserUnmarshaller.getDocumentType(doctype)));
	}

	public Object getFeature(String feature, String version) {
		return delegate.getFeature(feature, version);
	}
	
}
