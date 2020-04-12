import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TimeMSvsQS {

    /**
     * Creates a randomized array to be sorted
     * @param size size of the randomized array
     * @return the randomized array with the size @param size
     */
    public static Comparable[] createArray(int size) {
        Random rand = new Random(System.nanoTime());
        Comparable[] b = new Comparable[size];
        for (int i = 0; i < size; i++) {
            b[i] = rand.nextInt();
        }
        return b;
    }

    /**
     * Testing done here
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("How many elements are to be sorted?");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        System.out.println("How many loops should the avg be calculated by?");
        int loops = scanner.nextInt();
        double quicksortavgms = 0;
        double mergesortavgms = 0;
        double procent = 0;
        double add = (100/loops)/2;
        for (int k = 0; k < loops; k++) {
            System.out.print("\r Loading results " + procent + "% done");
            procent+=add;

            Comparable[] b = createArray(size);
            Comparable[] c = new Comparable[size];
            for (int i = 0; i < size; i++) {
                c[i] = b[i];
            }

            long start = System.nanoTime();
            QuickSort.sort(b);
            long end = System.nanoTime();
            long totalQS1 = end - start;
            double totalQS2 = (double) (TimeUnit.MILLISECONDS.convert(totalQS1, TimeUnit.NANOSECONDS));
            double totalQS3 = totalQS2 / 1000;
            //System.out.println("Quick sort " + size + " Elements \n" + "Sort took " + totalQS1 + " ns\n" + totalQS2 + " ms\n" + totalQS3 + " s\n\n");
            quicksortavgms += totalQS2;

            System.out.print("\r Loading results " + procent + "% done");
            procent+=add;

            start = System.nanoTime();
            MergeSort.sort(c);
            end = System.nanoTime();
            long totalMS1 = end - start;
            double totalMS2 = (double) TimeUnit.MILLISECONDS.convert(totalMS1, TimeUnit.NANOSECONDS);
            double totalMS3 = totalMS2 / 1000;
            //System.out.println("Merge sort " + size + " Elements \n" + "Sort took " + totalMS1 + " ns\n" + totalMS2 + " ms\n" + totalMS3 + " s\n\n");
            mergesortavgms += totalMS2;
        }
        System.out.println("\r Loading results " + procent+ "% done");
        System.out.println("\nQuicksort avg ms = " + quicksortavgms/loops);
        System.out.println("MergeSort avg ms = "+ mergesortavgms/loops);
    }
}
