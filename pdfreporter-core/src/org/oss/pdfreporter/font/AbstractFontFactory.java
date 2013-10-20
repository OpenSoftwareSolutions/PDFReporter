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

import org.oss.pdfreporter.font.factory.IFontFactory;
import org.oss.pdfreporter.registry.Session;


public abstract class AbstractFontFactory implements IFontFactory {

	private Session session = null;

	@Override
	public void setSession(Session session) {
		if (this.session!=null) {
			session.removeListener(getFontManager());
		}
		this.session = session;
		session.addListener(getFontManager());
	}

	@Override
	public Session getSession() {
		return session;
	}

}
