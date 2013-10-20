/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.image;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.oss.pdfreporter.registry.ISessionListener;
import org.oss.pdfreporter.registry.ISessionObject;


public abstract class AbstractImageManager implements IImageManager, ISessionListener {

	private final Logger logger = Logger.getLogger(AbstractImageManager.class.getName());
	private final Map<String,IImage> imageCache;
	
	AbstractImageManager() {
		imageCache = new HashMap<String, IImage>();
	}
	
	@Override
	public IImage loadImage(String filePath) throws IOException {
		return loadImage(filePath, 0.0f, 0.0f);
	}
	
	@Override
	public IImage loadImage(String filePath, float quality, float scale) throws IOException {
		String key = filePath + " quality:" + quality + " scale:" + scale;
		if (!imageCache.containsKey(key)) {
			imageCache.put(key, new ImageProxy(this, filePath, quality, scale));
			logger.finest("Caching image: " + filePath);
		} else {
			logger.finest("Load Image from cache: " + filePath);
		}
		return imageCache.get(key);
	}

	@Override
	public void dispose() {
		imageCache.clear();
		disposeInternal();
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
		return imageCache.values();
	}

	/**
	 * Load an Image.
	 * @param filePath
	 * @param quality
	 * @param scale
	 * @return IImage
	 * @throws IOException
	 */
	abstract IImage loadImageInternal(String filePath, float quality, float scale) throws IOException;
	
		
	/**
	 * Free all resources of previous loadImageInternal operations.
	 */
	abstract void disposeInternal();

}
