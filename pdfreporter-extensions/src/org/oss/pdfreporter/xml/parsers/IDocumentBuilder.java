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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.oss.pdfreporter.uses.org.w3c.dom.DOMImplementation;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;




public interface IDocumentBuilder {

	/**
	 * <p>Reset this <code>DocumentBuilder</code> to its original configuration.</p>
	 *
	 * <p><code>DocumentBuilder</code> is reset to the same state as when it was created with
	 * {@link IDocumentBuilderFactory#newDocumentBuilder()}.
	 * <code>reset()</code> is designed to allow the reuse of existing <code>DocumentBuilder</code>s
	 * thus saving resources associated with the creation of new <code>DocumentBuilder</code>s.</p>
	 *
	 * <p>The reset <code>DocumentBuilder</code> is not guaranteed to have the same {@link XMLEntityResolver} or {@link XMLErrorHandler}
	 * <code>Object</code>s, e.g. {@link Object#equals(Object obj)}.  It is guaranteed to have a functionally equal
	 * <code>XMLEntityResolver</code> and <code>ErrorHandler</code>.</p>
	 *
	 * @throws UnsupportedOperationException When implementation does not
	 *   override this method.
	 *
	 * @since 1.5
	 */
	void reset();

	/**
	 * Parse the content of the given <code>InputStream</code> as an XML
	 * document and return a new DOM {@link Document} object.
	 * An <code>IllegalArgumentException</code> is thrown if the
	 * <code>InputStream</code> is null.
	 *
	 * @param is InputStream containing the content to be parsed.
	 *
	 * @return <code>Document</code> result of parsing the
	 *  <code>InputStream</code>
	 *
	 * @throws IOException If any IO errors occur.
	 * @throws XMLParseException If any parse errors occur.
	 * @throws IllegalArgumentException When <code>is</code> is <code>null</code>
	 *
	 * @see org.xml.sax.DocumentHandler
	 */

	Document parse(InputStream is) throws XMLParseException, IOException;

	/**
	 * Parse the content of the given <code>InputStream</code> as an
	 * XML document and return a new DOM {@link Document} object.
	 * An <code>IllegalArgumentException</code> is thrown if the
	 * <code>InputStream</code> is null.
	 *
	 * @param is InputStream containing the content to be parsed.
	 * @param systemId Provide a base for resolving relative URIs.
	 *
	 * @return A new DOM Document object.
	 *
	 * @throws IOException If any IO errors occur.
	 * @throws XMLParseException If any parse errors occur.
	 * @throws IllegalArgumentException When <code>is</code> is <code>null</code>
	 * 
	 * @see org.xml.sax.DocumentHandler
	 */

	Document parse(InputStream is, String systemId) throws XMLParseException,
			IOException;

	/**
	 * Parse the content of the given URI as an XML document
	 * and return a new DOM {@link Document} object.
	 * An <code>IllegalArgumentException</code> is thrown if the
	 * URI is <code>null</code> null.
	 *
	 * @param uri The location of the content to be parsed.
	 *
	 * @return A new DOM Document object.
	 *
	 * @throws IOException If any IO errors occur.
	 * @throws XMLParseException If any parse errors occur.
	 * @throws IllegalArgumentException When <code>uri</code> is <code>null</code>
	 *
	 * @see org.xml.sax.DocumentHandler
	 */

	Document parse(String uri) throws XMLParseException, IOException;

	/**
	 * Parse the content of the given file as an XML document
	 * and return a new DOM {@link Document} object.
	 * An <code>IllegalArgumentException</code> is thrown if the
	 * <code>File</code> is <code>null</code> null.
	 *
	 * @param f The file containing the XML to parse.
	 *
	 * @throws IOException If any IO errors occur.
	 * @throws XMLParseException If any parse errors occur.
	 * @throws IllegalArgumentException When <code>f</code> is <code>null</code>
	 *
	 * @see org.xml.sax.DocumentHandler
	 * @return A new DOM Document object.
	 */

	Document parse(File f) throws XMLParseException, IOException;

	/**
	 * Parse the content of the given input source as an XML document
	 * and return a new DOM {@link Document} object.
	 * An <code>IllegalArgumentException</code> is thrown if the
	 * <code>InputSource</code> is <code>null</code> null.
	 *
	 * @param is InputSource containing the content to be parsed.
	 *
	 * @return A new DOM Document object.
	 *
	 * @throws IOException If any IO errors occur.
	 * @throws XMLParseException If any parse errors occur.
	 * @throws IllegalArgumentException When <code>is</code> is <code>null</code>
	 *
	 * @see org.xml.sax.DocumentHandler
	 */

	Document parse(IInputSource is) throws XMLParseException, IOException;

	/**
	 * Indicates whether or not this parser is configured to
	 * understand namespaces.
	 *
	 * @return true if this parser is configured to understand
	 *         namespaces; false otherwise.
	 */

	boolean isNamespaceAware();

	/**
	 * Indicates whether or not this parser is configured to
	 * validate XML documents.
	 *
	 * @return true if this parser is configured to validate
	 *         XML documents; false otherwise.
	 */

	boolean isValidating();

	/**
	 * Specify the {@link XMLEntityResolver} to be used to resolve
	 * entities present in the XML document to be parsed. Setting
	 * this to <code>null</code> will result in the underlying
	 * implementation using it's own default implementation and
	 * behavior.
	 *
	 * @param er The <code>EntityResolver</code> to be used to resolve entities
	 *           present in the XML document to be parsed.
	 */

	void setEntityResolver(XMLEntityResolver er);

	/**
	 * Specify the {@link XMLErrorHandler} to be used by the parser.
	 * Setting this to <code>null</code> will result in the underlying
	 * implementation using it's own default implementation and
	 * behavior.
	 *
	 * @param eh The <code>ErrorHandler</code> to be used by the parser.
	 */

	void setErrorHandler(XMLErrorHandler eh);

	/**
	 * Obtain a new instance of a DOM {@link Document} object
	 * to build a DOM tree with.
	 *
	 * @return A new instance of a DOM Document object.
	 */

	Document newDocument();

	/**
	 * Obtain an instance of a {@link DOMImplementation} object.
	 *
	 * @return A new instance of a <code>DOMImplementation</code>.
	 */

	DOMImplementation getDOMImplementation();

	/**
	 * <p>Get the XInclude processing mode for this parser.</p>
	 * 
	 * @return
	 *      the return value of
	 *      the {@link IDocumentBuilderFactory#isXIncludeAware()}
	 *      when this parser was created from factory.
	 * 
	 * @throws UnsupportedOperationException When implementation does not
	 *   override this method
	 * 
	 * @since 1.5
	 * 
	 * @see IDocumentBuilderFactory#setXIncludeAware(boolean)
	 */
	boolean isXIncludeAware();

}
