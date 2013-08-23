package org.oss.pdfreporter.image;


public interface IImage {
	/**
	 * Width of image.
	 * @return
	 */
	int getWidth();
	/**
	 * Height of image
	 * @return
	 */
	int getHeight();
	
	/**
	 * Returns the path to the image resource
	 * @return
	 */
	String getResourcePath();
	
	/**
	 * Returns an internal or native representation of the Image
	 * @return
	 */
	Object getPeer();
	
	/**
	 * Returns the image manager.
	 * @return
	 */
	IImageManager getImageManager();
}
