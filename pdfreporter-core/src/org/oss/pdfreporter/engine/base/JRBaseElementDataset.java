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
import org.oss.pdfreporter.engine.JRDatasetRun;
import org.oss.pdfreporter.engine.JRElementDataset;
import org.oss.pdfreporter.engine.JRExpression;
import org.oss.pdfreporter.engine.JRGroup;
import org.oss.pdfreporter.engine.JRRuntimeException;
import org.oss.pdfreporter.engine.type.IncrementTypeEnum;
import org.oss.pdfreporter.engine.type.ResetTypeEnum;
import org.oss.pdfreporter.engine.util.JRCloneUtils;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRBaseElementDataset.java 5180 2012-03-29 13:23:12Z teodord $
 */
public abstract class JRBaseElementDataset implements JRElementDataset, Serializable
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	protected ResetTypeEnum resetTypeValue = ResetTypeEnum.REPORT;
	protected IncrementTypeEnum incrementTypeValue = IncrementTypeEnum.NONE;
	protected JRGroup resetGroup;
	protected JRGroup incrementGroup;
	
	protected JRDatasetRun datasetRun;
	protected JRExpression incrementWhenExpression;

	
	protected JRBaseElementDataset()
	{
	}

	
	/**
	 *
	 */
	protected JRBaseElementDataset(JRElementDataset dataset)
	{
		if (dataset != null) {
			resetTypeValue = dataset.getResetTypeValue();
			incrementTypeValue = dataset.getIncrementTypeValue();
			resetGroup = dataset.getResetGroup();
			incrementGroup = dataset.getIncrementGroup();
			datasetRun = dataset.getDatasetRun();
			incrementWhenExpression = dataset.getIncrementWhenExpression();
		}
	}


	/**
	 *
	 */
	protected JRBaseElementDataset(JRElementDataset dataset, JRBaseObjectFactory factory)
	{
		factory.put(dataset, this);

		resetTypeValue = dataset.getResetTypeValue();
		incrementTypeValue = dataset.getIncrementTypeValue();
		resetGroup = factory.getGroup(dataset.getResetGroup());
		incrementGroup = factory.getGroup(dataset.getIncrementGroup());
		
		datasetRun = factory.getDatasetRun(dataset.getDatasetRun());
		incrementWhenExpression = factory.getExpression(dataset.getIncrementWhenExpression());
	}

	
	/**
	 *
	 */
	public ResetTypeEnum getResetTypeValue()
	{
		return this.resetTypeValue;
	}
		
	/**
	 *
	 */
	public IncrementTypeEnum getIncrementTypeValue()
	{
		return this.incrementTypeValue;
	}
		
	/**
	 *
	 */
	public JRGroup getResetGroup()
	{
		return resetGroup;
	}
		
	/**
	 *
	 */
	public JRGroup getIncrementGroup()
	{
		return incrementGroup;
	}


	public JRDatasetRun getDatasetRun()
	{
		return datasetRun;
	}


	public JRExpression getIncrementWhenExpression()
	{
		return incrementWhenExpression;
	}
	
	//TODO: Daniel (19.4.2013) - Not needed, removed
//	/*
//	 * These fields are only for serialization backward compatibility.
//	 */
//	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2; //NOPMD
//	/**
//	 * @deprecated
//	 */
//	private byte resetType;
//	/**
//	 * @deprecated
//	 */
//	private byte incrementType;
//	
//	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
//	{
//		in.defaultReadObject();
//
//		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2)
//		{
//			resetTypeValue = ResetTypeEnum.getByValue(resetType);
//			incrementTypeValue = IncrementTypeEnum.getByValue(incrementType);
//		}
//		
//	}

	
	/**
	 *
	 */
	public Object clone() 
	{
		JRBaseElementDataset clone = null;

		try
		{
			clone = (JRBaseElementDataset)super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			throw new JRRuntimeException(e);
		}

		clone.incrementWhenExpression = JRCloneUtils.nullSafeClone(incrementWhenExpression);
		clone.datasetRun = JRCloneUtils.nullSafeClone(datasetRun);
		
		return clone;
	}

	
}
