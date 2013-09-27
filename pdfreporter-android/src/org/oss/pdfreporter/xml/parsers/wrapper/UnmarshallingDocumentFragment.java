package org.oss.pdfreporter.xml.parsers.wrapper;

import org.w3c.dom.DocumentFragment;

public class UnmarshallingDocumentFragment extends UnmarshallingNode implements DocumentFragment {
	private final org.oss.pdfreporter.uses.org.w3c.dom.DocumentFragment delegate;

	public UnmarshallingDocumentFragment(org.oss.pdfreporter.uses.org.w3c.dom.DocumentFragment delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	public org.oss.pdfreporter.uses.org.w3c.dom.DocumentFragment getDelegate() {
		return delegate;
	}
}
