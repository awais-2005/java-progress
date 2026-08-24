package phase1.day7;

public class TestApp {
    public static void main(String[] args) {

        MyArrayList<String> names = new MyArrayList<>(1);
        names.add("A");
        names.add("B");
        names.add("C");
        names.add("D");
        names.add("E");

        MyListIterator<String> itr = names.iterator();
        int index = 0;

        while (itr.hasNext()) {
            System.out.println(itr.next());
            if (index == names.length() - 1) {
                names.remove(index);
            }
            index++;
        }

    }
}
