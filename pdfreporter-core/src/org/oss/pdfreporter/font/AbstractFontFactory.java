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
