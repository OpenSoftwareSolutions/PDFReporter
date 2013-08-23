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
package org.oss.pdfreporter.crosstabs.base;

import java.util.Iterator;
import java.util.List;

import org.oss.pdfreporter.commons.arrays.Array2D;
import org.oss.pdfreporter.commons.arrays.Array2DImpl;
import org.oss.pdfreporter.crosstabs.JRCellContents;
import org.oss.pdfreporter.crosstabs.JRCrosstab;
import org.oss.pdfreporter.crosstabs.JRCrosstabCell;
import org.oss.pdfreporter.crosstabs.JRCrosstabColumnGroup;
import org.oss.pdfreporter.crosstabs.JRCrosstabDataset;
import org.oss.pdfreporter.crosstabs.JRCrosstabGroup;
import org.oss.pdfreporter.crosstabs.JRCrosstabMeasure;
import org.oss.pdfreporter.crosstabs.JRCrosstabParameter;
import org.oss.pdfreporter.crosstabs.JRCrosstabRowGroup;
import org.oss.pdfreporter.crosstabs.design.JRDesignCrosstab;
import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.JRElement;
import org.oss.pdfreporter.engine.JRExpression;
import org.oss.pdfreporter.engine.JRExpressionCollector;
import org.oss.pdfreporter.engine.JRLineBox;
import org.oss.pdfreporter.engine.JRVariable;
import org.oss.pdfreporter.engine.JRVisitor;
import org.oss.pdfreporter.engine.base.JRBaseElement;
import org.oss.pdfreporter.engine.base.JRBaseObjectFactory;
import org.oss.pdfreporter.engine.type.ModeEnum;
import org.oss.pdfreporter.engine.type.RunDirectionEnum;
import org.oss.pdfreporter.engine.util.JRStyleResolver;
import org.oss.pdfreporter.geometry.IColor;


/**
 * Base read-only {@link org.oss.pdfreporter.crosstabs.JRCrosstab crosstab} implementation.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRBaseCrosstab.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRBaseCrosstab extends JRBaseElement implements JRCrosstab
{
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	public static final String PROPERTY_RUN_DIRECTION = "runDirection";
	
	public static final String PROPERTY_IGNORE_WIDTH = "ignoreWidth";

	protected int id;
	protected JRCrosstabParameter[] parameters;
	protected JRVariable[] variables;
	protected JRExpression parametersMapExpression;
	protected JRCrosstabDataset dataset;
	protected JRCrosstabRowGroup[] rowGroups;
	protected JRCrosstabColumnGroup[] columnGroups;
	protected JRCrosstabMeasure[] measures;
	protected int columnBreakOffset;
	protected boolean repeatColumnHeaders = true;
	protected boolean repeatRowHeaders = true;
	protected RunDirectionEnum runDirectionValue;
	protected Array2D<JRCrosstabCell> cells;
	protected JRCellContents whenNoDataCell;
	protected JRCellContents headerCell;
	protected Boolean ignoreWidth;
	protected JRLineBox lineBox;
	
	public JRBaseCrosstab(JRCrosstab crosstab, JRBaseObjectFactory factory, int id)
	{
		super(crosstab, factory);
		
		this.id = id;
		
		this.columnBreakOffset = crosstab.getColumnBreakOffset();
		this.repeatColumnHeaders = crosstab.isRepeatColumnHeaders();
		this.repeatRowHeaders = crosstab.isRepeatRowHeaders();
		this.runDirectionValue = crosstab.getRunDirectionValue();
		this.ignoreWidth = crosstab.getIgnoreWidth();
		
		this.dataset = factory.getCrosstabDataset(crosstab.getDataset());
		
		copyParameters(crosstab, factory);		
		copyVariables(crosstab, factory);		
		headerCell = factory.getCell(crosstab.getHeaderCell());
		copyRowGroups(crosstab, factory);		
		copyColumnGroups(crosstab, factory);
		copyMeasures(crosstab, factory);
		copyCells(crosstab, factory);
		
		whenNoDataCell = factory.getCell(crosstab.getWhenNoDataCell());
		lineBox = crosstab.getLineBox().clone(this);
	}

	/**
	 *
	 */
	public ModeEnum getModeValue()
	{
		return JRStyleResolver.getMode(this, ModeEnum.TRANSPARENT);
	}
	
	private void copyParameters(JRCrosstab crosstab, JRBaseObjectFactory factory)
	{
		JRCrosstabParameter[] crossParameters = crosstab.getParameters();
		if (crossParameters != null)
		{
			parameters = new JRCrosstabParameter[crossParameters.length];
			for (int i = 0; i < parameters.length; i++)
			{
				parameters[i] = factory.getCrosstabParameter(crossParameters[i]);
			}
		}
		
		parametersMapExpression = factory.getExpression(crosstab.getParametersMapExpression());
	}

	private void copyVariables(JRCrosstab crosstab, JRBaseObjectFactory factory)
	{
		JRVariable[] vars = crosstab.getVariables();
		if (vars != null)
		{
			variables = new JRVariable[vars.length];
			for (int i = 0; i < vars.length; i++)
			{
				variables[i] = factory.getVariable(vars[i]);
			}
		}
	}

	private void copyRowGroups(JRCrosstab crosstab, JRBaseObjectFactory factory)
	{
		JRCrosstabRowGroup[] crossRowGroups = crosstab.getRowGroups();
		if (crossRowGroups != null)
		{
			this.rowGroups = new JRCrosstabRowGroup[crossRowGroups.length];
			for (int i = 0; i < crossRowGroups.length; ++i)
			{
				this.rowGroups[i] = factory.getCrosstabRowGroup(crossRowGroups[i]);
			}
		}
	}

	private void copyColumnGroups(JRCrosstab crosstab, JRBaseObjectFactory factory)
	{
		JRCrosstabColumnGroup[] crossColumnGroups = crosstab.getColumnGroups();
		if (crossColumnGroups != null)
		{
			this.columnGroups = new JRCrosstabColumnGroup[crossColumnGroups.length];
			for (int i = 0; i < crossColumnGroups.length; ++i)
			{
				this.columnGroups[i] = factory.getCrosstabColumnGroup(crossColumnGroups[i]);
			}
		}
	}

	private void copyMeasures(JRCrosstab crosstab, JRBaseObjectFactory factory)
	{
		JRCrosstabMeasure[] crossMeasures = crosstab.getMeasures();
		if (crossMeasures != null)
		{
			this.measures = new JRCrosstabMeasure[crossMeasures.length];
			for (int i = 0; i < crossMeasures.length; ++i)
			{
				this.measures[i] = factory.getCrosstabMeasure(crossMeasures[i]);
			}
		}
	}

	private void copyCells(JRCrosstab crosstab, JRBaseObjectFactory factory)
	{
		Array2D<? extends JRCrosstabCell> crossCells = crosstab.getCells();
		if (crossCells != null)
		{
			this.cells = new Array2DImpl<JRCrosstabCell>(rowGroups.length + 1, columnGroups.length + 1);
			for (int i = 0; i <= rowGroups.length; i++)
			{
				for (int j = 0; j <= columnGroups.length; ++j)
				{
					this.cells.set(i, j, factory.getCrosstabCell(crossCells.get(i, j)));
				}
			}
		}
	}
	
	public int getId()
	{
		return id;
	}

	public JRCrosstabDataset getDataset()
	{
		return dataset;
	}

	public JRCrosstabRowGroup[] getRowGroups()
	{
		return rowGroups;
	}

	public JRCrosstabColumnGroup[] getColumnGroups()
	{
		return columnGroups;
	}

	public JRCrosstabMeasure[] getMeasures()
	{
		return measures;
	}

	public void collectExpressions(JRExpressionCollector collector)
	{
		collector.collect(this);
	}

	public void visit(JRVisitor visitor)
	{
		visitor.visitCrosstab(this);
	}

	public int getColumnBreakOffset()
	{
		return columnBreakOffset;
	}

	public boolean isRepeatColumnHeaders()
	{
		return repeatColumnHeaders;
	}

	public boolean isRepeatRowHeaders()
	{
		return repeatRowHeaders;
	}

	public Array2D<? extends JRCrosstabCell> getCells()
	{
		return cells;
	}

	public JRCrosstabParameter[] getParameters()
	{
		return parameters;
	}

	public JRExpression getParametersMapExpression()
	{
		return parametersMapExpression;
	}

	public JRCellContents getWhenNoDataCell()
	{
		return whenNoDataCell;
	}
	
	public static JRElement getElementByKey(JRCrosstab crosstab, String key)
	{
		JRElement element = null;
		
		if (crosstab.getHeaderCell() != null)
		{
			element = crosstab.getHeaderCell().getElementByKey(key);
		}
		
		if (element == null)
		{
			element = getHeadersElement(crosstab.getRowGroups(), key);
		}		

		if (element == null)
		{
			element = getHeadersElement(crosstab.getColumnGroups(), key);
		}
		
		if (element == null)
		{
			if (crosstab instanceof JRDesignCrosstab)
			{
				List<JRCrosstabCell> cellsList = ((JRDesignCrosstab) crosstab).getCellsList();
				for (Iterator<JRCrosstabCell> it = cellsList.iterator(); element == null && it.hasNext();)
				{
					JRCrosstabCell cell = it.next();
					element = cell.getContents().getElementByKey(key);
				}
			}
			else
			{
				Array2D<? extends JRCrosstabCell> cells = crosstab.getCells();
				for (int i = cells.getLengthI()- 1; element == null && i >= 0; --i)
				{
					for (int j = cells.getLengthJ() - 1; element == null && j >= 0; --j)
					{
						JRCrosstabCell cell = cells.get(i, j);
						if (cell != null)
						{
							element = cell.getContents().getElementByKey(key);
						}
					}
				}
			}
		}
		
		if (element == null && crosstab.getWhenNoDataCell() != null)
		{
			element = crosstab.getWhenNoDataCell().getElementByKey(key);
		}
		
		return element;
	}

	private static JRElement getHeadersElement(JRCrosstabGroup[] groups, String key)
	{
		JRElement element = null;
		
		if (groups != null)
		{
			for (int i = 0; element == null && i < groups.length; i++)
			{
				JRCellContents header = groups[i].getHeader();
				element = header.getElementByKey(key);
				
				if (element == null)
				{
					JRCellContents totalHeader = groups[i].getTotalHeader();
					element = totalHeader.getElementByKey(key);
				}
			}
		}
		
		return element;
	}

	
	public JRElement getElementByKey(String elementKey)
	{
		return getElementByKey(this, elementKey);
	}

	public JRCellContents getHeaderCell()
	{
		return headerCell;
	}

	public JRVariable[] getVariables()
	{
		return variables;
	}

	
	/**
	 *
	 */
	public RunDirectionEnum getRunDirectionValue()
	{
		return this.runDirectionValue;
	}

	/**
	 *
	 */
	public void setRunDirection(RunDirectionEnum runDirectionValue)
	{
		RunDirectionEnum old = this.runDirectionValue;
		this.runDirectionValue = runDirectionValue;
		getEventSupport().firePropertyChange(PROPERTY_RUN_DIRECTION, old, this.runDirectionValue);
	}

	/**
	 * 
	 */
	public Object clone() 
	{
		throw new UnsupportedOperationException();//FIXMECLONE: implement this
	}

	public Boolean getIgnoreWidth()
	{
		return ignoreWidth;
	}

	public void setIgnoreWidth(Boolean ignoreWidth)
	{
		Object old = this.ignoreWidth;
		this.ignoreWidth = ignoreWidth;
		getEventSupport().firePropertyChange(PROPERTY_IGNORE_WIDTH, 
				old, this.ignoreWidth);
	}

	public void setIgnoreWidth(boolean ignoreWidth)
	{
		setIgnoreWidth(Boolean.valueOf(ignoreWidth));
	}
	
	public IColor getDefaultLineColor()
	{
		return getForecolor();
	}

	public JRLineBox getLineBox()
	{
		return lineBox;
	}

	// TODO: Daniel (19.4.2013) - Removed, unused
//	/*
//	 * These fields are only for serialization backward compatibility.
//	 */
//	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID; //NOPMD
//	/**
//	 * @deprecated
//	 */
//	private byte runDirection;
//	
//	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
//	{
//		in.defaultReadObject();
//
//		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2)
//		{
//			runDirectionValue = RunDirectionEnum.getByValue(runDirection);
//		}
//		
//		if (lineBox == null)
//		{
//			lineBox = new JRBaseLineBox(this);
//		}
//	}


}
