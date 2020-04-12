/***
 * @author Benjamin Gafvelin
 *  Code generated 2019-09-09
 *  The Code is a list, where the user can add items to the list, then remove the k:th element by specifing this in user input
 *  Code based on code from previous asigments
 */

public class GeneralizedListUpg5 {
    private int size;
    private Node first;
    private Node last;

    public GeneralizedListUpg5(){

    }
    private class Node{
        int value;
        Node next;
        Node prev;
    }

    /**
     * Help method to print the list
     */
    void printQueue(){
        String queue = toString();
        System.out.println(queue);
    }

    /**
     * Adds and element to the list
     * @param item  The element that is to be added to the list
     */
    void enQueue(int item){
        Node oldLast = last;
        last = new Node();
        last.value = item;
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
     * Removes the k:th element from the list, counting from the most recently added element.
     * @param index the k:th number (index) to be removed
     */
    void deQueue(int index){
        if(!isEmpty()) {
            Node temp = last;
            if(size()==1){
                first = null;
                last = null;
                size = 0;
                return;

            }
            if (index < size() && index > 1) {
                for (int i = 1; i < index; i++) {
                    temp = temp.prev;
                }
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
                size--;
            } else if (index == size()) {
                first = first.next;
                size--;
            } else if (index == 1) {
                last = last.prev;
                size--;
            } else {
                System.out.println("Number specified does not fall between 1<=k<=size of list");
                return;
            }
            printQueue();
        }
    }

    /**
     * Checks if the list is empty
     * @return returns true if empty, false if not
     */
    boolean isEmpty(){
        return first ==null;
    }

    int size(){
        return this.size;
    }

    /**
     * Returns a formatted String of the content of the list, by iterating over it
     * @return a formatted string of the content of list
     */
    public String toString(){
        String string = "";
        Node temp = first;
        for(int i=0;i<size;i++){
            string += ("[" + temp.value + "]");
            if(i<size -1){
                string+=",";
            }
            temp = temp.next;
        }

        return string;
    }


    public static void main(String[] args) {
        GeneralizedListUpg5 list = new GeneralizedListUpg5();
        System.out.println("adding element 4 to list");
        list.enQueue(4);
        System.out.println("adding element 5 to list");
        list.enQueue(5);
        System.out.println("adding element 7 to list");
        list.enQueue(7);
        System.out.println("adding element 9 to list");
        list.enQueue(9);
        System.out.println("Attempt to remove element not in scope i.e remove the 8th recently added element");
        list.deQueue(8);

        System.out.println("Removing the 3rd most recently added element i.e 5");
        list.deQueue(3);
        System.out.println("5 should no longer be in list");
        System.out.println("Removing the second most recently added element i.e 7");
        list.deQueue(2);
        System.out.println("7 should no longer be in list");
        System.out.println("removing the most recently added item i.e 9");
        list.deQueue(1);
        System.out.println("9 should no longer be in queue");
        System.out.println("Removing the most recently added item, i.e 4");
        list.deQueue(1);
        System.out.println("4 should no longer be in queue");
        System.out.println("List should be empty, is list empty? " + list.isEmpty());
        System.out.println("Trying to remove element from empty list");
        list.deQueue(3);
    }
}
