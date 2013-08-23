package org.oss.pdfreporter.xml.parsers.wrapper;

import org.w3c.dom.DOMStringList;

public class UnmarshallingDOMStringList implements DOMStringList {
	private final org.oss.pdfreporter.uses.org.w3c.dom.DOMStringList delegate;

	public UnmarshallingDOMStringList(org.oss.pdfreporter.uses.org.w3c.dom.DOMStringList delegate) {
		super();
		this.delegate = delegate;
	}

	public org.oss.pdfreporter.uses.org.w3c.dom.DOMStringList getDelegate() {
		return delegate;
	}

	@Override
	public String item(int index) {
		return delegate.item(index);
	}

	@Override
	public int getLength() {
		return delegate.getLength();
	}

	@Override
	public boolean contains(String str) {
		return delegate.contains(str);
	}
	
}
