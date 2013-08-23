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
package org.oss.pdfreporter.engine.util;

import org.oss.pdfreporter.engine.type.ColorEnum;
import org.oss.pdfreporter.geometry.IColor;
import org.oss.pdfreporter.registry.ApiRegistry;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRColorUtil.java 5180 2012-03-29 13:23:12Z teodord $
 */
public final class JRColorUtil
{

	/**
	 *
	 */
	public static final int COLOR_MASK = Integer.parseInt("FFFFFF", 16);

	/**
	 *
	 */
	public static String getColorHexa(IColor color)
	{
		String hexa = Integer.toHexString(color.getRGB() & COLOR_MASK).toUpperCase();
		return ("000000" + hexa).substring(hexa.length());
	}

	/**
	 *
	 */
	public static IColor getColor(String strColor, IColor defaultColor)
	{
		IColor color = null;

		if (strColor != null && strColor.length() > 0)
		{
			char firstChar = strColor.charAt(0);
			if (firstChar == '#')
			{
				color = ApiRegistry.getGeometryFactory().newColor(Integer.parseInt(strColor.substring(1), 16));
			}
			else if ('0' <= firstChar && firstChar <= '9')
			{
				color = ApiRegistry.getGeometryFactory().newColor(Integer.parseInt(strColor));
			}
			else
			{
				ColorEnum colorEnum = ColorEnum.getByName(strColor);
				if (colorEnum == null)
				{
					color = defaultColor;
				}
				else
				{
					color = colorEnum.getColor();
				}
			}
		}

		return color;
	}


	private JRColorUtil()
	{
	}
}
