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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;

import org.oss.pdfreporter.uses.org.w3c.dom.DOMImplementation;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;
import org.oss.pdfreporter.xml.parsers.IDocumentBuilder;
import org.oss.pdfreporter.xml.parsers.IInputSource;
import org.oss.pdfreporter.xml.parsers.XMLEntityResolver;
import org.oss.pdfreporter.xml.parsers.XMLErrorHandler;
import org.oss.pdfreporter.xml.parsers.XMLParseException;
import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;
import org.xml.sax.SAXException;



public class DelegatingDocumentBuilder implements IDocumentBuilder {
	private final DocumentBuilder delegate;

	public DelegatingDocumentBuilder(DocumentBuilder delegate) {
		super();
		this.delegate = delegate;
	}

	public DocumentBuilder getDelegate() {
		return delegate;
	}

	public int hashCode() {
		return delegate.hashCode();
	}

	public void reset() {
		delegate.reset();
	}

	public boolean equals(Object obj) {
		return delegate.equals(obj);
	}

	public Document parse(InputStream is) throws XMLParseException, IOException {
		try {
			return XmlParserUnmarshaller.getDocument(delegate.parse(is));
		} catch (SAXException e) {
			throw new XMLParseException(e);
		}
	}

	public Document parse(InputStream is, String systemId) throws XMLParseException,
			IOException {
		try {
			return XmlParserUnmarshaller.getDocument(delegate.parse(is, systemId));
		} catch (SAXException e) {
			throw new XMLParseException(e);
		}
	}

	public Document parse(String uri) throws XMLParseException, IOException {
		try {
			return XmlParserUnmarshaller.getDocument(delegate.parse(uri));
		} catch (SAXException e) {
			throw new XMLParseException(e);
		}
	}

	public Document parse(File f) throws XMLParseException, IOException {
		try {
			return XmlParserUnmarshaller.getDocument(delegate.parse(f));
		} catch (SAXException e) {
			throw new XMLParseException(e);
		}
	}

	public Document parse(IInputSource is) throws XMLParseException, IOException {
		try {
			return XmlParserUnmarshaller.getDocument(delegate.parse(XmlParserUnmarshaller.getInputSource(is)));
		} catch (SAXException e) {
			throw new XMLParseException(e);
		}
	}

	public boolean isNamespaceAware() {
		return delegate.isNamespaceAware();
	}

	public boolean isValidating() {
		return delegate.isValidating();
	}

	public void setEntityResolver(XMLEntityResolver er) {
		delegate.setEntityResolver(XmlParserUnmarshaller.getEntityResolver(er));
	}

	public String toString() {
		return delegate.toString();
	}

	public void setErrorHandler(XMLErrorHandler eh) {
		delegate.setErrorHandler(XmlParserUnmarshaller.getErrorHandler(eh));
	}

	public Document newDocument() {
		return XmlParserUnmarshaller.getDocument(delegate.newDocument());
	}

	public DOMImplementation getDOMImplementation() {
		return XmlParserUnmarshaller.getDOMImplementation(delegate.getDOMImplementation());
	}

	public boolean isXIncludeAware() {
		return delegate.isXIncludeAware();
	}
	
}
