/**
 * Class that implements various Utility methods on a BST.
 *
 * @author YOUR NAME HERE
 * @version 1.0
 */
public class BSTUtilities {

    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode root;

    // FOR ALL METHODS BELOW (except add(), which is provided for you):
    //
    // You may NOT write any additional methods, public or private. You may NOT
    // use loops or external data structures (such as arrays, lists, or queues)
    // of any kind. You may NOT modify the root instance variable to point to
    // another node. You may NOT modify the tree in any way. You may NOT change
    // the method headers of any method (public or private).
    //
    // You MUST use proper constructor chaining for these constructors.
    // Failure to do so will result in significant penalties.

    /**
     * A no argument constructor that should initialize an empty BST.
     * Fill out this constructor USING CONSTRUCTOR CHAINING.
     * (Do not leave it blank.)
     */
    public BSTUtilities() {

    }

    /**
     * Initializes the BST with the data in the int array.
     * <p>
     * Fill out this constructor USING CONSTRUCTOR CHAINING.
     *
     * @param nums The integers to add to the tree.  If null, do nothing.
     */
    public BSTUtilities(int[] nums) {
        this(nums, 0, nums.length);
    }

    /**
     * Initializes the BST with the elements from indices 0 to end (excluding
     * end).
     * <p>
     * Fill out this constructor USING CONSTRUCTOR CHAINING.
     *
     * @param nums The integers to add to the tree.  If null, do nothing.
     * @param end  The index to stop adding items from the array.
     * @throws ArrayIndexOutOfBoundsException if end > nums.length.
     */
    public BSTUtilities(int[] nums, int end) {
        this(nums, 0, end);
    }

    /**
     * Initializes the BST with the elements from indices start to end
     * (excluding end).
     *
     * @param nums  The integers to add to the tree.  If null, do nothing.
     * @param start The index to start adding items from.
     * @param end   The index to stop adding items.
     * @throws ArrayIndexOutOfBoundsException if
     *                                        start < 0 or end > nums.length.
     * @throws IllegalArgumentException       if start > end.
     */
    public BSTUtilities(int[] nums, int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("Starting index cannot be great"
                    + "er than ending index.");
        }

        if (start < 0 || end > nums.length) {
            throw new ArrayIndexOutOfBoundsException("Starting index cannot be"
                    + " negative and ending index cannot be greater than"
                    + " length of input array");
        }

        for (int i = start; i < end; i++) {
            this.add(nums[i]);
        }
    }

    /**
     * Adds the data item to the BST.
     * <p>
     * This method has been implemented for you.  No need to change it.
     * <p>
     * Duplicates are ignored.
     *
     * @param data Item to add to BST.
     */
    public void add(int data) {
        root = add(data, root);
    }

    /**
     * Private helper method to traverse the tree and place the new data in the
     * correct spot.
     * <p>
     * This method has been implemented for you.  No need to change it.
     *
     * @param data    the new data to be added
     * @param current the BSTNode in the tree currently under examination
     * @return the updated BSTNode for the current position in the tree
     */
    private BSTNode add(int data, BSTNode current) {
        if (current == null) {
            return new BSTNode(data);
        } else if (data < current.getData()) {
            current.setLeft(add(data, current.getLeft()));
        } else if (data > current.getData()) {
            current.setRight(add(data, current.getRight()));
        }
        return current;
    }

    // One last warning.
    // DO NOT CHANGE ANY OF THE METHOD HEADERS.
    // DO NOT ADD ANY METHODS, PUBLIC OR PRIVATE
    // Everything you will need is provided to you.

    /**
     * Adds all of the data in the tree.
     * <p>
     * This method can and should be done in one line only.
     *
     * @return sum of data in the tree
     */
    public int dataSum() {
        return dataSum(this.root);
    }

    /**
     * Adds all of the data in the subtree.
     * <p>
     * This is the helper method to be used for {@code dataSum()}.
     *
     * @param current The current node for this subtree.
     * @return sum of data in the subtree.
     */
    private int dataSum(BSTNode current) {
        int sum = 0;

        if (current == null) {
            return 0;
        }

        if (current.getLeft() != null) {
            sum += dataSum(current.getLeft());
        }

        if (current.getRight() != null) {
            sum += dataSum(current.getRight());
        }

        sum += current.getData();
        return sum;
    }

    /**
     * Calculates the number of elements in the tree.
     * <p>
     * This method can and should be done in one line only.
     *
     * @return size of the tree
     */
    public int size() {
        return size(this.root);
    }

    /**
     * The size of the subtree.
     * <p>
     * This is the helper method to be used for {@code size()}.
     *
     * @param current The current node you're looking at.
     * @return The size of this subtree.
     */
    private int size(BSTNode current) {
        int count = 0;

        if (current == null) {
            return 0;
        }

        if (current.getLeft() != null) {
            count += size(current.getLeft());
        }

        if (current.getRight() != null) {
            count += size(current.getRight());
        }

        count++;

        return count;
    }

    /**
     * Returns a number calculated from the data in the tree, according to a
     * formula defined in the helper method's Javadoc comment.
     * <p>
     * This method can and should be done in one line only.
     *
     * @return zigAdd value for this tree.
     */
    public int zigAdd() {
        return zigAdd(this.root);
    }

    /**
     * Returns a number calculated from the data for this subtree, according to
     * a formula.
     * <p>
     * This is the helper method to be used for {@code zigAdd()}.
     * <p>
     * The formula:
     * <p>
     * current node's data + zigAdd(left subtree) - zigAdd(right subtree)
     *
     * @param current The current node you're looking at.
     * @return zigAdd for this subtree
     */
    private int zigAdd(BSTNode current) {
        int sum = 0;
        if (current == null) {
            return 0;
        }

        sum = current.getData() + zigAdd(current.getLeft())
                - zigAdd(current.getRight());

        return sum;
    }

    /**
     * Multiplies the left and right subtrees, according to a formula defined
     * in the helper method's Javadoc comment.
     * <p>
     * This method can and should be done in one line only.
     *
     * @return The baseMultiply value of the tree.
     */
    public int baseMultiply() {
        return baseMultiply(this.root);
    }

    /**
     * Returns a number calculated from the data for this subtree, according
     * to a formula.
     * <p>
     * This is the helper method to be used for {@code baseMultiply()}.
     * <p>
     * The formula:
     * <p>
     * current node's data + 6 * baseMultiply(left subtree)
     * + 7 * baseMultiply(right subtree)
     *
     * @param current The current node you're looking at.
     * @return The baseMultiply value of this subtree.
     */
    private int baseMultiply(BSTNode current) {
        int sum = 0;
        if (current == null) {
            return 0;
        }

        sum += current.getData() + 6 * (baseMultiply(current.getLeft()))
                + 7 * (baseMultiply(current.getRight()));

        return sum;
    }

    /**
     * Calculate the number of items that are strictly greater than the data
     * passed in.
     * <p>
     * HINT: For this method to be efficient, remember that the data in a BST
     * is ordered in a certain way.  You may not have to traverse an entire
     * subtree.
     * <p>
     * This method can and should be done in one line only.
     *
     * @param data Count the number of items greater than this element.
     * @return number of items strictly greater than the data passed in.
     */
    public int thresholdCount(int data) {
        return thresholdCount(this.root, data);
    }

    /**
     * Calculate the number of items in this subtree that are strictly greater
     * than the data passed in.
     * <p>
     * This is the helper method to be used for
     * {@code thresholdCount(int data)}.
     *
     * @param current The current node you're looking at.
     * @param data    Count the number of items greater than this element.
     * @return number of items in this subtree greater than data.
     */
    private int thresholdCount(BSTNode current, int data) {
        int count = 0;

        if (current == null) {
            return 0;
        }

        if (current.getData() > data) {
            count++;
            count += thresholdCount(current.getLeft(), data)
                    + thresholdCount(current.getRight(), data);
        }

        if (current.getData() <= data) {
            count += thresholdCount(current.getRight(), data);
        }

        return count;

    }

    /**
     * Calculates the sum of the nodes only on even levels of the tree.
     * The root is considered to be level 0, (0 is even) and the "number" of
     * the level increases as you go farther down the tree.
     * <p>
     * For example, for a tree that looks like the following:
     * 8
     * / \
     * /   \
     * 6    10
     * / \    \
     * /   \    \
     * 4     7    15
     * <p>
     * This will return 8 + 4 + 7 + 15 = 34.
     * <p>
     * This method can and should be done in one line only.
     *
     * @return the sum of the data contained in the nodes only on even levels
     * of the tree.
     */
    public int sumEvenLevels() {
        return sumEvenLevels(this.root);
    }

    /**
     * Calculates the sum of the nodes only on even levels of the subtree.
     * <p>
     * This is the helper method to be used for {@code sumEvenLevels()}.
     *
     * @param current The current node you are looking at.
     * @return the sum of the nodes only on even levels of the subtree.
     */
    private int sumEvenLevels(BSTNode current) {
        int sum = 0;

        if (current == null) {
            return 0;
        }

        sum += current.getData();

        if (current.getLeft() != null) {
            sum += sumEvenLevels(current.getLeft().getLeft());
            sum += sumEvenLevels(current.getLeft().getRight());
        }

        if (current.getRight() != null) {
            sum += sumEvenLevels(current.getRight().getLeft());
            sum += sumEvenLevels(current.getRight().getRight());
        }

        return sum;
    }

    /**
     * Finds the smallest item in the tree and returns it.
     * This method can and should be done in five lines or less.
     *
     * @return smallest item in the tree. If the tree is empty,
     * return {@code null}.
     */
    public Integer getMin() {
        if (this.root == null) {
            return null;
        } else {
            return getMin(this.root);
        }
    }

    /**
     * Finds the smallest item in the tree and returns it.
     * <p>
     * This is the helper method to be used for {@code getMin()}.
     *
     * @param current The current node you're looking at.
     * @return smallest item in the tree. If the tree is empty,
     * return {@code null}.
     */
    private Integer getMin(BSTNode current) {
        if (current.getLeft() != null) {
            return getMin(current.getLeft());
        }

        return current.getData();

    }

    /**
     * Finds the largest item in the tree and returns it.
     * <p>
     * This method can and should be done in five lines or less.
     *
     * @return largest item in the tree. If the tree is empty,
     * return {@code null}.
     */
    public Integer getMax() {
        if (this.root == null) {
            return null;
        } else {
            return getMax(this.root);
        }
    }

    /**
     * Finds the largest item in the tree and returns it.
     * <p>
     * This is the helper method to be used for {@code getMax()}.
     *
     * @param current The current node you're looking at.
     * @return largest item in the tree. If the tree is empty,
     * return {@code null}.
     */
    private Integer getMax(BSTNode current) {
        if (current == null) {
            return null;
        }

        if (current.getRight() != null) {
            return getMax(current.getRight());
        }

        return current.getData();
    }

    /**
     * Calculates the height of the tree.
     * <p>
     * This method can and should be done in one line only.
     *
     * @return height of the tree
     */
    public int height() {
        return height(this.root);
    }

    /**
     * Calculates the height of the tree.
     * <p>
     * This is the helper method to be used for {@code height()}.
     * <p>
     * The height of a tree is defined as:
     * <p>
     * max(height(left subtree), height(right subtree)) + 1
     * <p>
     * The height of a {@code null} node is -1, and the height of a leaf
     * node is 0.
     *
     * @param current The current node you're looking at.
     * @return height of the tree
     */
    private int height(BSTNode current) {
        if (current == null) {
            return -1;
        }

        int leftH = height(current.getLeft());
        int rightH = height(current.getRight());

        if (leftH > rightH) {
            return leftH + 1;
        } else {
            return rightH + 1;
        }
    }

    /**
     * Returns a string representation of the tree. The string should be
     * formatted as follows:
     * [currentData, leftSubtree, rightSubtree]
     * <p>
     * For example, for a tree that looks like the following:
     * 8
     * / \
     * /   \
     * 6    10
     * / \    \
     * /   \    \
     * 4     7    15
     * <p>
     * The string should be:
     * [8, [6, [4, [], []], [7, [], []]], [10, [], [15, [], []]]
     * <p>
     * An empty tree should return an empty set of brackets, i.e. [].
     * <p>
     * This method can and should be done in one line only.
     *
     * @return String representation of the tree
     */
    public String toString() {
        return toString(this.root);
    }

    /**
     * Returns the string representation of this subtree, using the format
     * specified in the in the public method Javadoc comment.
     * <p>
     * This is the helper method to be used for {@code toString()}.
     *
     * @param current The current node you're looking at.
     * @return string representation of the subtree.
     */
    private String toString(BSTNode current) {
        String result = "";

        if (current == null) {
            return "[]";
        }

        result += "[" + current.getData() + ", " + toString(current.getLeft())
                + ", " + toString(current.getRight()) + "]";

        return result;
    }

    /**
     * DO NOT USE THIS METHOD.  For testing purposes only.
     *
     * @return The root of the tree.
     */
    public BSTNode getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}
