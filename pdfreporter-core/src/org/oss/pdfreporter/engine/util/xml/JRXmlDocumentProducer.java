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
package org.oss.pdfreporter.engine.util.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.util.JRXmlUtils;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;
import org.oss.pdfreporter.uses.org.w3c.dom.Node;
import org.oss.pdfreporter.xml.parsers.IDocumentBuilder;
import org.oss.pdfreporter.xml.parsers.IDocumentBuilderFactory;
import org.oss.pdfreporter.xml.parsers.ParserConfigurationException;
import org.oss.pdfreporter.xml.parsers.XMLParseException;





/**
 * Produces a <code>ch.digireport.uses.org.w3c.dom.Document</code> based on a <code>java.io.File</code>, <code>java.io.InputStream</code> or a <code>java.lang.String</code> uri
 * 
 * @author Narcis Marcu (narcism@users.sourceforge.net)
 * @version $Id: JRXmlDocumentProducer.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRXmlDocumentProducer {
	
	private File file;
	
	private InputStream inputStream;
	
	private String uri;
	
	private IDocumentBuilderFactory documentBuilderFactory;
	
	
	public JRXmlDocumentProducer() {
	}

	public JRXmlDocumentProducer(File file) {
		this.file = file;
	}

	public JRXmlDocumentProducer(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public JRXmlDocumentProducer(String uri) {
		this.uri = uri;
	}
	
	public IDocumentBuilderFactory getDocumentBuilderFactory() {
		return documentBuilderFactory;
	}
	
	
	public void setDocumentBuilderFactory(IDocumentBuilderFactory documentBuilderFactory) {
		this.documentBuilderFactory = documentBuilderFactory;
	}
	
	
	public void setFile(File file) {
		this.file = file;
	}
	
	
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	
	public Document getDocument() throws JRException  {
		try {
			if (file != null) {
				return getDocumentBuilder().parse(file);
			} else if (inputStream != null ) {
				return getDocumentBuilder().parse(inputStream);
			} else if (uri != null) {
				return getDocumentBuilder().parse(uri);
			}
		} catch (XMLParseException e) {
			throw new JRException("Failed to parse the xml document", e);
		} catch (IOException e) {
			throw new JRException("Failed to parse the xml document", e);
		}
		return null;
	}
	
	
	public Document getDocument(Node sourceNode) throws JRException {
		Document doc = getDocumentBuilder().newDocument();

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
	
	
	protected IDocumentBuilder getDocumentBuilder() throws JRException {
		try{
			if (documentBuilderFactory != null) {
				return documentBuilderFactory.newDocumentBuilder();
			} else {
				return JRXmlUtils.createDocumentBuilder();
			}
		} catch (ParserConfigurationException e)
		{
			throw new JRException("Failed to create a document builder", e);
		}
	}
	
}
