package phase1.day5;

import java.util.HashSet;
import java.util.Set;

public class TestApp {
    public static void main(String[] args) {
        Set<Person> set = new HashSet<>();

        Person p1 = new Student("Awais", "30303-XXXXXXX-X", 22, "BSCS59533", "BS");
        Person p2 = new Teacher("Shomi", "10101-XXXXXXX-X", 55, "tid65232", "CS");
        Person p3 = new Teacher("Maaz", "10101-XXXXXXX-X", 23, "tid656332", "SE");

        set.add(p1);
        set.add(p2);
        if (!set.add(p3)) {
            System.out.println("same CNIC already exist!");
        }
    }
}
