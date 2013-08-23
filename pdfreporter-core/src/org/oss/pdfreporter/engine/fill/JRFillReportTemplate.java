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
package org.oss.pdfreporter.engine.fill;

import java.io.File;
import java.io.InputStream;

import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRExpression;
import org.oss.pdfreporter.engine.JRReportTemplate;
import org.oss.pdfreporter.engine.JRRuntimeException;
import org.oss.pdfreporter.engine.JRTemplate;
import org.oss.pdfreporter.engine.xml.JRXmlTemplateLoader;
import org.oss.pdfreporter.net.IURL;




/**
 * Fill-time {@link JRReportTemplate} implementation.
 * <p/>
 * Used to evaluate template source expressions.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRFillReportTemplate.java 5050 2012-03-12 10:11:26Z teodord $
 */
public class JRFillReportTemplate implements JRReportTemplate
{

	private final JRReportTemplate parent;
	private final JRBaseFiller filler;
	
	public JRFillReportTemplate(JRReportTemplate template, JRBaseFiller filler, JRFillObjectFactory factory)
	{
		factory.put(template, this);
		
		parent = template;
		this.filler = filler;
	}
	
	public JRExpression getSourceExpression()
	{
		return parent.getSourceExpression();
	}

	public JRTemplate evaluate() throws JRException
	{
		JRTemplate template;
		JRExpression sourceExpression = getSourceExpression();
		Object source = filler.evaluateExpression(sourceExpression, JRExpression.EVALUATION_DEFAULT);
		if (source == null)
		{
			template = null;
		}
		else
		{
			if (source instanceof JRTemplate)
			{
				template = (JRTemplate) source;
			}
			else
			{
				template = loadTemplate(source, filler);
			}
		}
		return template;
	}

	protected static JRTemplate loadTemplate(Object source, JRBaseFiller filler) throws JRException
	{
		JRTemplate template;
		if (filler.fillContext.hasLoadedTemplate(source))
		{
			template = filler.fillContext.getLoadedTemplate(source);
		}
		else
		{
			if (source instanceof String)
			{
				template = JRXmlTemplateLoader.getInstance(filler.getJasperReportsContext()).loadTemplate((String) source);
			}
			else if (source instanceof File)
			{
				template = JRXmlTemplateLoader.getInstance(filler.getJasperReportsContext()).loadTemplate((File) source);
			}
			else if (source instanceof IURL)
			{
				template = JRXmlTemplateLoader.getInstance(filler.getJasperReportsContext()).loadTemplate((IURL) source);
			}
			else if (source instanceof InputStream)
			{
				template = JRXmlTemplateLoader.getInstance(filler.getJasperReportsContext()).loadTemplate((InputStream) source);
			}
			else
			{
				throw new JRRuntimeException("Unknown template source class " + source.getClass().getName());
			}
			
			filler.fillContext.registerLoadedTemplate(source, template);
		}
		return template;
	}
	
}
