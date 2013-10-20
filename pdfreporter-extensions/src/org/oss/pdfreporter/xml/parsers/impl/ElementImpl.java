/*******************************************************************************
 * Copyright 2013 Open Software Solutions GmbH
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.oss.pdfreporter.xml.parsers.impl;

import org.oss.pdfreporter.exception.NotImplementedException;
import org.oss.pdfreporter.uses.org.w3c.dom.Attr;
import org.oss.pdfreporter.uses.org.w3c.dom.DOMException;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;
import org.oss.pdfreporter.uses.org.w3c.dom.Element;
import org.oss.pdfreporter.uses.org.w3c.dom.Node;
import org.oss.pdfreporter.uses.org.w3c.dom.NodeList;
import org.oss.pdfreporter.uses.org.w3c.dom.TypeInfo;

public class ElementImpl extends NodeImpl implements Element {

	ElementImpl(Document owner, String name) {
		super(owner, Element.ELEMENT_NODE, name, null);
	}

	@Override
	public String getTagName() {
		return getNodeName();
	}
	
	@Override
	public String getTextContent() throws DOMException {
		StringBuilder sb = new StringBuilder();
		NodeList nodeList = getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			if(node.getNodeType() != Node.COMMENT_NODE || node.getNodeType() != Node.PROCESSING_INSTRUCTION_NODE) {
				String text = node.getTextContent();
				if(text != null) sb.append(text);
			}
		}
		return sb.toString();
	}
	
	@Override
	public String getAttribute(String name) {
		Attr atribute = getAttributeNode(name);
		if(atribute != null) {
			return atribute.getValue();
		}
		else return "";
	}

	@Override
	public void setAttribute(String name, String value) throws DOMException {
		org.oss.pdfreporter.xml.parsers.impl.AttrImpl attr = 
				new AttrImpl(getOwnerDocument(), this, name, value);
		setAttributeNode(attr);
	}

	@Override
	public void removeAttribute(String name) throws DOMException {
		getAttributes().removeNamedItem(name);
	}

	@Override
	public Attr getAttributeNode(String name) {
		return (Attr) getAttributes().getNamedItem(name);
	}

	@Override
	public boolean hasAttribute(String name) {
		return getAttributes().getNamedItem(name) != null;
	}

	@Override
	public Attr setAttributeNode(Attr newAttr) throws DOMException {
		return (Attr) getAttributes().setNamedItem(newAttr);
	}

	@Override
	public Attr removeAttributeNode(Attr oldAttr) throws DOMException {
		return (Attr) getAttributes().removeNamedItem(oldAttr.getNodeName());
	}

	@Override
	public NodeList getElementsByTagName(String name) {
		throw new NotImplementedException();
	}

	@Override
	public String getAttributeNS(String namespaceURI, String localName)
			throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public void setAttributeNS(String namespaceURI, String qualifiedName,
			String value) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public void removeAttributeNS(String namespaceURI, String localName)
			throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public Attr getAttributeNodeNS(String namespaceURI, String localName)
			throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public Attr setAttributeNodeNS(Attr newAttr) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public NodeList getElementsByTagNameNS(String namespaceURI, String localName)
			throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public boolean hasAttributeNS(String namespaceURI, String localName)
			throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public TypeInfo getSchemaTypeInfo() {
		throw new NotImplementedException();
	}

	@Override
	public void setIdAttribute(String name, boolean isId) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public void setIdAttributeNS(String namespaceURI, String localName,
			boolean isId) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public void setIdAttributeNode(Attr idAttr, boolean isId)
			throws DOMException {
		throw new NotImplementedException();
	}
}
