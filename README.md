# ⭐ Assignment 4  |  Karakuzov Bekbolat
## 🔔 MyHashTable.java
### 📘 function: hash
> **Description:** This function takes a key of type K and returns the hash value of the key. It uses the hashCode() method of the key object and performs modulo operation with M (size of the hash table) to determine the index where the key-value pair will be stored.
```java
private int hash(K key) {
    return key.hashCode() % M;
}
```

### 📘 function: put
> **Description:** This function is used to insert a key-value pair into the hash table. It calculates the index using the hash function, and if the chain at that index is null, it creates a new LinkedList to store multiple key-value pairs. It then iterates over the chain to check if the key already exists. If found, it updates the value; otherwise, it adds a new HashNode with the given key and value.
```java
public void put(K key, V value) {
    int index = hash(key); // getting index with hashing
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
```

### 📘 function: get
> **Description:** This function is used to retrieve the value associated with a given key from the hash table. It calculates the index using the hash function and then iterates over the chain at that index to find the matching key. If found, it returns the corresponding value; otherwise, it returns null.
```java
public V get(K key) {
    int index = hash(key);
    if (chainArray[index] == null) return null;
    for (HashNode<K, V> node : chainArray[index]) {
        if (node.getKey().equals(key)) return node.getValue();
    }
    return null;
}
```

### 📘 function: remove
> **Description:** This function is used to remove a key-value pair from the hash table. It calculates the index using the hash function and then iterates over the chain at that index to find the matching key. If found, it removes the corresponding HashNode from the chain, decrements the size, and returns the value; otherwise, it returns null.
```java
public V remove(K key) {
    int index = hash(key); // getting index with hashing
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
```

### 📘 function: contains
> **Description:** This function checks if the hash table contains a specific value. It iterates over all the chains in the hash table and checks if any HashNode's value matches the given value. If found, it returns true; otherwise, it returns false.
```java
public boolean contains(V value) {
    for (LinkedList<HashNode<K, V>> list : chainArray) {
        for (HashNode<K, V> node : list) {
            if (node.getValue().equals(value)) return true;
        }
    }
    return false;
}
```

### 📘 function: getKey
> **Description:** This function retrieves the key associated with a specific value in the hash table. It iterates over all the chains in the hash table and checks if any HashNode's value matches the given value
```java
public K getKey(V value) {
    for (LinkedList<HashNode<K, V>> list : chainArray) {
        for (HashNode<K, V> node : list) {
            if (node.getValue().equals(value)) return node.getKey();
        }
    }
    return null;
}
```

### 📘 function: getSize
> **Description:** This function returns size of hash table with int
```java
public int getSize() {
    return size;
}
```
