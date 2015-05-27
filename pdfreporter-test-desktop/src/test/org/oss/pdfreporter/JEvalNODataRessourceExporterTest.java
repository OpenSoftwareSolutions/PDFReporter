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
package test.org.oss.pdfreporter;

import org.junit.Test;
import org.oss.pdfreporter.pdf.IDocument;


public class JEvalNODataRessourceExporterTest extends JEvalDesktopExporterTest{

	public JEvalNODataRessourceExporterTest() {
		super();
	}

	@Test
	public void exportFonts() throws Exception {
		getExporter(DESIGN_REPORT_FONTS, "fonts","extra-fonts")
			.exportPdf();
	}

	@Test
	public void exportStretch() throws Exception {
		getExporter(DESIGN_REPORT_STRETCH, "stretch")
			.exportPdf();
	}

	@Test
	public void exportTabular() throws Exception {
		getExporter(DESIGN_REPORT_TABULAR, "tabular","extra-fonts")
			.exportPdf();
	}

	@Test
	public void exportImages() throws Exception {
		getExporter(DESIGN_REPORT_IMAGE, "images")
			.exportPdf();
	}

	@Test
	public void exportLandscape() throws Exception {
		getExporter(DESIGN_REPORT_LANDSCAPE, "landscape")
			.exportPdf();
	}

	@Test
	public void exportShapes() throws Exception {
		getExporter(DESIGN_REPORT_SHAPES, "shapes")
			.exportPdf();
	}

	@Test
	public void exportRotation() throws Exception {
		getExporter(DESIGN_REPORT_ROTATION, "misc")
			.exportPdf();
	}

	@Test
	public void exportEncrypt() throws Exception {
		getExporter(DESIGN_REPORT_PDFCRYPT, "misc", null)
			.addEncryption(true, "jasper", "reports", IDocument.PERMISSION_COPY | IDocument.PERMISSION_PRINT)
			.exportPdf();
	}

	@Test
	public void exportParagraph() throws Exception {
		getExporter(DESIGN_REPORT_PARAGRAPH, "text","extra-fonts")
			.exportPdf();
	}

	@Test
	public void exportStyledText() throws Exception {
		getExporter(DESIGN_REPORT_STYLEDTEXT, "text","extra-fonts")
			.exportPdf();
	}

}
