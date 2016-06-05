import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Nishant Roy
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     * <p>
     * It should be:
     * in-place
     * stable
     * <p>
     * Have a worst case running time of:
     * O(n^2)
     * <p>
     * And a best case running time of:
     * O(n)
     * <p>
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     * <p>
     * See the PDF for more info on this sort.
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is null
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array to sort or comparator "
                    + "cannot be null.");
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);

                }
            }
        }


    }

    /**
     * Implement insertion sort.
     * <p>
     * It should be:
     * in-place
     * stable
     * <p>
     * Have a worst case running time of:
     * O(n^2)
     * <p>
     * And a best case running time of:
     * O(n)
     * <p>
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     * <p>
     * See the PDF for more info on this sort.
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array to sort or comparator "
                    + "cannot be null.");
        }

        for (int i = 1; i < arr.length; i++) {
            T temp = arr[i];
            int j = i - 1;
            while (j > -1 && comparator.compare(temp, arr[j]) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    /**
     * Implement selection sort.
     * <p>
     * It should be:
     * in-place
     * <p>
     * Have a worst case running time of:
     * O(n^2)
     * <p>
     * And a best case running time of:
     * O(n^2)
     * <p>
     * Note that there may be duplicates in the array, but they may not
     * necessarily stay in the same relative order.
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is null
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array or comparator may not "
                    + "be null");
        }
        int first;
        for (int i = arr.length - 1; i > 0; i--) {
            first = 0;
            for (int j = 1; j <= i; j++) {
                if (comparator.compare(arr[j], arr[first]) > 0) {
                    first = j;
                }
            }
            T temp = arr[first];
            arr[first] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * Implement quick sort.
     * <p>
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     * <p>
     * int pivotIndex = r.nextInt(b - a) + a;
     * <p>
     * It should be:
     * in-place
     * <p>
     * Have a worst case running time of:
     * O(n^2)
     * <p>
     * And a best case running time of:
     * O(n log n)
     * <p>
     * Note that there may be duplicates in the array.
     * <p>
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @throws IllegalArgumentException if the array or comparator or rand is
     *                                  null
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("Array to sort, Random object,"
                    + " or comparator cannot be null.");
        }
        quickSort(arr, comparator, rand, 0, arr.length);


    }

    /**
     * Helper method for quicksort
     * @param arr Array to be sorted
     * @param comparator Comparator used for sorting
     * @param rand Random object to get pivot
     * @param start Starting index to sort from
     * @param end Last index to sort till
     * @param <T> Type of data to sort
     */
    private static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                      Random rand, int start, int end) {
        if (end - start >= 2) {
            int pivot = rand.nextInt(end - start) + start;
            swap(arr, start, pivot);
            int i = start + 1;
            int j = end - 1;
            while (i <= j) {
                if (comparator.compare(arr[i], arr[start]) <= 0) {
                    i++;
                } else {
                    if (comparator.compare(arr[j], arr[start]) > 0) {
                        j--;
                    } else {
                        swap(arr, i++, j--);
                    }
                }
            }
            swap(arr, start, j);
            if (start < j) {
                quickSort(arr, comparator, rand, start, j);
            }

            if (end > i) {
                quickSort(arr, comparator, rand, i, end);
            }
        }



    }

    /**
     * Helper method to swap array elements
     * @param arr Array to swap elements in
     * @param ind1 Index of first element to swap
     * @param ind2 Index of second element to swap
     * @param <T> Type of data in array
     */
    private static <T> void swap(T[] arr, int ind1, int ind2) {
        T temp = arr[ind1];
        arr[ind1] = arr[ind2];
        arr[ind2] = temp;
    }


    /**
     * Implement merge sort.
     * <p>
     * It should be:
     * stable
     * <p>
     * Have a worst case running time of:
     * O(n log n)
     * <p>
     * And a best case running time of:
     * O(n log n)
     * <p>
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     * <p>
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is null
     */
    @SuppressWarnings("unchecked")
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array or comparator may not "
                    + "be null");
        }

        T[] temp = (T[]) new Object[arr.length];
        mergeSort(arr, comparator, 0, arr.length - 1, temp);
    }

    /**
     * @param arr        Array to sort
     * @param comparator Comparator for sorting
     * @param low        lower index of sortable array
     * @param high       higher index of sortable array
     * @param <T>        type to sort
     * @param temp       temporary array to store elements in
     */

    private static <T> void mergeSort(T[] arr, Comparator<T> comparator,
                                      int low, int high, T[] temp) {

        if (low < high) {
            int middle = (low + high) / 2;
            mergeSort(arr, comparator, low, middle, temp);
            mergeSort(arr, comparator, middle + 1, high, temp);
            merge(arr, comparator, low, middle, high, temp);
        }
    }

    /**
     * @param arr        Array to sort
     * @param comparator Comparator for sorting
     * @param low        lower index of sortable array
     * @param middle     middle index of sortable array
     * @param high       higher index of sortable array
     * @param temp       temp array
     * @param <T>        type to sort
     */

    private static <T> void merge(T[] arr, Comparator<T> comparator,
                                  int low, int middle, int high, T[] temp) {
        int k = low;
        int putItBack = high - low + 1;
        int midUp = middle + 1;

        while (low <= middle && midUp <= high) {
            if (comparator.compare(arr[low], arr[midUp]) > 0) {
                temp[k] = arr[midUp];
                midUp++;
            } else {
                temp[k] = arr[low];
                low++;
            }
            k++;
        }

        while (low <= middle) {
            temp[k++] = arr[low++];
        }

        while (midUp <= high) {
            temp[k++] = arr[midUp++];
        }
        for (int i = 0; i < putItBack; i++, high--) {
            arr[high] = temp[high];
        }

    }

    /**
     * Implement LSD (least significant digit) radix sort.
     * <p>
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     * <p>
     * It should be:
     * stable
     * <p>
     * Have a worst case running time of:
     * O(kn)
     * <p>
     * And a best case running time of:
     * O(kn)
     * <p>
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     * <p>
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     * <p>
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @param arr the array to be sorted
     * @return the sorted array
     * @throws IllegalArgumentException if the array is null
     */
    public static int[] lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        int min = arr[0];
        for (int anArr : arr) {
            if (anArr < min) {
                min = anArr;
            }
        }
        int offset = min;
        if (offset < 0) {
            offset *= -1;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] += offset;
        }

        List<Integer>[] digits = new LinkedList[10];
        for (int i = 0; i < 10; i++) {
            digits[i] = new LinkedList<>();
        }
        boolean done = false;
        int divisor = 1;
        while (!done) {
            done = true;
            for (int number : arr) {
                int digit = (number / divisor) % 10;
                digits[digit].add(number);
                if (digit > 0) {
                    done = false;
                }
            }
            int i = 0;
            for (int j = 0; j < 10; j++) {
                for (int number : digits[j]) {
                    arr[i++] = number;
                }
                digits[j].clear();
            }
            divisor *= 10;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] -= offset;
        }
        return arr;
    }

    /**
     * Implement MSD (most significant digit) radix sort.
     * <p>
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     * <p>
     * It should:
     * <p>
     * Have a worst case running time of:
     * O(kn)
     * <p>
     * And a best case running time of:
     * O(kn)
     * <p>
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     * <p>
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @param arr the array to be sorted
     * @return the sorted array
     * @throws IllegalArgumentException if the array is null
     */
    public static int[] msdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        int power = 0;
        boolean doneSorting = false;
        int arrLen = arr.length;

        while (!doneSorting) {
            int[] list = new int[20];
            int[] list2 = new int[arrLen];
            int divisor = pow(10, power);
            int max = arr[arrLen - 1];
            for (int i = 0; i < arrLen; i++) {
                list[9 + ((arr[i] / divisor) % 10)]++;
                if (arr[i] > max) {
                    max = arr[i];
                }
            }
            if (list[0] >= arrLen) {
                doneSorting = true;
            }

            for (int i = 1; i < list.length; i++) {
                list[i] += list[i - 1];
            }
            for (int i = arrLen - 1; i >= 0; i--) {
                list2[--list[((arr[i] / divisor) % 10) + 9]] = arr[i];
            }

            arr = list2;
            if ((max / divisor) == 0) {
                doneSorting = (max / divisor) == 0;
            }
            
            ++power;
        }
        return arr;
    }

    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sorts instead of {@code Math.pow()}.
     * <p>
     * DO NOT MODIFY THIS METHOD.
     *
     * @param base base of the number
     * @param exp  power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power
     * @throws IllegalArgumentException if both {@code base} and {@code exp} are
     *                                  0
     * @throws IllegalArgumentException if {@code exp} is negative
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * pow(base, (exp / 2) + 1);
        }
    }
}
