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

import org.oss.pdfreporter.engine.JRAlignment;
import org.oss.pdfreporter.engine.JRCommonImage;
import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.JRDefaultStyleProvider;
import org.oss.pdfreporter.engine.JRHyperlinkHelper;
import org.oss.pdfreporter.engine.JRImage;
import org.oss.pdfreporter.engine.JRLineBox;
import org.oss.pdfreporter.engine.JROrigin;
import org.oss.pdfreporter.engine.JRPen;
import org.oss.pdfreporter.engine.base.JRBaseLineBox;
import org.oss.pdfreporter.engine.base.JRBasePen;
import org.oss.pdfreporter.engine.type.HorizontalAlignEnum;
import org.oss.pdfreporter.engine.type.HyperlinkTargetEnum;
import org.oss.pdfreporter.engine.type.HyperlinkTypeEnum;
import org.oss.pdfreporter.engine.type.ModeEnum;
import org.oss.pdfreporter.engine.type.OnErrorTypeEnum;
import org.oss.pdfreporter.engine.type.ScaleImageEnum;
import org.oss.pdfreporter.engine.type.VerticalAlignEnum;
import org.oss.pdfreporter.engine.util.JRStyleResolver;
import org.oss.pdfreporter.engine.util.ObjectUtils;
import org.oss.pdfreporter.geometry.IColor;



/**
 * Image information shared by multiple print image objects.
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRTemplateImage.java 5180 2012-03-29 13:23:12Z teodord $
 * @see JRTemplatePrintImage
 */
public class JRTemplateImage extends JRTemplateGraphicElement implements JRAlignment, JRCommonImage
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 *
	 */
	private ScaleImageEnum scaleImageValue;
	private Boolean isUsingCache = Boolean.TRUE;
	private HorizontalAlignEnum horizontalAlignmentValue;
	private VerticalAlignEnum verticalAlignmentValue;
	protected boolean isLazy;
	protected OnErrorTypeEnum onErrorTypeValue = OnErrorTypeEnum.ERROR;
	private String linkType;
	private String linkTarget;

	/**
	 *
	 */
	private JRLineBox lineBox;
	

	/**
	 *
	 */
	protected JRTemplateImage(JROrigin origin, JRDefaultStyleProvider defaultStyleProvider, JRImage image)
	{
		super(origin, defaultStyleProvider);
		
		setImage(image);
	}

	/**
	 * Creates a template image.
	 * 
	 * @param origin the origin of the elements that will use this template
	 * @param defaultStyleProvider the default style provider to use for
	 * this template
	 */
	public JRTemplateImage(JROrigin origin, JRDefaultStyleProvider defaultStyleProvider)
	{
		super(origin, defaultStyleProvider);
		
		this.lineBox = new JRBaseLineBox(this);
		this.linePen = new JRBasePen(this);
	}

	/**
	 *
	 */
	protected void setImage(JRImage image)
	{
		super.setGraphicElement(image);
		
		lineBox = image.getLineBox().clone(this);

		setScaleImage(image.getScaleImageValue());
		setUsingCache(image.getUsingCache());
		setHorizontalAlignment(image.getHorizontalAlignmentValue());
		setVerticalAlignment(image.getVerticalAlignmentValue());
		setLazy(image.isLazy());
		setOnErrorType(image.getOnErrorTypeValue());
		setLinkType(image.getLinkType());
		setLinkTarget(image.getLinkTarget());
	}

	
	/**
	 * Copies box attributes.
	 * 
	 * @param box the object to copy attributes from
	 */
	public void copyLineBox(JRLineBox box)
	{
		this.lineBox = box.clone(this);
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
	public ModeEnum getModeValue()
	{
		return JRStyleResolver.getMode(this, ModeEnum.TRANSPARENT);
	}
		
	/**
	 * 
	 */
	public ScaleImageEnum getScaleImageValue()
	{
		return JRStyleResolver.getScaleImageValue(this);
	}

	/**
	 * 
	 */
	public ScaleImageEnum getOwnScaleImageValue()
	{
		return this.scaleImageValue;
	}

	/**
	 * 
	 */
	public void setScaleImage(ScaleImageEnum scaleImageValue)
	{
		this.scaleImageValue = scaleImageValue;
	}

	/**
	 *
	 */
	public boolean isUsingCache()
	{
		return isUsingCache == null ? true : isUsingCache.booleanValue();
	}

	/**
	 *
	 */
	public void setUsingCache(boolean isUsingCache)
	{
		this.isUsingCache = (isUsingCache ? Boolean.TRUE : Boolean.FALSE);
	}

	/**
	 *
	 */
	public void setUsingCache(Boolean isUsingCache)
	{
		this.isUsingCache = isUsingCache;
	}

	/**
	 *
	 */
	public HorizontalAlignEnum getHorizontalAlignmentValue()
	{
		return JRStyleResolver.getHorizontalAlignmentValue(this);
	}
		
	/**
	 *
	 */
	public HorizontalAlignEnum getOwnHorizontalAlignmentValue()
	{
		return horizontalAlignmentValue;
	}
		
	/**
	 *
	 */
	public void setHorizontalAlignment(HorizontalAlignEnum horizontalAlignmentValue)
	{
		this.horizontalAlignmentValue = horizontalAlignmentValue;
	}
		
	/**
	 *
	 */
	public VerticalAlignEnum getVerticalAlignmentValue()
	{
		return JRStyleResolver.getVerticalAlignmentValue(this);
	}
		
	/**
	 *
	 */
	public VerticalAlignEnum getOwnVerticalAlignmentValue()
	{
		return verticalAlignmentValue;
	}
		
	/**
	 *
	 */
	public void setVerticalAlignment(VerticalAlignEnum verticalAlignmentValue)
	{
		this.verticalAlignmentValue = verticalAlignmentValue;
	}
		
	/**
	 *
	 */
	public boolean isLazy()
	{
		return isLazy;
	}

	/**
	 *
	 */
	public void setLazy(boolean isLazy)
	{
		this.isLazy = isLazy;
	}

	/**
	 *
	 */
	public OnErrorTypeEnum getOnErrorTypeValue()
	{
		return this.onErrorTypeValue;
	}

	/**
	 *
	 */
	public void setOnErrorType(OnErrorTypeEnum onErrorTypeValue)
	{
		this.onErrorTypeValue = onErrorTypeValue;
	}

	
	/**
	 * Retrieves the hyperlink type for the element.
	 * <p>
	 * The actual hyperlink type is determined by {@link #getLinkType() getLinkType()}.
	 * This method can is used to determine whether the hyperlink type is one of the
	 * built-in types or a custom type. 
	 * When hyperlink is of custom type, {@link HyperlinkTypeEnum#CUSTOM CUSTOM} is returned.
	 * </p>
	 * @return one of the hyperlink type constants
	 * @see #getLinkType()
	 */
	public HyperlinkTypeEnum getHyperlinkTypeValue()
	{
		return JRHyperlinkHelper.getHyperlinkTypeValue(getLinkType());
	}

	
	/**
	 * Sets the link type as a built-in hyperlink type.
	 * 
	 * @param hyperlinkType the built-in hyperlink type
	 * @see #getLinkType()
	 */
	protected void setHyperlinkType(HyperlinkTypeEnum hyperlinkType)
	{
		setLinkType(JRHyperlinkHelper.getLinkType(hyperlinkType));
	}
		
	
	/**
	 *
	 */
	public HyperlinkTargetEnum getHyperlinkTargetValue()
	{
		return JRHyperlinkHelper.getHyperlinkTargetValue(getLinkTarget());
	}
		
	/**
	 *
	 */
	protected void setHyperlinkTarget(HyperlinkTargetEnum hyperlinkTarget)
	{
		setLinkTarget(JRHyperlinkHelper.getLinkTarget(hyperlinkTarget));
	}

	
	/**
	 * Returns the hyperlink target name.
	 * <p>
	 * The target name can be one of the built-in names
	 * (Self, Blank, Top, Parent),
	 * or can be an arbitrary name.
	 * </p>
	 * @return the hyperlink type
	 */
	public String getLinkTarget()
	{
		return linkTarget;
	}


	/**
	 * Sets the hyperlink target name.
	 * <p>
	 * The target name can be one of the built-in names
	 * (Self, Blank, Top, Parent),
	 * or can be an arbitrary name.
	 * </p>
	 * @param linkTarget the hyperlink target name
	 */
	public void setLinkTarget(String linkTarget)
	{
		this.linkTarget = linkTarget;
	}
	/**
	 * Returns the hyperlink type.
	 * <p>
	 * The type can be one of the built-in types
	 * (Reference, LocalAnchor, LocalPage, RemoteAnchor, RemotePage),
	 * or can be an arbitrary type.
	 * </p>
	 * @return the hyperlink type
	 */
	public String getLinkType()
	{
		return linkType;
	}


	/**
	 * Sets the hyperlink type.
	 * <p>
	 * The type can be one of the built-in types
	 * (Reference, LocalAnchor, LocalPage, RemoteAnchor, RemotePage),
	 * or can be an arbitrary type.
	 * </p>
	 * @param linkType the hyperlink type
	 */
	public void setLinkType(String linkType)
	{
		this.linkType = linkType;
	}
	
	
	/**
	 * 
	 */
	public Float getDefaultLineWidth() 
	{
		return JRPen.LINE_WIDTH_0;
	}
	
	
	/*
	 * These fields are only for serialization backward compatibility.
	 */
	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID; //NOPMD
	/**
	 * @deprecated
	 */
	private Byte horizontalAlignment;
	/**
	 * @deprecated
	 */
	private Byte verticalAlignment;
	/**
	 * @deprecated
	 */
	private Byte border;
	/**
	 * @deprecated
	 */
	private Byte topBorder;
	/**
	 * @deprecated
	 */
	private Byte leftBorder;
	/**
	 * @deprecated
	 */
	private Byte bottomBorder;
	/**
	 * @deprecated
	 */
	private Byte rightBorder;
	/**
	 * @deprecated
	 */
	private IColor borderColor;
	/**
	 * @deprecated
	 */
	private IColor topBorderColor;
	/**
	 * @deprecated
	 */
	private IColor leftBorderColor;
	/**
	 * @deprecated
	 */
	private IColor bottomBorderColor;
	/**
	 * @deprecated
	 */
	private IColor rightBorderColor;
	/**
	 * @deprecated
	 */
	private Integer padding;
	/**
	 * @deprecated
	 */
	private Integer topPadding;
	/**
	 * @deprecated
	 */
	private Integer leftPadding;
	/**
	 * @deprecated
	 */
	private Integer bottomPadding;
	/**
	 * @deprecated
	 */
	private Integer rightPadding;
	/**
	 * @deprecated
	 */
	private byte hyperlinkType;
	/**
	 * @deprecated
	 */
	private byte hyperlinkTarget;
	/**
	 * @deprecated
	 */
	private Byte scaleImage;
	/**
	 * @deprecated
	 */
	private byte onErrorType;

	
//	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
//	{
//		in.defaultReadObject();
//
//		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2)
//		{
//			horizontalAlignmentValue = HorizontalAlignEnum.getByValue(horizontalAlignment);
//			verticalAlignmentValue = VerticalAlignEnum.getByValue(verticalAlignment);
//			scaleImageValue = ScaleImageEnum.getByValue(scaleImage);
//			onErrorTypeValue = OnErrorTypeEnum.getByValue(onErrorType);
//
//			horizontalAlignment = null;
//			verticalAlignment = null;
//			scaleImage = null;
//		}
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
//
//		if (linkType == null)
//		{
//			 linkType = JRHyperlinkHelper.getLinkType(HyperlinkTypeEnum.getByValue(hyperlinkType));
//		}
//
//		if (linkTarget == null)
//		{
//			 linkTarget = JRHyperlinkHelper.getLinkTarget(HyperlinkTargetEnum.getByValue(hyperlinkTarget));
//		}
//	}

	public int getHashCode()
	{
		ObjectUtils.HashCode hash = ObjectUtils.hash();
		addGraphicHash(hash);
		hash.add(scaleImageValue);
		hash.add(isUsingCache);
		hash.add(horizontalAlignmentValue);
		hash.add(verticalAlignmentValue);
		hash.add(isLazy);
		hash.add(onErrorTypeValue);
		hash.add(linkType);
		hash.add(linkTarget);
		hash.addIdentical(lineBox);
		return hash.getHashCode();
	}

	public boolean isIdentical(Object object)
	{
		if (this == object)
		{
			return true;
		}
		
		if (!(object instanceof JRTemplateImage))
		{
			return false;
		}
		
		JRTemplateImage template = (JRTemplateImage) object;
		return graphicIdentical(template)
				&& ObjectUtils.equals(scaleImageValue, template.scaleImageValue)
				&& ObjectUtils.equals(isUsingCache, template.isUsingCache)
				&& ObjectUtils.equals(horizontalAlignmentValue, template.horizontalAlignmentValue)
				&& ObjectUtils.equals(verticalAlignmentValue, template.verticalAlignmentValue)
				&& ObjectUtils.equals(isLazy, template.isLazy)
				&& ObjectUtils.equals(onErrorTypeValue, template.onErrorTypeValue)
				&& ObjectUtils.equals(linkType, template.linkType)
				&& ObjectUtils.equals(linkTarget, template.linkTarget)
				&& ObjectUtils.identical(lineBox, template.lineBox);
	}
}
