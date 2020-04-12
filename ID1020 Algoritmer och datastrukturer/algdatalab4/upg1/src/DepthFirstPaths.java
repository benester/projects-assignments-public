/**
 *
 * This code is an implementation of the depth first, path finder algorithm.
 * Code generated 2019-10-02
 * @author benjamin Gafvelin
 * Code is taken from the book.
 */
class DepthFirstPaths
{
    private boolean[] marked; // Has dfs() been called for this vertex?
    private int[] edgeTo;     // last vertex on known path to this vertex
    private final int s;      // source

    /**
     * The constructor for the Depth first path algorithm. Takes two parameters, the graph and the
     * starting vertex.
     * @param G The graph to be traversed
     * @param s The starting vertex
     */
    DepthFirstPaths(Graph G, int s)
    {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    /**
     * The depth first algorithm. It will go from node to node, and mark them as visited. It will not
     * go to the same node twice. If that is the only choice, it will backtrack and check other possible
     * paths.
     * @param G The graph to be traversed
     * @param v The vertex we are searching for.
     */
    private void dfs(Graph G, int v)
    {
        marked[v] = true;
        for(int w : G.adj(v))
            if(!marked[w])
            {
                edgeTo[w] = v;
                dfs(G, w);
            }
    }

    /**
     * Will return if there exist a path from the starting vertex to the {@code v} vertex
     * @param v the vertex to check if there exist a path to
     * @return true if a path exist to the vertex, false if not
     */
    public boolean hasPathTo(int v)
    {
        return marked[v];
    }

    /**
     * Will return a stack that details the path that the algorithm found to the given vertex.
     * @param v the vertex to find a path to.
     * @return the path to the given vertex
     */
    public Stack<Integer> pathTo(int v)
    {
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for(int x = v; x != s; x = edgeTo[x]) path.push(x);
        path.push(s);
        return path;
    }
}