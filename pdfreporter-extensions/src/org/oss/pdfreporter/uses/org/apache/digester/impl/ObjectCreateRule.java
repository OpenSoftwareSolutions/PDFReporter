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
