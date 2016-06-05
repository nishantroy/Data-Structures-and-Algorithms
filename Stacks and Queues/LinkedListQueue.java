import java.util.NoSuchElementException;

/**
 * Your implementation of a Queue backed by a LinkedList.
 *
 * @author Nishant Roy
 * @version 1.0
 */
public class LinkedListQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    // Do not modify this variable.
    private LinkedListInterface<T> backingList;

    /**
     * Initialize the Queue.
     */
    public LinkedListQueue() {
        backingList = new SinglyLinkedList<>();
    }

    @Override
    public void enqueue(T item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        backingList.addToBack(item);
    }

    @Override
    public T dequeue() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return backingList.removeFromFront();
    }

    @Override
    public int size() {
        return backingList.size();
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Used for testing your code.
     * DO NOT USE THIS METHOD!
     *
     * @return the backing list of this queue.
     */
    public LinkedListInterface<T> getBackingList() {
        return backingList;
    }

}
