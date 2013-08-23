package org.oss.pdfreporter.image;

import org.oss.pdfreporter.image.factory.IImageFactory;
import org.oss.pdfreporter.registry.Session;

public abstract class AbstractImageFactory implements IImageFactory {

	private Session session = null;
	
	@Override
	public void setSession(Session session) {
		if (this.session!=null) {
			session.removeListener(getImageManager());
		}
		this.session = session;
		session.addListener(getImageManager());
	}

	@Override
	public Session getSession() {
		return session;
	}

}
