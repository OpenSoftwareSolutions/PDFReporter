package org.oss.pdfreporter.pdf;

import org.oss.pdfreporter.pdf.IEncryption.KeyLength;
import org.oss.pdfreporter.registry.ISessionObject;

/**
 * STUB FOR COMPILATION ONLY
 * @author donatmuller, 2013, last change 10:04:42 AM
 */
public class Document implements IDocument {

	Document(String path) {
	}
	
	@Override
	public void dispose() {
	}

	@Override
	public void get(String key) {
	}

	@Override
	public void put(String key, ISessionObject value) {
	}

	@Override
	public void remove(String key) {
	}

	@Override
	public void setAuthor(String author) {
	}

	@Override
	public void setCreator(String creator) {
	}

	@Override
	public void setTitle(String title) {
	}

	@Override
	public void setSubject(String subject) {
	}

	@Override
	public void setKeywords(String keywords) {
	}

	@Override
	public void setCompression(boolean compress) {
	}

	@Override
	public void setPdfVersion(char version) {
	}

	@Override
	public void setEncryption(KeyLength keyLength, String userPassword,
			String ownerPasswrod, int permission) throws DocumentException {
	}

	@Override
	public void setPdfConformance(ConformanceLevel level) {
	}

	@Override
	public void open() {
	}

	@Override
	public IPage newPage() {
		return null;
	}

	@Override
	public IPage newPage(PageOrientation orientation, int width, int height) {
		return null;
	}

	@Override
	public void close() {
	}

	@Override
	public void registerTrueTypeFont(String font, boolean embed) {
	}

	@Override
	public void registerTrueTypeFonts(String directory, boolean embed) {
	}

}
