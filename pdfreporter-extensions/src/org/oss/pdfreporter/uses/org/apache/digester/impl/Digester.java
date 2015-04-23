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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.registry.IRegistry;
import org.oss.pdfreporter.uses.org.apache.digester.IDigester;
import org.oss.pdfreporter.uses.org.apache.digester.IObjectCreationFactory;
import org.oss.pdfreporter.uses.org.apache.digester.IRule;
import org.oss.pdfreporter.uses.org.apache.digester.IRules;
import org.oss.pdfreporter.uses.org.apache.digester.SetPropertiesRule;
import org.oss.pdfreporter.xml.parsers.IAttributes;
import org.oss.pdfreporter.xml.parsers.IContentHandler;
import org.oss.pdfreporter.xml.parsers.IInputSource;
import org.oss.pdfreporter.xml.parsers.IXmlParser;
import org.oss.pdfreporter.xml.parsers.ParserConfigurationException;
import org.oss.pdfreporter.xml.parsers.XMLEntityResolver;
import org.oss.pdfreporter.xml.parsers.XMLErrorHandler;
import org.oss.pdfreporter.xml.parsers.XMLParseException;
import org.oss.pdfreporter.xml.parsers.factory.IXmlParserFactory;


public class Digester extends NotImplementedDigester implements IContentHandler {



	private final static Logger logger = Logger.getLogger(Digester.class.getName());

	private IDigester delegator;
    /**
     * The object stack being constructed.
     */
    private final Stack<Object> stack;
    /**
     * The current match pattern for nested element processing.
     */
    private String match;
    /**
     * Stack whose elements are List objects, each containing a list of
     * Rule objects as returned from Rules.getMatch(). As each xml element
     * in the input is entered, the matching rules are pushed onto this
     * stack. After the end tag is reached, the matches are popped again.
     * The depth of is stack is therefore exactly the same as the current
     * "nesting" level of the input xml.
     *
     * @since 1.6
     */
    private final Stack<List<IRule>> matches;

    /**
     * The "root" element of the stack (in other words, the last object
     * that was popped.
     */
    private Object root;

    /**
     * The parameters stack being utilized by CallMethodRule and
     * CallParamRule rules.
     *
     * @since 2.0
     */
    private final Stack<Object> params;

    /**
     * The body text of the current element.
     */
    private StringBuffer bodyText;


    /**
     * The stack of body text string buffers for surrounding elements.
     */
    private final Stack<StringBuffer> bodyTexts;

    /**
     * The <code>Rules</code> implementation containing our collection of
     * <code>Rule</code> instances and associated matching policy.  If not
     * established before the first rule is added, a default implementation
     * will be provided.
     */
    private IRules rules = null;

    /**
     * XML error handler
     * errorHandler
     */
    private XMLErrorHandler errorHandler = null;


    /**
     * XML entity resolver
     * resolver
     */
    private XMLEntityResolver resolver = null;

	public Digester() {
		this.stack = new Stack<Object>();
		this.match = "";
		this.matches  = new Stack<List<IRule>>();
		this.root = null;
		this.params  = new Stack<Object>();
		this.bodyText = new StringBuffer();
		this.bodyTexts  = new Stack<StringBuffer>();
	}

	public IXmlParserFactory getXMLParserFactory() {
		return IRegistry.getIXmlParserFactory();
	}


	@Override
	public void startElement(String namespaceURI, String localName, String qName,
			IAttributes list) throws XMLParseException {
        boolean debug = logger.isLoggable(Level.FINEST);
		if (debug) {
			logger.finest("startElement(" + namespaceURI + "," + localName + "," + qName + ")");
		}
        // Save the body text accumulated for our surrounding element
        bodyTexts.push(bodyText);
        if (debug) {
            logger.finest("  Pushing body text '" + bodyText.toString() + "'");
        }
        bodyText = new StringBuffer();

        // the actual element name is either in localName or qName, depending
        // on whether the parser is namespace aware
        String name = localName;
        if ((name == null) || (name.length() < 1)) {
            name = qName;
        }

        // Compute the current matching rule
        StringBuffer sb = new StringBuffer(match);
        if (match.length() > 0) {
            sb.append('/');
        }
        sb.append(name);
        match = sb.toString();
        if (debug) {
            logger.finest("  New match='" + match + "'");
        }

        // Fire "begin" events for all relevant rules
        List<IRule> rules = getRules().match(namespaceURI, match);
        matches.push(rules);
        if ((rules != null) && (rules.size() > 0)) {
            // TODO (10.05.2013, Donat, Open Software Solutions) Notice No Substitutor
            for (int i = 0; i < rules.size(); i++) {
                try {
                    IRule rule = rules.get(i);
                    if (debug) {
                        logger.finest("  Fire begin() for " + rule);
                    }
                    rule.begin(namespaceURI, name, list);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Begin event threw exception", e);
                    throw createSAXException(e);
                } catch (Error e) {
                	logger.log(Level.SEVERE, "Begin event threw error", e);
                    throw e;
                }
            }
        } else {
            if (debug) {
                logger.finest("  No rules found matching '" + match + "'.");
            }
        }

	}

	@Override
	public void characters(char[] buffer, int start, int length)
			throws XMLParseException {
        if (logger.isLoggable(Level.FINEST)) {
        	logger.finest("characters(" + new String(buffer, start, length) + ")");
        }
        bodyText.append(buffer, start, length);
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws XMLParseException {

        boolean debug = logger.isLoggable(Level.FINEST);

        if (debug) {
        	logger.finest("endElement(" + namespaceURI + "," + localName +
        			"," + qName + ")");
            logger.finest("  match='" + match + "'");
        }

        // the actual element name is either in localName or qName, depending
        // on whether the parser is namespace aware
        String name = localName;
        if ((name == null) || (name.length() < 1)) {
            name = qName;
        }

        // Fire "body" events for all relevant rules
        List<IRule> rules = matches.pop();
        if ((rules != null) && (rules.size() > 0)) {
            String bodyText = this.bodyText.toString();
            // TODO (10.05.2013, Donat, Open Software Solutions) Notice No Substitutor
            for (int i = 0; i < rules.size(); i++) {
                try {
                    IRule rule = rules.get(i);
                    if (debug) {
                        logger.finest("  Fire body() for " + rule);
                    }
                    rule.body(namespaceURI, name, bodyText);
                } catch (Exception e) {
                	logger.log(Level.SEVERE, "Body event threw exception", e);
                    throw createSAXException(e);
                } catch (Error e) {
                	logger.log(Level.SEVERE, "Body event threw error", e);
                    throw e;
                }
            }
        } else {
            if (debug) {
                logger.finest("  No rules found matching '" + match + "'.");
            }
        }

        // Recover the body text from the surrounding element
        bodyText = bodyTexts.pop();
        if (debug) {
            logger.finest("  Popping body text '" + bodyText.toString() + "'");
        }

        // Fire "end" events for all relevant rules in reverse order
        if (rules != null) {
            for (int i = 0; i < rules.size(); i++) {
                int j = (rules.size() - i) - 1;
                try {
                    IRule rule = rules.get(j);
                    if (debug) {
                        logger.finest("  Fire end() for " + rule);
                    }
                    rule.end(namespaceURI, name);
                } catch (Exception e) {
                	logger.log(Level.SEVERE, "End event threw exception", e);
                    throw createSAXException(e);
                } catch (Error e) {
                	logger.log(Level.SEVERE, "End event threw error", e);
                    throw e;
                }
            }
        }

        // Recover the previous match expression
        int slash = match.lastIndexOf('/');
        if (slash >= 0) {
            match = match.substring(0, slash);
        } else {
            match = "";
        }

	}


	@Override
	public Object peek() {
        try {
            return (stack.peek());
        } catch (EmptyStackException e) {
            logger.warning("Empty stack (returning null)");
            return (null);
        }
	}


	@Override
	public Object peek(int n) {
		int index = (stack.size() - 1) - n;
		if (index < 0) {
			logger.warning("Empty stack (returning null)");
			return (null);
		}
		try {
			return (stack.get(index));
		} catch (EmptyStackException e) {
			logger.warning("Empty stack (returning null)");
			return (null);
		}
	}

	@Override
	public Object pop() {
        try {
            Object popped = stack.pop();
            return popped;
        } catch (EmptyStackException e) {
            logger.warning("Empty stack (returning null)");
            return (null);
        }
	}


	@Override
	public void push(Object object) {
        if (stack.size() == 0) {
            root = object;
        }
        stack.push(object);
	}


	@Override
	public int getCount() {
        return (stack.size());
	}

	@Override
	public void setNamespaceAware(boolean namespaceAware) {
		getXMLParserFactory().setNamespaceAware(namespaceAware);
		getXMLParserFactory().configure();
	}


	@Override
	public void setRuleNamespaceURI(String ruleNamespaceURI) {
	    getRules().setNamespaceURI(ruleNamespaceURI);
	}


	@Override
	public void setErrorHandler(XMLErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}


	@Override
	public void setValidating(boolean validating) {
		getXMLParserFactory().setValidating(validating);
		getXMLParserFactory().configure();
	}


	@Override
	public void setXmlEntityResolver(XMLEntityResolver resolver) {
		this.resolver = resolver;
	}

	@Override
	public Object parse(IInputSource input) throws IOException,
			XMLParseException, ParserConfigurationException {
		IXmlParser parser = getXMLParserFactory().newXmlParser(input, this);
		parser.setErrorHandler(errorHandler);
		parser.setEntityResolver(resolver);
		parser.parse();
		return root;
	}

	@Override
	public IRules getRules() {
        if (this.rules == null) {
            this.rules = new RulesBase();
            this.rules.setDigester(this);
        }
        return (this.rules);
	}


	@Override
	public void setRules(IRules rules) {
        this.rules = rules;
        this.rules.setDigester(this);
	}


	@Override
	public void addRule(String pattern, IRule rule) {
        rule.setDigester(this);
        getRules().add(pattern, rule);
	}


	@Override
	public void addCallMethod(String pattern, String methodName, int paramCount) {
	       addRule(pattern,
	                new CallMethodRule(methodName, paramCount));
	}


	@Override
	public void addCallMethod(String pattern, String methodName) {
        addRule(
                pattern,
                new CallMethodRule(methodName));
	}


	@Override
	public void addCallParam(String pattern, int paramIndex,
			String attributeName) {
        addRule(pattern,
                new CallParamRule(paramIndex, attributeName));
	}


	@Override
	public void addFactoryCreate(String pattern, String className) {
		addRule(pattern, new FactoryCreateRule(className,null,false));
	}


	@Override
	public void addFactoryCreate(String pattern,
			IObjectCreationFactory creationFactory) {
	       creationFactory.setDigester(this);
	        addRule(pattern,
	                new FactoryCreateRule(creationFactory, false));
	}


	@Override
	public void addFactoryCreate(String pattern, Class<?> clazz) {
		addRule(pattern, new FactoryCreateRule(clazz.getName(),null,false));
	}


	@Override
	public void addObjectCreate(String pattern, Class<?> clazz) {
        addRule(pattern,
                new ObjectCreateRule(clazz.getName()));
	}


	@Override
	public void addSetNext(String pattern, String methodName, String paramType) {
        addRule(pattern,
                new SetNextRule(methodName, paramType));
	}


	@Override
	public void addSetNext(String pattern, String methodName) {
	       addRule(pattern,
	                new SetNextRule(methodName));
	}

	@Override
	public void addSetProperties(String pattern, String[] attributeNames,
			String[] propertyNames) {
		addRule(pattern,
                new SetPropertiesRule(attributeNames, propertyNames));

	}

	@Override
	public void addSetProperties(String pattern) {
		addRule(pattern,
                new SetPropertiesRule());


	}

	@Override
	public void pushParams(Object object) {
	        params.push(object);
	}

	@Override
	public Object popParams() {
	       try {
	            return (params.pop());
	        } catch (EmptyStackException e) {
	            logger.warning("Empty stack (returning null)");
	            return (null);
	        }
	}
	@Override
	public Object peekParams() {
        try {
            return (params.peek());
        } catch (EmptyStackException e) {
            logger.warning("Empty stack (returning null)");
            return (null);
        }
	}


	@Override
	public String getMatch() {
		return match;
	}


	@Override
	public void clear() {
        match = "";
        bodyTexts.clear();
        params.clear();
        stack.clear();
	}


	/**
     * Create a SAX exception which also understands about the location in
     * the digester file where the exception occurs
     *
     * @return the new exception
     */
    private XMLParseException createSAXException(Exception e) {
        if (e instanceof InvocationTargetException) {
            Throwable t = ((InvocationTargetException) e).getTargetException();
            if ((t != null) && (t instanceof Exception)) {
                e = (Exception) t;
            }
        }
        return new XMLParseException(e.getMessage(), e);
    }

	public IDigester getDelegator() {
		return delegator;
	}

	public void setDelegator(IDigester delegator) {
		this.delegator = delegator;
	}


}
