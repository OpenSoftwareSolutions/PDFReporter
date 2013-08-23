package org.oss.pdfreporter.uses.java.beans;

public class PropertyChangeSupport implements IPropertyChangeSupport {

	public PropertyChangeSupport(Object bean) {
		
	}
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {

	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {

	}

	@Override
	public PropertyChangeListener[] getPropertyChangeListeners() {
		return null;
	}

	@Override
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {

	}

	@Override
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {

	}

	@Override
	public PropertyChangeListener[] getPropertyChangeListeners(
			String propertyName) {
		return null;
	}

	@Override
	public void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void firePropertyChange(String propertyName, int oldValue,
			int newValue) {

	}

	@Override
	public void firePropertyChange(String propertyName, boolean oldValue,
			boolean newValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void firePropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fireIndexedPropertyChange(String propertyName, int index,
			Object oldValue, Object newValue) {

	}

	@Override
	public void fireIndexedPropertyChange(String propertyName, int index,
			int oldValue, int newValue) {

	}

	@Override
	public void fireIndexedPropertyChange(String propertyName, int index,
			boolean oldValue, boolean newValue) {

	}

	@Override
	public boolean hasListeners(String propertyName) {
		return false;
	}

}
