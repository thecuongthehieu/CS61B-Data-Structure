/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */

import edu.princeton.cs.algs4.Queue;

import java.util.ArrayList;

public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    private static final int RADIX_VALUE = 256;

    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        String[] sameLengthArr = new String[asciis.length];
        int maxLength = Integer.MIN_VALUE;

        for (String str : asciis) {
            maxLength = maxLength > str.length() ? maxLength : str.length();
        }

        for (int i = 0; i < asciis.length; ++i) {
            sameLengthArr[i] = asciis[i];

            int len = asciis[i].length();
            for (int k = 1; k <= (maxLength - len); ++k) {
                sameLengthArr[i] += (char)0;
            }

        }

        for (int id = maxLength - 1; id >= 0; --id) {
            sortHelperLSD(sameLengthArr, id);
        }

        return sameLengthArr;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        ArrayList<Queue<String>> queueList = new ArrayList<>();
        for (int i = 1; i <= RADIX_VALUE; ++i) {
            queueList.add(new Queue<>());
        }

        for (String str : asciis) {
            int bucketNum = (int) str.charAt(index);
            queueList.get(bucketNum).enqueue(str);
        }

        int i = 0;
        for (Queue<String> queue : queueList) {
            while (!queue.isEmpty()) {
                asciis[i] = queue.dequeue();
                ++i;
            }
        }
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] arr = new String[5];

        arr[3] = new String("bcd");
        arr[4] = new String("d");
        arr[0] = new String("a");
        arr[2] = new String("b");
        arr[1] = new String("ab");


        String[] sorted = sort(arr);

        for (String st : sorted) {
            System.out.println(st);
        }
    }
}
