package phase1.day5;

public class Student extends Person {
    public final String rollno;
    public String course;

    public Student(String name, String CNIC, int age, String rollno, String course) {
        super(name, CNIC, age);
        this.rollno = rollno;
        this.course = course;
    }

}
