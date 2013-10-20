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
package org.oss.pdfreporter.font;


/**
 * Metric of a font.
 * Type 1 Fonts store this information in a .afm file.
 * Unless such information i provided it can be approximated by
 * ascent = font size
 * descent = 0
 * leading = 0.25 * font size
 * @author donatmuller, 2013, last change 1:07:47 AM
 * 
 */
public interface IFontMetric {
	/**
	 * The numbers of characters of the given text that fit within width.<br>
	 * If wordwrap is set then instead of returning the exact number of<br>
	 * characters that can be displayed in width. The number of characters<br>
	 * up to the beginning of the first word that can no more be displayed<br>
	 * is returned.
	 * @param text
	 * @param width
	 * @param wordwrap
	 * @return
	 */
	int measureText(String text, int width, boolean wordwrap);
	
	
	/**
	 * Returns the width of the text.
	 * @param text
	 * @return
	 */
	int getWidth(String text);
	
	/**
	 * The fonts ascent.
	 * @return
	 */
	float getAscent();
	
	/**
	 * The fonts descent.
	 * @return
	 */
	float getDescent();
	
	/**
	 * The fonts leading.
	 * @return
	 */
	float getLeading();

}
