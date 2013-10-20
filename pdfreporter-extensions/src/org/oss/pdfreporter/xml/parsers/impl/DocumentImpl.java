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
