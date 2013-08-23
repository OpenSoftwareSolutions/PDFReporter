package org.oss.pdfreporter.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Session object to store shared resources.
 * When the session is disposed or an ISessionObject is removed
 * from the session dispose() is called to cleanup the resource.
 * @author donatmuller, 2013, last change 4:53:44 PM
 */
public class Session {
	private final static Logger logger = Logger.getLogger(Session.class.getName());
	private final Map<String,ISessionObject> session;
	private final List<ISessionListener> listeners;
	
	Session() {
		logger.finest("Create Session: " + this);
		this.session = new HashMap<String, ISessionObject>();
		this.listeners = new ArrayList<ISessionListener>();
	}
	
	public void addListener(ISessionListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(ISessionListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * Adds a session object.
	 * @param key
	 * @param obj
	 */
	public void put(String key, ISessionObject obj) {
		logger.finest("Set " + key + " = " + obj);
		session.put(key, obj);
		for (ISessionListener listener : listeners) {
			listener.put(key, obj);
		}
	}
	
	/**
	 * Removes an disposes a session object
	 * @param key
	 */
	public void remove(String key) {
		logger.finest("Remove " + key);
		ISessionObject removed = session.remove(key);
		if (removed != null) {
			removed.dispose();
		}
		for (ISessionListener listener : listeners) {
			listener.remove(key);
		}
	}
	
	/**
	 * Returns a session object
	 * @param key
	 * @return
	 */
	public ISessionObject get(String key) {
		ISessionObject obj =session.get(key);
		for (ISessionListener listener : listeners) {
			listener.get(key);
		}
		logger.finest("Get " + key + " = " + obj);
		return obj;
	}
	
	/**
	 * Dispose all session objects
	 */
	public void dispose() {
		logger.finest("Dispose " + this);
		for (ISessionObject so : session.values()) {
			so.dispose();
		}
		session.clear();
		for (ISessionListener listener : listeners) {
			listener.dispose();
		}
		listeners.clear();
	}
}
