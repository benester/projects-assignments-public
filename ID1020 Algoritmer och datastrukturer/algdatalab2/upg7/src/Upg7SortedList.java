/**
 * @Auther Benjamin Gafvelin
 * Code was generated 2019-09-18
 * Code was updated 2019-09-20
 * The program sorts input into a linked list in ascending order.
 * Code is based on linked list code from the course book, but the algorithm is self-made
 */

public class Upg7SortedList {
    private Node first;
    private int size = 0;

    private class Node {
        private Node next;
        private Node prev;
        private int value;
    }
    /**
     * Enqueue takes a value and sorts it into a doubley linked list, in acending order
     * @param value the value to be sorted in to the list
     */
    public void enqueue(int value) {
        Node newNode = new Node();
        newNode.value = value;

        if(size == 0){
            first = newNode;
        }else {
            Node iterator = first;
            if (first.next == null && first.value > newNode.value) {
                Node temp = first;
                first = newNode;
                newNode.next = temp;
                temp.prev = first;

            } else if (first.next == null) {
                first.next = newNode;
                newNode.prev = first;


                }else if(first.value > newNode.value){
                    Node temp = first;
                    first = newNode;
                    temp.prev = first;
                    first.next = temp;
                }
             else {
                iterator = iterator.next;
                for (int i = 1; i < size; i++) {
                    if (iterator.value >= newNode.value) {
                        newNode.next = iterator;
                        newNode.prev= iterator.prev;
                        iterator.prev.next = newNode;
                        break;
                    }
                    if (i + 1 == size) {
                        newNode.prev = iterator;
                        iterator.next = newNode;
                        break;
                    }
                    iterator = iterator.next;
                }
            }
        }
        size++;
        printList();
    }

    /**
     * Code for printing the content of the list, by itterating across it.
     */
    public void printList() {
        Node it = first;
        String hej = "";
        for (int i = 0; i < size; i++) {
            hej += ("[" + it.value + "]");
            it = it.next;
            if(i < size-1){ hej += ", ";}
        }
        System.out.println(hej);
    }


    /**
     * Testing done here
     */
    public static void main(String[] args) {
        Upg7SortedList list = new Upg7SortedList();
        System.out.println("Testing done here: ");
        System.out.println("Enqueueing : 2");
        list.enqueue(2);
        System.out.println("Enqueueing : 1");
        list.enqueue(1);
        System.out.println("Enqueueing : 5");
        list.enqueue(5);
        System.out.println("Enqueueing : 8");
        list.enqueue(8);
        System.out.println("Enqueueing : 9");
        list.enqueue(9);
        System.out.println("Enqueueing : 4");
        list.enqueue(4);
        System.out.println("Enqueueing : 3");
        list.enqueue(3);
        System.out.println("Enqueueing : 10");
        list.enqueue(10);
        System.out.println("Enqueueing : -8");
        list.enqueue(-8);
        System.out.println("Enqueueing : -10");
        list.enqueue(-10);
        System.out.println("Enqueueing : 0");
        list.enqueue(0);
        System.out.println("Enqueueing : 100");
        list.enqueue(100);
        System.out.println("List should be -10, -8, 0, 1 ,2 ,3, 4, 5, 8 ,9 ,10, 100");
    }
}
