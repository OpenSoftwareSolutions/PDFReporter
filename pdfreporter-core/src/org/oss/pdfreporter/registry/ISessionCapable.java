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
 * Factories can implement this interface to receive a Session object.
 * @See {@link Session}
 * @author donatmuller, 2013, last change 4:51:11 PM
 * 
 */
public interface ISessionCapable {
	/**
	 * Passes a session object to the receiver.
	 * @param session
	 */
	void setSession(Session session);	
	
	/**
	 * Returns the session
	 * @return
	 */
	Session getSession();
}
