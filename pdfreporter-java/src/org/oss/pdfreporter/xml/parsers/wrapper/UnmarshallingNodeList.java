package org.oss.pdfreporter.xml.parsers.wrapper;

import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;
import org.w3c.dom.NodeList;

import org.w3c.dom.Node;


public class UnmarshallingNodeList implements NodeList {
	private final org.oss.pdfreporter.uses.org.w3c.dom.NodeList delegate;

	public UnmarshallingNodeList(
			org.oss.pdfreporter.uses.org.w3c.dom.NodeList delegate) {
		super();
		this.delegate = delegate;
	}

	public org.oss.pdfreporter.uses.org.w3c.dom.NodeList getDelegate() {
		return delegate;
	}

	public Node item(int index) {
		return XmlParserUnmarshaller.getNode(delegate.item(index));
	}

	public int getLength() {
		return delegate.getLength();
	}

	
}
