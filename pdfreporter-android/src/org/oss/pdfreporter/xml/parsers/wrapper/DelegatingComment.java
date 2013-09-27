package org.oss.pdfreporter.xml.parsers.wrapper;

import org.oss.pdfreporter.uses.org.w3c.dom.Comment;

public class DelegatingComment extends DelegatingCharacterData implements Comment {

	private final org.w3c.dom.Comment delegate;
	public DelegatingComment(org.w3c.dom.Comment delegate) {
		super(delegate);
		this.delegate = delegate;
	}
	
	public org.w3c.dom.Comment getDelegate() {
		return delegate;
	}

}
