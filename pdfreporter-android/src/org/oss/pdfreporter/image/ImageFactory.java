/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
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
