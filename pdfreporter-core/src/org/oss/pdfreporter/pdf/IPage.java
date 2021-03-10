/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package org.oss.pdfreporter.pdf;

import org.oss.pdfreporter.font.IFont;
import org.oss.pdfreporter.geometry.IAffineTransformMatrix;
import org.oss.pdfreporter.geometry.IColor;
import org.oss.pdfreporter.image.IImage;

/**
 * A PDF Page interface.
 * @author donatmuller, 2013, last change 1:28:45 AM
 */
public interface IPage {
	
	public enum LineCap {
		BUTT_END,
		ROUND_END,
		PROJECTING_SCUARE_END
	}
	public enum LineJoin {
		MITER_JOIN,
		ROUND_JOIN,
		BEVEL_JOIN
	}

	public enum ScaleMode {
		SIZE,  	// sizes to width or height preserving aspect ratio
		SCALE, 	// stretches to width and height
		NONE 	// draw image in original size
	}

	/**
	 * Sets the endstyle of a line
	 * @param lineCap
	 */
	void setLineCap(LineCap lineCap);
	/**
	 * Sets style of two joining lines
	 * @param lineJoin
	 */
	void setLineJoin(LineJoin lineJoin);
	/**
	 * Seth the width of the line
	 * @param width
	 */
	void setLineWidth(float width);
	/**
	 * Sets the line color
	 * @param color
	 */
	void setRGBColorStroke(IColor color);
	/**
	 * Sets the fill color
	 * @param color
	 */
	void setRGBColorFill(IColor color);
	/**
	 * Sets the dash pattern for lines
	 * @param array
	 * @param phase
	 */
	void setLineDash(int[] array, int phase);
	
    /**
     * Paint the current path.
     */
    void stroke();
    
    /**
     * Fills the current path.
     */
    void fill();
    
    /**
     * Fills the current path then paints the path.
     */
    void fillStroke();
	
    // -------------- Path API -------------------
    
    /**
     * Moves coordinate context to position.
     * @param x
     * @param y
     */
    void moveTo(float x, float y);
    
    /**
     * Adds a line to the path from current position to x,y.
     * @param x
     * @param y
     */
    void lineTo(float x, float y);
    
    /**
     * Adds a rectangular path.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void rectangle(float x, float y, float width, float height);

    /**
     * Adds a rectangular path with rounded corners.
     * Use the following code from iText to implement it with primitives:
     *
     *  if (w < 0) {
     *      x += w;
     *      w = -w;
     *  }
     *  if (h < 0) {
     *      y += h;
     *      h = -h;
     *  }
     *  if (r < 0)
     *      r = -r;
     *  float b = 0.4477f;
     *  moveTo(x + r, y);
     *  lineTo(x + w - r, y);
     *  curveTo(x + w - r * b, y, x + w, y + r * b, x + w, y + r);
     *  lineTo(x + w, y + h - r);
     *  curveTo(x + w, y + h - r * b, x + w - r * b, y + h, x + w - r, y + h);
     *  lineTo(x + r, y + h);
     *  curveTo(x + r * b, y + h, x, y + h - r * b, x, y + h - r);
     *  lineTo(x, y + r);
     *  curveTo(x, y + r * b, x + r * b, y, x + r, y);
     *  
     * @param x
     * @param y
     * @param width
     * @param height
     * @param radius
     */
    void roundRectangle(float x, float y, float width, float height, int radius);
    
    /**
     * Outputs an elliptic path defined by a rectangle.
     * TODO use a rectangle as parameter
     * Implementation hint for bezier curves: http://www.spaceroots.org/documents/ellipse/elliptical-arc.pdf 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    void ellipse(float x1, float y1, float x2, float y2);

    // ---------------- TRANSFORM API ---------------------
    
    /**
     * Transforms the coordinates of the page according to the matrix.
     * @IAffineTransformMatrix
     * @param matrix
     */
    void transform(IAffineTransformMatrix matrix);
    
    /**
     * Restores the coordinate system transformation.
     */
    void restoreTransformation();
    
    // ---------------- TEXT API ---------------------
    
    /**
     * Begins a text section.
     */
    void beginText();
    
    /**
     * Ends a text section.
     */
    void endText();
        
    
    /**
     * Sets cursor at given position
     * @param x
     * @param y
     */
    void setTextPos(float x, float y);
    
    /**
     * Outputs a chunk of text at the current position
     * and advances the x position for next chunk.
     * @param text
     */
    void textOut(String text);
    
    
    /**
     * Sets the font.
     * @param font
     */
    void setFont(IFont font);
    
    /**
     * Sets extra word spacing.
     * Default is 0
     * @param spacing
     */
    void setWordSpacing(float spacing);
    
    /**
     * Sets extra chracter spacing.
     * Default is 0.
     * @param spacing
     */
    void setCharacterSpacing(float spacing);
    
    // ------------- Image API ----------------
    
    /**
     * Draws image in actual size at x,y
     * @param image
     * @param x
     * @param y
     */
    void draw(IImage image, float x, float y) throws DocumentException;
    
    /**
     * Draws image of size width and height at x, y
     * mode determines if the image is sized, streched or framed to fit within width and height
     * @param image
     * @param x
     * @param y
     * @param width
     * @param height
     */
    void draw(IImage image, float x, float y, float width, float height, ScaleMode mode) throws DocumentException;
    /**
     * Draws an image in the rectangle defined by x, y, width, height.
     * The  xoffset, yoffset defines the offset of the image to x, y
     * @param image
     * @param xoffset
     * @param yoffset
     * @param x
     * @param y
     * @param width
     * @param height
     * @throws DocumentException
     */
    void drawCropped(IImage image, float xoffset, float yoffset, float x, float y, float width, float height) throws DocumentException;
    
    void setTextRise(float rise);
    
}
