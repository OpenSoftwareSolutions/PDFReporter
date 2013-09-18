package org.oss.pdfreporter.xml.parsers.factory;

import java.io.InputStream;
import java.io.Reader;

import org.oss.pdfreporter.xml.parsers.IContentHandler;
import org.oss.pdfreporter.xml.parsers.IDocumentBuilderFactory;
import org.oss.pdfreporter.xml.parsers.IInputSource;
import org.oss.pdfreporter.xml.parsers.IXmlParser;
import org.oss.pdfreporter.xml.parsers.ParserConfigurationException;


public class XmlParserFactory implements IXmlParserFactory {

	@Override
	public void setNamespaceAware(boolean aware) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setXIncludeAware(boolean aware) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValidating(boolean validating) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configure() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IDocumentBuilderFactory newDocumentBuilderFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IInputSource newInputSource(InputStream is) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IInputSource newInputSource(Reader reader) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IXmlParser newXmlParser(IInputSource source, IContentHandler handler) throws ParserConfigurationException {
		// TODO Auto-generated method stub
		return null;
	}

	public static void registerFactory() {
		
	}


}
