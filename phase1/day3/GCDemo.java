package phase1.day3;

public class GCDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 10000000; i++) {
            new Student(i + "", i);
        }
    }
}

class Student {
    String name;
    int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
