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

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;

import org.oss.pdfreporter.xml.parsers.IDocumentBuilder;
import org.oss.pdfreporter.xml.parsers.XMLParseException;
import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



public class UnmarshallingDocumentBuilder extends DocumentBuilder {
	private final IDocumentBuilder delegate;

	public UnmarshallingDocumentBuilder(IDocumentBuilder delegate) {
		super();
		this.delegate = delegate;
	}

	public IDocumentBuilder getDelegate() {
		return delegate;
	}

	@Override
	public Document parse(InputSource is) throws SAXException,
			IOException {
		try {
			return XmlParserUnmarshaller.getDocument(delegate.parse(XmlParserUnmarshaller.getInputSource(is)));
		} catch (XMLParseException e) {
			throw new SAXException(e);
		}
	}

	@Override
	public boolean isNamespaceAware() {
		return delegate.isNamespaceAware();
	}

	@Override
	public boolean isValidating() {
		return delegate.isValidating();
	}

	@Override
	public void setEntityResolver(EntityResolver er) {
		delegate.setEntityResolver(XmlParserUnmarshaller.getEntityResolver(er));
		
	}

	@Override
	public void setErrorHandler(ErrorHandler eh) {
		delegate.setErrorHandler(XmlParserUnmarshaller.getErrorHandler(eh));
		
	}

	@Override
	public Document newDocument() {
		return XmlParserUnmarshaller.getDocument(delegate.newDocument());
	}

	@Override
	public DOMImplementation getDOMImplementation() {
		return XmlParserUnmarshaller.getDOMImplementation(delegate.getDOMImplementation());
	}

	
}
