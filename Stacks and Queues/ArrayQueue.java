import java.util.NoSuchElementException;

/**
 * Your implementation of a Queue backed by an array.
 *
 * @author Nishant Roy
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // Do not add instance variables.
    private T[] backingArray;
    private int size;
    private int front;
    private int back;

    /**
     * Construct a Queue with an initial capacity of {@code INITIAL_CAPACITY}.
     * <p>
     * Use Constructor Chaining
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Construct a Queue with the specified initial capacity of
     * {@code initialCapacity}.
     *
     * @param initialCapacity Initial capacity of the backing array.
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int initialCapacity) {
        backingArray = (T[]) new Object[initialCapacity];
        front = 0;
        back = -1;
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void enqueue(T item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        if (size == backingArray.length) {
            T[] temp = (T[]) new Object[backingArray.length * 2];
            int ind = front;
            for (int i = 0; i < size; i++) {
                temp[i] = backingArray[ind];
                ind = (ind + 1) % size;
            }
            backingArray = temp;
            front = 0;
        }

        int r = (front + this.size()) % backingArray.length;
        backingArray[r] = item;
        back++;
        this.size++;
    }

    @Override
    public T dequeue() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T out = backingArray[front];
        backingArray[front] = null;
        front = (front + 1) % backingArray.length;
        this.size--;
        return out;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Used for testing your code.
     * DO NOT USE THIS METHOD!
     *
     * @return the backing array of this queue.
     */
    public Object[] getBackingArray() {
        return backingArray;
    }

}
