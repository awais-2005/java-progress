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
        capacity = DEFAULT_CAPACITY;
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
        Node<K, V> bucket = table[getHash(key) % capacity];

        if (bucket == null) {
            table[getHash(key) % capacity] = new Node<>(key, value, getHash(key));
            size++; // increment size after successful insertion
            return;
        }

        Node<K, V> prev = null;

        while (bucket != null && !bucket.getKey().equals(key)) {
            prev = bucket;
            bucket = bucket.next;
        }

        if (bucket == null) {
            prev.next = new Node<>(key, value, getHash(key));
            size++; // increment size after successful insertion
        } else
            bucket.setValue(value);
    }

    public int size() {
        return size;
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
        Timer exitTimer = new Timer(3000, "Something went wrong while resize().");
        for (int i = 0; i < table.length; i++) {
            Node<K, V> node1 = table[i];
            while (node1 != null) {
                Node<K, V> node2 = newtable[node1.getHash() % capacity];
                if (node2 == null) {
                    newtable[node1.getHash() % capacity] = node1;
                    node1 = node1.next;
                    newtable[node1.getHash() % capacity].next = null;
                } else if (node2.next == null) {
                    node2.next = node1;
                    node1 = node1.next;
                    node2.next.next = null;
                } else {
                    node2 = node2.next;
                }
            }
        }
        table = newtable;
        exitTimer.dontExit();
        exitTimer.interrupt();
    }

}

class Timer extends Thread {
    private final int milliseconds;
    private final String exitMessage;
    private boolean exit = true;

    Timer(int milliseconds) {
        if (milliseconds < 100)
            throw new IllegalArgumentException("Atleast enter 100ms");
        this.milliseconds = milliseconds;
        exitMessage = "Time out";
    }

    Timer(int milliseconds, String exitMessage) {
        if (milliseconds < 100)
            throw new IllegalArgumentException("Atleast enter 100ms");
        this.milliseconds = milliseconds;
        this.exitMessage = exitMessage;
    }

    @Override
    public void run() {
        if (milliseconds == 0)
            return;
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }

        if (exit) {
            System.out.println(exitMessage);
            System.exit(0);
        }
    }

    public void dontExit() {
        this.exit = false;
    }
}
