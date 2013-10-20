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

public interface IXmlParser {
	
	boolean isNamespaceAware();

	boolean isValidating();

	boolean isXIncludeAware();
	
	/**
	 * Parses the configured xml source
	 * @throws XMLParseException
	 */
	void parse() throws XMLParseException;

	/**
	 * Parses the source
	 * @param source
	 * @throws XMLParseException
	 */
	void parse(IInputSource source) throws XMLParseException;

	void setEntityResolver(XMLEntityResolver er);

	void setErrorHandler(XMLErrorHandler eh);

	void setContentHandler(IContentHandler handler);
}
