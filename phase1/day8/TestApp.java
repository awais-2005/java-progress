package phase1.day8;

// @SuppressWarnings({ "unused" })
public class TestApp {
    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<>(4, 0.75f);

        map.put(0, "A");
        map.put(1, "B");
        map.put(2, "C");
        map.put(3, "D");
    }
}
