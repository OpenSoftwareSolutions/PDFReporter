package ors.oss.pdfreporter.awt.marshaller;

import java.text.AttributedCharacterIterator;
import java.util.Map;
import java.util.Set;


public class AwtUnmarshallingCharacterIterator implements AttributedCharacterIterator {
	private final org.oss.pdfreporter.uses.java.awt.text.IAttributedCharacterIterator delegate;
	public AwtUnmarshallingCharacterIterator(org.oss.pdfreporter.uses.java.awt.text.IAttributedCharacterIterator delegate) {
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
	public int getRunStart() {
		return delegate.getRunStart();
	}
	public int getIndex() {
		return delegate.getIndex();
	}
	public int getRunStart(Attribute attribute) {
		return delegate.getRunStart(AwtTextAttributeUnmarshaller.fromAwt(attribute));
	}
	public Object clone() {
		return delegate.clone();
	}
	public int getRunStart(Set<? extends Attribute> attributes) {
		return delegate.getRunStart(AwtTextAttributeUnmarshaller.fromAwt(attributes));
	}
	public int getRunLimit() {
		return delegate.getRunLimit();
	}
	public int getRunLimit(Attribute attribute) {
		return delegate.getRunLimit(AwtTextAttributeUnmarshaller.fromAwt(attribute));
	}
	public int getRunLimit(Set<? extends Attribute> attributes) {
		return delegate.getRunLimit(AwtTextAttributeUnmarshaller.fromAwt(attributes));
	}
	public Map<Attribute, Object> getAttributes() {
		return AwtTextAttributeUnmarshaller.toAwt(delegate.getAttributes());
	}
	public Object getAttribute(Attribute attribute) {
		Object obj =  AwtTextAttributeUnmarshaller.unmarshallValue(delegate.getAttribute(AwtTextAttributeUnmarshaller.fromAwt(attribute)));
		return obj;
	}
	public Set<Attribute> getAllAttributeKeys() {
		Set<Attribute> attributes =  AwtTextAttributeUnmarshaller.toAwt(delegate.getAllAttributeKeys());
		return attributes;
	}
	
	
}
