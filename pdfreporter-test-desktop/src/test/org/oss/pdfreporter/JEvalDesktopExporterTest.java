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
import java.util.ResourceBundle;

import test.org.oss.pdfreporter.providers.DesktopTestProvider;



public abstract class JEvalDesktopExporterTest extends ExporterTest{

	public JEvalDesktopExporterTest() {
		super(true, new DesktopTestProvider());
	}

	protected ResourceBundle getExpressionLanguage() {
		return ResourceBundle.getBundle("test.org.oss.pdfreporter.testbundle.test", new Locale("jeval", "JEVAL"));
    }
}
