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

import java.io.Serializable;

import org.oss.pdfreporter.crosstabs.JRCellContents;
import org.oss.pdfreporter.crosstabs.JRCrosstabBucket;
import org.oss.pdfreporter.crosstabs.JRCrosstabGroup;
import org.oss.pdfreporter.crosstabs.type.CrosstabTotalPositionEnum;
import org.oss.pdfreporter.engine.JRRuntimeException;
import org.oss.pdfreporter.engine.JRVariable;
import org.oss.pdfreporter.engine.base.JRBaseObjectFactory;
import org.oss.pdfreporter.engine.util.JRCloneUtils;


/**
 * Base read-only implementation for crosstab row and column groups.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRBaseCrosstabGroup.java 5180 2012-03-29 13:23:12Z teodord $
 */
public abstract class JRBaseCrosstabGroup implements JRCrosstabGroup, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7685014062058258277L;//it's OK to have calculated UID here, because we missed it when first releasing this class
	
	protected String name;
	protected CrosstabTotalPositionEnum totalPositionValue = CrosstabTotalPositionEnum.NONE;
	protected JRCrosstabBucket bucket;
	
	protected JRCellContents header;
	protected JRCellContents totalHeader;

	protected JRVariable variable;
	
	protected JRBaseCrosstabGroup()
	{
	}

	public JRBaseCrosstabGroup(JRCrosstabGroup group, JRBaseObjectFactory factory)
	{
		factory.put(group, this);
		
		this.name = group.getName();
		this.totalPositionValue = group.getTotalPositionValue();
		this.bucket = factory.getCrosstabBucket(group.getBucket());
		
		this.header = factory.getCell(group.getHeader());
		this.totalHeader = factory.getCell(group.getTotalHeader());
		
		this.variable = factory.getVariable(group.getVariable());
	}
	
	public String getName()
	{
		return name;
	}

	public JRCrosstabBucket getBucket()
	{
		return bucket;
	}

	public CrosstabTotalPositionEnum getTotalPositionValue()
	{
		return totalPositionValue;
	}

	public boolean hasTotal()
	{
		return totalPositionValue != CrosstabTotalPositionEnum.NONE;
	}

	public JRCellContents getHeader()
	{
		return header;
	}

	public JRCellContents getTotalHeader()
	{
		return totalHeader;
	}

	public JRVariable getVariable()
	{
		return variable;
	}

	/**
	 * 
	 */
	public Object clone() 
	{
		JRBaseCrosstabGroup clone = null;
		try
		{
			clone = (JRBaseCrosstabGroup) super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			// never
			throw new JRRuntimeException(e);
		}
		clone.bucket = JRCloneUtils.nullSafeClone(bucket);
		clone.header = JRCloneUtils.nullSafeClone(header);
		clone.totalHeader = JRCloneUtils.nullSafeClone(totalHeader);
		clone.variable = JRCloneUtils.nullSafeClone(variable);
		return clone;
	}

	// TODO: Daniel (19.4.2013) - Removed, unused
//	/*
//	 * These fields are only for serialization backward compatibility.
//	 */
//	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID; //NOPMD
//	/**
//	 * @deprecated
//	 */
//	private byte totalPosition;
//	
//	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
//	{
//		in.defaultReadObject();
//		
//		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2)
//		{
//			totalPositionValue = CrosstabTotalPositionEnum.getByValue(totalPosition);
//		}
//	}

}
