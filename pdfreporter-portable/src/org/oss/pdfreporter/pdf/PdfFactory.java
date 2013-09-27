package org.oss.pdfreporter.pdf;


import org.oss.pdfreporter.pdf.AbstractPdfFactory;
import org.oss.pdfreporter.pdf.DocumentException;
import org.oss.pdfreporter.pdf.IDocument;
import org.oss.pdfreporter.registry.ApiRegistry;

public class PdfFactory extends AbstractPdfFactory {

	public static void registerFactory() {
		ApiRegistry.register(new PdfFactory());
	}
	
	private PdfFactory() {
		super(1);
	}
	

	@Override
	protected IDocument newDocumentInternal(String filePath)
			throws DocumentException {
		return new Document(filePath);
	}

}
