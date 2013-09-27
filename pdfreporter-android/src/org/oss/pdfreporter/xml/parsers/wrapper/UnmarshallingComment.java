package org.oss.pdfreporter.xml.parsers.wrapper;

import org.w3c.dom.Comment;

public class UnmarshallingComment extends UnmarshallingCharacterData implements Comment {

	private org.oss.pdfreporter.uses.org.w3c.dom.Comment delegate;
	public UnmarshallingComment(org.oss.pdfreporter.uses.org.w3c.dom.Comment delegate) {
		super(delegate);
		this.delegate = delegate;
	}
	
	public org.oss.pdfreporter.uses.org.w3c.dom.Comment getDelegate() {
		return delegate;
	}

}
