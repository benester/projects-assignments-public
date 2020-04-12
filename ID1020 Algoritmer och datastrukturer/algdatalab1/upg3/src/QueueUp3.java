/***
 * @author Benjamin Gafvelin
 *  Code generated 2019-09-09
 *  The program takes an item as an input an ads it to a queue(enqueue) and removes the least most recently added element
 * @see ItemUpg3 for Item used in this assigment
 *  code loosly based on code from the book on FIFO queue
 */

public class QueueUp3 {
    private int size;
    private Node first;
    private Node last;

    public QueueUp3(){

    }
    private class Node{
        ItemUpg3 itemUpg3;
        Node next;
        Node prev;
    }

    /**
     * Help function for printing the queue
     */
    void printQueue(){
        String queue = toString();
        System.out.println(queue);
    }

    /**
     * Adds an element to the queue, by changing the last element of the queue
     * @param itemUpg3 The elemet to be added to the queue
     */
    void enQueue(ItemUpg3 itemUpg3){
        Node oldLast = last;
        last = new Node();
        last.itemUpg3 = itemUpg3;
        last.next = null;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.next = last;
            last.prev = oldLast;
        }

        size++;
        printQueue();
    }

    /**
     * Removes an element from the queue, at the first position
     * @return
     */
    ItemUpg3 deQueue(){
        if(size == 1){
            ItemUpg3 itemUpg3 = first.itemUpg3;
            first = null;
            last = null;
            return itemUpg3;
        }
        if(size==0){
            return null;
        }

        ItemUpg3 itemUpg3 = first.itemUpg3;
        Node temp = first.next;
        temp.prev = null;
        first = first.next;
        if (isEmpty())
            last = null;
        size--;
        printQueue();
        return itemUpg3;
    }

    /**
     * Boolean on if the queue is empty or not
     * @return
     */
    boolean isEmpty(){
        return first ==null;
    }

    /**
     * Returns the number of elements in the queue
     * @return
     */
    int size(){
        return this.size;
    }

    /**
     * A toString function for printing the queue in a formated way
     * @return the formated queue
     */
    public String toString(){
        String string = "";
        Node temp = first;
        for(int i=0;i<size;i++){
            string += ("[" + temp.itemUpg3.toString() + "]");
            if(i<size -1){
                string+=",";
            }
            temp = temp.next;
        }
        return string;
    }

    /**
     * The tests
     * @param args
     */
    public static void main(String[] args){
        QueueUp3 queueUp3 = new QueueUp3();
        ItemUpg3 itemUpg31 = new ItemUpg3("Hasse", 32);
        ItemUpg3 itemUpg32 = new ItemUpg3("Lasse", 40);
        ItemUpg3 itemUpg33 = new ItemUpg3("Bosse", 12);
        ItemUpg3 itemUpg34 = new ItemUpg3("Nisse", 7);
        System.out.println("Item1, (hasse, 32)");
        System.out.println("Item2, (Lasse, 40)");
        System.out.println("Item3, (Bosse, 12)");
        System.out.println("Item4, (Nisse, 7)");
        System.out.println("Adding Hasse to queue");
        queueUp3.enQueue(itemUpg31);
        System.out.println("Adding Lasse to queue");
        queueUp3.enQueue(itemUpg32);
        System.out.println("Adding Bosse to queue");
        queueUp3.enQueue(itemUpg33);
        System.out.println("Adding Nisse to queue");
        queueUp3.enQueue(itemUpg34);
        System.out.println("Queue should not be empty, is empty? "+ queueUp3.isEmpty());
        System.out.println("When removing from queue, Hasse should exit the queue removed element: "+ queueUp3.deQueue().toString());
        System.out.println("Hasse should no longer be in the queue");
        System.out.println("When removing from queue, Lasse should exit the queue, removed element: " + queueUp3.deQueue().toString());
        System.out.println("Lasse should no longer be in the queue");
        System.out.println("When removing from queue, Bosse should exit the queue, removed element: "+ queueUp3.deQueue().toString());
        System.out.println("Bosse should no longer be in the queue");
        System.out.println("When removing from queue, Nisse should exit the queue removed element: "+ queueUp3.deQueue().toString()+"\n");
        System.out.println("Nisse should no longer be in the queue and queueUp3 should be empty\n");
        System.out.println("Is the queue empty? " + queueUp3.isEmpty());
    }
}
