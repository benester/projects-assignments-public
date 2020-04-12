/***
 * @author Benjamin Gafvelin
 *  Code generated 2019-09-09
 *  The code takes an intiger as input, and sorts it into a queue in acending order
 *  Code based on previous asigments and preplab
 */

public class OrderdSortedQueueUpg6 {
    private int size = 0;
    private Node first;
    private Node last;

    private class Node{
        int value;
        Node next;
    }

    void printQueue(){
        String queue = toString();
        System.out.println(queue);
    }

    /**
     * Takes in input from user, to sort into the list
     * @param item The value to be inserted into the list
     */
    void addIntAscending(int item) {
        int index = 1;
        if (isEmpty()) {
            insertion(1, item);
            return;
        }

        Node temp = first;
        while (index <= size()) {
            if(temp.value<item){
                index++;
            }
            else if(temp.value>=item){
                break;
            }
            if(temp.next!=null){
                temp = temp.next;
            }
        }
        insertion(index, item);
    }

    /**
     * Inserts the element in the right position in the list
     * @param index the index the item is supposed be entered in
     * @param value The value that this element should hold
     */
        void insertion(int index, int value) {
        Node temp = new Node();
        temp.value = value;
        Node iterator = first;
        if(index==1) {
            if(first == null)
                first = temp;
            else{
                temp.next=first;
                first = temp;
            }
            size++;
            printQueue();
            return;
        }
        for(int i = 1;i<index-1;i++) {
            if(iterator.next!=null)
                iterator = iterator.next;
        }
        if(index-1 == size())
            iterator.next = temp;
        else if(index-1 < size()){
            temp.next = iterator.next;
            iterator.next = temp;
        }
        size++;
        printQueue();
    }

    boolean isEmpty(){
        return first==null;
    }

    int size(){
        return this.size;
    }

    /**
     * Makes a formatted string of the list
     * @return A formatted string of the elements in the list
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
        OrderdSortedQueueUpg6 list = new OrderdSortedQueueUpg6();
        System.out.println("Adding element 15 to the list");
        list.addIntAscending(15);
        System.out.println("Adding element 3 to the list, expected: 3,15");
        list.addIntAscending(3);
        System.out.println("Adding 7 to the list, expected 3,7,15");
        list.addIntAscending(7);
        System.out.println("Adding 4 to the list, expected 3,4,7,15");
        list.addIntAscending(4);
        System.out.println("Adding 3 to the list, expected 3,3,4,7,15");
        list.addIntAscending(3);
        System.out.println("Adding 0 to the list, expected 0,3,3,4,7,15");
        list.addIntAscending(0);
        System.out.println("Adding 16 to the list, expected 0,3,3,4,7,15,16");
        list.addIntAscending(16);
    }
}

