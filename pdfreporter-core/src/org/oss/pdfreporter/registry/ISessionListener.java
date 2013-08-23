package org.oss.pdfreporter.registry;

/**
 * Listens to session events.
 * @author donatmuller, 2013, last change 6:44:52 PM
 */
public interface ISessionListener {
	/**
	 * Called on Session.dispose()
	 */
	void dispose();
	/**
	 * Called on Session.get()
	 * @param key
	 * @return
	 */
	void get(String key);
	/**
	 * Called on Session.put()
	 * @param key
	 * @param value
	 */
	void put(String key, ISessionObject value);
	/**
	 * Called on Session.remove()
	 * @param key
	 */
	void remove(String key);
}
