/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package org.oss.pdfreporter.xml.parsers.wrapper;

import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public class UnmarshallingDocument extends UnmarshallingNode implements Document {
	
	private final org.oss.pdfreporter.uses.org.w3c.dom.Document delegate;

	public UnmarshallingDocument(org.oss.pdfreporter.uses.org.w3c.dom.Document delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	public org.oss.pdfreporter.uses.org.w3c.dom.Document getDelegate() {
		return delegate;
	}

	public DocumentType getDoctype() {
		return XmlParserUnmarshaller.getDocumentType(delegate.getDoctype());
	}

	public DOMImplementation getImplementation() {
		return XmlParserUnmarshaller.getDOMImplementation(delegate.getImplementation());
	}

	public Element getDocumentElement() {
		return XmlParserUnmarshaller.getElement(delegate.getDocumentElement());
	}

	public Element createElement(String tagName)
			throws DOMException {
		return XmlParserUnmarshaller.getElement(delegate.createElement(tagName));
	}

	public DocumentFragment createDocumentFragment() {
		return XmlParserUnmarshaller.getDocumentFragment(delegate.createDocumentFragment());
	}

	public Text createTextNode(String data) {
		return XmlParserUnmarshaller.getText(delegate.createTextNode(data));
	}

	public Comment createComment(String data) {
		return XmlParserUnmarshaller.getComment(delegate.createComment(data));
	}

	public CDATASection createCDATASection(String data)
			throws DOMException {
		return XmlParserUnmarshaller.getCDATASection(delegate.createCDATASection(data));
	}

	public ProcessingInstruction createProcessingInstruction(
			String target, String data) throws DOMException {
		return XmlParserUnmarshaller.getProcessingInstruction(delegate.createProcessingInstruction(target, data));
	}

	public Attr createAttribute(String name)
			throws DOMException {
		return XmlParserUnmarshaller.getAttr(delegate.createAttribute(name));
	}

	public EntityReference createEntityReference(String name)
			throws DOMException {
		return XmlParserUnmarshaller.getEntityReference(delegate.createEntityReference(name));
	}

	public NodeList getElementsByTagName(String tagname) {
		return XmlParserUnmarshaller.getNodeList(delegate.getElementsByTagName(tagname));
	}

	public Node importNode(Node importedNode,
			boolean deep) throws DOMException {
		return XmlParserUnmarshaller.getNode(delegate.importNode(XmlParserUnmarshaller.getNode(importedNode), deep));
	}

	public Element createElementNS(String namespaceURI,
			String qualifiedName) throws DOMException {
		return XmlParserUnmarshaller.getElement(delegate.createElementNS(namespaceURI, qualifiedName));
	}

	public Attr createAttributeNS(String namespaceURI,
			String qualifiedName) throws DOMException {
		return XmlParserUnmarshaller.getAttr(delegate.createAttributeNS(namespaceURI, qualifiedName));
	}

	public NodeList getElementsByTagNameNS(String namespaceURI,
			String localName) {
		return XmlParserUnmarshaller.getNodeList(delegate.getElementsByTagNameNS(namespaceURI, localName));
	}

	public Element getElementById(String elementId) {
		return XmlParserUnmarshaller.getElement(delegate.getElementById(elementId));
	}

	public String getInputEncoding() {
		return delegate.getInputEncoding();
	}

	public String getXmlEncoding() {
		return delegate.getXmlEncoding();
	}

	public boolean getXmlStandalone() {
		return delegate.getXmlStandalone();
	}

	public void setXmlStandalone(boolean xmlStandalone)
			throws DOMException {
		delegate.setXmlStandalone(xmlStandalone);
	}

	public String getXmlVersion() {
		return delegate.getXmlVersion();
	}

	public void setXmlVersion(String xmlVersion)
			throws DOMException {
		delegate.setXmlVersion(xmlVersion);
	}

	public boolean getStrictErrorChecking() {
		return delegate.getStrictErrorChecking();
	}

	public void setStrictErrorChecking(boolean strictErrorChecking) {
		delegate.setStrictErrorChecking(strictErrorChecking);
	}

	public String getDocumentURI() {
		return delegate.getDocumentURI();
	}

	public void setDocumentURI(String documentURI) {
		delegate.setDocumentURI(documentURI);
	}

	public Node adoptNode(Node source)
			throws DOMException {
		return XmlParserUnmarshaller.getNode(delegate.adoptNode(XmlParserUnmarshaller.getNode(source)));
	}

	public DOMConfiguration getDomConfig() {
		return XmlParserUnmarshaller.getDOMConfiguration(delegate.getDomConfig());
	}

	public void normalizeDocument() {
		delegate.normalizeDocument();
	}

	public Node renameNode(Node n, String namespaceURI,
			String qualifiedName) throws DOMException {
		return XmlParserUnmarshaller.getNode(delegate.renameNode(XmlParserUnmarshaller.getNode(n), namespaceURI, qualifiedName));
	}


}
