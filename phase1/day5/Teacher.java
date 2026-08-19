package phase1.day5;

public class Teacher extends Person {
    public final String teacherId;
    public String department;

    public Teacher(String name, String CNIC, int age, String teacherId, String department) {
        super(name, CNIC, age);
        this.teacherId = teacherId;
        this.department = department;
    }
}
