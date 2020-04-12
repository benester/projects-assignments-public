/**
 * This Class is an implementation of the Breadth first, shortest path algorithm. It will go through a
 * Graph and find the shortest path from a given starting vertex to another vertex
 * Code generated 2019-10-02
 * @author Benjamin Gafvelin
 * Code is taken from the oourse book.
 */
class BreadthFirstPath
{
    private boolean[] marked; // Marks if the shortest path to this vertex is found or not.
    private int[] edgeTo;     // The last know step in the path to this vertex
    private final int s;      // The starting vertex

    /**
     * This is the constructor for the breadth first path class which initializes with a Graph and a
     * starting point in the graph.
     * @param G The graph to be searched.
     * @param s The starting vertex.
     */
    BreadthFirstPath(Graph G, int s)
    {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    /**
     * The Breadth first algorithm. Where it will enqueue the nodes to explore, find a shortest path
     * and then mark the vertices as found i.e shortest path.
     * @param G The graph to traverse
     * @param s The starting vertex
     */
    private void bfs(Graph G, int s)
    {
        Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true;               // Mark the starting vertex
        queue.enqueue(s);               //   enqueue the starting vertex
        while(!queue.isEmpty())
        {
            int v = queue.dequeue();   // Dequeue the next vertex from the queue
            for(int w : G.adj(v))       //For every unmarked vertex that is adjacent
                if(!marked[w])
                {
                    edgeTo[w] = v;     //   store the last edge on a shortest path
                    marked[w] = true;  //   mark the edge because a path is now known
                    queue.enqueue(w);  //   add the vertex to the queue.
                }
        }
    }

    /**
     * If there is a path to a given vertex this method will return {@code true} otherwise it will return
     * {@code false}.
     * @param v The vertex to check if there is a path to
     * @return Is there a path to the given vertex? True if yes False if not.
     */
    public boolean hasPathTo(int v)
    {
        return marked[v];
    }

    /**
     * This method will return a stack that details the path to a given vertex. If no path exist, it will
     * return null
     * @param v The vertex to which a path is to be returned
     * @return The path to the vertex {@code v} or if a path does not exist, return null
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
