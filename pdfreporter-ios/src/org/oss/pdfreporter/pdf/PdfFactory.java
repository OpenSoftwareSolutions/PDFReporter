/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.pdf;

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
