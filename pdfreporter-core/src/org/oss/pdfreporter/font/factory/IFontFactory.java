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
package org.oss.pdfreporter.font.factory;

import org.oss.pdfreporter.font.IFontManager;
import org.oss.pdfreporter.font.text.IBreakIterator;
import org.oss.pdfreporter.font.text.ILineBreakMeasurer;
import org.oss.pdfreporter.registry.ISessionCapable;
import org.oss.pdfreporter.uses.java.awt.text.AttributedString;


public interface IFontFactory extends ISessionCapable {
	/**
	 * Returns the font manager
	 * @return
	 */
	IFontManager getFontManager();
	
	/**
	 * Returns a line break measurer.
	 * @param attributedString
	 * @return
	 */
	ILineBreakMeasurer newLineBreakMeasurer(AttributedString attributedString);
	
	/**
	 * Returns a line break measurer.
	 * @param attributedString
	 * @param breakIterator
	 * @return
	 */
	ILineBreakMeasurer newLineBreakMeasurer(AttributedString attributedString, IBreakIterator breakIterator);
	
	
	/**
	 * Returns a break iterator that breaks at character boundaries.
	 * @return
	 */
	IBreakIterator newCharacterBreakIterator();
	
	/**
	 * Returns a break iterator that breaks at line boundaries.
	 * @return
	 */
	IBreakIterator newWordBreakIterator();}
