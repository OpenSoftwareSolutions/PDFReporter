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

import org.oss.pdfreporter.engine.JRCommonText;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.engine.util.AbstractTextMeasurerFactory;

/**
 * Default text measurer factory.
 * 
 * This factory produces {@link TextMeasurer} instances. 
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: TextMeasurerFactory.java 5311 2012-04-26 16:14:49Z teodord $
 */
public class TextMeasurerFactory extends AbstractTextMeasurerFactory
{

	/**
	 * Returns a {@link TextMeasurer} instance for the text object.
	 */
	public JRTextMeasurer createMeasurer(JasperReportsContext jasperReportsContext, JRCommonText text)
	{
		return new TextMeasurer(jasperReportsContext, text);
	}

}
