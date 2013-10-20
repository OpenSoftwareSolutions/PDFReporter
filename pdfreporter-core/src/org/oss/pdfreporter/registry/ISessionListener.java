/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.registry;

/**
 * Listens to session events.
 * @author donatmuller, 2013, last change 6:44:52 PM
 */
public interface ISessionListener {
	/**
	 * Called on Session.dispose()
	 */
	void dispose();
	/**
	 * Called on Session.get()
	 * @param key
	 * @return
	 */
	void get(String key);
	/**
	 * Called on Session.put()
	 * @param key
	 * @param value
	 */
	void put(String key, ISessionObject value);
	/**
	 * Called on Session.remove()
	 * @param key
	 */
	void remove(String key);
}
