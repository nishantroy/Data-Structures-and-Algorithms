import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
/**
 * Your implementation of a skip list.
 *
 * @author Nishant Roy
 * @version 1.0
 */
public class SkipList<T extends Comparable<? super T>>
    implements SkipListInterface<T> {
    // Do not add any additional instance variables
    private CoinFlipper coinFlipper;
    private int size;
    private SkipListNode<T> head;

    /**
     * Constructs a SkipList object that stores data in ascending order.
     * When an item is inserted, the flipper is called until it returns a tails.
     * If, for an item, the flipper returns n heads, the corresponding node has
     * n + 1 levels.
     *
     * @param coinFlipper the source of randomness
     */
    public SkipList(CoinFlipper coinFlipper) {
        this.coinFlipper = coinFlipper;
        this.head = new SkipListNode<>(null, 1);
        this.size = 0;
    }

    @Override
    public T first() {
        if (this.size == 0) {
            throw new NoSuchElementException("Skip list is empty.");
        }

        SkipListNode<T> node = head;

        while (node.getDown() != null) {
            node = node.getDown();
        }

        return node.getNext().getData();
    }

    @Override
    public T last() {
        if (this.size == 0) {
            throw new NoSuchElementException("Skip list is empty.");
        }

        SkipListNode<T> node = head;

        boolean boo = true;

        while (boo) {
            while (node.getNext() != null) {
                node = node.getNext();
            }
            if (node.getLevel() != 1) {
                node = node.getDown();
            } else {
                boo = false;
            }
        }



        return node.getData();

    }

    @Override
    public void put(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }

        SkipListNode<T> node = search(data);
        SkipListNode<T> toAdd = new SkipListNode<T>(data, 1);

        int add = 0;

        while (coinFlipper.flipCoin() == CoinFlipper.Coin.HEADS) {
            add++;
        }

        toAdd.setNext(node.getNext());

        if (node.getNext() != null) {
            node.getNext().setPrev(toAdd);
        }

        node.setNext(toAdd);
        toAdd.setPrev(node);

        int i = add + 2;
        int ht = head.getLevel();

        while (i > ht) {
            addLevel();
            i--;
        }

        while (add > 0) {

            while (node.getUp() == null) {
                node = node.getPrev();
            }

            node = node.getUp();
            SkipListNode<T> addNode = new SkipListNode<T>(data,
                    node.getLevel());

            addNode.setPrev(node);
            addNode.setNext(node.getNext());
            addNode.setDown(toAdd);
            if (node.getNext() != null) {
                node.getNext().setPrev(addNode);
            }
            node.setNext(addNode);

            toAdd.setUp(addNode);

            toAdd = addNode;

            add--;
        }




        size++;
    }

    /**
     * Helper method to add levels to Skip List, used in put
     */

    private void addLevel() {
        SkipListNode<T> node = new SkipListNode<T>(null, head.getLevel() + 1);
        node.setNext(null);
        node.setDown(head);
        head.setUp(node);
        head = node;
    }

    /**
     * Private helper method to find Node with greatest data <= parameter data
     * @param data Data being searched for
     * @return Node with greatest data <= parameter data
     */

    private SkipListNode<T> search(T data) {
        SkipListNode<T> node = head;
        if (size > 1) {
            node = node.getDown();
        }

        boolean boo = true;

        while (boo) {
            while (node.getNext() != null && data.compareTo(node.getNext()
                    .getData()) >= 0) {
                node = node.getNext();
            }
            if (node.getLevel() != 1) {
                node = node.getDown();
            } else {
                boo = false;
            }
        }

        return node;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }


        SkipListNode<T> node = search(data);

        if (data.compareTo(node.getData()) == 0) {
            T toreturn = node.getData();
            while (node != null) {
                if (node.getPrev().getData() == null
                        && node.getNext() == null) {
                    node.getPrev().setUp(null);
                    node.getPrev().setNext(null);
                    node.setUp(null);
                    head = node.getPrev();
                } else if (node.getNext() != null) {
                    node.getPrev().setNext(node.getNext());
                    node.getNext().setPrev(node.getPrev());
                } else {
                    node.getPrev().setNext(null);
                }
                node = node.getUp();
            }
            size--;
            return toreturn;
        } else {
            throw new NoSuchElementException("Skip list does not contain this "
                    + "data");
        }

    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }

        try {
            get(data);
        } catch (NoSuchElementException e) {
            return false;
        }

        return true;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }

        SkipListNode<T> node = search(data);

        if (node.getData() != null && node.getData().compareTo(data) == 0) {
            return node.getData();
        } else {
            throw new NoSuchElementException("Skip list does not contain this "
                    + "data");
        }


    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.head = new SkipListNode<T>(null, 1);
        this.size = 0;
    }

    @Override
    public Set<T> dataSet() {
        Set<T> dataset = new HashSet<>();

        if (size == 0) {
            return dataset;
        }

        SkipListNode<T> node = head;

        while (node.getLevel() != 1) {
            node = node.getDown();
        }

        while (node.getNext() != null) {
            node = node.getNext();
            dataset.add(node.getData());
        }

        return dataset;


    }

    @Override
    public SkipListNode<T> getHead() {
        return head;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("**********************\n");
        builder.append(String.format("SkipList (size = %d)\n", size()));
        SkipListNode<T> levelCurr = getHead();

        while (levelCurr != null) {
            SkipListNode<T> curr = levelCurr;
            int level = levelCurr.getLevel();
            builder.append(String.format("Level: %2d   ", level));

            while (curr != null) {
                builder.append(String.format("(%s)%s", curr.getData(),
                            curr.getNext() == null ? "\n" : ", "));
                curr = curr.getNext();
            }
            levelCurr = levelCurr.getDown();
        }
        builder.append("**********************\n");
        return builder.toString();
    }

}
