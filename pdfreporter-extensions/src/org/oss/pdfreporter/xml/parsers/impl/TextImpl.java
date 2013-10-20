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
import org.oss.pdfreporter.uses.org.w3c.dom.Text;

public class TextImpl extends NodeImpl implements Text {

	TextImpl(Document owner, String data) {
		super(owner, Text.TEXT_NODE, "#text", data);
	}

	@Override
	public String getData() throws DOMException {
		return getNodeValue();
	}

	@Override
	public void setData(String data) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public int getLength() {
		throw new NotImplementedException();
	}

	@Override
	public String substringData(int offset, int count) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public void appendData(String arg) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public void insertData(int offset, String arg) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public void deleteData(int offset, int count) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public void replaceData(int offset, int count, String arg)
			throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public Text splitText(int offset) throws DOMException {
		throw new NotImplementedException();
	}

	@Override
	public boolean isElementContentWhitespace() {
		throw new NotImplementedException();
	}
	
	@Override
	public String getTextContent() throws DOMException {
		return getData();
	}

	@Override
	public String getWholeText() {
		throw new NotImplementedException();
	}

	@Override
	public Text replaceWholeText(String content) throws DOMException {
		throw new NotImplementedException();
	}
}
