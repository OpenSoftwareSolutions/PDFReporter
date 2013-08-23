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

import org.oss.pdfreporter.crosstabs.JRCrosstabRowGroup;
import org.oss.pdfreporter.crosstabs.type.CrosstabRowPositionEnum;
import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.base.JRBaseObjectFactory;

/**
 * Base read-only implementation of crosstab row groups.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRBaseCrosstabRowGroup.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRBaseCrosstabRowGroup extends JRBaseCrosstabGroup implements JRCrosstabRowGroup
{
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	protected int width;
	protected CrosstabRowPositionEnum positionValue = CrosstabRowPositionEnum.TOP;

	public JRBaseCrosstabRowGroup(JRCrosstabRowGroup group, JRBaseObjectFactory factory)
	{
		super(group, factory);

		width = group.getWidth();
		positionValue = group.getPositionValue();
	}

	public CrosstabRowPositionEnum getPositionValue()
	{
		return positionValue;
	}

	public int getWidth()
	{
		return width;
	}

	// TODO: Daniel (19.4.2013) - Removed, unused
//	/*
//	 * These fields are only for serialization backward compatibility.
//	 */
//	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID; //NOPMD
//	/**
//	 * @deprecated
//	 */
//	private byte position;
//	
//	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
//	{
//		in.defaultReadObject();
//		
//		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2)
//		{
//			positionValue = CrosstabRowPositionEnum.getByValue(position);
//		}
//	}

}
