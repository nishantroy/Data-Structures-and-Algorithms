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
    public void addAtIndex(int index, T data)
            throws IllegalArgumentException, IndexOutOfBoundsException {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index can't be negative"
                    + " or greater than the size of the Linked List.");
        } else if (data == null) {
            throw new IllegalArgumentException("Data stored in node cannot"
                    + " be null");
        }
        LinkedListNode<T> node = head;
        LinkedListNode<T> temp = new LinkedListNode<T>(data);


        if (index == 0) {
            this.addToFront(data);
        } else if (index == this.size) {
            this.addToBack(data);
        } else {
            while (--index > 0) {
                node = node.getNext();
            }
            temp.setNext(node.getNext());
            node.setNext(temp);
            size++;
        }
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index can't be negative "
                    + "or greater than/equal to size of linked list");
        }

        LinkedListNode<T> node = head;
        if (index == 0) {
            return this.head.getData();
        } else if (index == this.size - 1) {
            return this.tail.getData();
        } else {
            while (index > 0) {
                index--;
                node = node.getNext();
            }

            return node.getData();
        }
    }

    @Override
    public T removeAtIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index can't be negative "
                    + "or greater than/equal to size of linked list");
        }

        LinkedListNode<T> node = head;
        LinkedListNode<T> pre = null;
        T answer;
        if (index == 0) {
            answer = this.removeFromFront();
        } else if (index == this.size - 1) {
            answer = this.removeFromBack();
        } else {
            for (int i = 0; i < index; i++) {
                pre = node;
                node = node.getNext();
            }
            if (pre != null) {
                pre.setNext(node.getNext());
            } else {
                head = head.getNext();
            }
            answer = node.getData();
            size--;
        }
        return answer;
    }

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
    public boolean removeAllOccurrences(T data)
            throws IllegalArgumentException {
        boolean answer = false;
        LinkedListNode<T> node = head;
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        } else {
            int index = 0;
            while (node != null) {
                if (node.getData().equals(data)) {
                    this.removeAtIndex(index);
                    answer = true;
                } else {
                    index++;
                }
                node = node.getNext();
            }
        }
        return answer;

    }

    @Override
    public Object[] toArray() {
        LinkedListNode<T> node = head;
        Object[] arr = new Object[this.size];
        for (int i = 0; i < this.size; i++) {
            arr[i] = node.getData();
            node = node.getNext();
        }
        return arr;
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
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;

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
