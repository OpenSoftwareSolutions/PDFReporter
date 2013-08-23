package org.oss.pdfreporter.pdf.factory;

import org.oss.pdfreporter.pdf.DocumentException;
import org.oss.pdfreporter.pdf.IDocument;
import org.oss.pdfreporter.registry.ISessionCapable;

public interface IPdfFactory extends ISessionCapable {
	IDocument newDocument(String filePath) throws DocumentException;
}
