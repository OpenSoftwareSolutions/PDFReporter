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
