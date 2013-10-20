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
package org.oss.pdfreporter.xml.parsers.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.exception.NotImplementedException;
import org.oss.pdfreporter.net.IURL;
import org.oss.pdfreporter.net.MalformedURLException;
import org.oss.pdfreporter.registry.IRegistry;
import org.oss.pdfreporter.uses.org.w3c.dom.DOMImplementation;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;
import org.oss.pdfreporter.xml.parsers.IDocumentBuilder;
import org.oss.pdfreporter.xml.parsers.IInputSource;
import org.oss.pdfreporter.xml.parsers.IXmlParser;
import org.oss.pdfreporter.xml.parsers.ParserConfigurationException;
import org.oss.pdfreporter.xml.parsers.XMLEntityResolver;
import org.oss.pdfreporter.xml.parsers.XMLErrorHandler;
import org.oss.pdfreporter.xml.parsers.XMLParseException;
import org.oss.pdfreporter.xml.parsers.factory.IXmlParserFactory;


/**
 * A document builder that uses a sax parser to create a document.
 * @author donatmuller, 2013, last change 1:05:00 AM
 * 
 */
public class DocumentBuilder implements IDocumentBuilder {

	private final static Logger logger = Logger.getLogger(DocumentBuilder.class.getName());
	private final boolean namespaceAware, validating, xIncludeAware;

	private XMLErrorHandler errorHandler = null;
	private XMLEntityResolver entityResolver = null;
	private IXmlParserFactory factory = null;
	
	
	DocumentBuilder(boolean namespaceAware, boolean validating,
			boolean xIncludeAware) {
		super();
		this.namespaceAware = namespaceAware;
		this.validating = validating;
		this.xIncludeAware = xIncludeAware;
	}

	@Override
	public void reset() {
		errorHandler = null;
		entityResolver = null;
		factory = null;
	}

	@Override
	public Document parse(InputStream is) throws XMLParseException, IOException {
		IInputSource input = getFactory().newInputSource(is);
		return parse(input);
	}

	@Override
	public Document parse(InputStream is, String systemId)
			throws XMLParseException, IOException {
		throw new NotImplementedException();
	}

	@Override
	public Document parse(String uri) throws XMLParseException, IOException {
		try {
			IURL url = IRegistry.getINetFactory().newURL(uri);
			IInputSource is = getFactory().newInputSource(url.openStream());
			return parse(is);
		} catch (MalformedURLException e) {
			logger.log(Level.SEVERE, "Exception creating URL for '" + uri + "'", e);
			throw new XMLParseException(e.getMessage());
		}
	}

	@Override
	public Document parse(File f) throws XMLParseException, IOException {
		IInputSource input = getFactory().newInputSource(new FileInputStream(f));
		return parse(input);
	}

	@Override
	public Document parse(IInputSource input) throws XMLParseException,
			IOException {
		Document document = newDocument();
		try {
			newParser(input, document).parse();
			return document;
		} catch (ParserConfigurationException e) {
			logger.log(Level.SEVERE, "Exception configuring XML parser", e);
			throw new XMLParseException(e);
		} finally {
			close(input);
		}
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
	public void setEntityResolver(XMLEntityResolver entityResolver) {
		this.entityResolver = entityResolver;
	}

	@Override
	public void setErrorHandler(XMLErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}

	@Override
	public Document newDocument() {
		return new DocumentImpl();
	}

	@Override
	public DOMImplementation getDOMImplementation() {
		throw new NotImplementedException();
	}

	@Override
	public boolean isXIncludeAware() {
		return xIncludeAware;
	}
	
	private IXmlParserFactory getFactory() {
		if (factory==null) {
			factory = IRegistry.getIXmlParserFactory();
			factory.setNamespaceAware(namespaceAware);
			factory.setValidating(validating);
			factory.setXIncludeAware(xIncludeAware);
			factory.configure();			
		}
		return factory;
	}
	
	private IXmlParser newParser(IInputSource input, Document document) throws ParserConfigurationException {
		IXmlParser parser = getFactory().newXmlParser(input, new SaxToDomHandler(document));
		parser.setEntityResolver(entityResolver);
		parser.setErrorHandler(errorHandler);
		return parser;
	}

	private void close(IInputSource is) {
		try {
			if (is.getByteStream()!=null) {
				is.getByteStream().close();
			}
			if (is.getCharacterStream()!=null) {
				is.getCharacterStream().close();
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Exception while closing xml stream.", e);
		}
	}
	
}
