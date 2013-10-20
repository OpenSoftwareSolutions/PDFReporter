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
package org.oss.pdfreporter.commons.arrays;

public class Array2DImpl<T> implements Array2D<T> {
	
	private final int iMax, jMax;
	private final Object[] array;

	public Array2DImpl(int iMax, int jMax) {
		super();
		this.iMax = iMax;
		this.jMax = jMax;
		this.array = new Object[iMax*jMax];
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(int i, int j) {
		assertRange(i,j);
		return (T) array[i * iMax + j];
	}

	@Override
	public void set(int i, int j, Object element) {
		assertRange(i,j);
		array[i * iMax + j] = element;
	}


	@Override
	public int getLengthI() {
		return iMax;
	}

	@Override
	public int getLengthJ() {
		return jMax;
	}
	
	private void assertRange(int i, int j) {
		if (i>iMax || j>jMax || i<0 || j< 0) {
			throw new ArrayIndexOutOfBoundsException("assert failed: 0 <= i < " + iMax + " and 0 <= j < " + jMax);
		}
	}
	
}
