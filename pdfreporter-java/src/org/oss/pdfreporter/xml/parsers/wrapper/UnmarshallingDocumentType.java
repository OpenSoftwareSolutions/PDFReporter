package org.oss.pdfreporter.xml.parsers.wrapper;


import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;

public class UnmarshallingDocumentType extends UnmarshallingNode implements DocumentType {
	private final org.oss.pdfreporter.uses.org.w3c.dom.DocumentType delegate;

	public UnmarshallingDocumentType(org.oss.pdfreporter.uses.org.w3c.dom.DocumentType delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	public org.oss.pdfreporter.uses.org.w3c.dom.DocumentType getDelegate() {
		return delegate;
	}

	@Override
	public String getName() {
		return delegate.getName();
	}

	@Override
	public NamedNodeMap getEntities() {
		return XmlParserUnmarshaller.getNamedNodeMap(delegate.getEntities());
	}

	@Override
	public NamedNodeMap getNotations() {
		return XmlParserUnmarshaller.getNamedNodeMap(delegate.getNotations());
	}

	@Override
	public String getPublicId() {
		return delegate.getPublicId();
	}

	@Override
	public String getSystemId() {
		return delegate.getSystemId();
	}

	@Override
	public String getInternalSubset() {
		return delegate.getInternalSubset();
	}
}
