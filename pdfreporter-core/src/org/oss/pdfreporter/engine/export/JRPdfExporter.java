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

/*
 * Contributors:
 * Adrian Jackson - iapetus@users.sourceforge.net
 * David Taylor - exodussystems@users.sourceforge.net
 * Lars Kristensen - llk@users.sourceforge.net
 * Ling Li - lonecatz@users.sourceforge.net
 * Martin Clough - mtclough@users.sourceforge.net
 */
package org.oss.pdfreporter.engine.export;

//import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.engine.DefaultJasperReportsContext;
import org.oss.pdfreporter.engine.JRAbstractExporter;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRExporterParameter;
import org.oss.pdfreporter.engine.JRFont;
import org.oss.pdfreporter.engine.JRGenericPrintElement;
import org.oss.pdfreporter.engine.JRLineBox;
import org.oss.pdfreporter.engine.JRPen;
import org.oss.pdfreporter.engine.JRPrintElement;
import org.oss.pdfreporter.engine.JRPrintEllipse;
import org.oss.pdfreporter.engine.JRPrintFrame;
import org.oss.pdfreporter.engine.JRPrintImage;
import org.oss.pdfreporter.engine.JRPrintLine;
import org.oss.pdfreporter.engine.JRPrintPage;
import org.oss.pdfreporter.engine.JRPrintRectangle;
import org.oss.pdfreporter.engine.JRPrintText;
import org.oss.pdfreporter.engine.JRPropertiesUtil;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.engine.Renderable;
import org.oss.pdfreporter.engine.base.JRBaseFont;
import org.oss.pdfreporter.engine.export.legacy.BorderOffset;
import org.oss.pdfreporter.engine.type.LineDirectionEnum;
import org.oss.pdfreporter.engine.type.LineStyleEnum;
import org.oss.pdfreporter.engine.type.ModeEnum;
import org.oss.pdfreporter.engine.type.RenderableTypeEnum;
import org.oss.pdfreporter.engine.type.RotationEnum;
import org.oss.pdfreporter.engine.util.JRPdfaIccProfileNotFoundException;
import org.oss.pdfreporter.engine.util.JRStyledText;
import org.oss.pdfreporter.font.IFontManager;
import org.oss.pdfreporter.font.IFont.FontDecoration;
import org.oss.pdfreporter.font.IFont.FontStyle;
import org.oss.pdfreporter.font.factory.IFontFactory;
import org.oss.pdfreporter.font.text.TextAttribute;
import org.oss.pdfreporter.geometry.IAffineTransformMatrix;
import org.oss.pdfreporter.geometry.IColor;
import org.oss.pdfreporter.geometry.IDimension;
import org.oss.pdfreporter.geometry.IRectangle;
import org.oss.pdfreporter.geometry.factory.IGeometryFactory.Rotate90;
import org.oss.pdfreporter.image.IImage;
import org.oss.pdfreporter.pdf.DocumentException;
import org.oss.pdfreporter.pdf.IDocument;
import org.oss.pdfreporter.pdf.IEncryption;
import org.oss.pdfreporter.pdf.IPage;
import org.oss.pdfreporter.pdf.ParagraphRenderer;
import org.oss.pdfreporter.pdf.IDocument.ConformanceLevel;
import org.oss.pdfreporter.pdf.IDocument.PageOrientation;
import org.oss.pdfreporter.pdf.IPage.LineCap;
import org.oss.pdfreporter.pdf.IPage.ScaleMode;
import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.text.HorizontalAlignment;
import org.oss.pdfreporter.text.IPositionedLine;
import org.oss.pdfreporter.text.Paragraph;
import org.oss.pdfreporter.text.ParagraphText;
import org.oss.pdfreporter.text.PositionedLined;
import org.oss.pdfreporter.uses.java.awt.text.AttributedString;
import org.oss.pdfreporter.uses.java.awt.text.IAttributedCharacterIterator;
import org.oss.pdfreporter.uses.java.awt.text.IAttributedCharacterIterator.Attribute;





/**
 * Exports a JasperReports document to PDF format. It has binary output type and exports the document to
 * a free-form layout.
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRPdfExporter.java 5521 2012-07-30 13:25:30Z teodord $
 */
public class JRPdfExporter extends JRAbstractExporter
{

	private final static Logger logger = Logger.getLogger(JRPdfExporter.class.getName());
	
	public static final String PDF_EXPORTER_PROPERTIES_PREFIX = JRPropertiesUtil.PROPERTY_PREFIX + "export.pdf.";

	/**
	 * Prefix of properties that specify font files for the PDF exporter.
	 */
	public static final String PDF_FONT_FILES_PREFIX = PDF_EXPORTER_PROPERTIES_PREFIX + "font.";
	
	/**
	 * Prefix of properties that specify font directories for the PDF exporter.
	 */
	public static final String PDF_FONT_DIRS_PREFIX = PDF_EXPORTER_PROPERTIES_PREFIX + "fontdir.";

	/**
	 * The exporter key, as used in
	 * {@link GenericElementHandlerEnviroment#getHandler(org.oss.pdfreporter.engine.JRGenericElementType, String)}.
	 */
	public static final String PDF_EXPORTER_KEY = JRPropertiesUtil.PROPERTY_PREFIX + "pdf";
	
	private static final String EMPTY_BOOKMARK_TITLE = "";

	/**
	 *
	 */
	protected static final String JR_PAGE_ANCHOR_PREFIX = "JR_PAGE_ANCHOR_";

	protected static boolean fontsRegistered;

	protected class ExporterContext extends BaseExporterContext implements JRPdfExporterContext
	{
		public String getExportPropertiesPrefix()
		{
			return PDF_EXPORTER_PROPERTIES_PREFIX;
		}

		@Override
		public IDocument getPdfDocument() {
			return document;
		}

		@Override
		public IPage getCurrentPage() {
			return pdfPage;
		}
	}
	
	/**
	 *
	 */
	protected IDocument document;
	protected IPage pdfPage;

//	protected Document imageTesterDocument;
//	protected PdfContentByte imageTesterPdfContentByte;
	
//	protected JRPdfExporterTagHelper tagHelper = new JRPdfExporterTagHelper(this);

	protected JRExportProgressMonitor progressMonitor;

	protected int reportIndex;

	/**
	 *
	 */
	protected boolean forceSvgShapes;
	protected boolean isCreatingBatchModeBookmarks;
	protected boolean isCompressed;
	protected boolean isEncrypted;
	protected boolean is128BitKey;
	protected String userPassword;
	protected String ownerPassword;
	protected int permissions;
	protected Character pdfVersion;
	protected String pdfJavaScript;
	protected String printScaling;
	
	private boolean collapseMissingBookmarkLevels;

	/**
	 *
	 */
//	protected Map<Renderable,com.lowagie.text.Image> loadedImagesMap;
//	protected Image pxImage;

//	private BookmarkStack bookmarkStack;

	@SuppressWarnings("deprecation")
	private Map<FontKey,PdfFont> pdfFontMap;

//	private SplitCharacter splitCharacter;
	
	protected JRPdfExporterContext exporterContext = new ExporterContext();
	
	/**
	 * @see #JRPdfExporter(JasperReportsContext)
	 */
	public JRPdfExporter()
	{
		this(DefaultJasperReportsContext.getInstance());
	}

	
	/**
	 *
	 */
	public JRPdfExporter(JasperReportsContext jasperReportsContext)
	{
		super(jasperReportsContext);
	}
	

	/**
	 *
	 */
//	protected Image getPxImage()
//	{
//		if (pxImage == null)
//		{
//			try
//			{
//				pxImage =
//					Image.getInstance(
//						JRLoader.loadBytesFromResource("net/sf/jasperreports/engine/images/pixel.GIF")
//						);
//			}
//			catch(Exception e)
//			{
//				throw new JRRuntimeException(e);
//			}
//		}
//
//		return pxImage;
//	}


	/**
	 *
	 */
	public void exportReport() throws JRException
	{
		registerFonts();

		progressMonitor = (JRExportProgressMonitor)parameters.get(JRExporterParameter.PROGRESS_MONITOR);

		/*   */
		setOffset();

		try
		{
			/*   */
			setExportContext();

			/*   */
			setInput();
	
			if (!parameters.containsKey(JRExporterParameter.FILTER))
			{
				filter = createFilter(PDF_EXPORTER_PROPERTIES_PREFIX);
			}

			/*   */
			if (!isModeBatch)
			{
				setPageRange();
			}

			isCreatingBatchModeBookmarks = 
				getBooleanParameter(
					JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS,
					JRPdfExporterParameter.PROPERTY_CREATE_BATCH_MODE_BOOKMARKS,
					false
					);

			forceSvgShapes = //FIXME certain properties need to be read from each individual document in batch mode; check all exporters and all props
				getBooleanParameter(
					JRPdfExporterParameter.FORCE_SVG_SHAPES,
					JRPdfExporterParameter.PROPERTY_FORCE_SVG_SHAPES,
					false
					);

			isCompressed = 
				getBooleanParameter(
					JRPdfExporterParameter.IS_COMPRESSED,
					JRPdfExporterParameter.PROPERTY_COMPRESSED,
					false
					);

			isEncrypted = 
				getBooleanParameter(
					JRPdfExporterParameter.IS_ENCRYPTED,
					JRPdfExporterParameter.PROPERTY_ENCRYPTED,
					false
					);

			is128BitKey = 
				getBooleanParameter(
					JRPdfExporterParameter.IS_128_BIT_KEY,
					JRPdfExporterParameter.PROPERTY_128_BIT_KEY,
					false
					);

			userPassword = 
				getStringParameter(
					JRPdfExporterParameter.USER_PASSWORD,
					JRPdfExporterParameter.PROPERTY_USER_PASSWORD
					);

			ownerPassword = 
				getStringParameter(
					JRPdfExporterParameter.OWNER_PASSWORD,
					JRPdfExporterParameter.PROPERTY_OWNER_PASSWORD
					);

			Integer permissionsParameter = (Integer)parameters.get(JRPdfExporterParameter.PERMISSIONS);
			if (permissionsParameter != null)
			{
				permissions = permissionsParameter.intValue();
			}

			pdfVersion = 
				getCharacterParameter(
						JRPdfExporterParameter.PDF_VERSION,
						JRPdfExporterParameter.PROPERTY_PDF_VERSION
						);

			setFontMap();
			setSplitCharacter();
			setHyperlinkProducerFactory();

			pdfJavaScript = 
				getStringParameter(
					JRPdfExporterParameter.PDF_JAVASCRIPT,
					JRPdfExporterParameter.PROPERTY_PDF_JAVASCRIPT
					);

			printScaling =
				getStringParameter(
					JRPdfExporterParameter.PRINT_SCALING,
					JRPdfExporterParameter.PROPERTY_PRINT_SCALING
					);

//			tagHelper.setTagged( 
//				getBooleanParameter(
//					JRPdfExporterParameter.IS_TAGGED,
//					JRPdfExporterParameter.PROPERTY_TAGGED,
//					false
//					)
//				);
			
			// no export param for this
			collapseMissingBookmarkLevels = JRPropertiesUtil.getInstance(jasperReportsContext).getBooleanProperty(jasperPrint, 
					JRPdfExporterParameter.PROPERTY_COLLAPSE_MISSING_BOOKMARK_LEVELS, false);

			String fileName = (String)parameters.get(JRExporterParameter.OUTPUT_FILE_NAME);
			if (fileName==null) {
				File destFile = (File)parameters.get(JRExporterParameter.OUTPUT_FILE);
				if (destFile == null) {
					throw new JRException("Only export to file by file or filename is supported.");
				}
				fileName = destFile.getAbsolutePath();
			} 
			exportToFile(fileName);				
		}
		finally
		{
			resetExportContext();
		}
	}


	@SuppressWarnings("deprecation")
	protected void setFontMap()
	{
		pdfFontMap = (Map<FontKey,PdfFont>) parameters.get(JRExporterParameter.FONT_MAP);
	}


	protected void setSplitCharacter()
	{
		boolean useFillSplitCharacter;
		Boolean useFillSplitCharacterParam = (Boolean) parameters.get(JRPdfExporterParameter.FORCE_LINEBREAK_POLICY);
		if (useFillSplitCharacterParam == null)
		{
			useFillSplitCharacter = 
				JRPropertiesUtil.getInstance(jasperReportsContext).getBooleanProperty(
					jasperPrint.getPropertiesMap(),
					JRPdfExporterParameter.PROPERTY_FORCE_LINEBREAK_POLICY,
					false
					);
		}
		else
		{
			useFillSplitCharacter = useFillSplitCharacterParam.booleanValue();
		}

//		if (useFillSplitCharacter)
//		{
//			splitCharacter = new BreakIteratorSplitCharacter();
//		}
	}


	/**
	 *
	 */
	protected void exportToFile(String fileName) throws JRException
	{

		boolean sizePageToContent = JRPropertiesUtil.getInstance(jasperReportsContext).getBooleanProperty(jasperPrint, JRPdfExporterParameter.PROPERTY_SIZE_PAGE_TO_CONTENT, false);
		boolean closeDocuments = true;
		try
		{
			document = ApiRegistry.getPdfFactory().newDocument(fileName);

//			tagHelper.setPdfWriter(pdfWriter);
			
			if (pdfVersion != null)
			{
				document.setPdfVersion(pdfVersion.charValue());
			}
			document.setCompression(isCompressed);
			if (isEncrypted)
			{
				document.setEncryption(
					is128BitKey ? IEncryption.KeyLength.ENCRYPTION_128 : IEncryption.KeyLength.ENCRYPTION_40,
					userPassword,
					ownerPassword,
					permissions
					);
			}
			

			if (printScaling != null) 
			{
//				if (JRPdfExporterParameter.PRINT_SCALING_DEFAULT.equals(printScaling))
//				{
//					pdfWriter.addViewerPreference(PdfName.PRINTSCALING, PdfName.APPDEFAULT);
//				}
//				else if (JRPdfExporterParameter.PRINT_SCALING_NONE.equals(printScaling))
//				{
//					pdfWriter.addViewerPreference(PdfName.PRINTSCALING, PdfName.NONE);
//				}
			}

			// Add meta-data parameters to generated PDF document
			// mtclough@users.sourceforge.net 2005-12-05
			String title = (String)parameters.get(JRPdfExporterParameter.METADATA_TITLE);
			if( title != null )
			{
				document.setTitle(title);
			}
			String author = (String)parameters.get(JRPdfExporterParameter.METADATA_AUTHOR);
			if( author != null )
			{
				document.setAuthor(author);
			}
			String subject = (String)parameters.get(JRPdfExporterParameter.METADATA_SUBJECT);
			if( subject != null )
			{
				document.setSubject(subject);
			}
			String keywords = (String)parameters.get(JRPdfExporterParameter.METADATA_KEYWORDS);
			if( keywords != null )
			{
				document.setKeywords(keywords);
			}
			String creator = (String)parameters.get(JRPdfExporterParameter.METADATA_CREATOR);
			if( creator != null )
			{
				document.setCreator(creator);
			}
			else
			{
				document.setCreator("JasperReports (" + jasperPrint.getName() + ")");
			}
			
			// BEGIN: PDF/A support
			String pdfaConformance = getStringParameter( JRPdfExporterParameter.PDFA_CONFORMANCE, JRPdfExporterParameter.PROPERTY_PDFA_CONFORMANCE);
			boolean gotPdfa = false;
			if (pdfaConformance != null && !JRPdfExporterParameter.PDFA_CONFORMANCE_NONE.equalsIgnoreCase(pdfaConformance)) 
			{
				if (JRPdfExporterParameter.PDFA_CONFORMANCE_1A.equalsIgnoreCase(pdfaConformance))
				{
					document.setPdfConformance(ConformanceLevel.PDF_1A);
					gotPdfa = true;
				}
				else if (JRPdfExporterParameter.PDFA_CONFORMANCE_1B.equalsIgnoreCase(pdfaConformance))
				{
					document.setPdfConformance(ConformanceLevel.PDF_1B);
					gotPdfa = true;
				}
			}

			if (gotPdfa) 
			{
//				pdfWriter.createXmpMetadata();
//			} else 
//			{
//				pdfWriter.setRgbTransparencyBlending(true);
			}
			// END: PDF/A support

			document.open();
			
			// BEGIN: PDF/A support
			if (gotPdfa) {
				String iccProfilePath = getStringParameter( JRPdfExporterParameter.PDFA_ICC_PROFILE_PATH, JRPdfExporterParameter.PROPERTY_PDFA_ICC_PROFILE_PATH);
				if (iccProfilePath != null) {
//					PdfDictionary pdfDictionary = new PdfDictionary(PdfName.OUTPUTINTENT);
//					pdfDictionary.put(PdfName.OUTPUTCONDITIONIDENTIFIER, new PdfString("sRGB IEC61966-2.1"));
//					pdfDictionary.put(PdfName.INFO, new PdfString("sRGB IEC61966-2.1"));
//					pdfDictionary.put(PdfName.S, PdfName.GTS_PDFA1);
//					
//					InputStream iccIs = RepositoryUtil.getInstance(jasperReportsContext).getInputStreamFromLocation(iccProfilePath);
//					PdfICCBased pdfICCBased = new PdfICCBased(AwtUnmarshaller.getIIC_Profile(ICC_Profile.getInstance(iccIs)));
//					pdfICCBased.remove(PdfName.ALTERNATE);
//					pdfDictionary.put(PdfName.DESTOUTPUTPROFILE, pdfWriter.addToBody(pdfICCBased).getIndirectReference());
//
//					pdfWriter.getExtraCatalog().put(PdfName.OUTPUTINTENTS, new PdfArray(pdfDictionary));
				} else {
					throw new JRPdfaIccProfileNotFoundException();
				}
			}
			// END: PDF/A support
			
			if(pdfJavaScript != null)
			{
//				pdfWriter.addJavaScript(pdfJavaScript);
			}


//			tagHelper.init(page);
			
//			initBookmarks();

//			PdfWriter imageTesterPdfWriter =
//				PdfWriter.getInstance(
//					imageTesterDocument,
//					new NullOutputStream() // discard the output
//					);
//			imageTesterDocument.open();
//			imageTesterDocument.newPage();
//			imageTesterPdfContentByte = imageTesterPdfWriter.getDirectContent();
//			imageTesterPdfContentByte.setLiteral("\n");

			for(reportIndex = 0; reportIndex < jasperPrintList.size(); reportIndex++)
			{
				setJasperPrint(jasperPrintList.get(reportIndex));
//				loadedImagesMap = new HashMap<Renderable,com.lowagie.text.Image>();
				
				
				BorderOffset.setLegacy(
					JRPropertiesUtil.getInstance(jasperReportsContext).getBooleanProperty(jasperPrint, BorderOffset.PROPERTY_LEGACY_BORDER_OFFSET, false)
					);
				

				List<JRPrintPage> pages = jasperPrint.getPages();
				if (pages != null && pages.size() > 0)
				{
					if (isModeBatch)
					{
						throw new JRException("Batchmode is not supported.");
//						pdfPage = document.newPage();
//
//						if( isCreatingBatchModeBookmarks )
//						{
//							//add a new level to our outline for this report
//							addBookmark(0, jasperPrint.getName(), 0, 0);
//						}
//
//						startPageIndex = 0;
//						endPageIndex = pages.size() - 1;
					}

					for(int pageIndex = startPageIndex; pageIndex <= endPageIndex; pageIndex++)
					{
						if (Thread.interrupted())
						{
							throw new JRException("Current thread interrupted.");
						}

						JRPrintPage page = pages.get(pageIndex);

						if (sizePageToContent)
						{
							newPage(page);
						} else {
							newPage(null);
						}
						
						

						pdfPage.setLineCap(LineCap.PROJECTING_SCUARE_END);//PdfContentByte.LINE_CAP_PROJECTING_SQUARE since iText 1.02b

//						writePageAnchor(pageIndex);

						/*   */
						exportPage(page);
					}
				}
// TODO Strange case				
//				else
//				{
//					pdfPage = document.newPage();
//				}
			}

			closeDocuments = false;
			document.close();
//			imageTesterDocument.close();
		}
		catch(DocumentException e)
		{
			throw new JRException("PDF Document error : " + jasperPrint.getName(), e);
		}
		catch(IOException e)
		{
			throw new JRException("Error generating PDF report : " + jasperPrint.getName(), e);
		}
		finally
		{
			if (closeDocuments) //only on exception
			{
				try
				{
					document.close();
				}
				catch (Exception e)
				{
					// ignore, let the original exception propagate
				}

				try
				{
//					imageTesterDocument.close();
				}
				catch (Exception e)
				{
					// ignore, let the original exception propagate
				}
			}
		}

		//return os.toByteArray();
	}


	protected void writePageAnchor(int pageIndex) throws DocumentException 
	{
//		Map<Attribute,Object> attributes = new HashMap<Attribute,Object>();
//		FontUtil.getInstance(jasperReportsContext).getAttributesWithoutAwtFont(attributes, new JRBasePrintText(jasperPrint.getDefaultStyleProvider()));
//		// TODO (28.03.2013, Donat, Digireport): Think about AWT and PDFFont representation and mapping
//		Font pdfFont = getFont(attributes, getLocale(), false);
//		Chunk chunk = new Chunk(" ", pdfFont);
//		// TODO (28.03.2013, Donat, Digireport): Implement and use Chunks and Phrases
//		// IChunk chunk = IRegistry.getITextFactory().newChunk(" ", pdfFont);
//		
//		chunk.setLocalDestination(JR_PAGE_ANCHOR_PREFIX + reportIndex + "_" + (pageIndex + 1));
//
//		tagHelper.startPageAnchor();
//		
//		ColumnText colText = new ColumnText(pdfPage);
//		colText.setSimpleColumn(
//			new Phrase(chunk),
//			0,
//			jasperPrint.getPageHeight(),
//			1,
//			1,
//			0,
//			Element.ALIGN_LEFT
//			);
//
//		colText.go();
//
//		tagHelper.endPageAnchor();
	}

	/**
	 * Creates a new PDFPage for the JRPrintPage
	 */
	protected void newPage(JRPrintPage page) throws JRException, DocumentException, IOException
	{
		int pageWidth = jasperPrint.getPageWidth(); 
		int pageHeight = jasperPrint.getPageHeight();
		
		if (page != null)
		{
			Collection<JRPrintElement> elements = page.getElements();
			for (JRPrintElement element : elements)
			{
				int elementRight = element.getX() + element.getWidth();
				int elementBottom = element.getY() + element.getHeight();
				pageWidth = pageWidth < elementRight ? elementRight : pageWidth;
				pageHeight = pageHeight < elementBottom ? elementBottom : pageHeight;
			}
			
			pageWidth += jasperPrint.getRightMargin();
			pageHeight += jasperPrint.getBottomMargin();
		}
		
		switch (jasperPrint.getOrientationValue())
		{
		case LANDSCAPE:
			pdfPage = document.newPage(PageOrientation.LANDSCAPE, pageHeight, pageWidth);
			break;
		default:
			pdfPage = document.newPage(PageOrientation.PORTRAIT, pageWidth, pageHeight);
			break;
		}
	}

	/**
	 *
	 */
	protected void exportPage(JRPrintPage page) throws JRException, DocumentException, IOException
	{
//		tagHelper.startPage();
		
		Collection<JRPrintElement> elements = page.getElements();
		exportElements(elements);
		
//		tagHelper.endPage();

		if (progressMonitor != null)
		{
			progressMonitor.afterPageExport();
		}
	}

	protected void exportElements(Collection<JRPrintElement> elements) throws DocumentException, IOException, JRException
	{
		if (elements != null && elements.size() > 0)
		{
			for(Iterator<JRPrintElement> it = elements.iterator(); it.hasNext();)
			{
				JRPrintElement element = it.next();

				if (filter == null || filter.isToExport(element))
				{
//					tagHelper.startElement(element);

					if (element instanceof JRPrintLine)
					{
						exportLine((JRPrintLine)element);
					}
					else if (element instanceof JRPrintRectangle)
					{
						exportRectangle((JRPrintRectangle)element);
					}
					else if (element instanceof JRPrintEllipse)
					{
						exportEllipse((JRPrintEllipse)element);
					}
					else if (element instanceof JRPrintImage)
					{
						exportImage((JRPrintImage)element);
					}
					else if (element instanceof JRPrintText)
					{
						exportText((JRPrintText)element);
					}
					else if (element instanceof JRPrintFrame)
					{
						exportFrame((JRPrintFrame)element);
					}
					else if (element instanceof JRGenericPrintElement)
					{
						exportGenericElement((JRGenericPrintElement) element);
					}

//					tagHelper.endElement(element);
				}
			}
		}
	}


	/**
	 *
	 */
	protected void exportLine(JRPrintLine line)
	{
		float lineWidth = line.getLinePen().getLineWidth().floatValue(); 
		if (lineWidth > 0f)
		{
			preparePen(pdfPage, line.getLinePen(), LineCap.BUTT_END);

			if (line.getWidth() == 1)
			{
				if (line.getHeight() != 1)
				{
					//Vertical line
					if (line.getLinePen().getLineStyleValue() == LineStyleEnum.DOUBLE)
					{
						pdfPage.moveTo(
							line.getX() + getOffsetX() + 0.5f - lineWidth / 3,
							jasperPrint.getPageHeight() - line.getY() - getOffsetY()
							);
						pdfPage.lineTo(
							line.getX() + getOffsetX() + 0.5f - lineWidth / 3,
							jasperPrint.getPageHeight() - line.getY() - getOffsetY() - line.getHeight()
							);

						pdfPage.stroke();
						
						pdfPage.moveTo(
							line.getX() + getOffsetX() + 0.5f + lineWidth / 3,
							jasperPrint.getPageHeight() - line.getY() - getOffsetY()
							);
						pdfPage.lineTo(
							line.getX() + getOffsetX() + 0.5f + lineWidth / 3,
							jasperPrint.getPageHeight() - line.getY() - getOffsetY() - line.getHeight()
							);
					}
					else
					{
						pdfPage.moveTo(
							line.getX() + getOffsetX() + 0.5f,
							jasperPrint.getPageHeight() - line.getY() - getOffsetY()
							);
						pdfPage.lineTo(
							line.getX() + getOffsetX() + 0.5f,
							jasperPrint.getPageHeight() - line.getY() - getOffsetY() - line.getHeight()
							);
					}
				}
			}
			else
			{
				if (line.getHeight() == 1)
				{
					//Horizontal line
					if (line.getLinePen().getLineStyleValue() == LineStyleEnum.DOUBLE)
					{
						pdfPage.moveTo(
							line.getX() + getOffsetX(),
							jasperPrint.getPageHeight() - line.getY() - getOffsetY() - 0.5f + lineWidth / 3
							);
						pdfPage.lineTo(
							line.getX() + getOffsetX() + line.getWidth(),
							jasperPrint.getPageHeight() - line.getY() - getOffsetY() - 0.5f + lineWidth / 3
							);

						pdfPage.stroke();
						
						pdfPage.moveTo(
							line.getX() + getOffsetX(),
							jasperPrint.getPageHeight() - line.getY() - getOffsetY() - 0.5f - lineWidth / 3
							);
						pdfPage.lineTo(
							line.getX() + getOffsetX() + line.getWidth(),
							jasperPrint.getPageHeight() - line.getY() - getOffsetY() - 0.5f - lineWidth / 3
							);
					}
					else
					{
						pdfPage.moveTo(
							line.getX() + getOffsetX(),
							jasperPrint.getPageHeight() - line.getY() - getOffsetY() - 0.5f
							);
						pdfPage.lineTo(
							line.getX() + getOffsetX() + line.getWidth(),
							jasperPrint.getPageHeight() - line.getY() - getOffsetY() - 0.5f
							);
					}
				}
				else
				{
					//Oblique line
					if (line.getDirectionValue() == LineDirectionEnum.TOP_DOWN)
					{
						if (line.getLinePen().getLineStyleValue() == LineStyleEnum.DOUBLE)
						{
							double xtrans = lineWidth / (3 * Math.sqrt(1 + Math.pow(line.getWidth(), 2) / Math.pow(line.getHeight(), 2))); 
							double ytrans = lineWidth / (3 * Math.sqrt(1 + Math.pow(line.getHeight(), 2) / Math.pow(line.getWidth(), 2))); 
							
							pdfPage.moveTo(
								line.getX() + getOffsetX() + (float)xtrans,
								jasperPrint.getPageHeight() - line.getY() - getOffsetY() + (float)ytrans
								);
							pdfPage.lineTo(
								line.getX() + getOffsetX() + line.getWidth() + (float)xtrans,
								jasperPrint.getPageHeight() - line.getY() - getOffsetY() - line.getHeight() + (float)ytrans
								);

							pdfPage.stroke();
							
							pdfPage.moveTo(
								line.getX() + getOffsetX() - (float)xtrans,
								jasperPrint.getPageHeight() - line.getY() - getOffsetY() - (float)ytrans
								);
							pdfPage.lineTo(
								line.getX() + getOffsetX() + line.getWidth() - (float)xtrans,
								jasperPrint.getPageHeight() - line.getY() - getOffsetY() - line.getHeight() - (float)ytrans
								);
						}
						else
						{
							pdfPage.moveTo(
								line.getX() + getOffsetX(),
								jasperPrint.getPageHeight() - line.getY() - getOffsetY()
								);
							pdfPage.lineTo(
								line.getX() + getOffsetX() + line.getWidth(),
								jasperPrint.getPageHeight() - line.getY() - getOffsetY() - line.getHeight()
								);
						}
					}
					else
					{
						if (line.getLinePen().getLineStyleValue() == LineStyleEnum.DOUBLE)
						{
							double xtrans = lineWidth / (3 * Math.sqrt(1 + Math.pow(line.getWidth(), 2) / Math.pow(line.getHeight(), 2))); 
							double ytrans = lineWidth / (3 * Math.sqrt(1 + Math.pow(line.getHeight(), 2) / Math.pow(line.getWidth(), 2))); 
							
							pdfPage.moveTo(
								line.getX() + getOffsetX() + (float)xtrans,
								jasperPrint.getPageHeight() - line.getY() - getOffsetY() - line.getHeight() - (float)ytrans
								);
							pdfPage.lineTo(
								line.getX() + getOffsetX() + line.getWidth() + (float)xtrans,
								jasperPrint.getPageHeight() - line.getY() - getOffsetY() - (float)ytrans
								);

							pdfPage.stroke();

							pdfPage.moveTo(
								line.getX() + getOffsetX() - (float)xtrans,
								jasperPrint.getPageHeight() - line.getY() - getOffsetY() - line.getHeight() + (float)ytrans
								);
							pdfPage.lineTo(
								line.getX() + getOffsetX() + line.getWidth() - (float)xtrans,
								jasperPrint.getPageHeight() - line.getY() - getOffsetY() + (float)ytrans
								);
						}
						else
						{
							pdfPage.moveTo(
								line.getX() + getOffsetX(),
								jasperPrint.getPageHeight() - line.getY() - getOffsetY() - line.getHeight()
								);
							pdfPage.lineTo(
								line.getX() + getOffsetX() + line.getWidth(),
								jasperPrint.getPageHeight() - line.getY() - getOffsetY()
								);
						}
					}
				}
			}

			pdfPage.stroke();

			pdfPage.setLineDash(null,0);
			pdfPage.setLineCap(LineCap.PROJECTING_SCUARE_END);
		}
	}


	/**
	 *
	 */
	protected void exportRectangle(JRPrintRectangle rectangle)
	{
		pdfPage.setRGBColorFill(rectangle.getBackcolor());

		preparePen(pdfPage, rectangle.getLinePen(), LineCap.PROJECTING_SCUARE_END);

		float lineWidth = rectangle.getLinePen().getLineWidth().floatValue();
		float lineOffset = BorderOffset.getOffset(rectangle.getLinePen());
		
		if (rectangle.getModeValue() == ModeEnum.OPAQUE)
		{
			pdfPage.roundRectangle(
				rectangle.getX() + getOffsetX(),
				jasperPrint.getPageHeight() - rectangle.getY() - getOffsetY() - rectangle.getHeight(),
				rectangle.getWidth(),
				rectangle.getHeight(),
				rectangle.getRadius()
				);

			pdfPage.fill();
		}

		if (lineWidth > 0f)
		{
			if (rectangle.getLinePen().getLineStyleValue() == LineStyleEnum.DOUBLE)
			{
				
				drawRoundRect(pdfPage,
					rectangle.getX() + getOffsetX() - lineWidth / 3,
					jasperPrint.getPageHeight() - rectangle.getY() - getOffsetY() - rectangle.getHeight() - lineWidth / 3,
					rectangle.getWidth() + 2 * lineWidth / 3,
					rectangle.getHeight() + 2 * lineWidth / 3,
					rectangle.getRadius()
					);

				pdfPage.stroke();
				
				drawRoundRect(pdfPage,
					rectangle.getX() + getOffsetX() + lineWidth / 3,
					jasperPrint.getPageHeight() - rectangle.getY() - getOffsetY() - rectangle.getHeight() + lineWidth / 3,
					rectangle.getWidth() - 2 * lineWidth / 3,
					rectangle.getHeight() - 2 * lineWidth / 3,
					rectangle.getRadius()
					);
				
				pdfPage.stroke();
			}
			else
			{
				drawRoundRect(pdfPage,
					rectangle.getX() + getOffsetX() + lineOffset,
					jasperPrint.getPageHeight() - rectangle.getY() - getOffsetY() - rectangle.getHeight() + lineOffset,
					rectangle.getWidth() - 2 * lineOffset,
					rectangle.getHeight() - 2 * lineOffset,
					rectangle.getRadius()
					);

				pdfPage.stroke();
			}
		}

		pdfPage.setLineDash(null,0);
	}


	/**
	 *
	 */
	protected void exportEllipse(JRPrintEllipse ellipse)
	{
		pdfPage.setRGBColorFill(ellipse.getBackcolor());

		preparePen(pdfPage, ellipse.getLinePen(), LineCap.PROJECTING_SCUARE_END);

		float lineWidth = ellipse.getLinePen().getLineWidth().floatValue();
		float lineOffset = BorderOffset.getOffset(ellipse.getLinePen());
		
		if (ellipse.getModeValue() == ModeEnum.OPAQUE)
		{
			pdfPage.ellipse(
				ellipse.getX() + getOffsetX(),
				jasperPrint.getPageHeight() - ellipse.getY() - getOffsetY() - ellipse.getHeight(),
				ellipse.getX() + getOffsetX() + ellipse.getWidth(),
				jasperPrint.getPageHeight() - ellipse.getY() - getOffsetY()
				);

			pdfPage.fill();
		}

		if (lineWidth > 0f)
		{
			if (ellipse.getLinePen().getLineStyleValue() == LineStyleEnum.DOUBLE)
			{
				drawEllipse(pdfPage,
					ellipse.getX() + getOffsetX() - lineWidth / 3,
					jasperPrint.getPageHeight() - ellipse.getY() - getOffsetY() - ellipse.getHeight() - lineWidth / 3,
					ellipse.getX() + getOffsetX() + ellipse.getWidth() + lineWidth / 3,
					jasperPrint.getPageHeight() - ellipse.getY() - getOffsetY() + lineWidth / 3
					);

				pdfPage.stroke();

				drawEllipse(pdfPage,
					ellipse.getX() + getOffsetX() + lineWidth / 3,
					jasperPrint.getPageHeight() - ellipse.getY() - getOffsetY() - ellipse.getHeight() + lineWidth / 3,
					ellipse.getX() + getOffsetX() + ellipse.getWidth() - lineWidth / 3,
					jasperPrint.getPageHeight() - ellipse.getY() - getOffsetY() - lineWidth / 3
					);

				pdfPage.stroke();
			}
			else
			{
				drawEllipse(pdfPage,
					ellipse.getX() + getOffsetX() + lineOffset,
					jasperPrint.getPageHeight() - ellipse.getY() - getOffsetY() - ellipse.getHeight() + lineOffset,
					ellipse.getX() + getOffsetX() + ellipse.getWidth() - lineOffset,
					jasperPrint.getPageHeight() - ellipse.getY() - getOffsetY() - lineOffset
					);

				pdfPage.stroke();
			}
		}

		pdfPage.setLineDash(null,0);
	}


	/**
	 *
	 */
	// TODO (15.06.2013, Donat, Digireport): Replace Image loading with new Factory
	public void exportImage(JRPrintImage printImage) throws DocumentException, IOException,  JRException
	{
		if (printImage.getModeValue() == ModeEnum.OPAQUE)
		{
			pdfPage.setRGBColorFill(printImage.getBackcolor());
			pdfPage.rectangle(
				printImage.getX() + getOffsetX(),
				jasperPrint.getPageHeight() - printImage.getY() - getOffsetY(),
				printImage.getWidth(),
				- printImage.getHeight()
				);
			pdfPage.fill();
		}

		int topPadding = printImage.getLineBox().getTopPadding().intValue();
		int leftPadding = printImage.getLineBox().getLeftPadding().intValue();
		int bottomPadding = printImage.getLineBox().getBottomPadding().intValue();
		int rightPadding = printImage.getLineBox().getRightPadding().intValue();

		int availableImageWidth = printImage.getWidth() - leftPadding - rightPadding;
		availableImageWidth = (availableImageWidth < 0)?0:availableImageWidth;

		int availableImageHeight = printImage.getHeight() - topPadding - bottomPadding;
		availableImageHeight = (availableImageHeight < 0)?0:availableImageHeight;

		Renderable renderer = printImage.getRenderable();


		if (renderer != null)
		{
			int xoffset = 0; 
			int yoffset = 0;
			ScaleMode scaleMode = null;

			IDimension imageDimension = renderer.getDimension(jasperReportsContext);

			if (renderer.getTypeValue() == RenderableTypeEnum.IMAGE)
			{

				float xalignFactor = getXAlignFactor(printImage);
				float yalignFactor = getYAlignFactor(printImage);

				
				switch(printImage.getScaleImageValue())
				{
					case CLIP :
					{
						scaleMode = ScaleMode.NONE;
						// horizontal and vertical alignment offset for unscaled image
						xoffset = (int)(xalignFactor * (availableImageWidth - imageDimension.getWidth()));
						yoffset = (int)(yalignFactor * (availableImageHeight - imageDimension.getHeight()));
						if (xalignFactor!=0 || yalignFactor!=0) {
							logger.finest("xoffset: " + xalignFactor + " * (" + availableImageWidth + " - " + imageDimension.getWidth() + ") = " + xoffset);
							logger.finest("yoffset: " + yalignFactor + " * (" + availableImageHeight + " - " + imageDimension.getHeight() + ") = " + yoffset);
						}
						break;
					}
					case FILL_FRAME :
					{
						scaleMode = ScaleMode.SCALE;
						break;
					}
					case RETAIN_SHAPE :
					default :
					{
						scaleMode = ScaleMode.SIZE;
						
						// calc image scale factors to retain aspect ratio and fit into boundaries
						float scaleX = availableImageWidth * 1.0f / imageDimension.getWidth();
						float scaleY = availableImageHeight * 1.0f / imageDimension.getHeight();
						float scale = Math.min(scaleX, scaleY);
						float scaledWidth = imageDimension.getWidth() * scale ;
						float scaledHeight = imageDimension.getHeight() * scale ;
						
						// horizontal and vertical alignment offset for scaled image						
						xoffset = (int)(xalignFactor * (availableImageWidth - scaledWidth));
						yoffset = (int)(yalignFactor * (availableImageHeight - scaledHeight));
						if (xalignFactor!=0 || yalignFactor!=0) {
							logger.finest("xoffset: " + xalignFactor + " * (" + availableImageWidth + " - " + scaledWidth + ") = " + xoffset);
							logger.finest("yoffset: " + yalignFactor + " * (" + availableImageHeight + " - " + scaledHeight + ") = " + yoffset);
						}
						break;
					}
				}

			}
			else
			{
				throw new JRException("SVG Images not supported.");
			}

			
			float x = printImage.getX() + getOffsetX() + leftPadding;
			float y = jasperPrint.getPageHeight() - printImage.getY() - printImage.getHeight() - getOffsetY() + topPadding;
			float width = printImage.getWidth() - leftPadding - rightPadding;
			float height = printImage.getHeight() - topPadding - bottomPadding;
			IImage image = renderer.getImage(jasperReportsContext);
			logger.finest("drawImage(x: " + x + "(+" + xoffset + "), y: " + y + "(+" + yoffset + "), width: " + width + ", height: " +  height + ", mode: " + scaleMode ); 
			if (xoffset>=0 && yoffset>=0) {
				pdfPage.draw(
						image, 
						x + xoffset,
						y + yoffset,
						width,
						height,
						scaleMode);				
			} else {				
				pdfPage.drawCropped(image, xoffset, yoffset, x, y, width, height);
			}
			
			
			//pdfPage.stroke(); // Calling stroke without ani graphiscs added
		}


		if (
			printImage.getLineBox().getTopPen().getLineWidth().floatValue() <= 0f &&
			printImage.getLineBox().getLeftPen().getLineWidth().floatValue() <= 0f &&
			printImage.getLineBox().getBottomPen().getLineWidth().floatValue() <= 0f &&
			printImage.getLineBox().getRightPen().getLineWidth().floatValue() <= 0f
			)
		{
			if (printImage.getLinePen().getLineWidth().floatValue() > 0f)
			{
				exportPen(printImage.getLinePen(), printImage);
			}
		}
		else
		{
			/*   */
			exportBox(
				printImage.getLineBox(),
				printImage
				);
		}
	}


	private float getXAlignFactor(JRPrintImage printImage)
	{
		float xalignFactor = 0f;
		switch (printImage.getHorizontalAlignmentValue())
		{
			case RIGHT :
			{
				xalignFactor = 1f;
				break;
			}
			case CENTER :
			{
				xalignFactor = 0.5f;
				break;
			}
			case LEFT :
			default :
			{
				xalignFactor = 0f;
				break;
			}
		}
		return xalignFactor;
	}


	private float getYAlignFactor(JRPrintImage printImage)
	{
		float yalignFactor = 0f;
		switch (printImage.getVerticalAlignmentValue())
		{
			case BOTTOM :
			{
				yalignFactor = 1f;
				break;
			}
			case MIDDLE :
			{
				yalignFactor = 0.5f;
				break;
			}
			case TOP :
			default :
			{
				yalignFactor = 0f;
				break;
			}
		}
		return yalignFactor;
	}


	/**
	 *
	 */
	// TODO (25.06.2013, Donat, Digireport): Add Hyperlink support later
//	protected void setHyperlinkInfo(Chunk chunk, JRPrintHyperlink link)
//	{
//		if (link != null)
//		{
//			switch(link.getHyperlinkTypeValue())
//			{
//				case REFERENCE :
//				{
//					if (link.getHyperlinkReference() != null)
//					{
//						switch(link.getHyperlinkTargetValue())
//						{
//							case BLANK :
//							{
//								chunk.setAction(
//									PdfAction.javaScript(
//										"if (app.viewerVersion < 7)"
//											+ "{this.getURL(\"" + link.getHyperlinkReference() + "\");}"
//											+ "else {app.launchURL(\"" + link.getHyperlinkReference() + "\", true);};",
//										pdfWriter
//										)
//									);
//								break;
//							}
//							case SELF :
//							default :
//							{
//								chunk.setAnchor(link.getHyperlinkReference());
//								break;
//							}
//						}
//					}
//					break;
//				}
//				case LOCAL_ANCHOR :
//				{
//					if (link.getHyperlinkAnchor() != null)
//					{
//						chunk.setLocalGoto(link.getHyperlinkAnchor());
//					}
//					break;
//				}
//				case LOCAL_PAGE :
//				{
//					if (link.getHyperlinkPage() != null)
//					{
//						chunk.setLocalGoto(JR_PAGE_ANCHOR_PREFIX + reportIndex + "_" + link.getHyperlinkPage().toString());
//					}
//					break;
//				}
//				case REMOTE_ANCHOR :
//				{
//					if (
//						link.getHyperlinkReference() != null &&
//						link.getHyperlinkAnchor() != null
//						)
//					{
//						chunk.setRemoteGoto(
//							link.getHyperlinkReference(),
//							link.getHyperlinkAnchor()
//							);
//					}
//					break;
//				}
//				case REMOTE_PAGE :
//				{
//					if (
//						link.getHyperlinkReference() != null &&
//						link.getHyperlinkPage() != null
//						)
//					{
//						chunk.setRemoteGoto(
//							link.getHyperlinkReference(),
//							link.getHyperlinkPage().intValue()
//							);
//					}
//					break;
//				}
//				case CUSTOM :
//				{
//					if (hyperlinkProducerFactory != null)
//					{
//						String hyperlink = hyperlinkProducerFactory.produceHyperlink(link);
//						if (hyperlink != null)
//						{
//							switch(link.getHyperlinkTargetValue())
//							{
//								case BLANK :
//								{
//									chunk.setAction(
//										PdfAction.javaScript(
//											"if (app.viewerVersion < 7)"
//												+ "{this.getURL(\"" + hyperlink + "\");}"
//												+ "else {app.launchURL(\"" + hyperlink + "\", true);};",
//											pdfWriter
//											)
//										);
//									break;
//								}
//								case SELF :
//								default :
//								{
//									chunk.setAnchor(hyperlink);
//									break;
//								}
//							}
//						}
//					}
//				}
//				case NONE :
//				default :
//				{
//					break;
//				}
//			}
//		}
//	}


	/**
	 *
	 */
	protected Paragraph getParagraph(AttributedString as, String text, JRPrintText textElement)
	{
		Paragraph paragraph = new Paragraph();
		int runLimit = 0;

		IAttributedCharacterIterator iterator = as.getIterator();
		
		// TODO (15.06.2013, Donat, Digireport): Add locale support later
//		Locale locale = getTextLocale(textElement);
		 
		boolean firstText = true;
		while(runLimit < text.length() && (runLimit = iterator.getRunLimit()) <= text.length())
		{
			Map<Attribute,Object> attributes = iterator.getAttributes();
			ParagraphText paragraphText = getParagrapghText(attributes, text.substring(iterator.getIndex(), runLimit));
			
			// TODO (15.06.2013, Donat, Digireport): Add Bookmark support later
			if (firstText)
			{
				// only set anchor + bookmark for the first chunk in the text
//				setAnchor(chunk, textElement, textElement);
			}
			
			// TODO (25.06.2013, Donat, Digireport): Add Hyperlink support later
//			JRPrintHyperlink hyperlink = textElement;
//			if (hyperlink.getHyperlinkTypeValue() == HyperlinkTypeEnum.NONE)
//			{
//				hyperlink = (JRPrintHyperlink)attributes.get(JRTextAttribute.HYPERLINK);
//			}
//			
//			setHyperlinkInfo(chunk, hyperlink);
			paragraph.add(paragraphText);

			iterator.setIndex(runLimit);
			firstText = false;
		}

		return paragraph;
	}


	/**
	 * Returns a decorated Text to render a text paragraph
	 */
	protected ParagraphText getParagrapghText(Map<Attribute,Object> attributes, String text)
	{
		// TODO (27.06.2013, Donat, Digireport): Use AttributedStringConverter
		// size, style, underline and strikethrough are set on the font below
		org.oss.pdfreporter.font.IFont font = getFont(attributes);
		IColor forecolor = (IColor)attributes.get(TextAttribute.FOREGROUND);
		IColor backcolor = (IColor)attributes.get(TextAttribute.BACKGROUND);
		boolean underline = hasUnderline(attributes);
		boolean strikethrough = hasStrikethrough(attributes);
		IPositionedLine line = underline ? PositionedLined.newUnderline() : strikethrough ? PositionedLined.newStrikethrough() : null;
		return new ParagraphText(text,font,forecolor,backcolor,line);
		
// TODO (15.04.2013, Donat, Digireport): Add text raise to the IPage API (use leadingfactor 1.25 * fontsize to calc offset)
//		Object script = attributes.get(TextAttribute.SUPERSCRIPT);
//		if (script != null)
//		{
//			if (TextAttribute.SUPERSCRIPT_SUPER.equals(script))
//			{
//				chunk.setTextRise(font.getCalculatedLeading(1f)/2);
//			}
//			else if (TextAttribute.SUPERSCRIPT_SUB.equals(script))
//			{
//				chunk.setTextRise(-font.getCalculatedLeading(1f)/2);
//			}
//		}
	}

	protected boolean hasUnderline(Map<Attribute,Object> textAttributes)
	{
		Integer underline = (Integer) textAttributes.get(TextAttribute.UNDERLINE);
		return TextAttribute.UNDERLINE_ON.equals(underline);
	}

	protected boolean hasStrikethrough(Map<Attribute,Object> textAttributes)
	{
		Boolean strike = (Boolean) textAttributes.get(TextAttribute.STRIKETHROUGH);
		return TextAttribute.STRIKETHROUGH_ON.equals(strike);
	}

	private boolean hasSuperscipt(Map<Attribute, Object> attributes) {
		Integer scriptStyle = (Integer) attributes.get(TextAttribute.SUPERSCRIPT);
		return (scriptStyle != null && TextAttribute.SUPERSCRIPT_SUPER.equals(scriptStyle));
	}
	
	private boolean hasSubscipt(Map<Attribute, Object> attributes) {
		Integer scriptStyle = (Integer) attributes.get(TextAttribute.SUPERSCRIPT);
		return (scriptStyle != null && TextAttribute.SUPERSCRIPT_SUB.equals(scriptStyle));
	}
	

	protected org.oss.pdfreporter.font.IFont getFont(Map<Attribute,Object> attributes)
	{
		// TODO (15.06.2013, Donat, Digireport): Font loading is done elsewhere so the following translation is not required here
		// FontInfo fontInfo = FontUtil.getInstance(jasperReportsContext).getFontInfo(jrFont.getFontName(), locale);
		
		JRFont jrFont = new JRBaseFont(attributes);
		return toPdfFont(jrFont,hasUnderline(attributes), hasStrikethrough(attributes), hasSuperscipt(attributes), hasSubscipt(attributes));
	}



	/**
	 *
	 */
	public void exportText(JRPrintText text) throws DocumentException
	{
		if (logger.isLoggable(Level.FINEST)) {
			logger.finest("X: " + text.getX() + ", Y: " + text.getY() + ", Width: " + text.getWidth() + ", Height: " + text.getTextHeight() + ", Text: ['" + text.getFullText() + "']");
		}
		// NOTICE (04.07.2013, Donat, Digireport): SimpleTextLineWrapper lead to SimplePdfTextRenderer and ComplexTExtLineWrapper lead to PdfTextRenderer
		AbstractPdfTextRenderer textRenderer = 
			text.getLeadingOffset() == 0 
			? new PdfTextRenderer(
				jasperReportsContext,
				getPropertiesUtil().getBooleanProperty(JRStyledText.PROPERTY_AWT_IGNORE_MISSING_FONT)
				) 
			: new SimplePdfTextRenderer(
				jasperReportsContext,
				getPropertiesUtil().getBooleanProperty(JRStyledText.PROPERTY_AWT_IGNORE_MISSING_FONT)//FIXMECONTEXT replace with getPropertiesUtil in all exporters
				);//FIXMETAB optimize this
		
		textRenderer.initialize(this, pdfPage, text, getOffsetX(), getOffsetY());
		
		JRStyledText styledText = textRenderer.getStyledText();

		if (styledText == null)
		{
			return;
		}

		Rotate90 angle = Rotate90.DEGREE_360;

		switch (text.getRotationValue())
		{
			case LEFT :
			{
				angle = Rotate90.DEGREE_90;
				break;
			}
			case RIGHT :
			{
				angle = Rotate90.DEGREE_270;
				break;
			}
			case UPSIDE_DOWN :
			{
				angle = Rotate90.DEGREE_180;
				break;
			}
			case NONE :
			default :
			{
			}
		}

		boolean doRestoreState = false;
		if (text.getRotationValue()!=RotationEnum.NONE) {	
			IAffineTransformMatrix matrix = ApiRegistry.getGeometryFactory().newAffineTransformMatrix(textRenderer.getX(), jasperPrint.getPageHeight() - textRenderer.getY(), angle);
			pdfPage.transform(matrix);
			doRestoreState = true;
		}

		if (text.getModeValue() == ModeEnum.OPAQUE)
		{
			IColor backcolor = text.getBackcolor();
			pdfPage.setRGBColorFill(backcolor);
			pdfPage.rectangle(
				textRenderer.getX(),
				jasperPrint.getPageHeight() - textRenderer.getY(),
				textRenderer.getWidth(),
				- textRenderer.getHeight()
				);
			pdfPage.fill();
		}

		if (styledText.length() > 0)
		{
//			tagHelper.startText();
			
			/*   */
			textRenderer.render();

//			tagHelper.endText();
		}

		// TODO (15.06.2013, Donat, Digireport): We have the restore function that can undo a transformation instead of reverse transforming
//		atrans = IRegistry.getAwtFactory().newAffineTransform();
//		atrans.rotate(-angle, textRenderer.getX(), jasperPrint.getPageHeight() - textRenderer.getY());
//		pdfPage.transform(AwtUnmarshaller.getAffineTransform(atrans));
		if (doRestoreState) {			
			pdfPage.restoreTransformation();
		}

		/*   */
		exportBox(
			text.getLineBox(),
			text
			);
	}


	/**
	 *
	 */
	protected void exportBox(JRLineBox box, JRPrintElement element)
	{
		exportTopPen(box.getTopPen(), box.getLeftPen(), box.getRightPen(), element);
		exportLeftPen(box.getTopPen(), box.getLeftPen(), box.getBottomPen(), element);
		exportBottomPen(box.getLeftPen(), box.getBottomPen(), box.getRightPen(), element);
		exportRightPen(box.getTopPen(), box.getBottomPen(), box.getRightPen(), element);

		pdfPage.setLineDash(null,0);
		pdfPage.setLineCap(LineCap.PROJECTING_SCUARE_END);
	}


	/**
	 *
	 */
	protected void exportPen(JRPen pen, JRPrintElement element)
	{
		exportTopPen(pen, pen, pen, element);
		exportLeftPen(pen, pen, pen, element);
		exportBottomPen(pen, pen, pen, element);
		exportRightPen(pen, pen, pen, element);

		pdfPage.setLineDash(null,0);
		pdfPage.setLineCap(LineCap.PROJECTING_SCUARE_END);
	}


	/**
	 *
	 */
	protected void exportTopPen(
		JRPen topPen, 
		JRPen leftPen, 
		JRPen rightPen, 
		JRPrintElement element)
	{
		if (topPen.getLineWidth().floatValue() > 0f)
		{
			float leftOffset = leftPen.getLineWidth().floatValue() / 2 - BorderOffset.getOffset(leftPen);
			float rightOffset = rightPen.getLineWidth().floatValue() / 2 - BorderOffset.getOffset(rightPen);
			
			preparePen(pdfPage, topPen, LineCap.BUTT_END);
			
			if (topPen.getLineStyleValue() == LineStyleEnum.DOUBLE)
			{
				float topOffset = topPen.getLineWidth().floatValue();

				pdfPage.moveTo(
					element.getX() + getOffsetX() - leftOffset,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() + topOffset / 3
					);
				pdfPage.lineTo(
					element.getX() + getOffsetX() + element.getWidth() + rightOffset,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() + topOffset / 3
					);
				pdfPage.stroke();

				pdfPage.moveTo(
					element.getX() + getOffsetX() + leftOffset / 3,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() - topOffset / 3
					);
				pdfPage.lineTo(
					element.getX() + getOffsetX() + element.getWidth() - rightOffset / 3,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() - topOffset / 3
					);
				pdfPage.stroke();
			}
			else
			{
				float topOffset =  BorderOffset.getOffset(topPen);
				pdfPage.moveTo(
					element.getX() + getOffsetX() - leftOffset,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() - topOffset
					);
				pdfPage.lineTo(
					element.getX() + getOffsetX() + element.getWidth() + rightOffset,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() - topOffset
					);
				pdfPage.stroke();
			}
		}
	}


	/**
	 *
	 */
	protected void exportLeftPen(JRPen topPen, JRPen leftPen, JRPen bottomPen, JRPrintElement element)
	{
		if (leftPen.getLineWidth().floatValue() > 0f)
		{
			float topOffset = topPen.getLineWidth().floatValue() / 2 - BorderOffset.getOffset(topPen);
			float bottomOffset = bottomPen.getLineWidth().floatValue() / 2 - BorderOffset.getOffset(bottomPen);

			preparePen(pdfPage, leftPen, LineCap.BUTT_END);

			if (leftPen.getLineStyleValue() == LineStyleEnum.DOUBLE)
			{
				float leftOffset = leftPen.getLineWidth().floatValue();

				pdfPage.moveTo(
					element.getX() + getOffsetX() - leftOffset / 3,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() + topOffset
					);
				pdfPage.lineTo(
					element.getX() + getOffsetX() - leftOffset / 3,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() - element.getHeight() - bottomOffset
					);
				pdfPage.stroke();

				pdfPage.moveTo(
					element.getX() + getOffsetX() + leftOffset / 3,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() - topOffset / 3
					);
				pdfPage.lineTo(
					element.getX() + getOffsetX() + leftOffset / 3,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() - element.getHeight() + bottomOffset / 3
					);
				pdfPage.stroke();
			}
			else
			{
				float leftOffset =  BorderOffset.getOffset(leftPen);
				pdfPage.moveTo(
					element.getX() + getOffsetX() + leftOffset,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() + topOffset
					);
				pdfPage.lineTo(
					element.getX() + getOffsetX() + leftOffset,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() - element.getHeight() - bottomOffset
					);
				pdfPage.stroke();
			}
		}
	}


	/**
	 *
	 */
	protected void exportBottomPen(JRPen leftPen, JRPen bottomPen, JRPen rightPen, JRPrintElement element)
	{
		if (bottomPen.getLineWidth().floatValue() > 0f)
		{
			float leftOffset = leftPen.getLineWidth().floatValue() / 2 - BorderOffset.getOffset(leftPen);
			float rightOffset = rightPen.getLineWidth().floatValue() / 2 - BorderOffset.getOffset(rightPen);
			
			preparePen(pdfPage, bottomPen, LineCap.BUTT_END);
			
			if (bottomPen.getLineStyleValue() == LineStyleEnum.DOUBLE)
			{
				float bottomOffset = bottomPen.getLineWidth().floatValue();

				pdfPage.moveTo(
					element.getX() + getOffsetX() - leftOffset,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() - element.getHeight() - bottomOffset / 3
					);
				pdfPage.lineTo(
					element.getX() + getOffsetX() + element.getWidth() + rightOffset,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() - element.getHeight() - bottomOffset / 3
					);
				pdfPage.stroke();

				pdfPage.moveTo(
					element.getX() + getOffsetX() + leftOffset / 3,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() - element.getHeight() + bottomOffset / 3
					);
				pdfPage.lineTo(
					element.getX() + getOffsetX() + element.getWidth() - rightOffset / 3,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() - element.getHeight() + bottomOffset / 3
					);
				pdfPage.stroke();
			}
			else
			{
				float bottomOffset =  BorderOffset.getOffset(bottomPen);
				pdfPage.moveTo(
					element.getX() + getOffsetX() - leftOffset,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() - element.getHeight() + bottomOffset
					);
				pdfPage.lineTo(
					element.getX() + getOffsetX() + element.getWidth() + rightOffset,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() - element.getHeight() + bottomOffset
					);
				pdfPage.stroke();
			}
		}
	}


	/**
	 *
	 */
	protected void exportRightPen(JRPen topPen, JRPen bottomPen, JRPen rightPen, JRPrintElement element)
	{
		if (rightPen.getLineWidth().floatValue() > 0f)
		{
			float topOffset = topPen.getLineWidth().floatValue() / 2 - BorderOffset.getOffset(topPen);
			float bottomOffset = bottomPen.getLineWidth().floatValue() / 2 - BorderOffset.getOffset(bottomPen);

			preparePen(pdfPage, rightPen, LineCap.BUTT_END);

			if (rightPen.getLineStyleValue() == LineStyleEnum.DOUBLE)
			{
				float rightOffset = rightPen.getLineWidth().floatValue();

				pdfPage.moveTo(
					element.getX() + getOffsetX() + element.getWidth() + rightOffset / 3,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() + topOffset
					);
				pdfPage.lineTo(
					element.getX() + getOffsetX() + element.getWidth() + rightOffset / 3,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() - element.getHeight() - bottomOffset
					);
				pdfPage.stroke();

				pdfPage.moveTo(
					element.getX() + getOffsetX() + element.getWidth() - rightOffset / 3,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() - topOffset / 3
					);
				pdfPage.lineTo(
					element.getX() + getOffsetX() + element.getWidth() - rightOffset / 3,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() - element.getHeight() + bottomOffset / 3
					);
				pdfPage.stroke();
			}
			else
			{
				float rightOffset =  BorderOffset.getOffset(rightPen);
				pdfPage.moveTo(
					element.getX() + getOffsetX() + element.getWidth() - rightOffset,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() + topOffset
					);
				pdfPage.lineTo(
					element.getX() + getOffsetX() + element.getWidth() - rightOffset,
					jasperPrint.getPageHeight() - element.getY() - getOffsetY() - element.getHeight() - bottomOffset
					);
				pdfPage.stroke();
			}
		}
	}

	
	/**
	 * Convert iText style dash pattern to IPage dash pattern
	 * @param on
	 * @param off
	 * @return
	 */
	private static int[] toDashPattern(float on, float off) {
		return new int[] {(int)on, (int)off};
	}

	private static void drawRoundRect(IPage aPdfPage, float x, float y, float width, float height, int radius ) {
		aPdfPage.roundRectangle((int)x, (int)y, (int)width, (int)height, radius);
	}
	
    private static void drawEllipse(IPage aPdfPage, float x1, float y1, float x2, float y2) {
    	aPdfPage.ellipse((int)x1, (int)y1, (int)x2, (int)y2);
    }

	
    private static org.oss.pdfreporter.font.IFont toPdfFont(JRFont font, boolean underline, boolean strikethrough, boolean superscript, boolean subscript) {
    	IFontFactory fontFactory = ApiRegistry.getFontFactory();
    	IFontManager fontManager = fontFactory.getFontManager();
    	FontStyle style = font.isBold() && font.isItalic() ? FontStyle.BOLD_OBLIQUE : font.isBold() ? FontStyle.BOLD : font.isItalic() ? FontStyle.OBLIQUE : FontStyle.PLAIN;
    	org.oss.pdfreporter.font.IFont pdfFont = fontManager.getFont(font.getFontName(), style);
    	// TODO (27.06.2013, Donat, Digireport): Remove find font after migration of code
    	if (pdfFont==null) {
    		pdfFont = fontManager.findFont(font.getFontName(), style);
    	}
    	FontDecoration decoration = underline ? FontDecoration.UNDERLINE : strikethrough ? FontDecoration.STRIKE_THROUGH : superscript ? FontDecoration.SUPERSCRIPT : subscript ? FontDecoration.SUBSCRIPT : FontDecoration.NONE;
		float fontSizeScale = superscript || subscript ? 2f / 3 : 1f;
		return fontManager.getModifiedFont(pdfFont, font.getFontSize() * fontSizeScale, decoration);
    }
    
	static void drawParagraph(IPage aPdfPage, Paragraph paragraph, float llx, float lly, float urx, float ury, float leading, HorizontalAlignment alignment) {
		logger.finest("drawParagraph('" + paragraph.getText() + "', " + llx + ", " + lly + ", " + urx + ", " + ury + ", " + leading + ", " + alignment); 
		logger.finest("Pos(x=" + (int)llx + ", y=" + (int)lly + ", width=" + (int)(urx - llx) + ", height=" +  (int)(lly - ury)); 
		float verticalAlignmentHack = paragraph.getFirstParagraphText().getFont().getSize(); 
		IRectangle rect = ApiRegistry.getGeometryFactory().newRectangle((int)llx, (int)(lly - verticalAlignmentHack), (int)(urx - llx), (int)(lly - ury));
		ParagraphRenderer renderer = new ParagraphRenderer(paragraph, alignment, rect);
		renderer.render(aPdfPage, true);
	}

    
	/**
	 *
	 */
	// TODO (15.06.2013, Donat, Digireport): Add support for all LineCap styles
	private static void preparePen(IPage aPdfPage, JRPen pen, LineCap lineCap)
	{
		float lineWidth = pen.getLineWidth().floatValue();

		if (lineWidth <= 0)
		{
			return;
		}
		
		aPdfPage.setLineWidth(lineWidth);
		aPdfPage.setLineCap(lineCap);

		IColor color = pen.getLineColor();
		aPdfPage.setRGBColorStroke(color);

		switch (pen.getLineStyleValue())
		{
			case DOUBLE :
			{
				aPdfPage.setLineWidth(lineWidth / 3);
				aPdfPage.setLineDash(null,0);
				break;
			}
			case DOTTED :
			{
				switch (lineCap)
				{
					case BUTT_END :
					{
						aPdfPage.setLineDash(toDashPattern(lineWidth,lineWidth), 0);
						break;
					}
					case PROJECTING_SCUARE_END :
					{
						aPdfPage.setLineDash(toDashPattern(0, 2 * lineWidth), 0);
						break;
					}
				}
				break;
			}
			case DASHED :
			{
				switch (lineCap)
				{
					case BUTT_END :
					{
						aPdfPage.setLineDash(toDashPattern(5 * lineWidth, 3 * lineWidth), 0);
						break;
					}
					case PROJECTING_SCUARE_END :
					{
						aPdfPage.setLineDash(toDashPattern(4 * lineWidth, 4 * lineWidth), 0);
						break;
					}
				}
				break;
			}
			case SOLID :
			default :
			{
				aPdfPage.setLineDash(null,0);
				break;
			}
		}
	}


	protected static synchronized void registerFonts ()
	{
//		IFontManager fontManager = ApiRegistry.getFontFactory().getFontManager();
		// TODO (15.06.2013, Donat, Digireport): @see FontReportSample for alias feature, perhaps add an API method to register alias name for a font
		// TODO (15.06.2013, Donat, Digireport): Change font loading so that XML when font specification is read the font manager is called to load the basic font styles
//		if (!fontsRegistered)
//		{
//			List<PropertySuffix> fontFiles = JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance()).getProperties(PDF_FONT_FILES_PREFIX);//FIXMECONTEXT no default here and below
//			if (!fontFiles.isEmpty())
//			{
//				for (Iterator<PropertySuffix> i = fontFiles.iterator(); i.hasNext();)
//				{
//					JRPropertiesUtil.PropertySuffix font = i.next();
//					String file = font.getValue();
//					if (file.toLowerCase().endsWith(".ttc"))
//					{
//						FontFactory.register(file);
//					}
//					else
//					{
//						String alias = font.getSuffix();
//						FontFactory.register(file, alias);
//					}
//				}
//			}
//
//			List<PropertySuffix> fontDirs = JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance()).getProperties(PDF_FONT_DIRS_PREFIX);
//			if (!fontDirs.isEmpty())
//			{
//				for (Iterator<PropertySuffix> i = fontDirs.iterator(); i.hasNext();)
//				{
//					JRPropertiesUtil.PropertySuffix dir = i.next();
//					FontFactory.registerDirectory(dir.getValue());
//				}
//			}
//
//			fontsRegistered = true;
//		}
	}

	// TODO (15.06.2013, Donat, Digireport): Add Bookmark support later
/*
	static protected class Bookmark
	{
		final PdfOutline pdfOutline;
		final int level;

		Bookmark(Bookmark parent, int x, int top, String title)
		{
			this(parent, new PdfDestination(PdfDestination.XYZ, x, top, 0), title);
		}

		Bookmark(Bookmark parent, PdfDestination destination, String title)
		{
			this.pdfOutline = new PdfOutline(parent.pdfOutline, destination, title, false);
			this.level = parent.level + 1;
		}

		Bookmark(PdfOutline pdfOutline, int level)
		{
			this.pdfOutline = pdfOutline;
			this.level = level;
		}
	}

	static protected class BookmarkStack
	{
		LinkedList<Bookmark> stack;

		BookmarkStack()
		{
			stack = new LinkedList<Bookmark>();
		}

		void push(Bookmark bookmark)
		{
			stack.add(bookmark);
		}

		Bookmark pop()
		{
			return stack.removeLast();
		}

		Bookmark peek()
		{
			return stack.getLast();
		}
	}


	protected void initBookmarks()
	{
		bookmarkStack = new BookmarkStack();

		int rootLevel = isModeBatch && isCreatingBatchModeBookmarks ? -1 : 0;
		Bookmark bookmark = new Bookmark(pdfPage.getRootOutline(), rootLevel);
		bookmarkStack.push(bookmark);
	}


	protected void addBookmark(int level, String title, int x, int y)
	{
		Bookmark parent = bookmarkStack.peek();
		// searching for parent
		while(parent.level >= level)
		{
			bookmarkStack.pop();
			parent = bookmarkStack.peek();
		}

		if (!collapseMissingBookmarkLevels)
		{
			// creating empty bookmarks in order to preserve the bookmark level
			for (int i = parent.level + 1; i < level; ++i)
			{
				Bookmark emptyBookmark = new Bookmark(parent, parent.pdfOutline.getPdfDestination(), EMPTY_BOOKMARK_TITLE);
				bookmarkStack.push(emptyBookmark);
				parent = emptyBookmark;
			}
		}

		Bookmark bookmark = new Bookmark(parent, x, jasperPrint.getPageHeight() - y, title);
		bookmarkStack.push(bookmark);
	}

	protected void setAnchor(Chunk chunk, JRPrintAnchor anchor, JRPrintElement element)
	{
		String anchorName = anchor.getAnchorName();
		if (anchorName != null)
		{
			chunk.setLocalDestination(anchorName);

			if (anchor.getBookmarkLevel() != JRAnchor.NO_BOOKMARK)
			{
				addBookmark(anchor.getBookmarkLevel(), anchor.getAnchorName(), element.getX(), element.getY());
			}
		}
	}

*/

	protected void exportFrame(JRPrintFrame frame) throws DocumentException, IOException, JRException
	{
		if (frame.getModeValue() == ModeEnum.OPAQUE)
		{
			int x = frame.getX() + getOffsetX();
			int y = frame.getY() + getOffsetY();

			IColor backcolor = frame.getBackcolor();
			pdfPage.setRGBColorFill(backcolor);
			pdfPage.rectangle(
				x,
				jasperPrint.getPageHeight() - y,
				frame.getWidth(),
				- frame.getHeight()
				);
			pdfPage.fill();
		}

		setFrameElementsOffset(frame, false);
		try
		{
			exportElements(frame.getElements());
		}
		finally
		{
			restoreElementOffsets();
		}

		exportBox(frame.getLineBox(), frame);
	}


	/**
	 * Output stream implementation that discards all the data.
	 */
	public static class NullOutputStream extends OutputStream
	{
		public NullOutputStream()
		{
		}

		public void write(int b)
		{
			// discard the data
		}

		public void write(byte[] b, int off, int len)
		{
			// discard the data
		}

		public void write(byte[] b)
		{
			// discard the data
		}
	}




	/**
	 *
	 */
	protected void exportGenericElement(JRGenericPrintElement element)
	{
		GenericElementPdfHandler handler = (GenericElementPdfHandler) 
				GenericElementHandlerEnviroment.getInstance(getJasperReportsContext()).getElementHandler(
						element.getGenericType(), PDF_EXPORTER_KEY);
		
		if (handler != null)
		{
			handler.exportElement(exporterContext, element);
		}
		else
		{
			logger.warning("No PDF generic element handler for " + element.getGenericType());
		}
	}

	
	/**
	 *
	 */
	protected String getExporterKey()
	{
		return PDF_EXPORTER_KEY;
	}
}
