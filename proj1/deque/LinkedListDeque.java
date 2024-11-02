package deque;

import org.checkerframework.common.reflection.qual.NewInstance;

public class LinkedListDeque<item> {
    private IntNode sentinel;
    private int size;

    public class IntNode{
        public item first;
        public IntNode next;
        public IntNode prev;
        public IntNode(IntNode p,item f, IntNode n){
            prev = p;
            first = f;
            next = n;
        }
    }

    public LinkedListDeque(){
        sentinel = new IntNode(sentinel, null, sentinel);
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        if(size == 0) return true;
        else return false;
    }

    public void addFirst(item x){
        if(isEmpty()){
            sentinel.first = x;
            sentinel.prev = sentinel;
            sentinel.next = sentinel;
            size += 1;
            return;
        }
        IntNode first_node = new IntNode(sentinel.prev, x, sentinel);
        sentinel.prev = first_node;
        first_node.prev.next = first_node;
        sentinel = first_node;
        size += 1;
    }

    public void addLast(item x){
        if(isEmpty()){
            sentinel.first = x;
            sentinel.prev = sentinel;
            sentinel.next = sentinel;
            size += 1;
            return;
        }
        IntNode last_node = new IntNode(sentinel.prev, x, sentinel);
        sentinel.prev = last_node;
        last_node.prev.next = last_node;
        size += 1;
    }

    public item removeFirst(){
        if(isEmpty()){
            return null;
        }
        item x = sentinel.first;
        if(size == 1){
            sentinel = null;
            size = 0;
            return x;
        }
        sentinel.prev.next = sentinel.next;
        sentinel.next.prev = sentinel.prev;
        sentinel = sentinel.next;
        size -= 1;
        return x;
    }

    public item removeLast(){
        if(isEmpty()){
            return null;
        }
        item x = sentinel.prev.first;
        if(size == 1){
            sentinel = null;
            size = 0;
            return x;
        }
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return x;
    }

    public item getRecursive(int index){
        IntNode record = sentinel;
        for(int i = 0; i < index; i++){
            record = record.next;
        }
        return record.first;
    }

    public void printDeque(){
        IntNode record = sentinel;
        for(int i = 0; i < size; i++){
            System.out.print(record.first + " ");
            record = record.next;
        }
    }

}
