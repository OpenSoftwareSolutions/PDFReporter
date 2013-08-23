package org.oss.pdfreporter.uses.org.apache.digester.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.uses.org.apache.commons.beanutils.MethodUtils;

public class SetNextRule extends Rule {
	private final static Logger logger = Logger.getLogger(SetNextRule.class.getName());
	
	/**
     * The method name to call on the parent object.
     */
    protected String methodName = null;


    /**
     * The Java class name of the parameter type expected by the method.
     */
    protected String paramType = null;

    /**
     * Should we use exact matching. Default is no.
     */
    protected boolean useExactMatch = false;
   
    public SetNextRule(String methodName) {
        this(methodName, null);
    }
    
	public SetNextRule(String methodName, String paramType) {
		this.methodName = methodName;
		this.paramType = paramType;
	}    

	

    /**
     * Process the end of this element.
     */
    @Override
    public void end() throws Exception {

        // Identify the objects to be used
        Object child = digester.peek(0);
        Object parent = digester.peek(1);
        if (logger.isLoggable(Level.FINEST)) {
            if (parent == null) {
                logger.finest("[SetNextRule]{" + digester.getMatch() +
                        "} Call [NULL PARENT]." +
                        methodName + "(" + child + ")");
            } else {
                logger.finest("[SetNextRule]{" + digester.getMatch() +
                        "} Call " + parent.getClass().getName() + "." +
                        methodName + "(" + child + ")");
            }
        }

        // Call the specified method
        Class<?> paramTypes[] = new Class<?>[1];
        if (paramType != null) {
            paramTypes[0] = Class.forName(paramType);
        } else {
            paramTypes[0] = child.getClass();
        }
        
        if (useExactMatch) {            
            MethodUtils.invokeExactMethod(parent, methodName,
                new Object[]{ child }, paramTypes);
                
        } else {
        
            MethodUtils.invokeMethod(parent, methodName,
                new Object[]{ child }, paramTypes);
        
        }
    }


    /**
     * Render a printable version of this Rule.
     */
    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer("SetNextRule[");
        sb.append("methodName=");
        sb.append(methodName);
        sb.append(", paramType=");
        sb.append(paramType);
        sb.append("]");
        return (sb.toString());

    }

	
}
