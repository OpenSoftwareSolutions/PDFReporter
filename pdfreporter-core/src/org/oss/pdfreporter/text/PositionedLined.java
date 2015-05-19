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
package org.oss.pdfreporter.text;


/**
 * Defines an underline or strike through line for a font.
 * @author donatmuller, 2013, last change 11:53:45 PM
 * 
 */
public class PositionedLined implements IPositionedLine {
	private static final float STRIKETHROUGH_MULTIPLICATOR = 1f / 3;
	private static final float UNDERLINE_MULCTIPLICATOR = -1f / 12;
	private static final float THIKNESS_MULTIPLICATOR = 1f / 18;
	
	private final float position;
	private final float thikness;
	private final LineType type;
	
	
	PositionedLined(float position, float thikness, LineType type) {
		super();
		this.position = position;
		this.thikness = thikness;
		this.type = type;
	}

	public static IPositionedLine newUnderline() {
		return new PositionedLined(UNDERLINE_MULCTIPLICATOR,THIKNESS_MULTIPLICATOR,LineType.RELATIVE);
	}

	public static IPositionedLine newStrikethrough() {
		return new PositionedLined(STRIKETHROUGH_MULTIPLICATOR,THIKNESS_MULTIPLICATOR,LineType.RELATIVE);
	}

	public static IPositionedLine newLine(int position, float thikness, LineType type) {
		return new PositionedLined(position,thikness,type);
	}

	@Override
	public float getPosition() {
		return position;
	}

	@Override
	public float getThikness() {
		return thikness;
	}

	@Override
	public LineType getType() {
		return type;
	}
}
