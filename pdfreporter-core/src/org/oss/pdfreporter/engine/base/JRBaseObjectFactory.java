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
package org.oss.pdfreporter.engine.base;

import org.oss.pdfreporter.crosstabs.JRCellContents;
import org.oss.pdfreporter.crosstabs.JRCrosstab;
import org.oss.pdfreporter.crosstabs.JRCrosstabBucket;
import org.oss.pdfreporter.crosstabs.JRCrosstabCell;
import org.oss.pdfreporter.crosstabs.JRCrosstabColumnGroup;
import org.oss.pdfreporter.crosstabs.JRCrosstabDataset;
import org.oss.pdfreporter.crosstabs.JRCrosstabMeasure;
import org.oss.pdfreporter.crosstabs.JRCrosstabParameter;
import org.oss.pdfreporter.crosstabs.JRCrosstabRowGroup;
import org.oss.pdfreporter.crosstabs.base.JRBaseCellContents;
import org.oss.pdfreporter.crosstabs.base.JRBaseCrosstab;
import org.oss.pdfreporter.crosstabs.base.JRBaseCrosstabBucket;
import org.oss.pdfreporter.crosstabs.base.JRBaseCrosstabCell;
import org.oss.pdfreporter.crosstabs.base.JRBaseCrosstabColumnGroup;
import org.oss.pdfreporter.crosstabs.base.JRBaseCrosstabDataset;
import org.oss.pdfreporter.crosstabs.base.JRBaseCrosstabMeasure;
import org.oss.pdfreporter.crosstabs.base.JRBaseCrosstabParameter;
import org.oss.pdfreporter.crosstabs.base.JRBaseCrosstabRowGroup;
import org.oss.pdfreporter.engine.JRAbstractObjectFactory;
import org.oss.pdfreporter.engine.JRBand;
import org.oss.pdfreporter.engine.JRBreak;
import org.oss.pdfreporter.engine.JRComponentElement;
import org.oss.pdfreporter.engine.JRConditionalStyle;
import org.oss.pdfreporter.engine.JRDataset;
import org.oss.pdfreporter.engine.JRDatasetParameter;
import org.oss.pdfreporter.engine.JRDatasetRun;
import org.oss.pdfreporter.engine.JRDefaultStyleProvider;
import org.oss.pdfreporter.engine.JRElementGroup;
import org.oss.pdfreporter.engine.JREllipse;
import org.oss.pdfreporter.engine.JRExpression;
import org.oss.pdfreporter.engine.JRExpressionChunk;
import org.oss.pdfreporter.engine.JRExpressionCollector;
import org.oss.pdfreporter.engine.JRField;
import org.oss.pdfreporter.engine.JRFrame;
import org.oss.pdfreporter.engine.JRGenericElement;
import org.oss.pdfreporter.engine.JRGenericElementParameter;
import org.oss.pdfreporter.engine.JRGroup;
import org.oss.pdfreporter.engine.JRHyperlink;
import org.oss.pdfreporter.engine.JRHyperlinkParameter;
import org.oss.pdfreporter.engine.JRImage;
import org.oss.pdfreporter.engine.JRLine;
import org.oss.pdfreporter.engine.JRParameter;
import org.oss.pdfreporter.engine.JRPropertyExpression;
import org.oss.pdfreporter.engine.JRQuery;
import org.oss.pdfreporter.engine.JRQueryChunk;
import org.oss.pdfreporter.engine.JRRectangle;
import org.oss.pdfreporter.engine.JRReportTemplate;
import org.oss.pdfreporter.engine.JRRuntimeException;
import org.oss.pdfreporter.engine.JRScriptlet;
import org.oss.pdfreporter.engine.JRSection;
import org.oss.pdfreporter.engine.JRSortField;
import org.oss.pdfreporter.engine.JRStaticText;
import org.oss.pdfreporter.engine.JRStyle;
import org.oss.pdfreporter.engine.JRStyleContainer;
import org.oss.pdfreporter.engine.JRStyleSetter;
import org.oss.pdfreporter.engine.JRSubreport;
import org.oss.pdfreporter.engine.JRSubreportParameter;
import org.oss.pdfreporter.engine.JRSubreportReturnValue;
import org.oss.pdfreporter.engine.JRTextField;
import org.oss.pdfreporter.engine.JRVariable;


/**
 * Factory of objects used in compiled reports.
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRBaseObjectFactory.java 5726 2012-10-16 15:35:56Z lucianc $
 */
public class JRBaseObjectFactory extends JRAbstractObjectFactory
{


	/**
	 *
	 */
	private JRDefaultStyleProvider defaultStyleProvider;

	/**
	 * Expression collector used to retrieve generated expression IDs.
	 */
	private JRExpressionCollector expressionCollector;
	

	/**
	 *
	 */
	protected JRBaseObjectFactory(JRDefaultStyleProvider defaultStyleProvider)
	{
		this.defaultStyleProvider = defaultStyleProvider;
	}


	/**
	 * Constructs a base object factory.
	 *
	 * @param defaultStyleProvider the default style provider
	 * @param expressionCollector the expression collector used as expression ID provider
	 */
	protected JRBaseObjectFactory(JRDefaultStyleProvider defaultStyleProvider, JRExpressionCollector expressionCollector)
	{
		this.defaultStyleProvider = defaultStyleProvider;
		this.expressionCollector = expressionCollector;
	}

	protected JRBaseObjectFactory(JRExpressionCollector expressionCollector)
	{
		this.expressionCollector = expressionCollector;
	}
	
	public void setDefaultStyleProvider(JRDefaultStyleProvider defaultStyleProvider)
	{
		this.defaultStyleProvider = defaultStyleProvider;
	}

	/**
	 *
	 */
	public JRDefaultStyleProvider getDefaultStyleProvider()
	{
		return defaultStyleProvider;
	}


	/**
	 *
	 */
	public JRStyle getStyle(JRStyle style)
	{
		JRBaseStyle baseStyle = null;

		if (style != null)
		{
			baseStyle = (JRBaseStyle)get(style);
			if (baseStyle == null)
			{
				baseStyle = new JRBaseStyle(style, this);
				put(style, baseStyle);
			}
		}

		return baseStyle;
	}


	/**
	 * This method preserves both specified styles and external style name references.
	 *
	 * @see JRAbstractObjectFactory#setStyle(JRStyleSetter, JRStyleContainer)
	 */
	public void setStyle(JRStyleSetter setter, JRStyleContainer styleContainer)
	{
		JRStyle style = styleContainer.getStyle();
		String nameReference = styleContainer.getStyleNameReference();
		if (style != null)
		{
			JRStyle newStyle = getStyle(style);
			setter.setStyle(newStyle);
		}
		else if (nameReference != null)
		{
			handleStyleNameReference(setter, nameReference);
		}
	}

	protected void handleStyleNameReference(JRStyleSetter setter, String nameReference)
	{
		setter.setStyleNameReference(nameReference);
	}


	/**
	 *
	 */
	protected JRBaseScriptlet getScriptlet(JRScriptlet scriptlet)
	{
		JRBaseScriptlet baseScriptlet = null;

		if (scriptlet != null)
		{
			baseScriptlet = (JRBaseScriptlet)get(scriptlet);
			if (baseScriptlet == null)
			{
				baseScriptlet = new JRBaseScriptlet(scriptlet, this);
			}
		}

		return baseScriptlet;
	}


	/**
	 *
	 */
	protected JRBaseParameter getParameter(JRParameter parameter)
	{
		JRBaseParameter baseParameter = null;

		if (parameter != null)
		{
			baseParameter = (JRBaseParameter)get(parameter);
			if (baseParameter == null)
			{
				baseParameter = new JRBaseParameter(parameter, this);
			}
		}

		return baseParameter;
	}


	/**
	 *
	 */
	protected JRBaseQuery getQuery(JRQuery query)
	{
		JRBaseQuery baseQuery = null;

		if (query != null)
		{
			baseQuery = (JRBaseQuery)get(query);
			if (baseQuery == null)
			{
				baseQuery = new JRBaseQuery(query, this);
			}
		}

		return baseQuery;
	}


	/**
	 *
	 */
	protected JRBaseQueryChunk getQueryChunk(JRQueryChunk queryChunk)
	{
		JRBaseQueryChunk baseQueryChunk = null;

		if (queryChunk != null)
		{
			baseQueryChunk = (JRBaseQueryChunk)get(queryChunk);
			if (baseQueryChunk == null)
			{
				baseQueryChunk = new JRBaseQueryChunk(queryChunk, this);
			}
		}

		return baseQueryChunk;
	}


	/**
	 *
	 */
	protected JRBaseField getField(JRField field)
	{
		JRBaseField baseField = null;

		if (field != null)
		{
			baseField = (JRBaseField)get(field);
			if (baseField == null)
			{
				baseField = new JRBaseField(field, this);
			}
		}

		return baseField;
	}


	/**
	 *
	 */
	protected JRBaseSortField getSortField(JRSortField sortField)
	{
		JRBaseSortField baseSortField = null;

		if (sortField != null)
		{
			baseSortField = (JRBaseSortField)get(sortField);
			if (baseSortField == null)
			{
				baseSortField = new JRBaseSortField(sortField, this);
			}
		}

		return baseSortField;
	}


	/**
	 *
	 */
	public JRBaseVariable getVariable(JRVariable variable)
	{
		JRBaseVariable baseVariable = null;

		if (variable != null)
		{
			baseVariable = (JRBaseVariable)get(variable);
			if (baseVariable == null)
			{
				baseVariable = new JRBaseVariable(variable, this);
			}
		}

		return baseVariable;
	}


	public JRExpression getExpression(JRExpression expression, boolean assignNotUsedId)
	{
		JRBaseExpression baseExpression = null;

		if (expression != null)
		{
			baseExpression = (JRBaseExpression)get(expression);
			if (baseExpression == null)
			{
				Integer expressionId = getCollectedExpressionId(expression, assignNotUsedId);
				baseExpression = new JRBaseExpression(expression, this, expressionId);
			}
		}

		return baseExpression;
	}


	private Integer getCollectedExpressionId(JRExpression expression, boolean assignNotUsedId)
	{
		Integer expressionId = null;
		if (expressionCollector != null)
		{
			expressionId = expressionCollector.getExpressionId(expression);
			if (expressionId == null)
			{
				if (assignNotUsedId)
				{
					expressionId = JRExpression.NOT_USED_ID;
				}
				else
				{
					throw new JRRuntimeException("Expression ID not found for expression <<" + expression.getText() + ">>.");
				}
			}
		}
		return expressionId;
	}


	/**
	 *
	 */
	protected JRBaseExpressionChunk getExpressionChunk(JRExpressionChunk expressionChunk)
	{
		JRBaseExpressionChunk baseExpressionChunk = null;

		if (expressionChunk != null)
		{
			baseExpressionChunk = (JRBaseExpressionChunk)get(expressionChunk);
			if (baseExpressionChunk == null)
			{
				baseExpressionChunk = new JRBaseExpressionChunk(expressionChunk, this);
			}
		}

		return baseExpressionChunk;
	}


	/**
	 *
	 */
	protected JRBaseGroup getGroup(JRGroup group)
	{
		JRBaseGroup baseGroup = null;

		if (group != null)
		{
			baseGroup = (JRBaseGroup)get(group);
			if (baseGroup == null)
			{
				baseGroup = new JRBaseGroup(group, this);
			}
		}

		return baseGroup;
	}


	/**
	 *
	 */
	protected JRBaseSection getSection(JRSection section)
	{
		JRBaseSection baseSection = null;

		if (section != null)
		{
			baseSection = (JRBaseSection)get(section);
			if (baseSection == null)
			{
				baseSection = new JRBaseSection(section, this);
			}
		}

		return baseSection;
	}


	/**
	 *
	 */
	protected JRBaseBand getBand(JRBand band)
	{
		JRBaseBand baseBand = null;

		if (band != null)
		{
			baseBand = (JRBaseBand)get(band);
			if (baseBand == null)
			{
				baseBand = new JRBaseBand(band, this);
			}
		}

		return baseBand;
	}


	/**
	 *
	 */
	public void visitElementGroup(JRElementGroup elementGroup)
	{
		JRElementGroup baseElementGroup = null;

		if (elementGroup != null)
		{
			baseElementGroup = (JRElementGroup)get(elementGroup);
			if (baseElementGroup == null)
			{
				baseElementGroup = new JRBaseElementGroup(elementGroup, this);
			}
		}

		setVisitResult(baseElementGroup);
	}


	/**
	 *
	 */
	public void visitBreak(JRBreak breakElement)
	{
		JRBaseBreak baseBreak = null;

		if (breakElement != null)
		{
			baseBreak = (JRBaseBreak)get(breakElement);
			if (baseBreak == null)
			{
				baseBreak = new JRBaseBreak(breakElement, this);
			}
		}

		setVisitResult(baseBreak);
	}


	/**
	 *
	 */
	public void visitLine(JRLine line)
	{
		JRBaseLine baseLine = null;

		if (line != null)
		{
			baseLine = (JRBaseLine)get(line);
			if (baseLine == null)
			{
				baseLine = new JRBaseLine(line, this);
			}
		}

		setVisitResult(baseLine);
	}


	/**
	 *
	 */
	public void visitRectangle(JRRectangle rectangle)
	{
		JRBaseRectangle baseRectangle = null;

		if (rectangle != null)
		{
			baseRectangle = (JRBaseRectangle)get(rectangle);
			if (baseRectangle == null)
			{
				baseRectangle = new JRBaseRectangle(rectangle, this);
			}
		}

		setVisitResult(baseRectangle);
	}


	/**
	 *
	 */
	public void visitEllipse(JREllipse ellipse)
	{
		JRBaseEllipse baseEllipse = null;

		if (ellipse != null)
		{
			baseEllipse = (JRBaseEllipse)get(ellipse);
			if (baseEllipse == null)
			{
				baseEllipse = new JRBaseEllipse(ellipse, this);
			}
		}

		setVisitResult(baseEllipse);
	}


	/**
	 *
	 */
	public void visitImage(JRImage image)
	{
		JRBaseImage baseImage = null;

		if (image != null)
		{
			baseImage = (JRBaseImage)get(image);
			if (baseImage == null)
			{
				baseImage = new JRBaseImage(image, this);
			}
		}

		setVisitResult(baseImage);
	}


	/**
	 *
	 */
	public void visitStaticText(JRStaticText staticText)
	{
		JRBaseStaticText baseStaticText = null;

		if (staticText != null)
		{
			baseStaticText = (JRBaseStaticText)get(staticText);
			if (baseStaticText == null)
			{
				baseStaticText = new JRBaseStaticText(staticText, this);
			}
		}

		setVisitResult(baseStaticText);
	}


	/**
	 *
	 */
	public void visitTextField(JRTextField textField)
	{
		JRBaseTextField baseTextField = null;

		if (textField != null)
		{
			baseTextField = (JRBaseTextField)get(textField);
			if (baseTextField == null)
			{
				baseTextField = new JRBaseTextField(textField, this);
			}
		}

		setVisitResult(baseTextField);
	}


	/**
	 *
	 */
	public void visitSubreport(JRSubreport subreport)
	{
		JRBaseSubreport baseSubreport = null;

		if (subreport != null)
		{
			baseSubreport = (JRBaseSubreport)get(subreport);
			if (baseSubreport == null)
			{
				baseSubreport = new JRBaseSubreport(subreport, this);
			}
		}

		setVisitResult(baseSubreport);
	}


	/**
	 *
	 */
	protected JRBaseSubreportParameter getSubreportParameter(JRSubreportParameter subreportParameter)
	{
		JRBaseSubreportParameter baseSubreportParameter = null;

		if (subreportParameter != null)
		{
			baseSubreportParameter = (JRBaseSubreportParameter)get(subreportParameter);
			if (baseSubreportParameter == null)
			{
				baseSubreportParameter = new JRBaseSubreportParameter(subreportParameter, this);
				put(subreportParameter, baseSubreportParameter);
			}
		}

		return baseSubreportParameter;
	}


	protected JRBaseDatasetParameter getDatasetParameter(JRDatasetParameter datasetParameter)
	{
		JRBaseDatasetParameter baseSubreportParameter = null;

		if (datasetParameter != null)
		{
			baseSubreportParameter = (JRBaseDatasetParameter) get(datasetParameter);
			if (baseSubreportParameter == null)
			{
				baseSubreportParameter = new JRBaseDatasetParameter(datasetParameter, this);
				put(datasetParameter, baseSubreportParameter);
			}
		}

		return baseSubreportParameter;
	}


	/**
	 *
	 */
	protected JRBaseSubreportReturnValue getSubreportReturnValue(JRSubreportReturnValue returnValue)
	{
		JRBaseSubreportReturnValue baseSubreportReturnValue = null;

		if (returnValue != null)
		{
			baseSubreportReturnValue = (JRBaseSubreportReturnValue)get(returnValue);
			if (baseSubreportReturnValue == null)
			{
				baseSubreportReturnValue = new JRBaseSubreportReturnValue(returnValue, this);
				put(returnValue, baseSubreportReturnValue);
			}
		}

		return baseSubreportReturnValue;
	}


	/**
	 *
	 */
	public JRConditionalStyle getConditionalStyle(JRConditionalStyle conditionalStyle, JRStyle style)
	{
		JRBaseConditionalStyle baseConditionalStyle = null;
		if (conditionalStyle != null)
		{
			baseConditionalStyle = (JRBaseConditionalStyle) get(conditionalStyle);
			if (baseConditionalStyle == null) {
				baseConditionalStyle = new JRBaseConditionalStyle(conditionalStyle, style, this);
				put(conditionalStyle, baseConditionalStyle);
			}
		}
		return baseConditionalStyle;
	}


	public JRBaseCrosstabDataset getCrosstabDataset(JRCrosstabDataset crosstabDataset)
	{
		JRBaseCrosstabDataset baseCrosstabDataset = null;

		if (crosstabDataset != null)
		{
			baseCrosstabDataset = (JRBaseCrosstabDataset) get(crosstabDataset);
			if (baseCrosstabDataset == null)
			{
				baseCrosstabDataset = new JRBaseCrosstabDataset(crosstabDataset, this);
			}
		}

		return baseCrosstabDataset;
	}


	public JRBaseCrosstabRowGroup getCrosstabRowGroup(JRCrosstabRowGroup group)
	{
		JRBaseCrosstabRowGroup baseCrosstabRowGroup = null;

		if (group != null)
		{
			baseCrosstabRowGroup = (JRBaseCrosstabRowGroup) get(group);
			if (baseCrosstabRowGroup == null)
			{
				baseCrosstabRowGroup = new JRBaseCrosstabRowGroup(group, this);
			}
		}

		return baseCrosstabRowGroup;
	}


	public JRBaseCrosstabColumnGroup getCrosstabColumnGroup(JRCrosstabColumnGroup group)
	{
		JRBaseCrosstabColumnGroup baseCrosstabDataset = null;

		if (group != null)
		{
			baseCrosstabDataset = (JRBaseCrosstabColumnGroup) get(group);
			if (baseCrosstabDataset == null)
			{
				baseCrosstabDataset = new JRBaseCrosstabColumnGroup(group, this);
			}
		}

		return baseCrosstabDataset;
	}


	public JRBaseCrosstabBucket getCrosstabBucket(JRCrosstabBucket bucket)
	{
		JRBaseCrosstabBucket baseCrosstabBucket = null;

		if (bucket != null)
		{
			baseCrosstabBucket = (JRBaseCrosstabBucket) get(bucket);
			if (baseCrosstabBucket == null)
			{
				baseCrosstabBucket = new JRBaseCrosstabBucket(bucket, this);
			}
		}

		return baseCrosstabBucket;
	}


	public JRBaseCrosstabMeasure getCrosstabMeasure(JRCrosstabMeasure measure)
	{
		JRBaseCrosstabMeasure baseCrosstabMeasure = null;

		if (measure != null)
		{
			baseCrosstabMeasure = (JRBaseCrosstabMeasure) get(measure);
			if (baseCrosstabMeasure == null)
			{
				baseCrosstabMeasure = new JRBaseCrosstabMeasure(measure, this);
			}
		}

		return baseCrosstabMeasure;
	}


	public void visitCrosstab(JRCrosstab crosstab)
	{
		JRBaseCrosstab baseCrosstab = null;

		if (crosstab != null)
		{
			baseCrosstab = (JRBaseCrosstab) get(crosstab);
			if (baseCrosstab == null)
			{
				Integer id = expressionCollector.getCrosstabId(crosstab);
				if (id == null)
				{
					throw new JRRuntimeException("Crosstab ID not found.");
				}

				baseCrosstab = new JRBaseCrosstab(crosstab, this, id.intValue());
			}
		}

		setVisitResult(baseCrosstab);
	}


	public JRBaseDataset getDataset(JRDataset dataset)
	{
		JRBaseDataset baseDataset = null;

		if (dataset != null)
		{
			baseDataset = (JRBaseDataset)get(dataset);
			if (baseDataset == null)
			{
				baseDataset = new JRBaseDataset(dataset, this);
			}
		}

		return baseDataset;
	}


	public JRBaseDatasetRun getDatasetRun(JRDatasetRun datasetRun)
	{
		JRBaseDatasetRun baseDatasetRun = null;

		if (datasetRun != null)
		{
			baseDatasetRun = (JRBaseDatasetRun)get(datasetRun);
			if (baseDatasetRun == null)
			{
				baseDatasetRun = new JRBaseDatasetRun(datasetRun, this);
			}
		}

		return baseDatasetRun;
	}


	public JRBaseCellContents getCell(JRCellContents cell)
	{
		JRBaseCellContents baseCell = null;

		if (cell != null)
		{
			baseCell = (JRBaseCellContents)get(cell);
			if (baseCell == null)
			{
				baseCell = new JRBaseCellContents(cell, this);
			}
		}

		return baseCell;
	}


	public JRCrosstabCell getCrosstabCell(JRCrosstabCell cell)
	{
		JRBaseCrosstabCell baseCell = null;

		if (cell != null)
		{
			baseCell = (JRBaseCrosstabCell)get(cell);
			if (baseCell == null)
			{
				baseCell = new JRBaseCrosstabCell(cell, this);
			}
		}

		return baseCell;
	}


	public JRBaseCrosstabParameter getCrosstabParameter(JRCrosstabParameter parameter)
	{
		JRBaseCrosstabParameter baseParameter = null;

		if (parameter != null)
		{
			baseParameter = (JRBaseCrosstabParameter) get(parameter);
			if (baseParameter == null)
			{
				baseParameter = new JRBaseCrosstabParameter(parameter, this);
			}
		}

		return baseParameter;
	}


	public void visitFrame(JRFrame frame)
	{
		JRBaseFrame baseFrame = null;

		if (frame != null)
		{
			baseFrame = (JRBaseFrame) get(frame);
			if (baseFrame == null)
			{
				baseFrame = new JRBaseFrame(frame, this);
			}
		}

		setVisitResult(baseFrame);
	}


	public JRHyperlinkParameter getHyperlinkParameter(JRHyperlinkParameter parameter)
	{
		JRHyperlinkParameter baseParameter = null;

		if (parameter != null)
		{
			baseParameter = (JRHyperlinkParameter) get(parameter);
			if (baseParameter == null)
			{
				baseParameter = new JRBaseHyperlinkParameter(parameter, this);
			}
		}

		return baseParameter;
	}


	public JRHyperlink getHyperlink(JRHyperlink hyperlink)
	{
		JRHyperlink link = null;
		if (hyperlink != null)
		{
			link = (JRHyperlink) get(hyperlink);
			if (link == null)
			{
				link = new JRBaseHyperlink(hyperlink, this);
			}
		}
		return link;
	}



	public JRReportTemplate getReportTemplate(JRReportTemplate template)
	{
		JRReportTemplate baseTemplate = null;
		if (template != null)
		{
			baseTemplate = (JRReportTemplate) get(template);
			if (baseTemplate == null)
			{
				baseTemplate = new JRBaseReportTemplate(template, this);
			}
		}
		return baseTemplate;
	}

	public JRPropertyExpression getPropertyExpression(JRPropertyExpression propertyExpression)
	{
		JRPropertyExpression baseProp = null;
		if (propertyExpression != null)
		{
			baseProp = (JRPropertyExpression) get(propertyExpression);
			if (baseProp == null)
			{
				baseProp = new JRBasePropertyExpression(propertyExpression, this);
			}
		}
		return baseProp;
	}


	public void visitComponentElement(JRComponentElement componentElement)
	{
		JRBaseComponentElement base = null;

		if (componentElement != null)
		{
			base = (JRBaseComponentElement) get(componentElement);
			if (base == null)
			{
				base = new JRBaseComponentElement(componentElement, this);
			}
		}

		setVisitResult(base);
	}


	public JRGenericElementParameter getGenericElementParameter(
			JRGenericElementParameter elementParameter)
	{
		JRGenericElementParameter baseParameter = null;
		if (elementParameter != null)
		{
			baseParameter = (JRGenericElementParameter) get(elementParameter);
			if (baseParameter == null)
			{
				baseParameter = new JRBaseGenericElementParameter(
						elementParameter, this);
			}
		}
		return baseParameter;
	}


	public void visitGenericElement(JRGenericElement element)
	{
		JRBaseGenericElement base = null;
		if (element != null)
		{
			base = (JRBaseGenericElement) get(element);
			if (base == null)
			{
				base = new JRBaseGenericElement(element, this);
			}
		}
		setVisitResult(base);
	}
}
