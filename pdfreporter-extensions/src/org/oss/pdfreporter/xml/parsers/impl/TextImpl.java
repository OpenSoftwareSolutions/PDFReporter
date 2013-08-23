package org.oss.pdfreporter.xml.parsers.impl;

import org.oss.pdfreporter.exception.NotImplementedException;
import org.oss.pdfreporter.uses.org.w3c.dom.DOMException;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;
import org.oss.pdfreporter.uses.org.w3c.dom.Text;

public class TextImpl extends NodeImpl implements Text {

	TextImpl(Document owner, String data) {
		super(owner, Text.TEXT_NODE, "#text", data);
	}

	@Override
	public String getData() throws DOMException {
		return getNodeValue();
	}

	@Override
	public void setData(String data) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public int getLength() {
		throw new NotImplementedException();
	}

	@Override
	public String substringData(int offset, int count) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public void appendData(String arg) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public void insertData(int offset, String arg) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public void deleteData(int offset, int count) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public void replaceData(int offset, int count, String arg)
			throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public Text splitText(int offset) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public boolean isElementContentWhitespace() {
		throw new NotImplementedException();
	}
	
	@Override
	public String getTextContent() throws DOMException {
		return getData();
	}

	@Override
	public String getWholeText() {
		throw new NotImplementedException();
	}

	@Override
	public Text replaceWholeText(String content) throws DOMException {
		throw new NotImplementedException();
	}
}
