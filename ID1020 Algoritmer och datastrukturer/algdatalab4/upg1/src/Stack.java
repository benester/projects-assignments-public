import java.util.Iterator;

/**
 * A data-structure of the type generic stack. It can push items to the stack, and pop the latest added element
 * @param <Item> The type of stack that this instance will handle.
 * @author Benjamin Gafvelin
 * Code taken from the book
 */
public class Stack<Item> implements Iterable<Item>
{
    private Node first; // top of stack (most recently added node)
    private int N;      // number of items

    /**
     * a helper class for the stack. A node will point to the next node in the stack, if no next, null
     */
    private class Node
    {  // nested class to define nodes
        Item item;
        Node next;
    }

    /**
     * A get function that return true if the stack is empty, else true
     * @return true if the stack is empty, false if it is not empty
     */
    public boolean isEmpty()
    {
        return first == null;
    }

    /**
     * A get function that will return the size of the stack.
     * @return the size of the stack
     */
    public int size()
    {
        return N;
    }

    /**
     * Add an element of the type Item to the stack.
     * @param item the element(item) to be added to the stack
     */
    public void push(Item item)
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    /**
     * A method to retrieve the most recently added element from the stack.
     * @return the most recently added element from the stack.
     */
    public Item pop()
    {
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    /**
     * Returns an Iterator that can iterate over all the elements in the stack.
     * @return The iterator that can iterate over all the elements in the stack.
     */
    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }

    /**
     * This class is used to Iterate over the Stack
     */
    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;

        /**
         * Will return true if the stack has one more element in it
         * @return true if there is another element, false otherwise
         */
        public boolean hasNext()
        {
            return current != null;
        }

        /**
         * Iterates to the next Item
         * @return the next Item.
         */
        public Item next()
        {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
