package deque;

import java.util.Iterator;
import java.util.Objects;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private IntNode sentinel;
    private int size;

    public class IntNode {
        private T first;
        private IntNode next;
        private IntNode prev;
        public IntNode(IntNode p, T f, IntNode n) {
            prev = p;
            first = f;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(T x) {
        IntNode firstNode = new IntNode(sentinel, x, sentinel.next);
        sentinel.next.prev = firstNode;
        sentinel.next = firstNode;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        IntNode lastNode = new IntNode(sentinel.prev, x, sentinel);
        sentinel.prev.next = lastNode;
        sentinel.prev = lastNode;
        size += 1;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T x = sentinel.next.first;
        if (size == 1) {
            sentinel.next = sentinel;
            sentinel.prev = sentinel;
        } else {
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
        }
        size -= 1;
        return x;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T x = sentinel.prev.first;
        if (size == 1) {
            sentinel.next = sentinel;
            sentinel.prev = sentinel;
        } else {
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
        }
        size -= 1;
        return x;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        IntNode record = sentinel.next;
        for (int i = 0; i < index; i++) {
            record = record.next;
        }
        return record.first;
    }

    @Override
    public void printDeque() {
        IntNode record = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(record.first + " ");
            record = record.next;
        }
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(IntNode node, int index) {
        if (index == 0) {
            return node.first;
        } else {
            return getRecursiveHelper(node.next, index - 1);
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private IntNode node;

        LinkedListIterator() {
            this.node = sentinel.next;
        }

        public boolean hasNext() {
            return this.node != sentinel;
        }

        public T next() {
            T returnItem = node.first;
            node = node.next;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof Deque)) {
            return false;
        }

        Deque<?> otherDeque = (Deque<?>) other;
        if (otherDeque.size() != this.size()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (!Objects.equals(get(i), otherDeque.get(i))) {
                return false;
            }
        }
        return true;
    }

}
