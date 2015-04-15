/*******************************************************************************
 * Copyright 2013 Open Software Solutions GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.oss.pdfreporter.uses.org.apache.digester;

import java.io.IOException;

import org.oss.pdfreporter.xml.parsers.IInputSource;
import org.oss.pdfreporter.xml.parsers.ParserConfigurationException;
import org.oss.pdfreporter.xml.parsers.XMLEntityResolver;
import org.oss.pdfreporter.xml.parsers.XMLErrorHandler;
import org.oss.pdfreporter.xml.parsers.XMLParseException;



public interface IDigester {

	/**
	 * Return the current depth of the element stack.
	 */
	int getCount();

	/**
	 * Set the "namespace aware" flag for parsers we create.
	 *
	 * @param namespaceAware The new "namespace aware" flag
	 */
	void setNamespaceAware(boolean namespaceAware);

	/**
	 * Set the namespace URI that will be applied to all subsequently
	 * added <code>Rule</code> objects.
	 *
	 * @param ruleNamespaceURI Namespace URI that must match on all
	 *  subsequently added rules, or <code>null</code> for matching
	 *  regardless of the current namespace URI
	 */
	void setRuleNamespaceURI(String ruleNamespaceURI);

	/**
	 * Parse the content of the specified input source using this Digester.
	 * Returns the root element from the object stack (if any).
	 *
	 * @param input Input source containing the XML data to be parsed
	 * @exception IOException if an input/output error occurs
	 * @throws XMLParseException TODO
	 */
	Object parse(IInputSource input) throws IOException, XMLParseException, ParserConfigurationException;

	/**
	 * <p>Register a new Rule matching the specified pattern.
	 * This method sets the <code>Digester</code> property on the rule.</p>
	 *
	 * @param pattern Element matching pattern
	 * @param rule Rule to be registered
	 */
	void addRule(String pattern, IRule rule);

	/**
	 * Register a set of Rule instances defined in a RuleSet.
	 *
	 * @param ruleSet The RuleSet instance to configure from
	 */
	void addRuleSet(IRuleSet ruleSet);

	/**
	 * Add an "call method" rule for the specified parameters.
	 *
	 * @param pattern Element matching pattern
	 * @param methodName Method name to be called
	 * @param paramCount Number of expected parameters (or zero
	 *  for a single parameter from the body of this element)
	 * @see CallMethodRule
	 */
	void addCallMethod(String pattern, String methodName, int paramCount);

	/**
     * Add an "call method" rule for a method which accepts no arguments.
     *
     * @param pattern Element matching pattern
     * @param methodName Method name to be called
     * @see CallMethodRule
     */
    public void addCallMethod(String pattern, String methodName);

	/**
	 * Add a "call parameter" rule for the specified parameters.
	 *
	 * @param pattern Element matching pattern
	 * @param paramIndex Zero-relative parameter index to set
	 *  (from the specified attribute)
	 * @param attributeName Attribute whose value is used as the
	 *  parameter value
	 * @see CallParamRule
	 */
	void addCallParam(String pattern, int paramIndex, String attributeName);

	/**
	 * Add a "factory create" rule for the specified parameters.
	 * Exceptions thrown during the object creation process will be propagated.
	 *
	 * @param pattern Element matching pattern
	 * @param className Java class name of the object creation factory class
	 * @see FactoryCreateRule
	 */
	void addFactoryCreate(String pattern, String className);

    /**
     * Add a "factory create" rule for the specified parameters.
     * Exceptions thrown during the object creation process will be propagated.
     *
     * @param pattern Element matching pattern
     * @param creationFactory Previously instantiated ObjectCreationFactory
     *  to be utilized
     * @see FactoryCreateRule
     */
	public void addFactoryCreate(String pattern,  IObjectCreationFactory creationFactory);

	/**
	 * Add a "factory create" rule for the specified parameters.
	 * Exceptions thrown during the object creation process will be propagated.
	 *
	 * @param pattern Element matching pattern
	 * @param clazz Java class of the object creation factory class
	 * @see FactoryCreateRule
	 */
	void addFactoryCreate(String pattern, Class<?> clazz);

	/**
	 * Add an "object create" rule for the specified parameters.
	 *
	 * @param pattern Element matching pattern
	 * @param clazz Java class to be created
	 * @see ObjectCreateRule
	 */
	void addObjectCreate(String pattern, Class<?> clazz);

	/**
	 * Add a "set next" rule for the specified parameters.
	 *
	 * @param pattern Element matching pattern
	 * @param methodName Method name to call on the parent element
	 * @param paramType Java class name of the expected parameter type
	 *  (if you wish to use a primitive type, specify the corresonding
	 *  Java wrapper class instead, such as <code>java.lang.Boolean</code>
	 *  for a <code>boolean</code> parameter)
	 * @see SetNextRule
	 */
	void addSetNext(String pattern, String methodName, String paramType);

	 /**
     * Add a "set next" rule for the specified parameters.
     *
     * @param pattern Element matching pattern
     * @param methodName Method name to call on the parent element
     * @see SetNextRule
     */
    public void addSetNext(String pattern, String methodName);


    /**
     * Add a "set properties" rule with overridden parameters.
     * See {@link SetPropertiesRule#SetPropertiesRule(String [] attributeNames, String [] propertyNames)}
     *
     * @param pattern Element matching pattern
     * @param attributeNames names of attributes with custom mappings
     * @param propertyNames property names these attributes map to
     * @see SetPropertiesRule
     */
    public void addSetProperties(
                                String pattern,
                                String [] attributeNames,
                                String [] propertyNames);

    /**
     * Add a "set properties" rule for the specified parameters.
     *
     * @param pattern Element matching pattern
     * @see SetPropertiesRule
     */
    public void addSetProperties(String pattern);

	/**
	 * Clear the current contents of the default object stack, the param stack,
	 * all named stacks, and other internal variables.
	 * <p>
	 * Calling this method <i>might</i> allow another document of the same type
	 * to be correctly parsed. However this method was not intended for this
	 * purpose (just to tidy up memory usage). In general, a separate Digester
	 * object should be created for each document to be parsed.
	 * <p>
	 * Note that this method is called automatically after a document has been
	 * successfully parsed by a Digester instance. However it is not invoked
	 * automatically when a parse fails, so when reusing a Digester instance
	 * (which is not recommended) this method <i>must</i> be called manually
	 * after a parse failure.
	 */
	void clear();

	/**
	 * Return the top object on the stack without removing it.  If there are
	 * no objects on the stack, return <code>null</code>.
	 */
	Object peek();

	/**
	 * Return the n'th object down the stack, where 0 is the top element
	 * and [getCount()-1] is the bottom element.  If the specified index
	 * is out of range, return <code>null</code>.
	 *
	 * @param n Index of the desired element, where 0 is the top of the stack,
	 *  1 is the next element down, and so on.
	 */
	Object peek(int n);

	/**
	 * Pop the top object off of the stack, and return it.  If there are
	 * no objects on the stack, return <code>null</code>.
	 */
	Object pop();

	/**
	 * Push a new object onto the top of the object stack.
	 *
	 * @param object The new object
	 */
	void push(Object object);


    /**
     * Process notification of the end of an XML element being reached.
     *
     * @param namespaceURI - The Namespace URI, or the empty string if the
     *   element has no Namespace URI or if Namespace processing is not
     *   being performed.
     * @param localName - The local name (without prefix), or the empty
     *   string if Namespace processing is not being performed.
     * @param qName - The qualified XML 1.0 name (with prefix), or the
     *   empty string if qualified names are not available.
     * @exception XMLParseException if a parsing error is to be reported
     */
	void endElement(String namespaceURI, String localName,  String qName) throws XMLParseException;

    /**
     * Set the entity resolver for this Digester.
     *
     * @param resolver The new entity resolver
     */
	void setXmlEntityResolver(XMLEntityResolver resolver);

    /**
     * Set the error handler for this Digester.
     *
     * @param errorHandler The new error handler
     */
	void setErrorHandler(XMLErrorHandler errorHandler);

	/**
     * Set the validating parser flag.  This must be called before
     * <code>parse()</code> is called the first time.
     *
     * @param validating The new validating parser flag.
     */
	 void setValidating(boolean validating);

	/**
	 * Sets a flag indicating whether the requested feature is supported by the
	 * underlying implementation of <code>org.xml.sax.XMLReader</code>. See <a
	 * href="http://www.saxproject.org">the saxproject website</a> for
	 * information about the standard SAX2 feature flags. In order to be
	 * effective, this method must be called <strong>before</strong> the
	 * <code>getParser()</code> method is called for the first time, either
	 * directly or indirectly.
	 *
	 * @param feature
	 *            Name of the feature to set the status for
	 * @param value
	 *            The new value for this feature
	 *
	 * @exception XMLParseException
	 *                if a parser configuration error occurs
	 */
	public void setFeature(String feature, boolean value)
			throws XMLParseException;

	/**
	 * Return the <code>Rules</code> implementation object containing our rules
	 * collection and associated matching policy. If none has been established,
	 * a default implementation will be created and returned.
	 */
	public IRules getRules();

	/**
	 * Set the <code>Rules</code> implementation object containing our rules
	 * collection and associated matching policy.
	 *
	 * @param rules
	 *            New Rules implementation
	 */
	public void setRules(IRules rules);

	/**
	 * Return the current rule match path
	 */
	public String getMatch();

	/**
	 * <p>
	 * Push a new object onto the top of the parameters stack.
	 * </p>
	 *
	 * <p>
	 * The parameters stack is used to store <code>CallMethodRule</code>
	 * parameters. See {@link #params}.
	 * </p>
	 *
	 * @param object
	 *            The new object
	 */
	void pushParams(Object object);

	/**
	 * <p>
	 * Pop the top object off of the parameters stack, and return it. If there
	 * are no objects on the stack, return <code>null</code>.
	 * </p>
	 *
	 * <p>
	 * The parameters stack is used to store <code>CallMethodRule</code>
	 * parameters. See {@link #params}.
	 * </p>
	 */
	Object popParams();

	/**
	 * <p>
	 * Return the top object on the parameters stack without removing it. If
	 * there are no objects on the stack, return <code>null</code>.
	 * </p>
	 *
	 * <p>
	 * The parameters stack is used to store <code>CallMethodRule</code>
	 * parameters. See {@link #params}.
	 * </p>
	 */
	Object peekParams();

	void setDelegator(IDigester delegator);
	IDigester getDelegator();
}
