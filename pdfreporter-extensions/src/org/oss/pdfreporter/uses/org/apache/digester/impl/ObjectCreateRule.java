package org.oss.pdfreporter.uses.org.apache.digester.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.xml.parsers.IAttributes;


public class ObjectCreateRule extends Rule {
	private final static Logger logger = Logger.getLogger(ObjectCreateRule.class.getName());

	
	   /**
     * The attribute containing an override class name if it is present.
     */
    protected String attributeName = null;


    /**
     * The Java class name of the object to be created.
     */
    protected String className = null;

    public ObjectCreateRule(String className) {
        this(className, (String) null);
    }
    
	public ObjectCreateRule(String className, String attributeName) {

		this.className = className;
		this.attributeName = attributeName;
	}
 
	
    /**
     * Process the beginning of this element.
     *
     * @param attributes The attribute list of this element
     */
    @Override
    public void begin(IAttributes attributes) throws Exception {

        // Identify the name of the class to instantiate
        String realClassName = className;
        if (attributeName != null) {
            String value = attributes.getValue(attributeName);
            if (value != null) {
                realClassName = value;
            }
        }
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("[ObjectCreateRule]{" + digester.getMatch() +
                    "}New " + realClassName);
        }

        // Instantiate the new object and push it on the context stack
        Class<?> clazz = Class.forName(realClassName);
        Object instance = clazz.newInstance();
        digester.push(instance);

    }


    /**
     * Process the end of this element.
     */
    @Override
    public void end() throws Exception {

        Object top = digester.pop();
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("[ObjectCreateRule]{" + digester.getMatch() +
                    "} Pop " + top.getClass().getName());
        }

    }


    /**
     * Render a printable version of this Rule.
     */
    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer("ObjectCreateRule[");
        sb.append("className=");
        sb.append(className);
        sb.append(", attributeName=");
        sb.append(attributeName);
        sb.append("]");
        return (sb.toString());

    }
	
}
