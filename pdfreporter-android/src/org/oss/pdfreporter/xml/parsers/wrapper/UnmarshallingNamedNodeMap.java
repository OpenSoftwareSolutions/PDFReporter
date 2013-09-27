package org.oss.pdfreporter.xml.parsers.wrapper;

import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;
import org.w3c.dom.NamedNodeMap;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;


public class UnmarshallingNamedNodeMap implements NamedNodeMap {
	private final org.oss.pdfreporter.uses.org.w3c.dom.NamedNodeMap delegate;

	public UnmarshallingNamedNodeMap(
			org.oss.pdfreporter.uses.org.w3c.dom.NamedNodeMap delegate) {
		super();
		this.delegate = delegate;
	}

	public org.oss.pdfreporter.uses.org.w3c.dom.NamedNodeMap getDelegate() {
		return delegate;
	}

	public Node getNamedItem(String name) {
		return XmlParserUnmarshaller.getNode(delegate.getNamedItem(name));
	}

	public Node setNamedItem(Node arg) throws DOMException {
		return XmlParserUnmarshaller.getNode(delegate.setNamedItem(XmlParserUnmarshaller.getNode(arg)));
	}

	public Node removeNamedItem(String name) throws DOMException {
		return XmlParserUnmarshaller.getNode(delegate.removeNamedItem(name));
	}

	public Node item(int index) {
		return XmlParserUnmarshaller.getNode(delegate.item(index));
	}

	public int getLength() {
		return delegate.getLength();
	}

	public Node getNamedItemNS(String namespaceURI, String localName)
			throws DOMException {
		return XmlParserUnmarshaller.getNode(delegate.getNamedItemNS(namespaceURI, localName));
	}

	public Node setNamedItemNS(Node arg) throws DOMException {
		return XmlParserUnmarshaller.getNode(delegate.setNamedItemNS(XmlParserUnmarshaller.getNode(arg)));
	}

	public Node removeNamedItemNS(String namespaceURI, String localName)
			throws DOMException {
		return XmlParserUnmarshaller.getNode(delegate.removeNamedItemNS(namespaceURI, localName));
	}
	
}
