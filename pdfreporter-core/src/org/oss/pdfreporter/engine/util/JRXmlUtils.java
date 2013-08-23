/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package org.oss.pdfreporter.engine.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.registry.IRegistry;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;
import org.oss.pdfreporter.uses.org.w3c.dom.Node;
import org.oss.pdfreporter.xml.parsers.IDocumentBuilder;
import org.oss.pdfreporter.xml.parsers.IDocumentBuilderFactory;
import org.oss.pdfreporter.xml.parsers.IInputSource;
import org.oss.pdfreporter.xml.parsers.ParserConfigurationException;
import org.oss.pdfreporter.xml.parsers.XMLParseException;


/**
 * XML parsing utilities.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRXmlUtils.java 5497 2012-07-17 14:31:39Z narcism $
 */
public final class JRXmlUtils
{
	public static Document parse(IInputSource is) throws JRException
	{
		return parse(is, false);
	}


	/**
	 * Parses an input source into a document.
	 * 
	 * @param is the input source
	 * @return the parsed document
	 * @throws JRException
	 */
	public static Document parse(IInputSource is, boolean isNamespaceAware) throws JRException
	{
		try
		{
			return createDocumentBuilder(isNamespaceAware).parse(is);
		}
		catch (XMLParseException e)
		{
			throw new JRException("Failed to parse the xml document", e);
		}
		catch (IOException e)
		{
			throw new JRException("Failed to parse the xml document", e);
		}
	}
	
	// TODO (26.04.2013, Donat, Digireport): Not supported feature: static Document parse(String uri,  boolean isNamespaceAware) throws JRException
	

	
	public static Document parse(File file) throws JRException
	{
		return parse(file, false);
	}


	/**
	 * Parses a file into a document.
	 * 
	 * @param file the XML file
	 * @return the document
	 * @throws JRException
	 */
	public static Document parse(File file,  boolean isNamespaceAware) throws JRException
	{
		try
		{
			return createDocumentBuilder(isNamespaceAware).parse(file);
		}
		catch (XMLParseException e)
		{
			throw new JRException("Failed to parse the xml document", e);
		}
		catch (IOException e)
		{
			throw new JRException("Failed to parse the xml document", e);
		}
	}


	public static Document parse(InputStream is) throws JRException
	{
		return parse(is, false);
	}


	/**
	 * Parses an input stream into a XML document.
	 * 
	 * @param is the input stream
	 * @return the document
	 * @throws JRException
	 */
	public static Document parse(InputStream is, boolean isNamespaceAware) throws JRException
	{
		return parse(IRegistry.getIXmlParserFactory().newInputSource(is), isNamespaceAware);
	}


	// TODO (26.04.2013, Donat, Digireport): Not supported feature: static Document parse(IURL url, boolean isNamespaceAware) throws JRException


	public static IDocumentBuilder createDocumentBuilder() throws JRException
	{
		return createDocumentBuilder(false);
	}


	/**
	 * Creates a XML document builder.
	 * 
	 * @return a XML document builder
	 * @throws JRException
	 */
	public static IDocumentBuilder createDocumentBuilder(boolean isNamespaceAware) throws JRException
	{
		IDocumentBuilderFactory dbf = IRegistry.getIXmlParserFactory().newDocumentBuilderFactory();
		dbf.setValidating(false);
		dbf.setIgnoringComments(true);
		dbf.setNamespaceAware(isNamespaceAware);
		try
		{
			return dbf.newDocumentBuilder();
		}
		catch (ParserConfigurationException e)
		{
			throw new JRException("Failed to create a document builder factory", e);
		}
	}

	
	public static Document createDocument(Node sourceNode) throws JRException
	{
		return createDocument(sourceNode, false);
	}


	/**
	 * Creates a document having a node as root.
	 * 
	 * @param sourceNode the node
	 * @return a document having the specified node as root
	 * @throws JRException
	 */
	public static Document createDocument(Node sourceNode, boolean isNamespaceAware) throws JRException
	{
		Document doc = JRXmlUtils.createDocumentBuilder(isNamespaceAware).newDocument();
		Node source;
		if (sourceNode.getNodeType() == Node.DOCUMENT_NODE) {
			source = ((Document) sourceNode).getDocumentElement();
		} else {
			source = sourceNode;
		}

		Node node = doc.importNode(source, true);
		doc.appendChild(node);
		
		return doc;
	}
	
	
	private JRXmlUtils()
	{
	}
}
