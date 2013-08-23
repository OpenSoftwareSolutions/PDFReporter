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

import org.oss.pdfreporter.engine.fill.JRVirtualizationContext;

/**
 * @author John Bindel
 * @version $Id: JRVirtualizable.java 5180 2012-03-29 13:23:12Z teodord $
 * 
 * @param <T> the type of the virtual data object, see {@link #getVirtualData()}
 */
//FIXME use generics everywhere
public interface JRVirtualizable<T> extends IJRVirtualizable<T>{
	//FIXME use a more generic context type, JRVirtualizationContext has print page-specific methods
	//issue: changing JRVirtualizationContext hierarchy would impact serialization
	JRVirtualizationContext getContext(); 
}
