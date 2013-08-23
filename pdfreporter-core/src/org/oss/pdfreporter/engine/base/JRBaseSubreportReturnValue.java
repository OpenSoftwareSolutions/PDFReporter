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

import java.io.Serializable;

import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.JRRuntimeException;
import org.oss.pdfreporter.engine.JRSubreportReturnValue;
import org.oss.pdfreporter.engine.type.CalculationEnum;


/**
 * Base implementation of {@link org.oss.pdfreporter.engine.JRSubreportReturnValue JRSubreportReturnValue}.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRBaseSubreportReturnValue.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRBaseSubreportReturnValue implements JRSubreportReturnValue, Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 * The name of the subreport variable to be copied.
	 */
	protected String subreportVariable;

	/**
	 * The name of the master variable where the value should be copied.
	 */
	protected String toVariable;
	
	/**
	 * The calculation type.
	 */
	protected CalculationEnum calculationValue = CalculationEnum.NOTHING;
	
	/**
	 * The incrementer factory class name.
	 */
	protected String incrementerFactoryClassName;

	
	protected JRBaseSubreportReturnValue()
	{
	}

	
	protected JRBaseSubreportReturnValue(JRSubreportReturnValue returnValue, JRBaseObjectFactory factory)
	{
		factory.put(returnValue, this);

		subreportVariable = returnValue.getSubreportVariable();
		toVariable = returnValue.getToVariable();
		calculationValue = returnValue.getCalculationValue();
		incrementerFactoryClassName = returnValue.getIncrementerFactoryClassName();
	}

	/**
	 * Returns the name of the subreport variable whose value should be copied.
	 * 
	 * @return the name of the subreport variable whose value should be copied.
	 */
	public String getSubreportVariable()
	{
		return this.subreportVariable;
	}

	/**
	 * Returns the name of the master report variable where the value should be copied.
	 * 
	 * @return the name of the master report variable where the value should be copied.
	 */
	public String getToVariable()
	{
		return this.toVariable;
	}

	/**
	 * Returns the calculation type.
	 * <p>
	 * When copying the value from the subreport, a formula can be applied such that sum,
	 * maximum, average and so on can be computed.
	 * 
	 * @return the calculation type.
	 */
	public CalculationEnum getCalculationValue()
	{
		return calculationValue;
	}

	/**
	 * Returns the incrementer factory class name.
	 * <p>
	 * The factory will be used to increment the value of the master report variable
	 * with the value from the subreport.
	 * 
	 * @return the incrementer factory class name.
	 */
	public String getIncrementerFactoryClassName()
	{
		return incrementerFactoryClassName;
	}

	//TODO: Daniel (19.4.2013) - Not needed, removed
//	/*
//	 * These fields are only for serialization backward compatibility.
//	 */
//	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2; //NOPMD
//	/**
//	 * @deprecated
//	 */
//	private byte calculation;
//	
//	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
//	{
//		in.defaultReadObject();
//
//		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2)
//		{
//			calculationValue = CalculationEnum.getByValue(calculation);
//		}
//		
//	}

	/**
	 * 
	 */
	public Object clone() 
	{
		try
		{
			return super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			throw new JRRuntimeException(e);
		}
	}
}
