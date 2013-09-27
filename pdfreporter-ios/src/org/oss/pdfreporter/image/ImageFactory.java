package org.oss.pdfreporter.image;

import org.oss.pdfreporter.registry.ApiRegistry;

public class ImageFactory extends AbstractImageFactory {

	public static void registerFactory() {
		ApiRegistry.register(new ImageFactory());
	}
	private final IImageManager imageManager;
	
	private ImageFactory() {
		super();
		this.imageManager = new ImageManager();
	}
	
	@Override
	public IImageManager getImageManager() {
		return imageManager;
	}

}
