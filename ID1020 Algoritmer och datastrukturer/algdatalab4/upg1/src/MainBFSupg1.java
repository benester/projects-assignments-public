import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *This is the text class for the Breadth first path class
 * This will create the graph to do the path search on, and then print the path
 * @author Benjamin Gafvelin
 * code generated 2019-10-02
 */

public class MainBFSupg1
{

    public static void main(String[] args) throws FileNotFoundException
    {
        //The "database"
        Scanner scanner = new Scanner(new File("C:\\Users\\benja\\Desktop\\algdatalab4\\contiguous-usa.dat"));
        BinarySearchST<String, Integer> st = new BinarySearchST<>();


        int stateNumericValue = 0;

        Graph statesGraph = new Graph(49);

        while(scanner.hasNext())
        {
            String input1 = scanner.next();
            String input2 = scanner.next();

            if(!st.contains(input1))
            {
                st.put(input1, stateNumericValue++);
            }
            if(!st.contains(input2))
            {
                st.put(input2, stateNumericValue++);
            }

            statesGraph.addEdge(st.get(input1), st.get(input2));
        }

        String[] keys = new String[st.size()];
        for(String name : st.keys())
        {
            keys[st.get(name)] = name;
        }
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the states you would like to find a path between");

        BreadthFirstPath bfs = new BreadthFirstPath(statesGraph, st.get(userInput.next()));
        Stack<Integer> path = bfs.pathTo(st.get(userInput.next()));

        while(!path.isEmpty())
        {
            System.out.print(keys[path.pop()]);
            if(!path.isEmpty()){
                System.out.print(" -> ");
            }
        }
    }
}