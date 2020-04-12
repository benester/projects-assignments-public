/**
 * This code is a representation of a directed edge. It will go from a vertex to another vertex in
 * one direction only.
 * Code generated 2019-10-02
 * Code taken from course book.
 * @author Benjamin Gafvelin
 */
public class DirectedEdge
{
    private final int v;        //Edge source
    private final int w;        //Edge target
    private final double weight;    //Weight between "nodes"

    /**
     * Constructor to create a Directed edge, from one vertex to another with a given weight
     * @param v The first vertex, i.e the start
     * @param w the other vertex, i.e the destination
     * @param weight the weight of the edge.
     */
    public DirectedEdge(int v, int w, double weight)
    {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }


    /**
     * A get method that return the weight of the edge.
     * @return the weight of the edge
     */
    public double weight()
    {
        return weight;
    }

    /**
     * A get method, that returns the start vertex.
     * @return the "source" vertex
     */
    public int from()
    {
        return v;
    }

    /**
     * A get method that return the destination vertex
     * @return the "target" vertex
     */
    public int to()
    {
        return w;
    }

    /**
     * A method to override javas built in toString function for objects. In this case it will
     * format the string like this, (vertex1 -> vertex2 weight)
     * @return A formatted String of the edge.
     */
    @Override
    public String toString()
    {
        return String.format("%d->%d %.2f", v, w, weight);
    }
}
