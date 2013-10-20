package org.oss.pdfreporter.pdf;

import org.oss.pdfreporter.registry.ISessionListener;

/**
 * A PDF Document representation
 * @author donatmuller, 2013, last change 1:58:44 AM
 * 
 */
public interface IDocument extends  ISessionListener {
	/**
	 * PDF Conformance Level
	 */
	public enum ConformanceLevel {
		PDF_1A,
		PDF_1B		
	};
	/**
	 * Page orientation
	 */
	public enum PageOrientation {
		PORTRAIT,
		LANDSCAPE		
	};
	/**
	 * Page orientation
	 */
	public final static int PERMISSION_READ = 1;
	public final static int PERMISSION_PRINT = 2;
	public final static int PERMISSION_EDIT_ALL = 4;
	public final static int PERMISSION_COPY = 8;
	public final static int PERMISSION_EDIT = 16;
	
	
	// TODO (23.05.2013, Open Software Solutions, Donat) Verify what is addViewerPreference and the meaning of Scaling (JRPDFExporter 529 - 539)
	// TODO (23.05.2013, Open Software Solutions, Donat) Verify what is createXmpMetadata and setRgbTransparencyBlending (JRPDFExporter 590 - 596)
	// TODO (23.05.2013, Open Software Solutions, Donat)Add PDFA Dictionary support (JRPDFExporter 602 - 619)
	
	/**
	 * Document author
	 * @param author
	 */
	void setAuthor(String author);
	/**
	 * Software that document was created with
	 * @param creator
	 */
	void setCreator(String creator);
	/**
	 * Document title
	 * @param title
	 */
	void setTitle(String title);
	/**
	 * Document subject
	 * @param subject
	 */
	void setSubject(String subject);
	/**
	 * Document keywords
	 * @param keywords
	 */
	void setKeywords(String keywords);
	/**
	 * Enables or disables document compression.<br>
	 * By default compression is enabled.
	 * @param compress
	 */
	void setCompression(boolean compress);
	/**
	 * Set PDF version (optional operation)
	 * An implementation might switch to a higher version when features used require it.
	 * @param version
	 */
	void setPdfVersion(char version);
	/**
	 * Set Document encryption
	 * @param keyLength
	 * @param userPassword
	 * @param ownerPasswrod
	 * @param permission
	 * @throws DocumentException 
	 */
	void setEncryption(IEncryption.KeyLength keyLength, String userPassword, String ownerPasswrod, int permission) throws DocumentException;
	/**
	 * Set PDF A/B
	 * @param level
	 */
	void setPdfConformance(ConformanceLevel level);
	/**
	 * Signals that all meta data was set and pages can be added. 
	 */
	void open();
	/**
	 * Create a new page A4 portrait
	 * @return
	 */
	IPage newPage();
	/**
	 * Create a new page with orientation and size
	 * @param orientation
	 * @param width
	 * @param height
	 * @return
	 */
	IPage newPage(PageOrientation orientation, int width, int height);
	/**
	 * Closes the document by flushing content to file or output stream and releases all associated resources.
	 */
	void close();
	/**
	 * Loads a font and attach it to the document.
	 * Optionally embeds font in the document
	 * @param font
	 * @param embed
	 */
	void registerTrueTypeFont(String font, boolean embed);
	/**
	 * Loads all TrueType fonts in a directory and attaches them to the document.
	 * Optionally embeds fonts in the document
	 * @param directory
	 * @param embed
	 */
	void registerTrueTypeFonts(String directory, boolean embed);
	
}
