package org.oss.pdfreporter.xml.parsers.wrapper;

import org.w3c.dom.EntityReference;

public class UnmarshallingEntityReference extends UnmarshallingNode implements EntityReference {

	private final org.oss.pdfreporter.uses.org.w3c.dom.EntityReference delegate;
	public UnmarshallingEntityReference(org.oss.pdfreporter.uses.org.w3c.dom.EntityReference delegate) {
		super(delegate);
		this.delegate = delegate;
	}
	
	public org.oss.pdfreporter.uses.org.w3c.dom.EntityReference getDelegate() {
		return delegate;
	}

}
