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
