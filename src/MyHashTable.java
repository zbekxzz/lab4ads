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
    private LinkedList<HashNode<K, V>>[] chainArray; // linked list to store nodes
    private int M = 11;
    private int size; // table size
    /**
     * MyHashTable - constructor to create hash table
     * @noparam
     * @return void
     */
    public MyHashTable() {
        this.size = 0;
        this.chainArray = new LinkedList[M];
    }
    /**
     * MyHashTable - constructor with additional M to change
     * @param M - given M to change
     * @return void
     */
    public MyHashTable(int M) {
        this.M = M; // changing M field
        this.size = 0;
        this.chainArray = new LinkedList[M];
    }
    /**
     * hash - method to hashing key
     * @param key - given key to hash
     * @return int
     */
    private int hash(K key) {
        return key.hashCode() % M; // hashing
    }
    /**
     * put - method puts a value in hash table with key
     * @param key - given key to put within
     * @param value - given value to put
     * @return void
     */
    public void put(K key, V value) {
        int index = hash(key); // getting index with hashing
        if (chainArray[index] == null) {
            chainArray[index] = new LinkedList<HashNode<K, V>>(); // way if hash table in this index is null
        }
        for (HashNode<K, V> node : chainArray[index]) {
            if (node.getKey().equals(key)) {
                node.setValue(value); // putting value in node
                return;
            }
        }
        chainArray[index].add(new HashNode<K, V>(key, value)); // putting node in hash table
        size++;
    }
    /**
     * get - method gets a value with key
     * @param key - given key to get value within
     * @return V - value type
     */
    public V get(K key) {
        int index = hash(key); // getting index with hashing
        if (chainArray[index] == null) return null; // way if index in hash table is null

        for (HashNode<K, V> node : chainArray[index]) {
            if (node.getKey().equals(key)) return node.getValue(); // returning value if key exist
        }
        return null; // returning null otherwise
    }
    /**
     * remove - method removes value with key
     * @param key - given key to remove value within
     * @return V - value type
     */
    public V remove(K key) {
        int index = hash(key); // getting index with hashing
        if (chainArray[index] == null) return null; // way if index in hash table is null

        for (HashNode<K, V> node : chainArray[index]) {
            if (node.getKey().equals(key)) {
                chainArray[index].remove(node); // removing node from hash table
                size--;
                return node.getValue(); // returning removed value
            }
        }
        size--;
        return null; // returning null otherwise
    }
    /**
     * contains - method checks is hash table contains given value
     * @param value - given value to check
     * @return boolean
     */
    public boolean contains(V value) {
        for (LinkedList<HashNode<K, V>> list : chainArray) {
            for (HashNode<K, V> node : list) {
                if (node.getValue().equals(value)) return true; // returning true if value is in hash table
            }
        }
        return false; // returning false otherwise
    }

    /**
     * getKey - method returns key of value
     * @param value - given value to get key
     * @return K - key type
     */
    public K getKey(V value) {
        for (LinkedList<HashNode<K, V>> list : chainArray) {
            for (HashNode<K, V> node : list) {
                if (node.getValue().equals(value)) return node.getKey(); // returning key of value if exist
            }
        }
        return null; // returning null otherwise
    }

    /**
     * getSize - method returns size of hashTable
     * @noparam
     * @return int
     */
    public int getSize() {
        return size;
    }
}
