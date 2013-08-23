package org.oss.pdfreporter.xml.parsers;


public abstract class AbstractXmlParser implements IXmlParser {
	private final boolean validating;
	private final boolean namespaceAware;
	private final boolean xincludeAware;
	private final IInputSource input;
	private IContentHandler contentHandler;
	private XMLErrorHandler errorHandler;
	private XMLEntityResolver entityResolver;

	public AbstractXmlParser(IInputSource input, IContentHandler handler, boolean validating, boolean namespaceAware, boolean xincludeAware) throws ParserConfigurationException {
		this.input = input;
		this.contentHandler = handler;
		this.validating = validating;
		this.namespaceAware = namespaceAware;
		this.xincludeAware = xincludeAware;
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
	public boolean isXIncludeAware() {
		return xincludeAware;
	}


	@Override
	public void setEntityResolver(XMLEntityResolver entityResolver) {
		this.entityResolver = entityResolver;

	}

	@Override
	public void setErrorHandler(XMLErrorHandler errorHandler) {
		this.errorHandler = errorHandler;

	}

	@Override
	public void setContentHandler(IContentHandler contentHandler) {
		this.contentHandler = contentHandler;
	}

	protected IInputSource getInput() {
		return input;
	}

	protected IContentHandler getContentHandler() {
		return contentHandler;
	}

	protected XMLErrorHandler getErrorHandler() {
		return errorHandler;
	}

	protected XMLEntityResolver getEntityResolver() {
		return entityResolver;
	}
	
	

}
