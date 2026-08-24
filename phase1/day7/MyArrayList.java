package phase1.day7;

import java.util.Arrays;
import java.util.ConcurrentModificationException;

@SuppressWarnings({ "unchecked" })
public class MyArrayList<T> implements Iterable<T> {
    private final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size = 0;
    private int modCount = 0;
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
        modCount++;
        if (size + 1 == totalCapacity)
            resize();
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

    public void resize() {
        int newCapacity = (int) (totalCapacity * 1.5) + 1;
        elements = Arrays.copyOf(elements, newCapacity);
        totalCapacity = newCapacity;
    }

    public void insert(T element, int index) {
        modCount++;
        if (size + 1 == totalCapacity)
            resize();
        size++;
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }

        elements[index] = element;
    }

    public void remove(int index) {
        modCount++;
        for (int i = index; i < size - 1; i++)
            elements[i] = elements[i + 1];
        size--;
    }

    @Override
    public MyListIterator<T> iterator() {
        return new MyListIterator<T>() {
            int cursor = 0;
            int expectedModCount = modCount;

            @Override
            public T next() {
                return get(cursor++);
            }

            @Override
            public boolean hasNext() {
                if (cursor < size)
                    return true;
                checkExpectedModCount();
                return false;
            }

            private void checkExpectedModCount() {
                if (modCount != expectedModCount)
                    throw new ConcurrentModificationException();
            }

            public void add(T element) {
                expectedModCount++;
                MyArrayList.this.insert(element, cursor++);
            }

            public void remove() {
                expectedModCount++;
                MyArrayList.this.remove(--cursor);
            }
        };
    }
}
