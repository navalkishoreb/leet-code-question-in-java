package lld.lru_cache;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

public class LruCacheApplication {
    public static void main(String[] args) {
        int capacity = 5;
        LRUCache<Integer, String> cache = new LRUCache<>(capacity);
        String data = cache.get(1);
        assert data == null : "data should be null as cache not populated";
        cache.put(1, "one");
        data = cache.get(1);
        assert data.equals("one") : "key 1 should be return 'one'";
//        System.out.println(data);
        cache.put(2, "two");
        cache.put(3, "three");
        cache.put(4, "four");
        cache.put(5, "five");
        // if we don't have capacity we want to remove last
        cache.put(6, "six");
        assert cache.size() <= capacity : "cache should bounded with capacity";
        data = cache.get(1);
        assert data == null : "is key 1 got removed";


        // but if I access 2
        // and put another value
        // I don't want 2 to remove
        // 3 was least used can be evicted
        cache.get(2);
        cache.put(7, "seven");
        assert cache.size() == capacity;
        data = cache.get(2);
        assert data != null : "key 2 should not be removed";
        data = cache.get(3);
        assert data == null : "key 3 should  be removed";


        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.submit(new Consumer(1, "one",100, cache));
        pool.submit(new Consumer(2, "two",100, cache));
        pool.submit(new Consumer(3, "three",100, cache));
//        pool.submit(new Print(cache));
        pool.submit(new Consumer(4, "four",100, cache));
        pool.submit(new Consumer(5, "five",100, cache));
        pool.submit(new Consumer(6, "six",100, cache));
//        pool.submit(new Print(cache));
        pool.shutdown();
        // running iterator while modification are occurring will fail for ConcurrentModification
        // after using Collection.syncronizedMap
//         cache.print();
        try {
            pool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            pool.shutdownNow();
        }
        cache.print();

        // if only unique keys are access this will not cause any issue
        // in multi thread environemnt

        // exception not produced
//        pool = Executors.newFixedThreadPool(3);
//        pool.submit(new Consumer(1, "one",100, cache));
//        pool.submit(new Consumer(1, "one",100, cache));
//        pool.submit(new Consumer(2, "two",100, cache));
//        pool.submit(new Consumer(2, "two",100, cache));
//        pool.submit(new Consumer(3, "three",100, cache));
//        pool.submit(new Consumer(3, "three",100, cache));
//        pool.submit(new Consumer(1, "one",100, cache));
//        pool.submit(new Consumer(4, "four",100, cache));
//        pool.submit(new Consumer(4, "four",100, cache));
//        pool.submit(new Consumer(5, "five",100, cache));
//        pool.submit(new Consumer(5, "five",100, cache));
//        pool.submit(new Consumer(1, "one",100, cache));
//        pool.submit(new Consumer(6, "six",100, cache));
//        pool.submit(new Consumer(1, "one",100, cache));
//        pool.shutdown();
//        try {
//            pool.awaitTermination(5, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            pool.shutdownNow();
//        }
//        cache.print();


    }
}

class Print implements Runnable{
    private final LRUCache<Integer, String> cache;

    Print(LRUCache<Integer, String> cache) {
        this.cache = cache;
    }

    @Override
    public void run() {
        cache.print();
        sleep();
    }
    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Consumer implements Runnable {
    private final int key;
    private final String value;
    private final int sleep;
    private final LRUCache<Integer, String> cache;

    public Consumer(int key, String value, int sleep, LRUCache<Integer, String> cache) {
        this.key = key;
        this.value = value;
        this.sleep = sleep;
        this.cache = cache;
    }

    @Override
    public void run() {
        System.out.println(LocalDateTime.now() + "|"+ key + " was accessed ");
        String data = cache.get(key);
        if(data == null){
            cache.put(key, value);
        }
        sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Node<K,V>{
    private final K key;
    private final V value;

    Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    K getKey(){
        return this.key;
    }

    V getValue() {
        return this.value;
    }
}

class LRUCache<K, V> {
    private final int capacity;
//    private final Map<K, V> map;
    private final Map<K, Node<K,V>> map;
    private final Deque<Node<K,V>> deque;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        // hash map doesnot enforce capacity
        // this is just for optimization to capture initial heap space
//        map = new HashMap<>(capacity);

//        map = new LinkedHashMap<>(capacity, 0.75f, true);

        // linked hashmap with overriding works
//        map = new LinkedHashMap<>(capacity, 0.75f, true) {
//
//            // by default linkedHashmap does not remove eldestEntry
//            @Override
//            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
//                return size() > capacity;
//            }
//        };

        //  but LinkedHashMap is not threadsafe
        // we can use Collections.synchronizedMap() wrapper

//        map = Collections.synchronizedMap(new LinkedHashMap<>(capacity, 0.75f, true) {
//
//            // by default linkedHashmap does not remove eldestEntry
//            @Override
//            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
//                return size() > capacity;
//            }
//        });

        map = new ConcurrentHashMap<>(capacity);
        deque = new ArrayDeque<>(capacity);
    }

    void put(K key, V value) {
//        map.put(key, value);
        if(map.containsKey(key)){
            Node<K,V> old = map.get(key);
            deque.remove(old);
            map.remove(key);
        }else if(map.size() >= capacity){
            Node<K,V> node = deque.removeLast();
            map.remove(node.getKey());
        }
        Node<K,V> node = new Node<>(key, value);
        map.put(key, node);
        deque.addFirst(node);
    }

    V get(K key) {
//        return map.get(key);
        if(!map.containsKey(key)){
            return null;
        }
        Node<K,V> node = map.get(key);
        deque.remove(node);
        deque.addFirst(node);
        return node.getValue();
    }

    int size() {
        return map.size();
    }

    void print(){
        // synchornizemap lock get put method not iterator
        // print after pool is terminated
        System.out.print("[");
        for(K key: map.keySet()){
            System.out.print(key + ",");
        }
        System.out.println("]");
    }

//    void print() {
//        synchronized (map) {
//            System.out.print("[");
//            for (K key : map.keySet()) {
//                System.out.print(key + ",");
//            }
//            System.out.println("]");
//        }
//    }

}