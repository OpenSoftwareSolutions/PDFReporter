/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package org.oss.pdfreporter.image.factory;

import org.oss.pdfreporter.image.IImageManager;
import org.oss.pdfreporter.registry.ISessionCapable;

public interface IImageFactory extends ISessionCapable {
	IImageManager getImageManager();
}
