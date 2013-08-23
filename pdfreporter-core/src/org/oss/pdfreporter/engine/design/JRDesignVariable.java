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
package org.oss.pdfreporter.engine.design;

import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.JRExpression;
import org.oss.pdfreporter.engine.JRGroup;
import org.oss.pdfreporter.engine.base.JRBaseVariable;
import org.oss.pdfreporter.engine.design.events.JRChangeEventsSupport;
import org.oss.pdfreporter.engine.design.events.JRPropertyChangeSupport;
import org.oss.pdfreporter.engine.type.CalculationEnum;
import org.oss.pdfreporter.engine.type.IncrementTypeEnum;
import org.oss.pdfreporter.engine.type.ResetTypeEnum;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRDesignVariable.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRDesignVariable extends JRBaseVariable implements JRChangeEventsSupport
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	public static final String PROPERTY_CALCULATION = "calculation";
	
	public static final String PROPERTY_EXPRESSION = "expression";
	
	public static final String PROPERTY_INCREMENTER_FACTORY_CLASS_NAME = "incrementerFactoryClassName";
	
	public static final String PROPERTY_INCREMENT_GROUP = "incrementGroup";
	
	public static final String PROPERTY_INCREMENT_TYPE = "incrementType";
	
	public static final String PROPERTY_INITIAL_VALUE_EXPRESSION = "initialValueExpression";
	
	public static final String PROPERTY_NAME = "name";
	
	public static final String PROPERTY_RESET_GROUP = "resetGroup";
	
	public static final String PROPERTY_RESET_TYPE = "resetType";
	
	public static final String PROPERTY_SYSTEM_DEFINED = "systemDefined";
	
	public static final String PROPERTY_VALUE_CLASS_NAME = "valueClassName";

	/**
	 *
	 */
	public void setName(String name)
	{
		String old = this.name;
		this.name = name;
		getEventSupport().firePropertyChange(PROPERTY_NAME, old, this.name);
	}
		
	/**
	 *
	 */
	public void setValueClass(Class<?> clazz)
	{
		setValueClassName(clazz.getName());
	}
		
	/**
	 *
	 */
	public void setValueClassName(String className)
	{
		Object old = this.valueClassName;
		valueClassName = className;
		valueClass = null;
		valueClassRealName = null;
		getEventSupport().firePropertyChange(PROPERTY_VALUE_CLASS_NAME, old, this.valueClassName);
	}
		
	/**
	 *
	 */
	public void setIncrementerFactoryClass(Class<?> clazz)
	{
		setIncrementerFactoryClassName(clazz.getName());
	}
		
	/**
	 *
	 */
	public void setIncrementerFactoryClassName(String className)
	{
		Object old = this.incrementerFactoryClassName;
		incrementerFactoryClassName = className;
		incrementerFactoryClass = null;
		incrementerFactoryClassRealName = null;
		getEventSupport().firePropertyChange(PROPERTY_INCREMENTER_FACTORY_CLASS_NAME, old, this.incrementerFactoryClassName);
	}
		
	/**
	 *
	 */
	public void setResetType(ResetTypeEnum resetTypeValue)
	{
		Object old = this.resetTypeValue;
		this.resetTypeValue = resetTypeValue;
		getEventSupport().firePropertyChange(PROPERTY_RESET_TYPE, old, this.resetTypeValue);
	}
		
	/**
	 *
	 */
	public void setIncrementType(IncrementTypeEnum incrementTypeValue)
	{
		Object old = this.incrementTypeValue;
		this.incrementTypeValue = incrementTypeValue;
		getEventSupport().firePropertyChange(PROPERTY_INCREMENT_TYPE, old, this.incrementTypeValue);
	}
		
	/**
	 *
	 */
	public void setCalculation(CalculationEnum calculationValue)
	{
		CalculationEnum old = this.calculationValue;
		this.calculationValue = calculationValue;
		getEventSupport().firePropertyChange(PROPERTY_CALCULATION, old, this.calculationValue);
	}

	/**
	 *
	 */
	public void setSystemDefined(boolean isSystemDefined)
	{
		boolean old = this.isSystemDefined;
		this.isSystemDefined = isSystemDefined;
		getEventSupport().firePropertyChange(PROPERTY_SYSTEM_DEFINED, old, this.isSystemDefined);
	}

	/**
	 *
	 */
	public void setExpression(JRExpression expression)
	{
		Object old = this.expression;
		this.expression = expression;
		getEventSupport().firePropertyChange(PROPERTY_EXPRESSION, old, this.expression);
	}
		
	/**
	 *
	 */
	public void setInitialValueExpression(JRExpression expression)
	{
		Object old = this.initialValueExpression;
		this.initialValueExpression = expression;
		getEventSupport().firePropertyChange(PROPERTY_INITIAL_VALUE_EXPRESSION, old, this.initialValueExpression);
	}
	
	/**
	 *
	 */
	public void setResetGroup(JRGroup group)
	{
		Object old = this.resetGroup;
		this.resetGroup = group;
		getEventSupport().firePropertyChange(PROPERTY_RESET_GROUP, old, this.resetGroup);
	}
		
	/**
	 *
	 */
	public void setIncrementGroup(JRGroup group)
	{
		Object old = this.incrementGroup;
		this.incrementGroup = group;
		getEventSupport().firePropertyChange(PROPERTY_INCREMENT_GROUP, old, this.incrementGroup);
	}
	
	/**
	 * 
	 */
	public Object clone()
	{
		JRDesignVariable clone = (JRDesignVariable)super.clone();
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
