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

import java.util.Locale;

import org.junit.Test;


public class JEvalMultiLanguageExporterTest extends JEvalDesktopExporterTest{

	public JEvalMultiLanguageExporterTest() {
		super();
	}

	/*
	 * */
	@Test
	public void exportI18n() throws Exception {
		//Locale locale = chooseLocale();

		//if(locale != null){
			getExporter(DESIGN_REPORT_I18N, "i18n")
		    .addFillParameter("number", new Double(1234567 + Math.random()))
			.newResourceBundle("test.org.oss.pdfreporter.resourcebundle.i18n", Locale.US)
			.exportPdf();
		//}
	}
}
