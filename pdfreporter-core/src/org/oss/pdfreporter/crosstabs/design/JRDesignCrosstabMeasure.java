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
package org.oss.pdfreporter.crosstabs.design;

import org.oss.pdfreporter.crosstabs.base.JRBaseCrosstabMeasure;
import org.oss.pdfreporter.crosstabs.type.CrosstabPercentageEnum;
import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.JRExpression;
import org.oss.pdfreporter.engine.design.JRDesignVariable;
import org.oss.pdfreporter.engine.design.events.JRChangeEventsSupport;
import org.oss.pdfreporter.engine.design.events.JRPropertyChangeSupport;
import org.oss.pdfreporter.engine.type.CalculationEnum;

/**
 * Crosstab measure implementation to be used for report designing.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRDesignCrosstabMeasure.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRDesignCrosstabMeasure extends JRBaseCrosstabMeasure implements JRChangeEventsSupport
{
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public static final String PROPERTY_CALCULATION = "calculation";

	public static final String PROPERTY_INCREMENTER_FACTORY_CLASS_NAME = "incrementerFactoryClassName";

	public static final String PROPERTY_NAME = "name";

	public static final String PROPERTY_PERCENTAGE_CALCULATION_CLASS_NAME = "percentageCalculatorClassName";

	public static final String PROPERTY_PERCENTAGE_OF_TYPE = "percentageOfType";
	
	public static final String PROPERTY_VALUE_CLASS = "valueClassName";

	public static final String PROPERTY_VALUE_EXPRESSION = "expression";
	
	protected JRDesignVariable designVariable;
	
	
	/**
	 * Creates a crosstab measure.
	 */
	public JRDesignCrosstabMeasure()
	{
		super();
		
		variable = designVariable = new JRDesignVariable();
		designVariable.setCalculation(CalculationEnum.SYSTEM);
		designVariable.setSystemDefined(true);
	}

	
	/**
	 * Sets the calculation type.
	 * 
	 * @param calculationValue the calculation type
	 * @see org.oss.pdfreporter.crosstabs.JRCrosstabMeasure#getCalculationValue()
	 */
	public void setCalculation(CalculationEnum calculationValue)
	{
		Object old = this.calculationValue;
		this.calculationValue = calculationValue;
		getEventSupport().firePropertyChange(PROPERTY_CALCULATION, old, this.calculationValue);
	}

	
	/**
	 * Sets the measure value expression.
	 * 
	 * @param expression the measure value expression.
	 * @see org.oss.pdfreporter.crosstabs.JRCrosstabMeasure#getValueExpression()
	 */
	public void setValueExpression(JRExpression expression)
	{
		Object old = this.expression;
		this.expression = expression;
		getEventSupport().firePropertyChange(PROPERTY_VALUE_EXPRESSION, old, this.expression);
	}

	
	/**
	 * Sets the incrementer factory class name.
	 * 
	 * @param incrementerFactoryClassName the incrementer factory class name
	 * @see org.oss.pdfreporter.crosstabs.JRCrosstabMeasure#getIncrementerFactoryClassName()
	 */
	public void setIncrementerFactoryClassName(String incrementerFactoryClassName)
	{
		Object old = this.incrementerFactoryClassName;
		this.incrementerFactoryClassName = incrementerFactoryClassName;
		this.incrementerFactoryClass = null;
		this.incrementerFactoryClassRealName = null;
		getEventSupport().firePropertyChange(PROPERTY_INCREMENTER_FACTORY_CLASS_NAME, old, this.incrementerFactoryClassName);
	}

	
	/**
	 * Sets the measure name.
	 * 
	 * @param name the measure name
	 * @see org.oss.pdfreporter.crosstabs.JRCrosstabMeasure#getName()
	 */
	public void setName(String name)
	{
		Object old = this.name;
		this.name = name;
		designVariable.setName(name);
		getEventSupport().firePropertyChange(PROPERTY_NAME, old, this.name);
	}

	
	/**
	 * Sets the percentage calculation type.
	 * 
	 * @param percentageType the percentage calculation type
	 * @see org.oss.pdfreporter.crosstabs.JRCrosstabMeasure#getPercentageType()
	 */
	public void setPercentageType(CrosstabPercentageEnum percentageType)
	{
		Object old = this.percentageType;
		this.percentageType = percentageType;
		getEventSupport().firePropertyChange(PROPERTY_PERCENTAGE_OF_TYPE, old, this.percentageType);
	}

	
	/**
	 * Sets the percentage calculator class name.
	 * 
	 * @param percentageCalculatorClassName the percentage calculator class name
	 * @see org.oss.pdfreporter.crosstabs.JRCrosstabMeasure#getPercentageCalculatorClassName()
	 */
	public void setPercentageCalculatorClassName(String percentageCalculatorClassName)
	{
		Object old = this.percentageCalculatorClassName;
		this.percentageCalculatorClassName = percentageCalculatorClassName;
		this.percentageCalculatorClass = null;
		this.percentageCalculatorClassRealName = null;
		getEventSupport().firePropertyChange(PROPERTY_PERCENTAGE_CALCULATION_CLASS_NAME, old, this.percentageCalculatorClassName);
	}

	
	/**
	 * Sets the measure value class name.
	 * 
	 * @param valueClassName the measure value class name
	 * @see org.oss.pdfreporter.crosstabs.JRCrosstabMeasure#getValueClassName()
	 */
	public void setValueClassName(String valueClassName)
	{
		String old = this.valueClassName;
		
		this.valueClassName = valueClassName;
		this.valueClass = null;
		this.valueClassRealName = null;
		designVariable.setValueClassName(valueClassName);
		
		getEventSupport().firePropertyChange(PROPERTY_VALUE_CLASS, old,
				this.valueClassName);
	}
	

	/**
	 * Add a property listener to listen to all properties of this class.
	 * 
	 * @param l
	 *            The property listener to add.
	 *            
	 * @see #removePropertyChangeListener(PropertyChangeListener)
	 */
	//TODO: Daniel (19.4.2013) Removed, unused
	/*
	public void addPropertyChangeListener(PropertyChangeListener l)
	{
		getPropertyChangeSupport().addPropertyChangeListener(l);
	}
	*/

	/**
	 * Add a property listener to receive property change events for only one
	 * specific property.
	 * 
	 * @param propName
	 *            The property to listen to.
	 * @param l
	 *            The property listener to add.
	 *            
	 * @see #removePropertyChangeListener(String, PropertyChangeListener)
	 */
	//TODO: Daniel (19.4.2013) Removed, unused
	/*
	public void addPropertyChangeListener(String propName, PropertyChangeListener l)
	{
		getPropertyChangeSupport().addPropertyChangeListener(propName, l);
	}
	*/

	/**
	 * Remove a property change listener registered for all properties.
	 * 
	 * This will only remove listeners that were added through the 
	 * {@link #addPropertyChangeListener(PropertyChangeListener) addPropertyChangeListener(PropertyChangeListener)}
	 * method.
	 * 
	 * @param l
	 *            The listener to remove.
	 */
	//TODO: Daniel (19.4.2013) Removed, unused
	/*
	public void removePropertyChangeListener(PropertyChangeListener l)
	{
		getPropertyChangeSupport().removePropertyChangeListener(l);
	}
	*/

	/**
	 * Remove a property change listener registered for a specific property.
	 * 
	 * @param propName
	 *            The property to listen to.
	 * @param l
	 *            The listener to remove.
	 */
	//TODO: Daniel (19.4.2013) Removed, unused
	/*
	public void removePropertyChangeListener(String propName, PropertyChangeListener l)
	{
		getPropertyChangeSupport().removePropertyChangeListener(propName, l);
	}
	*/

	/**
	 * Get the property change support object for this class. Because the
	 * property change support object has to be transient, it may need to be
	 * created.
	 * 
	 * @return The property change support object.
	 */
	protected JRPropertyChangeSupport getPropertyChangeSupport()
	{
		return getEventSupport();
	}
	
	/**
	 * 
	 */
	public Object clone() 
	{
		JRDesignCrosstabMeasure clone = (JRDesignCrosstabMeasure) super.clone();
		// always the same instance
		clone.designVariable = (JRDesignVariable) clone.variable;
		clone.eventSupport = null;
		return clone;
	}

	private transient JRPropertyChangeSupport eventSupport;
	
	public JRPropertyChangeSupport getEventSupport()
	{
		synchronized (this)
		{
			if (eventSupport == null)
			{
				eventSupport = new JRPropertyChangeSupport(this);
			}
		}
		
		return eventSupport;
	}
}
