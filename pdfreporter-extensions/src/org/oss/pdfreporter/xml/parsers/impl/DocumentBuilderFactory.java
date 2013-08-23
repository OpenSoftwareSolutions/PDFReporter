package org.oss.pdfreporter.xml.parsers.impl;

import org.oss.pdfreporter.exception.NotImplementedException;
import org.oss.pdfreporter.xml.parsers.IDocumentBuilder;
import org.oss.pdfreporter.xml.parsers.IDocumentBuilderFactory;
import org.oss.pdfreporter.xml.parsers.ParserConfigurationException;

public class DocumentBuilderFactory implements IDocumentBuilderFactory {

	private boolean namespaceAware = false, validating = false, xIncludeAware = false;
	
	@Override
	public IDocumentBuilder newDocumentBuilder()
			throws ParserConfigurationException {
		return new DocumentBuilder(namespaceAware,validating,xIncludeAware);
	}

	@Override
	public void setNamespaceAware(boolean awareness) {
		this.namespaceAware = awareness;
	}

	@Override
	public void setValidating(boolean validating) {
		this.validating = validating;
	}

	@Override
	public void setIgnoringElementContentWhitespace(boolean whitespace) {
		throw new NotImplementedException();
	}

	@Override
	public void setExpandEntityReferences(boolean expandEntityRef) {
		throw new NotImplementedException();
	}

	@Override
	public void setIgnoringComments(boolean ignoreComments) {
//		throw new NotImplementedException();
	}

	@Override
	public void setCoalescing(boolean coalescing) {
		throw new NotImplementedException();
	}

	@Override
	public boolean isNamespaceAware() {
		return namespaceAware;
	}

	@Override
	public boolean isValidating() {
		return validating;
	}

	@Override
	public boolean isIgnoringElementContentWhitespace() {
		return false;
	}

	@Override
	public boolean isExpandEntityReferences() {
		return false;
	}

	@Override
	public boolean isIgnoringComments() {
		return true;
	}

	@Override
	public boolean isCoalescing() {
		return false;
	}

	@Override
	public void setAttribute(String name, Object value)
			throws IllegalArgumentException {
		throw new NotImplementedException();
	}

	@Override
	public Object getAttribute(String name) throws IllegalArgumentException {
		throw new NotImplementedException();
	}

	@Override
	public void setFeature(String name, boolean value)
			throws ParserConfigurationException {
		throw new NotImplementedException();
	}

	@Override
	public boolean getFeature(String name) throws ParserConfigurationException {
		throw new NotImplementedException();
	}

	@Override
	public void setXIncludeAware(boolean state) {
		xIncludeAware = state;
	}

	@Override
	public boolean isXIncludeAware() {
		return xIncludeAware;
	}

}
