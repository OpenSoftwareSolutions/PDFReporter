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
package org.oss.pdfreporter.engine.xml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.oss.pdfreporter.engine.DefaultJasperReportsContext;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRFont;
import org.oss.pdfreporter.engine.JROrigin;
import org.oss.pdfreporter.engine.JRPrintElement;
import org.oss.pdfreporter.engine.JRPrintHyperlinkParameter;
import org.oss.pdfreporter.engine.JRPrintPage;
import org.oss.pdfreporter.engine.JRStyle;
import org.oss.pdfreporter.engine.JasperPrint;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.engine.TabStop;
import org.oss.pdfreporter.registry.IRegistry;
import org.oss.pdfreporter.uses.org.apache.digester.SetNestedPropertiesRule;
import org.oss.pdfreporter.uses.org.apache.digester.SetPropertiesRule;
import org.oss.pdfreporter.xml.parsers.ParserConfigurationException;
import org.oss.pdfreporter.xml.parsers.XMLErrorHandler;
import org.oss.pdfreporter.xml.parsers.XMLParseException;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRPrintXmlLoader.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRPrintXmlLoader implements XMLErrorHandler
{
	

	/**
	 *
	 */
	// TODO (29.04.2013, Donat, Digireport): Only left for compatibility
	private JasperReportsContext jasperReportsContext;
	private JasperPrint jasperPrint;
	private List<Exception> errors = new ArrayList<Exception>();


	/**
	 * @deprecated Replaced by {@link #JRPrintXmlLoader(JasperReportsContext)}.
	 */
	protected JRPrintXmlLoader()
	{
	}
	

	/**
	 *
	 */
	protected JRPrintXmlLoader(JasperReportsContext jasperReportsContext)
	{
		this.jasperReportsContext = jasperReportsContext;
	}
	

	/**
	 *
	 */
	public void setJasperPrint(JasperPrint jasperPrint)
	{
		this.jasperPrint = jasperPrint;
	}


	/**
	 *
	 */
	public static JasperPrint loadFromFile(JasperReportsContext jasperReportsContext, String sourceFileName) throws JRException
	{
		JasperPrint jasperPrint = null;

		FileInputStream fis = null;

		try
		{
			fis = new FileInputStream(sourceFileName);
			JRPrintXmlLoader printXmlLoader = new JRPrintXmlLoader(jasperReportsContext);
			jasperPrint = printXmlLoader.loadXML(fis);
		}
		catch(IOException e)
		{
			throw new JRException(e);
		}
		finally
		{
			if (fis != null)
			{
				try
				{
					fis.close();
				}
				catch(IOException e)
				{
				}
			}
		}

		return jasperPrint;
	}


	/**
	 * @see #loadFromFile(JasperReportsContext, String)
	 */
	public static JasperPrint loadFromFile(String sourceFileName) throws JRException
	{
		return loadFromFile(DefaultJasperReportsContext.getInstance(), sourceFileName);
	}


	/**
	 * @see #loadFromFile(String)
	 */
	public static JasperPrint load(String sourceFileName) throws JRException
	{
		return loadFromFile(sourceFileName);
	}


	/**
	 *
	 */
	public static JasperPrint load(JasperReportsContext jasperReportsContext, InputStream is) throws JRException
	{
		JasperPrint jasperPrint = null;

		JRPrintXmlLoader printXmlLoader = new JRPrintXmlLoader(jasperReportsContext);
		jasperPrint = printXmlLoader.loadXML(is);

		return jasperPrint;
	}


	/**
	 * @see #load(JasperReportsContext, InputStream)
	 */
	public static JasperPrint load(InputStream is) throws JRException
	{
		return load(DefaultJasperReportsContext.getInstance(), is);
	}


	/**
	 *
	 */
	private JasperPrint loadXML(InputStream is) throws JRException
	{
		try
		{
			JRXmlDigester digester = prepareDigester();

			/*   */
			digester.parse(IRegistry.getIXmlParserFactory().newInputSource(is));
		}
		catch(XMLParseException e)
		{
			throw new JRException(e);
		}
		catch(IOException e)
		{
			throw new JRException(e);
		} catch (ParserConfigurationException e) {
			throw new JRException(e);
		}
		
		if (errors.size() > 0)
		{
			Exception e = errors.get(0);
			if (e instanceof JRException)
			{
				throw (JRException)e;
			}
			throw new JRException(e);
		}

		return this.jasperPrint;
	}


	/**
	 *
	 */
	protected JRXmlDigester prepareDigester() throws XMLParseException
	{
		JRXmlDigester digester = new JRXmlDigester(IRegistry.getIDigesterFactory().newDigester());
		
		// use a classloader that resolves both JR classes and classes from the context classloader
		
		digester.setNamespaceAware(true);
		
		digester.setRuleNamespaceURI(JRXmlConstants.JASPERPRINT_NAMESPACE);
		
		digester.push(this);
		//digester.setDebug(3);
		digester.setErrorHandler(this);
		digester.setValidating(true);
		
		/*   */
		digester.addFactoryCreate("jasperPrint", JasperPrintFactory.class.getName());
		digester.addSetNext("jasperPrint", "setJasperPrint", JasperPrint.class.getName());

		/*   */
		digester.addRule("*/property", new JRPropertyDigesterRule());

		/*   */
		digester.addFactoryCreate("jasperPrint/origin", JROriginFactory.class.getName());
		digester.addSetNext("jasperPrint/origin", "addOrigin", JROrigin.class.getName());

		/*   */
		digester.addFactoryCreate("jasperPrint/reportFont", JRStyleFactory.class.getName());
		digester.addSetNext("jasperPrint/reportFont", "addStyle", JRStyle.class.getName());

		/*   */
		digester.addFactoryCreate("jasperPrint/style", JRPrintStyleFactory.class.getName());
		digester.addSetNext("jasperPrint/style", "addStyle", JRStyle.class.getName());
		
		/*   */
		digester.addFactoryCreate("*/style/pen", JRPenFactory.Style.class.getName());

		/*   */
		digester.addFactoryCreate("jasperPrint/page", JRPrintPageFactory.class.getName());
		digester.addSetNext("jasperPrint/page", "addPage", JRPrintPage.class.getName());

		/*   */
		digester.addFactoryCreate("*/line", JRPrintLineFactory.class.getName());
		digester.addSetNext("*/line", "addElement", JRPrintElement.class.getName());

		/*   */
		digester.addFactoryCreate("*/reportElement", JRPrintElementFactory.class.getName());

		/*   */
		digester.addFactoryCreate("*/graphicElement", JRPrintGraphicElementFactory.class.getName());

		/*   */
		digester.addFactoryCreate("*/pen", JRPenFactory.class.getName());

		/*   */
		digester.addFactoryCreate("*/rectangle", JRPrintRectangleFactory.class.getName());
		digester.addSetNext("*/rectangle", "addElement", JRPrintElement.class.getName());

		/*   */
		digester.addFactoryCreate("*/ellipse", JRPrintEllipseFactory.class.getName());
		digester.addSetNext("*/ellipse", "addElement", JRPrintElement.class.getName());

		/*   */
		digester.addFactoryCreate("*/image", JRPrintImageFactory.class.getName());
		digester.addSetNext("*/image", "addElement", JRPrintElement.class.getName());

		/*   */
		digester.addFactoryCreate("*/box", JRBoxFactory.class.getName());

		/*   */
		digester.addFactoryCreate("*/box/pen", JRPenFactory.Box.class.getName());
		digester.addFactoryCreate("*/box/topPen", JRPenFactory.Top.class.getName());
		digester.addFactoryCreate("*/box/leftPen", JRPenFactory.Left.class.getName());
		digester.addFactoryCreate("*/box/bottomPen", JRPenFactory.Bottom.class.getName());
		digester.addFactoryCreate("*/box/rightPen", JRPenFactory.Right.class.getName());

		/*   */
		digester.addFactoryCreate("*/paragraph", JRParagraphFactory.class.getName());
		digester.addFactoryCreate("*/paragraph/tabStop", TabStopFactory.class.getName());
		digester.addSetNext("*/paragraph/tabStop", "addTabStop", TabStop.class.getName());

		/*   */
		// TODO (28.06.2013, Donat, Digireport): Removed support for embedded images in xml (icon as byte array)		
//		digester.addFactoryCreate("*/image/imageSource", JRPrintImageSourceFactory.class.getName());
//		digester.addCallMethod("*/image/imageSource", "setImageSource", 0);

		/*   */
		digester.addFactoryCreate("*/text", JRPrintTextFactory.class.getName());
		digester.addSetNext("*/text", "addElement", JRPrintElement.class.getName());
		SetNestedPropertiesRule textRule = new SetNestedPropertiesRule(
				new String[]{"textContent", "textTruncateSuffix", "reportElement", "box", "font",
						JRXmlConstants.ELEMENT_lineBreakOffsets}, 
				new String[]{"text", "textTruncateSuffix"});
		textRule.setTrimData(false);
		textRule.setAllowUnknownChildElements(true);
		digester.addRule("*/text", textRule);

		digester.addRule("*/text/textContent", 
				new SetPropertiesRule(JRXmlConstants.ATTRIBUTE_truncateIndex, "textTruncateIndex"));
		
		/*   */
		digester.addFactoryCreate("*/text/font", JRPrintFontFactory.class.getName());
		digester.addSetNext("*/text/font", "setFont", JRFont.class.getName());
		
		digester.addRule("*/text/" + JRXmlConstants.ELEMENT_lineBreakOffsets,
				new TextLineBreakOffsetsRule());
		
		addFrameRules(digester);

		addHyperlinkParameterRules(digester);
		
		addGenericElementRules(digester);
		
		return digester;
	}




	private void addFrameRules(JRXmlDigester digester)
	{
		digester.addFactoryCreate("*/frame", JRPrintFrameFactory.class.getName());
		digester.addSetNext("*/frame", "addElement", JRPrintElement.class.getName());
	}


	protected void addHyperlinkParameterRules(JRXmlDigester digester)
	{
		String parameterPattern = "*/" + JRXmlConstants.ELEMENT_hyperlinkParameter;
		digester.addFactoryCreate(parameterPattern, JRPrintHyperlinkParameterFactory.class);
		digester.addSetNext(parameterPattern, "addHyperlinkParameter", JRPrintHyperlinkParameter.class.getName());
		
		String parameterValuePattern = parameterPattern + "/" + JRXmlConstants.ELEMENT_hyperlinkParameterValue;
		digester.addFactoryCreate(parameterValuePattern, JRPrintHyperlinkParameterValueFactory.class);
		digester.addCallMethod(parameterValuePattern, "setData", 0);
	}


	protected void addGenericElementRules(JRXmlDigester digester)
	{
		String elementPattern = "*/" + JRXmlConstants.ELEMENT_genericElement;
		digester.addFactoryCreate(elementPattern, 
				JRGenericPrintElementFactory.class);
		digester.addSetNext(elementPattern, "addElement", 
				JRPrintElement.class.getName());
		
		String elementTypePattern = elementPattern + "/" 
				+ JRXmlConstants.ELEMENT_genericElementType;
		digester.addFactoryCreate(elementTypePattern, 
				JRGenericElementTypeFactory.class);
		digester.addSetNext(elementTypePattern, "setGenericType");
		
		String elementParameterPattern = elementPattern + "/" 
				+ JRXmlConstants.ELEMENT_genericElementParameter;
		digester.addFactoryCreate(elementParameterPattern, 
				JRGenericPrintElementParameterFactory.class);
		digester.addCallMethod(elementParameterPattern, "addParameter");
		digester.addRule(elementParameterPattern, new JRGenericPrintElementParameterFactory.ArbitraryValueSetter());
		
		String elementParameterValuePattern = elementParameterPattern + "/"
				+ JRXmlConstants.ELEMENT_genericElementParameterValue;
		digester.addFactoryCreate(elementParameterValuePattern, 
				JRGenericPrintElementParameterFactory.ParameterValueFactory.class);
		digester.addCallMethod(elementParameterValuePattern, "setData", 0);
		
		addValueHandlerRules(digester, elementParameterPattern);
	}

	protected void addValueHandlerRules(JRXmlDigester digester, String elementParameterPattern)
	{
		List<XmlValueHandler> handlers = XmlValueHandlerUtils.instance().getHandlers();
		for (XmlValueHandler handler : handlers)
		{
			XmlHandlerNamespace namespace = handler.getNamespace();
			if (namespace != null)
			{
				String namespaceURI = namespace.getNamespaceURI();
				
				digester.setRuleNamespaceURI(namespaceURI);
				handler.configureDigester(digester);
				
				String schemaResource = namespace.getInternalSchemaResource();
				if (schemaResource != null)
				{
					digester.addInternalEntityResource(namespace.getPublicSchemaLocation(), 
							schemaResource);
				}
			}
		}
		
		digester.setRuleNamespaceURI(JRXmlConstants.JASPERPRINT_NAMESPACE);
	}


	/**
	 *
	 */
	public void addError(Exception e)
	{
		this.errors.add(e);
	}
	
	/**
	 *
	 */
	public void error(XMLParseException e)
	{
		this.errors.add(e);
	}
	
	/**
	 *
	 */
	public void fatalError(XMLParseException e)
	{
		this.errors.add(e);
	}
	
	/**
	 *
	 */
	public void warning(XMLParseException e)
	{
		this.errors.add(e);
	}


}
