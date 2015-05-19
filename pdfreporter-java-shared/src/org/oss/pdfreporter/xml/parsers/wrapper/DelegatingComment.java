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

import org.oss.pdfreporter.uses.org.w3c.dom.Comment;

public class DelegatingComment extends DelegatingCharacterData implements Comment {

	private final org.w3c.dom.Comment delegate;
	public DelegatingComment(org.w3c.dom.Comment delegate) {
		super(delegate);
		this.delegate = delegate;
	}
	
	public org.w3c.dom.Comment getDelegate() {
		return delegate;
	}

}
