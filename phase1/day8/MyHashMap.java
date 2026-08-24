package phase1.day8;

class Node<K, V> {
    private final int hash;
    private final K key;
    private V value;
    Node<K, V> next;

    Node(K key, V value, int hash) {
        this.key = key;
        this.value = value;
        this.hash = hash;
        next = null;
    }

    void setValue(V value) {
        this.value = value;
    }

    int getHash() {
        return hash;
    }

    K getKey() {
        return key;
    }

    V getValue() {
        return value;
    }
}

@SuppressWarnings({ "unchecked" })
public class MyHashMap<K, V> {
    private Node<K, V>[] table;
    private float loadFactor;
    private int capacity;
    private int size = 0;
    private final int DEFAULT_CAPACITY = 10;
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    private final int DEFAULT_RESIZE_BY = 2;

    public MyHashMap() {
        table = new Node[DEFAULT_CAPACITY];
        loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public MyHashMap(int tableSize) {
        if (tableSize < 1)
            tableSize = DEFAULT_CAPACITY;
        table = new Node[tableSize];
        capacity = tableSize;
    }

    public MyHashMap(int capacity, float loadFactor) {
        if (loadFactor >= 1 || loadFactor <= 0)
            loadFactor = DEFAULT_LOAD_FACTOR;
        if (capacity < 1)
            capacity = DEFAULT_CAPACITY;
        table = new Node[capacity];
        this.capacity = capacity;
        this.loadFactor = loadFactor;
    }

    private int getHash(K key) {
        return key == null ? 0 : key.hashCode();
    }

    public void put(K key, V value) {
        if (size == (int) (capacity * loadFactor))
            resize();

        putVal(key, value);
    }

    private void putVal(K key, V value) {
        Node<K, V> node = table[getHash(key) % capacity];
        Node<K, V> newNode = new Node<K, V>(key, value, getHash(key));
        if (node == null) {
            table[getHash(key) % capacity] = newNode;
        } else {
            while (node.next != null)
                node = node.next;
            node.next = newNode;
        }
    }

    public V get(K key) {
        Node<K, V> node = table[getHash(key) % capacity];
        while (node != null) {
            if (key.equals(node.getKey()))
                return node.getValue();
            node = node.next;
        }
        return null;
    }

    private void resize() {
        capacity *= DEFAULT_RESIZE_BY;
        Node<K, V>[] newtable = new Node[(int) (capacity)];
        for (int i = 0; i < table.length; i++) {
            Node<K, V> node1 = table[i];
            while (node1 != null) {
                Node<K, V> node2 = newtable[node1.getHash() % capacity];
                if (node2 == null) {
                    newtable[node1.getHash() % capacity] = node1;
                    node1 = node1.next;
                } else if (node2.next == null) {
                    node2.next = node1;
                    node1 = node1.next;
                } else {
                    node2 = node2.next;
                }

            }
        }
        table = newtable;
    }

}
