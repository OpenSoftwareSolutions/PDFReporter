package org.oss.pdfreporter.xml.parsers.wrapper;

import org.oss.pdfreporter.xml.parsers.util.XmlParserUnmarshaller;
import org.w3c.dom.Node;
import org.w3c.dom.UserDataHandler;

public class UnmarshallingUserDataHandler implements UserDataHandler {
	private final org.oss.pdfreporter.uses.org.w3c.dom.UserDataHandler delegate;

	public UnmarshallingUserDataHandler(org.oss.pdfreporter.uses.org.w3c.dom.UserDataHandler delegate) {
		super();
		this.delegate = delegate;
	}

	public org.oss.pdfreporter.uses.org.w3c.dom.UserDataHandler getDelegate() {
		return delegate;
	}

	public void handle(short operation, String key, Object data, Node src,
			Node dst) {
		delegate.handle(operation, key, data, XmlParserUnmarshaller.getNode(src), XmlParserUnmarshaller.getNode(dst));
	}
	
}
