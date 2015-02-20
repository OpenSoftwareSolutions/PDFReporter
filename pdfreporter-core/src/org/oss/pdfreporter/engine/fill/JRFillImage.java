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

import java.io.File;
import java.io.InputStream;
import java.util.logging.Logger;

import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRExpression;
import org.oss.pdfreporter.engine.JRExpressionCollector;
import org.oss.pdfreporter.engine.JRGroup;
import org.oss.pdfreporter.engine.JRHyperlinkParameter;
import org.oss.pdfreporter.engine.JRImage;
import org.oss.pdfreporter.engine.JRImageRenderer;
import org.oss.pdfreporter.engine.JRLineBox;
import org.oss.pdfreporter.engine.JRPrintElement;
import org.oss.pdfreporter.engine.JRPrintHyperlinkParameters;
import org.oss.pdfreporter.engine.JRPrintImage;
import org.oss.pdfreporter.engine.JRVisitor;
import org.oss.pdfreporter.engine.Renderable;
import org.oss.pdfreporter.engine.type.EvaluationTimeEnum;
import org.oss.pdfreporter.engine.type.HorizontalAlignEnum;
import org.oss.pdfreporter.engine.type.HyperlinkTypeEnum;
import org.oss.pdfreporter.engine.type.ModeEnum;
import org.oss.pdfreporter.engine.type.OnErrorTypeEnum;
import org.oss.pdfreporter.engine.type.ScaleImageEnum;
import org.oss.pdfreporter.engine.type.VerticalAlignEnum;
import org.oss.pdfreporter.engine.util.JRStyleResolver;
import org.oss.pdfreporter.geometry.IDimension;
import org.oss.pdfreporter.image.IImage;
import org.oss.pdfreporter.net.IURL;




/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRFillImage.java 5632 2012-09-04 07:48:16Z teodord $
 */
public class JRFillImage extends JRFillGraphicElement implements JRImage
{
	private final static Logger logger = Logger.getLogger(JRFillImage.class.getName());

	/**
	 *
	 */
	private JRGroup evaluationGroup;

	/**
	 *
	 */
	private Renderable renderer;
	private boolean hasOverflowed;
	private Integer imageHeight;
	private Integer imageWidth;
	private Integer imageX;
	private String anchorName;
	private String hyperlinkReference;
	private String hyperlinkAnchor;
	private Integer hyperlinkPage;
	private String hyperlinkTooltip;
	private JRPrintHyperlinkParameters hyperlinkParameters;

	protected final JRLineBox initLineBox;
	protected JRLineBox lineBox;


	/**
	 *
	 */
	protected JRFillImage(
		JRBaseFiller filler,
		JRImage image, 
		JRFillObjectFactory factory
		)
	{
		super(filler, image, factory);
		
		initLineBox = image.getLineBox().clone(this);

		evaluationGroup = factory.getGroup(image.getEvaluationGroup());
	}


	protected JRFillImage(JRFillImage image, JRFillCloneFactory factory)
	{
		super(image, factory);
		
		initLineBox = image.getLineBox().clone(this);

		evaluationGroup = image.evaluationGroup;
	}


	/**
	 *
	 */
	protected void evaluateStyle(
		byte evaluation
		) throws JRException
	{
		super.evaluateStyle(evaluation);

		lineBox = null;
		
		if (providerStyle != null)
		{
			lineBox = initLineBox.clone(this);
			JRStyleResolver.appendBox(lineBox, providerStyle.getLineBox());
		}
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
		
	public ScaleImageEnum getOwnScaleImageValue()
	{
		return providerStyle == null || providerStyle.getOwnScaleImageValue() == null ? ((JRImage)this.parent).getOwnScaleImageValue() : providerStyle.getOwnScaleImageValue();
	}

	/**
	 *
	 */
	public void setScaleImage(ScaleImageEnum scaleImage)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 */
	public HorizontalAlignEnum getHorizontalAlignmentValue()
	{
		return JRStyleResolver.getHorizontalAlignmentValue(this);
	}
		
	public HorizontalAlignEnum getOwnHorizontalAlignmentValue()
	{
		return providerStyle == null || providerStyle.getOwnHorizontalAlignmentValue() == null ? ((JRImage)this.parent).getOwnHorizontalAlignmentValue() : providerStyle.getOwnHorizontalAlignmentValue();
	}

	/**
	 *
	 */
	public void setHorizontalAlignment(HorizontalAlignEnum horizontalAlignment)
	{
		throw new UnsupportedOperationException();
	}
		
	/**
	 *
	 */
	public VerticalAlignEnum getVerticalAlignmentValue()
	{
		return JRStyleResolver.getVerticalAlignmentValue(this);
	}
		
	public VerticalAlignEnum getOwnVerticalAlignmentValue()
	{
		return providerStyle == null || providerStyle.getOwnVerticalAlignmentValue() == null ? ((JRImage)this.parent).getOwnVerticalAlignmentValue() : providerStyle.getOwnVerticalAlignmentValue();
	}

	/**
	 *
	 */
	public void setVerticalAlignment(VerticalAlignEnum verticalAlignment)
	{
		throw new UnsupportedOperationException();
	}
		
	/**
	 * @deprecated Replaced by {@link #getUsingCache()}.
	 */
	public boolean isUsingCache()
	{
		return ((JRImage)this.parent).isUsingCache();
	}
		
	/**
	 * @deprecated Replaced by {@link #getUsingCache()}.
	 */
	public Boolean isOwnUsingCache()
	{
		return ((JRImage)this.parent).isOwnUsingCache();
	}
		
	/**
	 *
	 */
	public Boolean getUsingCache()
	{
		return ((JRImage)this.parent).getUsingCache();
	}
		
	/**
	 *
	 */
	public void setUsingCache(boolean isUsingCache)
	{
	}
		
	/**
	 *
	 */
	public void setUsingCache(Boolean isUsingCache)
	{
	}
		
	/**
	 *
	 */
	public boolean isLazy()
	{
		return ((JRImage)this.parent).isLazy();
	}
		
	/**
	 *
	 */
	public void setLazy(boolean isLazy)
	{
	}

	/**
	 *
	 */
	public OnErrorTypeEnum getOnErrorTypeValue()
	{
		return ((JRImage)this.parent).getOnErrorTypeValue();
	}
		
	/**
	 *
	 */
	public void setOnErrorType(OnErrorTypeEnum onErrorType)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 */
	public EvaluationTimeEnum getEvaluationTimeValue()
	{
		return ((JRImage)this.parent).getEvaluationTimeValue();
	}
		
	/**
	 *
	 */
	public JRGroup getEvaluationGroup()
	{
		return this.evaluationGroup;
	}
		
	/**
	 *
	 */
	public JRLineBox getLineBox()
	{
		return lineBox == null ? initLineBox : lineBox;
	}

	/**
	 * @deprecated Replaced by {@link #getHyperlinkTypeValue()}.
	 */
	public byte getHyperlinkType()
	{
		return getHyperlinkTypeValue().getValue();
	}

	/**
	 *
	 */
	public HyperlinkTypeEnum getHyperlinkTypeValue()
	{
		return ((JRImage)parent).getHyperlinkTypeValue();
	}
		
	/**
	 *
	 */
	public byte getHyperlinkTarget()
	{
		return ((JRImage)this.parent).getHyperlinkTarget();
	}
		
	/**
	 *
	 */
	public String getLinkTarget()
	{
		return ((JRImage)this.parent).getLinkTarget();
	}
		
	/**
	 *
	 */
	public JRExpression getExpression()
	{
		return ((JRImage)this.parent).getExpression();
	}

	/**
	 *
	 */
	public JRExpression getAnchorNameExpression()
	{
		return ((JRImage)this.parent).getAnchorNameExpression();
	}

	/**
	 *
	 */
	public JRExpression getHyperlinkReferenceExpression()
	{
		return ((JRImage)this.parent).getHyperlinkReferenceExpression();
	}

	/**
	 *
	 */
	public JRExpression getHyperlinkAnchorExpression()
	{
		return ((JRImage)this.parent).getHyperlinkAnchorExpression();
	}

	/**
	 *
	 */
	public JRExpression getHyperlinkPageExpression()
	{
		return ((JRImage)this.parent).getHyperlinkPageExpression();
	}

		
	/**
	 *
	 */
	protected Renderable getRenderable()
	{
		return this.renderer;
	}
		
	/**
	 *
	 */
	protected String getAnchorName()
	{
		return this.anchorName;
	}

	/**
	 *
	 */
	protected String getHyperlinkReference()
	{
		return this.hyperlinkReference;
	}

	/**
	 *
	 */
	protected String getHyperlinkAnchor()
	{
		return this.hyperlinkAnchor;
	}

	/**
	 *
	 */
	protected Integer getHyperlinkPage()
	{
		return this.hyperlinkPage;
	}
		

	protected String getHyperlinkTooltip()
	{
		return this.hyperlinkTooltip;
	}
		

	/**
	 *
	 */
	protected JRTemplateImage getJRTemplateImage()
	{
		return (JRTemplateImage) getElementTemplate();
	}

	protected JRTemplateElement createElementTemplate()
	{
		JRTemplateImage template = 
			new JRTemplateImage(
				getElementOrigin(), 
				filler.getJasperPrint().getDefaultStyleProvider(), 
				this
				);
		
		if (getScaleImageValue() == ScaleImageEnum.REAL_HEIGHT
				|| getScaleImageValue() == ScaleImageEnum.REAL_SIZE)
		{
			template.setScaleImage(ScaleImageEnum.RETAIN_SHAPE);
		}
		
		return template;
	}


	/**
	 *
	 */
	protected void evaluate(
		byte evaluation
		) throws JRException
	{
		initDelayedEvaluations();
		
		reset();
		
		evaluatePrintWhenExpression(evaluation);

		if (isPrintWhenExpressionNull() || isPrintWhenTrue())
		{
			if (isEvaluateNow())
			{
				hasOverflowed = false;
				evaluateImage(evaluation);
			}
		}
	}
	

	/**
	 *
	 */
	protected void evaluateImage(
		byte evaluation
		) throws JRException
	{
		evaluateProperties(evaluation);
		evaluateStyle(evaluation);
		
		JRExpression expression = this.getExpression();

		Renderable newRenderer = null;
		
		Object source = evaluateExpression(expression, evaluation);
		if (source != null)
		{
			Boolean isUsingCache = getUsingCache();
			if (isUsingCache == null)
			{
				isUsingCache = source instanceof String;
			}
			
			if (isUsingCache && filler.fillContext.hasLoadedImage(source))
			{
				newRenderer = filler.fillContext.getLoadedImage(source).getRenderable();
			}
			else
			{
				@SuppressWarnings("deprecation")
				org.oss.pdfreporter.engine.JRRenderable deprecatedRenderable = 
					source instanceof org.oss.pdfreporter.engine.JRRenderable 
					? (org.oss.pdfreporter.engine.JRRenderable)source 
					: null;

				if (source instanceof IImage)
				{
					IImage img = (IImage) source;
					newRenderer = JRImageRenderer.getInstance(img);
				}
				else if (source instanceof InputStream)
				{
					logger.warning("Image from input stream is not supported.");
				}
				else if (source instanceof IURL)
				{
					IURL url = (IURL) source;
					logger.warning("Image from url is not supported. URL: " + url.getPath());
				}
				else if (source instanceof File)
				{
					File file = (File) source;
					logger.warning("Image from file is not supported. File: " + file.getAbsolutePath());
				}
				else if (source instanceof String)
				{
					String location = (String) source;
					newRenderer = JRImageRenderer.getInstance(location);
				}
				else if (source instanceof Renderable)
				{
					newRenderer = (Renderable) source;
				}
				else if (deprecatedRenderable != null)
				{
					logger.warning("Image from deprecatedRenderable is not supported. Source: " + deprecatedRenderable);
				}
				else
				{
					logger.warning("Image from unknown source is not supported. Source: " + (source==null ? "null" : source.getClass().getName()));
				}

				if (isUsingCache)
				{
					JRPrintImage img = new JRTemplatePrintImage(getJRTemplateImage(), elementId);
					img.setRenderable(newRenderer);
					filler.fillContext.registerLoadedImage(source, img);
				}
			}
		}

		setValueRepeating(this.renderer == newRenderer);
	
		this.renderer = newRenderer;
		
		this.anchorName = (String) evaluateExpression(this.getAnchorNameExpression(), evaluation);
		this.hyperlinkReference = (String) evaluateExpression(this.getHyperlinkReferenceExpression(), evaluation);
		this.hyperlinkAnchor = (String) evaluateExpression(this.getHyperlinkAnchorExpression(), evaluation);
		this.hyperlinkPage = (Integer) evaluateExpression(this.getHyperlinkPageExpression(), evaluation);
		this.hyperlinkTooltip = (String) evaluateExpression(this.getHyperlinkTooltipExpression(), evaluation);
		hyperlinkParameters = JRFillHyperlinkHelper.evaluateHyperlinkParameters(this, expressionEvaluator, evaluation);
	}
	

	protected boolean prepare(
		int availableHeight,
		boolean isOverflow
		) throws JRException
	{
		boolean willOverflow = false;

		if (
			this.isPrintWhenExpressionNull() ||
			( !this.isPrintWhenExpressionNull() && 
			this.isPrintWhenTrue() )
			)
		{
			this.setToPrint(true);
		}
		else
		{
			this.setToPrint(false);
		}

		if (!this.isToPrint())
		{
			return willOverflow;
		}
		
		boolean isToPrint = true;
		boolean isReprinted = false;

		if (isEvaluateNow())
		{
			if (isOverflow && this.isAlreadyPrinted() && !this.isPrintWhenDetailOverflows())
			{
				isToPrint = false;
			}
	
			if (
				isToPrint && 
				this.isPrintWhenExpressionNull() &&
				!this.isPrintRepeatedValues() &&
				isValueRepeating()
				)
			{
				if (
					( !this.isPrintInFirstWholeBand() || !this.getBand().isFirstWholeOnPageColumn() ) &&
					( this.getPrintWhenGroupChanges() == null || !this.getBand().isNewGroup(this.getPrintWhenGroupChanges()) ) &&
					( !isOverflow || !this.isPrintWhenDetailOverflows() )
					)
				{
					isToPrint = false;
				}
			}

			if (
				isToPrint && 
				this.isRemoveLineWhenBlank() &&
				this.getRenderable() == null
				)
			{
				isToPrint = false;
			}
	
			if (isToPrint)
			{
				if (availableHeight < getRelativeY() + getHeight())
				{
					isToPrint = false;
					willOverflow = true;
				}
				else if (!isLazy() && (getScaleImageValue() == ScaleImageEnum.REAL_HEIGHT
						|| getScaleImageValue() == ScaleImageEnum.REAL_SIZE))
				{
					int padding = getLineBox().getBottomPadding().intValue() 
							+ getLineBox().getTopPadding().intValue();
					boolean reprinted = isOverflow 
						&& (this.isPrintWhenDetailOverflows() 
								&& (this.isAlreadyPrinted() 
										|| (!this.isAlreadyPrinted() && !this.isPrintRepeatedValues())));
					boolean imageOverflowAllowed = 
							filler.isBandOverFlowAllowed() && !reprinted && !hasOverflowed;
					boolean fits = fitImage(availableHeight - getRelativeY() - padding, imageOverflowAllowed, 
							getHorizontalAlignmentValue());
					if (fits)
					{
						if (imageHeight != null)
						{
							setStretchHeight(imageHeight.intValue() + padding);
						}
					}
					else
					{
						hasOverflowed = true;
						isToPrint = false;
						willOverflow = true;
						setStretchHeight(availableHeight - getRelativeY() - padding);
					}
				}
			}
			
			if (
				isToPrint && 
				isOverflow && 
				//(this.isAlreadyPrinted() || !this.isPrintRepeatedValues())
				(this.isPrintWhenDetailOverflows() && (this.isAlreadyPrinted() || (!this.isAlreadyPrinted() && !this.isPrintRepeatedValues())))
				)
			{
				isReprinted = true;
			}
		}
		else
		{
			if (isOverflow && this.isAlreadyPrinted() && !this.isPrintWhenDetailOverflows())
			{
				isToPrint = false;
			}
	
			if (
				isToPrint && 
				availableHeight < this.getRelativeY() + getHeight()
				)
			{
				isToPrint = false;
				willOverflow = true;
			}
			
			if (
				isToPrint && 
				isOverflow && 
				//(this.isAlreadyPrinted() || !this.isPrintRepeatedValues())
				(this.isPrintWhenDetailOverflows() && (this.isAlreadyPrinted() || (!this.isAlreadyPrinted() && !this.isPrintRepeatedValues())))
				)
			{
				isReprinted = true;
			}
		}

		this.setToPrint(isToPrint);
		this.setReprinted(isReprinted);
		
		return willOverflow;
	}

	protected void reset()
	{
		imageHeight = null;
		imageWidth = null;
		imageX = null;
		
		super.reset();
	}

	protected boolean fitImage(int availableHeight, boolean overflowAllowed,
			HorizontalAlignEnum hAlign) throws JRException
	{
		imageHeight = null;
		imageWidth = null;
		imageX = null;
		
		IDimension imageSize = renderer == null ? null : renderer.getDimension(filler.getJasperReportsContext());
		if (imageSize == null)
		{
			return true;
		}
		
		int realHeight = (int) imageSize.getHeight();
		int realWidth = (int) imageSize.getWidth();
		boolean fitted;
		
		int reducedHeight = realHeight;
		int reducedWidth = realWidth;
		if (realWidth > getWidth())
		{
			double wRatio = ((double) getWidth()) / realWidth;
			reducedHeight = (int) (wRatio * realHeight);
			reducedWidth = getWidth();
		}		
		
		if (reducedHeight <= availableHeight)
		{
			imageHeight = Integer.valueOf(reducedHeight);
			if (getScaleImageValue() == ScaleImageEnum.REAL_SIZE)
			{
				imageWidth = Integer.valueOf(reducedWidth);
			}
			fitted = true;
		}
		else if (overflowAllowed)
		{
			fitted = false;
		}
		else
		{
			imageHeight = Integer.valueOf(availableHeight);
			if (getScaleImageValue() == ScaleImageEnum.REAL_SIZE)
			{
				double hRatio = ((double) availableHeight) / realHeight;
				imageWidth = Integer.valueOf((int) (hRatio * realWidth));
			}
			fitted = true;
		}

		if (imageWidth != null && imageWidth.intValue() != getWidth())
		{
			switch (hAlign)
			{
			case RIGHT:
				imageX = Integer.valueOf(getX() + getWidth() - imageWidth.intValue());
				break;
			case CENTER:
				imageX = Integer.valueOf(getX() + (getWidth() - imageWidth.intValue()) / 2);
				break;
			default:
				break;
			}
		}
		
		return fitted;
	}

	/**
	 *
	 */
	protected JRPrintElement fill() throws JRException
	{
		EvaluationTimeEnum evaluationTime = this.getEvaluationTimeValue();
		JRTemplatePrintImage printImage;
		JRRecordedValuesPrintImage recordedValuesImage;
		if (isEvaluateAuto())
		{
			printImage = recordedValuesImage = new JRRecordedValuesPrintImage(getJRTemplateImage(), elementId);
		}
		else
		{
			printImage = new JRTemplatePrintImage(getJRTemplateImage(), elementId);
			recordedValuesImage = null;
		}
		
		printImage.setUUID(this.getUUID());
		printImage.setX(this.getX());
		printImage.setY(this.getRelativeY());
		printImage.setWidth(getWidth());
		printImage.setHeight(this.getStretchHeight());

		if (isEvaluateNow())
		{
			this.copy(printImage);
		}
		else if (isEvaluateAuto())
		{
			initDelayedEvaluationPrint(recordedValuesImage);
		}
		else
		{
			filler.addBoundElement(this, printImage, evaluationTime, getEvaluationGroup(), band);
		}
		
		return printImage;
	}
		

	/**
	 *
	 */
	protected void copy(JRPrintImage printImage)
	{
		printImage.setUUID(getUUID());

		if (imageX != null)
		{
			printImage.setX(imageX.intValue());
		}
		if (imageWidth != null)
		{
			printImage.setWidth(imageWidth.intValue());
		}
		
		printImage.setRenderable(this.getRenderable());
		printImage.setAnchorName(this.getAnchorName());
		printImage.setHyperlinkReference(this.getHyperlinkReference());
		printImage.setHyperlinkAnchor(this.getHyperlinkAnchor());
		printImage.setHyperlinkPage(this.getHyperlinkPage());
		printImage.setHyperlinkTooltip(getHyperlinkTooltip());
		printImage.setBookmarkLevel(this.getBookmarkLevel());
		printImage.setHyperlinkParameters(hyperlinkParameters);
		transferProperties(printImage);
	}


	/**
	 *
	 */
	public void collectExpressions(JRExpressionCollector collector)
	{
		collector.collect(this);
	}

	/**
	 *
	 */
	public void visit(JRVisitor visitor)
	{
		visitor.visitImage(this);
	}

	
	protected void resolveElement(JRPrintElement element, byte evaluation) throws JRException
	{
		evaluateImage(evaluation);

		JRPrintImage printImage = (JRPrintImage) element;

		if (getScaleImageValue() == ScaleImageEnum.REAL_SIZE)//to avoid get dimension and thus unnecessarily load the image
		{
			int padding = printImage.getLineBox().getBottomPadding().intValue() 
			+ printImage.getLineBox().getTopPadding().intValue();
			fitImage(getHeight() - padding, false, 
					printImage.getHorizontalAlignmentValue());
		}
		
		copy(printImage);
	}


	public int getBookmarkLevel()
	{
		return ((JRImage)this.parent).getBookmarkLevel();
	}


	public JRFillCloneable createClone(JRFillCloneFactory factory)
	{
		return new JRFillImage(this, factory);
	}
	
	protected void collectDelayedEvaluations()
	{
		super.collectDelayedEvaluations();
		
		collectDelayedEvaluations(getExpression());
		collectDelayedEvaluations(getAnchorNameExpression());
		collectDelayedEvaluations(getHyperlinkReferenceExpression());
		collectDelayedEvaluations(getHyperlinkAnchorExpression());
		collectDelayedEvaluations(getHyperlinkPageExpression());	
	}


	public JRHyperlinkParameter[] getHyperlinkParameters()
	{
		return ((JRImage) parent).getHyperlinkParameters();
	}


	public String getLinkType()
	{
		return ((JRImage) parent).getLinkType();
	}


	public JRExpression getHyperlinkTooltipExpression()
	{
		return ((JRImage) parent).getHyperlinkTooltipExpression();
	}

}
