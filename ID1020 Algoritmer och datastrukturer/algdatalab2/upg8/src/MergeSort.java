/**
 *
 */

public class MergeSort {
    private static Comparable aux[];

    private static void mergeSort(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = (mid + 1);

        for (int k = lo; k <= hi; k++){
            aux[k] = a[k];
        }


        for (int k = lo; k <= hi; k++) { // Merge back to a[lo..hi].
            if (i > mid){ a[k] = aux[j++];}
            else if (j > hi){ a[k] = aux[i++];}
            else if (less(aux[j], aux[i])){ a[k] = aux[j++];}
            else{ a[k] = aux[i++];}
        }
    }

    /**
     * Help function for the mergesort method, which creates the auxiallary array.
     *
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort1(a, 0, a.length - 1);
    }
    /**
     * Help function for the mergesort Algorithm
     * @param a  the array to be sorted
     * @param lo index low in the mergesort algorithm
     * @param hi the upper limit of the array
     */
    private static void sort1(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;

        int mid = lo + (hi - lo) / 2;
        sort1(a, lo, mid);
        sort1(a, mid + 1, hi);

        mergeSort(a, lo, mid, hi);
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

    public static void main(String[] args) {
        Comparable arr[] = new Comparable[100000];
        for(int i = 0; i < 100000; i++){
            arr[i] = (int)(Math.random()*((10000+10000)+1))-10000;
        }
        //print(arr);
        System.out.println("END OF ARRAY");
        sort(arr);
        //print(arr);
    }
}
