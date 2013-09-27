package org.oss.pdfreporter.image;

import java.io.IOException;
import java.util.Collection;

import org.oss.pdfreporter.registry.ISessionObject;

/**
 * STUB FOR COMPILATION ONLY
 * @author donatmuller, 2013, last change 10:00:14 AM
 */
public class ImageManager implements IImageManager {

	@Override
	public void dispose() {
	}

	@Override
	public void get(String key) {
	}

	@Override
	public void put(String key, ISessionObject value) {
	}

	@Override
	public void remove(String key) {
	}

	@Override
	public Collection<IImage> getLoadedImages() {
		return null;
	}

	@Override
	public IImage loadImage(String filePath) throws IOException {
		return null;
	}

	@Override
	public IImage loadImage(String filePath, float quality, float scale)
			throws IOException {
		return null;
	}

}
