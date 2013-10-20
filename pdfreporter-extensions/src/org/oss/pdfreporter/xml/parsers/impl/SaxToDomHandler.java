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

import org.oss.pdfreporter.uses.org.w3c.dom.Document;
import org.oss.pdfreporter.uses.org.w3c.dom.Element;
import org.oss.pdfreporter.uses.org.w3c.dom.Node;
import org.oss.pdfreporter.uses.org.w3c.dom.Text;
import org.oss.pdfreporter.xml.parsers.IAttributes;
import org.oss.pdfreporter.xml.parsers.IContentHandler;
import org.oss.pdfreporter.xml.parsers.XMLParseException;

/**
 * Simple SAX to DOM handler ignoring attributes.
 * 
 * @author donatmuller, 2013, last change 1:17:40 AM
 * 
 */
public class SaxToDomHandler implements IContentHandler {

	private final Document document;
	private Node currentNode;

	SaxToDomHandler(Document document) {
		super();
		this.document = document;
		this.currentNode = document;
	}

	@Override
	public void startElement(String uri, String localName, String qName, IAttributes attributes) throws XMLParseException {
		Element element = document.createElement(qName);
		for (int i = 0; i < attributes.getLength(); i++) {
			String attrName = attributes.getLocalName(i);
			if (attrName == null || "".equals(attrName)) {
				attrName = attributes.getQName(i);
			}
			if (attrName != null || !"".equals(attrName)) {
				element.setAttribute(attrName, attributes.getValue(i));
			}
		}
		currentNode.appendChild(element);
		currentNode = element;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws XMLParseException {
		currentNode = currentNode.getParentNode();
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws XMLParseException {
		String data = new String(ch, start, length);
		Text text = document.createTextNode(data);
		currentNode.appendChild(text);
	}

}
