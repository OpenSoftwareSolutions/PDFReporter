/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2005 - 2011 Works, Inc. All rights reserved.
 * http://www.works.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Licensed to Jaspersoft Corporation under a Contributer Agreement
 */
package org.oss.pdfreporter.engine;


/**
 * @author John Bindel
 * @version $Id: JRVirtualizer.java 5180 2012-03-29 13:23:12Z teodord $
 * @see org.oss.pdfreporter.engine.JRVirtualizationHelper
 */
public interface JRVirtualizer
{
	/**
	 * Lets this virtualizer know that it must track the object.<p>
	 *
	 * All virtualizable object must register with their virtualizer
	 * upon construction.
	 */
 	@SuppressWarnings("rawtypes")
	void registerObject(IJRVirtualizable o);

	/**
	 * Lets this virtualizer know that it no longer must track the
	 * object.
	 */
 	@SuppressWarnings("rawtypes")
	void deregisterObject(IJRVirtualizable o);

	/**
	 * Lets the virtualizer know that this object is still being used.
	 * This should be called to help the virtualizer determine which
	 * objects to keep in its cache, and which objects to page-out
	 * when it must do some paging-out.<p>
	 *
	 * The virtualizer gets to decide what type of caching strategy
	 * it will use.
	 */
	@SuppressWarnings("rawtypes")
	void touch(IJRVirtualizable o);

	/**
	 * Called when the virtual object must be paged-in.
	 * <p>
	 * If the object's virtual data is not paged-out, the object will only be
	 * {@link #touch(IJRVirtualizable) touched}.
	 */
	@SuppressWarnings("rawtypes")
	void requestData(IJRVirtualizable o);

	/**
	 * Called when the virtual object paged-out data should be freed.
	 * <p>
	 * If the object's virtual data is not paged-out, the object will only be
	 * {@link #touch(IJRVirtualizable) touched}.
	 */
	@SuppressWarnings("rawtypes")
	void clearData(IJRVirtualizable o);

	/**
	 * Called when the virtual object should be paged-out.
	 */
	@SuppressWarnings("rawtypes")
	void virtualizeData(IJRVirtualizable o);
	
	/**
	 * Called when we are done with the virtualizer and wish to
	 * cleanup any resources it has.
	 */
	void cleanup();
	
}
