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
package org.oss.pdfreporter.text;

public class ScaledLine implements IPositionedLine {

	private final IPositionedLine baseLine;
	private final float fontSize;
	
	
	ScaledLine(IPositionedLine baseLine, float fontSize) {
		super();
		this.baseLine = baseLine;
		this.fontSize = fontSize;
	}

	@Override
	public float getPosition() {
		return baseLine.getPosition() * fontSize;
	}

	@Override
	public float getThikness() {
		return baseLine.getThikness() * fontSize;
	}

	@Override
	public LineType getType() {
		return baseLine.getType();
	}

}
