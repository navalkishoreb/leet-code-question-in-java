package lld.lru_cache;

import lld.lru_cache.models.DLLNode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCacheImp2<K, V> implements LRUCache<K, V> {
    private final Map<K, DLLNode<K, V>> map;
    private final DoublyLinkedList<K, V> lruList;
    private final int capacity;

    public LRUCacheImp2(int capacity) {
        map = new ConcurrentHashMap<>();
        lruList = new DoublyLinkedList<>();
        this.capacity = capacity;
    }

    @Override
    public void put(K key, V value) {
        if (map.containsKey(key)) {
            DLLNode<K, V> node = map.get(key);
            node.setValue(value);
            lruList.moveNodeToFront(node);
            return;
        }
        if (map.size() >= this.capacity) {
            // evict
            DLLNode<K, V> node = lruList.removeTail();
            map.remove(node.getKey());
        }

        DLLNode<K, V> node = new DLLNode<>(key, value);
        map.put(key, node);
        lruList.addToFront(node);
    }

    @Override
    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        DLLNode<K, V> node = map.get(key);
        lruList.moveNodeToFront(node);
        return node.getValue();
    }

    @Override
    public void print() {
        System.out.print("[");
        for (K key : map.keySet()) {
            System.out.print(key + ",");
        }
        System.out.println("]");
    }

    @Override
    public int size() {
        return map.size();
    }
}

class DoublyLinkedList<K, V> {
    private final DLLNode<K, V> front;
    private final DLLNode<K, V> tail;

    public DoublyLinkedList() {
        // anchor point to avoid null checks at front and tail
        front = new DLLNode<>(null, null);
        tail = new DLLNode<>(null, null);
        front.setNext(tail);
        tail.setPrev(front);
    }

    public void moveNodeToFront(DLLNode<K, V> node) {
        removeNode(node);
        addToFront(node);
    }


    public DLLNode<K, V> removeTail() {
        DLLNode<K, V> node = tail.getPrev();
        removeNode(node);
        return node;
    }

    public void removeNode(DLLNode<K, V> node) {
        node.getNext().setPrev(node.getPrev());
        node.getPrev().setNext(node.getNext());
    }

    public void addToFront(DLLNode<K, V> node) {
       node.setPrev(front);
       front.getNext().setPrev(node);
       node.setNext(front.getNext());
       front.setNext(node);

    }
}