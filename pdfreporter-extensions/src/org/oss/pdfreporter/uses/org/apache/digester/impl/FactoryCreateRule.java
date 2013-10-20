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

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.uses.org.apache.digester.IObjectCreationFactory;
import org.oss.pdfreporter.xml.parsers.IAttributes;


public class FactoryCreateRule extends Rule {
	private final static Logger logger = Logger.getLogger(FactoryCreateRule.class.getName());
	
    /** Should exceptions thrown by the factory be ignored? */
    private boolean ignoreCreateExceptions;
    /** Stock to manage */
    private Stack<Boolean> exceptionIgnoredStack;
    
    
    /**
     * The attribute containing an override class name if it is present.
     */
    protected String attributeName = null;


    /**
     * The Java class name of the ObjectCreationFactory to be created.
     * This class must have a no-arguments constructor.
     */
    protected String className = null;


    /**
     * The object creation factory we will use to instantiate objects
     * as required based on the attributes specified in the matched XML
     * element.
     */
    protected IObjectCreationFactory creationFactory = null;

    
	public FactoryCreateRule(String className, String attributeName,
			boolean ignoreCreateExceptions) {

		this.className = className;
		this.attributeName = attributeName;
		this.ignoreCreateExceptions = ignoreCreateExceptions;

	}    
	
	public FactoryCreateRule(IObjectCreationFactory creationFactory,
			boolean ignoreCreateExceptions) {

		this.creationFactory = creationFactory;
		this.ignoreCreateExceptions = ignoreCreateExceptions;
	}
 
	
    /**
     * Process the beginning of this element.
     *
     * @param attributes The attribute list of this element
     */
    @Override
    public void begin(String namespace, String name, IAttributes attributes) throws Exception {
        
        if (ignoreCreateExceptions) {
        
            if (exceptionIgnoredStack == null) {
                exceptionIgnoredStack = new Stack<Boolean>();
            }
            
            try {
                Object instance = getFactory(attributes).createObject(attributes);
                
                if (logger.isLoggable(Level.FINEST)) {
                    logger.finest("[FactoryCreateRule]{" + digester.getMatch() +
                            "} New " + (instance == null ? "null object" :
                            instance.getClass().getName()));
                }
                digester.push(instance);
                exceptionIgnoredStack.push(Boolean.FALSE);
                
            } catch (Exception e) {
                // log message and error
                if (logger.isLoggable(Level.INFO)) {
                    logger.info("[FactoryCreateRule] Create exception ignored: " +
                        ((e.getMessage() == null) ? e.getClass().getName() : e.getMessage()));
                    if (logger.isLoggable(Level.FINEST)) {
                        logger.log(Level.FINEST,"[FactoryCreateRule] Ignored exception:", e);
                    }
                }
                exceptionIgnoredStack.push(Boolean.TRUE);
            }
            
        } else {
            Object instance = getFactory(attributes).createObject(attributes);
            
            if (logger.isLoggable(Level.FINEST)) {
                logger.finest("[FactoryCreateRule]{" + digester.getMatch() +
                        "} New " + (instance == null ? "null object" :
                        instance.getClass().getName()));
            }
            digester.push(instance);
        }
    }


    /**
     * Process the end of this element.
     */
    @Override
    public void end(String namespace, String name) throws Exception {
        
        // check if object was created 
        // this only happens if an exception was thrown and we're ignoring them
        if (
                ignoreCreateExceptions &&
                exceptionIgnoredStack != null &&
                !(exceptionIgnoredStack.empty())) {
                
            if (exceptionIgnoredStack.pop().booleanValue()) {
                // creation exception was ignored
                // nothing was put onto the stack
                if (logger.isLoggable(Level.FINEST)) {
                    logger.finest("[FactoryCreateRule] No creation so no push so no pop");
                }
                return;
            }
        } 

        Object top = digester.pop();
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("[FactoryCreateRule]{" + digester.getMatch() +
                    "} Pop " + top.getClass().getName());
        }

    }


    /**
     * Clean up after parsing is complete.
     */
    @Override
    public void finish() throws Exception {

        if (attributeName != null) {
            creationFactory = null;
        }

    }


    /**
     * Render a printable version of this Rule.
     */
    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer("FactoryCreateRule[");
        sb.append("className=");
        sb.append(className);
        sb.append(", attributeName=");
        sb.append(attributeName);
        if (creationFactory != null) {
            sb.append(", creationFactory=");
            sb.append(creationFactory);
        }
        sb.append("]");
        return (sb.toString());

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Return an instance of our associated object creation factory,
     * creating one if necessary.
     *
     * @param attributes Attributes passed to our factory creation element
     *
     * @exception Exception if any error occurs
     */
    protected IObjectCreationFactory getFactory(IAttributes attributes)
            throws Exception {

        if (creationFactory == null) {
            String realClassName = className;
            if (attributeName != null) {
                String value = attributes.getValue(attributeName);
                if (value != null) {
                    realClassName = value;
                }
            }
            if (logger.isLoggable(Level.FINEST)) {
                logger.finest("[FactoryCreateRule]{" + digester.getMatch() +
                        "} New factory " + realClassName);
            }
            Class<?> clazz = Class.forName(realClassName);
            creationFactory = (IObjectCreationFactory)
                    clazz.newInstance();
            creationFactory.setDigester(digester);
        }
        return (creationFactory);

    }    
	
}
