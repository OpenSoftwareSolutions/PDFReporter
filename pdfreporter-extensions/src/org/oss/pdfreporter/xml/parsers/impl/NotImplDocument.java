package org.oss.pdfreporter.xml.parsers.impl;

import org.oss.pdfreporter.exception.NotImplementedException;
import org.oss.pdfreporter.uses.org.w3c.dom.Attr;
import org.oss.pdfreporter.uses.org.w3c.dom.CDATASection;
import org.oss.pdfreporter.uses.org.w3c.dom.Comment;
import org.oss.pdfreporter.uses.org.w3c.dom.DOMConfiguration;
import org.oss.pdfreporter.uses.org.w3c.dom.DOMException;
import org.oss.pdfreporter.uses.org.w3c.dom.DOMImplementation;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;
import org.oss.pdfreporter.uses.org.w3c.dom.DocumentFragment;
import org.oss.pdfreporter.uses.org.w3c.dom.DocumentType;
import org.oss.pdfreporter.uses.org.w3c.dom.Element;
import org.oss.pdfreporter.uses.org.w3c.dom.EntityReference;
import org.oss.pdfreporter.uses.org.w3c.dom.Node;
import org.oss.pdfreporter.uses.org.w3c.dom.NodeList;
import org.oss.pdfreporter.uses.org.w3c.dom.ProcessingInstruction;
import org.oss.pdfreporter.uses.org.w3c.dom.Text;

public class NotImplDocument extends NodeImpl implements Document {

	NotImplDocument(Document owner, short type, String name, String value) {
		super(owner, type, name, value);
	}

	@Override
	public Document getOwnerDocument() {
		throw new NotImplementedException();
	}

	@Override
	public DocumentType getDoctype() {
		throw new NotImplementedException();
	}

	@Override
	public DOMImplementation getImplementation() {
		throw new NotImplementedException();
	}

	@Override
	public Element getDocumentElement() {
		throw new NotImplementedException();
	}

	@Override
	public Element createElement(String tagName) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public DocumentFragment createDocumentFragment() {
		throw new NotImplementedException();
	}

	@Override
	public Text createTextNode(String data) {
		throw new NotImplementedException();
	}

	@Override
	public Comment createComment(String data) {
		throw new NotImplementedException();
	}

	@Override
	public CDATASection createCDATASection(String data) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public ProcessingInstruction createProcessingInstruction(String target,
			String data) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public Attr createAttribute(String name) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public EntityReference createEntityReference(String name)
			throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public NodeList getElementsByTagName(String tagname) {
		throw new NotImplementedException();
	}

	@Override
	public Node importNode(Node importedNode, boolean deep) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public Element createElementNS(String namespaceURI, String qualifiedName)
			throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public Attr createAttributeNS(String namespaceURI, String qualifiedName)
			throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
		throw new NotImplementedException();
	}

	@Override
	public Element getElementById(String elementId) {
		throw new NotImplementedException();
	}

	@Override
	public String getInputEncoding() {
		throw new NotImplementedException();
	}

	@Override
	public String getXmlEncoding() {
		throw new NotImplementedException();
	}

	@Override
	public boolean getXmlStandalone() {
		throw new NotImplementedException();
	}

	@Override
	public void setXmlStandalone(boolean xmlStandalone) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public String getXmlVersion() {
		throw new NotImplementedException();
	}

	@Override
	public void setXmlVersion(String xmlVersion) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public boolean getStrictErrorChecking() {
		throw new NotImplementedException();
	}

	@Override
	public void setStrictErrorChecking(boolean strictErrorChecking) {
		throw new NotImplementedException();
	}

	@Override
	public String getDocumentURI() {
		throw new NotImplementedException();
	}

	@Override
	public void setDocumentURI(String documentURI) {
		throw new NotImplementedException();
	}

	@Override
	public Node adoptNode(Node source) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public DOMConfiguration getDomConfig() {
		throw new NotImplementedException();
	}

	@Override
	public void normalizeDocument() {
		throw new NotImplementedException();
	}

	@Override
	public Node renameNode(Node n, String namespaceURI, String qualifiedName)
			throws DOMException {
		throw new NotImplementedException();
	}

	
}
