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

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;

import org.oss.pdfreporter.xml.parsers.IDocumentBuilder;
import org.oss.pdfreporter.xml.parsers.IDocumentBuilderFactory;
import org.oss.pdfreporter.xml.parsers.ParserConfigurationException;


public class DelegatingDocumentBuilderFactory implements IDocumentBuilderFactory {
	private final DocumentBuilderFactory delegate;

	public DelegatingDocumentBuilderFactory(DocumentBuilderFactory delegate) {
		super();
		this.delegate = delegate;
	}

	public DocumentBuilderFactory getDelegate() {
		return delegate;
	}

	public int hashCode() {
		return delegate.hashCode();
	}

	public boolean equals(Object obj) {
		return delegate.equals(obj);
	}

	public IDocumentBuilder newDocumentBuilder()
			throws ParserConfigurationException {
		try {
			return new DelegatingDocumentBuilder(delegate.newDocumentBuilder());
		} catch (javax.xml.parsers.ParserConfigurationException e) {
			throw new ParserConfigurationException(e);
		}
	}

	public void setNamespaceAware(boolean awareness) {
		delegate.setNamespaceAware(awareness);
	}

	public void setValidating(boolean validating) {
		delegate.setValidating(validating);
	}

	public void setIgnoringElementContentWhitespace(boolean whitespace) {
		delegate.setIgnoringElementContentWhitespace(whitespace);
	}

	public String toString() {
		return delegate.toString();
	}

	public void setExpandEntityReferences(boolean expandEntityRef) {
		delegate.setExpandEntityReferences(expandEntityRef);
	}

	public void setIgnoringComments(boolean ignoreComments) {
		delegate.setIgnoringComments(ignoreComments);
	}

	public void setCoalescing(boolean coalescing) {
		delegate.setCoalescing(coalescing);
	}

	public boolean isNamespaceAware() {
		return delegate.isNamespaceAware();
	}

	public boolean isValidating() {
		return delegate.isValidating();
	}

	public boolean isIgnoringElementContentWhitespace() {
		return delegate.isIgnoringElementContentWhitespace();
	}

	public boolean isExpandEntityReferences() {
		return delegate.isExpandEntityReferences();
	}

	public boolean isIgnoringComments() {
		return delegate.isIgnoringComments();
	}

	public boolean isCoalescing() {
		return delegate.isCoalescing();
	}

	public void setAttribute(String name, Object value)
			throws IllegalArgumentException {
		delegate.setAttribute(name, value);
	}

	public Object getAttribute(String name) throws IllegalArgumentException {
		return delegate.getAttribute(name);
	}

	public void setFeature(String name, boolean value)
			throws ParserConfigurationException {
		try {
			delegate.setFeature(name, value);
		} catch (javax.xml.parsers.ParserConfigurationException e) {
			throw new ParserConfigurationException(e);
		}
	}

	public boolean getFeature(String name) throws ParserConfigurationException {
		try {
			return delegate.getFeature(name);
		} catch (javax.xml.parsers.ParserConfigurationException e) {
			throw new ParserConfigurationException(e);
		}
	}

	public Schema getSchema() {
		return delegate.getSchema();
	}

	public void setSchema(Schema schema) {
		delegate.setSchema(schema);
	}

	public void setXIncludeAware(boolean state) {
		delegate.setXIncludeAware(state);
	}

	public boolean isXIncludeAware() {
		return delegate.isXIncludeAware();
	}
	
}
