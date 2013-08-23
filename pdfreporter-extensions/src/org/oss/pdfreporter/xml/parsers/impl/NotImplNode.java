package org.oss.pdfreporter.xml.parsers.impl;

import org.oss.pdfreporter.exception.NotImplementedException;
import org.oss.pdfreporter.uses.org.w3c.dom.DOMException;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;
import org.oss.pdfreporter.uses.org.w3c.dom.NamedNodeMap;
import org.oss.pdfreporter.uses.org.w3c.dom.Node;
import org.oss.pdfreporter.uses.org.w3c.dom.NodeList;
import org.oss.pdfreporter.uses.org.w3c.dom.UserDataHandler;

public class NotImplNode implements Node {

	@Override
	public String getNodeName() {
		throw new NotImplementedException();
	}

	@Override
	public String getNodeValue() throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public void setNodeValue(String nodeValue) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public short getNodeType() {
		throw new NotImplementedException();
	}

	@Override
	public Node getParentNode() {
		throw new NotImplementedException();
	}

	@Override
	public NodeList getChildNodes() {
		throw new NotImplementedException();
	}

	@Override
	public Node getFirstChild() {
		throw new NotImplementedException();
	}

	@Override
	public Node getLastChild() {
		throw new NotImplementedException();
	}

	@Override
	public Node getPreviousSibling() {
		throw new NotImplementedException();
	}

	@Override
	public Node getNextSibling() {
		throw new NotImplementedException();
	}

	@Override
	public NamedNodeMap getAttributes() {
		throw new NotImplementedException();
	}

	@Override
	public Node insertBefore(Node newChild, Node refChild) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public Node removeChild(Node oldChild) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public Node appendChild(Node newChild) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public boolean hasChildNodes() {
		throw new NotImplementedException();
	}

	@Override
	public Node cloneNode(boolean deep) {
		throw new NotImplementedException();
	}

	@Override
	public void normalize() {
		throw new NotImplementedException();
	}

	@Override
	public boolean isSupported(String feature, String version) {
		throw new NotImplementedException();
	}

	@Override
	public String getNamespaceURI() {
		throw new NotImplementedException();
	}

	@Override
	public String getPrefix() {
		throw new NotImplementedException();
	}

	@Override
	public void setPrefix(String prefix) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public String getLocalName() {
		throw new NotImplementedException();
	}

	@Override
	public boolean hasAttributes() {
		throw new NotImplementedException();
	}

	@Override
	public String getBaseURI() {
		throw new NotImplementedException();
	}

	@Override
	public short compareDocumentPosition(Node other) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public String getTextContent() throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public void setTextContent(String textContent) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public boolean isSameNode(Node other) {
		throw new NotImplementedException();
	}

	@Override
	public String lookupPrefix(String namespaceURI) {
		throw new NotImplementedException();
	}

	@Override
	public boolean isDefaultNamespace(String namespaceURI) {
		throw new NotImplementedException();
	}

	@Override
	public String lookupNamespaceURI(String prefix) {
		throw new NotImplementedException();
	}

	@Override
	public boolean isEqualNode(Node arg) {
		throw new NotImplementedException();
	}

	@Override
	public Object getFeature(String feature, String version) {
		throw new NotImplementedException();
	}

	@Override
	public Object setUserData(String key, Object data, UserDataHandler handler) {
		throw new NotImplementedException();
	}

	@Override
	public Object getUserData(String key) {
		throw new NotImplementedException();
	}

	@Override
	public Document getOwnerDocument() {
		throw new NotImplementedException();
	}

}
