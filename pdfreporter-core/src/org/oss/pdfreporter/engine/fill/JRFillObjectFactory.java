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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;


import org.oss.pdfreporter.crosstabs.JRCrosstab;
import org.oss.pdfreporter.crosstabs.JRCrosstabDataset;
import org.oss.pdfreporter.crosstabs.JRCrosstabParameter;
import org.oss.pdfreporter.crosstabs.fill.JRFillCrosstabParameter;
import org.oss.pdfreporter.engine.JRBand;
import org.oss.pdfreporter.engine.JRBreak;
import org.oss.pdfreporter.engine.JRComponentElement;
import org.oss.pdfreporter.engine.JRConditionalStyle;
import org.oss.pdfreporter.engine.JRDataset;
import org.oss.pdfreporter.engine.JRDatasetRun;
import org.oss.pdfreporter.engine.JRDefaultStyleProvider;
import org.oss.pdfreporter.engine.JRElementGroup;
import org.oss.pdfreporter.engine.JREllipse;
import org.oss.pdfreporter.engine.JRExpression;
import org.oss.pdfreporter.engine.JRField;
import org.oss.pdfreporter.engine.JRFrame;
import org.oss.pdfreporter.engine.JRGenericElement;
import org.oss.pdfreporter.engine.JRGroup;
import org.oss.pdfreporter.engine.JRImage;
import org.oss.pdfreporter.engine.JRLine;
import org.oss.pdfreporter.engine.JRParameter;
import org.oss.pdfreporter.engine.JRRectangle;
import org.oss.pdfreporter.engine.JRReportTemplate;
import org.oss.pdfreporter.engine.JRRuntimeException;
import org.oss.pdfreporter.engine.JRSection;
import org.oss.pdfreporter.engine.JRStaticText;
import org.oss.pdfreporter.engine.JRStyle;
import org.oss.pdfreporter.engine.JRStyleContainer;
import org.oss.pdfreporter.engine.JRStyleSetter;
import org.oss.pdfreporter.engine.JRSubreport;
import org.oss.pdfreporter.engine.JRSubreportReturnValue;
import org.oss.pdfreporter.engine.JRTextField;
import org.oss.pdfreporter.engine.JRVariable;
import org.oss.pdfreporter.engine.base.JRBaseConditionalStyle;
import org.oss.pdfreporter.engine.base.JRBaseStyle;
import org.oss.pdfreporter.uses.org.apache.commons.collections.SequencedHashMap;


/**
 * A factory used to instantiate fill objects based on compiled report objects.
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRFillObjectFactory.java 5498 2012-07-20 11:20:34Z lucianc $
 */
public class JRFillObjectFactory extends JRAbstractFillObjectFactory
{
	private final static Logger logger = Logger.getLogger(JRFillObjectFactory.class.getName());

	/**
	 *
	 */
	protected JRBaseFiller filler;
	private JRFillExpressionEvaluator evaluator;

	private JRFillObjectFactory parentFiller;
	
//	private JRFont defaultFont;

	private List<JRFillElementDataset> elementDatasets = new ArrayList<JRFillElementDataset>();
	private Map<String,List<JRFillElementDataset>> elementDatasetMap = new HashMap<String,List<JRFillElementDataset>>();
	
	private Map<String,List<JRStyleSetter>> delayedStyleSettersByName = new HashMap<String,List<JRStyleSetter>>();
	
	protected static class StylesList
	{
		private final List<JRStyle> styles = new ArrayList<JRStyle>();
		private final Map<String,Integer> stylesIdx = new HashMap<String,Integer>();
		
		public boolean containsStyle(String name)
		{
			return stylesIdx.containsKey(name);
		}
		
		public JRStyle getStyle(String name)
		{
			Integer idx = stylesIdx.get(name);
			return idx == null ? null : styles.get(idx.intValue());
		}
		
		public void addStyle(JRStyle style)
		{
			styles.add(style);
			stylesIdx.put(style.getName(), Integer.valueOf(styles.size() - 1));
		}
		
		public void renamed(String oldName, String newName)
		{
			Integer idx = stylesIdx.remove(oldName);
			stylesIdx.put(newName, idx);
		}
	}
	
	private Set<JRStyle> originalStyleList;
	private StylesList stylesMap = new StylesList();


	/**
	 *
	 */
	protected JRFillObjectFactory(JRBaseFiller filler)
	{
		this(filler, filler.calculator);
	}


	/**
	 *
	 */
	public JRFillObjectFactory(JRBaseFiller filler, JRFillExpressionEvaluator expressionEvaluator)
	{
		this.filler = filler;
		this.evaluator = expressionEvaluator;
	}

	
	public JRFillObjectFactory(JRFillObjectFactory parent, JRFillExpressionEvaluator expressionEvaluator)
	{
		this.parentFiller = parent;
		this.filler = parent.filler;
		this.evaluator = expressionEvaluator;
	}

	
	/**
	 * Returns the expression evaluator which is to be used by objects
	 * created by this factory.
	 * 
	 * @return the expression evaluator associated with this factory
	 */
	public JRFillExpressionEvaluator getExpressionEvaluator()
	{
		return evaluator;
	}

	/**
	 *
	 */
	protected JRFillChartDataset[] getDatasets()
	{
		return elementDatasets.toArray(new JRFillChartDataset[elementDatasets.size()]);
	}


	protected JRFillElementDataset[] getElementDatasets(JRDataset dataset)
	{
		JRFillElementDataset[] elementDatasetsArray;
		List<JRFillElementDataset> elementDatasetsList;
		if (dataset.isMainDataset())
		{
			elementDatasetsList = elementDatasets;
		}
		else
		{
			elementDatasetsList = elementDatasetMap.get(dataset.getName());
		}

		if (elementDatasetsList == null || elementDatasetsList.size() == 0)
		{
			elementDatasetsArray = new JRFillElementDataset[0];
		}
		else
		{
			elementDatasetsArray = new JRFillElementDataset[elementDatasetsList.size()];
			elementDatasetsList.toArray(elementDatasetsArray);
		}

		return elementDatasetsArray;
	}


	protected void registerDelayedStyleSetter(JRStyleSetter delayedSetter, String styleName)
	{
		if (parentFiller == null)
		{
			List<JRStyleSetter> setters = delayedStyleSettersByName.get(styleName);
			if (setters == null)
			{
				setters = new ArrayList<JRStyleSetter>();
				delayedStyleSettersByName.put(styleName, setters);
			}
			
			setters.add(delayedSetter);
		}
		else
		{
			parentFiller.registerDelayedStyleSetter(delayedSetter, styleName);
		}
	}

	public void registerDelayedStyleSetter(JRStyleSetter delayedSetter, JRStyleContainer styleContainer)
	{
		JRStyle style = styleContainer.getStyle();
		String nameReference = styleContainer.getStyleNameReference();
		if (style != null)
		{
			registerDelayedStyleSetter(delayedSetter, style.getName());
		}
		else if (nameReference != null)
		{
			registerDelayedStyleSetter(delayedSetter, nameReference);
		}
	}
	
	public JRBaseStyle getStyle(JRStyle style)
	{
		JRBaseStyle fillStyle = null;

		if (style != null)
		{
			fillStyle = (JRBaseStyle) get(style);
			if (fillStyle == null)
			{
				fillStyle = new JRBaseStyle(style, this);
				
				// deduplicate to previously created identical instances
				fillStyle = filler.fillContext.deduplicate(fillStyle);
				
				put(style, fillStyle);
				
				if (originalStyleList != null && originalStyleList.contains(style))
				{
					renameExistingStyle(style.getName());
					stylesMap.addStyle(style);
				}				
			}
		}

		return fillStyle;
	}

	protected void renameExistingStyle(String name)
	{
		JRStyle originalStyle = stylesMap.getStyle(name);
		if (originalStyle != null)
		{
			//found a previous external style with the same name
			//renaming the previous style
			JRBaseStyle style = (JRBaseStyle) get(originalStyle);
			
			String newName;
			int suf = 1;
			do
			{
				newName = name + suf;
				++suf;
			}
			while(stylesMap.containsStyle(newName));
			
			style.rename(newName);
			stylesMap.renamed(name, newName);
		}
	}


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
			JRStyle originalStyle = stylesMap.getStyle(nameReference);
			if (originalStyle == null)
			{
				throw new JRRuntimeException("Style " + nameReference + " not found");
			}
			
			JRStyle externalStyle = (JRStyle) get(originalStyle);
			setter.setStyle(externalStyle);
		}
	}


	/**
	 *
	 */
	protected JRFillParameter getParameter(JRParameter parameter)
	{
		JRFillParameter fillParameter = null;

		if (parameter != null)
		{
			fillParameter = (JRFillParameter)get(parameter);
			if (fillParameter == null)
			{
				fillParameter = new JRFillParameter(parameter, this);
			}
		}

		return fillParameter;
	}


	/**
	 *
	 */
	protected JRFillField getField(JRField field)
	{
		JRFillField fillField = null;

		if (field != null)
		{
			fillField = (JRFillField)get(field);
			if (fillField == null)
			{
				fillField = new JRFillField(field, this);
			}
		}

		return fillField;
	}


	/**
	 *
	 */
	public JRFillVariable getVariable(JRVariable variable)
	{
		JRFillVariable fillVariable = null;

		if (variable != null)
		{
			fillVariable = (JRFillVariable)get(variable);
			if (fillVariable == null)
			{
				fillVariable = new JRFillVariable(variable, this);
			}
		}

		return fillVariable;
	}


	/**
	 *
	 */
	protected JRFillGroup getGroup(JRGroup group)
	{
		JRFillGroup fillGroup = null;

		if (group != null)
		{
			fillGroup = (JRFillGroup)get(group);
			if (fillGroup == null)
			{
				fillGroup = new JRFillGroup(group, this);
			}
		}

		return fillGroup;
	}


	/**
	 *
	 */
	protected JRFillSection getSection(JRSection section)
	{
		JRFillSection fillSection = null;

		if (section == null)
		{
			fillSection = filler.missingFillSection;
		}
		else
		{
			fillSection = (JRFillSection)get(section);
			if (fillSection == null)
			{
				fillSection = new JRFillSection(filler, section, this);
			}
		}

		return fillSection;
	}


	/**
	 *
	 */
	protected JRFillBand getBand(JRBand band)
	{
		JRFillBand fillBand = null;

		if (band == null)
		{
			fillBand = filler.missingFillBand;
		}
		else
		{
			fillBand = (JRFillBand)get(band);
			if (fillBand == null)
			{
				fillBand = new JRFillBand(filler, band, this);
			}
		}

		return fillBand;
	}


	/**
	 *
	 */
	public void visitElementGroup(JRElementGroup elementGroup)
	{
		JRFillElementGroup fillElementGroup = null;

		if (elementGroup != null)
		{
			fillElementGroup = (JRFillElementGroup)get(elementGroup);
			if (fillElementGroup == null)
			{
				fillElementGroup = new JRFillElementGroup(elementGroup, this);
			}
		}

		setVisitResult(fillElementGroup);
	}


	/**
	 *
	 */
	public void visitBreak(JRBreak breakElement)
	{
		JRFillBreak fillBreak = null;

		if (breakElement != null)
		{
			fillBreak = (JRFillBreak)get(breakElement);
			if (fillBreak == null)
			{
				fillBreak = new JRFillBreak(filler, breakElement, this);
			}
		}

		setVisitResult(fillBreak);
	}


	/**
	 *
	 */
	public void visitLine(JRLine line)
	{
		JRFillLine fillLine = null;

		if (line != null)
		{
			fillLine = (JRFillLine)get(line);
			if (fillLine == null)
			{
				fillLine = new JRFillLine(filler, line, this);
			}
		}

		setVisitResult(fillLine);
	}


	/**
	 *
	 */
	public void visitRectangle(JRRectangle rectangle)
	{
		JRFillRectangle fillRectangle = null;

		if (rectangle != null)
		{
			fillRectangle = (JRFillRectangle)get(rectangle);
			if (fillRectangle == null)
			{
				fillRectangle = new JRFillRectangle(filler, rectangle, this);
			}
		}

		setVisitResult(fillRectangle);
	}


	/**
	 *
	 */
	public void visitEllipse(JREllipse ellipse)
	{
		JRFillEllipse fillEllipse = null;

		if (ellipse != null)
		{
			fillEllipse = (JRFillEllipse)get(ellipse);
			if (fillEllipse == null)
			{
				fillEllipse = new JRFillEllipse(filler, ellipse, this);
			}
		}

		setVisitResult(fillEllipse);
	}


	/**
	 *
	 */
	public void visitImage(JRImage image)
	{
		JRFillImage fillImage = null;

		if (image != null)
		{
			fillImage = (JRFillImage)get(image);
			if (fillImage == null)
			{
				fillImage = new JRFillImage(filler, image, this);
			}
		}

		setVisitResult(fillImage);
	}


	/**
	 *
	 */
	public void visitStaticText(JRStaticText staticText)
	{
		JRFillStaticText fillStaticText = null;

		if (staticText != null)
		{
			fillStaticText = (JRFillStaticText)get(staticText);
			if (fillStaticText == null)
			{
				fillStaticText = new JRFillStaticText(filler, staticText, this);
			}
		}

		setVisitResult(fillStaticText);
	}


	/**
	 *
	 */
	public void visitTextField(JRTextField textField)
	{
		JRFillTextField fillTextField = null;

		if (textField != null)
		{
			fillTextField = (JRFillTextField)get(textField);
			if (fillTextField == null)
			{
				fillTextField = new JRFillTextField(filler, textField, this);
			}
		}

		setVisitResult(fillTextField);
	}


	/**
	 *
	 */
	public void visitSubreport(JRSubreport subreport)
	{
		JRFillSubreport fillSubreport = null;

		if (subreport != null)
		{
			fillSubreport = (JRFillSubreport)get(subreport);
			if (fillSubreport == null)
			{
				fillSubreport = new JRFillSubreport(filler, subreport, this);
			}
		}

		setVisitResult(fillSubreport);
	}
	
	
	protected JRFillSubreportReturnValue getSubreportReturnValue(JRSubreportReturnValue returnValue)
	{
		JRFillSubreportReturnValue fillReturnValue = null;

		if (returnValue != null)
		{
			fillReturnValue = (JRFillSubreportReturnValue) get(returnValue);
			if (fillReturnValue == null)
			{
				fillReturnValue = new JRFillSubreportReturnValue(returnValue, this, filler);
			}
		}

		return fillReturnValue;
	}


	public void visitCrosstab(JRCrosstab crosstabElement)
	{
		JRFillCrosstab fillCrosstab = null;
		logger.finest("Visit Crosstab: " + crosstabElement);
		if (crosstabElement != null)
		{
			fillCrosstab = (JRFillCrosstab) get(crosstabElement);
			if (fillCrosstab == null)
			{
				fillCrosstab = new JRFillCrosstab(filler, crosstabElement, this);
			}
		}

		setVisitResult(fillCrosstab);
	}


	public JRFillCrosstab.JRFillCrosstabDataset getCrosstabDataset(JRCrosstabDataset dataset, JRFillCrosstab fillCrosstab)
	{
		JRFillCrosstab.JRFillCrosstabDataset fillDataset = null;

		if (dataset != null)
		{
			fillDataset = (JRFillCrosstab.JRFillCrosstabDataset)get(dataset);
			if (fillDataset == null)
			{
				fillDataset = fillCrosstab.new JRFillCrosstabDataset(dataset, this);
				registerElementDataset(fillDataset);
			}
		}

		return fillDataset;
	}


	public JRFillDataset getDataset(JRDataset dataset)
	{
		JRFillDataset fillDataset = null;

		if (dataset != null)
		{
			fillDataset = (JRFillDataset) get(dataset);
			if (fillDataset == null)
			{
				fillDataset = new JRFillDataset(filler, dataset, this);
			}
		}

		return fillDataset;
	}


	/**
	 * Register an element dataset with the report filler.
	 * 
	 * <p>
	 * Registration of element datasets is required in order for the filler
	 * to increment the datasets when iterating through the datasource.
	 * 
	 * @param elementDataset the dataset to register
	 */
	public void registerElementDataset(JRFillElementDataset elementDataset)
	{
		List<JRFillElementDataset> elementDatasetsList;
		JRDatasetRun datasetRun = elementDataset.getDatasetRun();
		if (datasetRun == null)
		{
			elementDatasetsList = elementDatasets;
		}
		else
		{
			String datasetName = datasetRun.getDatasetName();
			elementDatasetsList = elementDatasetMap.get(datasetName);
			if (elementDatasetsList == null)
			{
				elementDatasetsList = new ArrayList<JRFillElementDataset>();
				elementDatasetMap.put(datasetName, elementDatasetsList);
			}
		}
		elementDatasetsList.add(elementDataset);
	}


	public JRFillDatasetRun getDatasetRun(JRDatasetRun datasetRun)
	{
		JRFillDatasetRun fillDatasetRun = null;

		if (datasetRun != null)
		{
			fillDatasetRun = (JRFillDatasetRun) get(datasetRun);
			if (fillDatasetRun == null)
			{
				fillDatasetRun = new JRFillDatasetRun(filler, datasetRun, this);
			}
		}

		return fillDatasetRun;
	}


	public JRFillCrosstabParameter getCrosstabParameter(JRCrosstabParameter parameter)
	{
		JRFillCrosstabParameter fillParameter = null;

		if (parameter != null)
		{
			fillParameter = (JRFillCrosstabParameter) get(parameter);
			if (fillParameter == null)
			{
				fillParameter = new JRFillCrosstabParameter(parameter, this);
			}
		}

		return fillParameter;
	}


	public void visitFrame(JRFrame frame)
	{
		Object fillFrame = null;
		// This is the only place where an object gets replaced in the factory map by something else,
		// and we can no longer make a precise cast when getting it.
		// The JRFillFrame object is replaced in the map by a JRFillFrameElements object.
		//JRFillFrame fillFrame = null;

		if (frame != null)
		{
			fillFrame = get(frame);
			//fillFrame = (JRFillFrame) get(frame);
			if (fillFrame == null)
			{
				fillFrame = new JRFillFrame(filler, frame, this);
			}
		}

		setVisitResult(fillFrame);
	}


	/**
	 * Returns the current report filler.
	 * 
	 * @return the current report filler
	 */
	public JRBaseFiller getFiller()
	{
		return filler;
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


	public JRExpression getExpression(JRExpression expression, boolean assignNotUsedId)
	{
		return expression;
	}




	public JRFillReportTemplate getReportTemplate(JRReportTemplate template)
	{
		JRFillReportTemplate fillTemplate = null;
		if (template != null)
		{
			fillTemplate = (JRFillReportTemplate) get(template);
			if (fillTemplate == null)
			{
				fillTemplate = new JRFillReportTemplate(template, filler, this);
			}
		}
		return fillTemplate;
	}
	
	public List<JRStyle> setStyles(List<JRStyle> styles)
	{
		originalStyleList = new HashSet<JRStyle>(styles);
		
		//filtering requested styles
		Set<JRStyle> requestedStyles = collectRequestedStyles(styles);
		
		//collect used styles
		Map<JRStyle,Object> usedStylesMap = new SequencedHashMap();
		Map<String,JRStyle> allStylesMap = new HashMap<String,JRStyle>();
		for (Iterator<JRStyle> it = styles.iterator(); it.hasNext();)
		{
			JRStyle style = it.next();
			if (requestedStyles.contains(style))
			{
				collectUsedStyles(style, usedStylesMap, allStylesMap);
			}
			allStylesMap.put(style.getName(), style);
		}
		
		List<JRStyle> includedStyles = new ArrayList<JRStyle>();
		for (Iterator<JRStyle> it = usedStylesMap.keySet().iterator(); it.hasNext();)
		{
			JRStyle style = it.next();
			JRStyle newStyle = getStyle(style);
			
			includedStyles.add(newStyle);
			if (requestedStyles.contains(style))
			{
				useDelayedStyle(newStyle);
			}			
		}
		
		checkUnresolvedReferences();
		
		return includedStyles;
	}

	protected Set<JRStyle> collectRequestedStyles(List<JRStyle> externalStyles)
	{
		Map<String,JRStyle> requestedStylesMap = new HashMap<String,JRStyle>();
		for (Iterator<JRStyle> it = externalStyles.iterator(); it.hasNext();)
		{
			JRStyle style = it.next();
			String name = style.getName();
			if (delayedStyleSettersByName.containsKey(name))
			{
				requestedStylesMap.put(name, style);
			}
		}
		
		return new HashSet<JRStyle>(requestedStylesMap.values());
	}

	protected void collectUsedStyles(JRStyle style, Map<JRStyle,Object> usedStylesMap, Map<String,JRStyle> allStylesMap)
	{
		if (!usedStylesMap.containsKey(style) && originalStyleList.contains(style))
		{
			JRStyle parent = style.getStyle();
			if (parent == null)
			{
				String parentName = style.getStyleNameReference();
				if (parentName != null)
				{
					parent = allStylesMap.get(parentName);
					if (parent == null)
					{
						throw new JRRuntimeException("Style " + parentName + " not found");
					}
				}
			}
			
			if (parent != null)
			{
				collectUsedStyles(parent, usedStylesMap, allStylesMap);
			}
			
			usedStylesMap.put(style, null);
		}
	}
	
	protected void useDelayedStyle(JRStyle style)
	{
		List<JRStyleSetter> delayedSetters = delayedStyleSettersByName.remove(style.getName());
		if (delayedSetters != null)
		{
			for (Iterator<JRStyleSetter> it = delayedSetters.iterator(); it.hasNext();)
			{
				JRStyleSetter setter = it.next();
				setter.setStyle(style);
			}
		}
	}

	protected void checkUnresolvedReferences()
	{
		if (!delayedStyleSettersByName.isEmpty())
		{
			StringBuffer errorMsg = new StringBuffer("Could not resolve style(s): ");
			for (Iterator<String> it = delayedStyleSettersByName.keySet().iterator(); it.hasNext();)
			{
				String name = it.next();
				errorMsg.append(name);
				errorMsg.append(", ");
			}
			
			throw new JRRuntimeException(errorMsg.substring(0, errorMsg.length() - 2));
		}
	}

	public JRDefaultStyleProvider getDefaultStyleProvider()
	{
		return filler.getJasperPrint().getDefaultStyleProvider();
	}


	public void visitComponentElement(JRComponentElement componentElement)
	{
		JRFillComponentElement fill = null;

		if (componentElement != null)
		{
			fill = (JRFillComponentElement) get(componentElement);
			if (fill == null)
			{
				fill = new JRFillComponentElement(filler, componentElement, this);
			}
		}

		setVisitResult(fill);
	}


	public void visitGenericElement(JRGenericElement element)
	{
		JRFillGenericElement fill = null;
		if (element != null)
		{
			fill = (JRFillGenericElement) get(element);
			if (fill == null)
			{
				fill = new JRFillGenericElement(filler, element, this);
			}
		}
		setVisitResult(fill);
	}

	// TODO (21.03.2013, Donat, Open Software Solutions): Refactoring JFreeChart reactivate removed chart packages, see original class for the job

}
