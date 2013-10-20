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

import org.w3c.dom.DOMException;
import org.w3c.dom.ProcessingInstruction;

public class UnmarshallingProcessingInstruction extends UnmarshallingNode implements ProcessingInstruction {

	private final org.oss.pdfreporter.uses.org.w3c.dom.ProcessingInstruction delegate;
	public UnmarshallingProcessingInstruction(org.oss.pdfreporter.uses.org.w3c.dom.ProcessingInstruction delegate) {
		super(delegate);
		this.delegate = delegate;
	}
	
	public org.oss.pdfreporter.uses.org.w3c.dom.ProcessingInstruction getDelegate() {
		return delegate;
	}

	@Override
	public String getTarget() {
		return delegate.getTarget();
	}

	@Override
	public String getData() {
		return delegate.getData();
	}

	@Override
	public void setData(String data) throws DOMException {
		delegate.setData(data);
	}

}
