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
package org.oss.pdfreporter.pdf.factory;

import org.oss.pdfreporter.pdf.DocumentException;
import org.oss.pdfreporter.pdf.IDocument;
import org.oss.pdfreporter.registry.ISessionCapable;

public interface IPdfFactory extends ISessionCapable {
	IDocument newDocument(String filePath) throws DocumentException;
}
