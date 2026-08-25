package phase1.day8;

// @SuppressWarnings({ "unused" })
public class TestApp {
    public static void main(String[] args) {
        MyHashMap<String, String> map = new MyHashMap<>(1);
        map.put("101", "Awais");
        map.put("102", "Usama");
        map.put("103", "Maaz");
        map.put("102", "Rizvi");

        System.out.println(map.get("102"));

        System.out.println("size: " + map.size());
    }
}
