package bstmap;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K> , V>implements Map61B<K, V> {
    private int size = 0;
    private BSTNode root;

    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;
        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
       return containsKey(key, root);
    }

    private boolean containsKey(K key, BSTNode root) {
        if (root == null) {
            return false;
        }
        if (root.key.equals(key)) {
            return true;
        } else if (root.key.compareTo(key) > 0) {
            return containsKey(key, root.left);
        } else {
            return containsKey(key, root.right);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }

    private BSTNode put(K key, V value, BSTNode root) {
        if (root == null) {
            size += 1;
            return new BSTNode(key, value);
        } else if (root.key.compareTo(key) > 0) {
            root.left = put(key, value, root.left);
        } else if (root.key.compareTo(key) < 0){
            root.right = put(key, value, root.right);
        } else {
            root.value = value;
        }
        return root;
    }

    @Override
    public V get(K key) {
        return get(key, root);
    }

    private V get(K key, BSTNode root) {
        if (root == null) {
            return null;
        }
        if (key.equals(root.key)) {
            return root.value;
        } else if (root.key.compareTo(key) > 0) {
            return get(key, root.left);
        } else {
            return get(key, root.right);
        }
    }

    private BSTNode search(K key, BSTNode root) {
        if (root == null) {
            return null;
        }
        if (key.equals(root.key)) {
            return root;
        } else if (root.key.compareTo(key) > 0) {
            return search(key, root.left);
        } else {
            return search(key, root.right);
        }
    }

    private BSTNode searchParent(K key, BSTNode root) {
        if (root == null) {
            return null;
        }
        if (root.left != null && root.left.key.equals(key) || root.right != null && root.right.key.equals(key)) {
            return root;
        } else if (root.key.compareTo(key) > 0) {
            return search(key, root.left);
        } else {
            return search(key, root.right);
        }
    }

    @Override
    public V remove(K key) {
        if(!containsKey(key)){
            return null;
        }
        BSTNode item = search(key, root);
        V value = item.value;
        BSTNode itemParent = searchParent(key, root);
        if (item.left == null && item.right == null) {
            if (itemParent == null) {
                root = null;
            } else if (itemParent.left == item) {
                itemParent.left = null;
            } else {
                itemParent.right = null;
            }
        } else if (item.left == null || item.right == null) {
            BSTNode child = (item.left != null) ? item.left : item.right;
            if (itemParent == null) {
                root = child;
            } else if (itemParent.left == item) {
                itemParent.left = child;
            } else {
                itemParent.right = child;
            }
        } else {
            BSTNode successorParent = item;
            BSTNode successor = item.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            item.key = successor.key;
            item.value = successor.value;

            if (successorParent.left == successor) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }
        }
        size--;
        return value;
    }

    @Override
    public V remove(K key, V value) {
        return remove(key);
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new TreeSet<>();
        addKeys(root, keys);
        return keys;
    }

    private void addKeys(BSTNode root, Set<K> keys) {
        if(root == null) {
            return;
        }
        addKeys(root.left, keys);
        keys.add(root.key);
        addKeys(root.right, keys);
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }


    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(BSTNode root) {
        System.out.println(root.key);
        System.out.println(root.left);
        System.out.println(root.right);
    }
}
