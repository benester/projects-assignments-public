import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is the test class for the Depth first path class, and will create a graph to use the search on
 * and then print the found path.
 * @author Benjamin Gafvelin
 * code generated 2019-10-02
 */
public class MainDFSupg2
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new File("C:\\Users\\benja\\Desktop\\algdatalab4\\contiguous-usa.dat"));
        BinarySearchST<String, Integer> st = new BinarySearchST<>();


        int stateNumericValue = 0;
        Graph statesGraph = new Graph(49);

        while(scanner.hasNext()){

            String input1 = scanner.next();


            String input2 = scanner.next();
            if(!st.contains(input1)){
                st.put(input1, stateNumericValue++);
            }
            if(!st.contains(input2)){
                st.put(input2, stateNumericValue++);
            }

            statesGraph.addEdge(st.get(input1),st.get(input2));
        }

        String [] keys = new String[st.size()];
        for(String name : st.keys()){
            keys[st.get(name)]=name;
        }

        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the states you would like to find a path between");
        DepthFirstPaths dfs = new DepthFirstPaths(statesGraph , st.get(userInput.next()));
        Stack <Integer> path = dfs.pathTo(st.get(userInput.next()));

        while(!path.isEmpty()){
            System.out.print(keys[path.pop()]);
            if(!path.isEmpty()){
                System.out.print(" -> ");
            }
        }
    }
}