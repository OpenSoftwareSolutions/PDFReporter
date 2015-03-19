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
package org.oss.pdfreporter.font;

import java.awt.Font;
import java.awt.FontMetrics;


public class FontPeer implements IFontPeer {

	private final Font delegate;
	private final FontMetrics fontMetric;
	
	FontPeer(Font delegate, FontMetrics metric) {
		super();
		this.delegate = delegate;
		this.fontMetric= metric;
	}

	@Override
	public IFontMetric getMetric() {
		return new FontMetric(fontMetric);
	}

	@Override
	public Object getPeer() {
		return delegate;
	}
}
