package org.oss.pdfreporter.uses.org.apache.digester.factory.impl;

import org.oss.pdfreporter.uses.org.apache.digester.IDigester;
import org.oss.pdfreporter.uses.org.apache.digester.factory.IDigesterFactory;
import org.oss.pdfreporter.uses.org.apache.digester.impl.Digester;
import org.oss.pdfreporter.xml.parsers.XMLParseException;


public class DigesterFactory implements IDigesterFactory {

	@Override
	public IDigester newDigester() throws XMLParseException {
		return new Digester();
	}

}
