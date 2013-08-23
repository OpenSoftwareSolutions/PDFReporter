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



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRClassLoader.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRClassLoader
{

	
	/**
	 *
	 */
	protected JRClassLoader()
	{
		super();
	}



	/**
	 *
	 */
	public static Class<?> loadClassForName(String className) throws ClassNotFoundException
	{
		Class<?> clazz = null;

		String classRealName = className;
		ClassNotFoundException initialEx = null;

		try
		{
			clazz = loadClassForRealName(classRealName);
		}
		catch (ClassNotFoundException e)
		{
			initialEx = e;
		}
		
		int lastDotIndex = 0;
		while (clazz == null && (lastDotIndex = classRealName.lastIndexOf('.')) > 0)
		{
			classRealName = 
				classRealName.substring(0, lastDotIndex) + "$" + classRealName.substring(lastDotIndex + 1);
			try
			{
				clazz = loadClassForRealName(classRealName);
			}
			catch (ClassNotFoundException e)
			{
			}
		}
		
		if (clazz == null)
		{
			throw initialEx;
		}
		
		return clazz;
	}


	/**
	 *
	 */
	public static Class<?> loadClassForRealName(String className) throws ClassNotFoundException
	{
		Class<?> clazz = Class.forName(className);
		if (clazz==null) {
			throw new ClassNotFoundException(className);
		}
		return clazz;
	}







	/**
	 *
	 */
	public static String getClassRealName(String className)
	{
		if (className == null)
		{
			return null;
		}
		
		int arrayDimension = 0;
		int classNameEnd = className.length();
		int index = 0;
		int pos = 0;
		while (index < classNameEnd && (pos = className.indexOf('[', index)) >= 0)
		{
			if (index == 0)
			{
				classNameEnd = pos;
			}
			index = pos;
			arrayDimension++;
		}

		if (arrayDimension > 0)
		{
			StringBuffer sbuffer = new StringBuffer();
			
			for(int i = 0; i < arrayDimension; i++)
			{
				sbuffer.append('[');
			}
			
			sbuffer.append('L');
			sbuffer.append(className.substring(0, classNameEnd));
			sbuffer.append(';');

			return sbuffer.toString();
		}
		
		return className;
	}


}
