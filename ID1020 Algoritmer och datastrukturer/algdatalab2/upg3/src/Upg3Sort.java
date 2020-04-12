import java.util.Scanner;

/**
 * @auther Benjamin Gafvelin
 * Code was generated: 2019-09-15
 * This code functions as a sorting algorithm, where the user specifies N elements to input, which then gets sorted
 * The code takes two inputs, firstly the amount of elements to be sorted, then the elements. It then prints the sorted array, and the amount of swaps
 * Code is identical to assignment 1, with the exception for the code that tracks the amount of swaps the algorithm performs while sorting
 */

public class Upg3Sort {

    static int nrOfSwaps = 0;

    /**
     * The insertion sort Algorithm is used in this method to sort the elements in the array
     * @param elements, The elements to be sorted.
     */
    public static void sort(Comparable[] elements){
        int length = elements.length;
        for(int i = 1; i<length;i++){
            for(int j = i; j > 0 && less(elements[j],elements[j-1]);j--) {
                exch(elements, j, j-1);
                nrOfSwaps++;
            }
            print(elements);
        }
    }

    /**
     * Simple function which compares two values, and returns true if a is less than b
     * @param a an element to be compared
     * @param b an element to be compared
     * @return returns true if a is less than b, else false
     */
    private static boolean less (Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    /**
     * Prints out the array
     * @param a the array to be printed
     */
    private static void print(Comparable[] a){
        for(int i = 0; i < a.length; i++){
            System.out.print("[" + a[i] + "] ");
        }
        System.out.println();
    }

    /**
     * This method checks if the array is sorted, by iterating over it
     * @return a boolean value, true if the array is sorted, false if not
     */
    public static boolean isSorted(Comparable[] a){
        for(int i = 1; i<a.length;i++){
            if(less(a[i],a[i-1]))
                return false;
        }
        return true;
    }

    /**
     * A function that changes two elements positions with each other
     * @param a the array where the two values are to be swapped
     * @param i the index of the first element in the array
     * @param j the index of the second element in the arrays
     */
    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static int getNrOfSwaps(){
        return nrOfSwaps;
    }

    public static void main (String[]args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the emount of elements to be sorted");
        int n = scanner.nextInt();
        Comparable[] a = new Comparable[n];
        System.out.println("Enter " + n + " integers");
        for(int i = 0; i<n;i++){
            a[i] = scanner.nextInt();
        }
        System.out.println("Your elements will now be sorted");

        sort(a);
        System.out.println("Your elements should be sorted, sorted? " + isSorted(a));
        System.out.println("Number of swaps: " + getNrOfSwaps());
        nrOfSwaps = 0;

        System.out.println("\nUNIT-TEST");
        Comparable[] b = new Comparable[6];
        b[0] = 1; b[1]=2; b[2]=4; b[3]=3; b[4]=5; b[5]=0;
        sort(b);
        System.out.println("It should now be sorted");
        System.out.println("Number of Swaps: "+getNrOfSwaps());
    }
}
