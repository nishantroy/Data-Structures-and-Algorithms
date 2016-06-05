/**
 * A container for key-value pairs for the skip list.
 * 
 * DO NOT EDIT THIS CLASS!
 *
 * @version 1.0
 * @author CS 1332 TAs
 */
public class SkipListNode<T extends Comparable<? super T>> {
    private T data;
    private int level;
    private SkipListNode<T> prev;
    private SkipListNode<T> next;
    private SkipListNode<T> up;
    private SkipListNode<T> down;

    /**
     * Constructs a skip list node for storing key-value pairs.
     * 
     * @param data the data to store
     * @param level the level of the node
     */
    public SkipListNode(T data, int level) {
        this(data, level, null, null, null, null);
    }

    /**
     * Constructs a skip list node for storing key-value pairs with the
     * specified neighbors.
     * 
     * @param data the data to store
     * @param level the level of the node
     * @param prev the prev node from this node
     * @param next the next node from this node
     * @param up the node above this node
     * @param down the node below this node
     */
    public SkipListNode(T data, int level,
                SkipListNode<T> prev, SkipListNode<T> next, SkipListNode<T> up,
                SkipListNode<T> down) {
        this.data = data;
        this.level = level;
        this.prev = prev;
        this.next = next;
        this.up = up;
        this.down = down;
    }


    /**
     * Gets the data of the node.
     * 
     * @return the data stored in the node
     */
    public T getData() {
        return data;
    }

    /**
     * Gets the level of the node.
     * 
     * @return the level of the node
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the previous node.
     * 
     * @return the previous node at this level
     */
    public SkipListNode<T> getPrev() {
        return prev;
    }

    /**
     * Gets the next node.
     * 
     * @return the next node at this level
     */
    public SkipListNode<T> getNext() {
        return next;
    }

    /**
     * Gets the node above this node.
     * 
     * @return the node one level above this node
     */
    public SkipListNode<T> getUp() {
        return up;
    }

    /**
     * Gets the node below this node.
     * 
     * @return the node one level below this node
     */
    public SkipListNode<T> getDown() {
        return down;
    }


    /**
     * Set the data of this node.
     * 
     * @param data the data the node will store
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Set the level of this node.
     * 
     * @param level the level of the node
     */
    public void setLevel(int level) {
        this.level = level;
    }


    /**
     * Set the previous node from this node.
     * 
     * @param prev the node to point to
     */
    public void setPrev(SkipListNode<T> prev) {
        this.prev = prev;
    }

    /**
     * Set the next node from this node.
     * 
     * @param next the node to point to
     */
    public void setNext(SkipListNode<T> next) {
        this.next = next;
    }

    /**
     * Set the node above this node.
     * 
     * @param up the node to point to
     */
    public void setUp(SkipListNode<T> up) {
        this.up = up;
    }

    /**
     * Set the node below this node.
     * 
     * @param down the node to point to
     */
    public void setDown(SkipListNode<T> down) {
        this.down = down;
    }

    @Override
    public String toString() {
        return String.format("Node at level %d containing %s", level, data);
    }
}
