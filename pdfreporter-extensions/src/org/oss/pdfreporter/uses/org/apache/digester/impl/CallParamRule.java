package org.oss.pdfreporter.uses.org.apache.digester.impl;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.xml.parsers.IAttributes;


public class CallParamRule extends Rule {
	private final static Logger logger = Logger.getLogger(CallParamRule.class.getName());
	/**
     * The attribute from which to save the parameter value
     */
    protected String attributeName = null;


    /**
     * The zero-relative index of the parameter we are saving.
     */
    protected int paramIndex = 0;


    /**
     * Is the parameter to be set from the stack?
     */
    protected boolean fromStack = false;
    
    /**
     * The position of the object from the top of the stack
     */
    protected int stackIndex = 0;

    /** 
     * Stack is used to allow nested body text to be processed.
     * Lazy creation.
     */
    protected Stack<String> bodyTextStack;
    
    /**
     * Construct a "call parameter" rule that will save the value of the
     * specified attribute as the parameter value.
     *
     * @param paramIndex The zero-relative parameter number
     * @param attributeName The name of the attribute to save
     */
    public CallParamRule(int paramIndex,
                         String attributeName) {

        this.paramIndex = paramIndex;
        this.attributeName = attributeName;

    }

    /**
     * Process the start of this element.
     *
     * @param attributes The attribute list for this element
     */
    @Override
    public void begin(IAttributes attributes) throws Exception {

        Object param = null;
        
        if (attributeName != null) {
        
            param = attributes.getValue(attributeName);
            
        } else if(fromStack) {
        
            param = digester.peek(stackIndex);
            
            if (logger.isLoggable(Level.FINEST)) {
            
                StringBuffer sb = new StringBuffer("[CallParamRule]{");
                sb.append(digester.getMatch());
                sb.append("} Save from stack; from stack?").append(fromStack);
                sb.append("; object=").append(param);
                logger.finest(sb.toString());
            }   
        }
        
        // Have to save the param object to the param stack frame here.
        // Can't wait until end(). Otherwise, the object will be lost.
        // We can't save the object as instance variables, as 
        // the instance variables will be overwritten
        // if this CallParamRule is reused in subsequent nesting.
        
        if(param != null) {
            Object parameters[] = (Object[]) digester.peekParams();
            parameters[paramIndex] = param;
        }
    }
    
    /**
     * Process the body text of this element.
     *
     * @param bodyText The body text of this element
     */
    @Override
    public void body(String bodyText) throws Exception {

        if (attributeName == null && !fromStack) {
            // We must wait to set the parameter until end
            // so that we can make sure that the right set of parameters
            // is at the top of the stack
            if (bodyTextStack == null) {
                bodyTextStack = new Stack<String>();
            }
            bodyTextStack.push(bodyText.trim());
        }

    }
    
    /**
     * Process any body texts now.
     */
    @Override
    public void end(String namespace, String name) {
        if (bodyTextStack != null && !bodyTextStack.empty()) {
            // what we do now is push one parameter onto the top set of parameters
            Object parameters[] = (Object[]) digester.peekParams();
            parameters[paramIndex] = bodyTextStack.pop();
        }
    }

    /**
     * Render a printable version of this Rule.
     */
    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer("CallParamRule[");
        sb.append("paramIndex=");
        sb.append(paramIndex);
        sb.append(", attributeName=");
        sb.append(attributeName);
        sb.append(", from stack=");
        sb.append(fromStack);
        sb.append("]");
        return (sb.toString());

    }


}
