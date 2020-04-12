/**
 * This class represents a Graph. It has vertices and edges connecting them.
 * Code generated 2019-10-02
 * @author Benjamin Gafvelin
 * Code taken from the course book
 */
public class Graph
{
    private final int V;          // number of vertices
    private int E;                // number of edges
    private Bag<Integer>[] adj;   // adjacency lists

    /**
     * Constructor for the Graph class. Initializes the graph with a max size of {@code V}
     * @param V The amount of Vertices to be added to the graph
     */
    Graph(int V)
    {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for(int v = 0; v < V; v++)
            adj[v] = new Bag<Integer>();         //   to empty.
    }

    /**
     * This method will return the amount of vertices in the graph.
     * @return The amount of vertices in the graph
     */
    public int V()
    {
        return V;
    }

    /**
     * This method will return the amount of Edges in graph
     * @return The amount of Edges in the graph
     */
    public int E()
    {
        return E;
    }

    /**
     * This method will add an edge to the graph, that connects Vertices {@code v} and {@code w}
     * @param v One of the vertices that is connected by the edge
     * @param w The other of the vertices that is connected by the Edge
     */
    public void addEdge(int v, int w)
    {
        adj[v].add(w);                      // Add w to v’s list.
        adj[w].add(v);                     // Add v to w’s list.
        E++;
    }

/**
 * This method will return a Iterable to be used to check all the vertices and edges adjacent to a
 * given vertex.
 * @param v The vertex to check adjacency's.
 * @return an Iterable with the adjacent vertices.
 **/
    public Iterable<Integer> adj(int v)
    {
        return adj[v];
    }
}
