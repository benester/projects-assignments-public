/***
 * @author Benjamin Gafvelin
 *  Code generated 2019-09-08
 *  The Code takes an element of the type ItemUpg4, and a character, then places this element either at the fron or back
 *  of a cicularly linked list. The user can remove an item from the front or back of list, by user input.
 * @see ItemUpg4
 *  Code is based on preporatory lab
 */

public class CircularListUpg4 {
private Node tail;
private Node head;
private int size = 0;

public CircularListUpg4(){
    this.head = new Node();
    head.next = head;
}

    private class Node{
        private Node next;
        private ItemUpg4 itemUpg4;
    }

    /**
     *
     * @param itemUpg4 the itemUpg4 to be stored in the lists
     * @param select '+' = front of list, '-' back of list
     */
    void enqueue(ItemUpg4 itemUpg4, char select) {
        Node newNode = new Node();
        newNode.itemUpg4 = itemUpg4;
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            newNode.next = head;
        }
        else if(select == '+'){
            Node temp = head;
            newNode.next = temp;
            head = newNode;
            tail.next = head;
        }
        else if(select == '-'){
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }
        else{
            System.out.println("Error adding to list, no itemUpg4 has been added to list");
            return;
        }
        size++;
        printList();
    }

    /**
     * Helper method to print the content of the circular list
     */
    private void printList(){
         String s = toString();
         System.out.println(s);
    }
    /**
     *Removes an element from the circular list, either in the front or the back depending on the user input
     * @param select '+' = front of the list, '-' is back of the list
     */
    void dequeue(char select){
        if(size<=0){
            System.out.println("Error: List empty, could not remove element");
            return;
        }
        else if(select == '+')
        {
            head = head.next;
            tail.next = head;
            size--;
        }
        else if(select == '-'){
            Node temp = head;

            while(temp.next!=tail){
                temp = temp.next;
            }
            tail = temp;
            tail.next = head;
            size--;
        }
        else{
            System.out.println("Faulty input");
            return;
        }
        printList();

    }

    /**
     * A method for getting the size of the circular list
     * @return the size of the circular list
     */
    int size(){
        return this.size;
    }

    /**
     * boolean method to determine if the list is empty or not
     * @return true if the list is empty, false if the list has elements
     */
    boolean isEmpty(){
        if(size()<=0){
            return true;
        }
        return false;
    }

    /**
     * Method for formating the list for print-out
     * @return returns a formated string of the list
     */
    public String toString(){
        if(isEmpty()){
            return "List is empty";
        }
        String s = "["+head.itemUpg4 +"]";
        Node temp = head.next;
        while(temp != head){
            s += "[" + temp.itemUpg4 + "]";
            temp = temp.next;
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println("Starting the tests");
        System.out.println("Creating itemUpg41, with value 1, itemUpg42:5, itemUpg43:4, itemUpg44:3");
        CircularListUpg4 list = new CircularListUpg4();
        ItemUpg4 item1 = new ItemUpg4(1);
        ItemUpg4 item2 = new ItemUpg4(5);
        ItemUpg4 item3 = new ItemUpg4(4);
        ItemUpg4 item4 = new ItemUpg4(3);
        System.out.println("Adding item1 to start of list");
        list.enqueue(item1,'+');
        System.out.println("Adding item2(value:5) to back of list");
        list.enqueue(item2, '-');
        System.out.println("Adding item3(value:4) to start of list");
        list.enqueue(item3,'+');
        System.out.println("Adding item4(value:3) to back of list");
        list.enqueue(item4,'-');
        System.out.println("Remove element from back of list, value:3 should not be present after");
        list.dequeue('-');
        System.out.println("Remove element from front of list, value:4 should not be present after");
        list.dequeue('+');
        System.out.println("Remove element from back of list, value:5 should not be present after");
        list.dequeue('-');
        System.out.println("Remove element from front of list, value:1 should not be present");
        list.dequeue('+');
        System.out.println("Trying to remove element from empty list");
        list.dequeue('+');
    }
}
