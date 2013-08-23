package org.oss.pdfreporter.xml.parsers.wrapper;

import org.oss.pdfreporter.uses.org.w3c.dom.CDATASection;

public class DelegatingCDATASection extends DelegatingText implements CDATASection {

	private final org.w3c.dom.CDATASection delegate;
	public DelegatingCDATASection(org.w3c.dom.CDATASection delegate) {
		super(delegate);
		this.delegate = delegate;
	}
	
	public org.w3c.dom.CDATASection getDelegate() {
		return delegate;
	}

}
