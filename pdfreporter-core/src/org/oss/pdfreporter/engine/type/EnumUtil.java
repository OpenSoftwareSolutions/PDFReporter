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
package org.oss.pdfreporter.engine.type;




/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: EnumUtil.java 5499 2012-07-20 11:34:40Z lucianc $
 */
public final class EnumUtil
{
	
	/**
	 *
	 */
	public static JREnum getByValue(JREnum[] values, Integer value)
	{
		if (values != null && value != null)
		{
			return getByValue(values, new Byte(value.byteValue()));
		}
		return null;
	}

	/**
	 *
	 */
	public static JREnum getByValue(JREnum[] values, Byte value)
	{
		if (values != null && value != null)
		{
			for(JREnum e:values)
			{
				if (value.equals(e.getValueByte()))
				{
					return e;
				}
			}
		}
		return null;
	}
	
	/**
	 *
	 */
	public static JREnum getByName(JREnum[] values, String enumName)
	{
		return EnumUtil.<JREnum>getEnumByName(values, enumName);
	}

	public static <T extends NamedEnum> T getEnumByName(T[] values, String enumName)
	{
		if (values != null && enumName != null)
		{
			for(T e:values)
			{
				if (enumName.equals(e.getName()))
				{
					return e;
				}
			}
		}
		return null;
	}

	/**
	 *
	 */
	public static JREnum getByEnumConstantName(JREnum[] values, String enumName)
	{
		if (values != null && enumName != null)
		{
			for(JREnum e:values)
			{
				if (enumName.equals(((Enum<?>)e).name()))
				{
					return e;
				}
			}
		}
		return null;
	}
	
	/**
	 *
	 */
	private EnumUtil()
	{
	}
}
