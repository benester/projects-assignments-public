import java.util.Random;

/**
 * @Auther Benjamin Gafvelin
 * All code in this .java file is copied directly from the course book.
 */
public class QuickSort {
    static Random random = new Random(System.currentTimeMillis());

    /**
     * Shuffle the input before sorting and then sends the array forward to the sorting algorithm
     *
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }


    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);  // Partition (see page 291).
        sort(a, lo, j - 1);              // Sort left part a[lo .. j-1].
        sort(a, j + 1, hi);              // Sort right part a[j+1 .. hi].
    }

    private static int partition(Comparable[] a, int lo, int hi) {

        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);       // Put v = a[j] into position
        return j;
    }

    /**
     * Code from book, to exchange positions in the array, from index i to j and vice versa.
     *
     * @param a the array where the swap is to take place
     * @param i the first index to be swapped
     * @param j the other index to be swapped
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
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
     * Code from book to shuffle the array before sorting it.
     *
     * @param a the array to be shuffled.
     */
    public static void shuffle(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {  // Exchange a[i] with random element in a[i..N-1]
            int r = i + uniform(N - i);
            Comparable temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    /**
     * Code from book, returns random value between 0 and N
     *
     * @param N The maximum value to be generated here.
     * @return
     */
    public static int uniform(int N) {
        return (random.nextInt(N));
    }

    /**
     * Testing QuickSort
     *
     * @param args
     */
    public static void main(String[] args) {

        Comparable arr[] = new Comparable[10000];
        for (int i = 0; i < 10000; i++) {
            arr[i] = (int) (Math.random() * ((10000 + 10000) + 1)) - 10000;
            //Random rand = new Random();
            //rand.nextInt();
        }
        //print(arr);
        System.out.println("END OF ARRAY");
        sort(arr);
        print(arr);
    }
}
