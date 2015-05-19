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

public interface IFontPeer {
	/**
	 * Returns the font metric for size and style.
	 * @return
	 */
	IFontMetric getMetric();
		
	/**
	 * Returns an internal or native representation of the font.
	 * @return
	 */
	Object getPeer();

}
