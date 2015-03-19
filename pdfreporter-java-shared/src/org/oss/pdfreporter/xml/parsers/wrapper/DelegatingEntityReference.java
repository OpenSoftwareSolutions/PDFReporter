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

import org.oss.pdfreporter.uses.org.w3c.dom.EntityReference;

public class DelegatingEntityReference extends DelegatingNode implements EntityReference {

	private final org.w3c.dom.EntityReference delegate;
	public DelegatingEntityReference(org.w3c.dom.EntityReference delegate) {
		super(delegate);
		this.delegate = delegate;
	}
	
	public org.w3c.dom.EntityReference getDelegate() {
		return delegate;
	}

}
