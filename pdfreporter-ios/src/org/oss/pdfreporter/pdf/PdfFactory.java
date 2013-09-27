package org.oss.pdfreporter.pdf;

public class PdfFactory extends AbstractPdfFactory {

	public PdfFactory(int maxDocuments) {
		super(maxDocuments);
	}

	@Override
	protected IDocument newDocumentInternal(String filePath) throws DocumentException {
		return null;
	}
	
	public static void registerFactory() {
		
	}
}
