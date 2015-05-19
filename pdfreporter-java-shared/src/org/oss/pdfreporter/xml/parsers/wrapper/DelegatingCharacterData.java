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

import org.oss.pdfreporter.uses.org.w3c.dom.CharacterData;
import org.oss.pdfreporter.uses.org.w3c.dom.DOMException;

public class DelegatingCharacterData extends DelegatingNode implements CharacterData {
	private final org.w3c.dom.CharacterData delegate;

	public DelegatingCharacterData(org.w3c.dom.CharacterData delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	public org.w3c.dom.CharacterData getDelegate() {
		return delegate;
	}

	public String getData() throws DOMException {
		return delegate.getData();
	}
	
	public void setData(String data) throws DOMException {
		delegate.setData(data);
	}
	
	public int getLength() {
		return delegate.getLength();
	}
	
	public String substringData(int offset, int count) throws DOMException {
		return delegate.substringData(offset, count);
	}
	
	public void appendData(String arg) throws DOMException {
		delegate.appendData(arg);
	}
	
	public void insertData(int offset, String arg) throws DOMException {
		delegate.insertData(offset, arg);
	}
	
	public void deleteData(int offset, int count) throws DOMException {
		delegate.deleteData(offset, count);
	}
	
	public void replaceData(int offset, int count, String arg) throws DOMException {
		delegate.replaceData(offset, count, arg);
	}
	
	
}
