package org.oss.pdfreporter.sql.factory;

import java.util.Date;

import org.oss.pdfreporter.sql.ITimestamp;


public class TimestampImpl implements ITimestamp {
	private final long milliseconds;

	TimestampImpl(long milliseconds) {
		this.milliseconds = milliseconds;
	}

	TimestampImpl(Date timestamp) {
		this(timestamp.getTime());
	}

	@Override
	public long getMilliseconds() {
		return milliseconds;
	}
}
