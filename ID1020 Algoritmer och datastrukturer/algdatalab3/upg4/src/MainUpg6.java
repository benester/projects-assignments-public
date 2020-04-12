import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *This program takes the text as input and compares the time it takes to insert all the elements and then retreive the max value
 * Code generated 2019-09-29
 * @auther Benjamin Gafvelin
 */
public class MainUpg6
{

    public static void main(String[] args) throws FileNotFoundException
    {
        int distinct = 0, words = 0;
        int minlen = 0;
        BSTUpg6<String, Integer> st = new BSTUpg6<>();
        //RedBlackLiteBSTUpg6<String, Integer> st = new RedBlackLiteBSTUpg6<>();
        Scanner scanner = new Scanner(new File("C:\\Users\\benja\\Desktop\\algdatalab3\\upg2\\src\\text.txt"));
        // compute frequency counts


        long temp;
        long start;
        long timetotal = 0;
        while(scanner.hasNext())
        {
            String key = scanner.next();
            start = System.nanoTime();

            if(key.length() < minlen) continue;
            words++;
            if(st.contains(key))
            {
                st.put(key, st.get(key) + 1);
            } else
            {
                st.put(key, 1);
                distinct++;
            }
            temp = System.nanoTime();
            timetotal += temp - start;
        }
        start = System.nanoTime();
        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for(String word : st.keys())
        {
            if(st.get(word) > st.get(max))
                max = word;
        }
        temp = System.nanoTime();
        timetotal += temp - start;
        double time = (double) timetotal / 1000000;

        System.out.println("Time: " + time);


    }
}
