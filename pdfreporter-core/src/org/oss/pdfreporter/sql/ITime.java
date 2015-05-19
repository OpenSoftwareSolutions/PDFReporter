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
package org.oss.pdfreporter.sql;

/**
 * A date HH:MM:SS
 * @author donatmuller, 2013, last change 10:27:04 AM
 * 
 */
public interface ITime extends IDateType {
	/**
	 * 0 - 23
	 * @return
	 */
	int getHours();
	/**
	 * 0 - 59
	 * @return
	 */
	int getMinutes();
	/**
	 * 0 - 59
	 * @return
	 */
	int getSeconds();
}
