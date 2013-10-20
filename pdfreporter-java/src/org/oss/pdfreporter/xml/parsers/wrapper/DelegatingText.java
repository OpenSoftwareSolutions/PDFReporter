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

import org.oss.pdfreporter.uses.org.w3c.dom.DOMException;
import org.oss.pdfreporter.uses.org.w3c.dom.Text;
import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;


public class DelegatingText extends DelegatingCharacterData implements Text {

	private final org.w3c.dom.Text delegate;
	
	public DelegatingText(org.w3c.dom.Text delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	public org.w3c.dom.Text getDelegate() {
		return delegate;
	}

	@Override
	public Text splitText(int offset) throws DOMException {
		return XmlParserUnmarshaller.getText(delegate.splitText(offset));
	}

	@Override
	public boolean isElementContentWhitespace() {
		return delegate.isElementContentWhitespace();
	}

	@Override
	public String getWholeText() {
		return delegate.getWholeText();
	}

	@Override
	public Text replaceWholeText(String content) throws DOMException {
		return XmlParserUnmarshaller.getText(delegate.replaceWholeText(content));
	}

}
