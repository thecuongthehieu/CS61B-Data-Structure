/**
* @author Nguyen Cuong: Linked-List Deque Abstract Data Type
* Do not use Java’s built in LinkedList data structure
*/

public class LinkedListDeque<T> {
	private class Node {
		/**
		* Nested Private Static Class: Do can not use any instance variables and methods of the outer class
		*/
		public T item;
		public Node next;
		public Node prev;

		public Node(T i, Node p, Node n) {
			/* Constructor of Node Class */
			this.item = i;
			this.prev = p;
			this.next = n;
		}
	}

	private Node frontSentinel;
	private Node backSentinel;
	private int size;

	public LinkedListDeque() { 
		/* Constructor of LinkedListDeque Class */
		frontSentinel = new Node(null, null, null);
		backSentinel = new Node(null, null, null);
		frontSentinel.next = backSentinel;
		backSentinel.prev = frontSentinel;

		size = 0;
	}

	public int size() {
		/* Returns the number of items in the deque. Take Constant Time */
		return this.size;
	}

	public void addFirst(T item) {
		/* Adds an item of type T to the front of the deque. Take Constant Time */
		Node newNode = new Node(item, frontSentinel, frontSentinel.next);

		frontSentinel.next.prev = newNode;
		frontSentinel.next = newNode;
		++size;
	}

	public void addLast(T item) {
		/* Adds an item of type T to the back of the deque. Take Constant Time */
		Node newNode = new Node(item, backSentinel.prev, backSentinel);

		backSentinel.prev.next = newNode;
		backSentinel.prev = newNode;
		++size;
	}

	public boolean isEmpty() {
		/* Returns true if deque is empty, false otherwise. Take Constant Time */
		return this.size() == 0;
	}

	private Node getFirstNode() {
		/* private helper methods */
		return frontSentinel.next;
	}

	private Node getLastNode() {
		/* private helper methods */
		return backSentinel.prev;
	}

	public T removeFirst() {
		/* Removes and returns the item at the front of the deque. If no such item exists, returns null. Take Constant Time */
		if (isEmpty()) {
			return null;
		}
		Node firstNode = getFirstNode();

		Node secondNode = firstNode.next;
		frontSentinel.next = secondNode;
		secondNode.prev = frontSentinel;
		--size;

		return firstNode.item;
	}

	public T removeLast() {
		/* Removes and returns the item at the back of the deque. If no such item exists, returns null. Take Constant Time */
		if (isEmpty()) {
			return null;
		}
		Node lastNode = getLastNode();

		Node prevLastNode = lastNode.prev;
		backSentinel.prev = prevLastNode;
		prevLastNode.next = backSentinel;
		--size;

		return lastNode.item;
	}

	public T get(int index) {
		/** 
		* Iteratively gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. 
		* If no such item exists, returns null. Do not alter the deque! Take linear Time on Size.
		**/
		if (isEmpty() || index < 0 || index > size - 1) {
			return null;
		}

		Node p = frontSentinel;
		for (int i = 0; i <= index; ++i) {
			p = p.next;
		}

		return p.item;
	}
	private T getRecursive(int count, int index, Node p) {
		if (count == index) {
			return p.item;
		} else {
			return getRecursive(count + 1, index, p.next);
		}
	}

	public T getRecursive(int index) {
		/** 
		* Recursively gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. 
		* If no such item exists, returns null. Do not alter the deque! Take linear Time on Size.
		**/
		if (index < 0 || index > size - 1) {
			return null;
		}
		return getRecursive(0, index, frontSentinel.next);
	}

	public void printDeque() {
		/*  Prints the items in the deque from first to last, separated by a space. Take linear Time on Size. */
		Node p = frontSentinel;
		while (p.next != backSentinel) {
			p = p.next;
			System.out.print(p.item + " ");
		}
		System.out.println();
	}
} 