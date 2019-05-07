package synthesizer;

import java.util.Iterator;

/**
 * @author: Nguyen Cuong
 * BoundedQueue Interface
 */

public interface BoundedQueue<T> extends Iterable<T> {
    /**
     * Returns size of buffer
     */
    int capacity();

    /**
     * Returns number of items currently in the buffer
     */
    int fillCount();

    /**
     * Adds item X to the end
     */
    void enqueue(T x);

    /**
     * Deletes and Returns item from the front
     */
    T dequeue();

    /**
     * Returns (but do not deletes) item from the front
     */
    T peek();

    /**
     * Is the buffer empty (fillCount equals zero) ?
     */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /**
     * Is the buffer full (fullCount is same as capacity) ?
     */
    default boolean isFull() {
        return fillCount() == capacity();
    }
}
