/***
 * @author Benjamin Gafvelin
 *  Code generated 2019-09-09
 *  The Code takes a string input from user and detemines if the perentheses are balanced
 *  Code borrowed from asigment 2
 */
public class StackUpg7 {
    Node first;
    int size = 0;

    private class Node{
        Node next;
        char character;
    }

    /**
     * Adds element to stack
     * @param character the character to be added to the stack
     */
    void push(char character){
        Node newItem = new Node();
        newItem.character = character;
        newItem.next = first;
        first = newItem;
        size++;
    }

    /**
     * Remove an item from stack
     * @return
     */
    char pop(){
        char returnValue = first.character;
        first = first.next;
        size--;
        return returnValue;
    }

    /**
     * Returns true if list is empty
     * @return  true if list is empty, otherwise false
     */
    boolean isEmpty(){
        if(size <= 0){
            return true;
        }
        return false;
    }

    /**
     * Pushes opening parentheses to the stack, and compares them to the closing parentheses, if anything doesn't match, return false
     * @param input the input string to be compared
     * @return returns true if parentheses are balanced, otherwise false
     */
    public boolean checkStack(String input){
        for(int i =0; i<input.length();i++){
            char a = input.charAt(i);
            if(a=='('||a=='['||a=='{'){
                push(a);
            }
            if(a==')'||a==']'||a=='}'){
                if(isEmpty()){
                    return false;
                }
                char b = pop();
               if((int)b - (int)a > 2 || (int)b - (int)a < -2){
                   return false;
               }
            }
        }
        if(isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Size of the list
     * @return returns the size of list
     */
    int size(){

        return this.size;
    }

    /**
     * Test for the program
     * @param args
     */
    public static void main(String[] args) {
        StackUpg7 stackUpg7 = new StackUpg7();
        System.out.println("Input string : (aaabbb(cccdd{dd}ss)dd)");
        String input = ("(aaabbb(cccdd{dd}ss)dd)");
        System.out.println("Should return true, balanced? "+ stackUpg7.checkStack(input));

        while(!stackUpg7.isEmpty()){
            stackUpg7.pop();
        }

        System.out.println("Input string: ((pp((pps{dd)))}");
        input = "((pp((pps{dd)))}";
        System.out.println("Should return false, balanced? "+ stackUpg7.checkStack(input));

        while(!stackUpg7.isEmpty()){
            stackUpg7.pop();
        }

        System.out.println("input string: ))((");
        input ="))((";
        System.out.println("Should return false, balanced? "+ stackUpg7.checkStack(input));

        StackUpg7 stack2 = new StackUpg7();
        System.out.println("input string: (({{[[aaaaa]]}}))");
        String input2 ="(({{[[aaaaa]]}}))";
        System.out.println("Should return true, balanced? " + stack2.checkStack(input2));

        StackUpg7 stack3 = new StackUpg7();
        System.out.println("Input string: aaa");
        String input3 = "aaa";
        System.out.println("Should return true, actual: " + stack3.checkStack(input3));
    }
}

