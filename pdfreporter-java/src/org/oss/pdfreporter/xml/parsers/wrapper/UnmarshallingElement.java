/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.xml.parsers.wrapper;


import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;

public class UnmarshallingElement extends UnmarshallingNode implements Element {
	private final org.oss.pdfreporter.uses.org.w3c.dom.Element delegate;
	
	public UnmarshallingElement(org.oss.pdfreporter.uses.org.w3c.dom.Element delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	public org.oss.pdfreporter.uses.org.w3c.dom.Element getDelegate() {
		return delegate;
	}

	public String getTagName() {
		return delegate.getTagName();
	}

	public String getAttribute(String name) {
		return delegate.getAttribute(name);
	}

	public void setAttribute(String name, String value) throws DOMException {
		delegate.setAttribute(name, value);
	}

	public void removeAttribute(String name) throws DOMException {
		delegate.removeAttribute(name);
	}

	public Attr getAttributeNode(String name) {
		return XmlParserUnmarshaller.getAttr(delegate.getAttributeNode(name));
	}

	public Attr setAttributeNode(Attr newAttr) throws DOMException {
		return XmlParserUnmarshaller.getAttr(delegate.setAttributeNode(XmlParserUnmarshaller.getAttr(newAttr)));
	}

	public Attr removeAttributeNode(Attr oldAttr) throws DOMException {
		return XmlParserUnmarshaller.getAttr(delegate.removeAttributeNode(XmlParserUnmarshaller.getAttr(oldAttr)));
	}

	public NodeList getElementsByTagName(String name) {
		return XmlParserUnmarshaller.getNodeList(delegate.getElementsByTagName(name));
	}

	public String getAttributeNS(String namespaceURI, String localName)
			throws DOMException {
		return delegate.getAttributeNS(namespaceURI, localName);
	}

	public void setAttributeNS(String namespaceURI, String qualifiedName,
			String value) throws DOMException {
		delegate.setAttributeNS(namespaceURI, qualifiedName, value);
	}

	public void removeAttributeNS(String namespaceURI, String localName)
			throws DOMException {
		delegate.removeAttributeNS(namespaceURI, localName);
	}

	public Attr getAttributeNodeNS(String namespaceURI, String localName)
			throws DOMException {
		return XmlParserUnmarshaller.getAttr(delegate.getAttributeNodeNS(namespaceURI, localName));
	}

	public Attr setAttributeNodeNS(Attr newAttr) throws DOMException {
		return XmlParserUnmarshaller.getAttr(delegate.setAttributeNodeNS(XmlParserUnmarshaller.getAttr(newAttr)));
	}

	public NodeList getElementsByTagNameNS(String namespaceURI, String localName)
			throws DOMException {
		return XmlParserUnmarshaller.getNodeList(delegate.getElementsByTagNameNS(namespaceURI, localName));
	}

	public boolean hasAttribute(String name) {
		return delegate.hasAttribute(name);
	}

	public boolean hasAttributeNS(String namespaceURI, String localName)
			throws DOMException {
		return delegate.hasAttributeNS(namespaceURI, localName);
	}

	public TypeInfo getSchemaTypeInfo() {
		return XmlParserUnmarshaller.getTypeInfo(delegate.getSchemaTypeInfo());
	}

	public void setIdAttribute(String name, boolean isId) throws DOMException {
		delegate.setIdAttribute(name, isId);
	}

	public void setIdAttributeNS(String namespaceURI, String localName,
			boolean isId) throws DOMException {
		delegate.setIdAttributeNS(namespaceURI, localName, isId);
	}

	public void setIdAttributeNode(Attr idAttr, boolean isId)
			throws DOMException {
		delegate.setIdAttributeNode(XmlParserUnmarshaller.getAttr(idAttr), isId);
	}

	
}
