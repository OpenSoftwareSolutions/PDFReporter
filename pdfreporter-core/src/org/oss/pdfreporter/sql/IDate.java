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
 * A date YYYY-MM-DD
 * @author donatmuller, 2013, last change 10:24:59 AM
 * 
 */
public interface IDate extends IDateType {
	/**
	 * 1 - 9999
	 * @return
	 */
	int getYear();
	/**
	 * 1 - 12
	 * @return
	 */
	int getMonth();
	/**
	 * 1 - 31
	 * @return
	 */
	int getDay();
}
