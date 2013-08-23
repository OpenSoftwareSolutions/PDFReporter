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

import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.geometry.IColor;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: ColorEnum.java 4595 2011-09-08 15:55:10Z teodord $
 */
public enum ColorEnum
{
	/**
	 *
	 */
	BLACK(IColor.black, "black"),

	/**
	 *
	 */
	BLUE(IColor.blue, "blue"),

	/**
	 *
	 */
	CYAN(IColor.cyan, "cyan"),

	/**
	 *
	 */
	DARK_GRAY(IColor.darkGray, "darkGray"),

	/**
	 *
	 */
	GRAY(IColor.gray, "gray"),

	/**
	 *
	 */
	GREEN(IColor.green, "green"),

	/**
	 *
	 */
	LIGHT_GRAY(IColor.lightGray, "lightGray"),

	/**
	 *
	 */
	MAGENTA(IColor.magenta, "magenta"),

	/**
	 *
	 */
	ORANGE(IColor.orange, "orange"),

	/**
	 *
	 */
	PINK(IColor.pink, "pink"),

	/**
	 *
	 */
	RED(IColor.red, "red"),

	/**
	 *
	 */
	YELLOW(IColor.yellow, "yellow"),

	/**
	 *
	 */
	WHITE(IColor.white, "white");

	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private final transient IColor color;
	private final transient String name;

	private ColorEnum(IColor color, String enumName)
	{
		this.color = color;
		this.name = enumName;
	}

	/**
	 *
	 */
	public final IColor getColor()
	{
		return color;
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
	public static ColorEnum getByName(String enumName)
	{
		ColorEnum[] values = values();
		if (values != null && enumName != null)
		{
			for(ColorEnum e:values)
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
	public static ColorEnum getByColor(IColor color)
	{
		ColorEnum[] values = values();
		if (values != null && color != null)
		{
			for(ColorEnum e:values)
			{
				if (color.equals(e.getColor()))
				{
					return e;
				}
			}
		}
		return null;
	}
	
}
