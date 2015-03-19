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
import org.w3c.dom.Node;
import org.w3c.dom.UserDataHandler;

public class UnmarshallingUserDataHandler implements UserDataHandler {
	private final org.oss.pdfreporter.uses.org.w3c.dom.UserDataHandler delegate;

	public UnmarshallingUserDataHandler(org.oss.pdfreporter.uses.org.w3c.dom.UserDataHandler delegate) {
		super();
		this.delegate = delegate;
	}

	public org.oss.pdfreporter.uses.org.w3c.dom.UserDataHandler getDelegate() {
		return delegate;
	}

	public void handle(short operation, String key, Object data, Node src,
			Node dst) {
		delegate.handle(operation, key, data, XmlParserUnmarshaller.getNode(src), XmlParserUnmarshaller.getNode(dst));
	}
	
}
