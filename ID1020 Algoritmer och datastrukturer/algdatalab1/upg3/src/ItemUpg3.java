public class ItemUpg3 {
    private String namn;
    private int age;

    public ItemUpg3(String namn, int age){
        this.namn = namn;
        this.age = age;
    }
    public String toString(){
        String s = (this.namn + "," + age);
        return s;
    }
}
