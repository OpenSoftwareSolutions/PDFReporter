package org.oss.pdfreporter.xml.parsers.wrapper;

import org.oss.pdfreporter.uses.org.w3c.dom.EntityReference;

public class DelegatingEntityReference extends DelegatingNode implements EntityReference {

	private final org.w3c.dom.EntityReference delegate;
	public DelegatingEntityReference(org.w3c.dom.EntityReference delegate) {
		super(delegate);
		this.delegate = delegate;
	}
	
	public org.w3c.dom.EntityReference getDelegate() {
		return delegate;
	}

}
