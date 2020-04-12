import java.util.Iterator;
/**
 * This class is an implementation of a Bag, which means that items can be stored in the bag we cannot
 * remove anything from the bag, but we can look at what's inside.
 * @param <Item> Generics, Item, to be able to save objects of several types in the bag. ex. Graph and Digraph
 * Code Generated 2019-10-05
 * @author Benjamin Gafvelin
 * code taken from the oourse book.
 */
public class Bag<Item> implements Iterable<Item>
{
    private Node first;

    /**
     * Class node is an internal class that holds the information in the bag.
     */
    private class Node
    {
        Item item;
        Node next;
    }

    /**
     * Adds a Node to the bag that stores the Item item.
     * @param item the item to be saved in the bag
     */
    public void add(Item item)
    {
        Node lastFirst = first;
        first = new Node();
        first.item = item;
        first.next = lastFirst;
    }

    /**
     * A constructor for the iterator class, which returns an iterator.
     * @return An iterator
     */
    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }

    /**
     * Inner class ListIterator, is needed since the bag implements the iterable interface.
     * This class is used to iterate through the bag.
     */
    private class ListIterator implements Iterator<Item>
    {

        private Node current = first;

        /**
         * This method checks weather or not the bag has another node in it or not.
         * @return  True if it has another node, False if not
         */
        public boolean hasNext()
        {
            return current != null;
        }

        /**
         * This class iterates to the next item in the bag
         * @return The next Item in the bag
         */
        public Item next()
        {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
