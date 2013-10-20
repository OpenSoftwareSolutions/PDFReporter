/* $Id: SetNestedPropertiesRule.java 992060 2010-09-02 19:09:47Z simonetripodi $
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.oss.pdfreporter.uses.org.apache.digester.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.uses.org.apache.commons.beanutils.MethodUtils;
import org.oss.pdfreporter.xml.parsers.IAttributes;


public class CallMethodRule extends Rule {
	private final static Logger logger = Logger.getLogger(CallMethodRule.class.getName());

    /**
     * The body text collected from this element.
     */
    protected String bodyText = null;


    /** 
     * location of the target object for the call, relative to the
     * top of the digester object stack. The default value of zero
     * means the target object is the one on top of the stack.
     */
    protected int targetOffset = 0;

    /**
     * The method name to call on the parent object.
     */
    protected String methodName = null;


    /**
     * The number of parameters to collect from <code>MethodParam</code> rules.
     * If this value is zero, a single parameter will be collected from the
     * body of this element.
     */
    protected int paramCount = 0;


    /**
     * The parameter types of the parameters to be collected.
     */
    protected Class<?> paramTypes[] = null;

    /**
     * The names of the classes of the parameters to be collected.
     * This attribute allows creation of the classes to be postponed until the digester is set.
     */
    private String paramClassNames[] = null;
    
    /**
     * Should <code>MethodUtils.invokeExactMethod</code> be used for reflection.
     */
    protected boolean useExactMatch = false;
    
    
    /**
     * Construct a "call method" rule with the specified method name.  The
     * parameter types (if any) default to java.lang.String.
     *
     * @param targetOffset location of the target object. Positive numbers are
     * relative to the top of the digester object stack. Negative numbers 
     * are relative to the bottom of the stack. Zero implies the top
     * object on the stack.
     * @param methodName Method name of the parent method to call
     * @param paramCount The number of parameters to collect, or
     *  zero for a single argument from the body of this element.
     */
	public CallMethodRule(int targetOffset, String methodName, int paramCount) {

		this.targetOffset = targetOffset;
		this.methodName = methodName;
		this.paramCount = paramCount;
		if (paramCount == 0) {
			this.paramTypes = new Class[] { String.class };
		} else {
			this.paramTypes = new Class[paramCount];
			for (int i = 0; i < this.paramTypes.length; i++) {
				this.paramTypes[i] = String.class;
			}
		}

	}

	public CallMethodRule(int targetOffset, String methodName, int paramCount,
			String paramTypes[]) {

		this.targetOffset = targetOffset;
		this.methodName = methodName;
		this.paramCount = paramCount;
		if (paramTypes == null) {
			this.paramTypes = new Class[paramCount];
			for (int i = 0; i < this.paramTypes.length; i++) {
				this.paramTypes[i] = String.class;
			}
		} else {
			// copy the parameter class names into an array
			// the classes will be loaded when the digester is set
			this.paramClassNames = new String[paramTypes.length];
			for (int i = 0; i < this.paramClassNames.length; i++) {
				this.paramClassNames[i] = paramTypes[i];
			}
		}
	}

	public CallMethodRule(int targetOffset, String methodName, int paramCount,
			Class<?> paramTypes[]) {

		this.targetOffset = targetOffset;
		this.methodName = methodName;
		this.paramCount = paramCount;
		if (paramTypes == null) {
			this.paramTypes = new Class[paramCount];
			for (int i = 0; i < this.paramTypes.length; i++) {
				this.paramTypes[i] = String.class;
			}
		} else {
			this.paramTypes = new Class[paramTypes.length];
			for (int i = 0; i < this.paramTypes.length; i++) {
				this.paramTypes[i] = paramTypes[i];
			}
		}
	}
	
	public CallMethodRule(int targetOffset, String methodName) {
		this(targetOffset, methodName, 0, (Class[]) null);
	}
	
	public CallMethodRule(String methodName, int paramCount) {
		this(0, methodName, paramCount);
	}

	public CallMethodRule(String methodName, int paramCount,
			Class<?> paramTypes[]) {
		this(0, methodName, paramCount, paramTypes);
	}
	
	public CallMethodRule(String methodName) {

		this(0, methodName, 0, (Class[]) null);

	}
    
	@Override
	public void begin(String namespace, String name, IAttributes attributes)
			throws Exception {
        // Push an array to capture the parameter values if necessary
        if (paramCount > 0) {
            Object parameters[] = new Object[paramCount];
            for (int i = 0; i < parameters.length; i++) {
                parameters[i] = null;
            }
            digester.pushParams(parameters);
        }		
	}

	@Override
	public void end(String namespace, String name) throws Exception {
        // Retrieve or construct the parameter values array
        Object parameters[] = null;
        if (paramCount > 0) {

            parameters = (Object[]) digester.popParams();
            
            
            // In the case where the target method takes a single parameter
            // and that parameter does not exist (the CallParamRule never
            // executed or the CallParamRule was intended to set the parameter
            // from an attribute but the attribute wasn't present etc) then
            // skip the method call.
            //
            // This is useful when a class has a "default" value that should
            // only be overridden if data is present in the XML. I don't
            // know why this should only apply to methods taking *one*
            // parameter, but it always has been so we can't change it now.
            if (paramCount == 1 && parameters[0] == null) {
                return;
            }

        } else if (paramTypes != null && paramTypes.length != 0) {
            // Having paramCount == 0 and paramTypes.length == 1 indicates
            // that we have the special case where the target method has one
            // parameter being the body text of the current element.

            // There is no body text included in the source XML file,
            // so skip the method call
            if (bodyText == null) {
                return;
            }

            parameters = new Object[1];
            parameters[0] = bodyText;
            if (paramTypes.length == 0) {
                paramTypes = new Class[1];
                paramTypes[0] = String.class;
            }

        } else {
            // When paramCount is zero and paramTypes.length is zero it
            // means that we truly are calling a method with no parameters.
            // Nothing special needs to be done here.
        }

        // Construct the parameter values array we will need
        // We only do the conversion if the param value is a String and
        // the specified paramType is not String. 
        Object paramValues[] = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            // convert nulls and convert stringy parameters 
            // for non-stringy param types
            if(
                parameters[i] == null ||
                 (parameters[i] instanceof String && 
                   !String.class.isAssignableFrom(paramTypes[i]))) {
                
//                paramValues[i] =
//                        ConvertUtils.convert((String) parameters[i], paramTypes[i]);
            } else {
                paramValues[i] = parameters[i];
            }
        }

        // Determine the target object for the method call
        Object target;
        if (targetOffset >= 0) {
            target = digester.peek(targetOffset);
        } else {
            target = digester.peek( digester.getCount() + targetOffset );
        }
        
        if (target == null) {
            StringBuffer sb = new StringBuffer();
            sb.append("[CallMethodRule]{");
            sb.append(digester.getMatch());
            sb.append("} Call target is null (");
            sb.append("targetOffset=");
            sb.append(targetOffset);
            sb.append(",stackdepth=");
            sb.append(digester.getCount());
            sb.append(")");
            throw new org.xml.sax.SAXException(sb.toString());
        }
        
        // Invoke the required method on the top object
        if (logger.isLoggable(Level.FINEST)) {
            StringBuffer sb = new StringBuffer("[CallMethodRule]{");
            sb.append(digester.getMatch());
            sb.append("} Call ");
            sb.append(target.getClass().getName());
            sb.append(".");
            sb.append(methodName);
            sb.append("(");
            for (int i = 0; i < paramValues.length; i++) {
                if (i > 0) {
                    sb.append(",");
                }
                if (paramValues[i] == null) {
                    sb.append("null");
                } else {
                    sb.append(paramValues[i].toString());
                }
                sb.append("/");
                if (paramTypes[i] == null) {
                    sb.append("null");
                } else {
                    sb.append(paramTypes[i].getName());
                }
            }
            sb.append(")");
            logger.finest(sb.toString());
        }
        
        Object result = null;
        if (useExactMatch) {
            // invoke using exact match
            result = MethodUtils.invokeExactMethod(target, methodName,
                paramValues, paramTypes);
                
        } else {
            // invoke using fuzzier match
            result = MethodUtils.invokeMethod(target, methodName,
                paramValues, paramTypes);            
        }
	}

	@Override
	public void body(String namespace, String name, String bodyText)
			throws Exception {
        if (paramCount == 0) {
            this.bodyText = bodyText.trim();
        }
		
	}

	@Override
	public void finish() throws Exception {
        bodyText = null;
	}

	   /**
     * Render a printable version of this Rule.
     */
    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer("CallMethodRule[");
        sb.append("methodName=");
        sb.append(methodName);
        sb.append(", paramCount=");
        sb.append(paramCount);
        sb.append(", paramTypes={");
        if (paramTypes != null) {
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(paramTypes[i].getName());
            }
        }
        sb.append("}");
        sb.append("]");
        return (sb.toString());

    }

}
