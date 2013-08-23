package org.oss.pdfreporter.pdf;

import org.oss.pdfreporter.registry.ISessionObject;

public abstract class AbstractDocument implements IDocument {
		
	@Override
	public void dispose() {
		close();
	}
	
	@Override
	public void get(String key) {
	}

	@Override
	public void put(String key, ISessionObject value) {
	}

	@Override
	public void remove(String key) {
	}

}
