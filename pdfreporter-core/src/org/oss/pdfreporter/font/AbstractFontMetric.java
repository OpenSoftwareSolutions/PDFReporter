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
package org.oss.pdfreporter.font;

/**
 * Default font metric.
 * @author donatmuller, 2013, last change 1:40:26 AM
 * 
 */
public abstract class AbstractFontMetric implements IFontMetric {

	@Override
	public float getAscent() {
		return 1.0f;
	}

	@Override
	public float getDescent() {
		return 0f;
	}

	@Override
	public float getLeading() {
		return 0.25f;
	}

}
