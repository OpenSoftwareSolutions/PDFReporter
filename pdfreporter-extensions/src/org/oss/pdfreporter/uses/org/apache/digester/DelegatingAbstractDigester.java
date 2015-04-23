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


public abstract class DelegatingAbstractDigester implements IDigester {


	private final IDigester delegate;

	public DelegatingAbstractDigester(IDigester delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	public void addSetProperties(String pattern) {
		delegate.addSetProperties(pattern);
	}

	@Override
	public void addSetProperties(String pattern, String[] attributeNames, String[] propertyNames) {
		delegate.addSetProperties(pattern, attributeNames, propertyNames);

	}

	@Override
	public IRules getRules() {
		return delegate.getRules();
	}

	@Override
	public void setRules(IRules rules) {
		delegate.setRules(rules);
	}

	@Override
	public String getMatch() {
		return delegate.getMatch();
	}

	@Override
	public int getCount() {
		return delegate.getCount();
	}


	@Override
	public void setNamespaceAware(boolean namespaceAware) {
		delegate.setNamespaceAware(namespaceAware);
	}


	@Override
	public void setRuleNamespaceURI(String ruleNamespaceURI) {
		delegate.setRuleNamespaceURI(ruleNamespaceURI);
	}


	@Override
	public Object parse(IInputSource input) throws IOException,
			XMLParseException, ParserConfigurationException {
		return delegate.parse(input);
	}

	@Override
	public void setXmlEntityResolver(XMLEntityResolver resolver) {
		delegate.setXmlEntityResolver(resolver);
	}

	@Override
	public void addRule(String pattern, IRule rule) {
		delegate.addRule(pattern, rule);
	}


	@Override
	public void addRuleSet(IRuleSet ruleSet) {
		delegate.addRuleSet(ruleSet);
	}


	@Override
	public void addCallMethod(String pattern, String methodName, int paramCount) {
		delegate.addCallMethod(pattern, methodName, paramCount);
	}


	@Override
	public void addCallParam(String pattern, int paramIndex,
			String attributeName) {
		delegate.addCallParam(pattern, paramIndex, attributeName);
	}


	@Override
	public void addFactoryCreate(String pattern, String className) {
		delegate.addFactoryCreate(pattern, className);
	}


	@Override
	public void addFactoryCreate(String pattern, Class<?> clazz) {
		delegate.addFactoryCreate(pattern, clazz);
	}


	@Override
	public void addObjectCreate(String pattern, Class<?> clazz) {
		delegate.addObjectCreate(pattern, clazz);
	}


	@Override
	public void addSetNext(String pattern, String methodName, String paramType) {
		delegate.addSetNext(pattern, methodName, paramType);
	}


	@Override
	public void clear() {
		delegate.clear();
	}


	@Override
	public Object peek() {
		return delegate.peek();
	}


	@Override
	public Object peek(int n) {
		return delegate.peek(n);
	}


	@Override
	public void push(Object object) {
		delegate.push(object);
	}


	@Override
	public void addFactoryCreate(String pattern,
			IObjectCreationFactory creationFactory) {
		delegate.addFactoryCreate(pattern, creationFactory);
	}


	@Override
	public void setErrorHandler(XMLErrorHandler errorHandler) {
		delegate.setErrorHandler(errorHandler);
	}


	@Override
	public void setValidating(boolean validating) {
		delegate.setValidating(validating);
	}


	@Override
	public void addSetNext(String pattern, String methodName) {
		delegate.addSetNext(pattern, methodName);
	}


	@Override
	public void addCallMethod(String pattern, String methodName) {
		delegate.addCallMethod(pattern, methodName);
	}


	@Override
	public void setFeature(String feature, boolean value) throws XMLParseException {
		delegate.setFeature(feature, value);
	}


	@Override
	public void pushParams(Object object) {
		delegate.pushParams(object);
	}


	@Override
	public Object popParams() {
		return delegate.popParams();
	}


	@Override
	public Object peekParams() {
		return delegate.peekParams();
	}
}
