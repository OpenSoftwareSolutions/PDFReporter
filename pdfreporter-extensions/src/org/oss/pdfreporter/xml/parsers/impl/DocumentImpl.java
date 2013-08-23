package org.oss.pdfreporter.xml.parsers.impl;

import org.oss.pdfreporter.uses.org.w3c.dom.DOMException;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;
import org.oss.pdfreporter.uses.org.w3c.dom.Element;
import org.oss.pdfreporter.uses.org.w3c.dom.Node;
import org.oss.pdfreporter.uses.org.w3c.dom.Text;

public class DocumentImpl extends NotImplDocument implements Document {

	private Element documentElement = null;
	
	DocumentImpl() {
		super(null, Node.DOCUMENT_NODE, "#document", null);
	}

	
	@Override
	public Node appendChild(Node newChild) throws DOMException {
		if (documentElement==null && newChild instanceof Element) {
			documentElement = (Element) newChild;
		}
		return super.appendChild(newChild);
	}


	@Override
	public Document getOwnerDocument() {
		return this;
	}

	@Override
	public Element getDocumentElement() {
		return documentElement;
	}

	@Override
	public Element createElement(String tagName) throws DOMException {
		return new ElementImpl(this,tagName);
	}

	@Override
	public Text createTextNode(String data) {
		return new TextImpl(this,data);
	}
	
}
