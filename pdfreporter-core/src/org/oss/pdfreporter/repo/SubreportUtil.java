/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.repo;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JasperCompileManager;
import org.oss.pdfreporter.engine.JasperReport;
import org.oss.pdfreporter.engine.design.JasperDesign;
import org.oss.pdfreporter.engine.xml.JRXmlLoader;


public class SubreportUtil {
	
	public static JasperReport loadSubreport(String location) throws JRException {
		InputStream isReport = null;
		try {
			isReport = FileResourceLoader.getInputStream(location.replace(".jasper", ".jrxml"));
			if (isReport==null) {
				throw new JRException("Subreport file not found: " + location);
			}
			JasperDesign design = JRXmlLoader.load(isReport);
			return JasperCompileManager.compileReport(design);
		} finally {
			close(isReport);
		}
	}
	

	private static void close(Closeable stream) throws JRException {
		if (stream!=null) {
			try {
				stream.close();
			} catch (IOException e) {
				throw new JRException(e);
			}
		}
	}
	
}
