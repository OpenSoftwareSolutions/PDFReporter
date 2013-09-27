package org.oss.pdfreporter.xml.parsers.wrapper;

import org.w3c.dom.TypeInfo;

public class UnmarshallingTypeInfo implements TypeInfo {
	private final org.oss.pdfreporter.uses.org.w3c.dom.TypeInfo delegate;

	public UnmarshallingTypeInfo(org.oss.pdfreporter.uses.org.w3c.dom.TypeInfo delegate) {
		super();
		this.delegate = delegate;
	}

	public org.oss.pdfreporter.uses.org.w3c.dom.TypeInfo getDelegate() {
		return delegate;
	}

	@Override
	public String getTypeName() {
		return delegate.getTypeName();
	}

	@Override
	public String getTypeNamespace() {
		return delegate.getTypeNamespace();
	}

	@Override
	public boolean isDerivedFrom(String typeNamespaceArg, String typeNameArg,
			int derivationMethod) {
		return delegate.isDerivedFrom(typeNamespaceArg, typeNameArg, derivationMethod);
	}
	
}
