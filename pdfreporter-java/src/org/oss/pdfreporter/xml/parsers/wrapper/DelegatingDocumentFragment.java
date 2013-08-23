package org.oss.pdfreporter.xml.parsers.wrapper;

import org.oss.pdfreporter.uses.org.w3c.dom.DocumentFragment;

public class DelegatingDocumentFragment extends DelegatingNode implements DocumentFragment {
	private final org.w3c.dom.DocumentFragment delegate;

	public DelegatingDocumentFragment(org.w3c.dom.DocumentFragment delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	public org.w3c.dom.DocumentFragment getDelegate() {
		return delegate;
	}
}
