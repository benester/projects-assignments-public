import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class will find the shortest path in a weighted undirected graph.
 * Code generated 2019-10-02
 * @author Benjamin Gafvelin
 */
public class MainWeightedGraphupg3
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new File("C:\\Users\\benja\\Desktop\\algdatalab4\\contiguous-usa.dat"));
        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        int stateNumericValue = 0;

        int weight = 1;

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
            DirectedEdge edge = new DirectedEdge(st.get(input1),st.get(input2),weight);
            DirectedEdge edgeOtherWay = new DirectedEdge(st.get(input2),st.get(input1),weight++);
            //Add the edges to the graph
            states.addEdge(edge);
            states.addEdge(edgeOtherWay);
        }

        String[] keys = new String[st.size()];
        for(String name : st.keys())
        {
            keys[st.get(name)] = name;
        }


        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the states you would like to find the shortest path between");


        BellmanFordSP bfSP = new BellmanFordSP(states, st.get(userInput.next()));
        Iterable<DirectedEdge> path = bfSP.pathTo(st.get(userInput.next()));

        int weightTotal = 0;
        for(DirectedEdge h : path)
        {
            weightTotal += h.weight();
            System.out.println(keys[h.from()] + " -> " + keys[h.to()] + " Weight: " + h.weight() + " Total Weight: " + weightTotal);
        }
    }
}