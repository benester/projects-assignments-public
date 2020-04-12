/***
 * @author Benjamin Gafvelin
 *  Code generated 2019-09-09
 *  The code simulates a stack, where the user can push an element to the stack, and pop(remove) the most recently added
 *  elemet from the stack
 *  Code is loosly based on code on from lectures and previous experiance with programming
 */

public class StackUpg2 {
    Node first;
    int size = 0;

    private class Node{
        Node next;
        char value;
        Node(char value){
            this.value = value;
        }
    }

    /**
     * Adds an item to the stack
     * @param integ The value that is to be added to the stack
     */
    void push(char integ){
        Node newItem = new Node(integ);
        newItem.next = first;
        first = newItem;
        size++;
    }

    /**
     * A to string function, converst the stack to a formated String, by iterating over the stack
     * @return The formated String
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

    /**
     * Removes the "top" aka the first element of the stack
     * @return the first element of the stack
     */
    int pop(){
        if(isEmpty()){
            return 0;
        }
        int returnValue = first.value;
        first = first.next;
        size--;
        return returnValue;
    }

    /**
     * Returns a boolean true/false, depending on if the stack is empty or not
     * @return true, if stack is empty, false if stack is not empty
     */
    boolean isEmpty(){
        if(size <= 0){
            return true;
        }
        return false;
    }

    /**
     * Returns the number of elemets in the stack
     * @return and integer, with the amount of elements in the stack
     */
    int size(){
        return this.size;
    }

    /**
     * These are the tests for this class
     * @param args
     */
    public static void main(String args[]){
        StackUpg2 stackUpg2 = new StackUpg2();
        System.out.println("Push: 1");
        stackUpg2.push('1');
        System.out.println("Stack right now: " + stackUpg2.toString());
        System.out.println("Push: 4");
        stackUpg2.push('4');
        System.out.println("Stack right now: " + stackUpg2.toString());
        System.out.println("Push: b");
        stackUpg2.push('b');
        System.out.println("Stack right now: " + stackUpg2.toString());
        System.out.println("Push: k");
        stackUpg2.push('k');
        System.out.println("Stack right now: " + stackUpg2.toString());
        System.out.println("Pop, expected: k, Actual: "+ (char) stackUpg2.pop());
        System.out.println("Stack right now: " + stackUpg2.toString());
        System.out.println("Stack should not be empty, isEmpty? " + stackUpg2.isEmpty());
        System.out.println("Pop, expected: b, Actual: "+ (char) stackUpg2.pop());
        System.out.println("Stack right now: " + stackUpg2.toString());
        System.out.println("Pop, expected: 4, Actual: "+ (char) stackUpg2.pop());
        System.out.println("Stack right now: " + stackUpg2.toString());
        System.out.println("Pop, expected: 1, Actual: "+ (char) stackUpg2.pop());
        System.out.println("Stack should be empty, isEmpty? " + stackUpg2.isEmpty());
    }
}

