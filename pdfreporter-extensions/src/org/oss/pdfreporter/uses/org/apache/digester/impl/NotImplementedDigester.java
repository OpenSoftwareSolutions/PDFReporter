/*******************************************************************************
 * Copyright 2013 Open Software Solutions GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
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
