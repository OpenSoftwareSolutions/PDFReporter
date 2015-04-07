/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.xml.parsers.factory;

import java.io.InputStream;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;

import org.oss.pdfreporter.engine.DefaultJasperReportsContext;
import org.oss.pdfreporter.engine.JRPropertiesUtil;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.registry.IRegistry;
import org.oss.pdfreporter.xml.parsers.IContentHandler;
import org.oss.pdfreporter.xml.parsers.IDocumentBuilderFactory;
import org.oss.pdfreporter.xml.parsers.IInputSource;
import org.oss.pdfreporter.xml.parsers.IXmlParser;
import org.oss.pdfreporter.xml.parsers.InputSource;
import org.oss.pdfreporter.xml.parsers.ParserConfigurationException;
import org.oss.pdfreporter.xml.parsers.XmlParser;
import org.oss.pdfreporter.xml.parsers.factory.IXmlParserFactory;
import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;


/**
 * IXmlParserFactory implementation based on SAXParser and DocumentBuilderFactory from JDK.<br>
 * Implements all features with wrappers.
 * @author donatmuller, 2013, last change 11:28:43 PM
 * 
 */
public class XmlParserFactory implements IXmlParserFactory {
	
	private SAXParserFactory xmlParserFactory= null;
	private DocumentBuilderFactory documentBuilderFactory = null;
	private boolean validating = false;
	private boolean namespaceAware = false;
	private boolean xincludeAware = false;
	
	public static void registerFactory() {
		IRegistry.register(new XmlParserFactory());
	}

	private XmlParserFactory() {
		// not intended to create
	}
	
	@Override
	public void setNamespaceAware(boolean aware) {
		this.namespaceAware = aware;
	}

	@Override
	public void setXIncludeAware(boolean aware) {
		this.xincludeAware = aware;
	}

	@Override
	public void setValidating(boolean validating) {
		this.validating = validating;
	}

	@Override
	public void configure() {
		xmlParserFactory= null;
		documentBuilderFactory = null;
	}

	@Override
	public IInputSource newInputSource(InputStream is) {
		return new InputSource(is);
	}

	@Override
	public IInputSource newInputSource(Reader r) {
		return new InputSource(r);
	}	
	
	@Override
	public IDocumentBuilderFactory newDocumentBuilderFactory() {
		return XmlParserUnmarshaller.getDocumentBuilderFactory(getDocumentBuilderFactory());
	}

	@Override
	public IXmlParser newXmlParser(IInputSource input, IContentHandler handeler) throws ParserConfigurationException {
		try {
			return new XmlParser(getXmlParserFactory().newSAXParser(),input,handeler);
		} catch (Exception e) {
			throw new ParserConfigurationException(e);
		}
	}
	
    private SAXParserFactory getXmlParserFactory() {
        if (xmlParserFactory == null) {
        	xmlParserFactory = SAXParserFactory.newInstance();
        	xmlParserFactory.setNamespaceAware(namespaceAware);
        	// setting XIncludeAwara will throw an unsupported exception on Android
        	if (JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance()).getBooleanProperty(JasperReportsContext.COMPILER_XML_XINCLUDEAWARE)) {
            	xmlParserFactory.setXIncludeAware(xincludeAware);
        	};
        	xmlParserFactory.setValidating(validating);
        }
        return (xmlParserFactory);
    }
    
    private DocumentBuilderFactory getDocumentBuilderFactory() {
    	if (documentBuilderFactory==null) {
    		documentBuilderFactory = DocumentBuilderFactory.newInstance();
    		documentBuilderFactory.setNamespaceAware(namespaceAware);
    		if (JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance()).getBooleanProperty(JasperReportsContext.COMPILER_XML_XINCLUDEAWARE)) {
        		documentBuilderFactory.setXIncludeAware(xincludeAware);	
    		}
    		documentBuilderFactory.setValidating(validating);
    	}
    	return (documentBuilderFactory);
    }



}
