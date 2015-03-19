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
package ors.oss.pdfreporter.awt.marshaller;

import java.awt.Color;
import java.awt.Font;

import org.oss.pdfreporter.font.IFont;
import org.oss.pdfreporter.geometry.IColor;




/**
 * for each Class in AWT, there will probable be an unmarshaller methode.
 * 
 * @author magnus
 *
 */
public class AwtUnmarshaller {

	
	public static Color getColor(IColor c){
		if (c == null) return null;
		return ((org.oss.pdfreporter.geometry.Color)c).delegate;
	}
	
	
	public static final Font getFont(IFont font) {
		if (font == null) return null;
		Font awtFont =  (Font) font.getPeer();
		return awtFont.deriveFont(font.getSize());
	}
	
}
