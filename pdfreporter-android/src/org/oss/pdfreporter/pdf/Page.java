/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.pdf;

import java.awt.geom.AffineTransform;
import java.io.IOException;

import org.oss.pdfreporter.exception.NotImplementedException;
import org.oss.pdfreporter.font.IFont;
import org.oss.pdfreporter.geometry.IAffineTransformMatrix;
import org.oss.pdfreporter.geometry.IColor;
import org.oss.pdfreporter.image.IImage;


import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;

public class Page implements IPage {
	private final PdfContentByte delegate;
	
	Page(PdfContentByte content) {
		this.delegate = content;
	}
	@Override
	public void setLineCap(LineCap lineCap) {
		delegate.setLineCap(translateLineCap(lineCap));
	}

	private int translateLineCap(LineCap lineCap) {
		switch (lineCap) {
		case BUTT_END:
			return PdfContentByte.LINE_CAP_BUTT;
		case ROUND_END:
			return PdfContentByte.LINE_CAP_ROUND;
		case PROJECTING_SCUARE_END:
			return PdfContentByte.LINE_CAP_PROJECTING_SQUARE;
		}
		throw new NotImplementedException("LineCap: "  + lineCap + " is unknown.");
	}
	
	@Override
	public void setLineJoin(LineJoin lineJoin) {
		delegate.setLineJoin(translateLineJoin(lineJoin));
	}

	private int translateLineJoin(LineJoin lineJoin) {
		switch (lineJoin) {
		case BEVEL_JOIN:
			return PdfContentByte.LINE_JOIN_BEVEL;
		case MITER_JOIN:
			return PdfContentByte.LINE_JOIN_MITER;
		case ROUND_JOIN:
			return PdfContentByte.LINE_JOIN_ROUND;
		}
		throw new NotImplementedException("LineJoin: "  + lineJoin + " is unknown.");
	}
	
	@Override
	public void setLineDash(int[] array, int phase) {
		delegate.setLineDash(toFloatArray(array), phase);

	}
	
	private float[] toFloatArray(int[] array) {
		int len = array==null ? 0 : array.length;
		float[] f = new float[len];
		for (int i = 0; i < len; i++) {
			f[i] = array[i];
		}
		return f;
	}
	
	@Override
	public void setLineWidth(float width) {
		delegate.setLineWidth(width);
	}
	
	@Override
	public void setRGBColorStroke(IColor color) {
		if (color != null) {			
			delegate.setRGBColorStroke(color.getRed(), color.getGreen(), color.getBlue());
		}
	}
	
	@Override
	public void setRGBColorFill(IColor color) {
		if (color!=null) {			
			delegate.setRGBColorFill(color.getRed(), color.getGreen(), color.getBlue());
		}
	}
	
	@Override
	public void roundRectangle(float x, float y, float w, float h, int r) {
		delegate.roundRectangle(x, y, w, h, r);
	}
	
	@Override
	public void fill() {
		delegate.fill();
	}
	
	@Override
	public void fillStroke() {
		delegate.fillStroke();
	}
	
	@Override
	public void stroke() {
		delegate.stroke();
	}
	@Override
	public void ellipse(float x1, float y1, float x2, float y2) {
		delegate.ellipse(x1, y1, x2, y2);
	}
	
	
	@Override
	public void rectangle(float x, float y, float width, float height) {
		delegate.rectangle(x, y, width, height);
	}
	
	@Override
	public void moveTo(float x, float y) {
		delegate.moveTo(x, y);
	}
	
	@Override
	public void lineTo(float x, float y) {
		delegate.lineTo(x, y);
	}
	@Override
	public void setTextPos(float x, float y) {
		delegate.setTextMatrix(x, y);
	}
	@Override
	public void textOut(String text) {
		delegate.showText(text);
		
	}
	@Override
	public void setFont(IFont font) {
		delegate.setFontAndSize((BaseFont) font.getPeer(), font.getSize());
	}
	
	private Image getImage(IImage image) throws BadElementException, IOException {
		if (image!=null) {
			return  Image.getInstance((Image)image.getPeer());
		}
		return null;
//		Image pdfImage = Image.getInstance((Image)image.getPeer());
//		return  Image.getInstance(pdfImage);
	}
	
	
	@Override
	public void draw(IImage image, float x, float y) throws DocumentException {
		try {
			Image pdfImage = getImage(image);
			pdfImage.setAbsolutePosition(x, y);
			delegate.addImage(pdfImage);
		} catch (Exception e) {
			throw new DocumentException(e);
		}		
	}
	
	@Override
	public void draw(IImage image, float x, float y, float width, float height,
			ScaleMode mode) throws DocumentException {
		try {
			Image pdfImage = null;
			switch (mode) {
			case NONE:
				pdfImage =  getImage(image);
				PdfTemplate t = delegate.getPdfWriter().getDirectContent().createTemplate(width, height);
				t.addImage(pdfImage, pdfImage.getWidth(), 0, 0, pdfImage.getHeight(), 0, height-pdfImage.getHeight());
				pdfImage = Image.getInstance(t);
				break;
			case SCALE:
				pdfImage =  getImage(image);
				pdfImage.scaleAbsolute(width, height);
				break;
			case SIZE:
				pdfImage =  getImage(image);
				pdfImage.scaleToFit(width, height);
				break;
			}			
			pdfImage.setAbsolutePosition(x, y);
			delegate.addImage(pdfImage);
		} catch (Exception e) {
			throw new DocumentException(e);
		}		
		
	}

	@Override
	public void drawCropped(IImage image, float xoffset, float yoffset, float x,
			float y, float width, float height) throws DocumentException {
		try {
			
			Image pdfImage =  getImage(image);
			PdfTemplate t = delegate.getPdfWriter().getDirectContent().createTemplate(width, height);
			t.addImage(pdfImage, pdfImage.getWidth(), 0, 0, pdfImage.getHeight(), xoffset, -yoffset+height-pdfImage.getHeight());
			pdfImage = Image.getInstance(t);
			
			pdfImage.setAbsolutePosition(x, y);
			delegate.addImage(pdfImage);
		} catch (Exception e) {
			throw new DocumentException(e);
		}
	}
	
	@Override
	public void transform(IAffineTransformMatrix m) {
		delegate.saveState();
		delegate.transform(new AffineTransform(m.getM00(), m.getM10(), m.getM01(), m.getM11(), m.getM02(), m.getM12()));		
	}
	
	@Override
	public void restoreTransformation() {
		delegate.restoreState();
		
	}
	
	@Override
	public void beginText() {
		delegate.beginText();
	}
	
	@Override
	public void endText() {
		delegate.endText();
	}
	
	@Override
	public void setWordSpacing(float spacing) {
		delegate.setWordSpacing(spacing);
		
	}
	@Override
	public void setCharacterSpacing(float spacing) {
		delegate.setCharacterSpacing(spacing);
		
	}

}
