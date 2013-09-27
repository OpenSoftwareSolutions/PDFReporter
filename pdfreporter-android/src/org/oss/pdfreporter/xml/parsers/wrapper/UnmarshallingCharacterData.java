package org.oss.pdfreporter.xml.parsers.wrapper;

import org.w3c.dom.CharacterData;
import org.w3c.dom.DOMException;

public class UnmarshallingCharacterData extends UnmarshallingNode implements CharacterData {
	private final org.oss.pdfreporter.uses.org.w3c.dom.CharacterData delegate;

	public UnmarshallingCharacterData(org.oss.pdfreporter.uses.org.w3c.dom.CharacterData delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	public org.oss.pdfreporter.uses.org.w3c.dom.CharacterData getDelegate() {
		return delegate;
	}

	public String getData() throws DOMException {
		return delegate.getData();
	}
	
	public void setData(String data) throws DOMException {
		delegate.setData(data);
	}
	
	public int getLength() {
		return delegate.getLength();
	}
	
	public String substringData(int offset, int count) throws DOMException {
		return delegate.substringData(offset, count);
	}
	
	public void appendData(String arg) throws DOMException {
		delegate.appendData(arg);
	}
	
	public void insertData(int offset, String arg) throws DOMException {
		delegate.insertData(offset, arg);
	}
	
	public void deleteData(int offset, int count) throws DOMException {
		delegate.deleteData(offset, count);
	}
	
	public void replaceData(int offset, int count, String arg) throws DOMException {
		delegate.replaceData(offset, count, arg);
	}
	
	
}
