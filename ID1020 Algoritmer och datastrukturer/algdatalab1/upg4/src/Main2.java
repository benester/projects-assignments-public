public class Main2 {

    public static void main(String[] args) {
        CircularListUpg4 list = new CircularListUpg4();
        ItemUpg4 itemUpg4 = new ItemUpg4(2);
        ItemUpg4 itemUpg42 = new ItemUpg4(3);
        ItemUpg4 itemUpg43 = new ItemUpg4(4);
        ItemUpg4 itemUpg44 = new ItemUpg4(5);
        list.enqueue(itemUpg4,'+');
        System.out.println(list.size());
        list.enqueue(itemUpg42,'+');
        list.enqueue(itemUpg43,'-');
        list.enqueue(itemUpg44,'+');
        list.dequeue('-');
        list.dequeue('+');
        list.dequeue('-');
        list.dequeue('-');
        list.enqueue(itemUpg42,'+');
    }
}
