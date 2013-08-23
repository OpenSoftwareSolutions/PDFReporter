package org.oss.pdfreporter.text;

/**
 * A listener to be notified for the event that
 * a Text splits into a left and a right part.
 * @author donatmuller, 2013, last change 2:23:06 PM
 * 
 * @param <T>
 */
public interface ISplitListener<T> {
	/**
	 * Notifies the listener about object before
	 * was split into left and right.
	 * @param before
	 * @param left
	 * @param right
	 */
	void split(T before, T left, T right);
}
