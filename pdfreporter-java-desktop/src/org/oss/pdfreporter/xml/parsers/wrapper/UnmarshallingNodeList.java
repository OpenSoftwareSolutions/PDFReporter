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

import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;
import org.w3c.dom.NodeList;

import org.w3c.dom.Node;


public class UnmarshallingNodeList implements NodeList {
	private final org.oss.pdfreporter.uses.org.w3c.dom.NodeList delegate;

	public UnmarshallingNodeList(
			org.oss.pdfreporter.uses.org.w3c.dom.NodeList delegate) {
		super();
		this.delegate = delegate;
	}

	public org.oss.pdfreporter.uses.org.w3c.dom.NodeList getDelegate() {
		return delegate;
	}

	public Node item(int index) {
		return XmlParserUnmarshaller.getNode(delegate.item(index));
	}

	public int getLength() {
		return delegate.getLength();
	}

	
}
