package phase1.day9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ArrayList<Teacher> list = new ArrayList<>(List.of(
                getRandomTeacher(null, 0),
                getRandomTeacher(null, 0),
                getRandomTeacher("Awais Rana", 42),
                getRandomTeacher("Awais Rana", 42),
                getRandomTeacher("Awais Rana", 42),
                getRandomTeacher(null, 50),
                getRandomTeacher(null, 50),
                getRandomTeacher(null, 0),
                getRandomTeacher(null, 0),
                getRandomTeacher(null, 0)));

        Comparator<Teacher> comparator = new Comparator<Teacher>() {
            @Override
            public int compare(Teacher o1, Teacher o2) {
                if (o1.age > o2.age)
                    return 1;
                if (o1.age < o2.age)
                    return -1;
                return 0;
            }
        };

        list.sort(comparator.thenComparing(t -> t.name).thenComparing(t -> t.salary));

        for (Teacher t : list) {
            System.out.println("\n=====================================");
            System.out.println(t);
        }

    }

    public static Teacher getRandomTeacher(String name, int age) {
        final String[] firstNames = { "Tariq", "Qaisar", "Kausar", "Awais", "Hassan", "Huzaifa", "Saeed",
                "Mudassir",
                "Usama" };
        final String[] lastNames = { "Ehtisham", "Naseem", "Waseem", "Javed", "Tanveer", "Maaz", "Ali",
                "Qasim", "Hamza",
                "Bilal" };
        StringBuilder fullName = name == null
                ? new StringBuilder(firstNames[(int) (Math.random() * firstNames.length)])
                        .append(" ")
                        .append(lastNames[(int) (Math.random() * lastNames.length)])
                : new StringBuilder(name);
        StringBuilder cnic = new StringBuilder((long) (Math.random() * 10_000_000_000_000L) + "").insert(5, "-")
                .insert(13, '-');

        return new Teacher(fullName.toString(), cnic.toString(), age == 0 ? (int) (Math.random() * 100) : age,
                "TID" + ((int) (Math.random() * 1_000_000)), "Computer Science",
                (int) (Math.random() * 1000000));
    }
}
