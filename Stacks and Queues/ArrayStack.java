import java.util.NoSuchElementException;

/**
 * Your implementation of a Stack backed by an array.
 *
 * @author Nishant Roy
 * @version 1.0
 */
public class ArrayStack<T> implements StackInterface<T> {
    // Do not add any new instance variables!
    private T[] backingArray;
    private int size;

    /**
     * Construct a Stack with an initial capacity of {@code INITIAL_CAPACITY}.
     * <p>
     * Use constructor chaining.
     */
    @SuppressWarnings("unchecked")
    public ArrayStack() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Construct a Stack with the specified initial capacity of
     * {@code initialCapacity}.
     *
     * @param initialCapacity Initial capacity of the backing array.
     */
    @SuppressWarnings("unchecked")
    public ArrayStack(int initialCapacity) {
        backingArray = (T[]) new Object[initialCapacity];
    }

    @Override
    @SuppressWarnings("unchecked")
    public void push(T item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (size == backingArray.length) {
            T[] temp = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < backingArray.length; i++) {
                temp[i] = backingArray[i];
            }
            backingArray = temp;
        }

        backingArray[size] = item;
        this.size++;
    }

    @Override
    public T pop() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        T out = backingArray[size - 1];
        backingArray[size - 1] = null;
        this.size--;
        return out;

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
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
