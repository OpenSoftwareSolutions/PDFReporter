package org.oss.pdfreporter.xml.parsers.wrapper;

import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMStringList;


public class UnmarshallingDOMConfiguration implements DOMConfiguration {
	private final org.oss.pdfreporter.uses.org.w3c.dom.DOMConfiguration delegate;

	public UnmarshallingDOMConfiguration(org.oss.pdfreporter.uses.org.w3c.dom.DOMConfiguration delegate) {
		super();
		this.delegate = delegate;
	}

	public org.oss.pdfreporter.uses.org.w3c.dom.DOMConfiguration getDelegate() {
		return delegate;
	}

	public void setParameter(String name, Object value) throws DOMException {
		delegate.setParameter(name, value);
	}

	public Object getParameter(String name) throws DOMException {
		return delegate.getParameter(name);
	}

	public boolean canSetParameter(String name, Object value) {
		return delegate.canSetParameter(name, value);
	}

	public DOMStringList getParameterNames() {
		return XmlParserUnmarshaller.getDOMStringList(delegate.getParameterNames());
	}
	
}
