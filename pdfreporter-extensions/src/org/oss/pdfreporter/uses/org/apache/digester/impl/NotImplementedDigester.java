package org.oss.pdfreporter.uses.org.apache.digester.impl;

import org.oss.pdfreporter.exception.NotImplementedException;
import org.oss.pdfreporter.uses.org.apache.digester.IDigester;
import org.oss.pdfreporter.uses.org.apache.digester.IRuleSet;
import org.oss.pdfreporter.xml.parsers.XMLParseException;


public abstract class NotImplementedDigester implements IDigester {


	@Override
	public void addRuleSet(IRuleSet ruleSet) {
		throw new NotImplementedException();
	}

	@Override
	public void setFeature(String feature, boolean value)
			throws XMLParseException {
		throw new NotImplementedException();
	}

}
