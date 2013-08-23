package org.oss.pdfreporter.registry;

/**
 * Factories can implement this interface to receive a Session object.
 * @See {@link Session}
 * @author donatmuller, 2013, last change 4:51:11 PM
 * 
 */
public interface ISessionCapable {
	/**
	 * Passes a session object to the receiver.
	 * @param session
	 */
	void setSession(Session session);	
	
	/**
	 * Returns the session
	 * @return
	 */
	Session getSession();
}
