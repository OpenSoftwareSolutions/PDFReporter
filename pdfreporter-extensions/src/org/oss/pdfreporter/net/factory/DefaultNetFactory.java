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
package org.oss.pdfreporter.net.factory;

import org.oss.pdfreporter.net.IURL;
import org.oss.pdfreporter.net.MalformedURLException;
import org.oss.pdfreporter.net.NullUrl;

/**
 * We have to provide a default INetFactory as jasper searches for jasperreports.properties
 * prior to jasperreports_extension.properties. So in the case that there is no jasperreports.properties
 * provided a fallback to URL loading for jasperreports.properties would fail with a NullpointerException.
 * The INetFactory is initialized with the ExtensionsRegistry iregistry.
 * @author donatmuller, 2013, last change 12:47:18 PM
 */
public class DefaultNetFactory implements INetFactory {

	@Override
	public IURL newURL(String url) throws MalformedURLException {
		return new NullUrl();
	}

}
