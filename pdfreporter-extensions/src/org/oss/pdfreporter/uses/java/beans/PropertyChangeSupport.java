/*******************************************************************************
 * Copyright 2013 Open Software Solutions GmbH
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
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
