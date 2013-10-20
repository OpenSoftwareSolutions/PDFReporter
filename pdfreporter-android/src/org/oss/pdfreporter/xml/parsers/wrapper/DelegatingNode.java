/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.xml.parsers.wrapper;

import org.oss.pdfreporter.uses.org.w3c.dom.DOMException;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;
import org.oss.pdfreporter.uses.org.w3c.dom.NamedNodeMap;
import org.oss.pdfreporter.uses.org.w3c.dom.Node;
import org.oss.pdfreporter.uses.org.w3c.dom.NodeList;
import org.oss.pdfreporter.uses.org.w3c.dom.UserDataHandler;
import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;


public class DelegatingNode implements Node {
	private final org.w3c.dom.Node delegate;

	public DelegatingNode(org.w3c.dom.Node delegate) {
		super();
		this.delegate = delegate;
	}
	
	public org.w3c.dom.Node getDelegate() {
		return delegate;
	}

	public String getNodeName() {
		return delegate.getNodeName();
	}

	public String getNodeValue() throws DOMException {
		return delegate.getNodeValue();
	}

	public void setNodeValue(String nodeValue) throws DOMException {
		delegate.setNodeValue(nodeValue);
	}

	public short getNodeType() {
		return delegate.getNodeType();
	}

	public Node getParentNode() {
		return XmlParserUnmarshaller.getNode(delegate.getParentNode());
	}

	public NodeList getChildNodes() {
		return XmlParserUnmarshaller.getNodeList(delegate.getChildNodes());
	}

	public Node getFirstChild() {
		return XmlParserUnmarshaller.getNode(delegate.getFirstChild());
	}

	public Node getLastChild() {
		return XmlParserUnmarshaller.getNode(delegate.getLastChild());
	}

	public Node getPreviousSibling() {
		return XmlParserUnmarshaller.getNode(delegate.getPreviousSibling());
	}

	public Node getNextSibling() {
		return XmlParserUnmarshaller.getNode(delegate.getNextSibling());
	}

	public NamedNodeMap getAttributes() {
		return XmlParserUnmarshaller.getNamedNodeMap(delegate.getAttributes());
	}

	public Document getOwnerDocument() {
		return XmlParserUnmarshaller.getDocument(delegate.getOwnerDocument());
	}

	public Node insertBefore(Node newChild,
			Node refChild) throws DOMException {
		return XmlParserUnmarshaller.getNode(delegate.insertBefore(XmlParserUnmarshaller.getNode(newChild), XmlParserUnmarshaller.getNode(refChild)));
	}

	public Node replaceChild(Node newChild,
			Node oldChild) throws DOMException {
		return XmlParserUnmarshaller.getNode(delegate.replaceChild(XmlParserUnmarshaller.getNode(newChild), XmlParserUnmarshaller.getNode(oldChild)));
	}

	public Node removeChild(Node oldChild)
			throws DOMException {
		return XmlParserUnmarshaller.getNode(delegate.removeChild(XmlParserUnmarshaller.getNode(oldChild)));
	}

	public Node appendChild(Node newChild)
			throws DOMException {
		return XmlParserUnmarshaller.getNode(delegate.appendChild(XmlParserUnmarshaller.getNode(newChild)));
	}

	public boolean hasChildNodes() {
		return delegate.hasChildNodes();
	}

	public Node cloneNode(boolean deep) {
		return XmlParserUnmarshaller.getNode(delegate.cloneNode(deep));
	}

	public void normalize() {
		delegate.normalize();
	}

	public boolean isSupported(String feature, String version) {
		return delegate.isSupported(feature, version);
	}

	public String getNamespaceURI() {
		return delegate.getNamespaceURI();
	}

	public String getPrefix() {
		return delegate.getPrefix();
	}

	public void setPrefix(String prefix) throws DOMException {
		delegate.setPrefix(prefix);
	}

	public String getLocalName() {
		return delegate.getLocalName();
	}

	public boolean hasAttributes() {
		return delegate.hasAttributes();
	}

	public String getBaseURI() {
		return delegate.getBaseURI();
	}

	public short compareDocumentPosition(Node other)
			throws DOMException {
		return delegate.compareDocumentPosition(XmlParserUnmarshaller.getNode(other));
	}

	public String getTextContent() throws DOMException {
		return delegate.getTextContent();
	}

	public void setTextContent(String textContent)
			throws DOMException {
		delegate.setTextContent(textContent);
	}

	public boolean isSameNode(Node other) {
		return delegate.isSameNode(XmlParserUnmarshaller.getNode(other));
	}

	public String lookupPrefix(String namespaceURI) {
		return delegate.lookupPrefix(namespaceURI);
	}

	public boolean isDefaultNamespace(String namespaceURI) {
		return delegate.isDefaultNamespace(namespaceURI);
	}

	public String lookupNamespaceURI(String prefix) {
		return delegate.lookupNamespaceURI(prefix);
	}

	public boolean isEqualNode(Node arg) {
		return delegate.isEqualNode(XmlParserUnmarshaller.getNode(arg));
	}

	public Object getFeature(String feature, String version) {
		return delegate.getFeature(feature, version);
	}

	public Object setUserData(String key, Object data,
			UserDataHandler handler) {
		return delegate.setUserData(key, data, XmlParserUnmarshaller.getUserDataHandler(handler));
	}

	public Object getUserData(String key) {
		return delegate.getUserData(key);
	}

	@Override
	public String toString() {
		return delegate.toString();
	}
	
	

}
