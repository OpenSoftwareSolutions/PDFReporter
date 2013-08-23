/*
 * %W% %E%
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.oss.pdfreporter.uses.java.util;

import java.util.Collection;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

/**
 * The <code>Stack</code> class represents a last-in-first-out
 * (LIFO) stack of objects. It extends class <tt>Vector</tt> with five
 * operations that allow a vector to be treated as a stack. The usual
 * <tt>push</tt> and <tt>pop</tt> operations are provided, as well as a
 * method to <tt>peek</tt> at the top item on the stack, a method to test
 * for whether the stack is <tt>empty</tt>, and a method to <tt>search</tt>
 * the stack for an item and discover how far it is from the top.
 * <p>
 * When a stack is first created, it contains no items.
 *
 * <p>A more complete and consistent set of LIFO stack operations is
 * provided by the {@link Deque} interface and its implementations, which
 * should be used in preference to this class.  For example:
 * <pre>   {@code
 *   Deque<Integer> stack = new ArrayDeque<Integer>();}</pre>
 *
 * @author  Jonathan Payne
 * @version %I%, %G%
 * @since   JDK1.0
 */
public class Stack<E>  {
	
	private final Vector<E> vector;
	
    /**
     * Creates an empty Stack.
     */
    public Stack() {
		vector = new Vector<E>();
    }

    /**
     * Copy constructor
     * @param clone
     */
    @SuppressWarnings("unchecked")
	public Stack(Stack<E> source) {
		vector = (Vector<E>) source.vector.clone();
    }

    /**
     * Pushes an item onto the top of this stack. This has exactly
     * the same effect as:
     * <blockquote><pre>
     * addElement(item)</pre></blockquote>
     *
     * @param   item   the item to be pushed onto this stack.
     * @return  the <code>item</code> argument.
     * @see     java.util.Vector#addElement
     */
    public E push(E item) {
	vector.addElement(item);

	return item;
    }

    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     *
     * @return     The object at the top of this stack (the last item
     *             of the <tt>Vector</tt> object).
     * @exception  EmptyStackException  if this stack is empty.
     */
    public synchronized E pop() {
	E	obj;
	int	len = vector.size();

	obj = peek();
	vector.removeElementAt(len - 1);

	return obj;
    }

    /**
     * Looks at the object at the top of this stack without removing it
     * from the stack.
     *
     * @return     the object at the top of this stack (the last item
     *             of the <tt>Vector</tt> object).
     * @exception  EmptyStackException  if this stack is empty.
     */
    public synchronized E peek() {
	int	len = vector.size();

	if (len == 0)
	    throw new EmptyStackException();
	return vector.elementAt(len - 1);
    }

    /**
     * Tests if this stack is empty.
     *
     * @return  <code>true</code> if and only if this stack contains
     *          no items; <code>false</code> otherwise.
     */
    public boolean empty() {
	return vector.size() == 0;
    }

    /**
     * Returns the 1-based position where an object is on this stack.
     * If the object <tt>o</tt> occurs as an item in this stack, this
     * method returns the distance from the top of the stack of the
     * occurrence nearest the top of the stack; the topmost item on the
     * stack is considered to be at distance <tt>1</tt>. The <tt>equals</tt>
     * method is used to compare <tt>o</tt> to the
     * items in this stack.
     *
     * @param   o   the desired object.
     * @return  the 1-based position from the top of the stack where
     *          the object is located; the return value <code>-1</code>
     *          indicates that the object is not on the stack.
     */
    public synchronized int search(Object o) {
	int i = vector.lastIndexOf(o);

	if (i >= 0) {
	    return vector.size() - i;
	}
	return -1;
    }
    
    

    @Override
	public Stack<E> clone()  {
		return new Stack<E>(this);
	}



	public void copyInto(Object[] anArray) {
		vector.copyInto(anArray);
	}

	public void trimToSize() {
		vector.trimToSize();
	}

	public void ensureCapacity(int minCapacity) {
		vector.ensureCapacity(minCapacity);
	}

	public Iterator<E> iterator() {
		return vector.iterator();
	}

	public void setSize(int newSize) {
		vector.setSize(newSize);
	}

	public ListIterator<E> listIterator() {
		return vector.listIterator();
	}

	public ListIterator<E> listIterator(int index) {
		return vector.listIterator(index);
	}

	public int capacity() {
		return vector.capacity();
	}

	public int size() {
		return vector.size();
	}

	public boolean isEmpty() {
		return vector.isEmpty();
	}

	public Enumeration<E> elements() {
		return vector.elements();
	}

	public boolean contains(Object o) {
		return vector.contains(o);
	}

	public int indexOf(Object o) {
		return vector.indexOf(o);
	}

	public int indexOf(Object o, int index) {
		return vector.indexOf(o, index);
	}

	public int lastIndexOf(Object o) {
		return vector.lastIndexOf(o);
	}

	public int lastIndexOf(Object o, int index) {
		return vector.lastIndexOf(o, index);
	}

	public E elementAt(int index) {
		return vector.elementAt(index);
	}

	public E firstElement() {
		return vector.firstElement();
	}

	public E lastElement() {
		return vector.lastElement();
	}

	public void setElementAt(E obj, int index) {
		vector.setElementAt(obj, index);
	}

	public void removeElementAt(int index) {
		vector.removeElementAt(index);
	}

	public void insertElementAt(E obj, int index) {
		vector.insertElementAt(obj, index);
	}

	public void addElement(E obj) {
		vector.addElement(obj);
	}

	public boolean removeElement(Object obj) {
		return vector.removeElement(obj);
	}

	public void removeAllElements() {
		vector.removeAllElements();
	}

	public Object[] toArray() {
		return vector.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return vector.toArray(a);
	}

	public E get(int index) {
		return vector.get(index);
	}

	public E set(int index, E element) {
		return vector.set(index, element);
	}

	public boolean add(E e) {
		return vector.add(e);
	}

	public boolean remove(Object o) {
		return vector.remove(o);
	}

	public void add(int index, E element) {
		vector.add(index, element);
	}

	public E remove(int index) {
		return vector.remove(index);
	}

	public void clear() {
		vector.clear();
	}

	public boolean containsAll(Collection<?> c) {
		return vector.containsAll(c);
	}

	public boolean addAll(Collection<? extends E> c) {
		return vector.addAll(c);
	}

	public boolean removeAll(Collection<?> c) {
		return vector.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return vector.retainAll(c);
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		return vector.addAll(index, c);
	}

	public boolean equals(Object o) {
		return vector.equals(o);
	}

	public int hashCode() {
		return vector.hashCode();
	}

	public String toString() {
		return vector.toString();
	}

	public List<E> subList(int fromIndex, int toIndex) {
		return vector.subList(fromIndex, toIndex);
	}

	/** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = 1224463164541339165L;
}
