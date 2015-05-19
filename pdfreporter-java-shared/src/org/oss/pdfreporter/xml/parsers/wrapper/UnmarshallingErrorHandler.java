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

import org.oss.pdfreporter.xml.parsers.XMLErrorHandler;
import org.oss.pdfreporter.xml.parsers.XMLParseException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class UnmarshallingErrorHandler implements org.xml.sax.ErrorHandler {

	private final XMLErrorHandler delegate;
	
	public UnmarshallingErrorHandler(XMLErrorHandler delegate) {
		this.delegate = delegate;
	}
	
	public XMLErrorHandler getDelegate() {
		return delegate;
	}

	@Override
	public void warning(SAXParseException exception) throws SAXException {
		try {
			delegate.warning(new XMLParseException(exception));
		} catch (XMLParseException e) {
			throw new SAXException(e);
		}
	}

	@Override
	public void error(SAXParseException exception) throws SAXException {
		try {
			delegate.error(new XMLParseException(exception));
		} catch (XMLParseException e) {
			throw new SAXException(e);
		}
	}

	@Override
	public void fatalError(SAXParseException exception) throws SAXException {
		try {
			delegate.fatalError(new XMLParseException(exception));
		} catch (XMLParseException e) {
			throw new SAXException(e);
		}
	}

}
