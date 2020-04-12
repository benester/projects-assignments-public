public class Main2 {

    public static void main(String[] args) {
        GeneralizedListUpg5 list = new GeneralizedListUpg5();
        list.enQueue(4);
        list.enQueue(5);
        list.enQueue(7);
        list.enQueue(9);
        list.deQueue(1);
        list.deQueue(3);
        list.deQueue(2);
        list.deQueue(1);
        list.enQueue(4);
    }
}
