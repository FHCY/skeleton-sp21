package deque;

public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items =  (T[]) new Object[8];

    public ArrayDeque() {
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    private void resize(int length) {
        T[] newitems = (T[]) new Object[length];
        newitems[0] = items[nextLast];
        for(int i = 0; i < size(); i++) {
            int index = (nextFirst + i + 1) % items.length;
            newitems[i] = items[index];
        }
        items = newitems;
        nextFirst = length - 1;
        nextLast = size();
    }

    @Override
    public void addFirst(T item) {
        if(size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if(size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0) return true;
        else return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            int index = (nextFirst + 1 + i) % items.length;
            System.out.print(items[index] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = (nextFirst + 1) % items.length;
        T lost = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        return lost;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = (nextLast - 1 + items.length) % items.length;
        T lost = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return lost;
    }

    @Override
    public T get(int index){
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }
}
