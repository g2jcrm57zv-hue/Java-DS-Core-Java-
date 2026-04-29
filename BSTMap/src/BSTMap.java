import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;

    // 显式 无参构造器
    public BSTMap() {
        this.root = null;
    }

    // 内部节点类
    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        int size;

        public Node(K k, V v, int s) {
            key = k;
            value = v;
            size = s;
        }
    }

    @Override
    public void put(K key, V value) {
        this.root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        // 基本情况
        if (node == null) {
            return new Node(key, value, 1);
        }

        int cmp = key.compareTo(node.key);

        // 递归逻辑
        if (cmp < 0){
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        }else{
            node.value = value;
        }

        node.size = 1 + size(node.left) + size(node.right);

        return node;
    }


    @Override
    public V get(K key) {
        Node node = get(root, key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Node get(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            return get(node.left, key);
        }else if (cmp > 0){
            return get(node.right, key);
        }else {
            return node;
        }
    }

    @Override
    public boolean containsKey(K key) {
        return get(root, key) != null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node n) {
        if (n == null) return 0;
        return n.size;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }
}

