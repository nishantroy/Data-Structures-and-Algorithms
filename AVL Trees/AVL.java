import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Nishant Roy
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {

    // Do not make any new instance variables.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {

    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {

        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        for (T t : data) {
            add(t);
        }


    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        AVLNode<T> node = new AVLNode<>(data);

        if (contains(data)) {
            return;
        }

        if (this.size == 0) {
            root = node;
            size++;
            findHtBf(root);
        } else {
            add(root, node);
            size++;
        }


    }

    /**
     * Private helper method to add to AVL
     * @param root Root of tree we are adding to
     * @param node Node to be added
     */
    private void add(AVLNode<T> root, AVLNode<T> node) {
        if (root == null) {
            return;
        }

        T rootData = root.getData();
        T nodeData = node.getData();

        //Root is less than node to add, so added to right
        if (rootData.compareTo(nodeData) < 0) {
            if (root.getRight() == null) {
                root.setRight(node);
            } else {
                add(root.getRight(), node);
            }

            //Root is greater than node to add, so added to left
        } else if (rootData.compareTo(nodeData) > 0) {
            if (root.getLeft() == null) {
                root.setLeft(node);
            } else {
                add(root.getLeft(), node);
            }
        }

        //Update balance and height
        findHtBf(root);
        //Restructure
        trinodeRotate(root);

    }

    /**
     * Private method to find height and balance factor of nodes (used in add)
     * @param node Node to find height and balance factor for
     */

    private void findHtBf(AVLNode<T> node) {
        int lh = (node.getLeft() != null) ? node.getLeft().getHeight() : -1;
        int rh = (node.getRight() != null) ? node.getRight().getHeight() : -1;

        node.setHeight(Math.max(lh, rh) + 1);
        node.setBalanceFactor(lh - rh);
    }

    /**
     * Private helper to find height and balance for all nodes in subtree
     * @param root Root of subtree to calculate height and balance factor for
     */

    private void findHtBfSub(AVLNode<T> root) {
        if (root.getLeft() != null) {
            findHtBfSub(root.getLeft());
        }

        if (root.getRight() != null) {
            findHtBfSub(root.getRight());
        }

        findHtBf(root);
    }

    /**
     * Private method to balance tree (used in add)
     * @param node Node to perform trinode rotation on
     */
    private void trinodeRotate(AVLNode<T> node) {

        //Tree is left heavy
        if (node.getBalanceFactor() > 1) {
            if (node.getLeft() != null) {
                if (node.getLeft().getBalanceFactor() >= 0) {
                    rightRotate(node);
                } else if (node.getLeft().getBalanceFactor() < 0) {
                    leftRightRotate(node);
                }
            }

            //Tree is right heavy
        } else if (node.getBalanceFactor() < -1) {
            if (node.getRight() != null) {
                if (node.getRight().getBalanceFactor() > 0) {
                    rightLeftRotate(node);
                } else if (node.getRight().getBalanceFactor() <= 0) {
                    leftRotate(node);
                }
            }
        }

    }

    /**
     * Private method to perform right rotation:
     * Called when node is left-heavy, and left child is left-heavy.
     * @param node Node to perform right rotation on
     */

    private void rightRotate(AVLNode<T> node) {
        AVLNode<T> newNode = new AVLNode<>(node.getData());
        AVLNode<T> left = node.getLeft();

        newNode.setRight(node.getRight());
        newNode.setLeft(left.getRight());

        node.setRight(newNode);
        node.setData(left.getData());
        node.setLeft(left.getLeft());
        left.setLeft(null);

        findHtBfSub(node);

    }

    /**
     * Private method to perform left rotation:
     * Called when node is right-heavy, and left child is right-heavy.
     * @param node Node to perform left rotation on
     */

    private void leftRotate(AVLNode<T> node) {
        T origData = node.getData();
        AVLNode<T> right = node.getRight();
        AVLNode<T> newNode = new AVLNode<>(origData);

        newNode.setLeft(node.getLeft());
        newNode.setRight(right.getLeft());

        node.setLeft(newNode);
        node.setData(right.getData());
        node.setRight(right.getRight());
        right.setRight(null);

        findHtBfSub(node);
    }

    /**
     * Private method to perform left-right rotation:
     * Called when node is left-heavy, and left child is right-heavy.
     * @param node Node to perform rotation at
     */

    private void leftRightRotate(AVLNode<T> node) {
        leftRotate(node.getLeft());
        rightRotate(node);
    }

    /**
     * Private method to perform right-left rotation:
     * Called when node is right-heavy, and right child is left-heavy.
     * @param node Node to perform rotation at
     */

    private void rightLeftRotate(AVLNode<T> node) {
        rightRotate(node.getRight());
        leftRotate(node);
    }

    @Override
    public T remove(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        if (!this.contains(data)) {
            throw new NoSuchElementException("Tree does not contain this data");
        }

        if (size == 1 && root.getData().compareTo(data) == 0) {
            T out = root.getData();
            root = null;
            size--;
            return out;

        } else {
            root = remove(root, data);
        }
        size--;
        return data;
    }

    /**
     * Helper method to find successor for removing node
     * @param node Node to find successor for
     * @return Successor of node
     */
    private T successor(AVLNode<T> node) {
        if (node.getRight() != null) {
            node = node.getRight();
        }
        if (node == null) {
            return null;
        }
        AVLNode<T> left = node.getLeft();
        if (left == null) {
            return node.getData();
        } else {
            return successor(left);
        }
    }

    /**
     * Private recursive helper method for remove
     * @param node Node at which we begin search to remove
     * @param data Data we are trying to remove
     * @return Removed node
     */

    private AVLNode<T> remove(AVLNode<T> node, T data) {
        if (node == null) {
            return null;
        }

        //this node is the one we need to remove
        if (data.compareTo(node.getData()) == 0) {

            //leaf
            if (node.getLeft() == null && node.getRight() == null) {
                return null;

                //only right child
            } else if (node.getLeft() == null) {
                return node.getRight();

                //only left child
            } else if (node.getRight() == null) {
                return node.getLeft();

                //two children
            } else {
                T replaceData = successor(node);
                node.setData(replaceData);
                node.setRight(remove(node.getRight(), replaceData));
            }
            //node to remove is in left subtree of current node
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(remove(node.getLeft(), data));

            //mode to remove is in right subtree of current node
        } else {
            node.setRight(remove(node.getRight(), data));
        }


        //update height and balance factor
        findHtBf(node);

        //restructure
        trinodeRotate(node);

        return node;
    }


    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        return get(root, data);

    }

    /**
     * Private helper method to get some data in tree (used in get)
     *
     * @param root Root of tree we are trying to retrieve data from
     * @param data Data we are looking for in tree
     * @return Data found in tree
     */

    private T get(AVLNode<T> root, T data) {
        if (root == null) {
            throw new NoSuchElementException("Tree does not contain this data");
        }

        if (root.getData().compareTo(data) > 0) {
            return get(root.getLeft(), data);
        } else if (root.getData().compareTo(data) < 0) {
            return get(root.getRight(), data);
        }

        return root.getData();
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }


        //get throws exceptions when data isn't found, so that means
        //tree doesn't contain this data
        try {
            get(data);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return false;
        }


        //if no exceptions, then true
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> preList = new ArrayList<>();
        preorder(root, preList);

        return preList;
    }

    /**
     * Helper method for preorder, used in {@code preorder()}
     * @param root Root to start preorder traversal at
     * @param preList List of elements by preorder traversal
     */
    private void preorder(AVLNode<T> root, List<T> preList) {
        if (root != null) {
            preList.add(root.getData());
        }


        if (root.getLeft() != null) {
            preorder(root.getLeft(), preList);
        }
        if (root.getRight() != null) {
            preorder(root.getRight(), preList);
        }

    }

    @Override
    public List<T> postorder() {
        List<T> postList = new ArrayList<>();
        postorder(root, postList);

        return postList;
    }

    /**
     * Recursive Helper method for postorder, used in {@code postorder()}
     *
     * @param root Root to being postorder traversal at
     * @param postList List of elements by postorder traversal
     */
    private void postorder(AVLNode<T> root, List<T> postList) {
        if (root.getLeft() != null) {
            postorder(root.getLeft(), postList);
        }

        if (root.getRight() != null) {
            postorder(root.getRight(), postList);
        }
        if (root != null) {
            postList.add(root.getData());
        }
    }

    @Override
    public List<T> inorder() {
        List<T> inList = new ArrayList<>();
        inorder(root, inList);
        return inList;
    }

    /**
     * Helper method for inorder, used in {@code inorder()}
     *
     * @param root Root to being inorder traversal at
     * @param inList List of elements by inorder traversal
     */

    private void inorder(AVLNode<T> root, List<T> inList) {

        if (root.getLeft() != null) {
            inorder(root.getLeft(), inList);
        }
        if (root != null) {
            inList.add(root.getData());
        }

        if (root.getRight() != null) {
            inorder(root.getRight(), inList);
        }

    }

    @Override
    public List<T> levelorder() {
        List<T> levelList = new ArrayList<>();

        if (size == 0) {
            return levelList;
        }

        Queue<AVLNode<T>> nodeQueue =
                new ArrayBlockingQueue<>(this.size);

        nodeQueue.add(root);

        while (!nodeQueue.isEmpty()) {
            int rowSize = nodeQueue.size();

            while (rowSize-- > 0) {
                AVLNode<T> curr = nodeQueue.remove();
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
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        }
        return root.getHeight();
    }


    /**
     * Compares two AVLs and checks to see if the trees are the same.  If
     * the trees have the same data in a different arrangement, this method
     * should return false.  This will only return true if the tree is in the
     * exact same arrangement as the other tree.
     * You may assume that you won't get an AVL with a different generic type.
     * For example, if this AVL holds Strings, then you will not get as an input
     * an AVL that holds Integers.
     * Be sure to also implement the other general checks that .equals() should
     * check as well.
     *
     * @param other the Object we are comparing this AVL to
     * @return true if other is equal to this AVL, false otherwise.
     */

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        return equals(this.getRoot(), ((AVL) other).getRoot());

    }

    /**
     * Recursive helper method for equality check
     * @param root1 Root of first AVL being checked for equality
     * @param root2 Root of second AVL being checked for equality
     * @return boolean if trees are equal
     */

    private boolean equals(AVLNode<T> root1, AVLNode<T> root2) {
        if (root1 == root2) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        return root1.getData().equals(root2.getData())
                && equals(root1.getRight(), root2.getRight())
                && equals(root1.getLeft(), root2.getLeft());
    }

    @Override
    public int hashCode() {
        int result = root != null ? root.hashCode() : 0;
        result = 31 * result + size;
        return result;
    }

    @Override
    public AVLNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}