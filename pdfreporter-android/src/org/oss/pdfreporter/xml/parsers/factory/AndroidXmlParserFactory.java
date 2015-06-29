/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 *
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package org.oss.pdfreporter.xml.parsers.factory;

import org.oss.pdfreporter.registry.IRegistry;


/**
 * IXmlParserFactory implementation based on SAXParser and DocumentBuilderFactory from JDK.<br>
 * Implements all features with wrappers.
 * @author donatmuller, 2013, last change 11:28:43 PM
 *
 */
public class AndroidXmlParserFactory extends XmlParserFactory {

	public static void registerFactory() {
		IRegistry.register(new AndroidXmlParserFactory());
	}


	@Override
	protected boolean isXIncludeSupported() {
		return false;
	}

}
