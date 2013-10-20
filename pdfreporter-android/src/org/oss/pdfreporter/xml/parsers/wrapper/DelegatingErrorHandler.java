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

import org.oss.pdfreporter.xml.parsers.XMLErrorHandler;
import org.oss.pdfreporter.xml.parsers.XMLParseException;
import org.xml.sax.ErrorHandler;


public class DelegatingErrorHandler implements XMLErrorHandler {

	private final ErrorHandler delegate;
	
	public DelegatingErrorHandler(ErrorHandler delegate) {
		this.delegate = delegate;
	}
	
	public ErrorHandler getDelegate() {
		return delegate;
	}

	@Override
	public void warning(XMLParseException exception) throws XMLParseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(XMLParseException exception) throws XMLParseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fatalError(XMLParseException exception)
			throws XMLParseException {
		// TODO Auto-generated method stub
		
	}


}
