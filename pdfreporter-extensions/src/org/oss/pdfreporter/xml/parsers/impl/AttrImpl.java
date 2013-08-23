package org.oss.pdfreporter.xml.parsers.impl;

import org.oss.pdfreporter.exception.NotImplementedException;
import org.oss.pdfreporter.uses.org.w3c.dom.DOMException;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;
import org.oss.pdfreporter.uses.org.w3c.dom.Element;
import org.oss.pdfreporter.uses.org.w3c.dom.Node;
import org.oss.pdfreporter.uses.org.w3c.dom.TypeInfo;

public class AttrImpl extends NodeImpl implements org.oss.pdfreporter.uses.org.w3c.dom.Attr{

	private Element ownerElement;
	private boolean isId = false;
	
	AttrImpl(Document owner, Element ownerElement, String name, String value) {
		this(owner, ownerElement, name, value, false);
	}
	
	AttrImpl(Document owner, Element ownerElement, String name, String value, boolean isId) {
		super(owner, Node.ATTRIBUTE_NODE, name, value);
		this.ownerElement = ownerElement;
		this.isId = isId;
	}

	@Override
	public String getName() {
		return getNodeName();
	}

	@Override
	public boolean getSpecified() {
		throw new NotImplementedException();
		//return true;
	}

	@Override
	public String getValue() {
		return getNodeValue();
	}

	@Override
	public void setValue(String value) throws DOMException {
		setNodeValue(value);
	}

	@Override
	public Element getOwnerElement() {
		return ownerElement;
	}

	@Override
	public TypeInfo getSchemaTypeInfo() {
		throw new NotImplementedException();
	}

	@Override
	public boolean isId() {
		return isId;
	}

	

}
