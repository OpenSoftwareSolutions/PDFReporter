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
package org.oss.pdfreporter.sql.factory;

import java.io.InputStream;
import java.util.Date;

import org.oss.pdfreporter.sql.IBlob;
import org.oss.pdfreporter.sql.IDate;
import org.oss.pdfreporter.sql.IDateTime;
import org.oss.pdfreporter.sql.ITime;
import org.oss.pdfreporter.sql.ITimestamp;


public abstract class AbstractSqlFactory implements ISqlFactory {


	@Override
	public IDate newDate(Date date) {
		return new DateImpl(date);
	}

	@Override
	public ITime newTime(Date time) {
		return new TimeImpl(time);
	}

	@Override
	public ITimestamp newTimestamp(long milliseconds) {
		return new TimestampImpl(milliseconds);
	}

	@Override
	public IDateTime newDateTime(Date datetime) {
		return new DateTimeImpl(datetime);
	}

	@Override
	public IBlob newBlob(InputStream is) {
		return new BlobImpl(is);
	}

	@Override
	public IBlob newBlob(byte[] bytes) {
		return new BlobImpl(bytes);
	}

}
