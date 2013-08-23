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
