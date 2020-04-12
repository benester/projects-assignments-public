import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainUpg5
{
    public static void main(String[] args) throws FileNotFoundException
    {
        int distinct = 0, words = 0;
        int minlen = 0;
        BinarySearchSTUpg5<String, Integer> st = new BinarySearchSTUpg5<>();
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


        Scanner input = new Scanner(System.in);
        System.out.println("Input n and x");
        int n = input.nextInt()-1;
        int x = input.nextInt();

        Object keys[] = st.getNthToNxThkeys(n,x);

        for(int i = n; i<=(n+x);i++){
            System.out.println(keys[i]);
        }
    }
}
