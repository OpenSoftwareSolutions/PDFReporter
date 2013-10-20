/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.xml.parsers.wrapper;


import org.oss.pdfreporter.uses.org.w3c.dom.DocumentType;
import org.oss.pdfreporter.uses.org.w3c.dom.NamedNodeMap;
import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;


public class DelegatingDocumentType extends DelegatingNode implements DocumentType {
	private final org.w3c.dom.DocumentType delegate;

	public DelegatingDocumentType(org.w3c.dom.DocumentType delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	public org.w3c.dom.DocumentType getDelegate() {
		return delegate;
	}

	@Override
	public String getName() {
		return delegate.getName();
	}

	@Override
	public NamedNodeMap getEntities() {
		return XmlParserUnmarshaller.getNamedNodeMap(delegate.getEntities());
	}

	@Override
	public NamedNodeMap getNotations() {
		return XmlParserUnmarshaller.getNamedNodeMap(delegate.getNotations());
	}

	@Override
	public String getPublicId() {
		return delegate.getPublicId();
	}

	@Override
	public String getSystemId() {
		return delegate.getSystemId();
	}

	@Override
	public String getInternalSubset() {
		return delegate.getInternalSubset();
	}
}
