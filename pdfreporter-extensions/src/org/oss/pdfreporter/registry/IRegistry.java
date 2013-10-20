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
package org.oss.pdfreporter.registry;

import org.oss.pdfreporter.commons.beans.factory.IBeansFactory;
import org.oss.pdfreporter.net.factory.DefaultNetFactory;
import org.oss.pdfreporter.net.factory.INetFactory;
import org.oss.pdfreporter.uses.org.apache.digester.factory.IDigesterFactory;
import org.oss.pdfreporter.uses.org.apache.digester.factory.impl.DigesterFactory;
import org.oss.pdfreporter.xml.parsers.factory.IXmlParserFactory;


public class IRegistry {
	private static IXmlParserFactory xmlParserFactory;
	private static IBeansFactory beansFactory;
	private static INetFactory netFactory = new DefaultNetFactory();
	
	
	public static void register(IXmlParserFactory factory) {
		IRegistry.xmlParserFactory = factory;
	}
	
	public static void register(IBeansFactory factory) {
		IRegistry.beansFactory = factory;
	}
	
	public static IDigesterFactory getIDigesterFactory() {
		return new DigesterFactory();
	}
	public static IXmlParserFactory getIXmlParserFactory() {
		return IRegistry.xmlParserFactory;
	}
	public static IBeansFactory getIBeansFactory() {
		return IRegistry.beansFactory;
	}
	public static INetFactory getINetFactory() {
		return IRegistry.netFactory;
	}
	
	public static void register(INetFactory factory) {
		IRegistry.netFactory = factory;
	}
	
}
