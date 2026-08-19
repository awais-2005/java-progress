package phase1.day5;

public class Person {
    public final String CNIC;
    public String name;
    public int age;

    public Person(String name, String CNIC, int age) {
        this.name = name;
        this.CNIC = CNIC;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return (this.CNIC.hashCode() / 31) * 17;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person p))
            return false;
        return this.CNIC == p.CNIC;
    }
}
