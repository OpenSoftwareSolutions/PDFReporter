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
package org.oss.pdfreporter.xml.parsers.factory;

import java.io.InputStream;
import java.io.Reader;

import org.oss.pdfreporter.xml.parsers.IContentHandler;
import org.oss.pdfreporter.xml.parsers.IDocumentBuilderFactory;
import org.oss.pdfreporter.xml.parsers.IInputSource;
import org.oss.pdfreporter.xml.parsers.IXmlParser;
import org.oss.pdfreporter.xml.parsers.ParserConfigurationException;


/**
 * Factory to to create a platform independent XMLParser and document builder factory.
 * TODO (10.05.2013, Open Software Solutions, Donat) implement an xml schema abstraction to configure from outside (we are only using DTDs for now).
 * @author donatmuller, 2013, last change 10:58:22 PM
 * 
 */
public interface IXmlParserFactory {
	/**
	 * @param aware
	 */
	void setNamespaceAware(boolean aware);
	
	/**
	 * @param aware
	 */
	void setXIncludeAware(boolean aware);
	
	/**
	 * Indicates whether or not this parser is configured to
	 * validate XML documents.
	 * @param validating
	 */
	void setValidating(boolean validating);
	
	/**
	 * Forces the factory to reconfigure according to the settings.
	 */
	void configure();
	
	/**
	 * Creates a document builder factory.
	 * @return
	 */
	IDocumentBuilderFactory newDocumentBuilderFactory();
	
	/**
	 * Creates an input source from a input stream.
	 * @param is
	 * @return
	 */
	IInputSource newInputSource(InputStream is);
	
	/**
	 * Creates an input source from a reader.
	 * @param reader
	 * @return
	 */
	IInputSource newInputSource(Reader reader);
	
	/**
	 * Creates an xml parser for a source with a content handler to listen to events while parsing.
	 * @param source
	 * @param handler
	 * @return
	 * @throws ParserConfigurationException
	 */
	IXmlParser newXmlParser(IInputSource source, IContentHandler handler) throws ParserConfigurationException;	
}
