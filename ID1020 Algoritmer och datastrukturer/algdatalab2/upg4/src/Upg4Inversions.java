import java.util.Scanner;

/**
 * @auther Benjamin Gafvelin
 * Code was generated: 2019-09-15
 * Code was updated: 2019-09-20
 * This code functions as a sorting algorithm, where the user specifies N elements to input, which then gets sorted
 * The code takes two inputs, firstly the amount of elements to be sorted, then the elements
 * Code is almost identical to assignment 3 except for the method that counts the number of inversions
 */
public class Upg4Inversions {
    static int nrOfSwaps = 0;

    /**
     * The insertion sort Algorithm is used in this method to sort the elements in the array
     *
     * @param elements, The elements to be sorted.
     */
    public static void sort(Comparable[] elements) {
        int length = elements.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0 && less(elements[j], elements[j - 1]); j--) {
                exch(elements, j, j - 1);
                nrOfSwaps++;
            }
            print(elements);
        }
    }

    /**
     * Simple function which compares two values, and returns true if a is less than b
     *
     * @param a an element to be compared
     * @param b an element to be compared
     * @return returns true if a is less than b, else false
     */
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    /**
     * Prints out the array
     *
     * @param a the array to be printed
     */
    private static void print(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print("[" + a[i] + "] ");
        }
        System.out.println();
    }

    /**
     * This method checks if the array is sorted, by iterating over it
     *
     * @return a boolean value, true if the array is sorted, false if not
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }

    /**
     * This method counts the numbers of inversions in a given array
     * @param a the array where you are supposed to count inversions
     * @return  the number of inversions in the array
     */
    private static int countInversions(Comparable[] a) {
        int inv = 0;
        int compare = 0;
        for (int i = 0; i < a.length; i++)
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[i])) {
                    System.out.println("[" + i + " [" + a[i] + "] " + j + " [" + a[j] + "]]");
                    inv++;
                }
            }
        System.out.println("Number compare: " + compare);
        return inv;

    }

    /**
     * A function that changes two elements positions with each other
     *
     * @param a the array where the two values are to be swapped
     * @param i the index of the first element in the array
     * @param j the index of the second element in the arrays
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the emount of elements to be sorted");
        int n = scanner.nextInt();
        Comparable[] a = new Comparable[n];
        System.out.println("Enter " + n + " integers");
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println("Inversions: ");

        System.out.println("Number of inversions: "+ countInversions(a));
        System.out.println("Your elements will now be sorted");

        sort(a);
        System.out.println("Your elements should be sorted, sorted? " + isSorted(a));
        nrOfSwaps = 0;

        System.out.println("\n---UNIT-TEST---");
        System.out.println("Inversions: ");
        Comparable[] b = new Comparable[6];
        b[0] = 1;
        b[1] = 2;
        b[2] = 4;
        b[3] = 3;
        b[4] = 5;
        b[5] = 0;
        System.out.println("Number of inversions: " + countInversions(b));
        System.out.println("Sorting:");
        sort(b);
        System.out.println("It should now be sorted");
        System.out.println("Number of swaps: " + nrOfSwaps);
        System.out.println("Number of swaps = number of inversions before sorting");
    }
}
