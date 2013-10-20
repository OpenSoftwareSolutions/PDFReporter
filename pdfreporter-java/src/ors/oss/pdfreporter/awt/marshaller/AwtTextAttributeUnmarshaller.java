package ors.oss.pdfreporter.awt.marshaller;

import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.oss.pdfreporter.exception.NotImplementedException;
import org.oss.pdfreporter.font.IFont;
import org.oss.pdfreporter.font.text.TextAttribute;
import org.oss.pdfreporter.geometry.IColor;
import org.oss.pdfreporter.uses.java.awt.text.IAttributedCharacterIterator.Attribute;


public class AwtTextAttributeUnmarshaller {
	
	public static Attribute fromAwt(java.text.AttributedCharacterIterator.Attribute a) {
		if (a instanceof java.awt.font.TextAttribute) {
			return fromAwt((java.awt.font.TextAttribute)a);
		}
		throw new UnmarshallException("Cannot unmarshall " + a + " can only handle instnces of java.awt.font.TextAttribute");
	}
	
	public static Attribute[] fromAwt(java.text.AttributedCharacterIterator.Attribute[] in) {
		Attribute[] out = new Attribute[in.length];
		for (int i=0;i<in.length;i++) {
			out[i] = fromAwt(in[i]);
		}
		return out;
	}
	
	public static TextAttribute fromAwt(java.awt.font.TextAttribute a) {
		try {
			// Access the private name field of java.awt.font.TextAttribut
			Field nameField = a.getClass().getSuperclass().getDeclaredField("name");
			nameField.setAccessible(true);
			String awtName = (String) nameField.get(a);
			
			// Access the private instanceMap field of org.oss.pdfreporter.awt.font.TextAttribut
			Field instanceMapField = TextAttribute.class.getDeclaredField("instanceMap");
			instanceMapField.setAccessible(true);
			Map drMap = (Map) instanceMapField.get(null);
			
			return (TextAttribute) drMap.get(awtName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
	
	public static java.text.AttributedCharacterIterator.Attribute toAwt(Attribute a) {
		if (a instanceof TextAttribute) {
			return toAwt((TextAttribute)a);
		} 
		return OtherAttribute.getInstance(a);
	}
	
	private static class OtherAttribute extends java.text.AttributedCharacterIterator.Attribute {

		private static final long serialVersionUID = 1L;
		private static final Map<String,OtherAttribute> instanceMap = new HashMap<String,OtherAttribute>(4);
		
		private OtherAttribute(String name)
		{
			super(name);
			
			if (this.getClass() == OtherAttribute.class)
			{
				instanceMap.put(name, this);
			}
		}
		
		public static OtherAttribute getInstance(Attribute a) {
			try {
				// Access the private name field of java.awt.font.TextAttribut
				Field nameField = a.getClass().getSuperclass().getDeclaredField("name");
				nameField.setAccessible(true);
				String awtName = (String) nameField.get(a);
				if (!instanceMap.containsKey(awtName)) {
					new OtherAttribute(awtName);
				}
				return instanceMap.get(awtName);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} 
		}
		
	}
	
	
	public static java.text.AttributedCharacterIterator.Attribute[] toAwt(Attribute[] in) {
		java.text.AttributedCharacterIterator.Attribute[] out = new java.text.AttributedCharacterIterator.Attribute[in.length];
		for (int i=0;i<in.length;i++) {
			out[i] = toAwt(in[i]);
		}
		return out;
	}
	
	@SuppressWarnings("rawtypes")
	public static java.awt.font.TextAttribute toAwt(TextAttribute a) {
		try {
			// Access the private static instanceMap field of java.awt.font.TextAttribut
			Field instanceMapField = java.awt.font.TextAttribute.class.getDeclaredField("instanceMap");
			instanceMapField.setAccessible(true);
			Map awtInstanceMap = (Map) instanceMapField.get(null);
			
			// Access the private name field of org.oss.pdfreporter.awt.font.TextAttribut
			Field nameField = a.getClass().getSuperclass().getDeclaredField("name");
			nameField.setAccessible(true);
			String drName = (String) nameField.get(a);
			
			return (java.awt.font.TextAttribute) awtInstanceMap.get(drName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
	
//	private static java.awt.font.TextAttribute addToAwt(IAttributedCharacterIterator.Attribute a) {
//		
//	}
	
	
	
	public static AbstractSet<Attribute> fromAwt(Set<? extends java.text.AttributedCharacterIterator.Attribute> delegate) {
		return new AttributeSet(delegate);
	}
	
	
	private static class AttributeSet extends AbstractSet<Attribute> {
		private final Set<? extends java.text.AttributedCharacterIterator.Attribute> delegate;
		
		public AttributeSet(
				Set<? extends java.text.AttributedCharacterIterator.Attribute> delegate) {
			super();
			this.delegate = delegate;
		}

		@Override
		public Iterator<Attribute> iterator() {
			return new AttributeIterator(delegate.iterator());
		}

		@Override
		public int size() {
			return delegate.size();
		}
	}
	
	private static class AttributeIterator implements Iterator<Attribute> {

		private final Iterator<? extends java.text.AttributedCharacterIterator.Attribute> delegate;
		
		public AttributeIterator(
				Iterator<? extends java.text.AttributedCharacterIterator.Attribute> delegate) {
			super();
			this.delegate = delegate;
		}

		@Override
		public boolean hasNext() {
			return delegate.hasNext();
		}

		@Override
		public Attribute next() {
			return fromAwt(delegate.next());
		}

		@Override
		public void remove() {
			delegate.remove();
		}
		
	}
	
	
	public static AbstractMap<java.text.AttributedCharacterIterator.Attribute, Object> toAwt(Map<Attribute, Object> delegate) {
		return new AwtAttributeMap(delegate);
	}
	
	
	
	private static class AwtAttributeMap extends AbstractMap<java.text.AttributedCharacterIterator.Attribute, Object> {
		private final Set<java.util.Map.Entry<Attribute, Object>> delegate;
		
		private AwtAttributeMap(Map<Attribute, Object> delegate) {
			super();
			this.delegate = delegate.entrySet();
		}

		@Override
		public Set<java.util.Map.Entry<java.text.AttributedCharacterIterator.Attribute, Object>> entrySet() {
			return new AwtAttributeEntrySet(delegate);
		}
		
	}
	
	private static class AwtAttributeEntrySet extends AbstractSet<java.util.Map.Entry<java.text.AttributedCharacterIterator.Attribute, Object>> {

		private final Set<java.util.Map.Entry<Attribute, Object>> delegate;
		
		public AwtAttributeEntrySet(Set<java.util.Map.Entry<Attribute, Object>> delegate) {
			super();
			this.delegate = delegate;
		}

		@Override
		public Iterator<java.util.Map.Entry<java.text.AttributedCharacterIterator.Attribute, Object>> iterator() {
			return new AwtAttributeEntryIterator(delegate.iterator());
		}

		@Override
		public int size() {
			return delegate.size();
		}
		
	}
	
	
	private static class AwtAttributeEntryIterator implements Iterator<java.util.Map.Entry<java.text.AttributedCharacterIterator.Attribute, Object>> {

		private final Iterator<java.util.Map.Entry<Attribute, Object>> delegate;
		
		public AwtAttributeEntryIterator(Iterator<java.util.Map.Entry<Attribute, Object>> delegate) {
			super();
			this.delegate = delegate;
		}

		@Override
		public boolean hasNext() {
			return delegate.hasNext();
		}

		@Override
		public java.util.Map.Entry<java.text.AttributedCharacterIterator.Attribute, Object> next() {
			return new AwtAttributeEntry(delegate.next()); 
		}

		@Override
		public void remove() {
			delegate.remove();
		}
		
	}
	
	private static class AwtAttributeEntry implements java.util.Map.Entry<java.text.AttributedCharacterIterator.Attribute, Object> {

		private final java.util.Map.Entry<Attribute, Object> delegate;
		
		public AwtAttributeEntry(Entry<Attribute, Object> delegate) {
			super();
			this.delegate = delegate;
		}

		@Override
		public java.text.AttributedCharacterIterator.Attribute getKey() {
			return toAwt(delegate.getKey());
		}

		@Override
		public Object getValue() {
			return unmarshallValue(delegate.getValue());
		}

		@Override
		public Object setValue(Object value) {
			throw new NotImplementedException();
		}
		
	}
	
	/**
	 * Marshalls an attribute value of an AttributedCharacterIterator
	 * @param value
	 * @return
	 */
	protected static Object unmarshallValue(Object value) {
		if (value instanceof IColor) {
			return AwtUnmarshaller.getColor((IColor) value);
		} else if (value instanceof IFont) {
			return AwtUnmarshaller.getFont((IFont) value);
		} 
		return value;
	}
	
	public static Set<java.text.AttributedCharacterIterator.Attribute> toAwt(Set<Attribute> delegate) {
		return new AwtAttributeSet(delegate);
	}
	private static class AwtAttributeSet extends AbstractSet<java.text.AttributedCharacterIterator.Attribute> {

		private final Set<Attribute> delegate;
		
		public AwtAttributeSet(Set<Attribute> delegate) {
			super();
			this.delegate = delegate;
		}

		@Override
		public Iterator<java.text.AttributedCharacterIterator.Attribute> iterator() {
			return new AwtAttributeIterator(delegate.iterator());
		}

		@Override
		public int size() {
			return delegate.size();
		}
		
	}

	private static class AwtAttributeIterator implements Iterator<java.text.AttributedCharacterIterator.Attribute> {

		private final Iterator<Attribute> delegate;
		
		public AwtAttributeIterator(Iterator<Attribute> delegate) {
			super();
			this.delegate = delegate;
		}

		@Override
		public boolean hasNext() {
			return delegate.hasNext();
		}

		@Override
		public java.text.AttributedCharacterIterator.Attribute next() {
			return toAwt(delegate.next()); 
		}

		@Override
		public void remove() {
			delegate.remove();
		}
		
	}
	
	public static java.util.Map.Entry<Attribute, Object> fromAwt(java.util.Map.Entry<java.awt.font.TextAttribute, ?> delegate) {
		return new AwtTextAttributeEntry(delegate);
	}
	
	private static class AwtTextAttributeEntry implements java.util.Map.Entry<Attribute, Object> {

		private final java.util.Map.Entry<java.awt.font.TextAttribute, ?> delegate;
		
		public AwtTextAttributeEntry(java.util.Map.Entry<java.awt.font.TextAttribute, ?> delegate) {
			super();
			this.delegate = delegate;
		}

		@Override
		public TextAttribute getKey() {
			return fromAwt(delegate.getKey());
		}

		@Override
		public Object getValue() {
			return delegate.getValue();
		}

		@Override
		public Object setValue(Object value) {
			throw new NotImplementedException();
		}
		
	}
	

}
