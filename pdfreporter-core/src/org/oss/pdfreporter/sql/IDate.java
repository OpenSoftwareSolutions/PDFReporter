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
