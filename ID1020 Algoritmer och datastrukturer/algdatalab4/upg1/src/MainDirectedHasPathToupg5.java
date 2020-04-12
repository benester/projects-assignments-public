import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class will figure out if there is a path between any two vertices in a given directed graph.
 * Code generated 2019-10-02.
 * @author Benjamin Gafvelin
 */
public class MainDirectedHasPathToupg5
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new File("C:\\Users\\benja\\Desktop\\algdatalab4\\contiguous-usa.dat"));
        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        int stateNumericValue = 0;

        EdgeWeightedDigraph states = new EdgeWeightedDigraph(107);
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
            //Add edges in both directions
            DirectedEdge edge = new DirectedEdge(st.get(input1),st.get(input2),1);
            //Add the edge to the graph
            states.addEdge(edge);
        }

        String[] keys = new String[st.size()];
        for(String name : st.keys())
        {
            keys[st.get(name)] = name;
        }


        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the states you would like to find if a path exist between");


        BellmanFordSP bfSP = new BellmanFordSP(states, st.get(userInput.next()));
        System.out.println("Has path -> "+bfSP.hasPathTo(st.get(userInput.next())));
    }
}