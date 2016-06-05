import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Nishant Roy
 * @version 1.0
 */

public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST.
     * YOU DO NOT NEED TO IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
        this.size = 0;
    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) throws IllegalArgumentException {
        this();
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        for (T d : data) {
            if (d == null) {
                throw new IllegalArgumentException("None of the data elements"
                        + "can be null");
            }
            add(d);
        }
    }

    @Override
    public void add(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        if (root == null) {
            root = new BSTNode<T>(data);
            this.size++;
            return;
        }

        add(new BSTNode<T>(data), root);

    }

    /**
     * Helper method to find where data is to be added,
     * used in {@code add(T data)}
     * @param node Node to be added
     * @param root Root node at which node is being added
     */

    private void add(BSTNode<T> node, BSTNode<T> root) {
        int x = root.getData().compareTo(node.getData());

        if (x == 0) {
            return;
        } else if (x < 0) {
            if (root.getRight() == null) {
                root.setRight(node);
                this.size++;
                return;
            }
            add(node, root.getRight());
        } else {
            if (root.getLeft() == null) {
                root.setLeft(node);
                this.size++;
                return;
            }
            add(node, root.getLeft());
        }
    }

    @Override
    public T remove(T data) throws IllegalArgumentException,
            NoSuchElementException {

        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        } else if (root == null) {
            throw new NoSuchElementException("Node containing {" + data
                    + "} could not be found");
        }

        if (root.getData().compareTo(data) == 0) {
            BSTNode<T> tNode = root;
            if (tNode.getLeft() != null) {
                root = root.getLeft();
                if (tNode.getRight() != null) {
                    add(tNode.getRight(), root);
                    this.size--;
                }
            } else if (tNode.getRight() != null) {
                root = tNode.getRight();
            } else {
                root = null;
            }
            this.size--;
            return tNode.getData();
        }
        return remove(data, root);

    }

    /**
     * Helper method for removing a node used in {@code remove(T data)}
     * @param data Data to be removed
     * @param root Root at which we are searching for data to be removed
     * @return Data that was removed
     */
    private T remove(T data, BSTNode<T> root) {
        int x = root.getData().compareTo(data);
        BSTNode<T> leaf;

        if (x < 0) {
            leaf = root.getRight();
        } else {
            leaf = root.getLeft();
        }

        if (leaf == null) {
            throw new NoSuchElementException("Node containing {" + data
                    + "} could not be found");
        } else if (leaf.getData().compareTo(data) == 0) {
            if (leaf.getLeft() != null) {
                if (x > 0) {
                    root.setLeft(leaf.getLeft());
                } else {
                    root.setRight(leaf.getLeft());
                }


                if (leaf.getRight() != null) {
                    add(leaf.getRight(), leaf.getLeft());
                    this.size--;
                }
            } else {
                if (x > 0) {
                    root.setLeft(leaf.getRight());
                } else {
                    root.setRight(leaf.getRight());
                }
            }
            this.size--;
            return leaf.getData();
        }
        return remove(data, leaf);
    }


    @Override
    public T get(T data) throws IllegalArgumentException,
            NoSuchElementException {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        BSTNode<T> tNode = root;

        while (tNode != null) {
            int x = tNode.getData().compareTo(data);
            BSTNode<T> leaf;

            if (x > 0) {
                leaf = tNode.getLeft();
            } else {
                leaf = tNode.getRight();
            }

            if (tNode.getData().equals(data)) {
                return tNode.getData();
            } else if (leaf == null) {
                throw new NoSuchElementException("Node containing {" + data
                        + "} could not be found");
            } else {
                tNode = leaf;
            }
        }

        throw new NoSuchElementException("Node containing {" + data
                + "} could not be found");
    }

    @Override
    public boolean contains(T data) throws IllegalArgumentException,
            NoSuchElementException {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        try {
            get(data);
        } catch (NoSuchElementException exception) {
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public List<T> preorder() {
        return preorder(root);
    }

    /**
     * Helper method for preorder, used in {@code preorder()}
     * @param root Root to start preorder traversal at
     * @return Returns List of elements from preorder traversal
     */
    private List<T> preorder(BSTNode<T> root) {
        if (root == null) {
            return new ArrayList<T>();
        }

        List<T> preList = new ArrayList<>();

        preList.add(root.getData());
        preList.addAll(preorder(root.getLeft()));
        preList.addAll(preorder(root.getRight()));

        return preList;
    }

    @Override
    public List<T> postorder() {
        return postorder(root);
    }

    /**
     * Helper method for postorder, used in {@code postorder()}
     * @param root Root to being postorder traversal at
     * @return List of elements obtained by postorder traversal
     */
    private List<T> postorder(BSTNode<T> root) {
        if (root == null) {
            return new ArrayList<T>();
        }

        List<T> postList = postorder(root.getLeft());
        postList.addAll(postorder(root.getRight()));
        postList.add(root.getData());

        return postList;
    }

    @Override
    public List<T> inorder() {
        return inorder(root);
    }

    /**
     * Helper method for inorder, used in {@code inorder()}
     * @param root Root to being inorder traversal at
     * @return List of elements obtained by inorder traversal
     */

    private List<T> inorder(BSTNode<T> root) {
        if (root == null) {
            return new ArrayList<T>();
        }

        List<T> inList = inorder(root.getLeft());
        inList.add(root.getData());
        inList.addAll(inorder(root.getRight()));

        return inList;
    }

    @Override
    public List<T> levelorder() {
        List<T> levelList = new ArrayList<>();

        if (size == 0) {
            return levelList;
        }

        Queue<BSTNode<T>> nodeQueue =
                new ArrayBlockingQueue<>(this.size);

        nodeQueue.add(root);

        while (!nodeQueue.isEmpty()) {
            int rowSize = nodeQueue.size();

            while (rowSize-- > 0) {
                BSTNode<T> curr = nodeQueue.remove();
                if (curr.getLeft() != null) {
                    nodeQueue.add(curr.getLeft());
                }

                if (curr.getRight() != null) {
                    nodeQueue.add(curr.getRight());
                }

                levelList.add(curr.getData());
            }
        }

        return levelList;
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public int height() {
        return height(root);
    }

    /**
     * Helper method for height(), used in {@code height()}
     * @param root Root of BST to find height for
     * @return Height of BST
     */

    private int height(BSTNode<T> root) {
        if (root == null) {
            return -1;
        }

        return Math.max(height(root.getLeft()), height(root.getRight())) + 1;
    }

    /**
     * Compares two BSTs and checks to see if the trees are the same.  If
     * the trees have the same data in a different arrangement, this method
     * should return false.  This will only return true if the tree is in the
     * exact same arrangement as the other tree.
     * <p>
     * You may assume that you won't get a BST with a different generic type.
     * For example, if this BST holds Strings, then you will not get as an input
     * a BST that holds Integers.
     * <p>
     * Be sure to also implement the other general checks that .equals() should
     * check as well.
     * <p>
     * Should have a running time of O(n).
     *
     * @param other the Object we are comparing this BST to
     * @return true if other is equal to this BST, false otherwise.
     */
    public boolean equals(Object other) {
        if (!(other instanceof BST)) {
            return false;
        }

        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }

        BST<T> b = (BST<T>) other;
        if (this.size() != b.size()) {
            return false;
        }

        return equals(this.getRoot(), b.getRoot());
    }

    /**
     * Helper method for equals, used in {@code equals(Object other)}
     * @param a Root of first BST to compare
     * @param b Root of second BST to compare
     * @return boolean saying whether the 2 BST's are equal or not
     */
    private boolean equals(BSTNode<T> a, BSTNode<T> b) {
        if (a == null && b == null) {
            return true;
        } else if (a == null || b == null) {
            return false;
        }

        boolean checkLeftTree = equals(a.getLeft(), b.getLeft());
        boolean checkThis = a.getData().equals(b.getData());
        boolean checkRightTree = equals(a.getRight(), b.getRight());
        return checkLeftTree && checkThis && checkRightTree;
    }


    @Override
    public BSTNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}
