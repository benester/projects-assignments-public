public class Main {

    public static void main(String[] args) {
        System.out.println("Creating stackUpg2");
        StackUpg2 stackUpg2 = new StackUpg2();
        System.out.println("Enter your integers");

        try{
            char i = (char) System.in.read();
            while(i != '\n'){
                stackUpg2.push(i);
                i = (char)System.in.read();
            }
        }
        catch(java.io.IOException i){
            System.out.println("Somwthing went wrong");
        }
        System.out.println(stackUpg2.toString());

        while(!stackUpg2.isEmpty()){
            System.out.print((char) stackUpg2.pop());
        }
    }
}