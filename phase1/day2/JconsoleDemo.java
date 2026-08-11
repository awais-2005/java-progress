package phase1.day2;

import java.util.Scanner;

public class JconsoleDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (true) {
            System.out.print("""
                    1. Create Student
                    0. exit
                    """);
            choice = sc.nextInt();
            if (choice == 0) {
                sc.close();
                return;
            }
            if (choice == 1) {
                for (int i = 0; i < Integer.MAX_VALUE; i++)
                    new Student("Awais" + i, i + 16);
            }
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
