package deque;

public class ArrayDeque<item>{
    private item[] a;
    private int size;

    public ArrayDeque(){
        a = (item[]) new Object[8];
        size = 0;
    }

    private void resize(int length){
        item[] new_a = (item[]) new Object[length];
        System.arraycopy(new_a, 0, a, 0, size);
        a = new_a;
    }

    public void addLast(item x){
        if(size == a.length){
            resize(size * 2);
        }
        a[size] = x;
    }

    public item getLast() {
        return a[size - 1];
    }

    public item get(int i) {
        return a[i];
    }

    public int size() {
        return size;
    }

    public item removeLast() {
        item lost = a[size - 1];
        size -= 1;
        a[size - 1] = null;
        return lost;
    }
}
