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
package org.oss.pdfreporter.image;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import com.lowagie.text.BadElementException;

public class ImageManager extends AbstractImageManager {

	
	ImageManager() {
		super();
	}

	@Override
	IImage loadImageInternal(String filePath, float quality, float scale) throws IOException {
		IImage image = null;
		if(quality>0 && scale>0) {
			BufferedImage original = ImageIO.read(new File(filePath));
			int width = (int) (original.getWidth()*scale);
			int height = (int) (original.getHeight()*scale);
			BufferedImage resized = new BufferedImage(width, height, original.getType());
		    Graphics2D g = resized.createGraphics();
		    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    g.drawImage(original, 0, 0, width, height, 0, 0, original.getWidth(), original.getHeight(), null);
		    g.dispose();
		    original = null;
		    
		    
		    ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
		    ImageWriteParam iwp = writer.getDefaultWriteParam();
		    iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		    iwp.setCompressionQuality(quality);
		    
		    ByteArrayOutputStream bout = new ByteArrayOutputStream();
		    writer.setOutput(ImageIO.createImageOutputStream(bout));
		    writer.write(null, new IIOImage(resized, null, null), iwp);
		    
		    bout.flush();
		    byte[] byteArray = bout.toByteArray();
		    
			try {
				com.lowagie.text.Image img = com.lowagie.text.Image.getInstance(byteArray);
				image = new Image(this,img, filePath);
			} catch (BadElementException e) {
				throw new IOException(e);
			}
		}
		else {
			try {
				com.lowagie.text.Image img = com.lowagie.text.Image.getInstance(filePath);
				image = new Image(this,img, filePath);
			} catch (BadElementException e) {
				throw new IOException(e);
			}
			
		}
		return image;
	}

	@Override
	void disposeInternal() {
	}

	

}
