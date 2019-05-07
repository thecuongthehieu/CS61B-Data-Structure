package synthesizer;

/**
 * @author Nguyen Cuong
 * BoundedQueue Abstract Class
 */

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    /**
     * Returns size of buffer
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Returns number of items currently in the buffer
     */
    public int fillCount() {
        return fillCount;
    }
    /**
     * Adds item X to the end
     */
    public abstract void enqueue(T x);

    /**
     * Deletes and Returns item from the front
     */
    public abstract T dequeue();

    /**
     * Returns (but do not deletes) item from the front
     */
    public abstract T peek();
}
