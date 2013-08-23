package org.oss.pdfreporter.xml.parsers.wrapper;

import org.w3c.dom.CDATASection;

public class UnmarshallingCDATASection extends UnmarshallingText implements CDATASection {

	private final org.oss.pdfreporter.uses.org.w3c.dom.CDATASection delegate;
	public UnmarshallingCDATASection(org.oss.pdfreporter.uses.org.w3c.dom.CDATASection delegate) {
		super(delegate);
		this.delegate = delegate;
	}
	
	public org.oss.pdfreporter.uses.org.w3c.dom.CDATASection getDelegate() {
		return delegate;
	}

}
