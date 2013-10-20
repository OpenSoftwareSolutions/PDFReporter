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
import org.oss.pdfreporter.uses.org.w3c.dom.DOMException;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;
import org.oss.pdfreporter.uses.org.w3c.dom.Element;
import org.oss.pdfreporter.uses.org.w3c.dom.Node;
import org.oss.pdfreporter.uses.org.w3c.dom.TypeInfo;

public class AttrImpl extends NodeImpl implements org.oss.pdfreporter.uses.org.w3c.dom.Attr{

	private Element ownerElement;
	private boolean isId = false;
	
	AttrImpl(Document owner, Element ownerElement, String name, String value) {
		this(owner, ownerElement, name, value, false);
	}
	
	AttrImpl(Document owner, Element ownerElement, String name, String value, boolean isId) {
		super(owner, Node.ATTRIBUTE_NODE, name, value);
		this.ownerElement = ownerElement;
		this.isId = isId;
	}

	@Override
	public String getName() {
		return getNodeName();
	}

	@Override
	public boolean getSpecified() {
		throw new NotImplementedException();
		//return true;
	}

	@Override
	public String getValue() {
		return getNodeValue();
	}

	@Override
	public void setValue(String value) throws DOMException {
		setNodeValue(value);
	}

	@Override
	public Element getOwnerElement() {
		return ownerElement;
	}

	@Override
	public TypeInfo getSchemaTypeInfo() {
		throw new NotImplementedException();
	}

	@Override
	public boolean isId() {
		return isId;
	}

	

}
