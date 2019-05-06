/**
 * @author Nguyen Cuong: Deque Interface
 * Task 1: Deque Interface
 */
public interface Deque<T> {
	public void addFirst(T item);
		/* Adds an item of type T to the front of the deque. Take Constant Time */

	public void addLast(T item);
		/* Adds an item of type T to the back of the deque. Take Constant Time */

	public boolean isEmpty();
		/* Returns true if deque is empty, false otherwise. Take Constant Time */

	public int size();
		/* Returns the number of items in the deque. Take Constant Time */

	public void printDeque();
		/*  Prints the items in the deque from first to last, separated by a space. Take linear Time on Size. */
		
	public T removeFirst();
		/* Removes and returns the item at the front of the deque. If no such item exists, returns null. Take Constant Time */

	public T removeLast();
		/* Removes and returns the item at the back of the deque. If no such item exists, returns null. Take Constant Time */

	public T get(int index);
		/** 
		* Iteratively gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. 
		* If no such item exists, returns null. Do not alter the deque! Take linear Time on Size.
		**/
}
