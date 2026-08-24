package phase1.day7;

public class TestApp {
    public static void main(String[] args) {

        MyArrayList<String> names = new MyArrayList<>(1);
        names.add("Awais");
        names.add("Soma");
        names.add("Usama");

        MyListIterator<String> itr = names.iterator();
        int safetyCounter = 0;
        int SAFETY_LIMIT = 20;

        while (itr.hasNext()) {
            safetyCounter++;
            if (safetyCounter > SAFETY_LIMIT) {
                System.out.println("Exit from Infinite Loop!");
                break;
            }
            String n = itr.next();
            System.out.println(safetyCounter + ": next() returned \"" + n + "\", list size now " + names.length());
            if (n.equals("Awais")) {
                itr.add("Awais");
            }
        }
    }
}
