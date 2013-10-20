/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.xml.parsers.wrapper;

import org.oss.pdfreporter.uses.org.w3c.dom.DOMConfiguration;
import org.oss.pdfreporter.uses.org.w3c.dom.DOMException;
import org.oss.pdfreporter.uses.org.w3c.dom.DOMStringList;
import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;



public class DelegatingDOMConfiguration implements DOMConfiguration {
	private final org.w3c.dom.DOMConfiguration delegate;

	public DelegatingDOMConfiguration(org.w3c.dom.DOMConfiguration delegate) {
		super();
		this.delegate = delegate;
	}

	public org.w3c.dom.DOMConfiguration getDelegate() {
		return delegate;
	}

	public void setParameter(String name, Object value) throws DOMException {
		delegate.setParameter(name, value);
	}

	public Object getParameter(String name) throws DOMException {
		return delegate.getParameter(name);
	}

	public boolean canSetParameter(String name, Object value) {
		return delegate.canSetParameter(name, value);
	}

	public DOMStringList getParameterNames() {
		return XmlParserUnmarshaller.getDOMStringList(delegate.getParameterNames());
	}
	
}
