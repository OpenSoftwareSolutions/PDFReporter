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

import org.w3c.dom.Comment;

public class UnmarshallingComment extends UnmarshallingCharacterData implements Comment {

	private org.oss.pdfreporter.uses.org.w3c.dom.Comment delegate;
	public UnmarshallingComment(org.oss.pdfreporter.uses.org.w3c.dom.Comment delegate) {
		super(delegate);
		this.delegate = delegate;
	}
	
	public org.oss.pdfreporter.uses.org.w3c.dom.Comment getDelegate() {
		return delegate;
	}

}
