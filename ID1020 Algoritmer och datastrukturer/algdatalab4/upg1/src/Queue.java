import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A implementation of the generic data-structure, generic queue. the first to enter queue will be first
 * to leave queue.
 * @param <Item> the data type to be used in the instance of the Queue
 */
public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private int n;               // number of elements on queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * The constructor for the Queue class. Will remove all the references, if there are any and will
     * therefore be an empty Queue.
     */
    public Queue() {
        first = null;
        last  = null;
        n = 0;
    }

    /**
     * A method to determine if the queue is empty or not
     * @return true if empty, false if not empty
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * A get method that will return the size of the Queue
     * @return The size of the queue. I.e amount of elements in the queue.
     */
    public int size() {
        return n;
    }

    /**
     * A method used to add an element to the queue.
     * @param item, the item to add to the queue
     */
    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        n++;
    }

    /**
     * A method to remove the element that has been in the queue the longest.
     * @return The element that has been in the queue the longest.
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    /**
     * A method used to print the content of the queue in a formatted way.
     * @return a formatted string of the content of the queue.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */

    /**
     * An Iterator will be returned that can iterate over all the elements in the queue, in FIFO order.
     * @return The iterator that can iterate over all the elements in the queue.
     */
    public Iterator<Item> iterator()  {
        return new ListIterator(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}