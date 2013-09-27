package org.oss.pdfreporter.font;

import org.oss.pdfreporter.registry.ApiRegistry;

public class FontFactory extends FontFactoryBase {

	
	public static void registerFactory() {
		ApiRegistry.register(new FontFactory());
	}
	
	private final IFontManager fontManager;
	
	private FontFactory() {
		super();
		this.fontManager = new FontManager();
	}
	
	@Override
	public IFontManager getFontManager() {
		return fontManager;
	}
}
