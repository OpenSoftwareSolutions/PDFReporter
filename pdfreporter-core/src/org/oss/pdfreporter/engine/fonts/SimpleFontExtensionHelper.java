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
package org.oss.pdfreporter.engine.fonts;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.oss.pdfreporter.engine.DefaultJasperReportsContext;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRRuntimeException;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.registry.IRegistry;
import org.oss.pdfreporter.repo.RepositoryUtil;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;
import org.oss.pdfreporter.uses.org.w3c.dom.NamedNodeMap;
import org.oss.pdfreporter.uses.org.w3c.dom.Node;
import org.oss.pdfreporter.uses.org.w3c.dom.NodeList;
import org.oss.pdfreporter.xml.parsers.IDocumentBuilder;
import org.oss.pdfreporter.xml.parsers.IDocumentBuilderFactory;
import org.oss.pdfreporter.xml.parsers.ParserConfigurationException;
import org.oss.pdfreporter.xml.parsers.XMLErrorHandler;
import org.oss.pdfreporter.xml.parsers.XMLParseException;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: SimpleFontExtensionHelper.java 5346 2012-05-08 12:08:01Z teodord $
 */
public final class SimpleFontExtensionHelper implements XMLErrorHandler
{
	/**
	 * Default XML output encoding.
	 */
	public static final String DEFAULT_ENCODING = "UTF-8";
	

	/**
	 *
	 */
	private static final String NODE_fontFamily = "fontFamily";
	private static final String NODE_normal = "normal";
	private static final String NODE_bold = "bold";
	private static final String NODE_italic = "italic";
	private static final String NODE_boldItalic = "boldItalic";
	private static final String NODE_pdfEncoding = "pdfEncoding";
	private static final String NODE_pdfEmbedded = "pdfEmbedded";
	private static final String NODE_exportFonts = "exportFonts";
	private static final String NODE_export = "export";
	private static final String NODE_locales = "locales";
	private static final String NODE_locale = "locale";
	private static final String ATTRIBUTE_name = "name";
	private static final String ATTRIBUTE_key = "key";

	/**
	 * Thread local soft cache of instances.
	 */
	// TODO (29.04.2013, Donat, Open Software Solutions): Notice single threaded application SoftReference are static
	private static SimpleFontExtensionHelper instance = null;
	
	/**
	 * Return a cached instance.
	 * 
	 * @return a cached instance
	 */
	public static SimpleFontExtensionHelper getInstance()
	{
		if (instance == null)
		{
			instance = new SimpleFontExtensionHelper();
		}
		return instance;
	}
	
	/**
	 *
	 */
	private IDocumentBuilder documentBuilder;


	/**
	 *
	 */
	private SimpleFontExtensionHelper()
	{
		try
		{
			IDocumentBuilderFactory factory = IRegistry.getIXmlParserFactory().newDocumentBuilderFactory();
			documentBuilder = factory.newDocumentBuilder();
			documentBuilder.setErrorHandler(this);
		}
		catch (ParserConfigurationException e)
		{
			throw new JRRuntimeException(e);
		}
	}


	/**
	 * @see #loadFontFamilies(JasperReportsContext, String)
	 */
	public List<FontFamily> loadFontFamilies(String file)
	{
		return loadFontFamilies(DefaultJasperReportsContext.getInstance(), file);
	}
	
	/**
	 *
	 */
	public List<FontFamily> loadFontFamilies(JasperReportsContext jasperReportsContext, String file)
	{
		InputStream is = null; 
		
		try
		{
			is = RepositoryUtil.getInstance(jasperReportsContext).getInputStreamFromLocation(file);
			return loadFontFamilies(jasperReportsContext, is);
		}
		catch (JRException e)
		{
			throw new JRRuntimeException(e);
		}
		finally
		{
			if (is != null)
			{
				try
				{
					is.close();
				}
				catch(IOException e)
				{
				}
			}
		}
	}
	
	/**
	 *
	 */
	public List<FontFamily> loadFontFamilies(JasperReportsContext jasperReportsContext, InputStream is)
	{
		List<FontFamily> fontFamilies = null;

		try
		{
			Document document = documentBuilder.parse(IRegistry.getIXmlParserFactory().newInputSource(new InputStreamReader(is, "UTF-8")));
			fontFamilies = parseFontFamilies(jasperReportsContext, document.getDocumentElement());
		}
		catch (XMLParseException e)
		{
			throw new JRRuntimeException(e);
		}
		
		catch (IOException e)
		{
			throw new JRRuntimeException(e);
		}
		
		return fontFamilies;
	}
	
	/**
	 * @deprecated To be removed.
	 */
	public List<FontFamily> loadFontFamilies(InputStream is)
	{
		return loadFontFamilies(DefaultJasperReportsContext.getInstance(), is);
	}

	/**
	 *
	 */
	private List<FontFamily> parseFontFamilies(JasperReportsContext jasperReportsContext, Node fontFamiliesNode) throws XMLParseException
	{
		List<FontFamily> fontFamilies = new ArrayList<FontFamily>();
		
		NodeList nodeList = fontFamiliesNode.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			if (
				node.getNodeType() == Node.ELEMENT_NODE
				&& NODE_fontFamily.equals(node.getNodeName())
				)
			{
				fontFamilies.add(parseFontFamily(jasperReportsContext, node));
			}
		}
		
		return fontFamilies;
	}

	/**
	 *
	 */
	private FontFamily parseFontFamily(JasperReportsContext jasperReportsContext, Node fontFamilyNode) throws XMLParseException
	{
		SimpleFontFamily fontFamily = new SimpleFontFamily(jasperReportsContext);
		
		NamedNodeMap nodeAttrs = fontFamilyNode.getAttributes();
		
		if (nodeAttrs.getNamedItem(ATTRIBUTE_name) != null)
		{
			fontFamily.setName(nodeAttrs.getNamedItem(ATTRIBUTE_name).getNodeValue());
		}

		NodeList nodeList = fontFamilyNode.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE)
			{
				if (NODE_normal.equals(node.getNodeName()))
				{
					fontFamily.setNormal(node.getTextContent());
				}
				else if (NODE_bold.equals(node.getNodeName()))
				{
					fontFamily.setBold(node.getTextContent());
				}
				else if (NODE_italic.equals(node.getNodeName()))
				{
					fontFamily.setItalic(node.getTextContent());
				}
				else if (NODE_boldItalic.equals(node.getNodeName()))
				{
					fontFamily.setBoldItalic(node.getTextContent());
				}
				else if (NODE_pdfEncoding.equals(node.getNodeName()))
				{
					fontFamily.setPdfEncoding(node.getTextContent());
				}
				else if (NODE_pdfEmbedded.equals(node.getNodeName()))
				{
					fontFamily.setPdfEmbedded(Boolean.valueOf(node.getTextContent()));
				}
				else if (NODE_exportFonts.equals(node.getNodeName()))
				{
					fontFamily.setExportFonts(parseExportFonts(node));
				}
				else if (NODE_locales.equals(node.getNodeName()))
				{
					fontFamily.setLocales(parseLocales(node));
				}
			}
		}
		
		return fontFamily;
	}

	/**
	 *
	 */
	private Map<String,String> parseExportFonts(Node exportFontsNode) throws XMLParseException
	{
		Map<String,String> exportFonts = new HashMap<String,String>();
		
		NodeList nodeList = exportFontsNode.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			if (
				node.getNodeType() == Node.ELEMENT_NODE
				&& NODE_export.equals(node.getNodeName())
				)
			{
				NamedNodeMap nodeAttrs = node.getAttributes();
				
				if (nodeAttrs.getNamedItem(ATTRIBUTE_key) != null)
				{
					exportFonts.put(nodeAttrs.getNamedItem(ATTRIBUTE_key).getNodeValue(), node.getTextContent());
				}
			}
		}
		
		return exportFonts;
	}

	/**
	 *
	 */
	public static String getFontsXml(List<FontFamily> fontFamilies)
	{
		StringBuffer buffer = null;
		
		if(fontFamilies != null)
		{
			buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"" + DEFAULT_ENCODING + "\"?>\n");
			buffer.append("<fontFamilies>\n");
			for (FontFamily fontFamily : fontFamilies)
			{
				addFontFamily(buffer, fontFamily);
			}
			buffer.append("</fontFamilies>\n");
			return buffer.toString();
		}
		else
		{
			return null;
		}
	}

	/**
	 *
	 */
	private static void addFontFamily(StringBuffer buffer, FontFamily fontFamily) 
	{
		if(fontFamily != null)
		{
			if(fontFamily.getName() == null)
			{
				return;
			}
			
			String indent = "  ";
			buffer.append(indent + "<fontFamily name=\"" + fontFamily.getName() + "\">\n");
			indent = "    ";
			
			if(fontFamily.getNormalFace() != null)
			{
				buffer.append(indent + "<normal>" + fontFamily.getNormalFace().getFile() +"</normal>\n");
			}
			if(fontFamily.getBoldFace() != null)
			{
				buffer.append(indent + "<bold>" + fontFamily.getBoldFace().getFile() +"</bold>\n");
				
			}
			if(fontFamily.getItalicFace() != null)
			{
				buffer.append(indent + "<italic>" + fontFamily.getItalicFace().getFile() +"</italic>\n");
				
			}
			if(fontFamily.getBoldItalicFace() != null)
			{
				buffer.append(indent + "<boldItalic>" + fontFamily.getBoldItalicFace().getFile() +"</boldItalic>\n");
			}
			if(fontFamily.getPdfEncoding() != null)
			{
				buffer.append(indent + "<pdfEncoding>" + fontFamily.getPdfEncoding() +"</pdfEncoding>\n");
				
			}
			if(fontFamily.isPdfEmbedded() != null)
			{
				buffer.append(indent + "<pdfEmbedded>" + fontFamily.isPdfEmbedded() +"</pdfEmbedded>\n");
				
			}
			
			if(fontFamily instanceof SimpleFontFamily)
			{
				SimpleFontFamily simpleFontFamily = (SimpleFontFamily)fontFamily;
				
				Map<String, String> exportFonts = simpleFontFamily.getExportFonts();
				
				if(exportFonts != null)
				{
					buffer.append(indent + "<exportFonts>\n");
					indent = "      ";
					for(String key : exportFonts.keySet())
					{
						buffer.append(indent + "<export key=\"" + key +"\">" + exportFonts.get(key) + "</export>\n");
					}
					indent = "    ";
					buffer.append(indent + "</exportFonts>\n");
				}
				
				Set<String> locales = simpleFontFamily.getLocales();
				
				if(locales != null)
				{
					buffer.append(indent + "<locales>\n");
					indent = "      ";
					for(String locale : locales)
					{
						buffer.append(indent + "<locale>" + locale + "</locale>\n");
					}
					indent = "    ";
					buffer.append(indent + "</locales>\n");
				}
			}
			indent = "  ";
			buffer.append(indent + "</fontFamily>\n\n");
		}		
	}
	

	/**
	 *
	 */
	public static void writeFontsXml(
		String destFileName,
		List<FontFamily> fontFamilies
		) throws JRException
	{
		FileOutputStream fos = null;

		try
		{
			fos = new FileOutputStream(destFileName);
			writeFontsXml(fos, fontFamilies);
		}
		catch (IOException e)
		{
			throw new JRException("Error writing to file : " + destFileName, e);
		}
		finally
		{
			if (fos != null)
			{
				try
				{
					fos.close();
				}
				catch(IOException e)
				{
				}
			}
		}
	}


	/**
	 *
	 */
	public static void writeFontsXml(
		OutputStream outputStream,
		List<FontFamily> fontFamilies
		) throws JRException
	{
		Writer out = null;
		try
		{
			out = new OutputStreamWriter(outputStream, DEFAULT_ENCODING);
			out.write(getFontsXml(fontFamilies));
			out.flush();
		}
		catch (Exception e)
		{
			throw new JRException("Error writing to OutputStream : ", e);
		}
	}


	/**
	 *
	 */
	public static void writeFontExtensionsProperties(String fontsXmlLocation, String destFileName) throws JRException
	{
		FileOutputStream fos = null;

		try
		{
			fos = new FileOutputStream(destFileName, false);
			writeFontExtensionsProperties(fontsXmlLocation, fos);
		}
		catch (IOException e)
		{
			throw new JRException("Error writing to file : " + destFileName, e);
		}
		finally
		{
			if (fos != null)
			{
				try
				{
					fos.close();
				}
				catch(IOException e)
				{
				}
			}
		}
	}


	/**
	 *
	 */
	public static void writeFontExtensionsProperties(String fontFamiliesPropertyValue, OutputStream outputStream) throws JRException
	{
		writeFontExtensionsProperties(
				SimpleFontExtensionsRegistryFactory.PROPERTY_SIMPLE_FONT_FAMILIES_REGISTRY_FACTORY,
				SimpleFontExtensionsRegistryFactory.class.getName(),
				SimpleFontExtensionsRegistryFactory.SIMPLE_FONT_FAMILIES_PROPERTY_PREFIX +	"location", 
				fontFamiliesPropertyValue,
				outputStream);
	}


	/**
	 *
	 */
	public static void writeFontExtensionsProperties(
			String fontRegistryFactoryPropertyName, 
			String fontRegistryFactoryPropertyValue, 
			String fontFamiliesPropertyName, 
			String fontFamiliesPropertyValue, 
			OutputStream outputStream
			) throws JRException
	{
		Writer out = null;
		try
		{
			out = new OutputStreamWriter(outputStream, DEFAULT_ENCODING);
			out.write(
					fontRegistryFactoryPropertyName + 
					"=" + 
					fontRegistryFactoryPropertyValue + 
					"\n"
					);
			out.write(
					fontFamiliesPropertyName +
					"=" + 
					fontFamiliesPropertyValue + 
					"\n"
					);
			out.flush();
		}
		catch (Exception e)
		{
			throw new JRException("Error writing to OutputStream : ", e);
		}
		finally
		{
			if (out != null)
			{
				try
				{
					out.close();
				}
				catch(IOException e)
				{
				}
			}
		}
	}


	/**
	 *
	 */
	private Set<String> parseLocales(Node localesNode) throws XMLParseException
	{
		Set<String> locales = new HashSet<String>();
		
		NodeList nodeList = localesNode.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			if (
				node.getNodeType() == Node.ELEMENT_NODE
				&& NODE_locale.equals(node.getNodeName())
				)
			{
				locales.add(node.getTextContent());
			}
		}
		
		return locales;
	}


	public void error(XMLParseException e) {
	}

	public void fatalError(XMLParseException e) {
	}

	public void warning(XMLParseException e) {
	}

}
