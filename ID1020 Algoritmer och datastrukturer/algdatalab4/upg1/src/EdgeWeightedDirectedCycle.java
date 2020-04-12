/**
 * This code will find directed cycles in a edge-weighted digraph.
 * Code generated 2019-10-02
 * @author Benjamin Gafvelin
 * code taken from the course book
 */

public class EdgeWeightedDirectedCycle {
    private boolean[] marked;             // marked[v] = has vertex v been marked?
    private DirectedEdge[] edgeTo;        // edgeTo[v] = previous edge on path to v
    private boolean[] onStack;            // onStack[v] = is vertex on the stack?
    private Stack<DirectedEdge> cycle;    // directed cycle (or null if no such cycle)

    /**
     * This method will determine if a cycle exist in the edge-weighted digraph {@code G}
     * @param G The specified edge-weighted directed Graph
     */

    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
        marked  = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo  = new DirectedEdge[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }


    /**
     * Finds the cycles by using the depth first algorithm. As soon as it finds a marked vertex, it knows that
     * there's a cycle.
     * @param G The directed graph
     * @param v the destination vertex
     */
    private void dfs(EdgeWeightedDigraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();

            // short circuit if directed cycle found
            if (cycle != null) return;

                // found new vertex, so recur
            else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            }

            // trace back directed cycle
            else if (onStack[w]) {
                cycle = new Stack<DirectedEdge>();

                DirectedEdge f = e;
                while (f.from() != w) {
                    cycle.push(f);
                    f = edgeTo[f.from()];
                }
                cycle.push(f);

                return;
            }
        }
        onStack[v] = false;
    }

    /**
     * If there exist a cycle in the digraph, this method will return true.
     * @return true if a cycle exists, false if there isn't
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * WIll return a Iterable with the found cycle. (will be null if no cycle exist)
     * @return an Iterable with the found cycle.
     */
    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }
}