package org.oss.pdfreporter.xml.parsers.wrapper;

import org.oss.pdfreporter.uses.org.w3c.dom.Node;
import org.oss.pdfreporter.uses.org.w3c.dom.NodeList;
import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;


public class DelegatingNodeList implements NodeList {
	private final org.w3c.dom.NodeList delegate;

	public DelegatingNodeList(org.w3c.dom.NodeList delegate) {
		super();
		this.delegate = delegate;
	}

	public org.w3c.dom.NodeList getDelegate() {
		return delegate;
	}
	
	public Node item(int index) {
		return XmlParserUnmarshaller.getNode(delegate.item(index));
	}

	public int getLength() {
		return delegate.getLength();
	}

}
