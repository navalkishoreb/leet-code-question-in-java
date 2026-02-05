package lld.lru_cache.models;

public class DLLNode<K, V> extends Node<K, V> {
    private DLLNode<K, V> next;
    private DLLNode<K, V> prev;

    public DLLNode(K key, V value) {
        super(key, value);
        prev = null;
        next = null;
    }

    public DLLNode<K, V> getPrev() {
        return prev;
    }

    public DLLNode<K, V> getNext() {
        return next;
    }

    public void setNext(DLLNode<K, V> next) {
        this.next = next;
    }

    public void setPrev(DLLNode<K, V> prev) {
        this.prev = prev;
    }
}
