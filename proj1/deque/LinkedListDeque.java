package deque;
import afu.org.checkerframework.checker.oigj.qual.O;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T> {
    private class LinkedListNode {
        public LinkedListNode prev;
        public T item;
        public LinkedListNode next;

        public LinkedListNode(LinkedListNode prev, T item, LinkedListNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private LinkedListNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new LinkedListNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new LinkedListNode(null, null, null);
        sentinel.next = new LinkedListNode(sentinel, item, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(T item) {
        sentinel.next = new LinkedListNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        sentinel.prev = new LinkedListNode(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        String result = "";
        LinkedListNode node = sentinel.next;
        while (node != sentinel) {
            result += node.item + " ";
            node = node.next;
        }

        System.out.print(result.trim());
        System.out.println();

    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T nodeItem = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return nodeItem;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T nodeItem = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return nodeItem;
        }
    }

    public T get(int index) {
        LinkedListNode node = sentinel.next;
        while (node != sentinel) {
            if (index == 0) {
                return node.item;
            }
            node = node.next;
            index -= 1;
        }
        return null;

    }

    public T getRecursive(int index) {
        LinkedListNode node = sentinel.next;
        return getRecursiveHelper(index, node);
    }

    private T getRecursiveHelper(int index, LinkedListNode node) {
        if (size == 0 || index >= size || index < 0) {
            return null;
        } else if (index == 0) {
            return node.item;
        } else {
            return getRecursiveHelper(index - 1, node.next);
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int index;

        public LinkedListDequeIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T result = get(index);
            index += 1;
            return result;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
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
