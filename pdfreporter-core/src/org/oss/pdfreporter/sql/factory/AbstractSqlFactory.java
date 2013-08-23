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
