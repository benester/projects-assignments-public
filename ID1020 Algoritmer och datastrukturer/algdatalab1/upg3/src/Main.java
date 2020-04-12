public class Main {

    public static void main(String[] args) {
        System.out.println("Creating queueUp3!");
        QueueUp3 queueUp3 = new QueueUp3();
        ItemUpg3 person1 = new ItemUpg3("Bosse",34);
        ItemUpg3 person2 = new ItemUpg3("Hasse",102);
        ItemUpg3 person3 = new ItemUpg3("Janne",5);
        ItemUpg3 person4 = new ItemUpg3("Bert", 34);

        queueUp3.enQueue(person1);
        queueUp3.enQueue(person2);
        queueUp3.enQueue(person3);
        queueUp3.deQueue();
        queueUp3.deQueue();
        queueUp3.enQueue(person4);
        queueUp3.deQueue();
    }
}
