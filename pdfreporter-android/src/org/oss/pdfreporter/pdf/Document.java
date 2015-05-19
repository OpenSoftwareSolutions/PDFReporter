/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package org.oss.pdfreporter.pdf;

import java.io.FileOutputStream;
import java.io.OutputStream;

import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfStream;
import com.lowagie.text.pdf.PdfWriter;

public class Document extends AbstractDocument {

	private final com.lowagie.text.Document delegate;
	private final OutputStream outputStream;
	private final PdfWriter pdfWriter;
	
	private Document(com.lowagie.text.Document document, String fileName) throws DocumentException {
		super();
		try {
			this.delegate = document;
			this.outputStream = new FileOutputStream(fileName);
			this.pdfWriter = PdfWriter.getInstance(delegate, outputStream);
		} catch (Exception e) {
			throw new DocumentException(e);
		} 
	}
	
	public Document(String fileName) throws DocumentException {
		this(new com.lowagie.text.Document(),fileName);
	}
	
	public Document(String fileName, int width, int height) throws DocumentException {
		this(new com.lowagie.text.Document(new Rectangle(width,height)),fileName);
	}
	
	@Override
	public void setAuthor(String author) {
		delegate.addAuthor(author);
	}

	@Override
	public void setCreator(String creator) {
		delegate.addCreator(creator);

	}

	@Override
	public void setTitle(String title) {
		delegate.addTitle(title);
	}

	@Override
	public void setSubject(String subject) {
		delegate.addSubject(subject);
	}

	@Override
	public void setKeywords(String keywords) {
		delegate.addKeywords(keywords);
	}

	@Override
	public void setCompression(boolean compress) {
		if (compress) {
			pdfWriter.setFullCompression();
		} else {			
			pdfWriter.setCompressionLevel(PdfStream.NO_COMPRESSION);
		}
	}

	@Override
	public void setPdfVersion(char version) {
		pdfWriter.setPdfVersion(version);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setEncryption(IEncryption.KeyLength keyLength, String userPassword,
			String ownerPasswrod, int permission) throws DocumentException {
		try {
			pdfWriter.setEncryption(keyLength==IEncryption.KeyLength.ENCRYPTION_128, userPassword, ownerPasswrod,convertPermission(permission));
		} catch (com.lowagie.text.DocumentException e) {
			throw new DocumentException(e);
		}
	}
	
	private static int convertPermission(int permission) throws DocumentException {
		int translated = 0;
		if ((permission & PERMISSION_COPY) == PERMISSION_COPY ) {
			translated |= PdfWriter.ALLOW_COPY;
		}
		if ((permission & PERMISSION_COPY) == PERMISSION_EDIT ) {
			translated |= PdfWriter.ALLOW_MODIFY_ANNOTATIONS;
		}
		if ((permission & PERMISSION_COPY) == PERMISSION_EDIT_ALL ) {
			translated |= PdfWriter.ALLOW_MODIFY_CONTENTS;
		}
		if ((permission & PERMISSION_COPY) == PERMISSION_READ) {
			translated |= PdfWriter.ALLOW_SCREENREADERS;
		}
		if ((permission & PERMISSION_COPY) == PERMISSION_PRINT ) {
			translated |= PdfWriter.ALLOW_PRINTING;
		}
		return translated;
	}
	
	@Override
	public void setPdfConformance(ConformanceLevel level) {
		pdfWriter.setPDFXConformance(level==ConformanceLevel.PDF_1A ? PdfWriter.PDFA1A : PdfWriter.PDFA1B);
	}

	@Override
	public void open() {
		delegate.open();
	}
	
	@Override
	public IPage newPage() {
		return newPage(PageOrientation.PORTRAIT,PageSize.A4);
	}

	@Override
	public IPage newPage(PageOrientation orientation, int width, int height) {
		return newPage(orientation,new Rectangle(width,height));
	}
	
	private IPage newPage(PageOrientation orientation, Rectangle pageSize) {
		// TODO (25.05.2013, Donat, Open Software Solutions): Verify if rotating is due to iText or Jasper (LANDSCAPE && WIDTH < HEIGHT)
		delegate.setPageSize(orientation==PageOrientation.LANDSCAPE ? pageSize.rotate() : pageSize);
		delegate.newPage();
		return new Page(pdfWriter.getDirectContent());
	}

	@Override
	public void close() {
		delegate.close();
	}

	/* (non-Javadoc)
	 * @see org.oss.pdfreporter.pdf.IDocument#registerTrueTypeFont(java.lang.String, boolean)
	 * Implementation note: the font is only registered but not attached or embedded 
	 */
	@Override
	public void registerTrueTypeFont(String pathToFont, boolean embed) {
		FontFactory.register(pathToFont);
		
	}

	/* (non-Javadoc)
	 * @see org.oss.pdfreporter.pdf.IDocument#registerTrueTypeFonts(java.lang.String, boolean)
	 * Implementation note: the fonts are only registered but not attached or embedded
	 */
	@Override
	public void registerTrueTypeFonts(String directory, boolean embed) {
		FontFactory.registerDirectory(directory);
	}
}
