import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *  This program takes a text file as an input and calculates how many times every word in text occurs
 *  code generated 2019-09-27
 * @auther Benjamin Gafvelin
 * All code except the code referring to N:th order of hundred words in this file is taken from the princeton website.
 *The code about time is also written by me.
 */
public class FrequencyCounterUpg4
{
    // Do not instantiate.
    private FrequencyCounterUpg4() { }

    public static void main(String[] args) throws FileNotFoundException {
        int distinct = 0, words = 0;
        int minlen = 1;
        Scanner scan = new Scanner(System.in);
        //BinarySearchSTupg4<String, Integer> st = new BinarySearchSTupg4<>();
        BSTupg4<String, Integer> st = new BSTupg4<>();
        Scanner scanner = new Scanner(new File("C:\\Users\\benja\\Desktop\\algdatalab3\\upg2\\src\\text.txt"));
        // compute frequency counts
        System.out.println("Enter N amount of words to be counted.(N magnitude 100)");
        int wordsToRead = scan.nextInt()*100;
        int readwords = 0;

        long start = System.nanoTime();
        while (scanner.hasNext() && readwords < wordsToRead) {
            String key = scanner.next();


            if (key.length() < minlen) continue;
            words++;

            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
            }
            readwords++;
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }

        long end = System.nanoTime();
        long totaltime = end - start;
        double timems = totaltime / 1000000;

        System.out.println(timems);

        System.out.println("max: " + max + " " + st.get(max));
        System.out.println("distinct = " + distinct);
        System.out.println("words    = " + words);
    }
}