import java.util.Set;

/**
 * Interface for a skip list.
 * 
 * DO NOT EDIT THIS CLASS!
 *
 * @version 1.0
 * @author CS 1332 TAs
 */
public interface SkipListInterface<T extends Comparable<? super T>> {

    /**
     * Finds and returns the first item in the skip list.
     * 
     * @throws java.util.NoSuchElementException if the skip list is empty
     * @return the first item in the skip list
     */
    public T first();

    /**
     * Finds and returns the last item in the skip list.
     * 
     * @throws java.util.NoSuchElementException if the skip list is empty
     * @return the last element in the skip list
     */
    public T last();

    /**
     * Adds a new item into the skip list.
     * 
     * There should always be an empty level at the top of the list.
     *
     * Duplicate items (items already in the skip list) will not be passed in.
     * 
     * @param data the item to put into the skip list
     * @throws IllegalArgumentException if the parameter is null
     */
    public void put(T data);


    /**
     * Removes an item from the skip list. Note that if you remove the only
     * item in a level, you should remove the entire level.
     * 
     * @param data an item that is equal to some other item in the skip list
     * @throws java.util.NoSuchElementException if the item is not in the skip
     * list.
     * @throws IllegalArgumentException if the parameter is null
     * @return the item removed if it was in the skip list. Do NOT just return
     * what was passed in.
     */
    public T remove(T data);

    /**
     * Searches the skip list for a given data.
     *
     * You may NOT use this in any method in this class.
     * 
     * @param data an item that is equal to some other item in the skip list
     * @throws IllegalArgumentException if the parameter is null
     * @return true if the data is in the skip list, false otherwise
     */
    public boolean contains(T data);

    /**
     * Finds and returns the data found in the skip list that is equal to the
     * data passed in.
     * 
     * @param data a item that is equal to some other item in the skip list
     * @throws IllegalArgumentException if the parameter is null
     * @throws java.util.NoSuchElementException if the item is not in skip list
     * @return the data in the skip list. Do NOT just return what was passed in.
     */
    public T get(T data);

    /**
     * Gives the size of the skip list.
     * 
     * @return the number of items in the skip list
     */
    public int size();

    /**
     * Clears the list. The size should be zero after this method is called.
     * Make sure that there is still an empty level in the skip list.
     */
    public void clear();

    /**
     * The data of each item of the skip list. If the list is empty, return an
     * empty set. You may use any implementation of Set.
     * 
     * @return a set of all the data in the skip list
     */
    public Set<T> dataSet();

    /**
     * Gets the head of the skip list. This is for grading purposes only.
     * DO NOT USE THIS METHOD!
     * 
     * @return the head of the skip list.
     */
    public SkipListNode<T> getHead();
}
