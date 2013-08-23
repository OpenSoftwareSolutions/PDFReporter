package org.oss.pdfreporter.font;

public class DecoratedFont implements IFont {

	private final IFont delegate;
	private final FontDecoration decoration;
	private final float size;
	
	DecoratedFont(IFont delegate, float size, FontDecoration decoration) {
		super();
		this.delegate = delegate;
		this.size = size;
		this.decoration = decoration;
		if (delegate == null) {
			System.out.println("ALERT");
		}
	}

	public String getName() {
		return delegate.getName();
	}

	public float getSize() {
		return size;
	}
	
	public FontStyle getStyle() {
		return delegate.getStyle();
	}

	public FontDecoration getDecoration() {
		return decoration;
	}

	public IFontMetric getMetric() {
		IFontMetric fm = delegate.getMetric();
		if (delegate.getSize()!=size) {
			fm = new ScaledFontMetric(delegate.getMetric(), size / delegate.getSize());
		}
		return fm;
	}

	public String getResourcePath() {
		return delegate.getResourcePath();
	}

	public Object getPeer() {
		return delegate.getPeer();
	}

	@Override
	public String getEncoding() {
		return delegate.getEncoding();
	}

	@Override
	public IFontManager getFontManager() {
		return delegate.getFontManager();
	}

}
