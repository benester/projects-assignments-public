/**
 * This class represents a edge weighted directed graph.
 * Code generated 2019-10-02
 * @author Benjamin Gafvelin
 * Code taken from the course book.
 */
public class EdgeWeightedDigraph
{
    private final int V;               // number of vertices
    private int E;                     // number of edges
    private Bag<DirectedEdge>[] adj;   // adjacency lists

    /**
     * The constructor for the class. Takes {@code V} as the amount of vertices to be added to the graph
     * @param V the amount of vertices to be added to the graph
     */
    public EdgeWeightedDigraph(int V)
    {
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for(int v = 0; v < V; v++) adj[v] = new Bag<DirectedEdge>();
    }

    /**
     * This method will return the amount of vertices in the edge weighted digraph
     * @return The amount of vertices in the digraph
     */
    public int V()
    {
        return V;
    }

    /**
     * Will return the amount of edges in the edge weighted digraph
     * @return The amount of edges in the digraph
     */
    public int E()
    {
        return E;
    }

    /**
     * Adds an Edge to digraph
     * @param e The edge to be added to the graph
     */
    public void addEdge(DirectedEdge e)
    {
        adj[e.from()].add(e);
        E++;
    }

    /**
     * This method will return a Iterable to be used to check all the vertices and edges adjacent to a
     * given vertex.
     * @param v The vertex to check adjacency's.
     * @return an Iterable with the adjacent vertices.
     */
    public Iterable<DirectedEdge> adj(int v)
    {
        return adj[v];
    }

    /**
     * This method will return all the edges in the Weighted directed graph as an {@code Iterable<DirectedEdge>}
     * @return All the edges in the graph as an Iterable.
     */
    public Iterable<DirectedEdge> edges()
    {
        Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
        for(int v = 0; v < V; v++) for(DirectedEdge e : adj[v]) bag.add(e);
        return bag;
    }
}
