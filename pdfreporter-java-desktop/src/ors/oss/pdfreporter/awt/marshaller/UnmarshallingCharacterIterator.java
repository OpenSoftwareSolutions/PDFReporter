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
package ors.oss.pdfreporter.awt.marshaller;

import java.text.CharacterIterator;

import org.oss.pdfreporter.uses.java.awt.text.ICharacterIterator;



public class UnmarshallingCharacterIterator implements CharacterIterator {
	private final ICharacterIterator delegate;

	public UnmarshallingCharacterIterator(ICharacterIterator delegate) {
		super();
		this.delegate = delegate;
	}

	public char first() {
		return delegate.first();
	}

	public char last() {
		return delegate.last();
	}

	public char current() {
		return delegate.current();
	}

	public char next() {
		return delegate.next();
	}

	public char previous() {
		return delegate.previous();
	}

	public char setIndex(int position) {
		return delegate.setIndex(position);
	}

	public int getBeginIndex() {
		return delegate.getBeginIndex();
	}

	public int getEndIndex() {
		return delegate.getEndIndex();
	}

	public int getIndex() {
		return delegate.getIndex();
	}

	public Object clone() {
		return delegate.clone();
	}
	
}
