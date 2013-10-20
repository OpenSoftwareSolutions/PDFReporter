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
package org.oss.pdfreporter.xml.parsers;


public abstract class AbstractXmlParser implements IXmlParser {
	private final boolean validating;
	private final boolean namespaceAware;
	private final boolean xincludeAware;
	private final IInputSource input;
	private IContentHandler contentHandler;
	private XMLErrorHandler errorHandler;
	private XMLEntityResolver entityResolver;

	public AbstractXmlParser(IInputSource input, IContentHandler handler, boolean validating, boolean namespaceAware, boolean xincludeAware) throws ParserConfigurationException {
		this.input = input;
		this.contentHandler = handler;
		this.validating = validating;
		this.namespaceAware = namespaceAware;
		this.xincludeAware = xincludeAware;
	}
	
	@Override
	public boolean isNamespaceAware() {
		return namespaceAware;
	}

	@Override
	public boolean isValidating() {
		return validating;
	}

	@Override
	public boolean isXIncludeAware() {
		return xincludeAware;
	}


	@Override
	public void setEntityResolver(XMLEntityResolver entityResolver) {
		this.entityResolver = entityResolver;

	}

	@Override
	public void setErrorHandler(XMLErrorHandler errorHandler) {
		this.errorHandler = errorHandler;

	}

	@Override
	public void setContentHandler(IContentHandler contentHandler) {
		this.contentHandler = contentHandler;
	}

	protected IInputSource getInput() {
		return input;
	}

	protected IContentHandler getContentHandler() {
		return contentHandler;
	}

	protected XMLErrorHandler getErrorHandler() {
		return errorHandler;
	}

	protected XMLEntityResolver getEntityResolver() {
		return entityResolver;
	}
	
	

}
