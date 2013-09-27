package org.oss.pdfreporter.image;


public class Image implements IImage {
	private final ImageManager imageManager;
	private final com.lowagie.text.Image delegate;
	private final String imagePath;
	
	Image(ImageManager imageManager, com.lowagie.text.Image image, String imagePath) {
		this.imageManager = imageManager;
		this.delegate = image;
		this.imagePath = imagePath;
	}
	@Override
	public int getWidth() {
		return (int) delegate.getPlainWidth();
	}

	@Override
	public int getHeight() {
		return (int) delegate.getPlainHeight();
	}
	
	public com.lowagie.text.Image getPeer() {
		return delegate;
	}
	@Override
	public String getResourcePath() {
		return imagePath;
	}
	@Override
	public IImageManager getImageManager() {
		return imageManager;
	}

}
