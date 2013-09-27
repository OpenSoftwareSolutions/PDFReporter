package org.oss.pdfreporter.xml.parsers.wrapper;

import org.oss.pdfreporter.uses.org.w3c.dom.DOMException;
import org.oss.pdfreporter.uses.org.w3c.dom.ProcessingInstruction;

public class DelegatingProcessingInstruction extends DelegatingNode implements ProcessingInstruction {

	private final org.w3c.dom.ProcessingInstruction delegate;
	public DelegatingProcessingInstruction(org.w3c.dom.ProcessingInstruction delegate) {
		super(delegate);
		this.delegate = delegate;
	}
	
	public org.w3c.dom.ProcessingInstruction getDelegate() {
		return delegate;
	}

	@Override
	public String getTarget() {
		return delegate.getTarget();
	}

	@Override
	public String getData() {
		return delegate.getData();
	}

	@Override
	public void setData(String data) throws DOMException {
		delegate.setData(data);
	}

}
