/**
* @author Nguyen Cuong: Circular Array Deque Abstract Data Type
* Do not use Java’s built in LinkedList data structure
*/

public class ArrayDeque<T> {
	private T[] items;
	private int size;
	private int nextFirst, nextLast; // For Circular Array Deque Purpose.

	public ArrayDeque() {
		items = (T[]) new Object[8]; // Generics ArrayDeque
		size = 0;

		nextFirst = 4; 
		nextLast = 5;
	}

	public int size() {
		/* Returns the number of items in the deque. Take Constant Time */
		return size;
	}

	private void resize(int capacity) {
		T[] newItems = (T[]) new Object[capacity];

		int newNextFirst = capacity - 2;
		int newNextLast = capacity - 1;
		
		for (int i = size - 1; i >= 0; --i) {
			newItems[newNextFirst] = get(i);
			--newNextFirst;
		} 
		items = newItems;
		nextFirst = newNextFirst;
		nextLast = newNextLast;
	}

	public void addFirst(T item) {
		/* Adds an item of type T to the front of the deque. Take Constant Time */
		if (size == (items.length - 2)) {
			resize(2 * size);
		}
		items[nextFirst] = item;
		
		if (nextFirst == 0) {
			nextFirst = items.length - 1;
		} else {
			--nextFirst;
		}
		++size;
	}

	public void addLast(T item) {
		/* Adds an item of type T to the back of the deque. Take Constant Time */
		if (size == (items.length - 2)) {
			resize(2 * size);
		}
		items[nextLast] = item;

		if (nextLast == items.length - 1) {
			nextLast = 0;
		} else {
			++nextLast;
		}
		++size;
	}

	public boolean isEmpty() {
		/* Returns true if deque is empty, false otherwise. Take Constant Time */
		return size() == 0;
	}

	private T getFirstNode() {
		/* private helper methods */
		if (nextFirst == (items.length - 1)) {
			return items[0];
		} else {
			return items[nextFirst + 1];
		}
	}

	private T getLastNode() {
		/* private helper methods */
		if (nextLast == 0) {
			return items[items.length - 1];
		} else {
			return items[nextLast - 1];
		}
	}

	private void updateCapacity() {
		/* This methods is used for Space Efficency. That Half array size when UsageRatio < 0.25 and taking large space for Capacity*/
		int usageRatio = size / items.length;
		if (items.length > 16 && usageRatio < 0.25) {
			resize(2 * size);
		}
	}

	public T removeFirst() {
		/* Removes and returns the item at the front of the deque. If no such item exists, returns null. Take Constant Time */
		if (isEmpty()) {
			return null;
		}
		T p = getFirstNode();

		if (nextFirst == (items.length - 1)) {
			nextFirst = 0;
		} else {
			++nextFirst;
		}

		items[nextFirst] = null;
		--size;
		updateCapacity();

		return p;
	}

	public T removeLast() {
		/* Removes and returns the item at the back of the deque. If no such item exists, returns null. Take Constant Time */
		if (isEmpty()) {
			return null;
		}
		T p = getLastNode();

		if (nextLast == 0) {
			nextLast = items.length - 1;
		} else {
			--nextLast;
		}

		items[nextLast] = null;
		--size;
		updateCapacity();

		return p;
	}

	public T get(int index) {
		/** 
		* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. 
		* If no such item exists, returns null. Do not alter the deque! Take Constant Time on Size.
		**/
		if (isEmpty() || index < 0 || index > size - 1) {
			return null;
		}

		int maxIndex = items.length - 1;
		if ((nextFirst + index) >= maxIndex) {
			return items[nextFirst + index - maxIndex];
		} else {
			return items[nextFirst + index + 1];
		}
	}

	public void printDeque() {
		/*  Prints the items in the deque from first to last, separated by a space. Take linear Time on Size. */
		for (int i = 0; i < size; ++i) {
			T p = get(i);
			System.out.print(p + " ");
		}
		System.out.println();
	}
}