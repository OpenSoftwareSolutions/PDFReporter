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


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.oss.pdfreporter.engine.JRChild;
import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.JRElement;
import org.oss.pdfreporter.engine.JRExpressionCollector;
import org.oss.pdfreporter.engine.JRFrame;
import org.oss.pdfreporter.engine.JRLineBox;
import org.oss.pdfreporter.engine.JRVisitor;
import org.oss.pdfreporter.engine.type.ModeEnum;
import org.oss.pdfreporter.engine.util.JRStyleResolver;
import org.oss.pdfreporter.geometry.IColor;


/**
 * Base read-only implementation of {@link org.oss.pdfreporter.engine.JRFrame JRFrame}.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRBaseFrame.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRBaseFrame extends JRBaseElement implements JRFrame
{
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	protected List<JRChild> children;

	protected JRLineBox lineBox;


	public JRBaseFrame(JRFrame frame, JRBaseObjectFactory factory)
	{
		super(frame, factory);

		List<JRChild> frameChildren = frame.getChildren();
		if (frameChildren != null)
		{
			children = new ArrayList<JRChild>(frameChildren.size());
			for (Iterator<JRChild> it = frameChildren.iterator(); it.hasNext();)
			{
				JRChild child = it.next();
				children.add((JRChild)factory.getVisitResult(child));
			}
		}
		
		lineBox = frame.getLineBox().clone(this);
	}

	public JRElement[] getElements()
	{
		return JRBaseElementGroup.getElements(children);
	}

	public void collectExpressions(JRExpressionCollector collector)
	{
		collector.collect(this);
	}

	/**
	 *
	 */
	public void visit(JRVisitor visitor)
	{
		visitor.visitFrame(this);
	}
	
	public List<JRChild> getChildren()
	{
		return children;
	}

	public JRElement getElementByKey(String elementKey)
	{
		return JRBaseElementGroup.getElementByKey(getElements(), elementKey);
	}
	
	public ModeEnum getModeValue()
	{
		return JRStyleResolver.getMode(this, ModeEnum.TRANSPARENT);
	}


	/**
	 *
	 */
	public JRLineBox getLineBox()
	{
		return lineBox;
	}

	/**
	 * 
	 */
	public IColor getDefaultLineColor() 
	{
		return getForecolor();
	}

	//TODO: Daniel (19.4.2013) - Not needed, removed
//	/*
//	 * These fields are only for serialization backward compatibility.
//	 */
//	/**
//	 * @deprecated
//	 */
//	private Byte border;
//	/**
//	 * @deprecated
//	 */
//	private Byte topBorder;
//	/**
//	 * @deprecated
//	 */
//	private Byte leftBorder;
//	/**
//	 * @deprecated
//	 */
//	private Byte bottomBorder;
//	/**
//	 * @deprecated
//	 */
//	private Byte rightBorder;
//	/**
//	 * @deprecated
//	 */
//	private IColor borderColor;
//	/**
//	 * @deprecated
//	 */
//	private IColor topBorderColor;
//	/**
//	 * @deprecated
//	 */
//	private IColor leftBorderColor;
//	/**
//	 * @deprecated
//	 */
//	private IColor bottomBorderColor;
//	/**
//	 * @deprecated
//	 */
//	private IColor rightBorderColor;
//	/**
//	 * @deprecated
//	 */
//	private Integer padding;
//	/**
//	 * @deprecated
//	 */
//	private Integer topPadding;
//	/**
//	 * @deprecated
//	 */
//	private Integer leftPadding;
//	/**
//	 * @deprecated
//	 */
//	private Integer bottomPadding;
//	/**
//	 * @deprecated
//	 */
//	private Integer rightPadding;
//	
//	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
//	{
//		in.defaultReadObject();
//
//		if (lineBox == null)
//		{
//			lineBox = new JRBaseLineBox(this);
//			JRBoxUtil.setToBox(
//				border,
//				topBorder,
//				leftBorder,
//				bottomBorder,
//				rightBorder,
//				borderColor,
//				topBorderColor,
//				leftBorderColor,
//				bottomBorderColor,
//				rightBorderColor,
//				padding,
//				topPadding,
//				leftPadding,
//				bottomPadding,
//				rightPadding,
//				lineBox
//				);
//			border = null;
//			topBorder = null;
//			leftBorder = null;
//			bottomBorder = null;
//			rightBorder = null;
//			borderColor = null;
//			topBorderColor = null;
//			leftBorderColor = null;
//			bottomBorderColor = null;
//			rightBorderColor = null;
//			padding = null;
//			topPadding = null;
//			leftPadding = null;
//			bottomPadding = null;
//			rightPadding = null;
//		}
//	}

	@Override
	public Object clone()
	{
		JRBaseFrame clone = (JRBaseFrame) super.clone();
		
		if (children != null)
		{
			clone.children = new ArrayList<JRChild>(children.size());
			for(int i = 0; i < children.size(); i++)
			{
				clone.children.add((JRChild)(children.get(i).clone(clone)));
			}
		}
		
		return clone;
	}
}
