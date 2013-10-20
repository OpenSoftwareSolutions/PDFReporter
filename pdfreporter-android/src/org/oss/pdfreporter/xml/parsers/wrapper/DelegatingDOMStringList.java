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

import org.oss.pdfreporter.uses.org.w3c.dom.DOMStringList;

public class DelegatingDOMStringList implements DOMStringList {
	private final org.w3c.dom.DOMStringList delegate;

	public DelegatingDOMStringList(org.w3c.dom.DOMStringList delegate) {
		super();
		this.delegate = delegate;
	}

	public org.w3c.dom.DOMStringList getDelegate() {
		return delegate;
	}

	@Override
	public String item(int index) {
		return delegate.item(index);
	}

	@Override
	public int getLength() {
		return delegate.getLength();
	}

	@Override
	public boolean contains(String str) {
		return delegate.contains(str);
	}
	
}
