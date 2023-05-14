import java.util.LinkedList;
public class MyHashTable<K, V> {
    private class HashNode<K, V> { // inner class necessary to create hash table
        private K key; // field to store key
        private V value; // field to store value
        /**
         * HashNode - constructor to set values in fields
         * @param key - given key to set
         * @param value - given value to set
         * @return void
         */
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
        /**
         * getKey - method that returns key of node
         * @noparam
         * @return K - key type
         */
        public K getKey() {
            return key;
        }
        /**
         * getValue - method that returns value of node
         * @noparam
         * @return V - value type
         */
        public V getValue() {
            return value;
        }
        /**
         * setValue - method that set value in node
         * @param value - given value to set
         * @return void
         */
        public void setValue(V value) {
            this.value = value;
        }

        /**
         * toString - method that returns node with specific type
         * @noparam
         * @return String
         */
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }
    private LinkedList<HashNode<K, V>>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        this.size = 0;
        this.chainArray = new LinkedList[M];
    }
    public MyHashTable(int M) {
        this.M = M;
        this.size = 0;
        this.chainArray = new LinkedList[M];
    }
    private int hash(K key) {
        return key.hashCode() % M;
    }
    public void put(K key, V value) {
        int index = hash(key);
        if (chainArray[index] == null) {
            chainArray[index] = new LinkedList<HashNode<K, V>>();
        }
        for (HashNode<K, V> node : chainArray[index]) {
            if (node.getKey().equals(key)) {
                node.setValue(value);
                return;
            }
        }
        chainArray[index].add(new HashNode<K, V>(key, value));
        size++;
    }
    public V get(K key) {
        int index = hash(key);
        if (chainArray[index] == null) return null;

        for (HashNode<K, V> node : chainArray[index]) {
            if (node.getKey().equals(key)) return node.getValue();
        }
        return null;
    }
    public V remove(K key) {
        int index = hash(key);
        if (chainArray[index] == null) return null;

        for (HashNode<K, V> node : chainArray[index]) {
            if (node.getKey().equals(key)) {
                chainArray[index].remove(node);
                size--;
                return node.getValue();
            }
        }
        size--;
        return null;
    }
    public boolean contains(V value) {
        for (LinkedList<HashNode<K, V>> list : chainArray) {
            for (HashNode<K, V> node : list) {
                if (node.getValue().equals(value)) return true;
            }
        }
        return false;
    }
    public K getKey(V value) {
        for (LinkedList<HashNode<K, V>> list : chainArray) {
            for (HashNode<K, V> node : list) {
                if (node.getValue().equals(value)) return node.getKey();
            }
        }
        return null;
    }
    public int getSize() {
        return size;
    }
}
