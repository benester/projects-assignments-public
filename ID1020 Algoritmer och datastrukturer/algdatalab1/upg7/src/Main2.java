import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        System.out.println("Input code");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        StackUpg7 stackUpg7 = new StackUpg7();
        System.out.println(stackUpg7.checkStack(input));
    }
}
