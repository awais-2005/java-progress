package phase1.day9;

public class Teacher implements Comparable<Teacher> {
    public String name;
    public String CNIC;
    public int age;
    public final String teacherId;
    public String department;
    public int salary;

    public Teacher(String name, String CNIC, int age, String teacherId, String department, int salary) {
        this.name = name;
        this.age = age;
        this.CNIC = CNIC;
        this.teacherId = teacherId;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public int compareTo(Teacher o) {
        if (this.age > o.age)
            return 1;
        if (this.age < o.age)
            return -1;
        return 0;
    }

    @Override
    public String toString() {
        return """
                Name: %s
                CNIC: %s
                TID: %s
                Age: %d
                Salary: %d
                """.formatted(name, CNIC, teacherId, age, salary);
    }
}
