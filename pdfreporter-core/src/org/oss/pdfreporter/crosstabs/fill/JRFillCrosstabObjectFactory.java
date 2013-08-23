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
package org.oss.pdfreporter.crosstabs.fill;

import org.oss.pdfreporter.crosstabs.JRCellContents;
import org.oss.pdfreporter.crosstabs.JRCrosstabCell;
import org.oss.pdfreporter.crosstabs.JRCrosstabColumnGroup;
import org.oss.pdfreporter.crosstabs.JRCrosstabMeasure;
import org.oss.pdfreporter.crosstabs.JRCrosstabRowGroup;
import org.oss.pdfreporter.engine.fill.JRFillCellContents;
import org.oss.pdfreporter.engine.fill.JRFillExpressionEvaluator;
import org.oss.pdfreporter.engine.fill.JRFillObjectFactory;
import org.oss.pdfreporter.engine.fill.JROriginProvider;

/**
 * Factory of fill instances of crosstab internal objects.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRFillCrosstabObjectFactory.java 4595 2011-09-08 15:55:10Z teodord $
 */
public class JRFillCrosstabObjectFactory extends JRFillObjectFactory
{

	private JROriginProvider parentOriginProvider;

	public JRFillCrosstabObjectFactory(JRFillObjectFactory parent,
			JRFillExpressionEvaluator expressionEvaluator)
	{
		super(parent, expressionEvaluator);
	}
	
	public JROriginProvider getParentOriginProvider()
	{
		return parentOriginProvider;
	}

	public void setParentOriginProvider(JROriginProvider parentOriginProvider)
	{
		this.parentOriginProvider = parentOriginProvider;
	}

	public JRFillCellContents getCell(JRCellContents cell, String cellType)
	{
		JRFillCellContents fillCell = null;

		if (cell != null)
		{
			fillCell = (JRFillCellContents) get(cell);
			if (fillCell == null)
			{
				fillCell = new JRFillCellContents(filler, cell, cellType, this);
			}
		}

		return fillCell;
	}

	public JRFillCrosstabRowGroup getCrosstabRowGroup(JRCrosstabRowGroup group)
	{
		JRFillCrosstabRowGroup fillGroup = null;

		if (group != null)
		{
			fillGroup = (JRFillCrosstabRowGroup) get(group);
			if (fillGroup == null)
			{
				fillGroup = new JRFillCrosstabRowGroup(group, this);
			}
		}

		return fillGroup;
	}

	public JRFillCrosstabColumnGroup getCrosstabColumnGroup(JRCrosstabColumnGroup group)
	{
		JRFillCrosstabColumnGroup fillGroup = null;

		if (group != null)
		{
			fillGroup = (JRFillCrosstabColumnGroup) get(group);
			if (fillGroup == null)
			{
				fillGroup = new JRFillCrosstabColumnGroup(group, this);
			}
		}

		return fillGroup;
	}

	public JRFillCrosstabCell getCrosstabCell(JRCrosstabCell cell)
	{
		JRFillCrosstabCell fillCell = null;

		if (cell != null)
		{
			fillCell = (JRFillCrosstabCell) get(cell);
			if (fillCell == null)
			{
				fillCell = new JRFillCrosstabCell(cell, this);
			}
		}

		return fillCell;
	}

	public JRFillCrosstabMeasure getCrosstabMeasure(JRCrosstabMeasure measure)
	{
		JRFillCrosstabMeasure fillMeasure = null;

		if (measure != null)
		{
			fillMeasure = (JRFillCrosstabMeasure) get(measure);
			if (fillMeasure == null)
			{
				fillMeasure = new JRFillCrosstabMeasure(measure, this);
			}
		}

		return fillMeasure;
	}

}
