package org.oss.pdfreporter.commons.arrays;

public interface Array2D<T> {
	T get(int i, int j) throws ArrayIndexOutOfBoundsException;
	void set(int i, int j, Object element) throws ArrayIndexOutOfBoundsException;
	int getLengthI();
	int getLengthJ();
}
