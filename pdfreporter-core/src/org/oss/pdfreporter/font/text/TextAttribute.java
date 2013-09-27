package org.oss.pdfreporter.font.text;

import java.util.HashMap;
import java.util.Map;

import org.oss.pdfreporter.uses.java.awt.text.IAttributedCharacterIterator.Attribute;



public class TextAttribute extends Attribute {

	private static final long serialVersionUID = 1L;
	
    // table of all instances in this class, used by readResolve
    private static final Map<String,TextAttribute> instanceMap = new HashMap<String,TextAttribute>(9);
	
	public static final TextAttribute SIZE = new TextAttribute("size");
	public static final TextAttribute FAMILY = new TextAttribute("family");
	public static final TextAttribute FONT = new TextAttribute("font");

	public static final TextAttribute FOREGROUND = new TextAttribute("foreground");
	
	public static final TextAttribute BACKGROUND = new TextAttribute("background");
	
	public static final TextAttribute WEIGHT = new TextAttribute("weight");
	public static final Float WEIGHT_BOLD = Float.valueOf(2.0f);
    public static final Float WEIGHT_REGULAR = Float.valueOf(1.0f);

	public static final TextAttribute POSTURE = new TextAttribute("posture");
	public static final Float POSTURE_OBLIQUE = Float.valueOf(0.20f);
    public static final Float POSTURE_REGULAR = Float.valueOf(0.0f);
	
	public static final TextAttribute UNDERLINE = new TextAttribute("underline");
	public static final Integer UNDERLINE_ON = Integer.valueOf(0);

	public static final TextAttribute SUPERSCRIPT = new TextAttribute("superscript");
    public static final Integer SUPERSCRIPT_SUPER = Integer.valueOf(1);
    public static final Integer SUPERSCRIPT_SUB = Integer.valueOf(-1);
	
    public static final TextAttribute STRIKETHROUGH = new TextAttribute("strikethrough");
	public static final Boolean STRIKETHROUGH_ON = Boolean.TRUE;
	

	protected TextAttribute(String name) {
		super(name);
        if (this.getClass() == TextAttribute.class) {
            instanceMap.put(name, this);
        }
	}

	

}
