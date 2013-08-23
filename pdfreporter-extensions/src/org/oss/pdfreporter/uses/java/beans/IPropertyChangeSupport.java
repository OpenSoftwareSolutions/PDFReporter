package org.oss.pdfreporter.uses.java.beans;

public interface IPropertyChangeSupport {

	/**
	 * Add a PropertyChangeListener to the listener list.
	 * The listener is registered for all properties.
	 * The same listener object may be added more than once, and will be called
	 * as many times as it is added.
	 * If <code>listener</code> is null, no exception is thrown and no action
	 * is taken.
	 *
	 * @param listener  The PropertyChangeListener to be added
	 */
	void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Remove a PropertyChangeListener from the listener list.
	 * This removes a PropertyChangeListener that was registered
	 * for all properties.
	 * If <code>listener</code> was added more than once to the same event
	 * source, it will be notified one less time after being removed.
	 * If <code>listener</code> is null, or was never added, no exception is
	 * thrown and no action is taken.
	 *
	 * @param listener  The PropertyChangeListener to be removed
	 */
	void removePropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Returns an array of all the listeners that were added to the
	 * PropertyChangeSupport object with addPropertyChangeListener().
	 * <p>
	 * If some listeners have been added with a named property, then
	 * the returned array will be a mixture of PropertyChangeListeners
	 * and <code>PropertyChangeListenerProxy</code>s. If the calling
	 * method is interested in distinguishing the listeners then it must
	 * test each element to see if it's a
	 * <code>PropertyChangeListenerProxy</code>, perform the cast, and examine
	 * the parameter.
	 * 
	 * <pre>
	 * PropertyChangeListener[] listeners = bean.getPropertyChangeListeners();
	 * for (int i = 0; i < listeners.length; i++) {
	 *	 if (listeners[i] instanceof PropertyChangeListenerProxy) {
	 *     PropertyChangeListenerProxy proxy = 
	 *                    (PropertyChangeListenerProxy)listeners[i];
	 *     if (proxy.getPropertyName().equals("foo")) {
	 *       // proxy is a PropertyChangeListener which was associated
	 *       // with the property named "foo"
	 *     }
	 *   }
	 * }
	 *</pre>
	 *
	 * @see PropertyChangeListenerProxy
	 * @return all of the <code>PropertyChangeListeners</code> added or an 
	 *         empty array if no listeners have been added
	 * @since 1.4
	 */
	PropertyChangeListener[] getPropertyChangeListeners();

	/**
	 * Add a PropertyChangeListener for a specific property.  The listener
	 * will be invoked only when a call on firePropertyChange names that
	 * specific property.
	 * The same listener object may be added more than once.  For each
	 * property,  the listener will be invoked the number of times it was added
	 * for that property.
	 * If <code>propertyName</code> or <code>listener</code> is null, no
	 * exception is thrown and no action is taken.
	 *
	 * @param propertyName  The name of the property to listen on.
	 * @param listener  The PropertyChangeListener to be added
	 */

	void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener);

	/**
	 * Remove a PropertyChangeListener for a specific property.
	 * If <code>listener</code> was added more than once to the same event
	 * source for the specified property, it will be notified one less time
	 * after being removed.
	 * If <code>propertyName</code> is null,  no exception is thrown and no
	 * action is taken.
	 * If <code>listener</code> is null, or was never added for the specified
	 * property, no exception is thrown and no action is taken.
	 *
	 * @param propertyName  The name of the property that was listened on.
	 * @param listener  The PropertyChangeListener to be removed
	 */

	void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener);

	/**
	 * Returns an array of all the listeners which have been associated 
	 * with the named property.
	 *
	 * @param propertyName  The name of the property being listened to
	 * @return all of the <code>PropertyChangeListeners</code> associated with
	 *         the named property.  If no such listeners have been added,
	 *         or if <code>propertyName</code> is null, an empty array is
	 *         returned.
	 * @since 1.4
	 */
	PropertyChangeListener[] getPropertyChangeListeners(String propertyName);

	/**
	 * Report a bound property update to any registered listeners.
	 * No event is fired if old and new are equal and non-null.
	 *
	 * <p>
	 * This is merely a convenience wrapper around the more general
	 * firePropertyChange method that takes {@code
	 * PropertyChangeEvent} value.
	 *
	 * @param propertyName  The programmatic name of the property
	 *		that was changed.
	 * @param oldValue  The old value of the property.
	 * @param newValue  The new value of the property.
	 */
	void firePropertyChange(String propertyName, Object oldValue,
			Object newValue);

	/**
	 * Report an int bound property update to any registered listeners.
	 * No event is fired if old and new are equal.
	 * <p>
	 * This is merely a convenience wrapper around the more general
	 * firePropertyChange method that takes Object values.
	 *
	 * @param propertyName  The programmatic name of the property
	 *		that was changed.
	 * @param oldValue  The old value of the property.
	 * @param newValue  The new value of the property.
	 */
	void firePropertyChange(String propertyName, int oldValue, int newValue);

	/**
	 * Report a boolean bound property update to any registered listeners.
	 * No event is fired if old and new are equal.
	 * <p>
	 * This is merely a convenience wrapper around the more general
	 * firePropertyChange method that takes Object values.
	 *
	 * @param propertyName  The programmatic name of the property
	 *		that was changed.
	 * @param oldValue  The old value of the property.
	 * @param newValue  The new value of the property.
	 */
	void firePropertyChange(String propertyName, boolean oldValue,
			boolean newValue);

	/**
	 * Fire an existing PropertyChangeEvent to any registered listeners.
	 * No event is fired if the given event's old and new values are
	 * equal and non-null.
	 * @param evt  The PropertyChangeEvent object.
	 */
	void firePropertyChange(PropertyChangeEvent evt);

	/**
	 * Report a bound indexed property update to any registered
	 * listeners. 
	 * <p>
	 * No event is fired if old and new values are equal
	 * and non-null.
	 *
	 * <p>
	 * This is merely a convenience wrapper around the more general
	 * firePropertyChange method that takes {@code PropertyChangeEvent} value.
	 *
	 * @param propertyName The programmatic name of the property that
	 *                     was changed.
	 * @param index        index of the property element that was changed.
	 * @param oldValue     The old value of the property.
	 * @param newValue     The new value of the property.
	 * @since 1.5
	 */
	void fireIndexedPropertyChange(String propertyName, int index,
			Object oldValue, Object newValue);

	/**
	 * Report an <code>int</code> bound indexed property update to any registered 
	 * listeners. 
	 * <p>
	 * No event is fired if old and new values are equal.
	 * <p>
	 * This is merely a convenience wrapper around the more general
	 * fireIndexedPropertyChange method which takes Object values.
	 *
	 * @param propertyName The programmatic name of the property that
	 *                     was changed.
	 * @param index        index of the property element that was changed.
	 * @param oldValue     The old value of the property.
	 * @param newValue     The new value of the property.
	 * @since 1.5
	 */
	void fireIndexedPropertyChange(String propertyName, int index,
			int oldValue, int newValue);

	/**
	 * Report a <code>boolean</code> bound indexed property update to any 
	 * registered listeners. 
	 * <p>
	 * No event is fired if old and new values are equal.
	 * <p>
	 * This is merely a convenience wrapper around the more general
	 * fireIndexedPropertyChange method which takes Object values.
	 *
	 * @param propertyName The programmatic name of the property that
	 *                     was changed.
	 * @param index        index of the property element that was changed.
	 * @param oldValue     The old value of the property.
	 * @param newValue     The new value of the property.
	 * @since 1.5
	 */
	void fireIndexedPropertyChange(String propertyName, int index,
			boolean oldValue, boolean newValue);

	/**
	 * Check if there are any listeners for a specific property, including
	 * those registered on all properties.  If <code>propertyName</code>
	 * is null, only check for listeners registered on all properties.
	 *
	 * @param propertyName  the property name.
	 * @return true if there are one or more listeners for the given property
	 */
	boolean hasListeners(String propertyName);

}