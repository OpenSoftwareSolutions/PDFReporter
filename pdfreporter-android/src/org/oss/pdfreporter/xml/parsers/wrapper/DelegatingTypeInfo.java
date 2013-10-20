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

import org.oss.pdfreporter.uses.org.w3c.dom.TypeInfo;

public class DelegatingTypeInfo implements TypeInfo {
	private final org.w3c.dom.TypeInfo delegate;

	public DelegatingTypeInfo(org.w3c.dom.TypeInfo delegate) {
		super();
		this.delegate = delegate;
	}

	public org.w3c.dom.TypeInfo getDelegate() {
		return delegate;
	}

	@Override
	public String getTypeName() {
		return delegate.getTypeName();
	}

	@Override
	public String getTypeNamespace() {
		return delegate.getTypeNamespace();
	}

	@Override
	public boolean isDerivedFrom(String typeNamespaceArg, String typeNameArg,
			int derivationMethod) {
		return delegate.isDerivedFrom(typeNamespaceArg, typeNameArg, derivationMethod);
	}
	
}
