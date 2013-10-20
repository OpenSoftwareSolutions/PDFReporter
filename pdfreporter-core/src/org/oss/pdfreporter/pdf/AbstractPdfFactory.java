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
package org.oss.pdfreporter.pdf;

import java.util.ArrayList;
import java.util.List;

import org.oss.pdfreporter.pdf.factory.IPdfFactory;
import org.oss.pdfreporter.registry.ISessionListener;
import org.oss.pdfreporter.registry.ISessionObject;
import org.oss.pdfreporter.registry.Session;


public abstract class AbstractPdfFactory implements IPdfFactory, ISessionListener {

	private List<IDocument> documents;
	private Session session;
	private final int maxDocuments;
	
	
	public AbstractPdfFactory(int maxDocuments) {
		this.maxDocuments = maxDocuments;
		documents = new ArrayList<IDocument>();
		session = null;
	}
	
	@Override
	public void setSession(Session session) {
		if (this.session!=null) {
			this.session.removeListener(this);
			if (!documents.isEmpty()) {
				throw new RuntimeException("Session: " + this.session + " was not disposed.");
			}
		}
		this.session = session;
		for (IDocument document : documents) {
			session.addListener(document);
		}
		session.addListener(this);
	}

	@Override
	public Session getSession() {
		return session;
	}

	@Override
	public IDocument newDocument(String filePath) throws DocumentException {
		if (maxDocuments > 0 && documents.size() >= maxDocuments) {
			throw new RuntimeException("This factory allows only " + maxDocuments + " document(s) per session.");
		}
		IDocument document = newDocumentInternal(filePath);
		documents.add(document);
		if (session!=null) {
			session.addListener(document);
		}
		return document;
	}

	@Override
	public void dispose() {
		// we do not have to remove listener on dispose the session will take care of this
		documents.clear();
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
	
	protected abstract IDocument newDocumentInternal(String filePath) throws DocumentException; 
}
