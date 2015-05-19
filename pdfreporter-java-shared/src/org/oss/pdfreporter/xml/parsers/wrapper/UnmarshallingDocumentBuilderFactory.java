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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.oss.pdfreporter.xml.parsers.IDocumentBuilderFactory;
import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;


public class UnmarshallingDocumentBuilderFactory extends DocumentBuilderFactory {
	private final IDocumentBuilderFactory delegate;

	public UnmarshallingDocumentBuilderFactory(IDocumentBuilderFactory delegate) {
		super();
		this.delegate = delegate;
	}

	public IDocumentBuilderFactory getDelegate() {
		return delegate;
	}

	@Override
	public DocumentBuilder newDocumentBuilder()
			throws ParserConfigurationException {
		try {
			return XmlParserUnmarshaller.getDocumentBuilder(delegate.newDocumentBuilder());
		} catch (org.oss.pdfreporter.xml.parsers.ParserConfigurationException e) {
			throw new ParserConfigurationException(e.getMessage());
		}
	}

	@Override
	public void setAttribute(String name, Object value)
			throws IllegalArgumentException {
		delegate.setAttribute(name, value);
	}

	@Override
	public Object getAttribute(String name) throws IllegalArgumentException {
		return delegate.getAttribute(name);
	}

	@Override
	public void setFeature(String name, boolean value)
			throws ParserConfigurationException {
		try {
			delegate.setFeature(name, value);
		} catch (org.oss.pdfreporter.xml.parsers.ParserConfigurationException e) {
			throw new ParserConfigurationException(e.getMessage());
		}
	}

	@Override
	public boolean getFeature(String name) throws ParserConfigurationException {
		try {
			return delegate.getFeature(name);
		} catch (org.oss.pdfreporter.xml.parsers.ParserConfigurationException e) {
			throw new ParserConfigurationException(e.getMessage());
		}
	}
}
