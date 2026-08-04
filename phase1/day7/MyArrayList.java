package phase1.day7;

import java.util.Arrays;
import java.util.Iterator;

@SuppressWarnings({ "unchecked" })
public class MyArrayList<T> implements Iterable<T> {
    private final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size = 0;
    private int totalCapacity;

    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        totalCapacity = DEFAULT_CAPACITY;
    }

    public MyArrayList(int capacity) {
        if (capacity < 1)
            capacity = DEFAULT_CAPACITY;
        elements = new Object[capacity];
        totalCapacity = capacity;
    }

    public MyArrayList(T[] array) {
        totalCapacity = (int) (array.length * 1.5);
        size = array.length;
        elements = Arrays.copyOf(array, totalCapacity);
    }

    public void add(T element) {
        if (size + 1 == totalCapacity)
            resize((int) (totalCapacity * 1.5));
        elements[size++] = element;
    }

    public T get(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(
                    "Index %d is invalid for range [%d, %d].".formatted(index, 0, size - 1));
        return (T) elements[index];
    }

    public int length() {
        return size;
    }

    public int capacity() {
        return totalCapacity;
    }

    public void resize(int newCapacity) {
        elements = Arrays.copyOf(elements, newCapacity);
        totalCapacity = newCapacity;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursor = 0;

            @Override
            public T next() {
                return get(cursor++);
            }

            @Override
            public boolean hasNext() {
                return cursor < size;
            }
        };
    }
}
