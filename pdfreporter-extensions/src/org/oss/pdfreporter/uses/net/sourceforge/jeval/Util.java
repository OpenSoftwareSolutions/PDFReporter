package org.oss.pdfreporter.uses.net.sourceforge.jeval;

public class Util {
	public static Double s2d(String value) {
		Double tmp;
		try {
			tmp = new Double(value);
		} catch (NumberFormatException e) {
			tmp = new Double(value.replace('.', ','));
		}
		return tmp;
	}
}
