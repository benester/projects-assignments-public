import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * This program takes the text as input, applies a hash on every distinct word, and then counts how many words
 * have the same hash.
 * All other classes in this assignment are borrowed from the princeton website.
 * genereated 2019-09-29
 * @auther Benjamin Gafvelin.
 */
public class MainUpg7
{
    public static void main(String[] args) throws FileNotFoundException
    {
        int amount = 97;
        int distinct = 0, words = 0;
        int minlen = 0;
        int array[] = new int[amount];
        BinarySearchSTUpg7<String, Integer> st = new BinarySearchSTUpg7<>();
        //BST<String, Integer> bst = new BST<>();
        Scanner scanner = new Scanner(new File("C:\\Users\\benja\\Desktop\\algdatalab3\\upg2\\src\\text.txt"));
        // compute frequency counts

        while(scanner.hasNext())
        {
            String key = scanner.next();
            if(key.length() < minlen) continue;
            words++;
            if(st.contains(key))
            {
                st.put(key, st.get(key) + 1);
            } else
            {
                st.put(key, 1);
                int hashkey = (Math.abs(key.hashCode())) % amount;
                array[hashkey]++;
                distinct++;
            }
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for(String word : st.keys())
        {
            if(st.get(word) > st.get(max))
                max = word;
        }



        System.out.println("max: " + max + " " + st.get(max));
        System.out.println("distinct = " + distinct);
        System.out.println("words    = " + words);
        Scanner scan = new Scanner(System.in);
        int MorethanOne = 0;
        int longest = array[0];
        int empty = 0;
        for(int i = 0; i < amount;i++){
            if(array[i] == 0){
                empty++;
            }
            if (array[i]>1){
                if(array[i]>longest){
                    longest = array[i];
                }
                MorethanOne++;
            }
        }
        System.out.println("The amount of indexes with more than one hash mapped to it: " + MorethanOne);
        System.out.println("The most amount of same mapped hashes is " + longest);
        System.out.println("The amount of empty hashes: " + empty);
        for(int i = 0; i < amount; i++){
            System.out.println(array[i]);
        }
    }
}
