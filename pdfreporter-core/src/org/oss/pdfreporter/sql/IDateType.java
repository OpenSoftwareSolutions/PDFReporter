package org.oss.pdfreporter.sql;

import java.util.Date;

/**
 * Common interface to all Date types.
 * Simple read only type without time zone support.
 * An implementor has to ensure that the internal representation
 * can be converted to a valid Date that represents the same
 * information as retrieved by the public members of subclasses
 * of IDateType.
 * @author donatmuller, 2013, last change 10:12:26 AM
 * 
 */
public interface IDateType {
	/**
	 * Returns the type as java.util.Date 
	 * @return
	 */
	Date getDate();
}
