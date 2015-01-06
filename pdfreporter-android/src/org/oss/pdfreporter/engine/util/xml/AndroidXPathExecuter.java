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

import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.uses.org.w3c.dom.Node;
import org.oss.pdfreporter.uses.org.w3c.dom.NodeList;
import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class AndroidXPathExecuter implements JRXPathExecuter {

    private XPath xPath;

	/**
	 * Default constructor.
	 */
	public AndroidXPathExecuter() {
        // XPath API facade
        final XPathFactory xpathAPI = XPathFactory.newInstance();
        xPath = xpathAPI.newXPath();
	}
	
	public NodeList selectNodeList(Node contextNode, String expression) throws JRException {

		try {
            final org.w3c.dom.Node node = XmlParserUnmarshaller.getNode(contextNode);
            final org.w3c.dom.NodeList nodeList = (org.w3c.dom.NodeList)xPath.evaluate(expression, node, XPathConstants.NODESET);
			return XmlParserUnmarshaller.getNodeList(nodeList);
		} catch (XPathExpressionException e) {
			throw new JRException("XPath selection failed. Expression: "
					+ expression, e);
		}
	}

	public Object selectObject(Node contextNode, String expression) throws JRException {
		try {
			org.w3c.dom.NodeList object =  (org.w3c.dom.NodeList) xPath.evaluate(expression, XmlParserUnmarshaller.getNode(contextNode), XPathConstants.NODESET);
            if (object != null && object.getLength() > 0) {
                return XmlParserUnmarshaller.getNode(object.item(0));
            }

			return null;
		} catch (XPathExpressionException e) {
			throw new JRException("XPath selection failed. Expression: "
					+ expression, e);
		}
	}
	
}
