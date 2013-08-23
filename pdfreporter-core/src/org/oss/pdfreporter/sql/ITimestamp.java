package org.oss.pdfreporter.sql;

/**
 * A Date YYYY-MM-DD HH:MM:SS s+
 * @author donatmuller, 2013, last change 10:27:18 AM
 * 
 */
public interface ITimestamp {
	/**
	 * Milliseconds sins 1.1.1970
	 * @return
	 */
	long getMilliseconds();
}
