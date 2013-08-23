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

import java.io.Serializable;

import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.JRHyperlinkHelper;
import org.oss.pdfreporter.engine.JRPrintHyperlink;
import org.oss.pdfreporter.engine.JRPrintHyperlinkParameter;
import org.oss.pdfreporter.engine.JRPrintHyperlinkParameters;
import org.oss.pdfreporter.engine.type.HyperlinkTargetEnum;
import org.oss.pdfreporter.engine.type.HyperlinkTypeEnum;



/**
 * Stand-alone implementation of {@link JRPrintHyperlink JRPrintHyperlink}.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRBasePrintHyperlink.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRBasePrintHyperlink implements JRPrintHyperlink, Serializable
{
	
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	private String linkType;
	private String linkTarget;
	private String hyperlinkReference;
	private String hyperlinkAnchor;
	private Integer hyperlinkPage;
	private String hyperlinkTooltip;
	private JRPrintHyperlinkParameters hyperlinkParameters;

	
	/**
	 * Creates a blank hyperlink.
	 */
	public JRBasePrintHyperlink()
	{
	}
	
	public String getHyperlinkAnchor()
	{
		return hyperlinkAnchor;
	}

	public Integer getHyperlinkPage()
	{
		return hyperlinkPage;
	}

	public JRPrintHyperlinkParameters getHyperlinkParameters()
	{
		return hyperlinkParameters;
	}

	public String getHyperlinkReference()
	{
		return hyperlinkReference;
	}

	public HyperlinkTargetEnum getHyperlinkTargetValue()
	{
		return JRHyperlinkHelper.getHyperlinkTargetValue(getLinkTarget());
	}

	public HyperlinkTypeEnum getHyperlinkTypeValue()
	{
		return JRHyperlinkHelper.getHyperlinkTypeValue(getLinkType());
	}

	public String getLinkType()
	{
		return linkType;
	}

	public String getLinkTarget()
	{
		return linkTarget;
	}

	public void setHyperlinkAnchor(String hyperlinkAnchor)
	{
		this.hyperlinkAnchor = hyperlinkAnchor;
	}

	public void setHyperlinkPage(Integer hyperlinkPage)
	{
		this.hyperlinkPage = hyperlinkPage;
	}

	public void setHyperlinkParameters(JRPrintHyperlinkParameters parameters)
	{
		this.hyperlinkParameters = parameters;
	}

	public void setHyperlinkReference(String hyperlinkReference)
	{
		this.hyperlinkReference = hyperlinkReference;
	}

	public void setHyperlinkTarget(HyperlinkTargetEnum hyperlinkTarget)
	{
		setLinkTarget(JRHyperlinkHelper.getLinkTarget(hyperlinkTarget));
	}

	public void setLinkTarget(String linkTarget)
	{
		this.linkTarget = linkTarget;
	}

	public void setHyperlinkType(HyperlinkTypeEnum hyperlinkType)
	{
		setLinkType(JRHyperlinkHelper.getLinkType(hyperlinkType));
	}

	public void setLinkType(String type)
	{
		this.linkType = type;
	}

	
	/**
	 * Adds a custom hyperlink parameter.
	 * 
	 * @param parameter the parameter to add
	 * @see #getHyperlinkParameters()
	 * @see JRPrintHyperlinkParameters#addParameter(JRPrintHyperlinkParameter)
	 */
	public void addHyperlinkParameter(JRPrintHyperlinkParameter parameter)
	{
		if (hyperlinkParameters == null)
		{
			hyperlinkParameters = new JRPrintHyperlinkParameters();
		}
		hyperlinkParameters.addParameter(parameter);
	}

	
	public String getHyperlinkTooltip()
	{
		return hyperlinkTooltip;
	}

	
	public void setHyperlinkTooltip(String hyperlinkTooltip)
	{
		this.hyperlinkTooltip = hyperlinkTooltip;
	}

	//TODO: Daniel (19.4.2013) - Not needed, removed
//	/*
//	 * These fields are only for serialization backward compatibility.
//	 */
//	/**
//	 * @deprecated
//	 */
//	private byte hyperlinkTarget;
//	
//	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
//	{
//		in.defaultReadObject();
//
//		if (linkTarget == null)
//		{
//			 linkTarget = JRHyperlinkHelper.getLinkTarget(HyperlinkTargetEnum.getByValue(hyperlinkTarget));
//		}
//	}
	
}
