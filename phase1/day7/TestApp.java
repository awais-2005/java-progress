package phase1.day7;

public class TestApp {
    public static void main(String[] args) {

        MyArrayList<String> names = new MyArrayList<>(1);

        names.add("Awais");
        names.add("Soma");
        names.add("Usama");
        names.add("Maaz");
        names.add("Saeed");
        names.add("Tariq");

        names.insert("Kabootar", 1);

        MyListIterator<String> itr = names.iterator();

        while (itr.hasNext()) {
            String n = itr.next();
            if (n.equals("Awais") || n.equals("Usama")) {
                itr.add("gap");
            }
        }
        for (String name : names)
            System.out.println(name);
    }

}
