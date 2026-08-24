package phase1.day7;

import java.util.Iterator;

public interface MyListIterator<T> extends Iterator<T> {
    public void add(T element);

    public void remove();
}
