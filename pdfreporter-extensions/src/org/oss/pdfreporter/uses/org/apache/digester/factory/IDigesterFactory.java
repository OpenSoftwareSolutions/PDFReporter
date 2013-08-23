package org.oss.pdfreporter.uses.org.apache.digester.factory;

import org.oss.pdfreporter.uses.org.apache.digester.IDigester;
import org.oss.pdfreporter.xml.parsers.XMLParseException;


public interface IDigesterFactory {
	IDigester newDigester() throws XMLParseException;
}
