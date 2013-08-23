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
package org.oss.pdfreporter.crosstabs.type;

import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.type.EnumUtil;
import org.oss.pdfreporter.engine.type.JREnum;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: CrosstabTotalPositionEnum.java 4595 2011-09-08 15:55:10Z teodord $
 */
public enum CrosstabTotalPositionEnum implements JREnum
{
	/**
	 * Constant indicating that total are not required for this bucket.
	 */
	NONE((byte)0, "None"),
	
	/**
	 * Constants indicating that totals are to be positioned before the other buckets.
	 */
	START((byte)1, "Start"),
	
	/**
	 * Constants indicating that totals are to be positioned at the end of the other buckets.
	 */
	END((byte)2, "End");

	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private final transient byte value;
	private final transient String name;

	private CrosstabTotalPositionEnum(byte value, String enumName)
	{
		this.value = value;
		this.name = enumName;
	}

	/**
	 *
	 */
	public Byte getValueByte()
	{
		return new Byte(value);
	}
	
	/**
	 *
	 */
	public final byte getValue()
	{
		return value;
	}
	
	/**
	 *
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 *
	 */
	public static CrosstabTotalPositionEnum getByName(String enumName)
	{
		return (CrosstabTotalPositionEnum)EnumUtil.getByName(values(), enumName);
	}
	
	/**
	 *
	 */
	public static CrosstabTotalPositionEnum getByValue(Byte enumName)
	{
		return (CrosstabTotalPositionEnum)EnumUtil.getByValue(values(), enumName);
	}
	
	/**
	 *
	 */
	public static CrosstabTotalPositionEnum getByValue(byte value)
	{
		return getByValue(new Byte(value));
	}
	
}
