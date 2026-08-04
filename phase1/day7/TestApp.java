package phase1.day7;

public class TestApp {
    public static void main(String[] args) {
        MyArrayList<String> names = new MyArrayList<>(5);
        System.out.println("Size: " + names.length());
        System.out.println("Capacity: " + names.capacity());
        names.add("Awais");
        System.out.println("Size: " + names.length());
        System.out.println("Capacity: " + names.capacity());
        names.add("Soma");
        System.out.println("Size: " + names.length());
        System.out.println("Capacity: " + names.capacity());
        names.add("Usama");
        System.out.println("Size: " + names.length());
        System.out.println("Capacity: " + names.capacity());
        names.add("Maaz");
        System.out.println("Size: " + names.length());
        System.out.println("Capacity: " + names.capacity());
        names.add("Saeed");
        System.out.println("Size: " + names.length());
        System.out.println("Capacity: " + names.capacity());
        names.add("Tariq");

        for (String name : names) {
            System.out.println(name);
        }
    }

}
