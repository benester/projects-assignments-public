/**
 * This is an implementation of the Bellman Ford algorithm to find the shortest path in a given
 * weighted directed Graph from a given source vertex to another vertex.
 * @author Benjamin Gafvelin
 * code generated 2019-10-02
 * Code is taken from the course book
 */
public class BellmanFordSP {
    private double[] distTo;               // distTo[v] = distance  of shortest s->v path
    private DirectedEdge[] edgeTo;         // edgeTo[v] = last edge on shortest s->v path
    private boolean[] onQueue;             // onQueue[v] = is v currently on the queue?
    private Queue<Integer> queue;          // queue of vertices to relax
    private int cost;                      // number of calls to relax()
    private Iterable<DirectedEdge> cycle;  // negative cycle (or null if no such cycle)

    /**
     *  The bellmanFord Algorithm. IT will calculate a shortest path tree from the "source" {@code s}
     *  to every other vertex in the graph {@code G}
     * @param G The acyclic edge-weighted digraph.
     * @param s The first vertex to find all the paths from.
     * @throws IllegalArgumentException if s is not in the range (0<= s < V)
     */
    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        distTo  = new double[G.V()];
        edgeTo  = new DirectedEdge[G.V()];
        onQueue = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // Bellman-Ford algorithm:
        queue = new Queue<Integer>();
        queue.enqueue(s);
        onQueue[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQueue[v] = false;
            relax(G, v);
        }
    }

    // relax vertex v and put other endpoints on queue if changed
    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQueue[w]) {
                    queue.enqueue(w);
                    onQueue[w] = true;
                }
            }
            if (++cost % G.V() == 0) {
                findNegativeCycle();
                if (hasNegativeCycle()) return;  // found a negative cycle
            }
        }
    }
    /**
     * This mehtod will return true if there is a negative cycle that is reachable from the starting vertex s
     * if there is not a negative cycle reachable it will return false
     * @return true if negative cycle is reachable, false otherwise
     */
    public boolean hasNegativeCycle() {
        return cycle != null;
    }
    /**
     * Will try to find a negative cycle that is reachable from the source vertex s, and if one is found
     * it will be saved in the Iterable variable cycle.
     */
    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++)
            if (edgeTo[v] != null)
                spt.addEdge(edgeTo[v]);

        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
        cycle = finder.cycle();
    }

    /**
     * This method will return true if there is a path to the specified vertex {@code v} form the source vertex
     * @param v the destination vertex
     * @return true if there is a path, false otherwise
     */
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * This method will return the shortest path between the source vertex and the specified vertex {@code v}
     * @param v the destination vertex.
     * @return an iterable, that contains the path between source vertex and destination vertex
     *
     * @throws UnsupportedOperationException If a negative cost cycle has been found, and is reachable.
     */

    public Iterable<DirectedEdge> pathTo(int v) {
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
}