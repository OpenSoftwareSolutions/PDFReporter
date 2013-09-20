package ch.digireport.awt.util;

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
