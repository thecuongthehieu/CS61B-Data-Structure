// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import synthesizer.AbstractBoundedQueue;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.capacity = capacity;
        rb = (T[]) new Object[this.capacity];

        this.fillCount = 0;
        first = 0;
        last = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
       if (isFull()) {
          throw new RuntimeException("Ring Buffer Overflow");
       }

       rb[last] = x;
       ++this.fillCount;

       if (last == (this.capacity - 1)) {
           last = 0;
       } else {
           ++last;
       }

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }

        T item = rb[first];
        rb[first] = null;
        --this.fillCount;

        if (first == (this.capacity - 1)) {
            first = 0;
        } else {
            ++first;
        }
        return item;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Is Empty");
        } else {
            return rb[first];
        }
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.

    /**
     * private class itemIterator for enhanced-for loop
     */
    private class itemIterator implements Iterator<T> {
        private int ptr;
        private int numItems;

        public itemIterator() {
            ptr = first;
            numItems = 0;

        }

        @Override
        public boolean hasNext() {
            return numItems != fillCount();
        }

        @Override
        public T next() {
            T item = rb[ptr];
            if (ptr == (capacity() - 1)) {
                ptr = 0;
            } else {
                ++ptr;
            }
            ++numItems;
            return item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new itemIterator();
    }
}
