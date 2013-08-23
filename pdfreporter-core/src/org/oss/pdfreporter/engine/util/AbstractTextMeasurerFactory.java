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

import org.oss.pdfreporter.engine.DefaultJasperReportsContext;
import org.oss.pdfreporter.engine.JRCommonText;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.engine.fill.JRTextMeasurer;

/**
 * Factory of {@link JdkGlyphFixTextMeasurer} instances.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JdkGlyphFixTextMeasurerFactory.java 5089 2012-03-15 12:46:09Z teodord $
 * @see JRTextMeasurerUtil#PROPERTY_TEXT_MEASURER_FACTORY
 */
public abstract class AbstractTextMeasurerFactory implements JRTextMeasurerFactory
{

	/**
	 * @deprecated Replaced by {@link #createMeasurer(JasperReportsContext, JRCommonText)}.
	 */
	public final JRTextMeasurer createMeasurer(JRCommonText text)
	{
		return createMeasurer(DefaultJasperReportsContext.getInstance(), text);
	}

}
