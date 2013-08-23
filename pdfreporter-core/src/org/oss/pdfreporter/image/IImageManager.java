package org.oss.pdfreporter.image;

import java.io.IOException;
import java.util.Collection;

import org.oss.pdfreporter.registry.ISessionListener;


public interface IImageManager extends ISessionListener {
	
	/**
	 * Images that are already loaded.
	 * @return
	 */
	Collection<IImage> getLoadedImages();
	/**
	 * Loads an image from file.
	 * @param filePath
	 * @return
	 */
	IImage loadImage(String filePath) throws IOException;
	/**
	 * Loads, rescale and reencode image from file as jpg
	 * @param filePath
	 * @param quality 0 - 1
	 * @param scale 1 = no scaling
	 * @return
	 * @throws IOException
	 */
	IImage loadImage(String filePath, float quality, float scale) throws IOException;
	
}
