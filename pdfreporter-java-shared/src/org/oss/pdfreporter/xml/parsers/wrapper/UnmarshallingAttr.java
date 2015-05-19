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
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.TypeInfo;

public class UnmarshallingAttr extends UnmarshallingNode implements Attr {
	private final org.oss.pdfreporter.uses.org.w3c.dom.Attr delegate;

	public UnmarshallingAttr(org.oss.pdfreporter.uses.org.w3c.dom.Attr delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	public org.oss.pdfreporter.uses.org.w3c.dom.Attr getDelegate() {
		return delegate;
	}

	@Override
	public String getName() {
		return delegate.getName();
	}

	@Override
	public boolean getSpecified() {
		return delegate.getSpecified();
	}

	@Override
	public String getValue() {
		return delegate.getValue();
	}

	@Override
	public void setValue(String value) throws DOMException {
		delegate.setValue(value);
	}

	@Override
	public Element getOwnerElement() {
		return null;
	}

	@Override
	public TypeInfo getSchemaTypeInfo() {
		return XmlParserUnmarshaller.getTypeInfo(delegate.getSchemaTypeInfo());
	}

	@Override
	public boolean isId() {
		return delegate.isId();
	}

	
	
}
