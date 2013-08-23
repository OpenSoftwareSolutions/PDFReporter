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
