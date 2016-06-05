import java.util.NoSuchElementException;

/**
 * Your implementation of a max heap.
 *
 * @author Nishant Roy
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>>
        implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial size of {@code STARTING_SIZE}.
     * <p>
     * Use the constant field in the interface. Do not use magic numbers!
     */
    @SuppressWarnings("unchecked")
    public MaxHeap() {
        backingArray = (T[]) new Comparable[STARTING_SIZE];
        this.size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void add(T item) {

        if (item == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        this.size++;

        if (size == backingArray.length) {
            T[] temp = (T[]) new Comparable[backingArray.length * 2];
            temp[0] = null;
            for (int i = 1; i < backingArray.length; i++) {
                temp[i] = backingArray[i];
            }
            backingArray = temp;
        }
        backingArray[size] = item;

        int ind = size;

        upheap(ind);

    }

    /**
     * Private method to swap two elements
     *
     * @param i Index of first element to be swapped
     * @param j Index of first element to be swapped
     */
    private void swap(int i, int j) {
        T t = backingArray[i];
        backingArray[i] = backingArray[j];
        backingArray[j] = t;
    }

    /**
     * Private method to upheap an element
     * @param ind Index of element to upheap
     */
    private void upheap(int ind) {
        while (ind > 1) {
            int x = ind / 2;
            if (backingArray[ind].compareTo(backingArray[x]) > 0) {
                swap(ind, x);
            }
            ind = x;
        }
    }

    @Override
    public T remove() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        } else {
            T t = backingArray[1];
            backingArray[1] = backingArray[size];
            backingArray[size] = null;
            int a = 1;
            downheap(a);
            this.size--;
            return t;
        }
    }

    /**
     * Private method to downheap an element
     *
     * @param a Index of element to downheap
     */

    private void downheap(int a) {
        while (a * 2 < size) {
            int left = a * 2;
            int swapper = left;
            if ((left + 1) < size) {
                int right = left + 1;
                if (backingArray[right]
                        .compareTo(backingArray[left]) > 0) {
                    swapper = right;
                }
            }

            if (backingArray[swapper].compareTo(backingArray[a]) > 0) {
                swap(a, swapper);
            }

            a = swapper;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        backingArray = (T[]) new Comparable[STARTING_SIZE];
        this.size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT CHANGE THIS METHOD!
        return backingArray;
    }

}
