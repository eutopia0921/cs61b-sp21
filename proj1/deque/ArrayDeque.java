package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private final int captial = 8;

    public ArrayDeque() {
        items = (T[]) new Object[captial];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }




    private boolean isFull() {
        return size() == items.length;
    }

    private boolean isShrink() {
        return size >= 16 && size < (items.length / 4);
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private void resize(int cp) {
        T[] a = (T[]) new Object[cp];
        int last = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            a[i] = items[last];
            last = plusOne(last);
        }
        items = a;
        nextFirst = cp - 1;
        nextLast = size;

    }

    private void upSize() {
        resize(size * 2);
    }

    private void downSize() {
        resize(items.length / 2);
    }

    public void addFirst(T item) {
        if (isFull()) {
            upSize();
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        if (isFull()) {
            upSize();
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }




    public int size() {
        return size;
    }

    public void printDeque() {
        int start = plusOne(nextFirst);
        while (start != nextLast) {
            System.out.print(items[start] + " ");
            start = plusOne(start);
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isShrink()) {
            downSize();
        }
        nextFirst = plusOne(nextFirst);
        T remove = items[nextFirst];
        items[nextFirst] = null;
        if (!isEmpty()) {
            size -= 1;
        }
        return remove;
    }

    public T removeLast() {
        if (isShrink()) {
            downSize();
        }
        nextLast = minusOne(nextLast);
        T remove = items[nextLast];
        items[nextLast] = null;
        if (!isEmpty()) {
            size -= 1;
        }
        return remove;

    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int i = 0;
        int result = plusOne(nextFirst);
        while (i < index) {
            result = plusOne(result);
            i += 1;
        }
        return items[result];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {

        private int index;

        ArrayDequeIterator() {
            index = 0;
        }

        @Override
        public T next() {
            T item = items[index];
            index += 1;
            return item;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }
    }


    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (this.get(i) != other.get(i)) {
                return false;
            }
        }

        return true;
    }

}
