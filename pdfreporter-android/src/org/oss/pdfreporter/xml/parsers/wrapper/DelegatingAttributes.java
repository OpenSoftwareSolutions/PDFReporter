package org.oss.pdfreporter.xml.parsers.wrapper;

import org.oss.pdfreporter.xml.parsers.IAttributes;
import org.xml.sax.Attributes;


public class DelegatingAttributes implements IAttributes {

	private final Attributes delegate;
	
	public DelegatingAttributes(Attributes delegate) {
		this.delegate = delegate;
	}
	
	public Attributes getDelegate() {
		return delegate;
	}

	public int getLength() {
		return delegate.getLength();
	}

	public String getURI(int index) {
		return delegate.getURI(index);
	}

	public String getLocalName(int index) {
		return delegate.getLocalName(index);
	}

	public String getQName(int index) {
		return delegate.getQName(index);
	}

	public String getType(int index) {
		return delegate.getType(index);
	}

	public String getValue(int index) {
		return delegate.getValue(index);
	}

	public int getIndex(String uri, String localName) {
		return delegate.getIndex(uri, localName);
	}

	public int getIndex(String qName) {
		return delegate.getIndex(qName);
	}

	public String getType(String uri, String localName) {
		return delegate.getType(uri, localName);
	}

	public String getType(String qName) {
		return delegate.getType(qName);
	}

	public String getValue(String uri, String localName) {
		return delegate.getValue(uri, localName);
	}

	public String getValue(String qName) {
		return delegate.getValue(qName);
	}
}
