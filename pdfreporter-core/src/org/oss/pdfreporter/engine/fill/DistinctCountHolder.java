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
package org.oss.pdfreporter.engine.fill;

import java.util.HashSet;
import java.util.Set;

public class DistinctCountHolder {
	private Set<Object> distinctValues;
	private Object lastValue;

	public DistinctCountHolder()
	{
		distinctValues = new HashSet<Object>();
	}

	public DistinctCountHolder(Set<Object> distinctValues)
	{
		this.distinctValues = distinctValues;
	}

	public DistinctCountHolder(DistinctCountHolder holder, Object lastValue)
	{
		this.distinctValues = holder.getDistinctValues();
		this.lastValue = lastValue;
	}

	public void init()
	{
		distinctValues = new HashSet<Object>();
	}

	public Set<Object> getDistinctValues()
	{
		return distinctValues;
	}

	public Object getLastValue()
	{
		return lastValue;
	}

	public void addLastValue()
	{
		if (lastValue != null)
		{
			distinctValues.add(lastValue);
		}
		lastValue = null;
	}

	public long getCount()
	{
		return distinctValues.size() + (lastValue == null || distinctValues.contains(lastValue) ? 0 : 1);
	}

}
