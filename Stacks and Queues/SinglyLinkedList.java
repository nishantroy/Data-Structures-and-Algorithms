/**
 * Your implementation of a SinglyLinkedList
 *
 * @author Nishant Roy
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {

    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void addToFront(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Node data cannot be null");
        }

        this.head = new LinkedListNode<T>(data, this.head);
        if (this.size == 0) {
            tail = head;
        }
        this.size++;

    }

    @Override
    public void addToBack(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Node data cannot be null");
        }
        LinkedListNode<T> node = new LinkedListNode<T>(data, null);
        if (head == null) {
            this.head = node;
        } else {
            this.tail.setNext(node);
        }
        this.tail = node;
        this.size++;

    }

    @Override
    public T removeFromFront() {
        if (head == null) {
            return null;
        }

        T answer = this.head.getData();
        this.head = this.head.getNext();
        this.size--;
        if (this.size == 0) {
            this.tail = null;
        }
        return answer;

    }

    @Override
    public T removeFromBack() {
        LinkedListNode<T> node = head;
        LinkedListNode<T> pre = null;

        if (head == null) {
            return null;
        }

        while (node.getNext() != null) {
            pre = node;
            node = node.getNext();
        }
        T answer = this.tail.getData();
        if (pre != null) {
            pre.setNext(null);
        } else {
            head = null;
        }

        this.tail = pre;

        size--;
        if (this.size == 0) {
            this.tail = null;
        }
        return answer;

    }

    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}
