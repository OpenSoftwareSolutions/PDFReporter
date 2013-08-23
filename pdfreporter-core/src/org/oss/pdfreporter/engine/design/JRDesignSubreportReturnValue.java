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
import org.oss.pdfreporter.engine.base.JRBaseSubreportReturnValue;
import org.oss.pdfreporter.engine.design.events.JRChangeEventsSupport;
import org.oss.pdfreporter.engine.design.events.JRPropertyChangeSupport;
import org.oss.pdfreporter.engine.type.CalculationEnum;

/**
 * Implementation of {@link org.oss.pdfreporter.engine.JRSubreportReturnValue JRSubreportReturnValue}
 * to be used for report desing purposes.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRDesignSubreportReturnValue.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRDesignSubreportReturnValue extends JRBaseSubreportReturnValue implements JRChangeEventsSupport
{

	/**
	 * 
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	public static final String PROPERTY_CALCULATION = "calculation";
	
	public static final String PROPERTY_INCREMENTER_FACTORY_CLASS_NAME = "incrementerFactoryClassName";
	
	public static final String PROPERTY_SUBREPORT_VARIABLE = "subreportVariable";
	
	public static final String PROPERTY_TO_VARIABLE = "toVariable";

	/**
	 * Sets the subreport variable name.
	 * 
	 * @param name the variable name
	 * @see org.oss.pdfreporter.engine.JRSubreportReturnValue#getSubreportVariable()
	 */
	public void setSubreportVariable(String name)
	{
		Object old = this.subreportVariable;
		this.subreportVariable = name;
		getEventSupport().firePropertyChange(PROPERTY_SUBREPORT_VARIABLE, old, this.subreportVariable);
	}

	/**
	 * Sets the master variable name.
	 * 
	 * @param name the variable name
	 * @see org.oss.pdfreporter.engine.JRSubreportReturnValue#getToVariable()
	 */
	public void setToVariable(String name)
	{
		Object old = this.toVariable;
		this.toVariable = name;
		getEventSupport().firePropertyChange(PROPERTY_TO_VARIABLE, old, this.toVariable);
	}

	/**
	 * Sets the calculation type.
	 * 
	 * @param calculationValue the calculation type
	 * @see org.oss.pdfreporter.engine.JRSubreportReturnValue#getCalculationValue()
	 */
	public void setCalculation(CalculationEnum calculationValue)
	{
		CalculationEnum old = this.calculationValue;
		this.calculationValue = calculationValue;
		getEventSupport().firePropertyChange(PROPERTY_CALCULATION, old, this.calculationValue);
	}
	
	/**
	 * Sets the incrementer factory class name.
	 * 
	 * @param incrementerFactoryClassName the name of the incrementer factory class
	 * @see org.oss.pdfreporter.engine.JRSubreportReturnValue#getIncrementerFactoryClassName()
	 */
	public void setIncrementerFactoryClassName(String incrementerFactoryClassName)
	{
		Object old = this.incrementerFactoryClassName;
		this.incrementerFactoryClassName = incrementerFactoryClassName;
		getEventSupport().firePropertyChange(PROPERTY_INCREMENTER_FACTORY_CLASS_NAME, old, this.incrementerFactoryClassName);
	}
	
	/**
	 * 
	 */
	public Object clone()
	{
		JRDesignSubreportReturnValue clone = (JRDesignSubreportReturnValue)super.clone();
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
