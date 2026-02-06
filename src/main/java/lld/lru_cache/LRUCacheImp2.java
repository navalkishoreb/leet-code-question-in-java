package lld.lru_cache;

import lld.lru_cache.models.DLLNode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class LRUCacheImp2<K, V> implements LRUCache<K, V> {
    private final Map<K, DLLNode<K, V>> map;
    private final DoublyLinkedList<K, V> lruList;
    private final int capacity;

    //    dont use read-write lock as onboth get and put call DDL is changed as we
//    move the node to front in list at get call --> this is write operation
//    we don't want two threads to change the head of the queue
//    private final ReentrantReadWriteLock
    private final ReentrantLock lock;

    public LRUCacheImp2(int capacity) {
        map = new ConcurrentHashMap<>();
        lruList = new DoublyLinkedList<>();
        this.capacity = capacity;
        lock = new ReentrantLock(true);
    }

    private <T> T tryWithLock(Supplier<T> action, Supplier<T> fallback) {
        if (lock.tryLock()) {
            try {
                return action.get();
            } finally {
                lock.unlock();
            }
        } else {
            return fallback.get();
        }
    }

    private void tryWithLock(Runnable action, Runnable fallback) {
        if (lock.tryLock()) {
            try {
                action.run();
            } finally {
                lock.unlock();
            }
        } else {
            fallback.run();
        }
    }

    private void putAction(K key, V value) {
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
    public void put(K key, V value) {
        tryWithLock(
                () -> putAction(key, value),
                () -> {
                    throw new IllegalStateException("lock not acquired");
                }
        );
    }

    private V getAction(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        DLLNode<K, V> node = map.get(key);
        lruList.moveNodeToFront(node);
        return node.getValue();
    }

    @Override
    public V get(K key) {
        if (lock.tryLock()) {
            try {
                return getAction(key);
            } finally {
                lock.unlock();
            }
        } else {
            throw new IllegalStateException("lock not acquired");
        }
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