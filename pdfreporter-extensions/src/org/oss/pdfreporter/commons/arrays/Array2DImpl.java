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
