/**
 * This interface describes the public methods needed for a SinglyLinkedList
 * that will be used as the backing structure for a Stack and a Queue.
 *
 * We've given you the expected Big-O for each method this time around.
 *
 * DO NOT ALTER THIS FILE!!
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public interface LinkedListInterface<T> {

    /**
     * Add a new node to the front of your linked list that holds the given
     * data. Make sure to update head.
     *
     * Must be O(1).
     *
     * @param data The data that the new node should hold.
     * @throws IllegalArgumentException if data is null.
     */
    public void addToFront(T data);

    /**
     * Add a new node to the back of your linked list that holds the given data.
     * Make sure to update tail.
     *
     * Must be O(1).
     *
     * @param data The data that the new node should hold.
     * @throws IllegalArgumentException if data is null.
     */
    public void addToBack(T data);

    /**
     * Remove the front node from the list and return the data from it. If the
     * list is empty, return {@code null}.
     *
     * Must be O(1).
     *
     * @return The data from the front node or null.
     */
    public T removeFromFront();

    /**
     * Remove the back node from the list and return the data from it. If the
     * list is empty, return {@code null}.
     *
     * Must be O(n).
     *
     * @return The data from the last node or null.
     */
    public T removeFromBack();

    /**
     * Return a boolean value representing whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return True if empty. False otherwise.
     */
    public boolean isEmpty();

    /**
     * Return the size of the list as an integer.
     *
     * Must be O(1).
     *
     * @return The size of the list.
     */
    public int size();

    /**
     * Reference to the head node of the linked list.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return Node representing the head of the linked list.
     */
    public LinkedListNode<T> getHead();

    /**
     * Reference to the tail node of the linked list.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return Node representing the tail of the linked list.
     */
    public LinkedListNode<T> getTail();
}
